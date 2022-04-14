package com.tron.common.mqtt

import android.content.Context
import android.os.Parcelable
import android.util.Log
import androidx.annotation.RawRes
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.util.*


class MqttClientHelper(context: Context, @RawRes crt: Int, serverUri: String, clientId: String = MqttClient.generateClientId()) {

    companion object {
        const val TAG = "MqttClientHelper"
    }

    private var mqttAndroidClient: MqttAndroidClient = MqttAndroidClient(context, serverUri, clientId)
    private var topics = ArrayList<Topic<out Parcelable>>()
    private var socketFactory: SocketFactory
    private var reconnect = false

    var callbackConnect: ((Boolean) -> Unit?)? = null
    set(value) {
        field = value
        value?.invoke(isConnect())
    }

    init {
        socketFactory = SocketFactory(
            SocketFactory.SocketFactoryOptions().also {
                it.withCaInputStream(context.resources.openRawResource(crt))
            })

        mqttAndroidClient.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.e(TAG, "Receive message: ${message.toString()} from topic: $topic")
                message?.payload?.let { bytes ->
                    val msg = String(bytes)
                    topics.find { it.topic == topic }?.onMessage(msg, context)
                }
            }

            override fun connectionLost(cause: Throwable?) {
                Log.e(TAG, "Connection lost ${cause.toString()}")
                callbackConnect?.invoke(false)
                this@MqttClientHelper.callbackConnect?.invoke(false)
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {

            }
        })
    }

    fun <T : Parcelable> subscribe(topic: Topic<T>, qos: Int = 1) {
        try {
            val find = topics.find { it == topic }
            if (find == null) {
                topics.add(topic)

                mqttAndroidClient.subscribe(topic.topic, qos, null, object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.e(TAG, "Subscribed to $topic")
                        topic.subscribeListener(true)
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.e(TAG, "Failed to subscribe $topic")
                        topic.subscribeListener(false)
                    }
                })
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun unsubscribe(topic: Topic<*>) {
        try {
            val find = topics.find { it == topic }
            if (find != null) {
                topics.remove(topic)
                mqttAndroidClient.unsubscribe(topic.topic, null, object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        Log.e(TAG, "Unsubscribed to $topic")
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        Log.e(TAG, "Failed to unsubscribe $topic")
                    }
                })
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun publish(topic: String, msg: String, qos: Int = 1, retained: Boolean = false, callbackPublish: ((isPublish: Boolean) -> Unit?)? = null) {
        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            message.qos = qos
            message.isRetained = retained
            mqttAndroidClient.publish(topic, message, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.e(TAG, "$msg published to $topic")
                    callbackPublish?.invoke(true)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.e(TAG, "Failed to publish $msg to $topic")
                    callbackPublish?.invoke(false)
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        try {
            mqttAndroidClient.disconnect(null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.e(TAG, "Disconnected")
                    this@MqttClientHelper.callbackConnect?.invoke(false)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.e(TAG, "Failed to disconnect")
                    this@MqttClientHelper.callbackConnect?.invoke(false)
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun connect(callbackConnect: ((isConnect: Boolean) -> Unit?)? = null) {
        try {
            val options = MqttConnectOptions().apply {
                isAutomaticReconnect = true
                isCleanSession = true
                isAutomaticReconnect = true
                socketFactory = this@MqttClientHelper.socketFactory

            }
            mqttAndroidClient.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.e(TAG, "Connection success")
                    callbackConnect?.invoke(true)
                    this@MqttClientHelper.callbackConnect?.invoke(true)

                    if (reconnect) {
                        val ts = ArrayList(topics)
                        topics.clear()
                        ts.forEach { subscribe(it) }
                        reconnect = false
                        Log.e(TAG, "ReConnection success")
                    }
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.e(TAG, "Connection failure")
                    callbackConnect?.invoke(false)
                    this@MqttClientHelper.callbackConnect?.invoke(false)
                    reconnect = true
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
            callbackConnect?.invoke(false)
            this@MqttClientHelper.callbackConnect?.invoke(false)
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            e.printStackTrace()
            callbackConnect?.invoke(false)
            this@MqttClientHelper.callbackConnect?.invoke(false)
        }
    }

    fun isConnect() = mqttAndroidClient.isConnected

    fun destroy() {
        mqttAndroidClient.unregisterResources()
        mqttAndroidClient.close()
    }

}
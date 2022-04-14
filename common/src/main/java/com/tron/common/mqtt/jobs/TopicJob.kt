package com.tron.common.mqtt.jobs

import android.content.Context
import android.media.RingtoneManager
import com.tron.common.mqtt.Payload
import com.tron.common.mqtt.Topic

class TopicJob : Topic<Payload>(Payload::class) {

    companion object {

        private lateinit var topicJob: TopicJob

        fun getInstance() : TopicJob {
            if (!this::topicJob.isInitialized) topicJob = TopicJob()

            return topicJob
        }
    }

    private var isSubscribe = false
    private val onListeners = ArrayList<OnListener>()

    override fun onMessage(string: String, context: Context) {
        super.onMessage(string, context)
        try {
            val r = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getMessage(job: String, payload: Payload) {
        onListeners.find { it.job == job }?.getMessage(payload)
    }

    fun addListener(onListener: OnListener) {
        val find = onListeners.find { it.job == onListener.job }

        if (isSubscribe) onListener.subscribeListener(isSubscribe)
        if (find == null) onListeners.add(onListener)
    }

    fun unListener(onListener: OnListener) {
        val find = onListeners.find { it.job == onListener.job }
        if (find != null) onListeners.remove(find)
        else onListeners.remove(onListener)
    }

    override fun subscribeListener(isSubscribe: Boolean) {
        this.isSubscribe = isSubscribe
        onListeners.forEach { it.subscribeListener(isSubscribe) }

    }

    fun isSubscribe() = isSubscribe

    abstract class OnListener(val job: String) {

        open fun subscribeListener(isSubscribe: Boolean) {}
        abstract fun getMessage(payload: Payload)
    }
}
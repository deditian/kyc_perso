package com.tron.common

import android.app.Application
import com.tron.common.mqtt.MqttClientHelper
import com.tron.common.util.Config
import com.tron.common.util.Pref

abstract class BaseApp : Application() {

    companion object {
        lateinit var mqttTerminal: MqttClientHelper
        lateinit var mqttUser: MqttClientHelper
    }

    override fun onCreate() {
        super.onCreate()
        setupDefaultEnvironment()
    }

    open fun setupDefaultEnvironment() {
        Pref.init(this.applicationContext)

        // hardcode the env here:
        if(BuildConfig.DEBUG) {
            if (Pref.getInt(Pref.ENV) == 0) Pref.setInt(
                Pref.ENV, Config.DEV_VM1)
        } else {
            Pref.setInt(Pref.ENV, Config.PROD)
        }
        Config.init()

        mqttTerminal = MqttClientHelper(baseContext, Config.MQT_CRT, Config.MQT_SSL_TERMINAL)
        mqttUser = MqttClientHelper(baseContext, Config.MQT_CRT, Config.MQT_SSL_USER)
    }

}
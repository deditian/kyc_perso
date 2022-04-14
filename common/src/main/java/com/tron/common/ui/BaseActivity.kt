package com.tron.common.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tron.common.R
import com.tron.common.SetupApp
import com.tron.common.databinding.LayoutLoadingBinding
import com.tron.common.mqtt.Payload
import com.tron.common.mqtt.jobs.TopicJob
import com.tron.common.util.Config
import com.tron.common.util.Constants
import com.tron.common.util.Pref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity() {

    protected val tag = this::class.java.simpleName
    private lateinit var envLabel: TextView
    private var progressDialog: Dialog? = null

    private val logOutJob = object : TopicJob.OnListener("log_out") {
        override fun getMessage(payload: Payload) {
            if (payload.data != "") logOutApp()
        }
    }

    override fun onStart() {
        super.onStart()
        setEnvironmentLabel()
    }

    override fun onResume() {
        super.onResume()
//        locationHelper.start(this)
//        monitoringManager.resume()

        TopicJob.getInstance().addListener(logOutJob)
    }

    override fun onPause() {
        super.onPause()
//        locationHelper.stopUsingGPS()
//        monitoringManager.pause()
//        TopicJob.getInstance().unListener(logOutJob)
    }

    private fun setEnvironmentLabel() {
        val envStr: String = getEnvLabel()
        val contentLayout = findViewById<ViewGroup>(android.R.id.content)
        if (!this::envLabel.isInitialized) {
            envLabel = TextView(this)
            envLabel.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            envLabel.text = envStr
            envLabel.gravity = Gravity.END
            envLabel.textSize = 7f
            envLabel.setTextColor(ContextCompat.getColor(this, R.color.gray_active))
            contentLayout.addView(envLabel)
        } else {
            envLabel.text = envStr
        }
    }

    fun getEnvLabel(): String {
        val env = Pref.getInt(Pref.ENV)
        var envStr = ""
        when (env) {
            Config.DEV1 -> envStr = "dev"
            Config.DEV2 -> envStr = "dev2"
            Config.DEV_VM1 -> envStr = "dev vm"
            Config.DEV_VM2 -> envStr = "dev vm2"
            Config.DEV_DOCKER1 -> envStr = "docker"
            Config.DEV_DOCKER2 -> envStr = "docker2"
            Config.DEV_NGINX1 -> envStr = "NGINX"
            Config.DEV_NGINX2 -> envStr = "NGINX2"

            Config.STAGE -> envStr = "stage"
            Config.STAGE_VM -> envStr = "stage vm"
            Config.STAGE_VM1 -> envStr = "stage vm2"
            Config.STAGE_DOCKER -> envStr = "stage docker"
            Config.STAGE_DOCKER1 -> envStr = "stage docker2"
            Config.STAGE_NGINX -> envStr = "stage nginx"
            Config.STAGE_NGINX1 -> envStr = "stage nginx2"

            Config.DEMO -> envStr = "demo"
            Config.DEMO_VM -> envStr = "demo vm"
            Config.DEMO_DOCKER -> envStr = "demo docker"
            Config.DEMO_NGINX -> envStr = "demo nginx"
            else -> {
            }
        }
        return envStr
    }

    protected fun logOutApp() {
        resetPaymentWithSwipePay()
        resetPrefValues()
        startActivity(Intent(this, SetupApp.SplashActivity.java))
    }

    protected open fun hideProgressDialog() {
        CoroutineScope(Dispatchers.Main).launch {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    fun showProgressDialog() {
        if (!isDestroyed && !isFinishing) {
            CoroutineScope(Dispatchers.Main).launch {
                if (progressDialog == null) {
                    progressDialog = Dialog(this@BaseActivity).apply {
                        val binding = LayoutLoadingBinding.inflate(LayoutInflater.from(this@BaseActivity))
                        setContentView(binding.root)
                        setCancelable(false)
                        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        if (!isShowing) show()
                    }
                }
            }
        }
    }

    companion object {

        @JvmStatic
        protected fun resetPaymentWithSwipePay() {
            Pref.setInt(Pref.INTENT_AMOUNT, 0)
            Pref.setBool(Pref.FROM_ANOTHER_APP, false)
            Pref.setBool(Pref.FROM_CLOUD_APP, false)
            Pref.setString(Pref.POS_REQUEST_TYPE, "")
            Pref.setString(Pref.PAYMENT_METHOD, "")
            Pref.setString(Pref.POS_CLOUD_POINTER, "")
        }

        @JvmStatic
        protected fun resetPrefValues() {
            Pref.setString(Constants.VAL_TOKEN, "")
            Pref.setString(Constants.LOGIN_CREDENTIAL, "")
            Pref.setString(Constants.DUKPT_COMPONENT_MODEL, "")
            Pref.setString(Pref.APP_CONFIG_QR, "")
            Pref.setString(Pref.APP_CONFIG_GENERAL, "")
            Pref.setString(Pref.PREPAID_BIN_AID, "")
            Pref.setLong(Pref.APP_CONFIG_QR_TIMESTAMP, 0L)
            Pref.setLong(Pref.APP_CONFIG_GENERAL_TIMESTAMP, 0L)
            Pref.setString(Pref.POS_EDC_TYPE, "1")
            Pref.setLong(Pref.LAST_TIME_LOGON_PCPS, 0L)
        }

    }


}
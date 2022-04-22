package com.tron.kyc_perso.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.tron.common.SetupApp
import com.tron.common.SetupApp.CheckBalanceActivity
import com.tron.common.databinding.ActivitySplashBinding
import com.tron.common.repositories.ApiResponse
import com.tron.common.util.*
import com.tron.kyc_perso.BuildConfig
import com.tron.kyc_perso.R
import com.tron.kyc_perso.ui.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
//    private val viewModel by obtainViewModel(ConfigViewModel::class)

    init {
        SetupApp.SplashActivity = SplashActivity::class
//        SetupApp.CheckBalanceActivity = CheckBalanceActivity::class
        SetupApp.VERSION_CODE = BuildConfig.VERSION_CODE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        lifecycleScope.launch {
//            delay(2000L)
//            gotoActivityNewTask(LoginActivity::class)
//        }

        binding.btnSplash.setOnClickListener {
            gotoActivityNewTask(LoginActivity::class)
        }


        Pref.setString(Constants.VAL_TOKEN_TECH, "")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

//        viewModel.responseBinAidList.observe(this) {
//            when(it) {
//                is ApiResponse.Error -> {
//                    showToast("Error download AID")
//                    if ("0F40" == it.error.responseCode || "0401" == it.error.responseCode) {
//                        gotoActivityNewTask(LoginActivity::class)
//                    } else gotoActivityNewTask(HomeActivity::class)
//                }
//                ApiResponse.Loading -> {}
//                is ApiResponse.Success -> {
//                    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
//                        this.putString("host_sam_cloud", it.data.sam_cloud_host)
//                        apply()
//                    }
//                    val prepaidString = MyFunctions.classToString(it.data)
//                    Pref.setString(Pref.PREPAID_BIN_AID, prepaidString)
//                    Pref.setString(Pref.PREPAID_INVOICE_CONFIG, it.data.invoice_number)
//
////                    CoroutineScope(Dispatchers.IO).launch {
////                        downloadBankInfo(it.data.aid_list)
////                        launch(Dispatchers.Main) {
////                            gotoActivityNewTask(HomeActivity::class)
////                        }
////                    }
//                }
//            }
//        }
    }

    override fun onStart() {
        super.onStart()
//        DeviceHelper.getInstance().connect(this) {
//            if (it) {
//                DeviceManager.setDeviceHelper(DeviceHelper.getInstance())
//                checkLoginCredential()
//
//                if (Pref.getString(Constants.SERIAL_NUMBER).isNullOrEmpty())
//                    Pref.setString(Constants.SERIAL_NUMBER, DeviceHelper.getInstance().serialNumber.also { sn -> Log.e("SplashActivity", sn) })
//
//                BaseApp.mqttTerminal.connect { isConnect -> if (isConnect) subscribeTerminal() }
//                Toast.makeText(this, "Hardware Device connect", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(this, "Hardware Device no connect", Toast.LENGTH_LONG).show()
//            }
//        }
    }

//    private fun subscribeTerminal() {
//        var serialNumber = Pref.getString(Constants.SERIAL_NUMBER)
//        serialNumber = if (serialNumber.isEmpty()) {
//            DeviceHelper.getInstance().serialNumber.also { Pref.setString(Constants.SERIAL_NUMBER, it) }
//        } else serialNumber
//        if (serialNumber.isNullOrEmpty()) return
//
//        BaseApp.mqttTerminal.subscribe(TerminalTopic.getInstance().setTopic("terminal/$serialNumber"))
//    }


//    private suspend fun downloadBankInfo(aidList: List<AId>) {
//        val bankInfoArrayList = ArrayList<BankInfo>()
//        aidList.forEach {
//            val bankInfo = viewModel.bankInfoExecute(it.batch_group_id)
//            if (bankInfo != null) {
//                Log.e("Splash", bankInfo.prepaid_name)
//                bankInfoArrayList.add(bankInfo.copy(id = it.batch_group_id))
//                Pref.setString(Pref.PREPAID_BANK_INFO_LIST, MyFunctions.classToString(bankInfoArrayList))
//            }
//        }
//    }

//    private fun checkLoginCredential() {
//        val loginCredential = Pref.getString(Constants.VAL_TOKEN)
//        if (loginCredential.isEmpty()) {
//            RSAAsyncTask(object : RSAAsyncTask.Notifier {
//                override fun onSuccessGetKey() {
//                    gotoActivityNewTask(LoginActivity::class)
//                }
//
//                override fun onErrorGetKey(message: String?) {
//                    showDialogCustom(message?:"Error get key RSA")
//                }
//            }).execute()
//        } else {
//            viewModel.binAidList()
//        }
//    }
}
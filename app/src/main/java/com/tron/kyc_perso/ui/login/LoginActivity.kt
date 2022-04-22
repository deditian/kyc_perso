package com.tron.kyc_perso.ui.login

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.core.widget.doOnTextChanged
import com.tron.common.ui.BaseActivity
import com.tron.common.databinding.ActivityLoginBinding
import com.tron.common.R
import com.tron.common.network.model.UserDeviceDetail
import com.tron.common.network.model.auth.model.AuthRequest
import com.tron.common.network.model.auth.model.User
import com.tron.common.repositories.ApiResponse
import com.tron.common.ui.LoginViewModel
import com.tron.common.util.*
import com.tron.kyc_perso.ui.register.RegisterActivity
import com.tron.kyc_perso.ui.splash.SplashActivity
import java.io.UnsupportedEncodingException
import java.lang.Exception


class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by obtainViewModel(LoginViewModel::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { validationLogin() }

        binding.signUp.setOnClickListener { gotoNewActivity(RegisterActivity::class) }

        binding.etUserId.editText?.doOnTextChanged { _, _, _, count ->
            binding.etUserId.isErrorEnabled = count == 0
            if (count == 0) binding.etUserId.error = getString(R.string.empty_id)
        }

        binding.etPassword.editText?.doOnTextChanged { _, _, _, count ->
            binding.etPassword.isErrorEnabled = count == 0
            if (count == 0) binding.etPassword.error = getString(R.string.empty_password)
        }
    }

    override fun onStart() {
        super.onStart()
        resetPaymentWithSwipePay()
        resetPrefValues()
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.e("TAG", "onPostCreate: "+binding.etUserId.editText!!.text.toString() )

        viewModel.responseLogin.observe(this) {
            when (it) {
                is ApiResponse.Error -> {
                    hideProgressDialog()
                    showDialogCustom(it.error)
                }
                ApiResponse.Loading -> showProgressDialog()
                is ApiResponse.Success -> {
                    hideProgressDialog()

                    val user: User = it.data
                    val token: String = user.token

                    Pref.setString(Constants.VAL_TOKEN, token)
                    setGetCredential(0)
                    val userDeviceDetail = UserDeviceDetail(
                        binding.etUserId.editText!!.text.toString(),
                        "",
                        user.deviceUserAddress ?: "",
                        user.merchantAddress2 ?: "",
                        user.merchantAddress1 ?: "",
                        user.deviceUserFirstName ?: "",
                        user.merchantName ?: "",
                        user.deviceUserLastName ?: "",
                        user.merchantId ?: "",
                    )
                    val userDetailJson = MyFunctions.classToString(userDeviceDetail)
                    Pref.setString(Constants.USER_DETAIL, userDetailJson)
                    Pref.setString(Constants.USER_DEVICE, userDeviceDetail.userName)
                    viewModel.accessToken()
                }
            }
        }

        viewModel.responseLoginTech.observe(this) {
            when (it) {
                is ApiResponse.Error -> {
                    hideProgressDialog()
                    showDialogCustom(it.error)
                }
                ApiResponse.Loading -> showProgressDialog()
                is ApiResponse.Success -> {
                    hideProgressDialog()
                    val myToken: String = it.data.token
                    Pref.setString(Constants.VAL_TOKEN_TECH, myToken)
                    viewModel.login(
                        AuthRequest(
                            deviceTimestamp = System.currentTimeMillis().toString(),
                            passHash = binding.etPassword.editText!!.text.toString(),
                            username = binding.etUserId.editText!!.text.toString()
                        )
                    )

                }
            }
        }

        viewModel.responseAccessToken.observe(this) {
            when (it) {
                is ApiResponse.Error -> {
                    showToast(getString(R.string.toast_download_token_failed))
                }
                ApiResponse.Loading -> {}
                is ApiResponse.Success -> {
                    showToast(getString(R.string.toast_download_token_success))
                    Log.e("Token", it.data.accessToken)
                    Pref.setString(Constants.ACCESS_TOKEN, it.data.accessToken)
                    gotoActivityNewTask(SplashActivity::class)
                }
            }
        }



    }

    private fun setGetCredential(mode: Int) {
        //encode
        if (mode == 0) {
            val userId: String = binding.etUserId.editText!!.text.toString()
            val password: String = binding.etPassword.editText!!.text.toString()
            Log.e("TAG", "setGetCredential: $userId $password" )
            val userDeviceDetail = UserDeviceDetail(userId, password)
            val userDeviceJsonString: String = MyFunctions.classToString(userDeviceDetail)
            try {
                val base64 = Base64.encodeToString(
                    userDeviceJsonString.toByteArray(charset("UTF-8")),
                    Base64.DEFAULT
                )
                Pref.setString(Constants.LOGIN_CREDENTIAL, base64)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        } else if (mode == 1) { //decode
            try {
                val token: String = Pref.getString(Constants.VAL_TOKEN)
                if (token.isEmpty()) return
                val base64: String = Pref.getString(Constants.LOGIN_CREDENTIAL)
                if (base64.isEmpty()) return
                val data = Base64.decode(base64, Base64.DEFAULT)
                val cred = String(data, charset("UTF-8"))
                val userDeviceDetail: UserDeviceDetail = MyFunctions.stringToClass(cred, UserDeviceDetail::class.java)
                if (cred.isEmpty()) return
                binding.etUserId.editText!!.setText(userDeviceDetail.userName)
                binding.etPassword.editText!!.setText(userDeviceDetail.password)
                validationLogin()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun validationLogin() {
        when {
            binding.etUserId.editText?.text!!.isEmpty() -> {
                binding.etUserId.isErrorEnabled = true
                binding.etUserId.error = getString(R.string.empty_id)
            }
            binding.etPassword.editText?.text!!.isEmpty() -> {
                binding.etPassword.isErrorEnabled = true
                binding.etPassword.error = getString(R.string.empty_password)
            }
            else -> viewModel.loginTech()
        }
    }
}
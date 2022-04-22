package com.tron.common.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tron.common.network.auth.model.PublicKeyRequest
import com.tron.common.network.auth.model.ResponseEnc
import com.tron.common.network.cardpay.GeneralRequest
import com.tron.common.network.model.GeneralResponse
import com.tron.common.network.model.UserRegistration
import com.tron.common.network.model.auth.Auth
import com.tron.common.network.model.auth.model.AuthRequest
import com.tron.common.network.model.auth.model.AuthResponse
import com.tron.common.network.model.auth.model.User
import com.tron.common.util.Config
import com.tron.common.util.Constants
import com.tron.common.util.MyFunctions
import com.tron.common.util.Pref

class AuthRepository(application: Application) {

    fun regis(regisPost: UserRegistration): LiveData<ApiResponse<User>> {
        val data = MutableLiveData<ApiResponse<User>>()

        val time = System.currentTimeMillis().toString()
//        val encryptedPassword = MyFunctions.encryptByMD5(regisPost.passHash)
//        val encryptedTimeWithPassword = MyFunctions.encryptBySHA256(time + encryptedPassword)

//        val request = regisPost.copy(passHash = encryptedTimeWithPassword, deviceTimestamp = time)
        data.value = ApiResponse.Loading

//        Auth.dongleRegistration(request, { response ->
//            val user: User = MyFunctions.stringToClass(response, User::class.java)
//            data.value = ApiResponse.Success(user)
//        }, { error ->
//            data.value = ApiResponse.Error(error)
//        })

        return data
    }

    fun login(authRequest: AuthRequest): LiveData<ApiResponse<User>> {
        val data = MutableLiveData<ApiResponse<User>>()

        val time = System.currentTimeMillis().toString()
        val encryptedPassword = MyFunctions.encryptByMD5(authRequest.passHash)
        val encryptedTimeWithPassword = MyFunctions.encryptBySHA256(time + encryptedPassword)

        val request = authRequest.copy(passHash = encryptedTimeWithPassword, deviceTimestamp = time)
        data.value = ApiResponse.Loading

        Auth.login(request, { response ->
            val user: User = MyFunctions.stringToClass(response, User::class.java)
            data.value = ApiResponse.Success(user)
        }, { error ->
            data.value = ApiResponse.Error(error)
        })

        return data
    }

    fun loginTech(): LiveData<ApiResponse<AuthResponse>> {
        val data = MutableLiveData<ApiResponse<AuthResponse>>()
        val time = System.currentTimeMillis().toString()
        val encryptedPassword = MyFunctions.encryptByMD5(Config.TECH_PWD)
        val encryptedTimeWithPassword = MyFunctions.encryptBySHA256(time + encryptedPassword)

        val request = AuthRequest(deviceTimestamp = time, username = Config.TECH_ID, passHash = encryptedTimeWithPassword)

        data.value = ApiResponse.Loading

        Auth.loginTech(request, { response ->
            val authResponse: AuthResponse =
                MyFunctions.stringToClass(response, AuthResponse::class.java)
            data.value = ApiResponse.Success(authResponse)
        }, { error ->
            data.value = ApiResponse.Error(error)
        })
        return data
    }


    fun logout(): LiveData<ApiResponse<GeneralResponse>> {
        val data = MutableLiveData<ApiResponse<GeneralResponse>>()
        val request = GeneralRequest().apply {
            deviceTimestamp = System.currentTimeMillis().toString()
        }

        data.value = ApiResponse.Loading
        Auth.logout(request, { response ->
            val generalResponse: GeneralResponse = MyFunctions.stringToClass(
                response,
                GeneralResponse::class.java
            )
            data.value = ApiResponse.Success(generalResponse)
        }, { error ->
            data.value = ApiResponse.Error(error)
        })
        return data
    }


    fun keyDownload() : LiveData<ApiResponse<ResponseEnc>> {
        val data = MutableLiveData<ApiResponse<ResponseEnc>>()

        data.value = ApiResponse.Loading

        val request = PublicKeyRequest(
            Pref.getString(Constants.SERIAL_NUMBER),
            System.currentTimeMillis().toString(),
            Pref.getString(Constants.PUBLIC_KEY))

        Auth.getApiKeyDownload(request, { response ->
            val responseEnc = MyFunctions.stringToClass(response, ResponseEnc::class.java)
            data.value = ApiResponse.Success(responseEnc)

        }, {error -> data.value = ApiResponse.Error(error) })

        return data
    }
}
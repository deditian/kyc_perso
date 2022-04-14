package com.tron.common.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tron.common.network.model.auth.model.AuthRequest
import com.tron.common.repositories.AuthRepository
import com.tron.common.repositories.SwipeTransRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : ViewModel() {

    private val authRepository = AuthRepository(application)
    private val swipeTransRepository = SwipeTransRepository(application)
    private val _loginTech = MutableLiveData<Unit>()
    private val _login = MutableLiveData<AuthRequest>()
    private val _accessToken = MutableLiveData<AuthRequest>()
    private val _keyDownload = MutableLiveData<Unit>()

    val responseLogin = Transformations.switchMap(_login) { authRepository.login(it)}
    val responseLoginTech = Transformations.switchMap(_loginTech) { authRepository.loginTech() }
    val responseAccessToken = Transformations.switchMap(_accessToken) { swipeTransRepository.accessToken(it) }
    val responseKeyDownload = Transformations.switchMap(_keyDownload) { authRepository.keyDownload() }

    fun login(authRequest: AuthRequest) = viewModelScope.launch {
        _login.value = authRequest
    }

    fun loginTech() = viewModelScope.launch {
        _loginTech.value = Unit
    }

    fun accessToken() = viewModelScope.launch {
        _login.value?.let {
            _accessToken.value = it
        }
    }
    fun keyDownload() = viewModelScope.launch {
        _keyDownload.value = Unit
    }
}
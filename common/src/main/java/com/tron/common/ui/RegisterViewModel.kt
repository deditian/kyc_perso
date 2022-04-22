package com.tron.common.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tron.common.network.model.UserRegistration
import com.tron.common.network.model.auth.model.AuthRequest
import com.tron.common.repositories.AuthRepository
import com.tron.common.repositories.SwipeTransRepository
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : ViewModel() {

    private val authRepository = AuthRepository(application)
    private val _regis = MutableLiveData<UserRegistration>()

    val responseRegis = Transformations.switchMap(_regis) { authRepository.regis(it)}

    fun register(regisPost: UserRegistration) = viewModelScope.launch {
        _regis.value = regisPost
    }


}
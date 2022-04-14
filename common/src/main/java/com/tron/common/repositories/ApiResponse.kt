package com.tron.common.repositories

import com.tron.common.network.model.GeneralResponse


sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val error: GeneralResponse) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}
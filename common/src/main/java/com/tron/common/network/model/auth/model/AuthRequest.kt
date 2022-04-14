package com.tron.common.network.model.auth.model

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("device_timestamp")
    val deviceTimestamp: String,
    @SerializedName("pass_hash")
    val passHash: String?,
    @SerializedName("username")
    val username: String?,
)

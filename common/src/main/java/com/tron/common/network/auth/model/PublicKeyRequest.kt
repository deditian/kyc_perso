package com.tron.common.network.auth.model

import com.google.gson.annotations.SerializedName

data class PublicKeyRequest(
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("device_timestamp")
    val deviceTimestamp: String,
    @SerializedName("pub_key")
    val pubKey: String,
)

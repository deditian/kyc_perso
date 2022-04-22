package com.tron.common.network.model

data class UserRegistration(
    var fullname: String = "",
    var photo_ktp: String = "",
    var nik: String = "",
    var kota_arus: String = "",
    var gender: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var city: String = "",
    var address: String = "",
)
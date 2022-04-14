package com.tron.common.network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Version implements Serializable {

    @SerializedName("device_crypto_ver")
    private String deviceCryptoVer;

    @SerializedName("credit_debit_cp_ver")
    private String creditDebitCpVer;

    public void setDeviceCryptoVer(String deviceCryptoVer) {
        this.deviceCryptoVer = deviceCryptoVer;
    }

    public String getDeviceCryptoVer() {
        return deviceCryptoVer;
    }

    public void setCreditDebitCpVer(String creditDebitCpVer) {
        this.creditDebitCpVer = creditDebitCpVer;
    }

    public String getCreditDebitCpVer() {
        return creditDebitCpVer;
    }
}
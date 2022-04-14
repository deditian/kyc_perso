package com.tron.common.network.model.auth.model;

import com.google.gson.annotations.SerializedName;
import com.tron.common.network.model.GeneralResponse;

import java.io.Serializable;

public class User extends GeneralResponse implements Serializable {

    @SerializedName("device_user_address")
    private String deviceUserAddress;

    @SerializedName("merchant_address2")
    private String merchantAddress2;

    @SerializedName("merchant_address1")
    private String merchantAddress1;

    @SerializedName("device_user_first_name")
    private String deviceUserFirstName;

    @SerializedName("merchant_name")
    private String merchantName;

    @SerializedName("device_user_last_name")
    private String deviceUserLastName;

    @SerializedName("token")
    private String token;

    public void setDeviceUserAddress(String deviceUserAddress) {
        this.deviceUserAddress = deviceUserAddress;
    }

    public String getDeviceUserAddress() {
        return deviceUserAddress;
    }

    public void setMerchantAddress2(String merchantAddress2) {
        this.merchantAddress2 = merchantAddress2;
    }

    public String getMerchantAddress2() {
        return merchantAddress2;
    }

    public void setMerchantAddress1(String merchantAddress1) {
        this.merchantAddress1 = merchantAddress1;
    }

    public String getMerchantAddress1() {
        return merchantAddress1;
    }

    public void setDeviceUserFirstName(String deviceUserFirstName) {
        this.deviceUserFirstName = deviceUserFirstName;
    }

    public String getDeviceUserFirstName() {
        return deviceUserFirstName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setDeviceUserLastName(String deviceUserLastName) {
        this.deviceUserLastName = deviceUserLastName;
    }

    public String getDeviceUserLastName() {
        return deviceUserLastName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
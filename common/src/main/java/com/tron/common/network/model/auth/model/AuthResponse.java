package com.tron.common.network.model.auth.model;

import com.google.gson.annotations.SerializedName;
import com.tron.common.network.model.GeneralResponse;

import java.io.Serializable;

public class AuthResponse extends GeneralResponse implements Serializable {

    @SerializedName("token")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
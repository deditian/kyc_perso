package com.tron.common.network.auth.model;

import com.google.gson.annotations.SerializedName;
import com.tron.common.network.model.GeneralResponse;

import java.io.Serializable;

public class ResponseEnc extends GeneralResponse implements Serializable {

    @SerializedName("enc_keys")
    private EncKeys encKeys;

    public void setEncKeys(EncKeys encKeys) {
        this.encKeys = encKeys;
    }

    public EncKeys getEncKeys() {
        return encKeys;
    }
}
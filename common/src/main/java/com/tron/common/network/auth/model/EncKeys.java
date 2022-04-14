package com.tron.common.network.auth.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EncKeys implements Serializable {

    @SerializedName("emv_ksn")
    private String emvKsn;

    @SerializedName("pin_ipek")
    private String pinIpek;

    @SerializedName("pin_block_mk_universal")
    private String pinBlockMkUniversal;

    @SerializedName("track_ksn")
    private String trackKsn;

    @SerializedName("amount_ksn")
    private String amountKsn;

    @SerializedName("general_ksn")
    private String generalKsn;

    @SerializedName("general_ipek")
    private String generalIpek;

    @SerializedName("amount_ipek")
    private String amountIpek;

    @SerializedName("emv_ipek")
    private String emvIpek;

    @SerializedName("pin_ksn")
    private String pinKsn;

    @SerializedName("track_ipek")
    private String trackIpek;

    @SerializedName("pin_block_wk_universal")
    private String pinBlockWkUniversal;

    public void setEmvKsn(String emvKsn) {
        this.emvKsn = emvKsn;
    }

    public String getEmvKsn() {
        return emvKsn;
    }

    public void setPinIpek(String pinIpek) {
        this.pinIpek = pinIpek;
    }

    public String getPinIpek() {
        return pinIpek;
    }

    public void setPinBlockMkUniversal(String pinBlockMkUniversal) {
        this.pinBlockMkUniversal = pinBlockMkUniversal;
    }

    public String getPinBlockMkUniversal() {
        return pinBlockMkUniversal;
    }

    public void setTrackKsn(String trackKsn) {
        this.trackKsn = trackKsn;
    }

    public String getTrackKsn() {
        return trackKsn;
    }

    public void setAmountKsn(String amountKsn) {
        this.amountKsn = amountKsn;
    }

    public String getAmountKsn() {
        return amountKsn;
    }

    public void setGeneralKsn(String generalKsn) {
        this.generalKsn = generalKsn;
    }

    public String getGeneralKsn() {
        return generalKsn;
    }

    public void setGeneralIpek(String generalIpek) {
        this.generalIpek = generalIpek;
    }

    public String getGeneralIpek() {
        return generalIpek;
    }

    public void setAmountIpek(String amountIpek) {
        this.amountIpek = amountIpek;
    }

    public String getAmountIpek() {
        return amountIpek;
    }

    public void setEmvIpek(String emvIpek) {
        this.emvIpek = emvIpek;
    }

    public String getEmvIpek() {
        return emvIpek;
    }

    public void setPinKsn(String pinKsn) {
        this.pinKsn = pinKsn;
    }

    public String getPinKsn() {
        return pinKsn;
    }

    public void setTrackIpek(String trackIpek) {
        this.trackIpek = trackIpek;
    }

    public String getTrackIpek() {
        return trackIpek;
    }

    public void setPinBlockWkUniversal(String pinBlockWkUniversal) {
        this.pinBlockWkUniversal = pinBlockWkUniversal;
    }

    public String getPinBlockWkUniversal() {
        return pinBlockWkUniversal;
    }
}
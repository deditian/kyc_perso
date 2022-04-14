package com.tron.common.network.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeneralResponse implements Serializable {

    @SerializedName(value = "response_code", alternate = "code")
    private String responseCode;

    @SerializedName("midware_timestamp")
    private String midwareTimestamp;

    @SerializedName("message")
    private String message;

    @SerializedName("version")
    private Version version;

    @SerializedName("status")
    private String status;

    @SerializedName("payment_channel_message")
    private String paymentChannelMessage;

    @SerializedName("print_receipt_merchant_name")
    private String printReceiptMerchantName;

    @SerializedName("print_receipt_address_line_1")
    private String printReceiptAddressLine1;

    @SerializedName("print_receipt_address_line_2")
    private String printReceiptAddressLine2;

    @SerializedName("merchant_id")
    private String merchantId;

    @SerializedName("data")
    private JsonObject data;

    public GeneralResponse() {

    }

    public GeneralResponse(GeneralResponse response) {
        responseCode = response.getResponseCode();
        midwareTimestamp = response.getMidwareTimestamp();
        message = response.getMessage();
        status = response.getStatus();
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setMidwareTimestamp(String midwareTimestamp) {
        this.midwareTimestamp = midwareTimestamp;
    }

    public String getMidwareTimestamp() {
        return midwareTimestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Version getVersion() {
        return version;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentChannelMessage() {
        return paymentChannelMessage;
    }

    public void setPaymentChannelMessage(String paymentChannelMessage) {
        this.paymentChannelMessage = paymentChannelMessage;
    }

    public String getPrintReceiptMerchantName() {
        return printReceiptMerchantName;
    }

    public void setPrintReceiptMerchantName(String printReceiptMerchantName) {
        this.printReceiptMerchantName = printReceiptMerchantName;
    }

    public String getPrintReceiptAddressLine1() {
        return printReceiptAddressLine1;
    }

    public void setPrintReceiptAddressLine1(String printReceiptAddressLine1) {
        this.printReceiptAddressLine1 = printReceiptAddressLine1;
    }

    public String getPrintReceiptAddressLine2() {
        return printReceiptAddressLine2;
    }

    public void setPrintReceiptAddressLine2(String printReceiptAddressLine2) {
        this.printReceiptAddressLine2 = printReceiptAddressLine2;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
package com.tron.common.network.cardpay;

import com.google.gson.annotations.SerializedName;

public class GeneralRequest {

    @SerializedName("device_timestamp")
    private String deviceTimestamp;

    @SerializedName("invoice_num")
    private String invoiceNum;

    @SerializedName("pass_hash")
    private String passHash;

    @SerializedName("approval_code")
    private String approvalCode;

    @SerializedName("rrn")
    private String rrn;

    @SerializedName("print_receipt_email")
    private String printReceiptEmail;

    @SerializedName("customer_signature")
    private String customerSignature;

    @SerializedName("mode")
    private String mode;

    @SerializedName("settlement_session_num")
    private int settlementSessionNumber;

    @SerializedName("pos_request_type")
    private String posRequestType;

    @SerializedName("pos_cloud_pointer")
    private String posCloudPointer;

    public String getCustomerSignature() {
        return customerSignature;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getSettlementSessionNumber() {
        return settlementSessionNumber;
    }

    public void setSettlementSessionNumber(int settlementSessionNumber) {
        this.settlementSessionNumber = settlementSessionNumber;
    }

    public void setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
    }

    public String getPrintReceiptEmail() {
        return printReceiptEmail;
    }

    public void setPrintReceiptEmail(String printReceiptEmail) {
        this.printReceiptEmail = printReceiptEmail;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public void setDeviceTimestamp(String deviceTimestamp) {
        this.deviceTimestamp = deviceTimestamp;
    }

    public String getDeviceTimestamp() {
        return deviceTimestamp;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getPosRequestType() {
        return posRequestType;
    }

    public void setPosRequestType(String posRequestType) {
        this.posRequestType = posRequestType;
    }

    public String getPosCloudPointer() {
        return posCloudPointer;
    }

    public void setPosCloudPointer(String posCloudPointer) {
        this.posCloudPointer = posCloudPointer;
    }

    @Override
    public String toString() {
        return
                "GeneralRequest{" +
                        "device_timestamp = '" + getDeviceTimestamp() + '\'' +
                        ",invoice_num = '" + getInvoiceNum() + '\'' +
                        ",pass_hash = '" + getPassHash() + '\'' +
                        ",approval_code = '" + getApprovalCode() + '\'' +
                        ",rrn = '" + getRrn() + '\'' +
                        ",print_receipt_email = '" + getPrintReceiptEmail() + '\'' +
                        "}";
    }
}
package com.tron.common.util;

/**
 * Created by User on 08/05/2018.
 */

public class Constants {

    public static final int REQUEST_CODE_INTRO = 1;
    public static final int REQUEST_CODE_PAIRING = 2;
    public static final int REQUEST_CODE_SMS = 3;
    public static final int REQUEST_CODE_PERMISSION_LOCATION = 4;
    public static final int REQUEST_CODE_PERMISSION_READ_PHONE_STATE = 5;
    public static final int REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 6;
    public static final int REQUEST_CODE_SIGNATURE = 7;
    public static final int REQUEST_CODE_BLUETOOTH = 8;
    public static final int REQUEST_CODE_NUMBER = 9;
    public static final int REQUEST_CODE_CAMERA = 11;
    public static final int REQUEST_CODE_SALE3RDPARTY = 12;

    public static final byte STATE_INCORRECT_PIN = 25;
    public static final byte STATE_PRINT_RECEIPT = 37;

    public static final String BASE64_SIGNATURE = "base64_signature";
    public static final String CUST_EMAIL = "cust_email";
    public static final String CUST_PHONE = "cust_phone";

    public static final String ASYNC_TASK_TYPE = "asyncTaskType";
    public static final String ASYNC_TASK_PARAMETER = "asyncTaskParameter";
    public static final String ASYNC_TASK_RESULT = "asyncTaskResult";
    public static final String ASYNC_TASK_BLUETOOTH = "asyncTaskBluetooth";
    public static final String ASYNC_TASK_SCAN = "asyncTaskScan";
    public static final String MESSAGE = "message";
    public static final String MENU_TYPE = "menu_type";
    public static final String RESPONSE_CODE = "response_code";
    public static final String BANK_RESPONSE_CODE_DEF = "bank_response_code_definition";
    public static final String BANK_RESPONSE_CODE = "bank_response_code";
    public static final String BONDED_BLUETOOTH = "bondedBluetooth";
    public static final String BLUETOOTH_SERIAL_NUMBER = "bluetoothSerialNumber";
    public static final String IS_CALLED = "isCalled";
    public static final String IS_CONNECTED = "isConnected";
    public static final String API_LOGIN = "login";
    //public static final String API_LOGIN = "user/login";
    public static final String API_PAYMENT = "credit_debit_cp/sale/sale_trx";
    public static final String API_ARPC_VERIFICATION = "credit_debit_cp/sale/arpc_result_check";
    public static final String API_SETTLEMENT = "credit_debit_cp/settlement";
    public static final String API_DETAIL_TRANSACTIONS = "detail_transactions";
    public static final String API_IS_DEBIT_CHECK = "credit_debit_cp/sale/is_debit_check";
    public static final String API_MAKE_REVERSAL = "credit_debit_cp/sale/reversal";
    public static final String API_PRINT_RECEIPT_SALE = "credit_debit_cp/sale/print_receipt";
    public static final String API_PRINT_RECEIPT_VOID = "credit_debit_cp/void/print_receipt";
    public static final String RSPCD_SETTLEMENT_SUCCESS = "0060";
    public static final String RSPCD_FIRST_COPY_SUCCESS = "0040";
    public static final String RSPCD_DUPLICATE_PRINT_SUCCESS = "0041";
    public static final String RSPCD_SUMMARYRPT_SUCCESS = "0060";
    public static final String RSPCD_GETALLSALE_SUCCESS = "0030";
    public static final String RSPCD_GETALLVOID_SUCCESS = "0033";
    public static final String RSPCD_DEBIT_CHECK = "00A0";
    public static final String RSPCD_INVCHECK_SUCCESS = "0050";
    public static final String RSPCD_VOIDINVCHECK_SUCCESS = "0051";
    public static final String RSPCD_VOID_SUCCESS = "0031";
    public static final String RSPCD_LOGIN_SUCCESS = "0000";
    public static final String RSPCD_SAVE_CUST_SIGNATURE = "00B0";
    public static final String RSPCD_QR_LINKAJA_SUCCESS = "00B0";
    public static final String RSPCD_CHK_LINKAJA_SUCCESS = "00B1";
    public static final String RSPCD_PAYMENT_SUCCESS = "0020";
    public static final String RSPCD_MAKEREV_SUCCESS = "0021";
    public static final String RSPCD_DONGLE_REG_SUCCESS = "0010";
    public static final String RSPCD_KEY_DOWNLOAD_SUCCESS = "0011";
    public static final String DUPLICATE_RECEIPT = "print_receipt_duplicate_copy";
    public static final String MERCHANT_RECEIPT = "print_receipt_merchant_copy";
    public static final String CUSTOMER_RECEIPT = "print_receipt_customer_copy";
    public static final String SETTLEMENT_RECEIPT = "settlement_print_receipt";
    public static final String VOID_YN = "void_yn";
    public static final String REQ_LAST_TRX = "req_last_trx";
    public static final String RSPCD_INV_NOTFOUND = "0R00";


    public static final String TRACK2_HASH = "track_2_hash";
    public static final String TRACK2_LEN = "track_2_len";
    public static final String TRACK2_ENC = "track_2_enc";
    public static final String TRACK2_KSN_INDEX = "track_ksn_index";
    public static final String DEVICE_ID = "device_id";

    public static final String USERNAME = "username";
    public static final String PASS_HASH = "pass_hash";
    public static final String QR_DETAIL = "qr_detail";
    public static final String MERCHANT_TRX_ID = "merchant_trx_id";
    public static final String DATA_TRX = "data_trx";

    public static final String API_KEY_DOWNLOAD = "TechnicianMobAppHost/v1/key_download";
    public static final String API_DONGLE_REG = "TechnicianMobAppHost/v1/dongle_reg";
    public static final String API_TECHLOGIN = "TechnicianMobAppHost/v1/login";

    public static final String SWIPEREST_URL = "http://36.37.119.121:61055/swiperestwebservice";
    public static final String QRLINKAJA_URL = "qrlinkaja";
    public static final String CHKLINKAJA_URL = "chktrxlinkaja";
    public static final String TOPUPLINKAJA_URL = "topuplinkaja";
    public static final String INQPOINT_URL = "debit_credit/sale/inquiry_loyalty_point";


    public static final String VAL_TOKEN = "valToken";
    public static final String VAL_TOKEN_TECH = "valTokenTech";
    public static final String JSON_TOKEN = "token";
    public static final String AUTHORIZATION = "Authorization";
    public static final String LOGIN_CREDENTIAL = "login_credential";
    public static final String YAP_TID = "yap_tid";
    public static final String YAP_MID = "yap_mid";
    public static final String DUKPT_COMPONENT = "dukpt_component";
    public static final String DUKPT_COMPONENT_MODEL = "dukpt_component";

    public static final String SERIAL_NUMBER = "serial_number";

    public static final String PUBLIC_KEY = "public_key";
    public static final String PRIVATE_KEY = "private_key";

    public static final String GET_SUCCESS_LIST = "credit_debit_cp/additional_service/get_all_success_sale_list"; //debit_credit/void1/get_all_success_sale_list
    public static final String GET_VOID_LIST = "credit_debit_cp/additional_service/get_all_success_void_list";
    public static final String INV_CHECK = "credit_debit_cp/additional_service/sale_invoice_check";
    public static final String VOID_INV_CHECK = "credit_debit_cp/additional_service/void_invoice_check";
    public static final String DO_VOID = "credit_debit_cp/void/void_trx";
    public static final String SUMMARY_REPORT = "credit_debit_cp/additional_service/summary_report";
    public static final String API_SAVE_SIGNATURE = "credit_debit_cp/sale/save_customer_signature";
    public static final String API_VOID_SAVE_SIGNATURE = "credit_debit_cp/void/save_customer_signature";
    public static final String REPRINT_LAST_TRX = "credit_debit_cp/additional_service/reprint_last_trx";
    public static final String REPRINT_LAST_STL = "credit_debit_cp/additional_service/reprint_last_settlement";

    public static final String INVOICE_NUM = "invoice_num";
    public static final String APPROVAL_CODE = "approval_code";
    public static final String RRN = "rrn";
    public static final String EMV_KSN_INDEX = "emv_ksn_index";
    public static final String EMV_RES_ENC = "emv_res_enc";
    public static final String EMV_RES_LEN = "emv_res_len";
    public static final String REVERSAL_INVOICE_NUMBER = "invoice_num_reversal";
    public static final String MIDWARE_TIMESTAMP = "midware_timestamp";
    public static final String DETAIL_TRX_LIST = "detail_trx_list";
    public static final String TRX_TYPE = "trx_type";
    public static final String ENTRY_MODE = "entry_mode";
    public static final String TRX_TIME = "trx_time";
    public static final String TRX_DATE = "trx_date";
    public static final String TRX_HOST_TIME = "trx_host_time";
    public static final String TRX_HOST_DATE = "trx_host_date";
    public static final String BASE_AMOUNT = "base_amount";
    public static final String MASKED_PAN = "masked_pan";
    public static final String TRX_TYPE_SALE = "SALE";
    public static final String TRX_TYPE_VOID = "VOID";
    public static final String TYPE_DEBIT = "DEBIT";
    public static final String TYPE_CREDIT = "CREDIT";
    public static final String FEEDBACK = "feedback";
    public static final String MYNUMBER = "mynumber";
    public static final String RESULT_NUMBER = "resultnumber";
    public static final String TRACE_NUMBER = "trace_number";
    public static final String APPR_CODE = "appr_code";
    public static final String BATCH_RVW_TYPE = "batch_rvw_type";
    public static final String DEVICE_TIMESTAMP = "device_timestamp";
    public static final String SUCCESS_SALE_LIST = "success_sale_list";
    public static final String PRINT_RECEIPT_EMAIL = "print_receipt_email";
    public static final String PRINT_RECEIPT_PHONE_NUM = "print_receipt_phone_num";
    public static final String CUSTOMER_SIGNATURE = "customer_signature";

    public static final String API_OPEN_ACCOUNT = "open_account";
    public static final String API_CASH_WITHDRAWAL = "cash_withdrawal";
    public static final String API_SEND_MONEY = "send_money";
    public static final String API_INQ_BILL_PAY = "trx_demo/bill_payment/inquiry";
    public static final String API_BILL_PAY = "trx_demo/bill_payment/pay";
    public static final String API_LOAN = "trx_demo/loan";

    public static final String RSPCD_DEMO_SUCCESS = "0010";
    public static final String RSPCD_OPACC_SUCCESS = "0050";
    public static final String RSPCD_INQBILL_SUCCESS = "0090";
    public static final String RSPCD_INQPAY_SUCCESS = "00A0";
    public static final String RSPCD_LOAN_SUCCESS = "0080";
    public static final String AMOUNT = "amount";
    public static final String PRODUCT_CODE = "product_code";
    public static final String IMG_CAPTURED = "img_captured";
    public static final String CANCEL_ON_PIN_INPUT = "cancel_on_pin_input";
    public static final String IS_DEBIT_FLAG = "is_debit_flag";
    public static final String CARD_TYPE = "cardType";
    public static final String BIN_RESULT = "bin_result";
    public static final String IS_KEY_UPDATED = "is_key_updated";
    public static final String REPRINT_FLAG = "is_reprint_flag";
    public static final String REPRINT_FROM_MENU = "reprint_from_menu";
    public static final String REPRINT_SETTLED_SALE = "reprint_settled_sale";
    public static final String PAYMENT_MENU = "payment_menu";

    public static final String CSM_DATA = "csm_data";
    public static final String MSISDN = "msisdn";
    public static final byte TRX_INQ_TOPUP_LINKAJA = 100;
    public static final byte TRX_TOPUP_LINKAJA = 101;
    public static final byte TRX_SEND_MONEY = 102;
    public static final byte TRX_LOAN = 103;
    public static final byte TRX_INQ_LOYALTY = 104;
    public static final byte TRX_FULL_LOYALTY = 105;
    public static final byte TRX_PARTIAL_LOYALTY = 106;

    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String YAP_URI_STAGING = "http://dashboard-mcmvisa.docotel.net/";
    public static final String YAP_STR_BUILDER = "service/string-builder/request";
    public static final String YAP_STR_INQUIRY = "service/inquiry/data";

    public static final String YAP_URI = "https://dashboard-merchant.spesolution.com/";

    //QR Payment
    public static final String WALLET_CODE = "wallet_code";
    public static final String QR_CRITERIA = "QR_CRITERIA";
    public static final String QR_ITEM_MODEL = "QR_ITEM_MODEL";
    public static final String REPRINT_RECEIPT_QR = "REPRINT";
    public static final String QR_PRINT = "QR_PRINT";
    public static final int LASTPRINT = 0;
    public static final int PAYMENT = 1;
    public static final String MODE = "MODEQR";

    //Json userd detail
    public static final String USER_DETAIL = "USERDETAIL";
    public static final String LAST_UPDATE = "LAST_UPDATE";
    public static final String USER_DEVICE = "USERDEVICE";

    // Json DUKPT Component
    public static final String DUKPT = "DUKPT";
    public static final String LAST_SETTLEMENT = "last_settlement";
    public static final String LANGUAGE = "language";

    // qr
    public static final int QR_CODE = 100;
    public static final int QR_RECEIPT = 101;
    public static final String PRINT_QR = "PRINTQR";

    public static final String SESSION_NUMBER = "session_number";
    public static final String TAG_PREPAID_WORK = "tag_prepaid_work";
    public static final String TAG_PREPAID_INIT_WORK = "tag_prepaid_init_work";
    public static final String TAG_PREPAID_PAYMENT_WORK = "tag_prepaid_payment_work";

    //SwipeTrans
    public static final String TRX_PREPAID_CARD = "PREPAID_CARD";
    public static final String TRX_QR_VOUCHER = "QR";
    public static final String METHOD_CREDIT_DEBIT = "credit_debit";
    public static final String METHOD_E_WALLET = "e_wallet";
    public static final String MODE_IN = "IN";
    public static final String MODE_OUT = "OUT";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String MARRY_CODE = "merry_code";
    public static final String MARRY_CODE_SENDER = "merry_code_sender";
    public static final String PAYMENT_POINTER = "payment_pointer";


    // trx type
    public static final int INSTALLMENT = 2;
    public static final int LOYALTY_POINT = 3;
    public static final int REFUND = 4;


    // mode card type
    public static final int CARD_TYPE_SWIPE = 3;
    public static final int CARD_TYPE_INSERT = 4;
}

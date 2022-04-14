package com.tron.common.util;


import com.tron.common.BuildConfig;
import com.tron.common.R;

public class Config {

    public static final int DEV1 = 0;
    public static final int DEV2 = 1;
    public static final int DEV_NGINX1 = 2;
    public static final int DEV_NGINX2 = 3;
    public static final int DEV_VM1 = 4;
    public static final int DEV_VM2 = 5;
    public static final int DEV_DOCKER1 = 6;
    public static final int DEV_DOCKER2 = 7;

    public static final int STAGE = 8;
    public static final int STAGE_VM = 9;
    public static final int STAGE_VM1 = 10;
    public static final int STAGE_DOCKER = 11;
    public static final int STAGE_DOCKER1 = 12;
    public static final int STAGE_NGINX = 13;
    public static final int STAGE_NGINX1 = 14;

    public static final int DEMO = 15;
    public static final int DEMO_VM = 16;
    public static final int DEMO_DOCKER = 17;
    public static final int DEMO_NGINX=  18;

    public static final int PROD = 19;

    public static String TECH_ID;
    public static String TECH_PWD;
    public static int MQT_CRT;
    private static String BASE_URL;
    public static String MQT_SSL_TERMINAL;
    public static String MQT_SSL_USER;
    public static String MERCHANT_URL;
    public static String TECH_URL;
    public static String SWIPE_TRANS_URL;
    public static String SWIPE_TRANS_PAYMENT_URL;
    public static String SWIPE_TRANS_TECH_URL;

    public static void init() {
        TECH_ID = getTechId();
        TECH_PWD = getTechPwd();
        BASE_URL = getBaseUrl();
        MQT_SSL_TERMINAL = getMqtSslTerminal();
        MQT_SSL_USER = getMqtSslUser();
        MQT_CRT = getCrt();
        MERCHANT_URL = getBaseUrl() + BuildConfig.MERCH_URL_PATH;
        TECH_URL = getBaseUrl() + BuildConfig.TECH_URL_PATH;
        SWIPE_TRANS_URL = getBaseUrl() + BuildConfig.SWIPE_TRANS_URL_PATH;
        SWIPE_TRANS_PAYMENT_URL = getBaseUrl() + BuildConfig.SWIPE_TRANS_PAYMENT_URL_PATH;
        SWIPE_TRANS_TECH_URL = getBaseUrl() + BuildConfig.SWIPE_TRANS_TECH_URL_PATH;
    }

    private static String getTechId() {
        int env = Pref.getInt(Pref.ENV);
        return env == PROD ? BuildConfig.TECH_ID_PROD : BuildConfig.TECH_ID_DEV;
    }

    private static String getTechPwd() {
        int env = Pref.getInt(Pref.ENV);
        return env == PROD ? BuildConfig.TECH_PROD : BuildConfig.TECH_DEV;
    }

    private static String getBaseUrl() {
        int env = Pref.getInt(Pref.ENV);
        switch (env) {
            case DEV1:
                return BuildConfig.BASE_URL_DEV;
            case DEV2:
                return BuildConfig.BASE_URL_DEV2;
            case DEV_VM1:
                return BuildConfig.BASE_URL_DEV_VM;
            case DEV_VM2:
                return BuildConfig.BASE_URL_DEV_VM1;
            case DEV_NGINX1:
                return BuildConfig.BASE_URL_DEV_NGINX;
            case DEV_NGINX2:
                return BuildConfig.BASE_URL_DEV_NGINX1;
            case DEV_DOCKER1:
                return BuildConfig.BASE_URL_DEV_DOCKER;
            case DEV_DOCKER2:
                return BuildConfig.BASE_URL_DEV_DOCKER1;

            case STAGE:
                return BuildConfig.BASE_URL_STAGE;
            case STAGE_VM:
                return BuildConfig.BASE_URL_STAGE_VM;
            case STAGE_VM1:
                return BuildConfig.BASE_URL_STAGE_VM1;
            case STAGE_DOCKER:
                return BuildConfig.BASE_URL_STAGE_DOCKER;
            case STAGE_DOCKER1:
                return BuildConfig.BASE_URL_STAGE_DOCKER1;
            case STAGE_NGINX:
                return BuildConfig.BASE_URL_STAGE_NGINX;
            case STAGE_NGINX1:
                return BuildConfig.BASE_URL_STAGE_NGINX1;

            case DEMO:
                return BuildConfig.BASE_URL_DEMO;
            case DEMO_VM:
                return BuildConfig.BASE_URL_DEMO_VM;
            case DEMO_DOCKER:
                return BuildConfig.BASE_URL_DEMO_DOCKER;
            case DEMO_NGINX:
                return BuildConfig.BASE_URL_DEMO_NGINX;
            case PROD:
                return BuildConfig.BASE_URL_PROD;
            default:
                return "";
        }
    }

    private static String getMqtSslTerminal() {
        int env = Pref.getInt(Pref.ENV);
        switch (env) {
            case DEV_VM1:
            case DEV_VM2:
            case DEV_NGINX1:
            case DEV_NGINX2:
            case DEV_DOCKER1:
            case DEV_DOCKER2:
                return BuildConfig.MQT_SSL_DEV_TERMINAL;

            case STAGE_DOCKER:
            case STAGE_DOCKER1:
            case STAGE_VM:
            case STAGE_VM1:
            case STAGE_NGINX:
            case STAGE_NGINX1:
            case STAGE:
                return BuildConfig.MQT_SSL_STAGE_TERMINAL;
            case DEMO_DOCKER:
            case DEMO_NGINX:
            case DEMO_VM:
            case DEMO:
                return BuildConfig.MQT_SSL_DEMO_TERMINAL;
            case PROD:
                return BuildConfig.MQT_SSL_PROD_TERMINAL;
            default:
                return "";
        }
    }

    private static String getMqtSslUser() {
        int env = Pref.getInt(Pref.ENV);
        switch (env) {
            case DEV_VM1:
            case DEV_VM2:
            case DEV_NGINX1:
            case DEV_NGINX2:
            case DEV_DOCKER1:
            case DEV_DOCKER2:
                return BuildConfig.MQT_SSL_DEV_USER;

            case STAGE_DOCKER:
            case STAGE_DOCKER1:
            case STAGE_VM:
            case STAGE_VM1:
            case STAGE_NGINX:
            case STAGE_NGINX1:
            case STAGE:
                return BuildConfig.MQT_SSL_STAGE_USER;
            case DEMO_DOCKER:
            case DEMO_NGINX:
            case DEMO_VM:
            case DEMO:
                return BuildConfig.MQT_SSL_DEMO_USER;
            case PROD:
                return BuildConfig.MQT_SSL_PROD_USER;
            default:
                return "";
        }
    }

    private static int getCrt() {
        int env = Pref.getInt(Pref.ENV);
        switch (env) {
            case PROD:
                return R.raw.m2mqtt_prod_ca;
            case STAGE_DOCKER:
            case STAGE_DOCKER1:
            case STAGE_VM:
            case STAGE_VM1:
            case STAGE_NGINX:
            case STAGE_NGINX1:
            case STAGE:
                return R.raw.m2mqtt_stage_ca;
            default:
                return R.raw.m2mqtt_dev_ca;
        }
    }
}

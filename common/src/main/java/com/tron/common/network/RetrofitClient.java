package com.tron.common.network;

import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tron.common.network.model.GeneralResponse;
import com.tron.common.network.model.auth.AuthAPI;
import com.tron.common.network.trans.TransAPI;
import com.tron.common.util.Config;
import com.tron.common.util.Constants;
import com.tron.common.util.MyFunctions;
import com.tron.common.util.Pref;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    public static final int MERCHANT = 0;
    public static final int TECH = 1;
    public static final int SWIPE_TRANS = 2;
    private static Retrofit retrofit = null;
    private static Retrofit retrofitTech = null;
    private static Retrofit retrofitTrans = null;

    public static void init() {
        retrofit = buildClient(Config.SWIPE_TRANS_PAYMENT_URL);
        retrofitTech = buildClient(Config.SWIPE_TRANS_TECH_URL);
        retrofitTrans = buildClient(Config.SWIPE_TRANS_URL);
    }

    private static Retrofit getClient(int type) {
        if (type == MERCHANT && retrofit == null) {
            retrofit = buildClient(Config.SWIPE_TRANS_PAYMENT_URL);
        } else if (type == TECH && retrofitTech == null) {
            retrofitTech = buildClient(Config.SWIPE_TRANS_TECH_URL);
        } else if (type == SWIPE_TRANS && retrofitTrans == null) {
            retrofitTrans = buildClient(Config.SWIPE_TRANS_URL);
        }
        Log.e("getClient", " "+ type);
        return type == MERCHANT ? retrofit : (type == SWIPE_TRANS ? retrofitTrans : retrofitTech);
    }

    private static Retrofit buildClient(String baseUrl) {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttpClient())
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
                .addInterceptor(getHttpInterceptor())
                .build();
    }

    private static Interceptor getHttpInterceptor() {
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            String currentLanguage = Pref.getString(Constants.LANGUAGE);
            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("language", currentLanguage)
                    .build();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder().url(url);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //HttpLoggingInterceptor.Level levelLog = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        HttpLoggingInterceptor.Level levelLog = HttpLoggingInterceptor.Level.BODY;
        logging.setLevel(levelLog);
        return logging;
    }

//    public static CardPayAPI cardPayAPI() {
//        return RetrofitClient.getClient(MERCHANT).create(CardPayAPI.class);
//    }

//    public static QRPayAPI qrPayAPI() {
//        return RetrofitClient.getClient(MERCHANT).create(QRPayAPI.class);
//    }

//    public static PrepaidPayAPI prepaidPayAPI() {
//        return RetrofitClient.getClient(MERCHANT).create(PrepaidPayAPI.class);
//    }

    public static AuthAPI authAPI(int type) {
        return RetrofitClient.getClient(type).create(AuthAPI.class);
    }

//    public static AppConfigAPI appConfigAPI() {
//        return RetrofitClient.getClient(MERCHANT).create(AppConfigAPI.class);
//    }

    public static TransAPI swipeTrans() {
        return RetrofitClient.getClient(SWIPE_TRANS).create(TransAPI.class);
    }

    public static void enqueue(Call<String> apiCall, OnSuccess onSuccess, OnFailure onFailure) {
        Call<String> apiCallClone = apiCall.clone();
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GeneralResponse generalResponse = MyFunctions.stringToClass(response.body(), GeneralResponse.class);
                    if (response.body().contains("response_code")) {
                        handleSuccessGeneralApi(response.body(), onSuccess, onFailure);
                    } else { // response.body().contains("code")
                        // prepaid api
                        if ("0098".equalsIgnoreCase(generalResponse.getResponseCode())) {
                            onFailure.onFailure(generalResponse);
                        } else if (generalResponse.getData() != null || "SUCCESS".equalsIgnoreCase(generalResponse.getStatus())) {
                            handleSuccessPrepaidApi(generalResponse, onSuccess, onFailure);
                        } else {
                            onFailure.onFailure(generalResponse);
                        }
                    }
                } else {
                    GeneralResponse error = buildError(response.message(), "0" + response.code());
                    onFailure.onFailure(error);
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                GeneralResponse error = getErrorResponse(t.getMessage());
                onFailure.onFailure(error);
            }
        });
    }

    private static void handleSuccessPrepaidApi(GeneralResponse generalResponse, OnSuccess onSuccess, OnFailure onFailure) {
        if (generalResponse.getData() != null) {
            String dataStr = MyFunctions.classToString(generalResponse.getData());
            onSuccess.onSuccess(dataStr);
        } else {
            onSuccess.onSuccess(generalResponse.getMessage());
        }
    }

    private static void handleSuccessGeneralApi(String responseBody, OnSuccess onSuccess, OnFailure onFailure) {
        GeneralResponse generalResponse = MyFunctions.stringToClass(responseBody, GeneralResponse.class);
        String responseCodeFull = generalResponse.getResponseCode();
        String responseCode = responseCodeFull.substring(0, 2);
        if ("00".equalsIgnoreCase(responseCode)
                || "0P00".equalsIgnoreCase(responseCodeFull)
                || "0P01".equalsIgnoreCase(responseCodeFull)) {
            onSuccess.onSuccess(responseBody);
        } else {
            onFailure.onFailure(generalResponse);
        }
    }

    public static void enqueue(final Call<String> apiCall, final OnResponse onResponse) {
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if (response.isSuccessful()) {
                    GeneralResponse generalResponse = MyFunctions.stringToClass(response.body(), GeneralResponse.class);
                    String responseCodeFull = generalResponse.getResponseCode();
                    String responseCode = responseCodeFull.substring(0, 2);
                    if ("00".equalsIgnoreCase(responseCode)
                            || "0P00".equalsIgnoreCase(responseCodeFull)
                            || "0P01".equalsIgnoreCase(responseCodeFull)) {
                        onResponse.onSuccess(response.body());
                    } else {
                        onResponse.onFailure(generalResponse);
                    }
                } else {
                    GeneralResponse error = buildError(response.message(),  "0" + response.code());
                    onResponse.onFailure(error);
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                GeneralResponse error = getErrorResponse(t.getMessage());
                onResponse.onFailure(error);
            }
        });
    }

    private static GeneralResponse getErrorResponse(String message) {
        if (message != null && message.toLowerCase().contains("timeout")) {
            message = "Service Timeout. Check jaringan internet atau hubungi teknisi swipepay";
        } else if (message != null && message.toLowerCase().contains("failed")) {
            message = "Koneksi gagal. Check jaringan internet atau hubungi teknisi swipepay";
        }
        return buildError(message, null);
    }

    private static GeneralResponse buildError(String message, String code) {
        if (message == null || message.isEmpty()) {
            message = "Koneksi gagal. Check jaringan internet atau hubungi teknisi swipepay";
        }
        if (code != null && !code.isEmpty() && code.equals("0401")) {
            message = "Session expired. Please re-login!";
        }

        GeneralResponse error = new GeneralResponse();
        error.setResponseCode((code != null && !code.isEmpty()) ? code : "XX");
        error.setMessage(message);
        return error;
    }

    public interface OnResponse {
        void onSuccess(String response);

        void onFailure(GeneralResponse error);
    }

    public interface OnSuccess {
        void onSuccess(String response);
    }

    public interface OnFailure {
        void onFailure(GeneralResponse error);
    }


}

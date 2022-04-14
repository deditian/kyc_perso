package com.tron.common.network.model.auth;

import com.tron.common.network.HeaderUtil;
import com.tron.common.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import retrofit2.Call;


public class Auth {

    public static void getApiKeyDownload(@NotNull Object body, @NotNull RetrofitClient.OnSuccess onSuccess, @NotNull RetrofitClient.OnFailure onFailure) {
        HashMap<String, String> headers = HeaderUtil.getTechHeaders();
        Call<String> apiCall = RetrofitClient.authAPI(RetrofitClient.TECH).getApiKeyDownload(headers, body);
        if (apiCall != null) RetrofitClient.enqueue(apiCall, onSuccess, onFailure);
    }

    public static void dongleRegistration(@NotNull Object body, @NotNull RetrofitClient.OnSuccess onSuccess, @NotNull RetrofitClient.OnFailure onFailure) {
        HashMap<String, String> headers = HeaderUtil.getTechHeaders();
        Call<String> apiCall = RetrofitClient.authAPI(RetrofitClient.TECH).getDongleRegistration(headers, body);
        if (apiCall != null) RetrofitClient.enqueue(apiCall, onSuccess, onFailure);
    }

    public static void loginTech(@NotNull Object body, @NotNull RetrofitClient.OnSuccess onSuccess, @NotNull RetrofitClient.OnFailure onFailure) {
        HashMap<String, String> headers = HeaderUtil.getHeadersPerso();
        Call<String> apiCall = RetrofitClient.authAPI(RetrofitClient.TECH).login(headers, body);
        if (apiCall != null) RetrofitClient.enqueue(apiCall, onSuccess, onFailure);
    }

//    public static void loginTech(@NotNull Object body, @NotNull RetrofitClient.OnSuccess onSuccess, @NotNull RetrofitClient.OnFailure onFailure) {
//        HashMap<String, String> headers = HeaderUtil.getTechHeaders();
//        Call<String> apiCall = RetrofitClient.authAPI(RetrofitClient.TECH).login(headers, body);
//        if (apiCall != null) RetrofitClient.enqueue(apiCall, onSuccess, onFailure);
//    }

    public static void login(@NotNull Object body, @NotNull RetrofitClient.OnSuccess onSuccess, @NotNull RetrofitClient.OnFailure onFailure) {
        HashMap<String, String> headers = HeaderUtil.getHeadersPerso();
        Call<String> apiCall = RetrofitClient.authAPI(RetrofitClient.MERCHANT).login(headers, body);
        if (apiCall != null) RetrofitClient.enqueue(apiCall, onSuccess, onFailure);
    }

    public static void logout(@NotNull Object body, @NotNull RetrofitClient.OnSuccess onSuccess, @NotNull RetrofitClient.OnFailure onFailure) {
        HashMap<String, String> headers = HeaderUtil.getHeaders();
        Call<String> apiCall = RetrofitClient.authAPI(RetrofitClient.MERCHANT).logout(headers, body);
        if (apiCall != null) RetrofitClient.enqueue(apiCall, onSuccess, onFailure);
    }
}

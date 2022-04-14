package com.tron.common.network.model.auth;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST("key_download")
    Call<String> getApiKeyDownload(
            @HeaderMap Map<String, String> headers,
            @Body Object body
    );

    @POST("dongle_reg")
    Call<String> getDongleRegistration(
            @HeaderMap Map<String, String> headers,
            @Body Object body
    );

    @POST("login")
    Call<String> login(
            @HeaderMap Map<String, String> headers,
            @Body Object body
    );

    @POST("logout")
    Call<String> logout(
            @HeaderMap Map<String, String> headers,
            @Body Object body
    );

}

package com.tron.common.network;


import com.tron.common.util.Constants;
import com.tron.common.util.Pref;

import java.util.HashMap;

public class HeaderUtil {

    public static HashMap<String, String> getHeadersPerso() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Hit-From", "swipepay");
        headers.put("Content-Type", "application/json");
        return headers;
    }

    public static HashMap<String, String> getHeaders() {
        String authToken = Pref.getString(Constants.VAL_TOKEN);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("authorization", authToken);
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }

    public static HashMap<String, String> getPrepaidHeaders() {
        String authToken = Pref.getString(Constants.ACCESS_TOKEN);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("authorization", authToken);
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }

    public static HashMap<String, String> getTechHeaders() {
        String authToken = Pref.getString(Constants.VAL_TOKEN_TECH);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("authorization", authToken);
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }

    public static HashMap<String, String> getTerminalHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }
}

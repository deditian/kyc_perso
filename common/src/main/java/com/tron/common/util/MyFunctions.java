package com.tron.common.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Donald on 2016. 5. 2..
 * Edited by Angga
 */
public class MyFunctions {
    private String TAG = this.getClass().getSimpleName();
    private Activity payByCard, signature;
    private boolean isLocationAllowed, isPhoneStateAllowed;
    private ProgressDialog progressDialog;
    private File mReceiptImageFile;
    private static boolean isSignature;
    private boolean is_DEMO;

    public static String getBinDisplay(String binResult) {
        String binDisplay = "";
        if (binResult != null && !binResult.isEmpty()) {
            String[] binWords = binResult.split("_");
            StringBuilder bin = new StringBuilder();
            for (int i = 0; i < binWords.length - 1; i++) {
                bin.append(binWords[i]).append(" ");
            }
            binDisplay = bin.toString();
        }
        return binDisplay;
    }

    public static String getEntryModeDisplay(String trxType, String entryMode) {
        String entryModeDisplay;
        if (entryMode.equals("051") || entryMode.equals("052")) {
            entryModeDisplay = trxType + " (Dip)";
        } else if (entryMode.equals("021")) {
            entryModeDisplay = trxType + " (Swipe)";
        } else {
            entryModeDisplay = trxType;
        }
        return entryModeDisplay;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    public static <T> T stringToClass(String stringToConvert, Class<T> classType) {
        try {
            return new Gson().fromJson(stringToConvert, classType);
        } catch (Exception e) {
            return null;
        }
    }


    public static String classToString(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }

    @Nullable
    public static Bitmap getBitmapFromBase64(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            date = new Date(System.currentTimeMillis());
        }
        return dateFormat.format(date);
    }

    public Date stringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date stringToDate = null;
        try {
            stringToDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringToDate;
    }

    public long dateToMilliseconds(Date date) {
        return date.getTime();
    }

    public String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
        String date = String.format("%02d", calendar.get(Calendar.DATE));
        return year + "-" + month + "-" + date;
    }

    public boolean isRegularID(String id) {
        boolean check = false;
        if (Pattern.matches("^[0-9]*$", id)) {
            check = true;
        }
        return check;
    }

    public void setPayByCardActivity(Activity activity) {
        payByCard = activity;
    }

    public void setSignatureActivity(Activity activity) {
        signature = activity;
    }

    public void finishPaymentActivities() {
        if (payByCard != null) {
            payByCard.finish();
        }
        if (signature != null) {
            signature.finish();
        }
        payByCard = null;
        signature = null;
    }

    public boolean getIsDemo() {
        return is_DEMO;
    }

    public void setIsDemo(boolean _isDEMO) {
        is_DEMO = _isDEMO;
    }

    public static boolean getIsSignature() {
        return isSignature;
    }

    public static void setIsSignature(boolean _isSignature) {
        isSignature = _isSignature;
    }

    public boolean getLocationPermission() {
        return isLocationAllowed;
    }

    public void setLocationAllowed(boolean permission) {
        isLocationAllowed = permission;
    }

    public boolean getPhoneStatePermission() {
        return isPhoneStateAllowed;
    }

    public void setPhoneStateAllowed(boolean permission) {
        isPhoneStateAllowed = permission;
    }

    public String byteArrayToString(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

    public String byteArrayToHex(byte[] bArray) {
        StringBuilder sb = new StringBuilder();
        for (final byte b : bArray) {
            sb.append(String.format("0x%02x ", b & 0xff));
        }
        return sb.toString();
    }

    public byte[] stringToByteArray(String encoded) {
        if (encoded.length() == 0) {
            return new byte[0];
        } else {
            encoded = encoded.replaceAll(" ", "");
            encoded = encoded.replaceAll("\\p{Z}", "");
            if (encoded.length() == 0) {
                return new byte[0];
            } else if (encoded.length() % 2 != 0) {
                throw new IllegalArgumentException("Input string must contain an even number of characters: " + encoded);
            } else {
                byte[] result = new byte[encoded.length() / 2];
                char[] enc = encoded.toCharArray();
                for (int i = 0; i < enc.length; i += 2) {
                    StringBuilder curr = new StringBuilder(2);
                    curr.append(enc[i]).append(enc[i + 1]);
                    result[i / 2] = (byte) Integer.parseInt(curr.toString(), 16);
                }
                return result;
            }
        }
    }

    public String byteToAsciiString(byte[] d) {
        int idx;
        int len = 0;
        final char[] ret;

        int s = 0;
        int n = d.length;

        int e = s + n;

        for (idx = s; idx < e; idx++, len++) {
            if (d[idx] == 0x0) break;
        }

        ret = new char[len];
        e = s + len;

        idx = 0;
        for (int i = s; i < e; ++i) {
            ret[idx++] = (char) d[i];
        }
        return new String(ret);
    }

    public String byteToString(byte b) {
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append("0123456789ABCDEF".charAt(b >> 4 & 15));
        sbBuffer.append("0123456789ABCDEF".charAt(b & 15));
        return sbBuffer.toString();
    }

    public byte[] encryptByRSA(byte[] buffer, String key) {
        byte[] byteData = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(key));
            byteData = cipher.doFinal(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteData;
    }

    private PublicKey getPublicKey(String pubKey) throws Exception {
        byte[] encoded = stringToByteArray(pubKey);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePublic(keySpec);
    }

//    public byte[] encryptByDESede(byte[] plain, byte[] key) {
//        byte[] encrypt = null;
//        try {
//            Security.addProvider(new BouncyCastleProvider());
//            SecretKey keySpec = new SecretKeySpec(key, "DESede");
//            IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//            Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding", "BC");
//            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
//            encrypt = cipher.doFinal(plain);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return encrypt;
//    }

    public byte[] decryptByDESede(byte[] encrypted, byte[] key) {
        byte[] utf8 = null;
        try {
            SecretKey keySpec = new SecretKeySpec(key, "DESede");
            IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            utf8 = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utf8;
    }

//    public byte[] encryptByDES(byte[] plain, byte[] key) {
//        byte[] encrypt = null;
//        try {
//            Security.addProvider(new BouncyCastleProvider());
//            SecretKey keySpec = new SecretKeySpec(key, "DES");
//            IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding", "BC");
//            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
//            encrypt = cipher.doFinal(plain);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return encrypt;
//    }

    public static byte[] decryptByDES(byte[] encrypted, byte[] key) {
        byte[] utf8 = null;
        try {
            SecretKey keySpec = new SecretKeySpec(key, "DES");
            IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            utf8 = cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utf8;
    }

    public byte[] encryptBySHA1(byte[] content) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md.digest();
    }

    public String makeNetworkParameter(Map<String, String> params) {
        String networkParam = null;
        Set<String> set = params.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            String key = String.valueOf(iterator.next());
            String value = String.valueOf(params.get(key));

            if (networkParam == null) {
                networkParam = key + "=" + value;
            } else {
                networkParam += "&" + key + "=" + value;
            }
        }

        return networkParam;
    }

    public static void makeAlert(Context context, String msg, String positiveString, DialogInterface.OnClickListener positiveListener, String negativeString, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveString, positiveListener);
        builder.setCancelable(false);
        if (negativeString != null) {
            builder.setNegativeButton(negativeString, negativeListener);
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    public String base64Encoding(String text) {
        return Base64.encodeToString(text.getBytes(), 0);
    }

    public String base64Decoding(String text) {
        return new String(Base64.decode(text, 0));
    }

    public File getScreenShot(View view) throws Exception {
        view.setDrawingCacheEnabled(true);
        Bitmap screenShot = view.getDrawingCache();
        String filename = "screenShot.png";

        try {
            mReceiptImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename);
            if (mReceiptImageFile.exists()) {
                mReceiptImageFile.delete();
            }
            mReceiptImageFile.createNewFile();
            OutputStream outStream = new FileOutputStream(mReceiptImageFile);
            screenShot.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.setDrawingCacheEnabled(false);
        return mReceiptImageFile;
    }

    public void deleteScreenShot() {
        if (mReceiptImageFile != null && mReceiptImageFile.exists()) {
            mReceiptImageFile.delete();
        }
    }

    public boolean isDateSame(String dateToCompare) {
        boolean isSame = false;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);

        if (dateToCompare == null || dateToCompare.equals("")) {
            isSame = false;
        } else if (year == Integer.valueOf(dateToCompare.split("-")[0])
                && month == Integer.valueOf(dateToCompare.split("-")[1])
                && date == Integer.valueOf(dateToCompare.split("-")[2])) {
            isSame = true;
        }
        return isSame;
    }

    public String intToOctalString(int num) {
        String octal = Integer.toOctalString(num);
        return String.format("%02d", Integer.valueOf(octal));
    }

    public String intToHexString(int num) {
        String hex = Integer.toHexString(num);
        return hex.length() == 1 ? "0" + hex : hex;
    }

    public int hexToInt(String hexString) {
        return Integer.parseInt(hexString, 16);
    }

    public String getStringAmount(String amount) {
        String stringAmount = String.format("%010d", Integer.valueOf(amount));
        return stringAmount + "00";
    }

    public static String encryptBySHA256(String txt) {
        StringBuilder stringBuffer = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(txt.getBytes());
            byte[] byteString = messageDigest.digest();
            for (byte tmpStrByte : byteString) {
                String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);
                stringBuffer.append(tmpEncTxt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String encryptByMD5(String txt) {
        Log.e("TAG", "encryptByMD5: "+txt );
        StringBuffer stringBuffer = new StringBuffer();
        String strKu = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(txt.getBytes());
            byte[] byteString = messageDigest.digest();
            strKu = bytesToHex(byteString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strKu.toLowerCase();
    }

    public static String fmtDateTime(String _date, String _time) {
        String myDate = null, myFmt, myTime;

        if (_date.substring(0, 2).equals("01")) myDate = "Jan ";
        else if (_date.substring(0, 2).equals("02")) myDate = "Feb ";
        else if (_date.substring(0, 2).equals("03")) myDate = "Mar ";
        else if (_date.substring(0, 2).equals("04")) myDate = "Apr ";
        else if (_date.substring(0, 2).equals("05")) myDate = "May ";
        else if (_date.substring(0, 2).equals("06")) myDate = "Jun ";
        else if (_date.substring(0, 2).equals("07")) myDate = "Jul ";
        else if (_date.substring(0, 2).equals("08")) myDate = "Agt ";
        else if (_date.substring(0, 2).equals("09")) myDate = "Sep ";
        else if (_date.substring(0, 2).equals("10")) myDate = "Okt ";
        else if (_date.substring(0, 2).equals("11")) myDate = "Nov ";
        else if (_date.substring(0, 2).equals("12")) myDate = "Des ";

        myTime = _time.substring(0, 2) + ":" + _time.substring(2, 4) + ":" + _time.substring(4, 6);
        myFmt = myDate + _date.substring(2, 4) + ", " + myTime;
        return myFmt;
    }

    public static String formatDateTime(String pattern) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(pattern);
        Date now = new Date();
        return dateTimeFormat.format(now);
    }

    public static String changeFormatDateTime(String inputDate, String inputPattern, String outputPattern) {
        DateFormat inputFormat = new SimpleDateFormat(inputPattern);
        DateFormat outputformat = new SimpleDateFormat(outputPattern);
        Date date;
        String output = "";
        try {
            //Conversion of input String to date
            date = inputFormat.parse(inputDate);
            //old date format to new date format
            output = outputformat.format(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return output;
    }

    public static String getRandomHexString() {
        Random r = new Random();
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 5) {
            builder.append(Integer.toHexString(r.nextInt()));
        }
        return builder.toString().substring(0, 5).toUpperCase();
    }

    public static  String getRandomNumber() {
        Random r = new Random();
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 8) {
            builder.append(r.nextInt());
        }
        return builder.substring(0, 8);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String asciiToHex(String asciiValue) {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    public static String hexStringToAsciiString(String hexString) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < hexString.length(); i += 2) {
            String str = hexString.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }


//    public static DUKPTComponentModel getDUKPTComponent(KeyModel keyModel, String privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException {
//        //decode by RSA private key
//        DUKPTComponentModel dukpt = new DUKPTComponentModel();
//        String decodePinKsn = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncPinKsn()), privateKey);
//        String decodeTrackKsn = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncTrackKsn()), privateKey);
//        String decodeEmvKsn = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncEmvKsn()), privateKey);
//        String decodeAmountKsn = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncAmountKsn()), privateKey);
//        String decodeGeneralKsn = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncGeneralKsn()), privateKey);
//        String decodePinIpek = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncPinIpek()), privateKey);
//        String decodeTrackIpek = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncTrackIpek()), privateKey);
//        String decodeEmvIpek = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncEmvIpek()), privateKey);
//        String decodeAmountIpek = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncAmountIpek()), privateKey);
//        String decodeGeneralIpek = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncGenerakIpek()), privateKey);
//        String decodePinBlockMkUniversal = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncPinBlockMk()), privateKey);
//        String decodePinBlockWkUniversal = RSAUtil.decryptByPrivateKey(MyFunctions.hexStringToByteArray(keyModel.getEncPinBlockWk()), privateKey);
//
//        dukpt.setPin_ksn(decodePinKsn.substring(490));
//        dukpt.setTrack_ksn(decodeTrackKsn.substring(490));
//        dukpt.setEmv_ksn(decodeEmvKsn.substring(490));
//        dukpt.setAmount_ksn(decodeAmountKsn.substring(490));
//        dukpt.setGeneral_ksn(decodeGeneralKsn.substring(490));
//        dukpt.setPin_ipek(decodePinIpek.substring(478));
//        dukpt.setTrack_ipek(decodeTrackIpek.substring(478));
//        dukpt.setEmv_ipek(decodeEmvIpek.substring(478));
//        dukpt.setAmount_ipek(decodeAmountIpek.substring(478));
//        dukpt.setGeneral_ipek(decodeGeneralIpek.substring(478));
//        dukpt.setPin_block_mk_universal(decodePinBlockMkUniversal.substring(494));
//        dukpt.setPin_block_wk_universal(decodePinBlockWkUniversal.substring(494));
//        return dukpt;
//    }


//    public static Map<String, Object> generateEnc(String track2Ksn, String track2Ipek, String originalTrack2) {
//        Map<String, Object> encMap = new HashMap<>(2);
//        //generate baTrack2Ksn
//        byte[] baTrack2Ksn = UtilString.hexStringToBytes(track2Ksn);
//
//        //generate baTrack2Ipek
//        byte[] baTrack2Ipek = UtilString.hexStringToBytes(track2Ipek);
//
//        //generate baFmtTrack2
//        String fmtTrack2 = originalTrack2.replace("=", "D");
//
//        int originalLenTrack2 = originalTrack2.length();
//        int iFmtTrack2Len = originalLenTrack2;
//        if (originalLenTrack2 % 2 == 1) {
//            fmtTrack2 += "0";
//            iFmtTrack2Len += 1;
//        }
//
//        int iModulus = (iFmtTrack2Len / 2) % 8;
//        if (iModulus != 0) {
//            for (int i = 0; i < 8 - iModulus; i++) {
//                fmtTrack2 += "00";
//            }
//        }
//
//        byte[] baFmtTrack2 = UtilString.hexStringToBytes(fmtTrack2);
//        if (baFmtTrack2 == null) Log.e("baFmtTrack2", "null cay");
//        byte[] baTrack2 = DUKPTUtil.encryptTrackWithIPEK(baFmtTrack2, baTrack2Ksn, baTrack2Ipek);
//        String track2Enc = Hex.toHexString(baTrack2);
//
//        encMap.put("track2Enc", track2Enc);
//        encMap.put("originalLenTrack2", originalLenTrack2);
//        return encMap;
//    }

    public static String getJsonString(JSONObject json, String key) {
        String value = "";
        try {
            value = json != null && json.has(key) ? json.get(key).toString() : "";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static Boolean getJsonBoolean(JSONObject json, String key) {
        Boolean value = null;
        try {
            value = json != null && json.has(key) ? json.getBoolean(key) : null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static String decodeBitmap(int resource, Context context) {
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), resource);
        return bitMapToString(logo);
    }

    public static String bitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] arr = baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

//    public static DUKPTComponentModel decodeDUKPT(String dukptComponentBase64) {
//        DUKPTComponentModel dukptModel = new DUKPTComponentModel();
//        byte[] data = Base64.decode(dukptComponentBase64, Base64.DEFAULT);
//        try {
//            String dukptComponent = new String(data, "UTF-8");
//            dukptModel = MyFunctions.stringToClass(dukptComponent, DUKPTComponentModel.class);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return dukptModel;
//    }

    public static <T> ArrayList<T> stringToClassList(String string, Type type) {
        try {
            return new Gson().fromJson(string.isEmpty()? "[]" : string, type);
        } catch (Exception e) {
            return new ArrayList<T>();
        }
    }
}
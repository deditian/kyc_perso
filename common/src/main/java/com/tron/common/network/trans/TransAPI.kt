package com.tron.common.network.trans

import retrofit2.Call
import retrofit2.http.*

interface TransAPI {

    @POST("trx/tap-in-out")
    fun tapInOut(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @POST("trx/tap-in-out/send-receipt")
    fun sendReceipt(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @POST("general/get-token")
    fun getToken(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @GET("general")
    fun inquiryBalance(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @POST("general")
    fun settlement(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @GET("general")
    fun getTapInOutHistory(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @GET("general")
    fun getSettlementHistory(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>


    @GET("general")
    fun getPetugas(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @GET("general")
    fun getGeneralInformation(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @GET("general/bus-information")
    fun getBusInformation(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @GET("general/fare-information")
    fun getFareInformation(@HeaderMap headers: Map<String, String>) : Call<String>

    @POST("trx/generate-qr-voucher")
    fun generateQRVoucher(@HeaderMap headers: Map<String, String>, @Body body: Any) : Call<String>

    @GET("trx/generate-qr-receipt/{card_no}")
    fun generateQRReceipt(@HeaderMap headers: Map<String, String>, @Path("card_no") cardNo: String) : Call<String>
}
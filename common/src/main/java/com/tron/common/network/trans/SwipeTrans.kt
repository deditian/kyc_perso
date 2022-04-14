package com.tron.common.network.trans

import com.tron.common.network.HeaderUtil
import com.tron.common.network.RetrofitClient

object SwipeTrans {

//    fun tapInOut(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().tapInOut(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }

//    fun sendReceipt(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().sendReceipt(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }

    fun getToken(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
        val headers = HeaderUtil.getHeaders()
        val apiCall = RetrofitClient.swipeTrans().getToken(headers, body)
        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
    }

//    fun inquiryBalance(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().inquiryBalance(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }

//    fun settlement(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().settlement(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }

//    fun getTapInOutHistory(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().getTapInOutHistory(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }
//
//    fun getSettlementHistory(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().getSettlementHistory(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }
//
//    fun getPetugas(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().getPetugas(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }
//
//    fun getGeneralInformation(body: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().getGeneralInformation(headers, body)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }
//
//    fun getFareInformation(onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().getFareInformation(headers)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }
//
//    fun generateQRVoucher(paymentPointer: Any, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().generateQRVoucher(headers, paymentPointer)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }
//
//    fun generateQRReceipt(cardNo: String, onSuccess: RetrofitClient.OnSuccess, onFailure: RetrofitClient.OnFailure) {
//        val headers = HeaderUtil.getHeaders()
//        val apiCall = RetrofitClient.swipeTrans().generateQRReceipt(headers, cardNo)
//        RetrofitClient.enqueue(apiCall, onSuccess, onFailure)
//    }
}
package com.tron.common.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tron.common.network.model.auth.model.AuthRequest
import com.tron.common.network.trans.SwipeTrans
import com.tron.common.network.trans.model.AccessToken
import com.tron.common.util.MyFunctions

class SwipeTransRepository(private val application: Application) {

//    fun tapInOut(request: RequestTapInOut): LiveData<ApiResponse<TapInOut>> {
//        val data = MutableLiveData<ApiResponse<TapInOut>>()
//        data.value = ApiResponse.Loading
//        SwipeTrans.tapInOut(request, { response ->
//            val responseTapInOut = MyFunctions.stringToClass(response, TapInOut::class.java)
//            val inquiry = responseTapInOut!!.inquiry?.copy(fare = 1)
//            data.value = ApiResponse.Success(responseTapInOut.copy(trxType = request.trxType))
//            Log.e("SwipeTransRepository", data.value.toString())
//        }, { error ->
//            data.value = ApiResponse.Error(error)
//        })
//        return data
//    }
//
//    fun fareAmount(method: String): LiveData<ApiResponse<QRVoucher>> {
//        val data = MutableLiveData<ApiResponse<QRVoucher>>()
//        data.value = ApiResponse.Loading
//
//        SwipeTrans.getFareInformation({ resonse ->
//            val inquiry = MyFunctions.stringToClass(resonse, Inquiry::class.java)
//            data.value = ApiResponse.Success(QRVoucher("", "", if (method == Constants.METHOD_CREDIT_DEBIT) inquiry.fare else inquiry.qrFare))
//        }, { error ->
//            data.value = ApiResponse.Error(error)
//        })
//        return data
//    }

    fun accessToken(request: AuthRequest): LiveData<ApiResponse<AccessToken>> {
        val data = MutableLiveData<ApiResponse<AccessToken>>()

        data.value = ApiResponse.Loading
        SwipeTrans.getToken(request, { response ->
            Log.e("SwipeTransRepository", response)
            val user = MyFunctions.stringToClass(response, AccessToken::class.java)
            data.value = ApiResponse.Success(user)
        }, { error ->
            data.value = ApiResponse.Error(error)
        })

        return data

    }

//    fun generateQRVoucher(pointerPayment: QRVoucher) : LiveData<ApiResponse<QRVoucher>> {
//        val data = MutableLiveData<ApiResponse<QRVoucher>>()
//
//        data.value = ApiResponse.Loading
//
//        SwipeTrans.generateQRVoucher(pointerPayment, { response ->
//            data.value = ApiResponse.Success(MyFunctions.stringToClass(response, QRVoucher::class.java))
//        }, { error -> data.value = ApiResponse.Error(error) })
//
//        return  data
//    }
//
//    fun generateQRReceipt(cardNo: String) : LiveData<ApiResponse<QRVoucher>> {
//        val data = MutableLiveData<ApiResponse<QRVoucher>>()
//        data.value = ApiResponse.Loading
//        SwipeTrans.generateQRReceipt(cardNo, { response ->
//            data.value = ApiResponse.Success(MyFunctions.stringToClass(response, QRVoucher::class.java))
//        }, { error ->
//            data.value = ApiResponse.Error(error)
//        })
//
//        return data
//    }

}
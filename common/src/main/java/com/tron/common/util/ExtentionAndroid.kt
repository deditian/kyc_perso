package com.tron.common.util

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tron.common.di.ViewModelFactory
import com.tron.common.databinding.LayoutDialogErrorBinding
import com.tron.common.network.model.GeneralResponse
import kotlin.reflect.KClass

fun <VM : ViewModel> AppCompatActivity.obtainViewModel(viewModel: KClass<VM>): Lazy<VM> {
    return lazy {
        val factory = ViewModelFactory.getInstance(this.application)
        return@lazy ViewModelProvider(this, factory).get(viewModel.java)
    }
}

fun Activity.showDialogCustom(error: GeneralResponse, callbackOk: ((dialog: Dialog) -> Unit?)? = null): Dialog? {
    return showDialogCustom(error.message, error.responseCode, callbackOk = callbackOk)
}

fun Activity.showDialogCustom(message: String, code: String? = null, title:String? = null, callbackOk: ((dialog: Dialog) -> Unit?)? = null, callbackCancel: ((dialog: Dialog) -> Unit?)? = null): Dialog? {
    if (isFinishing || isDestroyed) return null

    val dialog = Dialog(this)
    val binding = LayoutDialogErrorBinding.inflate(LayoutInflater.from(this))
    dialog.setContentView(binding.root)
    dialog.setCancelable(true)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    binding.errorResponseCode.let {
        it.isVisible = !code.isNullOrEmpty()
        it.text = code
    }
    binding.errorTitle.let {
        it.isVisible = !title.isNullOrEmpty()
        it.text = title
    }

    binding.btnCancel.isVisible = callbackCancel != null
    binding.btnCancel.setOnClickListener {
        callbackCancel?.invoke(dialog)
    }

    binding.btnOk.isVisible = callbackOk != null
    binding.btnOk.setOnClickListener {
        callbackOk?.invoke(dialog)
    }
    binding.errorMessage.text = message

    dialog.show()
    return dialog
}

fun Activity.showToast(message: String) {
    this.runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
}

fun <K: Activity> Activity.gotoActivityNewTask(klass: KClass<K>) {
    startActivity(Intent(this, klass.java)
//        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
//    finish()
}
package com.tron.kyc_perso.ui.register

import android.R.attr.data
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.doOnTextChanged
import com.tron.common.R
import com.tron.common.databinding.ActivityRegisterBinding
import com.tron.common.network.model.UserRegistration
import java.io.File
import java.io.InputStream


class RegisterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRegisterBinding
    private val pickImage = 100
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.etFullName.editText?.doOnTextChanged { _, _, _, count ->
            binding.etFullName.isErrorEnabled = count == 0
            if (count == 0) binding.etFullName.error = getString(R.string.empty_id)
        }

        binding.btnUploadktp.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        val items = listOf("Male", "Female")
        val adapter = ArrayAdapter(this, R.layout.list_item_gender, items)
        (binding.etGender.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.btnRegis.setOnClickListener {
            pushRegister()
        }

    }
    fun pushRegister(){
        val fullname: String = binding.etFullName.editText!!.text.toString()
        val photo_ktp: String = imageUri.toString()
        val nik: String = binding.etNik.editText?.text.toString()
        val kota_arus: String = binding.etArusmudik.editText?.text.toString()
        val gender: String = binding.etGender.editText?.text.toString()
        val email: String = binding.etEmail.editText?.text.toString()
        val phoneNumber: String = binding.etActiveMobileNumber.editText?.text.toString()
        val city: String = binding.etCityAccordingToIdcard.editText?.text.toString()
        val address: String = binding.etAddress.editText?.text.toString()

        val userRegistration = UserRegistration(fullname, photo_ktp, nik, kota_arus, gender, email,
            phoneNumber, city, address)
        Log.e("TAG", "setGetCredential: $userRegistration" )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.txNamePhotoKtp.text = getNameFileFromGallery(imageUri)
            if (getImageSizeFromUriInMegaByte(this, imageUri) > 1.0){
                Log.e("TAG", "onActivityResult:  > 1" )
            }else{
                Log.e("TAG", "onActivityResult: < 1" )
            }
        }
    }
    fun getNameFileFromGallery(uri: Uri?): String {
        val filename: String?
        val cursor: Cursor? = contentResolver.query(uri!!, null, null, null, null)
        if (cursor == null) filename = uri.path else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
            filename = cursor.getString(idx)
            cursor.close()
        }

        val name = filename?.substring(0, filename.lastIndexOf("."))
        val extension = filename?.substring(filename.lastIndexOf(".") + 1)
        return  "$name.$extension"
    }

    fun getImageSizeFromUriInMegaByte(context: Context, uri: Uri?): Double {
        val scheme: String? = uri?.scheme
        var dataSize = 0.0
        if (scheme == ContentResolver.SCHEME_CONTENT) {
            try {
                val fileInputStream: InputStream? = context.contentResolver.openInputStream(uri)
                if (fileInputStream != null) {
                    dataSize = fileInputStream.available().toDouble()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (scheme == ContentResolver.SCHEME_FILE) {
            val path: String? = uri.path
            var file: File? = null
            try {
                file = File(path)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (file != null) {
                dataSize = file.length().toDouble()
            }
        }
        return dataSize / (1024 * 1024)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_gender_register, menu)
        return true
    }
}
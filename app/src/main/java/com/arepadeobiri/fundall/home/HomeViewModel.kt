package com.arepadeobiri.fundall.home

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.UploadImage
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class HomeViewModel(private val appComponent: AppComponent) : ViewModel() {


    @Inject
    lateinit var pref: SharedPreferences

    @Inject
    lateinit var fundallIO: Fundall

    @Inject
    lateinit var picasso: Picasso

    private val job = Job()

    private val scope = CoroutineScope(Dispatchers.Main + job)

    init {
        appComponent.inject(this)
    }


    fun uploadAvatar(image: UploadImage) {
        scope.launch {


            try {
                val response = fundallIO.uploadAvatarAsync(
                    getRequestPart(UploadImage(image.avatar.toString())),
                    getFilePart(image.file)
                ).await()
                if (response.success != null) {
                    Log.d("Ares", "${response.success.message}")

                } else {
                }

            } catch (t: Throwable) {
                Log.d("HomeViewModel", "${t.message}")
            }
        }


    }

    private fun getRequestPart(uploadImage: UploadImage): RequestBody {
        val gson = Gson()
        return gson.toJson(uploadImage).toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }


    private fun getFilePart(file: File?): MultipartBody.Part? {
        file ?: return null
        val requestBody = file.asRequestBody(MEDIA_TYPE.toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("avatar", file.name, requestBody)
    }

    companion object {
        private const val MEDIA_TYPE = "image/*"
    }


}
package com.arepadeobiri.fundall.home

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.UploadImage
import com.arepadeobiri.fundall.network.avatarDataModels.Error

import com.arepadeobiri.fundall.network.avatarDataModels.Success
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

    private val _avatarUploadSuccessful = MutableLiveData<Success>()
    val avatarUploadSuccessful: LiveData<Success> get() = _avatarUploadSuccessful

    private val _avatarUploadFailed = MutableLiveData<Error>()
    val avatarUploadFailed: LiveData<Error> get() = _avatarUploadFailed

    private val _progressBarShow = MutableLiveData<Boolean>()
    val progressBarShow: LiveData<Boolean> get() = _progressBarShow




    init {
        appComponent.inject(this)
    }


    fun uploadAvatar(image: UploadImage) {
        scope.launch {
        _progressBarShow.value = true

            try {
                val response = fundallIO.uploadAvatarAsync(
                    getRequestPart(UploadImage(image.avatar.toString())),
                    getFilePart(image.file)
                ).await()
                if (response.success != null) {
                    _avatarUploadSuccessful.value = response.success

                } else {
                    _avatarUploadFailed.value = response.error

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


    fun showProgressBar(value: Boolean){
        _progressBarShow.value = value
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
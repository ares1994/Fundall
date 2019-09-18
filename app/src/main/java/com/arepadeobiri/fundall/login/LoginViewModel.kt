package com.arepadeobiri.fundall.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.loginDataModels.Error
import com.arepadeobiri.fundall.network.loginDataModels.Success
import com.arepadeobiri.fundall.util.FundallUtils.Companion.AVATAR
import com.arepadeobiri.fundall.util.FundallUtils.Companion.EMAIL
import com.arepadeobiri.fundall.util.FundallUtils.Companion.FIRST_NAME
import com.arepadeobiri.fundall.util.FundallUtils.Companion.LAST_NAME
import com.arepadeobiri.fundall.util.FundallUtils.Companion.TOKEN
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import javax.inject.Inject

class LoginViewModel(appComponent: AppComponent) : ViewModel() {


    private val job = Job()

    private val scope = CoroutineScope(Dispatchers.Main + job)

    private val _loginSuccessful = MutableLiveData<Success>()
    val loginSuccessful: LiveData<Success> get() = _loginSuccessful

    private val _loginFailed = MutableLiveData<Error>()
    val loginFailed: LiveData<Error> get() = _loginFailed

    private val _profileInfo = MutableLiveData<com.arepadeobiri.fundall.network.profileDataModels.Success>()
    val profileInfo: LiveData<com.arepadeobiri.fundall.network.profileDataModels.Success> get() = _profileInfo


    private val _buttonVisible = MutableLiveData<Boolean>()
    val buttonVisible: LiveData<Boolean> get() = _buttonVisible

    @Inject
    lateinit var pref: SharedPreferences

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var fundallIO: Fundall


    init {
        appComponent.inject(this)
        _buttonVisible.value = false
    }


    fun loginUser(email: String, password: String) {
        scope.launch {

            try {
                val response = fundallIO.logInAsync(email, password).await()

                if (response.success != null) {
                    _loginSuccessful.value = response.success
                    val user = response.success.user
                    pref.edit().apply {
                        putString(FIRST_NAME, user!!.firstname).apply()
                        putString(LAST_NAME, user.lastname).apply()
                        putString(AVATAR, user.avatar).apply()
                        putString(EMAIL, user.email).apply()
                        putString(TOKEN, "Bearer " + user.accessToken).apply()
                    }
                } else {
                    _loginFailed.value = response.error
                }


            } catch (t: Throwable) {
                Log.d("LoginViewModel", "${t.message}")
                _loginFailed.value = Error(null,"Login failed")


            }
        }


    }

    fun retrieveProfile() {
        scope.launch {
            val response = fundallIO.retrieveProfileAsync().await()
            if (response.success != null) {
                _profileInfo.value = response.success
            }

        }
    }

    fun isButtonVisible(value: Boolean) {
        _buttonVisible.value = value
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
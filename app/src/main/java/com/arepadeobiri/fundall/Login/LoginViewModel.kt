package com.arepadeobiri.fundall.Login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.loginDataModels.Error
import com.arepadeobiri.fundall.network.loginDataModels.Success
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

    @Inject
    lateinit var pref: SharedPreferences

    @Inject
    lateinit var picasso : Picasso

    @Inject
    lateinit var fundallIO: Fundall


    init {
        appComponent.inject(this)
    }


    fun loginUser(email: String, password: String) {
        scope.launch {

            try {
                val list = fundallIO.logInAsync(email, password).await()
                _loginSuccessful.value = list.success
                val user = list.success!!.user
                pref.edit().apply {
                    putString("firstname", user!!.firstname).apply()
                    putString("lastname", user.lastname).apply()
                    putString("avatarUrl", user.avatar).apply()
                    putString("email", user.email).apply()
                    putString("token", user.accessToken).apply()
                }


            } catch (t: Throwable) {
                _loginFailed.value = Error(t.message, "Please check your email and password")
                Log.d("LoginViewModel", "${t.message}")


            }
        }


    }

}
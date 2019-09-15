package com.arepadeobiri.fundall.Login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.database.UserDao
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.loginDataModels.Error
import com.arepadeobiri.fundall.network.loginDataModels.LoginResponse
import com.arepadeobiri.fundall.network.loginDataModels.Success
import com.arepadeobiri.fundall.network.loginDataModels.User
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
    lateinit var database: UserDao

    @Inject
    lateinit var fundallIO: Fundall


    init {
        appComponent.inject(this)
    }

    val currentUser = database.getAll()


    fun loginUser(email: String, password: String) {
        scope.launch {

            try {
                val list = fundallIO.logInAsync(email, password).await()
                _loginSuccessful.value = list.success


            } catch (t: Throwable) {
                _loginFailed.value = Error(t.message, "Please check your email and password")
                Log.d("LoginViewModel", "${t.message}")


            }
        }


    }

}
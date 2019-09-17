package com.arepadeobiri.fundall.signUp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import com.arepadeobiri.fundall.network.Fundall
import com.arepadeobiri.fundall.network.signUpDataModels.Info
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class SignUpViewModel(appComponent: AppComponent) : ViewModel() {

    private val job = Job()

    private val scope = CoroutineScope(Dispatchers.Main + job)

    private val _registerMessage = MutableLiveData<Info>()
    val registerMessage: LiveData<Info> get() = _registerMessage


    @Inject
    lateinit var fundallIO: Fundall


    init {
        appComponent.inject(this)
    }


    fun fundallRegisterUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        scope.launch {
            try {
                val list = fundallIO.registerUserAsync(
                    firstName,
                    lastName,
                    email,
                    password,
                    passwordConfirmation
                ).await()
                if (list.success != null) {
                    _registerMessage.value = list.success
                } else {
                    _registerMessage.value = list.error
                }

            } catch (t: Throwable) {
                Log.d("SignUpViewModel", "${t.message}")
                _registerMessage.value = Info("Registration unsuccessful, please try again", null)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
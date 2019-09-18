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

    private val _buttonVisible = MutableLiveData<Boolean>()
    val buttonVisible: LiveData<Boolean> get() = _buttonVisible






    @Inject
    lateinit var fundallIO: Fundall


    init {
        appComponent.inject(this)
        _buttonVisible.value = false
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
                val response = fundallIO.registerUserAsync(
                    firstName,
                    lastName,
                    email,
                    password,
                    passwordConfirmation
                ).await()
                if (response.success != null) {
                    _registerMessage.value = response.success
                } else {
                    _registerMessage.value = response.error
                }

            } catch (t: Throwable) {
                Log.d("SignUpViewModel", "${t.message}")
                _registerMessage.value = Info("Registration unsuccessful, please try again")
            }
        }

    }

    fun isButtonVisible(value: Boolean){
        _buttonVisible.value = value
    }



    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
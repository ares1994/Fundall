package com.arepadeobiri.fundall.splash

import androidx.lifecycle.ViewModel
import com.arepadeobiri.fundall.database.User
import com.arepadeobiri.fundall.database.UserDao
import com.arepadeobiri.fundall.daggerUtil.AppComponent
import kotlinx.coroutines.*
import javax.inject.Inject

class SplashViewModel(appComponent: AppComponent) : ViewModel() {

    @Inject
    lateinit var database: UserDao

    val job = Job()

    lateinit var currentUser: List<User>

    val scope = CoroutineScope(Dispatchers.Main + job)

    init {
        appComponent.inject(this)
        scope.launch {
            withContext(Dispatchers.IO) {
                currentUser = database.getAll()
            }
        }

    }


}
package com.arepadeobiri.fundall.daggerUtil



import com.arepadeobiri.fundall.Login.LoginViewModel
import com.arepadeobiri.fundall.home.HomeViewModel
import com.arepadeobiri.fundall.signUp.SignUpViewModel
import com.arepadeobiri.fundall.splash.SplashFragment
import com.arepadeobiri.fundall.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: SplashFragment)

    fun inject(viewModel: SplashViewModel)

    fun inject(viewModel: SignUpViewModel)

    fun inject(viewModel: LoginViewModel)

    fun inject(viewModel: HomeViewModel)




}

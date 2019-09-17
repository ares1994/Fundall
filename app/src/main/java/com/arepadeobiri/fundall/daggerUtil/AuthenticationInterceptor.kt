package com.arepadeobiri.fundall.daggerUtil

import android.content.SharedPreferences
import com.arepadeobiri.fundall.util.FundallUtils.Companion.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
        private val user: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(chain
                    .request()
                    .newBuilder()
                .addHeader(HEADER_AUTHORIZATION, user.getString(TOKEN,"") ?: "")
                    .build())

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }

}
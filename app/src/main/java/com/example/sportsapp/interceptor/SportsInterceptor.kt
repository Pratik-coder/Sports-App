package com.example.sportsapp.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class SportsInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl =chain.request().url.newBuilder()
            .build()

        var request=chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
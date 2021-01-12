package com.davinder.betworks.network

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody


class MockInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
         val requestWithNewUrl = request.newBuilder().url("http://github.com").build()
        val responseString = """{"isSuccessful": "true", "username": "username"}"""
        return chain.proceed(requestWithNewUrl)
            .newBuilder()
            .code(200)
            .message(responseString)
            .body(ResponseBody.create(MediaType.parse("application/json"),
                responseString.toByteArray()))
            .addHeader("content-type", "application/json")
            .build()
    }
}
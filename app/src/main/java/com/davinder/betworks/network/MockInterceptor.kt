package com.davinder.betworks.network

import okhttp3.*
import java.net.InetAddress
import java.net.InetSocketAddress


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
package com.davinder.betworks.network

import com.davinder.betworks.login.UserLoginResult
import io.reactivex.Single
import retrofit2.http.GET

interface BetworksService {

    @GET("/login")
    fun getLoginResult(): Single<UserLoginResult>
}
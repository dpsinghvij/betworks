package com.davinder.betworks.login


import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call

interface LoginRepository {

    fun loginUser(): Single<UserLoginResult>
}
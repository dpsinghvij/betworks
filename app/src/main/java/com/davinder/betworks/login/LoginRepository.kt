package com.davinder.betworks.login


import io.reactivex.Single

interface LoginRepository {

    fun loginUser(): Single<UserLoginResult>
}
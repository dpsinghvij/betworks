package com.davinder.betworks.login


import io.reactivex.Single

interface LoginRepository {

    /**
     * Logs user In
     */
    fun loginUser(): Single<UserLoginResult>
}
package com.davinder.betworks.login

import io.reactivex.Observable

object UserSingleton {
    var username: String = ""

    fun getUserName(): Observable<String> {
        return Observable.just(username)
    }
}
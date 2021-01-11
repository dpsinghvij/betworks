package com.davinder.betworks.login

import com.davinder.betworks.network.BetworksService
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val betworksService: BetworksService): LoginRepository {
    override fun loginUser(): Single<UserLoginResult> {
        return betworksService.getLoginResult()
    }
}
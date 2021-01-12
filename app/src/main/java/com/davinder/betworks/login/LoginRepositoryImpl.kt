package com.davinder.betworks.login

import com.davinder.betworks.network.BetworksService
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val betworksService: BetworksService): LoginRepository {
    override fun loginUser(): Single<UserLoginResult> {
        return betworksService.getLoginResult()
    }
}
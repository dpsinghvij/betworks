package com.davinder.betworks.userscreen

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davinder.betworks.login.UserSingleton
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserScreenViewModel @Inject constructor() : ViewModel() {

    var _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    @SuppressLint("CheckResult")
    fun showUserData() {
        UserSingleton.getUserName()
            .subscribeOn(Schedulers.io())
            .subscribe {
                _username.postValue(it)
            }
    }
}
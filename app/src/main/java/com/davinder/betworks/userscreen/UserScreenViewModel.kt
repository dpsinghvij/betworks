package com.davinder.betworks.userscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davinder.betworks.login.UserSingleton
import javax.inject.Inject

class UserScreenViewModel @Inject constructor() : ViewModel() {

    var _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    fun showUserData() {
        _username.value = UserSingleton.username
    }
}
package com.davinder.betworks.login

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davinder.betworks.utils.EspressoIdlingResource
import com.davinder.betworks.views.ViewItem
import com.davinder.betworks.views.ViewItem.InputViewItem
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val viewItemList: List<ViewItem>,
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private var _viewItems = MutableLiveData<List<ViewItem>>()
    val viewItems: LiveData<List<ViewItem>>
        get() = _viewItems

    private var _validationStatus = MutableLiveData<Boolean>()
    val validationStatus: LiveData<Boolean>
        get() = _validationStatus

    private var _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    fun setUp() {
        _viewItems.value = viewItemList
    }

    fun validateUsernameAndPassword() {
        val count = _viewItems.value?.filterIsInstance<InputViewItem>()
            ?.filter { it.validate() }
            ?.count() ?: 0

        _validationStatus.value = count == 2
    }

    @SuppressLint("CheckResult")
    fun loginUser() {
        EspressoIdlingResource.increment()
        loginRepository.loginUser()
            .subscribeOn(Schedulers.io())
            .subscribe { _ ->
                val username = _viewItems.value?.filterIsInstance<InputViewItem>()
                    ?.first { it.id == "username" }
                    ?.value
                UserSingleton.username = username ?: ""
                _loginStatus.postValue(true)
                EspressoIdlingResource.decrement()
            }
    }


}

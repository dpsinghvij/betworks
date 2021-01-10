package com.davinder.betworks.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davinder.betworks.views.ViewItem
import com.davinder.betworks.views.ViewItem.InputViewItem

class LoginViewModel (val viewItemList: List<ViewItem>) : ViewModel() {

    private var _viewItems = MutableLiveData<List<ViewItem>>()
    val viewItems: LiveData<List<ViewItem>>
        get() = _viewItems

    private var _validationError = MutableLiveData<Boolean>()
    val validationError: LiveData<Boolean>
        get() = _validationError

    fun setUp() {
        _viewItems.value = viewItemList
    }

    fun validateUsernameAndPassword() {
        val count = _viewItems.value?.filterIsInstance<InputViewItem>()
            ?.filter { it.validate() }
            ?.count() ?: 0

        _validationError.value = count == 2
    }


}

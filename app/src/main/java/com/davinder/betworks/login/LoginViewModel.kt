package com.davinder.betworks.login

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davinder.betworks.views.InputEditText
import com.davinder.betworks.views.ViewItem
import com.davinder.betworks.views.ViewItem.InputViewItem
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private var _viewItems = MutableLiveData<List<ViewItem>>()
    val viewItems: LiveData<List<ViewItem>>
        get() = _viewItems

    fun setUp() {
        _viewItems.value = mutableListOf(InputViewItem(id= "username", hint = "Username", value = ""),
            InputViewItem(id= "password", hint = "Password", value = ""),
            ViewItem.ButtonViewItem(id = "loginbutton", text = "Login")
            )
    }
}

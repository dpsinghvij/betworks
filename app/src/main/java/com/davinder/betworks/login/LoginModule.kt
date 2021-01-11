package com.davinder.betworks.login

import com.davinder.betworks.views.ViewItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
object LoginModule {

    @Provides
    fun provideViewItems(loginRepository: LoginRepositoryImpl): LoginViewModel {
        return LoginViewModel(mutableListOf(ViewItem.InputViewItem(id = "username", hint = "Username", value = ""),
            ViewItem.InputViewItem(id = "password", hint = "Password", value = ""),
            ViewItem.ButtonViewItem(id = "loginbutton", text = "Login")
        ), loginRepository)
    }
}
package com.davinder.betworks.userscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davinder.betworks.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserScreenFragment : Fragment() {

    
    companion object {
        fun newInstance() = UserScreenFragment()
    }

    private lateinit var viewModel: UserScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
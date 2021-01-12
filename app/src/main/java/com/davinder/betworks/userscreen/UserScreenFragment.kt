package com.davinder.betworks.userscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.davinder.betworks.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserScreenFragment : Fragment() {

    @Inject
    lateinit var viewModel: UserScreenViewModel

    val username: TextView? by lazy {
        view?.findViewById(R.id.username) as? TextView
    }

    companion object {
        fun newInstance() = UserScreenFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showUserData()

        observeUsername()
    }

    private fun observeUsername() {
        viewModel.username.observe(viewLifecycleOwner) {
            username?.text = it
        }
    }
}
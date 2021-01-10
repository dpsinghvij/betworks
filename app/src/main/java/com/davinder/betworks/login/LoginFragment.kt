package com.davinder.betworks.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davinder.betworks.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    private val loginViews: RecyclerView? by lazy {
        view?.findViewById(R.id.loginViews)
    }
    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setUp()

        observeListViews()
    }

    private fun observeListViews() {
        viewModel.viewItems.observe(viewLifecycleOwner, Observer { viewItems ->
            val loginAdapter = LoginAdapter(viewItems)
            loginViews?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = loginAdapter
            }
        })
    }

}
package com.davinder.betworks.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davinder.betworks.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel

    private val loginViews: RecyclerView? by lazy {
        view?.findViewById(R.id.loginViews)
    }

    private val container: View? by lazy {
        view?.findViewById(R.id.container)
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

        observeInputValidation()

        observeSuccessfulLogin()
    }

    private fun observeSuccessfulLogin() {
        viewModel.loginStatus.observe(viewLifecycleOwner) { loginStatus ->
            if(loginStatus) {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToUserScreenFragment()
                view?.findNavController()?.navigate(action)
            }
        }
    }

    private fun observeInputValidation() {
        viewModel.validationStatus.observe(viewLifecycleOwner) { status ->
            if (!status) {
                showErrorMessage()
                return@observe
            }

            viewModel.loginUser()

        }
    }

    private fun showErrorMessage() {
        container?.let {
            Snackbar.make(it,
                context?.getString(R.string.validation_error) ?: "", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun observeListViews() {
        viewModel.viewItems.observe(viewLifecycleOwner, { viewItems ->
            val loginAdapter = LoginAdapter(viewItems) {
                viewModel.validateUsernameAndPassword()
            }
            loginViews?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = loginAdapter
            }
        })
    }

}
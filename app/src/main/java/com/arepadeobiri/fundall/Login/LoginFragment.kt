package com.arepadeobiri.fundall.Login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.R
import com.arepadeobiri.fundall.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments?.let { LoginFragmentArgs.fromBundle(it) }

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        val viewModelFactory =
            LoginViewModelFactory(((this.activity!!.application) as FundallApplication).getAppComponent())

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        if (args!!.isUserPresent) {
            binding.profileImageView.visibility = View.VISIBLE
            binding.welcomeBackTextView.visibility = View.VISIBLE
            binding.missYouTextView.visibility = View.VISIBLE
            binding.emailTextView.visibility = View.VISIBLE
            binding.emailInputLayout.visibility = View.INVISIBLE
            binding.missYouTextView.text = getString(R.string.we_miss_you, viewModel.pref.getString("firstname", ""))
            binding.emailTextView.text = viewModel.pref.getString("email", "")
            viewModel.picasso.load(viewModel.pref.getString("avatarUrl", "")).into(binding.profileImageView)
        } else {
            binding.profileImageView.visibility = View.INVISIBLE
            binding.welcomeBackTextView.visibility = View.INVISIBLE
            binding.emailTextView.visibility = View.INVISIBLE
            binding.missYouTextView.visibility = View.INVISIBLE
            binding.emailInputLayout.visibility = View.VISIBLE
        }


        binding.loginButton.setOnClickListener {
            if (args.isUserPresent) {
                viewModel.loginUser(viewModel.pref.getString("email", "")!!, binding.passwordEditText.text.toString())
            } else {
                viewModel.loginUser(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            }
        }

        binding.createAccountTextView.setOnClickListener {
            this.findNavController().navigate(LoginFragmentDirections.actionWelcomeBackFragmentToSignUpFragment())
        }

        viewModel.loginSuccessful.observe(this, Observer {
            Snackbar.make(binding.root, "Login Successful", Snackbar.LENGTH_LONG).show()
        })

        viewModel.loginFailed.observe(this, Observer {
            Snackbar.make(binding.root, "${it.message}", Snackbar.LENGTH_LONG).show()
        })







        binding.cancelTextView.setOnClickListener {
            this.findNavController().navigateUp()
        }




        return binding.root
    }


}

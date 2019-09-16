package com.arepadeobiri.fundall.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.arepadeobiri.fundall.R
import com.arepadeobiri.fundall.databinding.FragmentSignUpBinding
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.GenericViewModelFactory
import com.google.android.material.snackbar.Snackbar


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        this.activity!!.setTheme(R.style.AppTheme)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        val viewModelFactory =
            GenericViewModelFactory(((this.activity!!.application) as FundallApplication).getAppComponent())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)

        viewModel.registerMessage.observe(this, Observer {
            Snackbar.make(binding.root, "${it.message}", Snackbar.LENGTH_LONG).show()
        })

        binding.signUpButton.setOnClickListener {
            viewModel.fundallRegisterUser(
                binding.firstNameEditText.text.toString(),
                binding.lastNameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.cancelTextView.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.loginTextView.setOnClickListener {
            this.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToWelcomeBackFragment(false))
        }



        return binding.root
    }


}

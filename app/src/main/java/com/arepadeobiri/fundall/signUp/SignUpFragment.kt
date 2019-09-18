package com.arepadeobiri.fundall.signUp

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.arepadeobiri.fundall.R
import com.arepadeobiri.fundall.databinding.FragmentSignUpBinding
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.GenericViewModelFactory
import com.google.android.material.snackbar.Snackbar


class SignUpFragment : Fragment(), TextWatcher {


    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        this.activity!!.setTheme(R.style.AppTheme)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        val viewModelFactory =
            GenericViewModelFactory(((this.activity!!.application) as FundallApplication).getAppComponent())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)

        val progressDialog = ProgressDialog(this.context)
        progressDialog.apply {
            setMessage("Signing up...")
            setCancelable(false)
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
        }

        viewModel.registerMessage.observe(this, Observer {
            progressDialog.dismiss()
            Snackbar.make(binding.root, "${it.message}", Snackbar.LENGTH_LONG).show()
        })

        viewModel.buttonVisible.observe(this, Observer {
            binding.signUpButton.isEnabled = it
        })



        binding.firstNameEditText.addTextChangedListener(this)
        binding.lastNameEditText.addTextChangedListener(this)
        binding.emailEditText.addTextChangedListener(this)
        binding.passwordEditText.addTextChangedListener(this)
        binding.phoneEditText.addTextChangedListener(this)


        binding.signUpButton.setOnClickListener {
            viewModel.fundallRegisterUser(
                binding.firstNameEditText.text.toString(),
                binding.lastNameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
            progressDialog.show()
        }

        binding.cancelTextView.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.loginTextView.setOnClickListener {
            this.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToWelcomeBackFragment(false))
        }



        return binding.root
    }










    override fun afterTextChanged(p0: Editable?) {

        when {
            binding.firstNameEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
            binding.lastNameEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
            binding.phoneEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
            binding.emailEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
            binding.passwordEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
            else -> viewModel.isButtonVisible(true)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }


}

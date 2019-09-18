package com.arepadeobiri.fundall.login


import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.arepadeobiri.fundall.GenericViewModelFactory
import com.arepadeobiri.fundall.util.FundallUtils.Companion.EMAIL
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment(), TextWatcher {

    private lateinit var args: LoginFragmentArgs
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = LoginFragmentArgs.fromBundle(arguments!!)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        val viewModelFactory =
            GenericViewModelFactory(((this.activity!!.application) as FundallApplication).getAppComponent())

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        val progressDialog = ProgressDialog(this.context)
        progressDialog.apply {
            setMessage("Logging In...")
            setCancelable(false)
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
        }


        if (args.isUserPresent) {
            viewModel.retrieveProfile()
            binding.profileImageView.visibility = View.VISIBLE
            binding.welcomeBackTextView.visibility = View.VISIBLE
            binding.missYouTextView.visibility = View.VISIBLE
            binding.emailTextView.visibility = View.VISIBLE
            binding.emailInputLayout.visibility = View.INVISIBLE
            binding.missYouTextView.text = getString(R.string.we_miss_you, viewModel.pref.getString("firstname", ""))
            binding.emailTextView.text = viewModel.pref.getString(EMAIL, "")


            viewModel.profileInfo.observe(this, Observer {
                viewModel.picasso.load(it.data!!.avatar).placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(binding.profileImageView)
            })

        } else {
            binding.profileImageView.visibility = View.INVISIBLE
            binding.welcomeBackTextView.visibility = View.INVISIBLE
            binding.emailTextView.visibility = View.INVISIBLE
            binding.missYouTextView.visibility = View.INVISIBLE
            binding.emailInputLayout.visibility = View.VISIBLE
            binding.emailEditText.addTextChangedListener(this)
        }



        viewModel.loginSuccessful.observe(this, Observer {
            progressDialog.dismiss()
            Snackbar.make(binding.root, "Login Successful", Snackbar.LENGTH_LONG).show()
            this.findNavController().navigate(LoginFragmentDirections.actionWelcomeBackFragmentToHomeFragment())
        })

        viewModel.loginFailed.observe(this, Observer {
            progressDialog.dismiss()
            Snackbar.make(binding.root, "${it.message}", Snackbar.LENGTH_LONG).show()
        })

        viewModel.buttonVisible.observe(this, Observer {
            binding.loginButton.isEnabled = it
        })



        binding.passwordEditText.addTextChangedListener(this)


        binding.loginButton.setOnClickListener {
            if (args.isUserPresent) {
                viewModel.loginUser(viewModel.pref.getString(EMAIL, "")!!, binding.passwordEditText.text.toString())
            } else {
                viewModel.loginUser(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            }
            progressDialog.show()
        }

        binding.createAccountTextView.setOnClickListener {
            this.findNavController().navigate(LoginFragmentDirections.actionWelcomeBackFragmentToSignUpFragment())
        }

        binding.cancelTextView.setOnClickListener {
            this.findNavController().navigateUp()
        }

        return binding.root
    }


    override fun afterTextChanged(p0: Editable?) {
        if (args.isUserPresent) {
            when {
                binding.passwordEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
                else -> viewModel.isButtonVisible(true)
            }
        } else {
            when {
                binding.emailEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
                binding.passwordEditText.text.toString().isEmpty() -> viewModel.isButtonVisible(false)
                else -> viewModel.isButtonVisible(true)
            }

        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }


}

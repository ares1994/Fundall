package com.arepadeobiri.fundall.loginOption


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.GenericViewModelFactory
import com.arepadeobiri.fundall.R
import com.arepadeobiri.fundall.databinding.FragmentLoginOptionBinding


class LoginOptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginOptionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login_option, container, false
        )

        val viewModelFactory =
            GenericViewModelFactory(((this.activity!!.application) as FundallApplication).getAppComponent())
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginOptionViewModel::class.java)

        binding.lifestyleTextView.text = getString(R.string.lifestyle, viewModel.pref.getString("firstname", ""))
        binding.switchAccountTextView.text =
            getString(R.string.not_switch_account, viewModel.pref.getString("firstname", ""))
        binding.switchAccountTextViewButton.setOnClickListener {
            this.findNavController()
                .navigate(LoginOptionFragmentDirections.actionLoginOptionFragmentToWelcomeBackFragment(false))
        }
        binding.newHereTextViewButton.setOnClickListener {
            this.findNavController().navigate(LoginOptionFragmentDirections.actionLoginOptionFragmentToSignUpFragment())
        }

        binding.passwordTextView.setOnClickListener {
            this.findNavController().navigate(
                LoginOptionFragmentDirections.actionLoginOptionFragmentToWelcomeBackFragment(
                    true
                )
            )
        }
        return binding.root
    }

}

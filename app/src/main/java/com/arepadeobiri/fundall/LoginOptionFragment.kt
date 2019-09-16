package com.arepadeobiri.fundall


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.arepadeobiri.fundall.databinding.FragmentLoginOptionBinding



class LoginOptionFragment : Fragment() {
    private lateinit var binding : FragmentLoginOptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_option, container, false)

        binding.passwordTextView.setOnClickListener {
            this.findNavController().navigate(LoginOptionFragmentDirections.actionLoginOptionFragmentToWelcomeBackFragment(true))
        }
        return binding.root
    }

}

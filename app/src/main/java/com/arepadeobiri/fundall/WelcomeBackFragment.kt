package com.arepadeobiri.fundall


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.arepadeobiri.fundall.databinding.FragmentWelcomeBackBinding


class WelcomeBackFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBackBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments?.let { WelcomeBackFragmentArgs.fromBundle(it) }

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_back, container, false)

        if (args!!.isUserPresent) {
            binding.profileImageView.visibility = View.VISIBLE
            binding.welcomeBackTextView.visibility = View.VISIBLE
            binding.missYouTextView.visibility = View.VISIBLE
            binding.emailTextView.visibility = View.VISIBLE
            binding.emailInputLayout.visibility = View.INVISIBLE
        } else {
            binding.profileImageView.visibility = View.INVISIBLE
            binding.welcomeBackTextView.visibility = View.INVISIBLE
            binding.emailTextView.visibility = View.INVISIBLE
            binding.missYouTextView.visibility = View.INVISIBLE
            binding.emailInputLayout.visibility = View.VISIBLE
        }


        binding.cancelTextView.setOnClickListener {
            this.findNavController().navigateUp()
        }




        return binding.root
    }


}

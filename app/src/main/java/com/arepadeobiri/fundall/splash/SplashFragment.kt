package com.arepadeobiri.fundall.splash


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.arepadeobiri.fundall.R
import com.arepadeobiri.fundall.database.UserDao
import com.arepadeobiri.fundall.databinding.FragmentSplashBinding
import com.arepadeobiri.fundall.FundallApplication
import javax.inject.Inject


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var database: UserDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity!!.application as FundallApplication).getAppComponent().inject(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        val viewModelFactory =
            SplashViewModelFactory(((this.activity!!.application) as FundallApplication).getAppComponent())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        binding.startText.setOnClickListener {
            if (viewModel.currentUser.isNullOrEmpty()) {
                this.findNavController().navigate(R.id.action_splashFragment_to_signUpFragment)
                return@setOnClickListener
            } else {
                this.findNavController()
            }

        }

        return binding.root
    }
}




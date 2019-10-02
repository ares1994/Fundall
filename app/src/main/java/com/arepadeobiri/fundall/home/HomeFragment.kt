package com.arepadeobiri.fundall.home


import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toFile
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arepadeobiri.CardsRecyclerAdapter
import com.arepadeobiri.fundall.FundallApplication
import com.arepadeobiri.fundall.R
import com.arepadeobiri.fundall.databinding.FragmentHomeBinding
import com.arepadeobiri.fundall.GenericViewModelFactory
import com.arepadeobiri.fundall.network.UploadImage
import com.arepadeobiri.fundall.util.FundallUtils.Companion.AVATAR
import com.google.android.material.snackbar.Snackbar
import com.theartofdev.edmodo.cropper.CropImage


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var imageUri: Uri
    private lateinit var viewModel: HomeViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        val categories =
            listOf("Fundall lifestyle card", "Fundall rave card", "Fundall lifestyle card", "Fundall lifestyle card")


        val viewModelFactory =
            GenericViewModelFactory(((this.activity!!.application) as FundallApplication).getAppComponent())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)


        progressDialog = ProgressDialog(this.context,R.style.avatarDialogTheme)
        progressDialog.apply {
            setCancelable(false)


        }

        viewModel.picasso.load(viewModel.pref.getString(AVATAR, "")).placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.avatarImageView)




        binding.avatarImageView.setOnClickListener {
            CropImage.activity().start(this.activity!!.applicationContext, this)
        }

        binding.backButton.setOnClickListener {
            this.findNavController().navigateUp()
        }


        viewModel.avatarUploadSuccessful.observe(this, Observer {
            viewModel.showProgressBar(false)
            Snackbar.make(binding.root, "Avatar uploaded successfully", Snackbar.LENGTH_LONG).show()
            binding.avatarImageView.setImageURI(imageUri)

        })

        viewModel.avatarUploadFailed.observe(this, Observer {
            viewModel.showProgressBar(false)
            progressDialog.dismiss()
            Snackbar.make(binding.root, "Avatar upload failed", Snackbar.LENGTH_LONG).show()
        })


        viewModel.progressBarShow.observe(this, Observer {
            if (it) progressDialog.show() else progressDialog.dismiss()
        })


        binding.cardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CardsRecyclerAdapter(categories)
        }

        binding.pageIndicator.attachTo(binding.cardsRecyclerView)


        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {


            val result = CropImage.getActivityResult(data)

            if (resultCode == RESULT_OK) {
                imageUri = result.uri


                viewModel.uploadAvatar(UploadImage(imageUri.toString(), imageUri.toFile()))


            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Toast.makeText(this.context, "Error is : ${result.error}", Toast.LENGTH_LONG).show()
            }
        }






    }
}

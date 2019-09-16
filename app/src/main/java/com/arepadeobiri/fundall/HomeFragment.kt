package com.arepadeobiri.fundall


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.arepadeobiri.fundall.R
import com.arepadeobiri.fundall.databinding.FragmentHomeBinding
import com.theartofdev.edmodo.cropper.CropImage


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var imageUri: Uri
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)




        binding.cropButton.setOnClickListener {
            CropImage.activity().start(this.activity!!.applicationContext, this)
        }


        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            val result = CropImage.getActivityResult(data)

            if (resultCode == RESULT_OK) {
                imageUri = result.uri
                binding.avatarImageView.setImageURI(imageUri)
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this.context, "Error is : ${result.error}", Toast.LENGTH_LONG).show()
            }
        }


    }
}

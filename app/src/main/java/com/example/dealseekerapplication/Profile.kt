package com.example.dealseekerapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.dealseekerapplication.databinding.FragmentProfileBinding

class Profile : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settings.setOnClickListener {
            replaceFragment(Settings())
        }

        binding.privacypolicy.setOnClickListener {
            replaceFragment(PrivacyPolicy())
        }

        binding.requestsupport.setOnClickListener {
            replaceFragment(RequestSupport())
        }

        binding.twitter.setOnClickListener {
            replaceFragment(Twitter())
        }

    }


    private fun openInstagram() {
        val uri = Uri.parse("https://www.instagram.com/dealseeker8/")
        val instagram = Intent(Intent.ACTION_VIEW, uri)
        instagram.setPackage("com.instagram.android")
        try {
            startActivity(instagram)
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/dealseeker8/")))
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




package com.example.dealseekerapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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

        // Find the ImageView
        val instagramImageView: ImageView = view.findViewById(R.id.instagram)

        // Set OnClickListener for the ImageView
        instagramImageView.setOnClickListener {
            openInstagram()
        }

        // Find the ImageView for Facebook
        val facebookImageView: ImageView = view.findViewById(R.id.facebook)

        // Set OnClickListener for Facebook
        facebookImageView.setOnClickListener {
            openSocialMedia("https://www.facebook.com/profile.php?id=61560205074195", "com.facebook.katana")
        }

        // Find the ImageView for Twitter
        val twitterImageView: ImageView = view.findViewById(R.id.twitter)

        // Set OnClickListener for Twitter
        twitterImageView.setOnClickListener {
            openSocialMedia("https://x.com/home", "com.twitter.android")
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

    private fun openSocialMedia(profileUrl: String, appPackage: String) {
        val uri = Uri.parse(profileUrl)
        val socialMediaIntent = Intent(Intent.ACTION_VIEW, uri)
        socialMediaIntent.setPackage(appPackage)
        try {
            startActivity(socialMediaIntent)
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(profileUrl)))
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




package com.example.dealseekerapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dealseekerapplication.databinding.FragmentProfileBinding

class Profile : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.privacypolicy.setOnClickListener {
            findNavController().navigate(R.id.action_Profile_to_PrivacyPolicy )
        }
        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.action_Profile_to_Settings )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

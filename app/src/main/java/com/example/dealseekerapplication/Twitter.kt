package com.example.dealseekerapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dealseekerapplication.R
import com.example.dealseekerapplication.databinding.FragmentRequestSupportBinding
import com.example.dealseekerapplication.databinding.FragmentTwitterBinding

class Twitter : Fragment(R.layout.fragment_twitter) {

    private var _binding: FragmentTwitterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTwitterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
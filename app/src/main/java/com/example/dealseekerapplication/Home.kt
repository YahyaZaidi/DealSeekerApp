package com.example.dealseekerapplication

import android.graphics.Camera
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dealseekerapplication.databinding.FragmentHomeBinding
import com.example.dealseekerapplication.databinding.FragmentProfileBinding

class Home : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnphoneandgps.setOnClickListener {
            replaceFragment(PhonesAndGPS())
        }

        binding.btnCameras.setOnClickListener {
            replaceFragment(Cameras())
        }

        binding.btnComputers.setOnClickListener {
            replaceFragment(ComputerAndAccessories())
        }
        binding.btnAudio.setOnClickListener {
            replaceFragment(AudioAndVideo())
        }
        binding.btnGames.setOnClickListener {
            replaceFragment(GamesAndConsoles())
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
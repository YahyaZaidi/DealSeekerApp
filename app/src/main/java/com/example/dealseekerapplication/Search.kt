package com.example.dealseekerapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dealseekerapplication.databinding.FragmentProfileBinding
import com.example.dealseekerapplication.databinding.FragmentSearchBinding

class Search : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPhones.setOnClickListener {
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
        binding.btnHousehold.setOnClickListener {
            replaceFragment(HomeAndHouseholds())
        }
        binding.btnBeauty.setOnClickListener {
            replaceFragment(BeautyAndHealth())
        }
        binding.btnSports.setOnClickListener {
            replaceFragment(OutdoorAndSports())
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
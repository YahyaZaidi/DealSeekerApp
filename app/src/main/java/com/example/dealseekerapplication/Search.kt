package com.example.dealseekerapplication
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import com.example.dealseekerapplication.databinding.FragmentSearchBinding
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*

class Search : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var databaseManager: DatabaseManager
    private lateinit var adapter: UniversalAdapter  // This should be capable of handling both Camera and CameraA

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        databaseManager = DatabaseManager(requireContext())
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = UniversalAdapter(listOf())  // Make sure your adapter can handle both types of items
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            performSearch(binding.searchInput.text.toString())
        }
    }

    private fun performSearch(query: String) {
        lifecycleScope.launch {
            val results = withContext(Dispatchers.IO) {
                databaseManager.searchAllCameras(query)
            }
            if (results.isEmpty()) {
                binding.errorTextView.text = "No items found."
                binding.errorTextView.visibility = View.VISIBLE
            } else {
                binding.errorTextView.visibility = View.GONE
                adapter.updateList(results)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
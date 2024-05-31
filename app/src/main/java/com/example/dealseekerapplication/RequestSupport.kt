package com.example.dealseekerapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dealseekerapplication.databinding.FragmentRequestSupportBinding

class RequestSupport : Fragment(R.layout.fragment_request_support) {

    private var _binding: FragmentRequestSupportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestSupportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val message = binding.messageEditText.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && message.isNotEmpty()) {
                sendEmail(name, email, message)
            } else {
                Toast.makeText(activity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendEmail(name: String, email: String, message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("dealseeker806@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Support Request from $name")
            putExtra(Intent.EXTRA_TEXT, "Name: $name\nEmail: $email\n\nMessage:\n$message")
        }

        try {
            startActivity(Intent.createChooser(intent, "Send email using..."))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(activity, "No email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

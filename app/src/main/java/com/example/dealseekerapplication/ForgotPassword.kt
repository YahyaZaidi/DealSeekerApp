package com.example.dealseekerapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForgotPassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgotPassword : Fragment() {
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        val resetPasswordButton = view.findViewById<Button>(R.id.resetPasswordButton)
        val usernameEditText = view.findViewById<EditText>(R.id.usernameEditText)
        val newPasswordEditText = view.findViewById<EditText>(R.id.newPasswordEditText)

        resetPasswordButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val newPassword = newPasswordEditText.text.toString()
            if (username.isNotEmpty() && newPassword.isNotEmpty()) {
                val usersRef = firebaseDatabase.getReference("users")
                usersRef.child(username).child("password").setValue(newPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Password updated successfully", Toast.LENGTH_SHORT).show()
                            // Redirect back to login page
                            requireActivity().startActivity(Intent(requireContext(), loginActivity::class.java))
                        } else {
                            Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(), "Please enter your username and new password", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
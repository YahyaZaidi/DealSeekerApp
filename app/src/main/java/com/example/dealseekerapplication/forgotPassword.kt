package com.example.dealseekerapplication

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dealseekerapplication.databinding.ActivityForgotPasswordBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User

class forgotPassword : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        binding.confirmButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.newPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                updatePassword(username, password)
            } else {
                Toast.makeText(this@forgotPassword, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginRedirect.setOnClickListener {
            startActivity(Intent(this@forgotPassword, loginActivity::class.java))
            finish()


        }
    }
    private fun updatePassword(username: String, password: String) {
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        userSnapshot.ref.child("userPassword").setValue(password)
                    }
                    Toast.makeText(this@forgotPassword, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@forgotPassword, loginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@forgotPassword, "User not found", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@forgotPassword, "Error updating password", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
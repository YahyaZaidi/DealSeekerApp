package com.example.dealseekerapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dealseekerapplication.databinding.ActivitySignupBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class signupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.signupButton.setOnClickListener {
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()

            if (signupUsername.isNotEmpty() && signupPassword.isNotEmpty()) {
                signupUser(signupUsername, signupPassword)
            } else {
                Toast.makeText(this@signupActivity, "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()

            }

        }

        binding.loginRedirect.setOnClickListener {
            startActivity(Intent(this@signupActivity, loginActivity::class.java))
            finish()


        }
    }

    private fun signupUser(username : String, password : String){

        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(!dataSnapshot.exists()){
                    val id = databaseReference.push().key
                    val userData = UserData(id, username, password)
                    databaseReference.child(id!!).setValue(userData)
                    Toast.makeText(this@signupActivity, "Signup Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@signupActivity, loginActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this@signupActivity, "Username already exists", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@signupActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
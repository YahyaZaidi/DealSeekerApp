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

        //Setting onClickListener for the signup button
        binding.signupButton.setOnClickListener {
            // Get the username and password entered by the user
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()

            // Check if both username and password fields are not empty
            if (signupUsername.isNotEmpty() && signupPassword.isNotEmpty()) {
                signupUser(signupUsername, signupPassword)
            } else {
                //Message asking user to fill in all fields
                Toast.makeText(this@signupActivity, "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()

            }

        }
        //Set OnClickListener for login redirect button
        binding.loginRedirect.setOnClickListener {
            //End current activity and start the login activity
            startActivity(Intent(this@signupActivity, loginActivity::class.java))
            finish()
        }
    }

    //Function to signup user
    private fun signupUser(username : String, password : String){
        //Check if the username already exists in the database
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object : ValueEventListener{
            //Handle database changes
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Check if the username already exists
                if(!dataSnapshot.exists()){
                    //Generate a unique ID for the user
                    val id = databaseReference.push().key
                    //Create a new user data object
                    val userData = UserData(id, username, password)
                    //Save the new user's data in the database under the generated ID
                    databaseReference.child(id!!).setValue(userData)
                    //Signup successful toast message
                    Toast.makeText(this@signupActivity, "Signup Successful", Toast.LENGTH_SHORT).show()
                    //Start login activity and end the current activity
                    startActivity(Intent(this@signupActivity, loginActivity::class.java))
                    finish()
                } else{
                    //Toast message when username already exists
                    Toast.makeText(this@signupActivity, "Username already exists", Toast.LENGTH_SHORT).show()
                }
            }
            //Function handling database errors
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@signupActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
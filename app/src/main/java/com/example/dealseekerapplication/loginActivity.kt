package com.example.dealseekerapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dealseekerapplication.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class loginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        //Setting OnCLickListener for the login button
        binding.loginButton.setOnClickListener {
            //Get username and password entered by the user
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()

            //Check if both username and password fields are not empty
            if(loginUsername.isNotEmpty() && loginPassword.isNotEmpty()){
                //Call for the login user function
                loginUser(loginUsername, loginPassword)
                //signupUser(signupUsername, signupPassword)
                //signupUser(signupUsername, signupPassword)
            }else{
                //If any of the fields are empty, display a toast message
                Toast.makeText(this@loginActivity, "Please fill in all fields", Toast.LENGTH_SHORT).show()

            }
        }

        //Setting onClickListener for signup redirect button
        binding.signupRedirect.setOnClickListener {
            //Start signup activity and finish the current activity
            startActivity(Intent(this@loginActivity, signupActivity::class.java))
            finish()
        }
        //Setting onClickListener for forgot password redirect button
        binding.forgotPasswordRedirect.setOnClickListener {
            //Start forgot password activity and finish the current activity
            startActivity(Intent(this@loginActivity, forgotPassword::class.java))
            finish()
        }

    }
    //Function to login user
    private fun loginUser(username: String, password: String) {
        //Query the database to check if the user exists
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object : ValueEventListener{
            //Handle data changes in the database
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Check is user exists in username
                if(dataSnapshot.exists()){
                    //Iteration of user snapshots
                    for(userSnapshot in dataSnapshot.children){
                        //Get the user data from the snapshot
                        val userData = userSnapshot.getValue(UserData::class.java)

                        //Check user data is not null and password matches database password
                        if (userData != null && userData.password == password) {
                            //Display Login succuessful message
                            Toast.makeText(this@loginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                            //Finish the current activity and start the main activity
                            startActivity(Intent(this@loginActivity, MainActivity::class.java))
                            finish()
                            return
                        }

                    }
                }
                //Login failed message
                Toast.makeText(this@loginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
            }
            //Message for database error
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@loginActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        }
        )}

}
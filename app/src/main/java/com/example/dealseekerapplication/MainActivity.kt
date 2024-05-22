package com.example.dealseekerapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dealseekerapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Initially set the Home fragment when the activity is created
        replaceFragment(Home())

        // Setup item selection handling for the bottom navigation view
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(Home())
                    true
                }
                R.id.search -> {
                    replaceFragment(Search())
                    true
                }
                R.id.profile -> {
                    replaceFragment(Profile())
                    true
                }
                R.id.wishlist -> {
                    replaceFragment(Wishlist())
                    true
                }
                R.id.btnPhones -> {
                    replaceFragment(PhonesAndGPS())  // Assuming this is correctly defined in your menu XML
                    true
                }
                else -> false  // It's typically a good practice to handle an 'else' case even if it does nothing.
            }
        }
    }

    // Function to replace fragments based on the selected tab
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()  // Do not add to back stack to keep tab behavior consistent
    }
}

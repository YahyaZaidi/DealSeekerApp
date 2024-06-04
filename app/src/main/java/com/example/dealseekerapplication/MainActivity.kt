package com.example.dealseekerapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dealseekerapplication.constants.AppConstant
import com.example.dealseekerapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var permission : AppPermissions
    private lateinit var notificationPermission: AppPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        replaceFragment(Home())


        setupBottomNavigationView()
        permission = AppPermissions()
        notificationPermission = AppPermissions()


        if (permission.isLocationOk(this)) {
            println("Location Permission Allowed")
        } else {
            permission.requestLocationPermission(this)
            println("Location Permission Denied")
        }

        if (notificationPermission.isNotificationOk(this)) {
            println("Notification Permission Allowed")
        } else {
            if (notificationPermission.isNotificationOk(this)) {
                println("Notification Permission Allowed")
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                    notificationPermission.showPermissionExplanationDialog(this) {
                        notificationPermission.requestNotificationPermission(this)
                    }
                } else {
                    notificationPermission.requestNotificationPermission(this)
                }
            }
        }

    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.search -> replaceFragment(Search())
                R.id.profile -> replaceFragment(Profile())
                R.id.wishlist -> replaceFragment(Wishlist())
                R.id.btnPhones -> replaceFragment(PhonesAndGPS())
                else -> false
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            AppConstant.LOCATION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    println("Location Permission Granted")
                } else {
                    println("Location Permission Denied")
                }
            }
            AppConstant.NOTIFICATION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    println("Notification Permission Granted")
                } else {
                    println("Notification Permission Denied")
                }
            }
        }
    }

}






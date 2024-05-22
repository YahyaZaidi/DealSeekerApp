package com.example.dealseekerapplication

import android.app.Application
import com.google.firebase.FirebaseApp

class DealSeekerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}

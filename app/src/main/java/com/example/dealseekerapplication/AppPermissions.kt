package com.example.dealseekerapplication

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dealseekerapplication.constants.AppConstant
class AppPermissions {

    fun isLocationOk(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            AppConstant.LOCATION_REQUEST_CODE
        )
    }

    fun isNotificationOk(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true // If below TIRAMISU, no runtime permission needed
        }
    }

    fun requestNotificationPermission(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                AppConstant.NOTIFICATION_REQUEST_CODE
            )
        }
    }

    fun showPermissionExplanationDialog(activity: Activity, onPositiveClick: () -> Unit) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Notification Permission Needed")
        builder.setMessage("We need your permission to send you notifications about important updates and offers. Notifications can be turned off anytime from settings.")
        builder.setPositiveButton("OK") { dialog, which ->
            onPositiveClick()
        }
        builder.setNegativeButton("No thanks") { dialog, which ->
            dialog.dismiss()
        }
        builder.create().show()
    }

}
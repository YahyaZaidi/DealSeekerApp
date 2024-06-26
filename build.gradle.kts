// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    //dependency for google services gradle plugin
    //id("com.google.gms.google-services") version "4.4.2" apply false

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
buildscript {
    dependencies {
        classpath ("com.google.gms:google-services:4.3.10")  // Check for the latest version
    }
}



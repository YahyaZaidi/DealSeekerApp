package com.example.dealseekerapplication

import android.content.Context
import android.database.Cursor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseManager(private val context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    suspend fun searchAllCameras(query: String): List<Any> = withContext(Dispatchers.IO) {
        val cameras = mutableListOf<Any>()
        cameras.addAll(searchCamerasA(query))
        cameras.addAll(searchCameras(query))
        cameras
    }

    suspend fun searchCamerasA(query: String): List<CameraA> = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val camerasA = mutableListOf<CameraA>()
        val cursor = db.query(
            "CamerasA",
            null,
            "name LIKE ? OR main_category LIKE ?",
            arrayOf("%$query%", "%$query%"),
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                camerasA.add(it.toCameraA())
            }
        }
        camerasA
    }

    suspend fun searchCameras(query: String): List<Camera> = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cameras = mutableListOf<Camera>()
        val cursor = db.query(
            "Cameras",
            null,
            "Product_Name LIKE ? OR Brand LIKE ?",
            arrayOf("%$query%", "%$query%"),
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                cameras.add(it.toCamera())
            }
        }
        cameras
    }

    private fun Cursor.toCameraA(): CameraA = CameraA(
        id = getInt(getColumnIndexOrThrow("id")),
        name = getString(getColumnIndexOrThrow("name")),
        mainCategory = getString(getColumnIndexOrThrow("main_category")),
        subCategory = getString(getColumnIndexOrThrow("sub_category")),
        image = getString(getColumnIndexOrThrow("image")),
        link = getString(getColumnIndexOrThrow("link")),
        ratings = getDouble(getColumnIndexOrThrow("ratings")),
        noOfRatings = getInt(getColumnIndexOrThrow("no_of_ratings")),
        discountPrice = getString(getColumnIndexOrThrow("discount_price")),
        actualPrice = getString(getColumnIndexOrThrow("actual_price"))
    )

    private fun Cursor.toCamera(): Camera = Camera(
        url = getString(getColumnIndexOrThrow("url")),
        pictureURL = getString(getColumnIndexOrThrow("Picture_URL")),
        brand = getString(getColumnIndexOrThrow("Brand")),
        productName = getString(getColumnIndexOrThrow("Product_Name")),
        model = getString(getColumnIndexOrThrow("Model")),
        priceInIndia = getString(getColumnIndexOrThrow("Price_in_India")),
        stars1 = getInt(getColumnIndexOrThrow("Stars_1")),
        stars2 = getInt(getColumnIndexOrThrow("Stars_2")),
        stars3 = getInt(getColumnIndexOrThrow("Stars_3")),
        stars4 = getInt(getColumnIndexOrThrow("Stars_4")),
        stars5 = getInt(getColumnIndexOrThrow("Stars_5")),
        modelName = getString(getColumnIndexOrThrow("Model_Name")),
        type = getString(getColumnIndexOrThrow("Type")),
        color = getString(getColumnIndexOrThrow("Color")),
        dimensions = getString(getColumnIndexOrThrow("Dimensions")),
        batteryType = getString(getColumnIndexOrThrow("Battery_Type")),
        effectivePixels = getString(getColumnIndexOrThrow("Effective_Pixels")),
        sensorType = getString(getColumnIndexOrThrow("Sensor_Type")),
        displaySize = getString(getColumnIndexOrThrow("Display_Size")),
        temperature = getString(getColumnIndexOrThrow("Temperature")),
        selfTimer = getString(getColumnIndexOrThrow("Self_timer")),
        imageFormat = getString(getColumnIndexOrThrow("Image_Format")),
        weight = getString(getColumnIndexOrThrow("Weight")),
        autoFocus = getString(getColumnIndexOrThrow("Auto_Focus")),
        shutterSpeed = getString(getColumnIndexOrThrow("Shutter_Speed")),
        builtInFlash = getString(getColumnIndexOrThrow("Built_in_Flash")),
        sensorSize = getString(getColumnIndexOrThrow("Sensor_Size")),
        modelNumber = getString(getColumnIndexOrThrow("Model_Number")),
        whiteBalancing = getString(getColumnIndexOrThrow("White_Balancing")),
        wifi = getString(getColumnIndexOrThrow("Wifi")),
        flashModes = getString(getColumnIndexOrThrow("Flash_Modes")),
        viewFinder = getString(getColumnIndexOrThrow("View_Finder")),
        videoFormat = getString(getColumnIndexOrThrow("Video_Format")),
        videoResolution = getString(getColumnIndexOrThrow("Video_Resolution")),
        meteringModes = getString(getColumnIndexOrThrow("Metering_Modes")),
        maximumISO = getString(getColumnIndexOrThrow("Maximum_ISO")),
        cardType = getString(getColumnIndexOrThrow("Card_Type")),
        isoRating = getString(getColumnIndexOrThrow("ISO_Rating")),
        displayType = getString(getColumnIndexOrThrow("Display_Type")),
        focusMode = getString(getColumnIndexOrThrow("Focus_Mode")),
        continuousShots = getString(getColumnIndexOrThrow("Continuous_Shots")),
        dustReduction = getString(getColumnIndexOrThrow("Dust_Reduction")),
        lensMount = getString(getColumnIndexOrThrow("Lens_Mount")),
        manualFocus = getString(getColumnIndexOrThrow("Manual_Focus")),
        series = getString(getColumnIndexOrThrow("Series")),
        exposureCompensation = getString(getColumnIndexOrThrow("Exposure_Compensation")),
        faceDetection = getString(getColumnIndexOrThrow("Face_Detection")),
        lcdDisplayImageResolution = getString(getColumnIndexOrThrow("LCD_Display_Image_Resolution")),
        tripodSocket = getString(getColumnIndexOrThrow("Tripod_Socket")),
        viewFinderType = getString(getColumnIndexOrThrow("View_Finder_Type")),
        slrVariant = getString(getColumnIndexOrThrow("SLR_Variant")),
        externalFlash = getString(getColumnIndexOrThrow("External_Flash")),
        shootingModes = getString(getColumnIndexOrThrow("Shooting_Modes")),
        exposureMode = getString(getColumnIndexOrThrow("Exposure_Mode")),
        focusRange = getString(getColumnIndexOrThrow("Focus_Range")),
        opticalZoom = getString(getColumnIndexOrThrow("Optical_Zoom")),
        upgradableMemory = getString(getColumnIndexOrThrow("Upgradable_Memory")),
        aspectRatio = getString(getColumnIndexOrThrow("Aspect_Ratio")),
        shutterType = getString(getColumnIndexOrThrow("Shutter_Type")),
        touchScreen = getString(getColumnIndexOrThrow("Touch_Screen")),
        usb = getString(getColumnIndexOrThrow("USB")),
        displayResolution = getString(getColumnIndexOrThrow("Display_Resolution")),
        manualExposure = getString(getColumnIndexOrThrow("Manual_Exposure")),
        microphone = getString(getColumnIndexOrThrow("Microphone")),
        otherFocusFeatures = getString(getColumnIndexOrThrow("Other_Focus_Features")),
        viewfinderCoverage = getString(getColumnIndexOrThrow("Viewfinder_Coverage")),
        flashCompensation = getString(getColumnIndexOrThrow("Flash_Compensation")),
        apertureRange = getString(getColumnIndexOrThrow("Aperture_Range")),
        viewfinderMagnification = getString(getColumnIndexOrThrow("Viewfinder_Magnification")),
        digitalZoom = getString(getColumnIndexOrThrow("Digital_Zoom")),
        otherInfo = getString(getColumnIndexOrThrow("other_info"))
    )
}
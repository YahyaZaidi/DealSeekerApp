package com.example.dealseekerapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dealseeker.db"
        private const val DATABASE_VERSION = 1

        // Existing table for CamerasA
        const val CREATE_TABLE_CAMERASA = """
            CREATE TABLE CamerasA (
                id INTEGER PRIMARY KEY,
                name TEXT,
                main_category TEXT,
                sub_category TEXT,
                image TEXT,
                link TEXT,
                ratings REAL,
                no_of_ratings INTEGER,
                discount_price TEXT,
                actual_price TEXT
            )
        """

        // New table for Cameras
        const val CREATE_TABLE_CAMERAS = """
            CREATE TABLE Cameras (
                url TEXT,
                Picture_URL TEXT,
                Brand TEXT,
                Product_Name TEXT,
                Model TEXT,
                Price_in_India TEXT,
                Stars_1 INTEGER,
                Stars_2 INTEGER,
                Stars_3 INTEGER,
                Stars_4 INTEGER,
                Stars_5 INTEGER,
                Model_Name TEXT,
                Type TEXT,
                Color TEXT,
                Dimensions TEXT,
                Battery_Type TEXT,
                Effective_Pixels TEXT,
                Sensor_Type TEXT,
                Display_Size TEXT,
                Temperature TEXT,
                Self_timer TEXT,
                Image_Format TEXT,
                Weight TEXT,
                Auto_Focus TEXT,
                Shutter_Speed TEXT,
                Built_in_Flash TEXT,
                Sensor_Size TEXT,
                Model_Number TEXT,
                White_Balancing TEXT,
                Wifi TEXT,
                Flash_Modes TEXT,
                View_Finder TEXT,
                Video_Format TEXT,
                Video_Resolution TEXT,
                Metering_Modes TEXT,
                Maximum_ISO TEXT,
                Card_Type TEXT,
                ISO_Rating TEXT,
                Display_Type TEXT,
                Focus_Mode TEXT,
                Continuous_Shots TEXT,
                Dust_Reduction TEXT,
                Lens_Mount TEXT,
                Manual_Focus TEXT,
                Series TEXT,
                Exposure_Compensation TEXT,
                Face_Detection TEXT,
                LCD_Display_Image_Resolution TEXT,
                Tripod_Socket TEXT,
                View_Finder_Type TEXT,
                SLR_Variant TEXT,
                External_Flash TEXT,
                Shooting_Modes TEXT,
                Exposure_Mode TEXT,
                Focus_Range TEXT,
                Optical_Zoom TEXT,
                Upgradable_Memory TEXT,
                Aspect_Ratio TEXT,
                Shutter_Type TEXT,
                Touch_Screen TEXT,
                USB TEXT,
                Display_Resolution TEXT,
                Manual_Exposure TEXT,
                Microphone TEXT,
                Other_Focus_Features TEXT,
                Viewfinder_Coverage TEXT,
                Flash_Compensation TEXT,
                Aperture_Range TEXT,
                Viewfinder_Magnification TEXT,
                Digital_Zoom TEXT,
                other_info TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_CAMERASA)
        db?.execSQL(CREATE_TABLE_CAMERAS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Check what the old version is and perform the necessary updates
        if (oldVersion < 2) {
            // Upgrade from version 1 to version 2
            db?.execSQL("ALTER TABLE CamerasA ADD COLUMN new_column TEXT DEFAULT 'default_value'")
            db?.execSQL("ALTER TABLE Cameras ADD COLUMN new_feature TEXT DEFAULT 'not_specified'")
        }
        if (oldVersion < 3) {
            // Upgrade from version 2 to version 3
            db?.execSQL("CREATE TABLE NewTable (id INTEGER PRIMARY KEY, feature_name TEXT)")
            db?.execSQL("ALTER TABLE Cameras ADD COLUMN additional_feature TEXT DEFAULT 'none'")
        }
    }

}
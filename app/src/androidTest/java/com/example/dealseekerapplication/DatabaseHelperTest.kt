
package com.example.dealseekerapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DatabaseHelperTest {

    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var mockDb: SQLiteDatabase

    private lateinit var databaseHelper: DatabaseHelper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        databaseHelper = DatabaseHelper(mockContext)  // Create instance of DatabaseHelper
    }

    @Test
    fun onCreate_createsTables() {
        databaseHelper.onCreate(mockDb)

        // Verify that the SQL commands to create tables are executed
        verify(mockDb).execSQL(DatabaseHelper.CREATE_TABLE_CAMERASA)
        verify(mockDb).execSQL(DatabaseHelper.CREATE_TABLE_CAMERAS)
    }

    @Test
    fun onUpgrade_upgradesFromVersion1ToVersion2() {
        // Simulate upgrading from version 1 to version 2
        databaseHelper.onUpgrade(mockDb, 1, 2)

        // Verify that the SQL commands to alter tables are executed
        verify(mockDb).execSQL("ALTER TABLE CamerasA ADD COLUMN new_column TEXT DEFAULT 'default_value'")
        verify(mockDb).execSQL("ALTER TABLE Cameras ADD COLUMN new_feature TEXT DEFAULT 'not_specified'")
    }

    @Test
    fun onUpgrade_upgradesFromVersion2ToVersion3() {
        // Simulate upgrading from version 2 to version 3
        databaseHelper.onUpgrade(mockDb, 2, 3)

        // Verify that the SQL commands for the next version upgrade are executed
        verify(mockDb).execSQL("CREATE TABLE NewTable (id INTEGER PRIMARY KEY, feature_name TEXT)")
        verify(mockDb).execSQL("ALTER TABLE Cameras ADD COLUMN additional_feature TEXT DEFAULT 'none'")
    }
}

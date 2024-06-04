package com.example.dealseekerapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mockStatic
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun locationPermissionGranted() {
        // Mock the context and permission state
        val permissions = AppPermissions()

        // Mock the checkSelfPermission method to return PERMISSION_GRANTED
        mockStatic(ContextCompat::class.java).use { mockedContextCompat ->
            Mockito.`when`(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION))
                .thenReturn(PackageManager.PERMISSION_GRANTED)

            assertTrue(permissions.isLocationOk(context))
        }
    }

    @Test
    fun locationPermissionDenied() {
        // Mock the context and permission state
        val permissions = AppPermissions()

        // Mock the checkSelfPermission method to return PERMISSION_DENIED
        mockStatic(ContextCompat::class.java).use { mockedContextCompat ->
            Mockito.`when`(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION))
                .thenReturn(PackageManager.PERMISSION_DENIED)

            assertFalse(permissions.isLocationOk(context))
        }
    }


}
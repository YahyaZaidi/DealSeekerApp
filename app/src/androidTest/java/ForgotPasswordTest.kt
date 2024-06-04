package com.example.dealseekerapplication

import com.google.firebase.database.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForgotPasswordTest {

    @Mock
    private lateinit var mockDatabase: FirebaseDatabase

    @Mock
    private lateinit var mockDatabaseReference: DatabaseReference

    @Mock
    private lateinit var mockDataSnapshot: DataSnapshot

    @Captor
    private lateinit var valueEventListenerCaptor: ArgumentCaptor<ValueEventListener>

    private lateinit var forgotPasswordActivity: forgotPassword

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        // Assuming ForgotPassword accepts FirebaseDatabase as a constructor parameter
        forgotPasswordActivity = forgotPassword(mockDatabase)
        `when`(mockDatabase.getReference("users")).thenReturn(mockDatabaseReference)
        forgotPasswordActivity.databaseReference = mockDatabaseReference
    }

    @Test
    fun testUpdatePassword_UserExists() {
        // Mock the database reference to return itself for chaining
        `when`(mockDatabaseReference.orderByChild("username")).thenReturn(mockDatabaseReference)
        `when`(mockDatabaseReference.equalTo("testUser")).thenReturn(mockDatabaseReference)

        // Trigger the test method
        forgotPasswordActivity.updatePassword("testUser", "newPassword")

        // Capture the ValueEventListener to trigger onDataChange
        verify(mockDatabaseReference).addListenerForSingleValueEvent(valueEventListenerCaptor.capture())
        val listener = valueEventListenerCaptor.value

        // Setup the DataSnapshot to simulate existing user
        `when`(mockDataSnapshot.exists()).thenReturn(true)
        `when`(mockDataSnapshot.children).thenReturn(listOf(mockDataSnapshot).asIterable())
        `when`(mockDataSnapshot.getValue(UserData::class.java)).thenReturn(UserData("testUser", "oldPassword"))

        // Trigger onDataChange
        listener.onDataChange(mockDataSnapshot)

        // Verify that setValue is called with updated password
        verify(mockDataSnapshot.ref).setValue(UserData("testUser", "newPassword"))

        // Additional verification for setValue can be added to confirm the exact user object changes
    }
}

data class UserData(val username: String, var password: String)  // Assuming UserData class structure


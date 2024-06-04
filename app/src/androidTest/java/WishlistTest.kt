package com.example.dealseekerapplication

import android.content.Context
import android.content.SharedPreferences
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class WishlistTest {

    private val sharedPreferences = mock(SharedPreferences::class.java)
    private val editor = mock(SharedPreferences.Editor::class.java)

    @Test
    fun testWishlistItemsLoading() {
        // Mock context and shared preferences
        val context = mock(Context::class.java)
        `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
        val jsonString = "[\"Item 1\", \"Item 2\"]"
        `when`(sharedPreferences.getString("wishlistItems", null)).thenReturn(jsonString)

        // Launch fragment and invoke loading of items
        val scenario = launchFragmentInContainer<Wishlist>()
        scenario.onFragment { fragment ->
            fragment.loadWishlistItems()
        }

        // Assert correct items are loaded
        scenario.onFragment { fragment ->
            assertThat(fragment.wishlistItems.size, `is`(2))
            assertThat(fragment.wishlistItems[0], `is`("Item 1"))
            assertThat(fragment.wishlistItems[1], `is`("Item 2"))
        }
    }

    @Test
    fun testSaveWishlistItems() {
        // Mocking the SharedPreferences editor
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)

        val scenario = launchFragmentInContainer<Wishlist>()
        scenario.onFragment { fragment ->
            fragment.wishlistItems.addAll(listOf("Item 1", "Item 2"))
            fragment.saveWishlistItems()
        }

        // Verify SharedPreferences was correctly interacted with
        verify(editor).putString(eq("wishlistItems"), anyString())
        verify(editor).apply()
    }

    @Test
    fun testItemRemovalFromWishlist() {
        // Prepare fragment with initial items
        val scenario = launchFragmentInContainer<Wishlist>()
        scenario.onFragment { fragment ->
            fragment.wishlistItems.addAll(listOf("Item 1", "Item 2"))
        }

        // Remove item and assert size change
        scenario.onFragment { fragment ->
            val listView = mock(ListView::class.java)  // Mocking ListView if needed for the getView call
            val view = fragment.adapter.getView(0, null, listView)  // Obtaining the view for the first item
            fragment.adapter.remove(fragment.wishlistItems[0])  // Remove the first item
        }

        scenario.onFragment { fragment ->
            assertThat(fragment.wishlistItems.size, `is`(1))
            assertThat(fragment.wishlistItems.contains("Item 1"), `is`(false))
            assertThat(fragment.wishlistItems.contains("Item 2"), `is`(true))
        }
    }
}



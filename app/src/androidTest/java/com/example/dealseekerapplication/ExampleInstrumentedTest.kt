package com.example.dealseekerapplication

import android.view.ViewTreeObserver
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.action.ViewActions.click
import androidx.fragment.app.testing.FragmentScenario
import com.example.dealseekerapplication.Profile
import com.example.dealseekerapplication.R
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureTimeMillis

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun testButtonResponseTime(){
        val scenario = FragmentScenario.launchInContainer(Profile::class.java)

        scenario.onFragment { fragment: Profile ->
            val view = fragment.view ?: return@onFragment
            view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    view.viewTreeObserver.removeOnPreDrawListener(this)

                    val duration = measureTimeMillis {
                        onView(withId(R.id.settings)).perform(click())
                    }
                    println("Button click response time: $duration ms")

                    return true
                }
            })
        }
    }
}






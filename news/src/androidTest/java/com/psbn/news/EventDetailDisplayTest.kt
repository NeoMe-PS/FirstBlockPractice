package com.psbn.news

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.psbn.news.presentation.models.CategoryUI
import com.psbn.news.presentation.models.EventUI
import com.psbn.news.presentation.ui.NewsDetailFragment
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventDetailDisplayTest {

    val args = EventUI(
        id = 1,
        categories = listOf(CategoryUI(1, "asd", "asd")),
        label = "event.label",
        shortDesc = "event.shortDesc",
        fullDesc = "event.fullDesc",
        date = "Осталось 0 дней (07.17 - 07.17)",
        dateStart = "07.17",
        dateEnd = "07.17",
        thumbnail = 123,
        newsImages = listOf(3, 2),
        address = "event.address",
        phone = "event.phone",
        company = "event.company",
        diffInDays = "0",
        isRead = false
    )

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appContext.setTheme(R.style.TestTheme)
        assertEquals("com.psbn.news.test", appContext.packageName)
    }

    @Test
    fun testEventFragment() {
        // The "fragmentArgs" argument is optional.
        val fragmentArgs = bundleOf("“event”" to args)
        val scenario = launchFragment<NewsDetailFragment>(fragmentArgs)

        onView(withId(R.id.help_money_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.help_money_btn)).check(matches(isClickable()))

        onView(withId(R.id.image_button_back)).check(matches(isDisplayed()))
        onView(withId(R.id.image_button_back)).check(matches(isClickable()))

        onView(withId(R.id.help_money_btn)).perform(click()).check(matches(isDisplayed()));
    }

}

package com.psbn.news.presentation.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.psbn.news.R
import com.psbn.news.presentation.models.CategoryUI
import com.psbn.news.presentation.models.EventUI
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventDetailDisplayTest {

    private val args = EventUI(
        id = 1,
        categories = listOf(CategoryUI(1, "asd", "asd")),
        label = "event.label",
        shortDesc = "event.shortDesc",
        fullDesc = "event.fullDesc",
        date = "Осталось 0 дней (07.17 - 07.17)",
        dateStart = "07.17",
        dateEnd = "07.17",
        thumbnail = 123,
        newsImages = listOf(3, 2, 3),
        address = "event.address",
        phone = "event.phone",
        company = "event.company",
        diffInDays = "0",
        isRead = false
    )
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        assertEquals("com.psbn.news.test", appContext.packageName)
    }

    @Test
    fun testEventFragment() {
        val fragmentArgs = bundleOf("event" to args)
        val scenario = launchFragmentInContainer<NewsDetailFragment>(
            fragmentArgs = fragmentArgs,
            themeResId = com.google.android.material.R.style.Theme_MaterialComponents
        )
        // проверка правильности отображения макета
        onView(withId(R.id.news_title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.timer_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.fond_name_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.address_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.phones_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.image_button_back)).check(matches(isDisplayed()))
        onView(withId(R.id.image_button_back)).check(matches(isClickable()))
        onView(withId(R.id.help_money_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.help_money_btn)).check(matches(isClickable()))

        // проверки значений текста из аргументов
        onView(withId(R.id.news_title_tv)).check(matches(withText("event.label")))
        onView(withId(R.id.timer_tv)).check(matches(withText("Осталось 0 дней (07.17 - 07.17)")))
        onView(withId(R.id.fond_name_tv)).check(matches(withText("event.company")))
        onView(withId(R.id.address_tv)).check(matches(withText("event.address")))
        onView(withId(R.id.phones_tv)).check(matches(withText("event.phone")))

        // проверка отыртия дилаога
        onView(withId(R.id.help_money_btn)).perform(click())
        onView(withId(R.id.first_hint_text))
            .inRoot(isDialog())

        // проврека вводимых значений
        onView(withId(R.id.value_edittext)).perform(typeText("250"))
        onView(withId(R.id.value_edittext)).check(matches(withText("250")))

        // проврека кнопки с заявленным значением 1000 значений
        onView(withId(R.id.sum_1000_rb)).perform(click())
        onView(withId(R.id.value_edittext)).check(matches(withText("1000")))

        onView(withId(R.id.cancel_button)).perform(click())
    }
}

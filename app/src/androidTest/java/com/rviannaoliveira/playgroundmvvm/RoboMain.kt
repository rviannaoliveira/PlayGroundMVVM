
import android.os.SystemClock.sleep
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rviannaoliveira.main.presentation.adapter.CharacterListViewHolder
import com.rviannaoliveira.playgroundmvvm.R
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not

fun listItems(func: RoboMain.() -> Unit) = RoboMain().apply(func)

class RoboMain {

    fun checkCharacterScreen(characterName: String): RoboMain {
        sleep(1500)
        onView(withText(containsString(characterName))).check(matches(isDisplayed()))
        return this
    }

    fun checkCharacterScreenDoesntExist(characterName: String): RoboMain {
        onView(withText(containsString(characterName))).check(matches(not(isDisplayed())))
        return this
    }

    fun clickItem(position: Int): RoboMain {
        sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CharacterListViewHolder>(position, click()))
        return this
    }

    fun backToList(): RoboMain {
        sleep(3000)
        onView(withContentDescription("Navigate up")).perform(click())
        return this
    }

}

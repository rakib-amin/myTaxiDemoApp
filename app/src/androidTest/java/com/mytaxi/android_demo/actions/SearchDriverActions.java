package com.mytaxi.android_demo.actions;

import android.support.test.rule.ActivityTestRule;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mytaxi.android_demo.utils.TestUtils.waitForSeconds;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

public class SearchDriverActions {

    private ActivityTestRule activityTestRule;

    public SearchDriverActions(ActivityTestRule activityTestRule) {
        this.activityTestRule = activityTestRule;
    }

    public void partialSearch(String partialName) {

        for (int i = 0; i < partialName.length(); i++) {

            waitForSeconds(1);

            onView(withId(R.id.textSearch))
                    .perform(typeText(Character.toString(partialName.charAt(i))));
        }
    }

    public void openDriverFromList(String driverName) {

        onView(withText(driverName))
                .inRoot(withDecorView(not(is(this.activityTestRule.getActivity()
                        .getWindow()
                        .getDecorView()))))
                .perform(click());

        onView(withId(R.id.textViewDriverName)).check(matches(withText(containsString(driverName))));
    }
}

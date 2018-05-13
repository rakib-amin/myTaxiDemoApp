package com.mytaxi.android_demo.actions;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CallDriverActions {

    public void callDriver() {

        onView(withId(R.id.fab)).perform(click());
    }

}

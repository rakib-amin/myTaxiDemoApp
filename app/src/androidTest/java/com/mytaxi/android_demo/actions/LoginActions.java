package com.mytaxi.android_demo.actions;

import android.support.test.espresso.contrib.DrawerActions;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mytaxi.android_demo.utils.TestUtils.waitForSeconds;

public class LoginActions {

    public void login(String username, String password) {

        onView(withId(R.id.edt_username)).perform(clearText());
        onView(withId(R.id.edt_username)).perform(typeText(username), closeSoftKeyboard());

        onView(withId(R.id.edt_password)).perform(clearText());
        onView(withId(R.id.edt_password)).perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        waitForSeconds(1);
    }

    public void logout() {

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());

        waitForSeconds(1);

        onView(withText("Logout")).perform(click());
    }
}

package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.actions.LoginActions;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/*
* Login Activity Test
*
* This class contains all possible tests of currently available validations
* on Login Screen
*
* TODO:
* - add username validation tests when validations are available in the app
* - add password validation tests when validations are available in the app
*
* @author Rakib Amin
* @since 11.05.2018
* */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private LoginActions loginActions;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void init() {
        TestUtils.cleanSlate();
        this.loginActions = new LoginActions();
    }

    /*
    * Tests an invalid login with invalid username/password
    * */
    @Test
    public void testInvalidLogin() {

        loginActions.login("whiteelephant", "video");

        onView(withText(R.string.message_login_fail))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    /*
     * Tests a valid login with valid username/password
     * */
    @Test
    public void testValidLogin() {

        loginActions.login("whiteelephant261", "video1");

        onView(withId(R.id.textSearch)).check(matches(isDisplayed()));
    }

    /*
     * Tests logout in case of a valid login has been performed
     * */
    @Test
    public void testLogout() {

        loginActions.login("whiteelephant261", "video1");

        loginActions.logout();

        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }

    @After
    public void cleanup() {
        TestUtils.cleanSlate();
    }

}

package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;

import com.mytaxi.android_demo.actions.CallDriverActions;
import com.mytaxi.android_demo.actions.LoginActions;
import com.mytaxi.android_demo.actions.SearchDriverActions;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.IsNot.not;

/*
 * Search and Call Activity Test
 *
 * This class contains all possible tests of currently available validations
 * on Search Screen and Driver Profile Screen
 *
 * TODO:
 * - add "No data found" validation test for search when available
 *
 * @author Rakib Amin
 * @since 11.05.2018
 * */

public class SearchAndCallActivityTest {

    private LoginActions loginActions;
    private SearchDriverActions searchDriverActions;
    private CallDriverActions callDriverActions;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void init() {
        TestUtils.cleanSlate();
        this.loginActions = new LoginActions();
        this.searchDriverActions = new SearchDriverActions(mActivityRule);
        this.callDriverActions = new CallDriverActions();
    }

    /*
    * Test search functionality by partially searching and populating a list
    * */
    @Test
    public void testSearchDrivers() {

        loginActions.login("whiteelephant261", "video1");

        searchDriverActions.partialSearch("sa");

        onView(TestUtils.atLeastOneMatch(withText(startsWith("Sa"))))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity()
                        .getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /*
     * Test open a driver from filtered search results
     * */
    @Test
    public void testOpenDriverProfile() {

        loginActions.login("whiteelephant261", "video1");

        searchDriverActions.partialSearch("sa");

        searchDriverActions.openDriverFromList("Sarah Friedrich");
    }

    /*
     * Test open a driver's profile from filtered search results, and call him/her
     * */
    @Test
    public void testSearchAndCallDriver() {

        loginActions.login("whiteelephant261", "video1");

        searchDriverActions.partialSearch("sa");

        searchDriverActions.openDriverFromList("Sarah Friedrich");

        callDriverActions.callDriver();
    }

    @After
    public void cleanUp() {
        TestUtils.cleanSlate();
    }

}

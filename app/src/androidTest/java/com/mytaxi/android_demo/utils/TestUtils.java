package com.mytaxi.android_demo.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class TestUtils {

    /*
    * Cleans Up SharedPreferences for destroying authentication, resulting in a clean start up.
    * <!-- Note: For Passed and Failed Tests, it's harder to keep track of App's Authentication State,
    * so this method cleans up the SharedPreference which acts as a storage for authentication
    * credentials. See Readme.MD file in the repo for a complete explanation. -->
    *
    * */
    public static void cleanSlate() {

        // for first time apk installation
        allowPermissionsIfNeeded("ACCESS_FINE_LOCATION");

        InstrumentationRegistry.getTargetContext()
                .getSharedPreferences("MytaxiPrefs", Context.MODE_PRIVATE)
                .edit().clear().commit();
    }

     /*
     * Waits For Given amount of seconds using Thread.sleep()
     * <!-- Note: The reason this hard-coded and seemingly dirty wait function exists, is because
     * there are lack of places we can use/implement IdlingResources in the source code. See
     * Readme.MD file in the repo for a complete explanation. -->
     *
     * @param seconds Number of seconds to wait manually
     * @see Thread
     * */
    public static void waitForSeconds(int seconds) {

        try {
            Thread.sleep((long) seconds * 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    * Handles Permission Alert Dialog Thrown by System
    * See <a href="https://gist.github.com/rocboronat/65b1187a9fca9eabfebb5121d818a3c4">Gist</a>
    *
    * @author rocboronat
    * @param permissionNeeded Android Permissions
    * */
    private static void allowPermissionsIfNeeded(String permissionNeeded) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasNeededPermission(permissionNeeded)) {

                UiDevice device = UiDevice.getInstance(getInstrumentation());
                UiObject allowPermissions = device.findObject(new UiSelector()
                        .clickable(true)
                        .checkable(false)
                        .index(1));
                if (allowPermissions.exists()) {
                    allowPermissions.click();
                }
            }
        } catch (UiObjectNotFoundException e) {
            Log.e("UIAutomator", "There is no permissions dialog to interact with");
        }
    }

    private static boolean hasNeededPermission(String permissionNeeded) {
        Context context = InstrumentationRegistry.getTargetContext();
        int permissionStatus = ContextCompat.checkSelfPermission(context, permissionNeeded);
        return permissionStatus == PackageManager.PERMISSION_GRANTED;
    }

    /*
    * Custom Matcher for when you have more than one view on the screen with the same text, and
    * you need at least one match
    * See <a href="https://stackoverflow.com/a/36866682/2453382">Explanation</a>
    *
    * @author mattmook
    * @param matcher the matcher matching given ViewMatcher
    * */
    public static <T> Matcher<T> atLeastOneMatch(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isFirst = true;

            @Override
            public boolean matches(final Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }

                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return first matching item");
            }
        };
    }
}

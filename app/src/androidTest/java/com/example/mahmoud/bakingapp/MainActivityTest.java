package com.example.mahmoud.bakingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
/**
 * Created by mahmoudelsakka on 11/01/18.
 */


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkLoading(){
        if(isConnected(mainActivityActivityTestRule.getActivity())){
            onView(withId(R.id.rv_recipe_card)).check(matches(isCompletelyDisplayed()));
        }
    }
    @Test
    public void recipeCount(){
        if(isConnected(mainActivityActivityTestRule.getActivity())){
            onView(withId(R.id.rv_recipe_card)).check(new RecycleViewItemCount(4));
        }
    }

    public class RecycleViewItemCount implements ViewAssertion{

        private final int expCount;

        public RecycleViewItemCount(int expCount){
            this.expCount = expCount;
        }
        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if(noViewFoundException != null){
                throw noViewFoundException;
            }
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(),is(expCount));
        }
    }

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

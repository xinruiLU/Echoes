/*
 * Class Name: MyHabitsActivity
 *
 * Version: Version 1.0
 *
 * Date: October 23rd, 2017
 *
 * Copyright (c) Team cmput301f17t19, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta
 */

package com.example.cmput301f17t19.echoes;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

import static com.example.cmput301f17t19.echoes.LoginActivity.LOGIN_USERNAME;

/**
 * My Habits UI
 *
 * @author Shan Lu
 * @version 1.0
 * @since 1.0
 */
public class MyHabitsActivity extends AppCompatActivity {

    private RecyclerView habitsRecyclerView;
    private static HabitOverviewAdapter habitOverviewAdapter;

    private static Context mContext;

    // The userName of the Logged-in user
    private String login_userName;
    // The user profile of the logged-in user
    private static UserProfile login_userProfile;
    // The arrayList of Habits of the login user
    private static ArrayList<Habit> mHabits;

    private Button addHabitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.primary_dark));
        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhabits);
        this.setTitle(R.string.my_habits);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get the login username and user Profile
        Intent intent = getIntent();
        login_userName = intent.getStringExtra(LOGIN_USERNAME);
        login_userProfile = getLogin_UserProfile();

        mContext = this;

        // Set up recycler view for habit event overview in the Habit History
        habitsRecyclerView = (RecyclerView) findViewById(R.id.habits_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        habitsRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(habitsRecyclerView.getContext(),
                layoutManager.getOrientation());
        habitsRecyclerView.addItemDecoration(mDividerItemDecoration);

        habitsRecyclerView.setHasFixedSize(true);

        addHabitButton = (Button) findViewById(R.id.habit_add_button);

        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open an empty Habit Detail Screen
                Intent habitDetail_Intent = new Intent(mContext, HabitDetail.class);
                mContext.startActivity(habitDetail_Intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // The arrayList of habits of the login user
        mHabits = login_userProfile.getHabit_list().getHabits();

        habitOverviewAdapter = new HabitOverviewAdapter(getApplicationContext());

        habitsRecyclerView.setAdapter(habitOverviewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mapp_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_menu:
                // Go back to main menu
                // Pass the username of the login user to the main menu
                Intent mainMenu_intent = new Intent(getApplicationContext(), main_menu.class);
                mainMenu_intent.putExtra(LOGIN_USERNAME, login_userName);
                startActivity(mainMenu_intent);

                finish();

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Get the arrayList of Habit displayed in My Habits
     */
    public static ArrayList<Habit> getHabits_MyHabits(){
        // Get the arrayList of habits of the logged-in user
        return mHabits;
    }

    /**
     * Update the HabitList of the Logged-in User
     *
     * @param updated_HabitList: ArrayList<Habit>, the update HabitList of the logged-in User
     */
    public static void updateHabitList(ArrayList<Habit> updated_HabitList) {
        login_userProfile.getHabit_list().setHabits(updated_HabitList);
        mHabits = updated_HabitList;
    }

    /**
     * Update My Habits Screen and User Profile file and online data of the logged-in user
     */
    public static void updateDataStorage() {
        // Update Screen
        habitOverviewAdapter.notifyDataSetChanged();

        // Update offline file
        OfflineStorageController offlineStorageController = new OfflineStorageController(mContext, login_userProfile.getUserName());
        offlineStorageController.saveInFile(login_userProfile);

        // Update Online data
        ElasticSearchController.syncOnlineWithOffline(login_userProfile);
    }

    /**
     * Get the Login user Profile from offline file
     *
     * @return UserProfile: the User Profile of the login User
     */
    private UserProfile getLogin_UserProfile() {
        OfflineStorageController offlineStorageController = new OfflineStorageController(this, login_userName);

        return offlineStorageController.readFromFile();
    }
}

package com.example.jana.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.jana.R;
import com.example.jana.fragment.CategoryFragment;
import com.example.jana.fragment.DashboardFragment;
import com.example.jana.fragment.ProfileFragment;
import com.example.jana.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity  {
    RelativeLayout topappbar;
    public static FragmentManager fm = null;
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        topappbar = findViewById(R.id.topappbar);
        fm = getSupportFragmentManager();



        chipNavigationBar = findViewById(R.id.chipNavigationBar);
        chipNavigationBar.setItemSelected(R.id.dashboard_nav,
                true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,
                        new DashboardFragment()).commit();

        bottomMenu();
    }

    private void bottomMenu() {


        chipNavigationBar.setOnItemSelectedListener
                (new ChipNavigationBar.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int i) {
                        Fragment fragment = null;
                        switch (i){
                            case R.id.dashboard_nav:
                                fragment = new DashboardFragment();
                                topappbar.setVisibility(View.VISIBLE);
                                break;
                            case R.id.category_nav:
                                fragment = new CategoryFragment();
                                topappbar.setVisibility(View.VISIBLE);
                                break;

                            case R.id.profile_nav:
                                fragment = new ProfileFragment();
                                topappbar.setVisibility(View.GONE);
                                break;

                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,
                                        fragment).commit();
                    }
                });
    }




    }







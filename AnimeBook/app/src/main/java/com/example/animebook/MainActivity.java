package com.example.animebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.animebook.fragments.AnimeListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        //TODO: Define fragments here
        //final Fragment animeFragment = new AnimeFragment();
        //add others as needed

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.action_animeList:
                        fragment = new AnimeListFragment();
                        Log.i(TAG, "Anime List Selected");
                        break;
                    case R.id.action_search:
                        //TODO: Add your fragment here. Replace AnimeListFragment
                        fragment = new AnimeListFragment();
                        Log.i(TAG, "Search Selected");
                        break;
                    //Add other cases as needed
                    default:
                        //TODO: Add your fragment here. Replace AnimeListFragment
                        fragment = new AnimeListFragment();
                        Log.i(TAG, "Profile Selected");
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        //Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_animeList);
    }
}
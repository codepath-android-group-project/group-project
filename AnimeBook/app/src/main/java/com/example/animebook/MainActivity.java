package com.example.animebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.animebook.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        //TODO: Define fragments here
        //final Fragment animeFragment = new AnimeFragment();
        //add others as needed

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //will change this once all other fragments are done
                Fragment fragment = new Fragment();
                switch (item.getItemId()){
                    case R.id.action_animeList:
                        //fragment = animeFragment;
                        Log.i(TAG, "Anime List Selected");
                        break;
                    case R.id.action_search:
                        //fragment = searchFragment;
                        Log.i(TAG, "Search Selected");
                        break;
                    //Add other cases as needed
                    case R.id.action_profile:
                    default:
                        Toast.makeText(MainActivity.this, "profile",Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        //fragment = profileFragment;
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
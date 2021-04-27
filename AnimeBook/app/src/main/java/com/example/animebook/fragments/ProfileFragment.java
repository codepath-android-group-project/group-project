package com.example.animebook.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animebook.LoginActivity;
import com.example.animebook.MainActivity;
import com.example.animebook.R;
import com.parse.ParseUser;


public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";
    private ImageView profileImage;
    private TextView userName;

    //Rv for displaying user's favorite animes
    
    //private RecyclerView rvFavorites;
    private Button btLogout;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileImage = view.findViewById(R.id.profileImage);
        userName = view.findViewById(R.id.userName);
        //rvFavorites = view.findViewById(R.id.rvFavorites);
        btLogout = view.findViewById(R.id.btLogout);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goLoginActivity();
                Toast.makeText(getContext(), "logout", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goLoginActivity(){
        Intent intent = new Intent (getActivity(), LoginActivity.class);
        startActivity(intent);
    }

}
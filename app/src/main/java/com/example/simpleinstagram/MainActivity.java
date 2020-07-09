package com.example.simpleinstagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.simpleinstagram.databinding.ActivityMainBinding;
import com.example.simpleinstagram.fragments.ComposeFragment;
import com.example.simpleinstagram.fragments.HomeFragment;
import com.example.simpleinstagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bottomNavigationView = binding.bottomNavigation;
        btnLogout = binding.btnLogout;
        btnLogout.setVisibility(View.GONE);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = new HomeFragment();
                        btnLogout.setVisibility(View.GONE);
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        btnLogout.setVisibility(View.GONE);
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        btnLogout.setVisibility(View.VISIBLE);
                        btnLogout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ParseUser.getCurrentUser().logOut();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        });
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

}
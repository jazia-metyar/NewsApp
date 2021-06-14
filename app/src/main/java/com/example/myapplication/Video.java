package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
// Action Bar
        getSupportActionBar().hide();
        Toolbar toolbar=findViewById(R.id.topAppBar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext()
                                ,Search.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                ,Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.addPost:
                        startActivity(new Intent(getApplicationContext()
                                ,CreatePost.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.Video);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.Category:
                        startActivity(new Intent(getApplicationContext()
                                ,Category.class));
                        Video.this.finish();
                      //  bottomNavigationView.setItemTextColor();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Video:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));
                        Video.this.finish();
                        overridePendingTransition(0,0);
                        return true;
                   case R.id.Favorite:
                        startActivity(new Intent(getApplicationContext()
                                ,Favorite.class));
                        Video.this.finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
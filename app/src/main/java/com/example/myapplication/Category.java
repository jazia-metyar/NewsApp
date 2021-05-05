package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Category extends AppCompatActivity {
    LinearLayout layout1,layout2,layout3,layout4,
                 layout5,layout6,layout7,layout8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        // Action Bar
        getSupportActionBar().hide();


        layout1=findViewById(R.id.world);
        layout2=findViewById(R.id.entertainment);
        layout3=findViewById(R.id.topnews);
        layout4=findViewById(R.id.sport);
        layout5=findViewById(R.id.travel);
        layout6=findViewById(R.id.health);
        layout7=findViewById(R.id.technology);
        layout8=findViewById(R.id.business);


        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","World");

                startActivity(intent);
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","Entertainment");

                startActivity(intent);
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","Top News");

                startActivity(intent);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","Sports");

                startActivity(intent);
            }
        });


        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","Travel");

                startActivity(intent);
            }
        });
        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","Health");

                startActivity(intent);
            }
        });

        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","Technology");

                startActivity(intent);
            }
        });


        layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext()
                        , NewsSortCateg.class);
                intent.putExtra("category","Business");

                startActivity(intent);
            }
        });

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
                }
                return false;
            }
        });
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.Category);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.Category:
                        return true;

                    case R.id.Video:
                        startActivity(new Intent(getApplicationContext()
                                ,Video.class));
                        Category.this.finish();
                        bottomNavigationView.setItemIconTintList(null);

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));
                        Category.this.finish();
                        bottomNavigationView.setItemIconTintList(null);

                        overridePendingTransition(0,0);
                        return true;

                   case R.id.Favorite:
                        startActivity(new Intent(getApplicationContext()
                                ,Favorite.class));
                        Category.this.finish();
                       bottomNavigationView.setItemIconTintList(null);

                       overridePendingTransition(0,0);
                       return true;
                }

                return false;
            }
        });
    }
}
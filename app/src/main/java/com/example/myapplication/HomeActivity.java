package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private List<Post> listData;
    private RecyclerView rv;
    private PostListAdapter adapter;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        // Action Bar0
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



        // BottomNavigationView
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.Category:
                        startActivity(new Intent(getApplicationContext()
                                ,Category.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Video:
                        startActivity(new Intent(getApplicationContext()
                                ,Video.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                         return true;
                    case R.id.Favorite:
                        startActivity(new Intent(getApplicationContext()
                                ,Favorite.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        show();

    }



    public void show() {


        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Posts");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        Post l=npsnapshot.getValue(Post.class);
                        listData.add(l);
                    }
                    adapter=new PostListAdapter(getApplicationContext(),listData);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






/*
        imageView=(ImageView)findViewById(R.id.imageView);
        title=findViewById(R.id.textView9);
        category=findViewById(R.id.textView10);
        date=findViewById(R.id.textView11);
        body=findViewById(R.id.textView12);
        ref= FirebaseDatabase.getInstance().getReference().child("Posts").child("km");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    title.setText(snapshot.child("title").getValue().toString());
                    date.setText(snapshot.child("date").getValue().toString());
                    category.setText(snapshot.child("cat").getValue().toString());
                    body.setText(snapshot.child("body").getValue().toString());
                    String link=snapshot.child("photos").getValue(String.class);
                     /*Picasso.get().load(link)
                        .into(imageView);*/
      /*              Glide.with(getApplicationContext())
                        .load(link)
                        .into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        // Ajouter des éléments au RecyclerView.
        for (int i = 0; i < mWordList.size(); i++) {
            mWordList.addLast();
        }

        // Créer une variable de type reycylerView
        mRecyclerView = findViewById(R.id.recyclerview);

        // Créer l'Adapter et lui passer la liste dont il va afficher les éléments
        mAdapter = new PostListAdapter(this, mWordList);

        // Connecter l'Adapter à notre RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        // Passer au RecyclerView le LayoutManager désiré
        LinearLayoutManager l = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(l);
*/

    }
}
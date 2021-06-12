package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsSortCateg extends AppCompatActivity {
    private List<Post> listData;
    private RecyclerView rv;
    private PostListAdapter adapter;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newssortcateg);
        getSupportActionBar().hide();
        Toolbar toolbar=findViewById(R.id.topAppBar);
        category=getIntent().getStringExtra("category");
        toolbar.setTitle(" "+category);



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

    }


    @Override
    protected void onStart() {
        super.onStart();
        show();
    }

    public void show() {


        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Posts");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Post l = npsnapshot.getValue(Post.class);
                         if (l.getCat().equals(category))
                            listData.add(l);
                    }
                    adapter = new PostListAdapter(getApplicationContext(), listData);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
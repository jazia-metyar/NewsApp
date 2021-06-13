package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostDetails extends AppCompatActivity {
    private TextView date,body,title,category;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        title=findViewById(R.id.textView9);
        category=findViewById(R.id.textView10);
        date=findViewById(R.id.textView11);
        body=findViewById(R.id.textView12);
        imageView=(ImageView)findViewById(R.id.imageView);

        title.setText(getIntent().getStringExtra("title"));
        body.setText(getIntent().getStringExtra("body"));
        category.setText(getIntent().getStringExtra("category"));
        date.setText(getIntent().getStringExtra("date"));
        String link=getIntent().getStringExtra("image");
        Picasso.get().load(link)
                .into(imageView);

    }
}
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class CreatePost extends AppCompatActivity {
    private TextInputLayout title,date,category,body;
    private ImageView imageView;
    private Button save,photo;
    private DatabaseReference ref;
    Uri FilePathUri;
    StorageReference storageReference;
 //   DatabaseReference databaseReference;
    int Image_Request_Code = 2;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        title=findViewById(R.id.title);
        date=findViewById(R.id.date);
        category=findViewById(R.id.category);
        body=findViewById(R.id.body);
        save=findViewById(R.id.save);
        photo=findViewById(R.id.photo);

        imageView=(ImageView) findViewById(R.id.imageView);
        storageReference = FirebaseStorage.getInstance().getReference("Posts");
        ref= FirebaseDatabase.getInstance().getReference().child("Posts");
        progressDialog = new ProgressDialog(CreatePost.this);
        photo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent();
              intent.setType("image/*");
              intent.setAction(Intent.ACTION_GET_CONTENT);
              startActivityForResult(Intent.createChooser(intent, "Select Image"),2);

                }
            });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FilePathUri!=null){
                    UploadToFireBase(FilePathUri);


                }else{
                    Toast.makeText(CreatePost.this, "Please select an image", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            FilePathUri=data.getData();
            imageView.setImageURI(FilePathUri);
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    // old version

   /*
    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." +GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String postTitle=title.getEditText().getText().toString();
                            String postDate=date.getEditText().getText().toString();
                            String postCategory=category.getEditText().getText().toString();
                            String postBody=body.getEditText().getText().toString();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            Post post = new Post(postDate, postTitle,postBody,postCategory,taskSnapshot.getUploadSessionUri().toString());

                           ref.child(postTitle).setValue(post);
                          //  Toast.makeText(CreatePost.this, "Post created successfully", Toast.LENGTH_LONG).show();

                         // String ImageUploadId = ref.push().getKey();
                         //   ref.child(ImageUploadId).setValue(post);
                        }
                    });
        }
        else {

            Toast.makeText(CreatePost.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }*/

    // new version
    private void UploadToFireBase(Uri uri){
        progressDialog.setTitle("Image is Uploading...");
        progressDialog.show();
        StorageReference fileRef=storageReference.child(System.currentTimeMillis()+"."+GetFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String postTitle=title.getEditText().getText().toString();
                        String postDate=date.getEditText().getText().toString();
                        String postCategory=category.getEditText().getText().toString();
                        String postBody=body.getEditText().getText().toString();
                        Post post = new Post(postDate, postTitle,postBody,postCategory,uri.toString());

                       // String modelId=ref.push().getKey();
                        ref.child(postTitle).setValue(post);
                        progressDialog.dismiss();
                        Toast.makeText(CreatePost.this, "uploaded successfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreatePost.this, "Problem upload", Toast.LENGTH_LONG).show();
            }
        });
    }
}
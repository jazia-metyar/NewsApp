package com.example.myapplication;

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
    int Image_Request_Code = 7;
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
     //   databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        progressDialog = new ProgressDialog(CreatePost.this);
      photo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent();
              intent.setType("image/*");
              intent.setAction(Intent.ACTION_GET_CONTENT);
              startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

          }
      });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UploadImage();

            }
        });

      /*

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PostTitle=title.getEditText().getText().toString();
                String PostDate=date.getEditText().getText().toString();
               String PostCategory=category.getEditText().getText().toString();
                String PostBody=body.getEditText().getText().toString();
                String postPhoto=imageView.toString();
               // Glide.with(getApplicationContext()).load(postPhoto).into(imageView);
                Post post1=new Post(PostDate,PostTitle,PostBody,
                        PostCategory,postPhoto);

                ref.child(PostCategory).child(PostTitle).setValue(post1);
                Toast.makeText(CreatePost.this, "Post created successfully", Toast.LENGTH_LONG).show();

            }
        });*/


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }





    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
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

                            ref.child(postCategory).setValue(post);
                          //  Toast.makeText(CreatePost.this, "Post created successfully", Toast.LENGTH_LONG).show();

                         //   String ImageUploadId = ref.push().getKey();
                          //  ref.child(ImageUploadId).setValue(post);
                        }
                    });
        }
        else {

            Toast.makeText(CreatePost.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }












  /*  @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent intent)
    {
        super.onActivityResult(requestCode, resultcode, intent);

        if (requestCode == 1)
        {
            if (intent != null && resultcode == RESULT_OK)
            {

                Uri selectedImage = intent.getData();
                Toast.makeText(CreatePost.this, "Image created successfully", Toast.LENGTH_LONG).show();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                if(bitmap != null && !bitmap.isRecycled())
                {
                    bitmap = null;
                }

                bitmap = BitmapFactory.decodeFile(filePath);

                imageView.setBackgroundResource(0);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(CreatePost.this, "photo created successfully", Toast.LENGTH_LONG).show();



            }
            else
            {
                Log.d("Status:", "Action Not Completed");
            }
        }
    }*/
}
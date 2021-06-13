package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private TextInputLayout name,email,password,phone;
    private Button regBtn ,regToLoginBtn,privacy;
    private DatabaseReference reff;
    private User user;
    FirebaseAuth fAuth;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
            getSupportActionBar().hide();
        //Hooks to all wml elements
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        regBtn=findViewById(R.id.register);
        regToLoginBtn=findViewById(R.id.textButton1);
        privacy=findViewById(R.id.textButton);
         fAuth = FirebaseAuth.getInstance();

        user=new User();
        reff= FirebaseDatabase.getInstance().getReference().child("User");


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fEmail=email.getEditText().getText().toString().trim();
                String fPassword=password.getEditText().getText().toString().trim();
                String fPhone=phone.getEditText().getText().toString().trim();
                user.setName(name.getEditText().getText().toString().trim());
                user.setPhone(fPhone);
                user.setEmail(fEmail);
                user.setPassword(fPassword);



                fAuth.createUserWithEmailAndPassword(fEmail,fPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Profile.this, "User created successfully", Toast.LENGTH_LONG).show();

                            /*
                            ////
                            /////register in realtime database
                            */
                            reff.child(fPhone).setValue(user);
                            Toast.makeText(Profile.this,"Data saved successfully",Toast.LENGTH_LONG).show();

                               startActivity(new Intent(getApplicationContext(),LogIn.class));
                                finish();
                        }
                        else
                        {
                            Toast.makeText(Profile.this, "Errorrr!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });





            }
        });

      regToLoginBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext()
                      ,LogIn.class));
              finish();


          }
      });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
         if (currentUser!=null) {
             startActivity(new Intent(getApplicationContext()
                     , MyProfile.class));
         finish();
         }
    }
}
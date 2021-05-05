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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {
    private TextInputLayout email,password;
    //private TextInputLayout phone;
    private Button logBtn ,toRegBtn;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
        // phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        logBtn=findViewById(R.id.btnlogin);
        toRegBtn=findViewById(R.id.textButton1);
       fAuth=FirebaseAuth.getInstance();

        logBtn.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fEmail=email.getEditText().getText().toString().trim();
                String fPassword=password.getEditText().getText().toString().trim();

                fAuth.signInWithEmailAndPassword(fEmail,fPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {
                           Toast.makeText(LogIn.this, "LogIn successfully", Toast.LENGTH_LONG).show();
                           System.err.println(fEmail);
                           System.err.println(fPassword);

                           startActivity(new Intent(getApplicationContext()
                                   , MyProfile.class));
                           finish();
                       }
                       else
                           {
                           Toast.makeText(LogIn.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                           }

                       }
                });
            }
        });
      /*  logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });*/
        toRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext()
                        ,Profile.class));
                 finish();

            }
        });


    }

   /* public void logIn(){
        String fPhone=phone.getEditText().getText().toString().trim();
        String fPassword=password.getEditText().getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User");
        Query checkUser=reference.orderByChild("phone").equalTo(fPhone);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              if(snapshot.exists())
              { phone.setError(null);
                  phone.setErrorEnabled(false);
                String passwordFromDb=snapshot.child(fPhone).child("password").getValue(String.class);
                if(passwordFromDb.equals(fPassword))
                {   password.setError(null);
                    password.setErrorEnabled(false);

                    String nameFromDb=snapshot.child(fPhone).child("name").getValue(String.class);
                    String emailFromDb=snapshot.child(fPhone).child("email").getValue(String.class);
                    String phoneFromDb=snapshot.child(fPhone).child("phone").getValue(String.class);

                    Intent intent=new Intent(getApplicationContext(),MyProfile.class);
                    intent.putExtra("name",nameFromDb);
                    intent.putExtra("email",emailFromDb);
                    intent.putExtra("password",passwordFromDb);
                    intent.putExtra("phone",phoneFromDb);

                    startActivity(intent);

                }
                else {
                    password.setError("wrong password");
                   password.requestFocus();
                }
            }
              else {
                  phone.setError("No such user exists");
                  phone.requestFocus();
              }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
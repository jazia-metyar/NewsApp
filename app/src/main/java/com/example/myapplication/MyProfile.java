package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfile extends AppCompatActivity {
    private TextInputLayout name,email,password,phone;
    private Button update;
    private DatabaseReference reff;
    private FirebaseAuth bD;

    String userName ,userPhone,userPassword,userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        update=findViewById(R.id.update);
        reff= FirebaseDatabase.getInstance().getReference().child("User");

        showAllUserData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    private void showAllUserData() {

        Intent intent =getIntent();
        userName=intent.getStringExtra("name");
        userEmail=intent.getStringExtra("email");
         userPassword=intent.getStringExtra("password");
         userPhone=intent.getStringExtra("phone");



        name.getEditText().setText(userName);
        email.getEditText().setText(userEmail);
        phone.getEditText().setText(userPhone);
        password.getEditText().setText(userPassword);
    }

    private void update(){
        if(isNameChanged()||isEmailChanged()||isPasswordChanged())
        {
            Toast.makeText(MyProfile.this,"Data has been updated",Toast.LENGTH_LONG).show();
        }
        if(!(isNameChanged() && isEmailChanged() && isPasswordChanged())){
            Toast.makeText(MyProfile.this,"Data is same and can't be updated",Toast.LENGTH_LONG).show();
        }
     /*   if(isEmailChanged())
        {
            Toast.makeText(MyProfile.this,"Data has been updated",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MyProfile.this,"Data is same and can't be updated",Toast.LENGTH_LONG).show();
        }

        if(isPasswordChanged())
        {
            Toast.makeText(MyProfile.this,"Data has been updated",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MyProfile.this,"Data is same and can't be updated",Toast.LENGTH_LONG).show();
        }*/


    }

    private boolean isNameChanged() {
        if(!userName.equals(name.getEditText().getText().toString()))
        {
           reff.child(userPhone).child("name").setValue(name.getEditText().getText().toString());
           userName=name.getEditText().getText().toString();
       return true;
        }
        else
            return false;
    }

    private boolean isEmailChanged() {
        if(!userEmail.equals(email.getEditText().getText().toString()))
        {
            reff.child(userPhone).child("email").setValue(email.getEditText().getText().toString());
            userEmail=email.getEditText().getText().toString();
            return true;
        }
        else
            return false;
    }
    private boolean isPasswordChanged() {
        if(!userPassword.equals(password.getEditText().getText().toString()))
        {
            reff.child(userPhone).child("password").setValue(password.getEditText().getText().toString());
            userPassword=password.getEditText().getText().toString();
            return true;
        }
        else
            return false;
    }
}
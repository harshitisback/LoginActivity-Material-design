package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button signup;

    TextInputLayout name, phone, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        signup = (Button) findViewById(R.id.signup);
        name = (TextInputLayout)findViewById(R.id.name);
        email = (TextInputLayout)findViewById(R.id.email);
        phone = (TextInputLayout)findViewById(R.id.phone);
        password = (TextInputLayout)findViewById(R.id.password);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });








    }

    private void userlogin() {
        String Name = name.getEditText().getText().toString().trim();
        String Email = email.getEditText().getText().toString().trim();
        String Phone = phone.getEditText().getText().toString().trim();
        String Password = password.getEditText().getText().toString().trim();

    }
}
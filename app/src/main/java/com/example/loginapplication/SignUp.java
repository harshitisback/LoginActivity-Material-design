package com.example.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button signup;

    TextInputLayout name, phone, email, password;
    ProgressBar progressBar;

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
        progressBar = (ProgressBar)findViewById(R.id.pb);



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

        if (Name.isEmpty()) {
            name.setError("Your name is required");
            name.requestFocus();
            return;
        }
        if (Email.isEmpty()) {
            email.setError("Your Email is required");
            email.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            password.setError("Your Pass is required");
            password.requestFocus();
            return;
        }
        if (Phone.isEmpty()){
            phone.setError("Your Phone is required");
            phone.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please Provide Valid email");
            email.requestFocus();
            return;
        }
        if (Password.length() < 6) {
            password.setError("Min Password length should be 6 char");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(Name,Email,Phone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(SignUp.this,"User has been registered succesfully",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignUp.this,LoginActivity.class));
                                            }else {
                                                Toast.makeText(SignUp.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }else{
                            Toast.makeText(SignUp.this,"Registraion Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
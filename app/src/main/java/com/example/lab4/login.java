package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email,password;
    Button loginBtn;
    FirebaseAuth fAuth;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        fAuth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.signUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vemail = email.getText().toString().trim();
                String vpassword = password.getText().toString().trim();
                if(TextUtils.isEmpty(vemail)){
                    email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(vpassword)){
                    password.setError("Password is Required");
                    return;
                }

                if (password.length()< 6){
                    password.setError("Password Must be at Least 6 Characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(vemail,vpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),clientActivity.class));
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sign_up.class));
            }
        });

    }
}

package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

class TrainerActivity extends AppCompatActivity {
    Button logoutBtn1;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        fAuth = FirebaseAuth.getInstance();

       // if(fAuth.getCurrentUser())
            logoutBtn1 = findViewById(R.id.logoutBtn1);

        logoutBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

    }



}

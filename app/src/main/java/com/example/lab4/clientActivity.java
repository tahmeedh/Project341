package com.example.lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class clientActivity extends AppCompatActivity {
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    String Name;
    TextView str;
    Button logoutBtn2;
    String userType;
    TextView ut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        str = findViewById(R.id.Nameid);
        logoutBtn2 = findViewById(R.id.logoutBtn1);



        DocumentReference dRef = fStore.collection("users").document(userId);
        dRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                userType = documentSnapshot.getString("UserType");
                Name = documentSnapshot.getString("name");
                str.setText(Name);

                if(userType.equals("Trainer")){
                    finish();
                    startActivity(new Intent(getApplicationContext(),TrainActivity.class));
                }

            }
        });

        logoutBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
    }

    public void showProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void viewProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }
    public void showWorkout(View view){
        Intent intent  = new Intent(this, inputData_Client.class);
        startActivity(intent);
    }

}

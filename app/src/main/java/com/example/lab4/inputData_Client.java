package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class inputData_Client extends AppCompatActivity {
    TextView workoutview;
    TextView repsview;
    TextView commentview;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata_client);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        workoutview  = findViewById(R.id.workout);
        repsview  = findViewById(R.id.reps);
        commentview  = findViewById(R.id.comment);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");


        DocumentReference documentReference = fStore.collection("userData").document(name);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String exercise = documentSnapshot.getString("exName");
                String reps = documentSnapshot.getString("numReps");
                String comments = documentSnapshot.getString("comment");
                workoutview.setText(exercise);
                repsview.setText(reps);
                commentview.setText(comments);
            }
        });


        }
    }


package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class inputData_Trainer extends AppCompatActivity {
    private ArrayList<Workout> worklist;
    private ArrayList<String> titleList;
    private Adapter adapter;
    private Spinner spinner_client;
    private EditText ex;
    private FirebaseFirestore reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata_trainer);
        worklist = DataFilter.loadWork(this);
        titleList = new ArrayList<>();
        spinner_client = findViewById(R.id.spinner_Client);
        for(int i = 0; i<worklist.size();i++) {
            String str = worklist.get(i).getTitle();
            titleList.add(str);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  titleList);

        ArrayAdapter<CharSequence> adapter_Client = ArrayAdapter.createFromResource(this,
                R.array.ary_Client, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       final Spinner spinner_Client = (Spinner) findViewById(R.id.spinner_Client);
        spinner_Client.setAdapter((SpinnerAdapter) adapter);

        ArrayAdapter<CharSequence> adapter_Rep = ArrayAdapter.createFromResource(this,
                R.array.ary_Reps, android.R.layout.simple_spinner_item);
        adapter_Rep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner_Rep = (Spinner) findViewById(R.id.spinner_Reps);
        spinner_Rep.setAdapter(adapter_Rep);


        Button button = findViewById(R.id.button_Submit);
        EditText exName = findViewById(R.id.exName);
        EditText comment = findViewById(R.id.comment);

        String faculty = spinner_Client.getSelectedItem().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            reff = FirebaseFirestore.getInstance();
                EditText exName = findViewById(R.id.exName);
                EditText comment = findViewById(R.id.comment);

                String client = spinner_Client.getSelectedItem().toString();
                String reps = spinner_Rep.getSelectedItem().toString();
                 String ex =exName.getText().toString();
                String cmt =comment.getText().toString();
                DocumentReference documentReference = reff.collection("userData").document(client);
                Map<String,Object> userIn = new HashMap<>();
                userIn.put("exName",ex);
                userIn.put("numReps",reps);
                userIn.put("comment",cmt);



                documentReference.set(userIn).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(), TrainActivity.class));
                    }
                });

            }
        });
    }
    public void Back(View view){
        Intent intent = new Intent(this, FitnessTracker.class);
        startActivity(intent);
    }

}


package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class inputData_Trainer extends AppCompatActivity {
    private ArrayList<Workout> worklist;
    private ArrayList<String> titleList;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata_trainer);
        worklist = DataFilter.loadWork(this);
        titleList = new ArrayList<>();
        for(int i = 0; i<worklist.size();i++) {
            String str = worklist.get(i).getTitle();
            titleList.add(str);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  titleList);

        ArrayAdapter<CharSequence> adapter_Client = ArrayAdapter.createFromResource(this,
                R.array.ary_Client, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner_Client = (Spinner) findViewById(R.id.spinner_Client);
        spinner_Client.setAdapter((SpinnerAdapter) adapter);

        ArrayAdapter<CharSequence> adapter_Rep = ArrayAdapter.createFromResource(this,
                R.array.ary_Reps, android.R.layout.simple_spinner_item);
        adapter_Rep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner_Rep = (Spinner) findViewById(R.id.spinner_Reps);
        spinner_Rep.setAdapter(adapter_Rep);


        Button button = findViewById(R.id.button_Submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inputData_Trainer.this, inputData_Client.class);
                startActivity(intent);
            }
        });
    }
    public void Back(View view){
        Intent intent = new Intent(this, FitnessTracker.class);
        startActivity(intent);
    }

}


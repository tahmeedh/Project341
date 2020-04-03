package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> adapter_Client = ArrayAdapter.createFromResource(this,
                R.array.ary_Client, android.R.layout.simple_spinner_item);
        adapter_Client.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner_Client = (Spinner) findViewById(R.id.spinner_Client);
        spinner_Client.setAdapter(adapter_Client);

        ArrayAdapter<CharSequence> adapter_Reps = ArrayAdapter.createFromResource(this,
                R.array.ary_Reps, android.R.layout.simple_spinner_item);
        adapter_Reps.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner_Reps = (Spinner) findViewById(R.id.spinner_Reps);
        spinner_Reps.setAdapter(adapter_Reps);

        ArrayAdapter<CharSequence> adapter_Exercise = ArrayAdapter.createFromResource(this,
                R.array.ary_Exercise, android.R.layout.simple_spinner_item);
        adapter_Exercise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner_Exercise = (Spinner) findViewById(R.id.spinner_Exercise);
        spinner_Exercise.setAdapter(adapter_Exercise);

    }
}

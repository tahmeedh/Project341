package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;

public class Write extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.divisionList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void submit(View view) {
        RadioGroup group = (findViewById(R.id.select));
        Integer checkGender =  group.getCheckedRadioButtonId();
        String gender ;
        if(checkGender != -1){
            RadioButton gender2 = findViewById(checkGender);
            gender = gender2.getText().toString();
        }else{
            gender = "";
        }
        EditText number1 = findViewById(R.id.EnterNumber);
        String studentid = number1.getText().toString();
        EditText firstname = findViewById(R.id.EnterLName);
        String firstname1 = firstname.getText().toString();
        Spinner spinner = findViewById(R.id.spinner);
        String faculty = spinner.getSelectedItem().toString();
        Integer length = studentid.length();
        boolean checkFname = firstname1.isEmpty();
        boolean checkDivision = faculty.length()>0;
        if (length == 8 && !checkFname && checkGender != -1  && checkDivision) {
            String filename = "output.txt";
            String info = studentid+","+firstname1+","+gender+","+faculty+"\n";
            FileOutputStream outputStream;

            try {
                // opening file to be written
                outputStream = openFileOutput(filename, this.MODE_APPEND);
                outputStream.write(info.getBytes());
                outputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }else{
            if(length != 8){
                Toast.makeText(getApplicationContext(), "Invalid Student Number", Toast.LENGTH_SHORT).show();
            }
            if(checkFname){
                Toast.makeText(getApplicationContext(), "Invalid Name", Toast.LENGTH_SHORT).show();
            }
            if(checkGender == -1){
                Toast.makeText(getApplicationContext(), "Select Gender", Toast.LENGTH_SHORT).show();
            }
            if(!checkDivision){
                Toast.makeText(getApplicationContext(), "Select Faculty", Toast.LENGTH_SHORT).show();
            }


        }

        }

    }

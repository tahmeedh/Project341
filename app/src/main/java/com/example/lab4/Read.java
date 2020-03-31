package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Read extends AppCompatActivity {
    public int current = 0;
    public int count = 0;
    public String[] allData = new String[50];
    public TextView setNum;
    public TextView setName;
    public TextView setDiv;
    public TextView setGen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        setNum = findViewById(R.id.stuid);
        setName = findViewById(R.id.name);
        setDiv = findViewById(R.id.div);
        setGen = findViewById(R.id.gender);
        String file = "output.txt";
        String line;
        try{
            FileInputStream input = openFileInput(file);
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader buffer = new BufferedReader(reader);

            while((line = buffer.readLine()) != null){
                allData[count] = line;
                count++;


            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] student = allData[0].split(",");
        setNum.setText("Student ID: " + student[0]);
        setName.setText("Name: " + student[1]);
        setDiv.setText("Faculty: " + student[2]);
        setGen.setText("Gender: " + student[3]);


    }

    public void Previous(View view) {
        if(current == 0){
            current = count-1;
        }else{
            current--;
        }
        String[] student = allData[current].split(",");
        setNum.setText("Student ID: " + student[0]);
        setName.setText("Name: " + student[1]);
        setDiv.setText("Faculty: " + student[2]);
        setGen.setText("Gender: " + student[3]);

    }

    public void Next(View view) {
        if(current == count-1){
            current = 0;
        }else{
            current++;
        }
        String[] student = allData[current].split(",");
        setNum.setText("Student ID: " + student[0]);
        setName.setText("Name: " + student[1]);
        setDiv.setText("Faculty: " + student[2]);
        setGen.setText("Gender: " + student[3]);



    }

    public void Submit(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}

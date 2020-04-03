package com.example.lab4;

import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class inputData_Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata_client);

        LinearLayout linLayout = findViewById(R.id.innerLayout);
        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,100,0,0);
        for(int i =1; i<10;i++){

            Toast.makeText(getApplicationContext(), "Ran this part: "+i, Toast.LENGTH_LONG).show();
            LinearLayout horLayout = new LinearLayout(this);
            linLayout.addView(horLayout,params);
            TextView textView = new TextView(this);
            textView.setText("Exercise: "+i+"   ");
            textView.setTextSize(32);
            horLayout.addView(textView);

            textView = new TextView(this);
            textView.setText("Reps: "+i+"           ");
            textView.setTextSize(20);
            horLayout.addView(textView);

            Button button = new Button(this);
            button.setText("Start");
            horLayout.addView(button);
        }
    }
}

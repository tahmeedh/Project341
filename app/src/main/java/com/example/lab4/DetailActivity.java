package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView work;
    String t;
    String w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.title);
        work = findViewById(R.id.work);
        Bundle extra = getIntent().getExtras();
        if(extra != null){
             t = extra.getString("title");
             w  = extra.getString("work");

            title.setText(t);
            work.setText(w);
        }


    }
    public void GoScreen(View view){
        Intent intent = new Intent(this, inputData_Trainer.class);
        intent.putExtra("title", t);
        intent.putExtra("work", w);
        startActivity(intent);
    }
}

package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.title);
        work = findViewById(R.id.work);
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            String t = extra.getString("title");
            String w  = extra.getString("work");

            title.setText(t);
            work.setText(w);
        }
    }
}

package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FitnessTracker extends AppCompatActivity implements AdapterView.OnItemClickListener {

  private ListView lv;
  private ArrayList<Workout> worklist;
  private ArrayList<String> titleList;
  private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_tracker);
        lv =  findViewById(R.id.lv);
        worklist = DataFilter.loadWork(this);
        titleList = new ArrayList<>();
        for(int i = 0; i<worklist.size();i++){
            String str = worklist.get(i).getTitle();
            titleList.add(str);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  titleList);
        lv.setAdapter((ListAdapter) adapter);
        lv.setOnItemClickListener(this);


            }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        Intent intent;
        intent = new Intent(this, DetailActivity.class);
        String title = worklist.get(pos).getTitle();
        String work = worklist.get(pos).getWod();
        intent.putExtra("title", title);
        intent.putExtra("work", work);
        startActivity(intent);

    }
}


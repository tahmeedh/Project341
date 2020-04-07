package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        createNotificationChannel();

        logoutBtn = findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });


//        notificationButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//                    public void onClick(View v){
//
////                Toast.makeText(this, "Reminder!", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(MainActivity.this, notificationbutton.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
//
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//                long startTime = System.currentTimeMillis();
//                long fiveSecondsInMillisecond = 5 * 10000;
//
//                alarmManager.set(AlarmManager.RTC_WAKEUP, startTime + fiveSecondsInMillisecond, pendingIntent);
//            }
//        });

//        notificationButton.setOnClickListener(v -> {
//            Toast.makeText(this,"Reminder!",Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(MainActivity.this,notificationbutton.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
//
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//            long startTime = System.currentTimeMillis();
//            long fiveSecondsInMillisecond = 5 * 10000;
//
//            alarmManager.set(AlarmManager.RTC_WAKEUP, startTime + fiveSecondsInMillisecond,pendingIntent);
//        });
//
 }

//    private void createNotificationChannel(){
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            CharSequence name = "Notification";
//            String description = "Include all notificatoins";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//
//
//            NotificationChannel notificationChannel= new NotificationChannel("my_noti", name, importance);
//            notificationChannel.setDescription(description);
//        }
//
//    }

    }



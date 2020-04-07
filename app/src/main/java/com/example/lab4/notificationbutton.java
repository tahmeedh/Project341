package com.example.lab4;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.Intent;
import java.text.DateFormat;
import java.util.Calendar;


//Whole Class for notification
public class notificationbutton extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private EditText editTextTitle;
    private EditText editTextMessage;
    private Button button_Channel1;
    private Button button_Channel2;
    private NotificationHelper mNotificationHelper;

    //alarm
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationbutton);

        editTextTitle = findViewById(R.id.edittext_title);
        editTextMessage = findViewById(R.id.edittext_message);
        button_Channel1 = findViewById(R.id.button_channel1);
        button_Channel2 = findViewById(R.id.button_channel2);
        mNotificationHelper = new NotificationHelper(this);


        button_Channel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder = mNotificationHelper.getChannel1Notification(editTextTitle.getText().toString(), editTextMessage.getText().toString());
                mNotificationHelper.getManager().notify(1, builder.build());

            }
        });

        button_Channel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = mNotificationHelper.getChannel2Notification(editTextTitle.getText().toString(), editTextMessage.getText().toString());
                mNotificationHelper.getManager().notify(2, builder.build());
            }
        });



        //Create Alarm

        mTextView = findViewById(R.id.textView);
        Button time_Picker = findViewById(R.id.button_alarm1);
        time_Picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        //Cancel Alarm

        Button cancel_Alarm = findViewById(R.id.button_alarm2);
        cancel_Alarm.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                cancelAlarm();
            }
        });

    }
        //Create Alarm setting


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hourOfDay);
        cal.set(Calendar.MINUTE,minute);
        cal.set(Calendar.SECOND,0);

        updateTimeText(cal);
        setAlarm(cal);
    }

    private void updateTimeText(Calendar cal){
        String timeText = "Alarm set for time: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(cal.getTime());
        mTextView.setText(timeText);
    }

    private void setAlarm(Calendar cal){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        //Turn off alarm notifiation if it was past time
        if(cal.before(Calendar.getInstance())){
            cal.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        mTextView.setText("Alarm canceled");
    }
}








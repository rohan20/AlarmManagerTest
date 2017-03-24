package com.example.rohantaneja.alarmmanagertest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker alarmTimePicker;
    TextView alarmStatusTextView;
    ToggleButton alarmToggleButton;

    PendingIntent alarmPendingIntent;
    static AlarmManager alarmManager;
    Intent alarmIntent;

    int STATUS_CODE = 1;

    AlarmManagerBroadcastReceiver alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker) findViewById(R.id.time_picker);
        alarmStatusTextView = (TextView) findViewById(R.id.alarm_status_text_view);
        alarmToggleButton = (ToggleButton) findViewById(R.id.toggle_button);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmToggle();
            }
        });

        alarm = new AlarmManagerBroadcastReceiver();

    }

    private void alarmToggle() {
        if (alarmToggleButton.isChecked()) {
            //set alarm
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
            calendar.set(calendar.MINUTE, alarmTimePicker.getMinute());

            alarm.setAlarm(this, calendar.getTimeInMillis());

            //update text view
            alarmStatusTextView.setText("Alarm set for - " + alarmTimePicker.getHour() + ":" + alarmTimePicker.getMinute());

            //hide time picker
            alarmTimePicker.setVisibility(View.GONE);
        } else {
            if (alarm != null)
                alarm.cancelAlarm(this);

            alarmStatusTextView.setText("Alarm not set");
            alarmTimePicker.setVisibility(View.VISIBLE);
        }
    }
}

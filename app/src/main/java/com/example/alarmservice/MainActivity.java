package com.example.alarmservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtSeconds;
    Button btnStartTimer;
    Calendar calendar = Calendar.getInstance();
    private final TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourOfDay, minute) -> {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        long timerMillis = calendar.getTimeInMillis();
        long currentMillis = System.currentTimeMillis();
        if (timerMillis < currentMillis) {
            long dayMillis = 24 * 60 * 60 * 1000;
            timerMillis += dayMillis;

            Intent intent = new Intent(MainActivity.this, Alarm.class);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, timerMillis,
                    PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0));
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSeconds = findViewById(R.id.edtSeconds);
        btnStartTimer = findViewById(R.id.btnStartTimer);
        btnStartTimer.setOnClickListener(view -> new TimePickerDialog(MainActivity.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true)
                .show());
    }
}
package com.example.alarmservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {
    /*
        Метод срабатывает при окончании работы таймера
        Выводит сообщение и срабатывает вибраиця (1000 = 1 секунда)
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Таймер закончил работу!", Toast.LENGTH_LONG).show();
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }
}

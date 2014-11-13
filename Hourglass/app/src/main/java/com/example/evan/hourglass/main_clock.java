package com.example.evan.hourglass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class main_clock extends Activity {

    private long _currentTime;
    private long _second;
    private long _minute;
    private long _hour;
    private Time now;

    private long offset = -5;
    private int _counter = 0;
    private boolean ampm = false;

    private Timer _timerCount =  new Timer();
    private Handler _uiHandler = new Handler();

    private String timeToString(long millisecond, long offset, boolean ampm){
        _second = (millisecond / 1000) % 60;
        _minute = (millisecond / (1000 * 60)) % 60;
        _hour = (millisecond / (1000 * 60 * 60)) % 24;

        _hour = _hour + offset;

        if (ampm) {
            if (_hour < 0)
                _hour += 12;
            return String.format("%02d:%02d:%02d", _hour % 12, _minute, _second);
        }
        else{
            if (_hour < 0)
                _hour += 24;
            return String.format("%02d:%02d:%02d", _hour % 24, _minute, _second);
        }
    }

    public void buttonOnClick(View view){
        Intent act = null;
        if(view.getId() == R.id.buttonSetting)
            act = new Intent(view.getContext(), settings.class);
        if(view.getId() == R.id.buttonClockTwo)
            act = new Intent(view.getContext(), MainActivity.class);

        if(act != null)
            startActivity(act);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences settings = getSharedPreferences("settings", 0);
        setContentView(R.layout.activity_main_clock);

        ampm = settings.getBoolean("twentyFour", true);

        now = new Time();
        now.setToNow();
        _currentTime = now.toMillis(false);
        // Gain current time in milliseconds

        TextView textView = (TextView)findViewById(R.id.textViewClockOne);
        textView.setText(timeToString(_currentTime, offset, true));

        _timerCount.schedule(new TimerTask() {
            @Override
            public void run() {
                _counter++;
                _currentTime+=1000;

                // Use the handler to marshal/invoke the Runnable code on the UI thread
                _uiHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        TextView textView = (TextView)findViewById(R.id.textViewClockOne);
                        textView.setText(timeToString(_currentTime, offset, ampm));
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_clock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

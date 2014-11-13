package com.example.evan.hourglass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
    private Handler _uiHandler = new Handler();
    private Timer _timer = new Timer();

    public void buttonOnClick(View view) {
        Intent act = null;
        if(view.getId() == R.id.buttonSetting)
            act = new Intent(view.getContext(), settings.class);
        if(view.getId() == R.id.buttonClockOne)
            act = new Intent(view.getContext(), main_clock.class);

        if(act != null)
            startActivity(act);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences settings = getPreferences(0);

        setContentView(R.layout.activity_main);

        _timer.schedule(new TimerTask() {
            @Override
            public void run() {

                _uiHandler.post(new Runnable(){
                    @Override
                    public void run(){

                        hourglass h = (hourglass)findViewById(R.id.viewClockTwo);
                        h.setSettings(settings.getBoolean("twentyFour", true));
                        h.invalidate();
                    }
                });

            }
        }, 0, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

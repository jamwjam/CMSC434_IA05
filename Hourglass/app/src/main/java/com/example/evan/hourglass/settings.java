package com.example.evan.hourglass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.ToggleButton;


public class settings extends Activity {
    SharedPreferences settings;
    public void switchOnClick(View view) {
        boolean on = ((Switch) view).isChecked();
        SharedPreferences.Editor editMode = settings.edit();
        if(on){
            editMode.putBoolean("twentyFour", true);


        } else {
            editMode.putBoolean("twentyFour", false);


        }
        editMode.commit();


    }

    public void buttonOnClick(View view) {
        Intent act = null;
        if(view.getId() == R.id.buttonClockOne)
            act = new Intent(view.getContext(), main_clock.class);
        if(view.getId() == R.id.buttonClockTwo)
            act = new Intent(view.getContext(), MainActivity.class);

        if(act != null) {
            System.out.println("GO!");
            startActivity(act);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences("settings", 0);


        setContentView(R.layout.activity_settings);

        boolean is12Hour = !settings.getBoolean("twentyFour", false);
        Switch n = (Switch) findViewById(R.id.switch_timeMode);
        n.setChecked(!is12Hour);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
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

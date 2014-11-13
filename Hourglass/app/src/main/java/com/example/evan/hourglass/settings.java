package com.example.evan.hourglass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.ToggleButton;


public class settings extends Activity {

    public void switchOnClick(View view) {
        boolean on = ((Switch) view).isChecked();

        if(on){
            //Switch is set to ON
            System.out.println("on");
        } else {
            //Switch is set to OFF
            System.out.println("off");
        }

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
        setContentView(R.layout.activity_settings);
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

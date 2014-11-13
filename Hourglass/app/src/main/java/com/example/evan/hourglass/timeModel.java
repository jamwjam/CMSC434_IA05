package com.example.evan.hourglass;
import android.os.Handler;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Evan on 11/13/2014.
 */
public class timeModel {
    public  long _millis, _second, _minute, _hour;
    private Timer _timer =  new Timer();
    private Handler _uiHandler = new Handler();

    public timeModel(){
        Time t = new Time();
        t.setToNow();

         _millis = t.toMillis(false);
         setFromMillis();

    }
    private void setFromMillis(){
        _second = (_millis / 1000) % 60;

        _minute = (_millis / (1000 * 60)) % 60;
        _hour = (_millis / (1000 * 60 * 60)) % 24 - 5;
        return;
    }
    public void increment(){
        _millis +=1000;
        setFromMillis();
    }

}

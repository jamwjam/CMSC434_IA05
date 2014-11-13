package com.example.evan.hourglass;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.text.format.Time;



public class hourglass extends View {
    private timeModel _timer;
    private Paint _brush = new Paint();
    public hourglass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Time t = new Time();
        t.setToNow();
        _timer = new timeModel();

    }


    public hourglass(Context context) {
        super(context);
        Time t = new Time();
        t.setToNow();
        _timer = new timeModel();
    }

    public hourglass(Context context, AttributeSet attrs) {
        super(context, attrs);
        Time t = new Time();
        t.setToNow();
        _timer = new timeModel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        _timer.increment();
        topLevelGlass bigGlass = new topLevelGlass(canvas, 10, 30, 760, 1130, 0, false);
        topLevelGlass[] smallGlassTop = new topLevelGlass[24];
        topLevelGlass[] smallGlassBottom = new topLevelGlass[24];
        int n = 10;
        long bottomGlasses = _timer._hour;
        long topGlasses = 24- bottomGlasses;
        int counter = 0;


        //create and paint seconds counter
        int secondsHeight = (int)_timer._second * 17;
        canvas.drawLine(800, 1130, 800, 1130-secondsHeight, _brush);

        //create and paint top glasses
        for (int d = 0; d<4; d++) {
            if (counter == topGlasses)
                break;


            if (d==0)
                n = 10;
            if (d==1)
                n = 7;
            if (d==2)
                n=5;
            if (d==3){
                n =2;
            }
            int curYOffset = 115 * d;

            for (int c = 0; c < n; c++) {

                if (counter == topGlasses)
                    break;

                int curXOffset = 60 * c + 80*d;
                if (d == 2)
                    curXOffset -= 5;
                int  fillState = 0;
                if (counter == topGlasses - 1){
                    fillState = (int)_timer._minute;
                }
                smallGlassTop[c] = new topLevelGlass(canvas, 90 + curXOffset, 45 + curYOffset, 130 + curXOffset, 135+ curYOffset, fillState, false);
                smallGlassTop[c].render();


                counter ++;
            }

        }
        counter = 0;
        //create and render bottom glasses
        for (int d = 0; d<4; d++) {
            if (counter == bottomGlasses)
                break;
            if (d==0)
                n = 10;
            if (d==1)
                n = 7;
            if (d==2)
                n=5;
            if (d==3){
                n = 2;
            }
            int curYOffset = 115 * d;
            for (int c = 0; c < n; c++) {
                if (counter == bottomGlasses)
                    break;

                int curXOffset = 60 * c + 80*d;
                if (d == 2)
                    curXOffset -= 5;
                smallGlassBottom[c] = new topLevelGlass(canvas, 90 + curXOffset, 1115 - curYOffset, 130 + curXOffset, 1025 - curYOffset, 0, true);
                smallGlassBottom[c].render();


                counter ++;

            }

        }
        bigGlass.render();


    }
}

package com.example.evan.tes3;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class hourglass extends View {
    private Paint _paintBrush = new Paint();

    public hourglass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public hourglass(Context context) {
        super(context);

    }

    public hourglass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        topLevelGlass bigGlass = new topLevelGlass(canvas, 10, 30, 760, 1130, 0, false);
        topLevelGlass[] smallGlassTop = new topLevelGlass[24];
        topLevelGlass[] smallGlassBottom = new topLevelGlass[24];
        int n = 10;
        int topGlasses = 20;
        int bottomGlasses = 24- topGlasses;
        int counter = 0;

        //create and paint top glasses
        for (int d = 0; d<4; d++) {
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

                int curXOffset = 60 * c + 80*d;
                if (d == 2)
                    curXOffset -= 5;
                smallGlassTop[c] = new topLevelGlass(canvas, 90 + curXOffset, 45 + curYOffset, 130 + curXOffset, 135+ curYOffset, 60, false);
                smallGlassTop[c].render();


                counter ++;
                if (counter == topGlasses)
                    break;
            }
            if (counter == topGlasses)
                break;

        }
        counter = 0;
        //create and render bottom glasses
        for (int d = 0; d<4; d++) {
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

                int curXOffset = 60 * c + 80*d;
                if (d == 2)
                    curXOffset -= 5;
                smallGlassBottom[c] = new topLevelGlass(canvas, 90 + curXOffset, 1115 - curYOffset, 130 + curXOffset, 1025 - curYOffset, 60, true);
                smallGlassBottom[c].render();


                counter ++;
                if (counter == bottomGlasses)
                    break;
            }
            if (counter == bottomGlasses)
                break;

        }
        bigGlass.render();


    }
}

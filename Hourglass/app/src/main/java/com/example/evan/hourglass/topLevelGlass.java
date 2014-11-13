package com.example.evan.hourglass;

import android.graphics.Canvas;
import android.graphics.Paint;

public class topLevelGlass {
    int startX, startY, stopX, stopY, fillState, bottomTransform;
    boolean isTop;
    Paint brush = new Paint();
    Canvas canvas;
    public topLevelGlass(Canvas canvas, int startX, int startY, int stopX, int stopY, int fillState, boolean isBottom) {
       this.startX = startX;
       this.startY = startY;
       this.stopX = stopX;
       this.stopY = stopY;
       this.fillState = fillState;
        this.canvas = canvas;
        if (isBottom) {
            this.bottomTransform = -1;
        } else {
            this.bottomTransform = 1;
        }

    }
    public void render(){

        canvas.drawLine(startX,startY,stopX,startY, brush);
        canvas.drawLine(startX,startY, stopX,stopY, brush);
        canvas.drawLine(startX,stopY,stopX,stopY, brush);
        canvas.drawLine(stopX,startY,startX,stopY, brush);
        if (stopX - startX  > 500){
            return;
        }
        int counter = 0;
        int topSand = 10;
        int bottomSand = 60 - topSand;
        int n = 15;


        //draw
        for (int row = 0; row < 6; row ++  ) {
            int curYOffset = 5 * (row);
            if (row == 0)
                n = 16;
            if (row == 1)
                n = 14;
            if (row == 2)
                n = 12;
            if (row == 3)
                n =10;
            if (row == 4)
                n = 7;
            if (row == 5)
                n = 3;
            for (int c = 0; c < n; c ++) {
                int curXOffset =  2 * row + 2*c + 2;
                canvas.drawLine(startX + curXOffset+ 3, startY + bottomTransform * (curYOffset +3), startX + curXOffset + 3, startY + bottomTransform * (curYOffset+6), brush);

                canvas.drawLine(startX + curXOffset + 4, startY +  bottomTransform * (curYOffset +3), startX + curXOffset + 4, startY + bottomTransform * (curYOffset+6), brush);
                counter ++;
                if (counter == topSand)
                    break;
            }
            if (counter == topSand){
                break;
            }
        }


        //paint the bottom of the top glass, will only happen for the primary glass
        counter = 0;
        for (int row = 0; row < 6; row ++  ) {
            int curYOffset = 5 * (row);
            if (row == 0)
                n = 16;
            if (row == 1)
                n = 14;
            if (row == 2)
                n = 12;
            if (row == 3)
                n =10;
            if (row == 4)
                n = 7;
            if (row == 5)
                n = 3;
            for (int c = 0; c < n; c ++) {
                int curXOffset =  2 * row + 2*c + 2;
                canvas.drawLine(startX + curXOffset+ 3, stopY -  bottomTransform * (curYOffset +3), startX + curXOffset + 3, stopY - bottomTransform * (curYOffset+6), brush);
                canvas.drawLine(startX + curXOffset+ 4, stopY -  bottomTransform * (curYOffset +3), startX + curXOffset + 4, stopY - bottomTransform * (curYOffset+6), brush);
                counter ++;
            if (counter == bottomSand)
                break;
            }
            if (counter == bottomSand)
                break;
        }
    }
}

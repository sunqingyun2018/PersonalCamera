package com.example.functionone;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by QingYun on 2017/9/24.
 */

public class FaceFrame  {

    private long startTime= -1;

    private static final int DEFAULT_OFFSET_VALUE=2000;

    private static int sStartOffset=DEFAULT_OFFSET_VALUE;

    private static final int sStartDuring=500;

    private boolean finished;

    private Rect curRect;

    private boolean isDisplay=false;

    public FaceFrame(Rect rect) {

        curRect= rect;

    }

    public FaceFrame(Rect curRect, boolean isDisplay) {

        this.curRect= curRect;

        this.isDisplay= isDisplay;

    }

    public boolean isFinished() {

        return finished;

    }

    public void updateRect(Rect rect) {

        if(!finished) {

            curRect= rect;

        }

    }

    public void draw(Canvas canvas, Drawable faceFrame) {

        sStartOffset=  Integer.MAX_VALUE;

        if(startTime<=0) {

            startTime= System.currentTimeMillis();

        }

        long curTime = System.currentTimeMillis();

        float t = ((curTime -startTime-sStartOffset) / (float)sStartDuring);//t is from 0 to 1 linearly.

        t = clamp(t,0,1);

        int alpha = (int) (255* (1- t));

        finished= (alpha ==0);

        faceFrame.setAlpha(alpha);

        faceFrame.setBounds(curRect);

        faceFrame.draw(canvas);

    }

    public float  clamp(float fValue, float fMin, float fMax)
    {
        if (fValue > fMax )   {
            return fMax;
        }
        else if ( fValue < fMin) {
            return fMin;
        }
        else return fValue;
    }



}

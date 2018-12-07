package com.example.functionone;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;


import com.camera2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by QingYun on 2017/9/24.
 */

public class FaceIndicatorView extends CustomView {
    private String TAG=FaceIndicatorView.class.getSimpleName();
    private Map<Integer,FaceFrame> faceFrames =new ConcurrentHashMap<Integer,FaceFrame>();

    private final Drawable faceFrameDrawable;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FaceIndicatorView(Context context) {
        super(context);
        faceFrameDrawable= context.getDrawable(R.drawable.face);

    }

    @Override
    public void setVisible(boolean visible) {

        super.setVisible(visible);

        if(!visible) {
            faceFrames.clear();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        boolean allFramesDone =true;

        for(FaceFrame face :faceFrames.values()) {

            if(!face.isFinished()) {

                allFramesDone =false;
                face.draw(canvas,faceFrameDrawable);

            }

        }
        if(!allFramesDone) {
            Log.i(TAG," invalidate");
            invalidate();
        }
    }

    public void updateFaces(Map<Integer,Rect> faceRects) {

        boolean hasFaceRectsChanged =false;

      //  Remove faces which have gone.

        List <Integer>faceIdsGone =new ArrayList<Integer>();

        for(Map.Entry faceFrameEntry :faceFrames.entrySet()) {

            if(!faceRects.containsKey(faceFrameEntry.getKey())) {

                faceIdsGone.add((Integer) faceFrameEntry.getKey());

            }

        }

        for(Integer faceId : faceIdsGone) {

            faceFrames.remove(faceId);

            hasFaceRectsChanged =true;

        }

        //  Update origin faces rect or add new faces.

        //1 .

        for(Map.Entry faceRectEntry : faceRects.entrySet()) {

            FaceFrame faceFrame =faceFrames.get(faceRectEntry.getKey());

            if(faceFrame !=null) {

                if(!faceFrame.isFinished()) {

                    faceFrame.updateRect((Rect) faceRectEntry.getValue());

                    hasFaceRectsChanged =true;

                }

            }else{


                faceFrames.put((Integer) faceRectEntry.getKey(), new FaceFrame((Rect) faceRectEntry.getValue()));

                hasFaceRectsChanged =true;

            }

        }

        if(hasFaceRectsChanged) {

            postInvalidate();

        }

    }


    public int getPriority() {

        return 1;

    }

}

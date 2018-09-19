package component;

import android.app.Activity;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.Face;
import android.media.ImageReader;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Surface;

import com.camera2.CameraActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.CameraUtils;

public class CaptureFlow  extends BaseFlow{

    private CaptureRequest.Builder mCaptureRequestBuilder;



    public CaptureFlow(CameraDevice cameraDevice) {
        super(cameraDevice);
    }


    public CaptureRequest.Builder createCaptureRequest(Surface surface,boolean mFlashSupported) throws CameraAccessException {

        // This is the CaptureRequest.Builder that we use to take a picture.
        mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
        mCaptureRequestBuilder.addTarget(surface);

        // Auto focus should be continuous for camera preview.
        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);


        // Flash is automatically enabled when necessary.
        CameraUtils.setAutoFlash(mFlashSupported,mCaptureRequestBuilder);


        return mCaptureRequestBuilder;
    }





    public void capture(CaptureRequest.Builder mCaptureRequestBuilder,CameraCaptureSession mCaptureSession,CameraCaptureSession.CaptureCallback mCaptureCallback,
                        Handler mBackgroundHandler) throws CameraAccessException {

        mCaptureSession.capture(mCaptureRequestBuilder.build(), mCaptureCallback,
                mBackgroundHandler);
    }


    public CaptureRequest getCaptureRequest(){
        return mCaptureRequestBuilder.build();
    }

    public CaptureRequest.Builder getCaptureRequestBuilder(){
        return mCaptureRequestBuilder;
    }


}

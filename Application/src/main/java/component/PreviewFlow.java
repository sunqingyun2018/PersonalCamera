package component;

import android.app.Activity;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Surface;

import com.camera2.CameraActivity;

import java.util.Arrays;


import util.CameraUtils;

public class PreviewFlow extends BaseFlow {

    /**
     * {@link CaptureRequest.Builder} for the camera preview
     */
   private CaptureRequest.Builder mPreviewRequestBuilder;

    /**
     * {@link CaptureRequest} generated by {@link #mPreviewRequestBuilder}
     */
    private CaptureRequest mPreviewRequest;


    public PreviewFlow(CameraDevice cameraDevice) {
        super(cameraDevice);
    }


    public CaptureRequest.Builder createCaptureRequest(Surface surface,boolean mFlashSupported) throws CameraAccessException {
        mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        mPreviewRequestBuilder.addTarget(surface);

        // Auto focus should be continuous for camera preview.
        mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

        mPreviewRequestBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE, CameraMetadata.STATISTICS_FACE_DETECT_MODE_FULL);

        // Flash is automatically enabled when necessary.
        CameraUtils.setAutoFlash(mFlashSupported,mPreviewRequestBuilder);



        return mPreviewRequestBuilder;
    }




     public void setRepeatingRequest(CameraCaptureSession mCaptureSession,CameraCaptureSession.CaptureCallback mCaptureCallback,
                                     Handler mBackgroundHandler) throws CameraAccessException {
         mCaptureSession.setRepeatingRequest(getPreViewCaptureRequest(), mCaptureCallback, mBackgroundHandler);
     }



    public CaptureRequest getPreViewCaptureRequest(){
            return mPreviewRequestBuilder.build();
    }

    public CaptureRequest.Builder getPreviewRequestBuilder(){
        return mPreviewRequestBuilder;
    }

}

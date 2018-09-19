package util;

import android.app.Activity;
import android.hardware.camera2.CaptureRequest;
import android.widget.Toast;

public class CameraUtils {

    public static void setAutoFlash(boolean mFlashSupported,CaptureRequest.Builder requestBuilder) {
        if (mFlashSupported) {
            requestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                    CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
        }
    }
    /**
     * Shows a {@link Toast} on the UI thread.
     *
     * @param text The message to show
     */
    public static void showToast(Activity activity,final String text) {

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

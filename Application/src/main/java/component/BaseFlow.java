package component;

import android.hardware.camera2.CameraDevice;

public class BaseFlow {
    public CameraDevice mCameraDevice;
    public  BaseFlow(CameraDevice cameraDevice ){
        this.mCameraDevice=cameraDevice;

    }
}

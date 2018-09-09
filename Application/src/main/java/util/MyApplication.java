package util;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by xupengpai on 2017/5/26.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    /** 主线程ID */
    private static int mMainThreadId = -1;
    /** 主线程ID */
    private static Thread mMainThread;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;
    /** 主线程Looper */
    private static Looper mMainLooper;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
    }

    public static MyApplication getContext(){
        return myApplication;
    }
    /**
     * 获取主线程ID
     *
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     *
     */
    public static Thread getMainThread() {
        return mMainThread;
    }
}

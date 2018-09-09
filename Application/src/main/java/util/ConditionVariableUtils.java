package util;

import android.os.ConditionVariable;
import android.os.Looper;
import android.view.View;

public class ConditionVariableUtils {

    private static final int TIMEOUT_WAIT_UI = 1000;

    public void method(View view){

        if (Looper.myLooper() != Looper.getMainLooper()) {

            final ConditionVariable completed = new ConditionVariable(); // 构造一个条件变量

           runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        doSomeThingInUiThread(); // 将期望在另外线程做的事post出去
                    } finally { // finally很重要，防止运行时异常远跳转将ConditionVariable忘了open
                        completed.open(); // 事情办完了，notify到另外线程
                    }
                }
            });
            completed.block(TIMEOUT_WAIT_UI); // 等着另外线程做的事完成，同时支持设置超时
        } else {
            doSomeThingInUiThread();
        }
    }

    public void runOnUiThread(Runnable runnable) {
        UIUtils.runInMainThread(runnable);
    }

    private void doSomeThingInUiThread() {

    }




}

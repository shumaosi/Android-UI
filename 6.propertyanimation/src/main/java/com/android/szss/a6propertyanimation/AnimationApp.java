package com.android.szss.a6propertyanimation;

import android.app.Application;
import android.os.Debug;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * @Description:
 * @author： 鼠茂斯
 * @date： 2018/2/27
 */

public class AnimationApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        File file = new File(Environment.getExternalStorageDirectory(),"tracefile");
//        Log.i("ww", "oncreate: " + file.getAbsolutePath());
//        Debug.startMethodTracing(file.getAbsolutePath());
//        Debug.stopMethodTracing();
    }
}

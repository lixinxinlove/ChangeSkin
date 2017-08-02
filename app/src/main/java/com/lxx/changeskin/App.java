package com.lxx.changeskin;

import android.app.Application;

import cn.feng.skin.manager.loader.SkinManager;

/**
 * Created by android on 2017/8/2.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }
}

package com.idocnet.inos.app;

import android.app.Application;

import com.idocnet.inos.di.component.ApplicationComponent;
import com.idocnet.inos.di.component.DaggerApplicationComponent;
import com.idocnet.inos.di.module.ApplicationModule;

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}

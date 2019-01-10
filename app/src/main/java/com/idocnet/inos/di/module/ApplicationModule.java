package com.idocnet.inos.di.module;

import com.idocnet.inos.app.MyApplication;
import com.idocnet.inos.utils.Constants;
import com.idocnet.inos.utils.TinyDB;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private TinyDB tinyDB;

    public ApplicationModule(MyApplication application){
        this.tinyDB = new TinyDB(application);
    }

    @Singleton
    @Provides
    public TinyDB tinyDB(){
        return tinyDB;
    }

    @Singleton
    @Provides
    @Named("ui_thread")
    public Scheduler uiThread(){
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    @Named("executor_thread")
    public Scheduler executorThread(){
        return Schedulers.io();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}

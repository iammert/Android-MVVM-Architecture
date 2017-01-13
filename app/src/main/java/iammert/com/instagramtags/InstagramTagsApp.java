package iammert.com.instagramtags;

import android.app.Application;

import iammert.com.instagramtags.di.app.AppComponent;
import iammert.com.instagramtags.di.app.AppModule;
import iammert.com.instagramtags.di.app.DaggerAppComponent;
import iammert.com.instagramtags.di.app.NetworkModule;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class InstagramTagsApp extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

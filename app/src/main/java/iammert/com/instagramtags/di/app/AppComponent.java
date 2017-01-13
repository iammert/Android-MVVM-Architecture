package iammert.com.instagramtags.di.app;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import iammert.com.instagramtags.model.api.ApiSource;
import iammert.com.instagramtags.util.RxBus;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    ApiSource apiSource();
    SharedPreferences sharedPreferences();
    Gson gson();
    RxBus bus();
}

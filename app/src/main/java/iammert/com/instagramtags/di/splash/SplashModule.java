package iammert.com.instagramtags.di.splash;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import iammert.com.instagramtags.di.Activity;
import iammert.com.instagramtags.domain.splash.SplashUsecase;
import iammert.com.instagramtags.domain.splash.SplashUsecaseImpl;
import iammert.com.instagramtags.model.preferences.PreferencesHelper;
import iammert.com.instagramtags.model.preferences.PreferencesHelperImpl;
import iammert.com.instagramtags.viewmodel.splash.SplashViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Module
public class SplashModule {

    SplashViewModel.SplashListener listener;

    public SplashModule(SplashViewModel.SplashListener listener) {
        this.listener = listener;
    }

    @Provides
    @Activity
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences sharedPreferences, Gson gson){
        return new PreferencesHelperImpl<>(sharedPreferences, gson);
    }

    @Provides
    @Activity
    SplashUsecase provideSplashUsecase(PreferencesHelper<String> tokenPreferencesHelper){
        return new SplashUsecaseImpl(tokenPreferencesHelper);
    }

    @Provides
    @Activity
    SplashViewModel provideSplashViewModel(SplashUsecase splashUsecase){
        return new SplashViewModel(splashUsecase, listener);
    }
}

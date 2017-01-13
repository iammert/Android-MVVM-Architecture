package iammert.com.instagramtags.di.splash;

import dagger.Component;
import iammert.com.instagramtags.di.Activity;
import iammert.com.instagramtags.di.app.AppComponent;
import iammert.com.instagramtags.view.splash.SplashActivity;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Activity
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}

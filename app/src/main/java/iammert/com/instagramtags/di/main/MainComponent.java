package iammert.com.instagramtags.di.main;

import dagger.Component;
import iammert.com.instagramtags.di.Activity;
import iammert.com.instagramtags.di.app.AppComponent;
import iammert.com.instagramtags.view.main.MainActivity;

/**
 * Created by mertsimsek on 13/01/17.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}

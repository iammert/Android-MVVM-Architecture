package iammert.com.instagramtags.di.medialist;

import dagger.Component;
import iammert.com.instagramtags.di.Fragment;
import iammert.com.instagramtags.di.app.AppComponent;
import iammert.com.instagramtags.view.medialist.MediaListFragment;

/**
 * Created by mertsimsek on 14/01/17.
 */
@Fragment
@Component(dependencies = AppComponent.class, modules = MediaListModule.class)
public interface MediaListComponent {
    void inject(MediaListFragment fragment);
}

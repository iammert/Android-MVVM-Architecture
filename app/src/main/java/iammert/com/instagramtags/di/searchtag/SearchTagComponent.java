package iammert.com.instagramtags.di.searchtag;

import dagger.Component;
import iammert.com.instagramtags.di.Fragment;
import iammert.com.instagramtags.di.app.AppComponent;
import iammert.com.instagramtags.view.searchtag.SearchTagFragment;

/**
 * Created by mertsimsek on 13/01/17.
 */
@Fragment
@Component(dependencies = AppComponent.class, modules = SearchTagModule.class)
public interface SearchTagComponent {
    void inject(SearchTagFragment searchTagFragment);
}

package iammert.com.instagramtags.di.medialist;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import iammert.com.instagramtags.di.Fragment;
import iammert.com.instagramtags.domain.medialist.MediaListUsecase;
import iammert.com.instagramtags.domain.medialist.MediaListUsecaseImpl;
import iammert.com.instagramtags.model.api.ApiSource;
import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.model.preferences.PreferencesHelper;
import iammert.com.instagramtags.model.preferences.PreferencesHelperImpl;
import iammert.com.instagramtags.view.medialist.MediaListAdapter;
import iammert.com.instagramtags.viewmodel.medialist.MediaListViewModel;

/**
 * Created by mertsimsek on 14/01/17.
 */
@Module
public class MediaListModule {

    MediaListViewModel.MediaListListener listener;
    Tag tag;

    public MediaListModule(MediaListViewModel.MediaListListener listener, Tag tag) {
        this.listener = listener;
        this.tag = tag;
    }

    @Provides
    @Fragment
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences preferences, Gson gson){
        return new PreferencesHelperImpl<>(preferences, gson);
    }

    @Provides
    @Fragment
    MediaListUsecase provideMediaListUsecase(ApiSource apiSource, PreferencesHelper<String> tokenPreferencesHelper){
        return new MediaListUsecaseImpl(apiSource, tokenPreferencesHelper);
    }

    @Provides
    @Fragment
    MediaListViewModel provideMediaListViewModel(MediaListUsecase mediaListUsecase){
        return new MediaListViewModel(mediaListUsecase, listener, tag);
    }

    @Provides
    @Fragment
    MediaListAdapter provideMediaListAdapter(){
        return new MediaListAdapter();
    }
}

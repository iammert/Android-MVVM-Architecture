package iammert.com.instagramtags.di.searchtag;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import iammert.com.instagramtags.di.Fragment;
import iammert.com.instagramtags.domain.searchtag.SearchTagUsecase;
import iammert.com.instagramtags.domain.searchtag.SearchTagUsecaseImpl;
import iammert.com.instagramtags.model.api.ApiSource;
import iammert.com.instagramtags.model.preferences.PreferencesHelper;
import iammert.com.instagramtags.model.preferences.PreferencesHelperImpl;
import iammert.com.instagramtags.util.RxBus;
import iammert.com.instagramtags.view.searchtag.SearchTagAdapter;
import iammert.com.instagramtags.viewmodel.searchtag.SearchTagViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */
@Module
public class SearchTagModule {

    SearchTagViewModel.SearchTagListener listener;

    public SearchTagModule(SearchTagViewModel.SearchTagListener listener) {
        this.listener = listener;
    }

    @Provides
    @Fragment
    PreferencesHelper<String> provideTokenPreferencesHelper(SharedPreferences sharedPreferences, Gson gson){
        return new PreferencesHelperImpl<>(sharedPreferences, gson);
    }

    @Provides
    @Fragment
    SearchTagUsecase provideSearchTagUsecase(PreferencesHelper<String> tokenHelper, ApiSource apiSource){
        return new SearchTagUsecaseImpl(tokenHelper, apiSource);
    }

    @Provides
    @Fragment
    SearchTagViewModel provideSearchTagViewModel(SearchTagUsecase usecase){
        return new SearchTagViewModel(usecase, listener);
    }

    @Provides
    @Fragment
    SearchTagAdapter provideSearchTagAdapter(RxBus rxBus){
        return new SearchTagAdapter(rxBus);
    }
}

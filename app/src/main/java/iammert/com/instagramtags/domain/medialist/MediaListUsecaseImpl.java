package iammert.com.instagramtags.domain.medialist;

import javax.inject.Inject;

import iammert.com.instagramtags.model.api.ApiSource;
import iammert.com.instagramtags.model.api.entity.MediaListResponse;
import iammert.com.instagramtags.model.preferences.PreferencesHelper;
import iammert.com.instagramtags.util.Constants;
import iammert.com.instagramtags.util.RxTransformer;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListUsecaseImpl implements MediaListUsecase{

    private ApiSource apiSource;
    private PreferencesHelper<String> tokenPreferencesHelper;

    @Inject
    public MediaListUsecaseImpl(ApiSource apiSource, PreferencesHelper<String> tokenPreferencesHelper) {
        this.apiSource = apiSource;
        this.tokenPreferencesHelper = tokenPreferencesHelper;
    }

    @Override
    public Observable<MediaListResponse> searchMedia(String tag) {
        return tokenPreferencesHelper.get(Constants.SHARED_KEY_TOKEN, String.class)
                .flatMap(s -> apiSource.searchMedia(tag, s))
                .compose(RxTransformer.applyIOSchedulers());
    }
}

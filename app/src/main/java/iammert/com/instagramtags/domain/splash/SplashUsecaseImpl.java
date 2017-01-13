package iammert.com.instagramtags.domain.splash;

import javax.inject.Inject;

import iammert.com.instagramtags.model.preferences.PreferencesHelper;
import iammert.com.instagramtags.util.Constants;
import iammert.com.instagramtags.util.RxTransformer;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SplashUsecaseImpl implements SplashUsecase{

    PreferencesHelper<String> tokenHelper;

    @Inject
    public SplashUsecaseImpl(PreferencesHelper<String> tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @Override
    public Observable<String> saveToken(String token) {
        return tokenHelper.save(Constants.SHARED_KEY_TOKEN, token)
                .compose(RxTransformer.applyComputationSchedulers());
    }
}

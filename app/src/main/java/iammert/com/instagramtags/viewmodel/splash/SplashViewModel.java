package iammert.com.instagramtags.viewmodel.splash;

import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import iammert.com.instagramtags.domain.splash.SplashUsecase;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SplashViewModel {

    private CompositeSubscription subscription;
    private SplashUsecase usecase;
    private SplashListener listener;

    @Inject
    public SplashViewModel(SplashUsecase usecase, SplashListener listener) {
        this.usecase = usecase;
        this.listener = listener;
        subscription = new CompositeSubscription();
    }

    public void onLoginClicked(View view) {
        listener.onLoginClicked();
    }

    public void saveToken(String token) {
        subscription.add(usecase.saveToken(token).subscribe(s -> listener.onUserLoggedIn()));
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public interface SplashListener {
        void onLoginClicked();

        void onUserLoggedIn();
    }

}

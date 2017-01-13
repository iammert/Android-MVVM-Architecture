package iammert.com.instagramtags.domain.splash;


import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface SplashUsecase {

    Observable<String> saveToken(String token);
}

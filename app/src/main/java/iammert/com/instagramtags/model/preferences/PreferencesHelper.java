package iammert.com.instagramtags.model.preferences;

import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface PreferencesHelper<T> {

    Observable<T> save(String key, T value);

    Observable<T> get(String key, Class<T> generic);

    Observable<Boolean> clear();
}

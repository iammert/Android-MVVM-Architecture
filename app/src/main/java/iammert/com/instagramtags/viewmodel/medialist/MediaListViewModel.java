package iammert.com.instagramtags.viewmodel.medialist;

import android.databinding.ObservableField;

import javax.inject.Inject;

import iammert.com.instagramtags.domain.medialist.MediaListUsecase;
import iammert.com.instagramtags.model.api.entity.MediaListResponse;
import iammert.com.instagramtags.model.api.entity.Tag;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListViewModel {

    public ObservableField<Boolean> isLoading;
    public ObservableField<Boolean> refreshEnabled;

    private CompositeSubscription subscription;
    private MediaListUsecase mediaListUsecase;
    private MediaListListener listener;
    private Tag tag;

    @Inject
    public MediaListViewModel(MediaListUsecase mediaListUsecase, MediaListListener listener, Tag tag) {
        this.mediaListUsecase = mediaListUsecase;
        this.listener = listener;
        this.tag = tag;
        subscription = new CompositeSubscription();

        isLoading = new ObservableField<>(true);
        refreshEnabled = new ObservableField<>(true);

        subscription.add(mediaListUsecase.searchMedia(tag.name)
                .doOnNext(response -> isLoading.set(false))
                .doOnNext(response -> refreshEnabled.set(false))
                .subscribe(listener::onMediaListLoaded));
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public interface MediaListListener {
        void onMediaListLoaded(MediaListResponse response);
    }
}

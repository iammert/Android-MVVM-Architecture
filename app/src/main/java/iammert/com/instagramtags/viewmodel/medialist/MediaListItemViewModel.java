package iammert.com.instagramtags.viewmodel.medialist;

import android.content.Context;
import android.databinding.ObservableField;

import iammert.com.instagramtags.R;
import iammert.com.instagramtags.model.api.entity.Media;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListItemViewModel {

    public ObservableField<String> userImage;
    public ObservableField<String> image;
    public ObservableField<String> userName;
    public ObservableField<String> likeCount;

    private Media media;

    public MediaListItemViewModel(Context context, Media media) {
        this.media = media;

        likeCount = new ObservableField<>(context.getString(R.string.like_count, String.valueOf(media.likes.count)));
        userImage = new ObservableField<>(media.user.profilePicture);
        userName = new ObservableField<>(media.user.username);
        image = new ObservableField<>(media.images.lowResolution.url);
    }
}

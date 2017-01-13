package iammert.com.instagramtags.viewmodel.searchtag;

import android.content.Context;
import android.databinding.ObservableField;

import iammert.com.instagramtags.R;
import iammert.com.instagramtags.model.api.entity.Tag;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SearchTagItemViewModel {

    public ObservableField<String> tagName;
    public ObservableField<String> postCount;

    private Tag tag;

    public SearchTagItemViewModel(Context context, Tag tag) {
        this.tag = tag;

        tagName = new ObservableField<>(context.getString(R.string.tag, tag.name));
        postCount = new ObservableField<>(context.getString(R.string.post_count, String.valueOf(tag.mediaCount)));

    }
}

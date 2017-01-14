package iammert.com.instagramtags.viewmodel.searchtag;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import iammert.com.instagramtags.R;
import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.util.RxBus;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SearchTagItemViewModel {

    public ObservableField<String> tagName;
    public ObservableField<String> postCount;

    private Tag tag;
    private RxBus rxBus;

    public SearchTagItemViewModel(Context context, RxBus rxBus, Tag tag) {
        this.tag = tag;
        this.rxBus = rxBus;

        tagName = new ObservableField<>(context.getString(R.string.tag, tag.name));
        postCount = new ObservableField<>(context.getString(R.string.post_count, String.valueOf(tag.mediaCount)));
    }

    public void onTagClicked(View view) {
        rxBus.send(new TagClickEvent(tag));
    }
}

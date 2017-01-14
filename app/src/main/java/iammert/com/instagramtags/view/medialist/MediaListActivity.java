package iammert.com.instagramtags.view.medialist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.parceler.Parcels;

import iammert.com.instagramtags.R;
import iammert.com.instagramtags.model.api.entity.Tag;

/**
 * Created by mertsimsek on 13/01/17.
 */
public class MediaListActivity extends AppCompatActivity {

    public static final String KEY_TAG = "key_tag";

    public static Intent newIntent(Context context, Tag tag) {
        Intent intent = new Intent(context, MediaListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_TAG, Parcels.wrap(tag));
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        Tag tag = Parcels.unwrap(getIntent().getExtras().getParcelable(KEY_TAG));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("#" + tag.name);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerDetail, MediaListFragment.newInstance(tag))
                    .commitAllowingStateLoss();
    }
}

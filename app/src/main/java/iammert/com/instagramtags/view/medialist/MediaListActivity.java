package iammert.com.instagramtags.view.medialist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import iammert.com.instagramtags.R;

/**
 * Created by mertsimsek on 13/01/17.
 */
public class MediaListActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MediaListActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerDetail, MediaListFragment.newInstance())
                    .commitAllowingStateLoss();
    }
}

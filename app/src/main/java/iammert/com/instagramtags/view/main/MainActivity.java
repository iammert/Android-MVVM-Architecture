package iammert.com.instagramtags.view.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import iammert.com.instagramtags.InstagramTagsApp;
import iammert.com.instagramtags.R;
import iammert.com.instagramtags.databinding.ActivityMainBinding;
import iammert.com.instagramtags.di.main.DaggerMainComponent;
import iammert.com.instagramtags.di.main.MainModule;
import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.view.medialist.MediaListActivity;
import iammert.com.instagramtags.view.medialist.MediaListFragment;
import iammert.com.instagramtags.view.searchtag.SearchTagFragment;
import iammert.com.instagramtags.viewmodel.main.MainViewModel;

public class MainActivity extends AppCompatActivity implements MainViewModel.MainListener {

    ActivityMainBinding binding;

    @Inject
    MainViewModel viewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeInjectors();
        initializeViews(savedInstanceState);
        binding.setViewModel(viewModel);
    }

    private void initializeViews(Bundle savedInstance) {
        if (findViewById(R.id.containerDetail) != null)
            viewModel.setTwoPane(true);

        if (savedInstance == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerMaster, SearchTagFragment.newInstance())
                    .commitAllowingStateLoss();
    }

    private void initializeInjectors() {
        DaggerMainComponent.builder()
                .appComponent(((InstagramTagsApp) getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.stop();
    }

    @Override
    public void onTagItemClicked(Tag tag) {
        if (!viewModel.isTwoPane())
            startActivity(MediaListActivity.newIntent(this, tag));
        else {
            if (!isFinishing())
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerDetail, MediaListFragment.newInstance(tag))
                        .commitAllowingStateLoss();
        }
    }
}

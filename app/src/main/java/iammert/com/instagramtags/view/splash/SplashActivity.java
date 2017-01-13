package iammert.com.instagramtags.view.splash;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import iammert.com.instagramtags.InstagramTagsApp;
import iammert.com.instagramtags.R;
import iammert.com.instagramtags.databinding.ActivitySplashBinding;
import iammert.com.instagramtags.di.splash.DaggerSplashComponent;
import iammert.com.instagramtags.di.splash.SplashModule;
import iammert.com.instagramtags.util.Constants;
import iammert.com.instagramtags.view.login.LoginActivity;
import iammert.com.instagramtags.view.main.MainActivity;
import iammert.com.instagramtags.viewmodel.splash.SplashViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SplashActivity extends AppCompatActivity implements SplashViewModel.SplashListener{

    private static int RC_LOGIN = 1001;

    ActivitySplashBinding binding;

    @Inject
    SplashViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        initializeInjectors();
        binding.setViewModel(viewModel);
    }

    private void initializeInjectors(){
        InstagramTagsApp app = (InstagramTagsApp) getApplication();
        DaggerSplashComponent.builder()
                .appComponent(app.getAppComponent())
                .splashModule(new SplashModule(this))
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.stop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(RC_LOGIN == requestCode){
            if(resultCode == Activity.RESULT_OK)
                viewModel.saveToken(data.getStringExtra(Constants.KEY_INTENT_TOKEN));
        }
    }

    @Override
    public void onLoginClicked() {
        startActivityForResult(new Intent(SplashActivity.this, LoginActivity.class), RC_LOGIN);
    }

    @Override
    public void onUserLoggedIn() {
        startActivity(MainActivity.newIntent(this));
        finish();
    }
}

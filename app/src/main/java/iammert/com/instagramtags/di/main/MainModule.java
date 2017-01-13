package iammert.com.instagramtags.di.main;

import dagger.Module;
import dagger.Provides;
import iammert.com.instagramtags.di.Activity;
import iammert.com.instagramtags.util.RxBus;
import iammert.com.instagramtags.viewmodel.main.MainViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */
@Module
public class MainModule {

    MainViewModel.MainListener mainListener;

    public MainModule(MainViewModel.MainListener mainListener) {
        this.mainListener = mainListener;
    }

    @Provides
    @Activity
    MainViewModel provideMainViewModel(RxBus rxBus){
        return new MainViewModel(rxBus, mainListener);
    }
}

package iammert.com.instagramtags.viewmodel.main;

import iammert.com.instagramtags.util.RxBus;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MainViewModel {

    private RxBus rxBus;
    private MainListener listener;
    private boolean isTwoPane = false;

    public MainViewModel(RxBus rxBus, MainListener listener) {
        this.rxBus = rxBus;
        this.listener = listener;
    }

    public void setTwoPane(boolean isTwoPane){
        this.isTwoPane = isTwoPane;
    }

    public interface MainListener{

    }
}

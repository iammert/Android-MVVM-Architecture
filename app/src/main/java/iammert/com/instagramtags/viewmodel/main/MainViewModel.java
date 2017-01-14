package iammert.com.instagramtags.viewmodel.main;

import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.util.RxBus;
import iammert.com.instagramtags.viewmodel.searchtag.TagClickEvent;

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

        rxBus.toObserverable()
                .filter(o -> o instanceof TagClickEvent)
                .map(o -> (TagClickEvent) o)
                .map(tagClickEvent -> tagClickEvent.tag)
                .subscribe(listener::onTagItemClicked);
    }

    public void setTwoPane(boolean isTwoPane){
        this.isTwoPane = isTwoPane;
    }

    public boolean isTwoPane(){
        return isTwoPane;
    }

    public interface MainListener{
        void onTagItemClicked(Tag tag);
    }
}

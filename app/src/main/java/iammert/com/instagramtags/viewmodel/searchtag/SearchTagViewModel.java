package iammert.com.instagramtags.viewmodel.searchtag;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.TextWatcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import iammert.com.instagramtags.domain.searchtag.SearchTagUsecase;
import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.util.RxBus;
import iammert.com.instagramtags.util.SimpleTextWatcher;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SearchTagViewModel {

    public ObservableField<Boolean> isLoading;
    public ObservableField<Boolean> refreshEnabled;
    public ObservableList<SearchTagItemViewModel> tagObservableList;

    private PublishSubject<String> publishSubject;
    private SearchTagUsecase usecase;
    private SearchTagListener listener;
    private Context context;
    private RxBus rxBus;

    public SearchTagViewModel(Context context, RxBus rxBus, SearchTagUsecase usecase, SearchTagListener listener) {
        this.usecase = usecase;
        this.listener = listener;
        this.context = context;
        this.rxBus = rxBus;

        isLoading = new ObservableField<>(false);
        refreshEnabled = new ObservableField<>(false);
        tagObservableList = new ObservableArrayList<>();
        publishSubject = PublishSubject.create();

        publishSubject.debounce(200, TimeUnit.MILLISECONDS)
                .doOnNext(response -> refreshEnabled.set(true))
                .doOnNext(response -> isLoading.set(true))
                .doOnNext(s -> tagObservableList.clear())
                .flatMap(usecase::searchTag)
                .doOnNext(response -> isLoading.set(false))
                .doOnNext(response -> refreshEnabled.set(false))
                .map(tagSearchResponse -> tagSearchResponse.data)
                .flatMap(Observable::from)
                .map(tag -> new SearchTagItemViewModel(context, rxBus, tag))
                .subscribe(tagObservableList::add, listener::onError);
    }

    public void setTagList(List<Tag> tags){
        tagObservableList.clear();
        Observable.from(tags)
                .map(tag -> new SearchTagItemViewModel(context, rxBus, tag))
                .subscribe(tagObservableList::add);
    }

    public Observable<List<Tag>> getTagList(){
        return Observable.from(tagObservableList)
                .map(SearchTagItemViewModel::getTag)
                .toList();
    }

    public TextWatcher getTextWatcher() {
        return new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() > 2)
                    publishSubject.onNext(s.toString());
            }
        };
    }

    public interface SearchTagListener{

        void onError(Throwable error);
    }
}

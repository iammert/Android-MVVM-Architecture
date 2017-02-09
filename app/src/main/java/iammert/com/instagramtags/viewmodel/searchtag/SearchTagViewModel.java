package iammert.com.instagramtags.viewmodel.searchtag;

import android.databinding.ObservableField;
import android.text.TextWatcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import iammert.com.instagramtags.domain.searchtag.SearchTagUsecase;
import iammert.com.instagramtags.model.api.entity.TagSearchResponse;
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

    public SearchTagViewModel(Context context, RxBus rxBus, SearchTagUsecase usecase, SearchTagListener listener) {
        this.usecase = usecase;
        this.listener = listener;

        isLoading = new ObservableField<>(false);
        refreshEnabled = new ObservableField<>(false);
        tagObservableList = new ObservableArrayList<>();
        publishSubject = PublishSubject.create();

        publishSubject.debounce(200, TimeUnit.MILLISECONDS)
                .doOnNext(response -> refreshEnabled.set(true))
                .doOnNext(response -> isLoading.set(true))
                .flatMap(usecase::searchTag)
                .doOnNext(response -> isLoading.set(false))
                .doOnNext(response -> refreshEnabled.set(false))
                .map(tagSearchResponse -> tagSearchResponse.data)
                .flatMap(Observable::from)
                .map(tag -> new SearchTagItemViewModel(context, rxBus, tag))
                .toList()
                .subscribe(tagObservableList::addAll, listener::onError);
    }

    public void setTagList(List<SearchTagItemViewModel> tagViewModels){
        tagObservableList.clear();
        tagObservableList.addAll(tagViewModels);
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

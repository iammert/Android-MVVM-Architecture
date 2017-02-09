package iammert.com.instagramtags.view.searchtag;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.lastadapter.LastAdapter;

import org.parceler.Parcels;

import javax.inject.Inject;

import iammert.com.instagramtags.BR;
import iammert.com.instagramtags.InstagramTagsApp;
import iammert.com.instagramtags.R;
import iammert.com.instagramtags.databinding.FragmentSearchTagBinding;
import iammert.com.instagramtags.di.searchtag.DaggerSearchTagComponent;
import iammert.com.instagramtags.di.searchtag.SearchTagModule;
import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.model.api.entity.TagSearchResponse;
import iammert.com.instagramtags.util.DialogBuilder;
import iammert.com.instagramtags.viewmodel.searchtag.SearchTagViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SearchTagFragment extends Fragment implements SearchTagViewModel.SearchTagListener {

    public static final String KEY_STATE_LIST = "key_state_list";

    FragmentSearchTagBinding binding;

    @Inject
    SearchTagViewModel viewModel;

    @Inject
    SearchTagAdapter adapter;

    public static SearchTagFragment newInstance() {
        Bundle args = new Bundle();
        SearchTagFragment fragment = new SearchTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_tag, container, false);
        initializeInjectors();
        binding.setViewModel(viewModel);

        LastAdapter.with(viewModel.tagObservableList, BR.viewModel)
                .map(Tag.class, R.layout.item_search_tag)
                .into(binding.recyclerView);

        if (savedInstanceState != null)
            viewModel.setTagList(Parcels.unwrap(savedInstanceState.getParcelable(KEY_STATE_LIST)));

        return binding.getRoot();
    }

    private void initializeInjectors() {
        DaggerSearchTagComponent.builder()
                .appComponent(((InstagramTagsApp) getActivity().getApplication()).getAppComponent())
                .searchTagModule(new SearchTagModule(getActivity(), this))
                .build().inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (viewModel != null && viewModel.tagObservableList != null)
            outState.putParcelable(KEY_STATE_LIST, Parcels.wrap(viewModel.tagObservableList));
    }

    @Override
    public void onError(Throwable error) {
        DialogBuilder
                .infoDialog(getActivity(),
                        R.string.dialog_error_title,
                        R.string.dialog_error_content)
                .show();
    }
}

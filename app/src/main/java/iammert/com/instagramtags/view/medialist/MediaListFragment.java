package iammert.com.instagramtags.view.medialist;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import javax.inject.Inject;

import iammert.com.instagramtags.InstagramTagsApp;
import iammert.com.instagramtags.R;
import iammert.com.instagramtags.databinding.FragmentMediaListBinding;
import iammert.com.instagramtags.di.medialist.DaggerMediaListComponent;
import iammert.com.instagramtags.di.medialist.MediaListModule;
import iammert.com.instagramtags.model.api.entity.MediaListResponse;
import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.util.DialogBuilder;
import iammert.com.instagramtags.viewmodel.medialist.MediaListViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListFragment extends Fragment implements MediaListViewModel.MediaListListener {

    public static final String KEY_TAG = "key_tag";
    public static final String KEY_STATE_LIST = "key_state_list";

    FragmentMediaListBinding binding;

    @Inject
    MediaListViewModel viewModel;

    @Inject
    MediaListAdapter adapter;

    public static MediaListFragment newInstance(Tag tag) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_TAG, Parcels.wrap(tag));
        MediaListFragment fragment = new MediaListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_media_list, container, false);
        initializeInjectors();
        initializeViews();
        binding.setViewModel(viewModel);

        if (savedInstanceState == null)
            viewModel.loadMedias();
        else
            adapter.setMedias(Parcels.unwrap(savedInstanceState.getParcelable(KEY_STATE_LIST)));

        return binding.getRoot();
    }

    private void initializeViews() {
        int displayMode = getResources().getConfiguration().orientation;

        GridLayoutManager gridLayoutManager;
        if (displayMode == Configuration.ORIENTATION_PORTRAIT)
            gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        else
            gridLayoutManager = new GridLayoutManager(getActivity(), 3);

        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    private void initializeInjectors() {
        Tag tag = Parcels.unwrap(getArguments().getParcelable(KEY_TAG));
        DaggerMediaListComponent.builder()
                .appComponent(((InstagramTagsApp) getActivity().getApplication()).getAppComponent())
                .mediaListModule(new MediaListModule(this, tag))
                .build().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.stop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter.getMedias() != null)
            outState.putParcelable(KEY_STATE_LIST, Parcels.wrap(adapter.getMedias()));
    }

    @Override
    public void onMediaListLoaded(MediaListResponse response) {
        adapter.setMedias(response.data);
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

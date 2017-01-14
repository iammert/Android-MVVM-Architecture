package iammert.com.instagramtags.view.medialist;

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
import iammert.com.instagramtags.viewmodel.medialist.MediaListViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListFragment extends Fragment implements MediaListViewModel.MediaListListener {

    public static final String KEY_TAG = "key_tag";

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
        return binding.getRoot();
    }

    private void initializeViews() {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
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
    public void onMediaListLoaded(MediaListResponse response) {
        adapter.setMedias(response.data);
    }
}

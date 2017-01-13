package iammert.com.instagramtags.view.medialist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iammert.com.instagramtags.R;
import iammert.com.instagramtags.databinding.FragmentMediaListBinding;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class MediaListFragment extends Fragment{

    FragmentMediaListBinding binding;

    public static MediaListFragment newInstance() {
        Bundle args = new Bundle();
        MediaListFragment fragment = new MediaListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_media_list, container, false);
        return binding.getRoot();
    }
}

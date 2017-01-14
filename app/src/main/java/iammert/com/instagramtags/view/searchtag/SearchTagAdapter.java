package iammert.com.instagramtags.view.searchtag;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import iammert.com.instagramtags.databinding.ItemSearchTagBinding;
import iammert.com.instagramtags.model.api.entity.Tag;
import iammert.com.instagramtags.util.RxBus;
import iammert.com.instagramtags.viewmodel.searchtag.SearchTagItemViewModel;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class SearchTagAdapter extends RecyclerView.Adapter<SearchTagAdapter.SearchTagViewHolder> {

    private List<Tag> tags;
    private RxBus rxBus;

    public SearchTagAdapter(RxBus rxBus) {
        this.rxBus = rxBus;
        tags = new ArrayList<>();
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        notifyDataSetChanged();
    }

    public List<Tag> getTags(){
        return tags;
    }

    @Override
    public SearchTagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SearchTagViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(SearchTagViewHolder holder, int position) {
        holder.bind(new SearchTagItemViewModel(holder.itemView.getContext(), rxBus, tags.get(position)));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    static class SearchTagViewHolder extends RecyclerView.ViewHolder {

        static SearchTagAdapter.SearchTagViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemSearchTagBinding binding = ItemSearchTagBinding.inflate(inflater, parent, false);
            return new SearchTagAdapter.SearchTagViewHolder(binding);
        }

        private ItemSearchTagBinding binding;

        public SearchTagViewHolder(ItemSearchTagBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SearchTagItemViewModel viewModel){
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }
}

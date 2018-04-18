package com.example.vuclip.mvvm_repository.posts;

import android.support.v7.widget.RecyclerView;

import com.example.vuclip.mvvm_repository.databinding.PostListItem;

/**
 * Created by Banty on 18/04/18.
 */
public class PostsViewHolder extends RecyclerView.ViewHolder {
    private final PostListItem itemBinding;

    public PostsViewHolder(PostListItem postListItem) {
        super(postListItem.getRoot());
        this.itemBinding = postListItem;
    }

    public void bind(PostItemViewModel model) {
        itemBinding.setPostViewModel(model);
        itemBinding.executePendingBindings();
    }
}

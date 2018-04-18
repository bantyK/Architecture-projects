package com.example.vuclip.mvvm_repository.posts;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.data.Post;
import com.example.vuclip.mvvm_repository.R;
import com.example.vuclip.mvvm_repository.databinding.PostListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Banty on 18/04/18.
 */
public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsViewHolder> {

    private List<Post> mPosts;

    public PostsRecyclerAdapter() {
        mPosts = new ArrayList<>();
    }

    public void setPosts(List<Post> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostListItem itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.post_item, parent, false);
        return new PostsViewHolder(itemBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.bind(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}

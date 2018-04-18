package com.example.vuclip.mvvm_repository.posts;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.data.Post;

import java.util.List;

/**
 * Created by Banty on 18/04/18.
 */
public class PostListBinding {

    @BindingAdapter("bind:posts")
    public static void setPosts(RecyclerView recyclerView, List<Post> posts) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (adapter != null && adapter instanceof PostsRecyclerAdapter) {
            ((PostsRecyclerAdapter) adapter).setPosts(posts);
        } else {
            throw new IllegalStateException("RecyclerView either has no adapter set or the "
                    + "adapter isn't of type PostsRecyclerAdapter");
        }
    }
}

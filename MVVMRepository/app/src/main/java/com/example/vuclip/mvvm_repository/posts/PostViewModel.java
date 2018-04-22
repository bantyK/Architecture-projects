package com.example.vuclip.mvvm_repository.posts;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.example.data.Post;
import com.example.data.repository.PostDataSource;
import com.example.data.repository.PostRepository;

import java.util.List;

/**
 * Created by Banty on 17/04/18.
 */
public class PostViewModel extends BaseObservable {

    public final ObservableList<Post> mPosts = new ObservableArrayList<>();

    public final ObservableBoolean showProgressBar = new ObservableBoolean(false);

    private final PostRepository mPostRepository;

    public PostViewModel(PostRepository postRepository) {
        mPostRepository = postRepository;
    }

    public void start() {
        getPosts();
    }

    private void getPosts() {
        showProgressBar.set(true);
        mPostRepository.getPosts(new PostDataSource.LoadPostCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                showProgressBar.set(false);
                mPosts.clear();
                mPosts.addAll(posts);
            }

            @Override
            public void onDataNotAvailable(String message) {
                showProgressBar.set(false);
            }
        });
    }
}

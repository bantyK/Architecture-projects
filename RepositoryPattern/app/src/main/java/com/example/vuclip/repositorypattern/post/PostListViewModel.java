package com.example.vuclip.repositorypattern.post;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.vuclip.repositorypattern.data.PostDataRepository;
import com.example.vuclip.repositorypattern.data.PostRepository;
import com.example.vuclip.repositorypattern.model.Post;

import java.util.List;

/**
 * Created by Banty on 12/04/18.
 */
public class PostListViewModel extends ViewModel {
    private static final String TAG = "PostListViewModel";

    private MutableLiveData<List<Post>> postsData;

    private final PostRepository mPostRepository;

    public PostListViewModel(PostRepository postRepository) {
        mPostRepository = postRepository;
    }

    public LiveData<List<Post>> getPosts() {
        if (postsData == null) {
            postsData = new MutableLiveData<>();
            loadPosts();
        }

        return postsData;
    }

    private void loadPosts() {
        mPostRepository.getPosts(new PostDataRepository.LoadPostsCallback() {
            @Override
            public void onPostLoaded(List<Post> posts) {
                Log.d(TAG, "onPostLoaded: posts : " + posts.size());
                postsData.setValue(posts);
            }

            @Override
            public void onPostFailedToLoad() {
                Log.d(TAG, "onPostFailedToLoad: ");
            }
        });

    }
}

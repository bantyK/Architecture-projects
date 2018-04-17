package com.example.vuclip.mvvm_repository.posts;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.data.Post;
import com.example.data.repository.PostDataSource;
import com.example.data.repository.PostRepository;

import java.util.List;

/**
 * Created by Banty on 17/04/18.
 */
public class PostViewModel extends BaseObservable {

    public final ObservableField<String> messageString = new ObservableField<>();

    private final PostRepository mPostRepository;

    public PostViewModel(PostRepository postRepository) {
        mPostRepository = postRepository;
    }

    public void start() {
        getPosts();
    }

    private void getPosts() {
        mPostRepository.getPosts(new PostDataSource.LoadPostCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                messageString.set("Number of posts loaded : " + posts.size());
            }

            @Override
            public void onDataNotAvailable(String message) {
                messageString.set("Post loading failed : " + message);
            }
        });
    }
}

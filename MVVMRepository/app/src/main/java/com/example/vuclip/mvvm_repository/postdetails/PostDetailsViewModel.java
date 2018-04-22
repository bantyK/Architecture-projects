package com.example.vuclip.mvvm_repository.postdetails;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.data.Post;
import com.example.data.repository.PostDataSource;
import com.example.data.repository.PostRepository;

/**
 * Created by Banty on 22/04/18.
 */
public class PostDetailsViewModel extends BaseObservable {

    private final String postId;


    public ObservableField<String> postTitle = new ObservableField<>();

    public ObservableField<String> postBody = new ObservableField<>();

    private final PostRepository mPostRepository;

    public PostDetailsViewModel(String postId, PostRepository postRepository) {
        this.postId = postId;
        mPostRepository = postRepository;
    }

    public void getPost() {
        mPostRepository.getPost(postId, new PostDataSource.GetPostCallback() {
            @Override
            public void onPostLoaded(Post post) {
                postTitle.set(post.getTitle());
                postBody.set(post.getBody());
            }

            @Override
            public void onDataNotAvailable(String message) {
                postTitle.set("Post cannot be loaded because : " + message);
            }
        });
    }
}

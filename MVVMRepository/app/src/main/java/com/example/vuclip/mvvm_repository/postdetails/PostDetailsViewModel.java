package com.example.vuclip.mvvm_repository.postdetails;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.data.Initializer;
import com.example.data.Post;
import com.example.data.repository.PostDataSource;
import com.example.data.repository.PostRepository;

/**
 * Created by Banty on 22/04/18.
 */
public class PostDetailsViewModel extends BaseObservable {

    private final String postId;

    private final Context mContext;

    public ObservableField<String> postTitle = new ObservableField<>();

    public ObservableField<String> postBody = new ObservableField<>();

    public PostDetailsViewModel(String postId, Context context) {
        this.postId = postId;
        mContext = context;
    }

    public void getPost() {
        Initializer initializer = new Initializer();
        PostRepository postRepository = initializer.getPostRepository(mContext);

        postRepository.getPost(postId, new PostDataSource.GetPostCallback() {
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

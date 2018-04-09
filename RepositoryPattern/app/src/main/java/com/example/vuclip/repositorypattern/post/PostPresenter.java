package com.example.vuclip.repositorypattern.post;

import android.util.Log;

import com.example.vuclip.repositorypattern.data.PostDataRepository;
import com.example.vuclip.repositorypattern.data.PostRepository;
import com.example.vuclip.repositorypattern.model.Post;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Banty on 08/04/18.
 */
public class PostPresenter implements PostContract.Presenter {

    private static final String TAG = "PostPresenter";

    private final PostRepository mPostRepository;

    private final PostContract.View mPostView;

    public PostPresenter(PostRepository postRepository, PostContract.View postFragment) {
        mPostRepository = checkNotNull(postRepository, "postRepository cannot be null");
        mPostView = checkNotNull(postFragment, "postFragment cannot be null");

        mPostView.setPresenter(this);
    }

    @Override
    public void loadPosts() {
        mPostRepository.getPosts(new PostDataRepository.LoadPostsCallback() {
            @Override
            public void onPostLoaded(List<Post> posts) {
                Log.i(TAG, "onPostLoaded: " + posts.size());
                mPostView.showPosts(posts);
            }

            @Override
            public void onPostFailedToLoad() {
                Log.i(TAG, "onPostFailedToLoad: loadPosts failed to load");
            }
        });
    }

    @Override
    public void start() {
        loadPosts();
    }
}

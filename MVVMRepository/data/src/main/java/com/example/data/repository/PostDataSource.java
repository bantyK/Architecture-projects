package com.example.data.repository;

import android.support.annotation.NonNull;

import com.example.data.Post;

import java.util.List;

/**
 * Created by Banty on 15/04/18.
 */
public interface PostDataSource {

    interface LoadPostCallback {

        void onPostsLoaded(List<Post> posts);

        void onDataNotAvailable(String message);
    }

    interface GetPostCallback {

        void onPostLoaded(Post post);

        void onDataNotAvailable(String message);
    }

    void getPosts(@NonNull LoadPostCallback callback);

    void getPost(@NonNull String postId, @NonNull GetPostCallback callback);

    void savePost(@NonNull Post post);

    void refreshPosts();

    void deletePost(@NonNull String postId);

    void deleteAllPosts();

}

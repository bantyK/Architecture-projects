package com.example.data.repository.remote;

import android.support.annotation.NonNull;

import com.example.data.Post;
import com.example.data.repository.PostDataSource;
import com.example.data.repository.remote.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Banty on 15/04/18.
 */
public class RemotePostRepository implements PostDataSource {

    private static RemotePostRepository INSTANCE = null;

    public static RemotePostRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemotePostRepository();
        }

        return INSTANCE;
    }


    @Override
    public void getPosts(@NonNull final LoadPostCallback callback) {
        RetrofitClient.getRetrofitClient()
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<List<Post>>() {
                    @Override
                    public void onNext(List<Post> value) {
                        if (value == null || value.isEmpty()) {
                            callback.onDataNotAvailable("API did not return any posts");
                        } else {
                            callback.onPostsLoaded(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // no need to implement for this case.
                    }
                });
    }

    @Override
    public void getPost(@NonNull final String postId, @NonNull final GetPostCallback callback) {
        RetrofitClient.getRetrofitClient()
                .getPost(postId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Post>() {
                    @Override
                    public void onNext(Post value) {
                        if (value == null) {
                            callback.onDataNotAvailable("API did not return post for id : " + postId);
                        } else {
                            callback.onPostLoaded(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable("API error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // no need to implement for this case.
                    }
                });
    }

    @Override
    public void savePost(@NonNull Post post) {
        // not applicable
    }

    @Override
    public void refreshPosts() {
        // not applicable
    }

    @Override
    public void deletePost(@NonNull String postId) {
        // not applicable
    }

    @Override
    public void deleteAllPosts() {
        // not applicable
    }
}

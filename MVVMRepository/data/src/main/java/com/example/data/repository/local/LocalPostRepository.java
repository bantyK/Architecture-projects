package com.example.data.repository.local;

import android.support.annotation.NonNull;

import com.example.data.Post;
import com.example.data.repository.PostDataSource;
import com.example.executor.AppExecutors;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Banty on 15/04/18.
 */
public class LocalPostRepository implements PostDataSource {

    private static LocalPostRepository INSTANCE = null;

    private final AppExecutors mAppExecutor;

    private final PostDao mPostDao;

    private LocalPostRepository(@NonNull AppExecutors appExecutor, @NonNull PostDao postDao) {
        mAppExecutor = appExecutor;
        mPostDao = postDao;
    }

    public static LocalPostRepository getInstance(
            @NonNull AppExecutors appExecutor, @NonNull PostDao postDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalPostRepository(appExecutor, postDao);
        }

        return INSTANCE;

    }

    @Override
    public void getPosts(@NonNull final LoadPostCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Post> posts = mPostDao.getPosts();
                mAppExecutor.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (posts.isEmpty()) {
                            callback.onDataNotAvailable("No post saved in DB");
                        } else {
                            callback.onPostsLoaded(posts);
                        }
                    }
                });
            }
        };

        mAppExecutor.diskIO().execute(runnable);
    }

    @Override
    public void getPost(@NonNull final String postId, @NonNull final GetPostCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Post post = mPostDao.getPost(postId);

                mAppExecutor.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (post == null) {
                            callback.onDataNotAvailable("Post with id = " + postId + " is not saved in" +
                                    " the database");
                        } else {
                            callback.onPostLoaded(post);
                        }
                    }
                });
            }
        };

        mAppExecutor.diskIO().execute(runnable);
    }

    @Override
    public void savePost(@NonNull final Post post) {
        checkNotNull(post, "Post cannot be null");
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mPostDao.insertPost(post);
            }
        };

        mAppExecutor.diskIO().execute(saveRunnable);
    }

    @Override
    public void refreshPosts() {
        // not required
    }

    @Override
    public void deletePost(@NonNull final String postId) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mPostDao.deletePost(postId);
            }
        };

        mAppExecutor.diskIO().execute(deleteRunnable);
    }

    @Override
    public void deleteAllPosts() {
        Runnable clearDBRunnable = new Runnable() {
            @Override
            public void run() {
                mPostDao.deletePosts();
            }
        };

        mAppExecutor.diskIO().execute(clearDBRunnable);
    }

    static void clearInstance() {
        INSTANCE = null;
    }
}

package com.example.data.repository;

import android.support.annotation.NonNull;

import com.example.data.Post;
import com.example.data.repository.local.LocalPostRepository;
import com.example.data.repository.remote.RemotePostRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Banty on 15/04/18.
 */
public class PostRepository implements PostDataSource {

    private static PostRepository INSTANCE = null;

    private final LocalPostRepository mLocalPostRepository;

    private final RemotePostRepository mRemotePostRepository;

    private Map<String, Post> cachedPosts;

    private boolean isCacheDirty = false;

    private PostRepository(LocalPostRepository localPostRepository, RemotePostRepository remotePostRepository) {
        mLocalPostRepository = checkNotNull(localPostRepository, "LocalPostRepository cannot be null");
        mRemotePostRepository = checkNotNull(remotePostRepository, "LocalPostRepository cannot be null");
    }

    public static PostRepository getInstance(@NonNull LocalPostRepository localPostRepository,
                                             RemotePostRepository remotePostRepository) {
        if (INSTANCE == null) {
            INSTANCE = new PostRepository(localPostRepository, remotePostRepository);
        }
        return INSTANCE;
    }

    @Override
    public void getPosts(@NonNull final LoadPostCallback callback) {
        checkNotNull(callback);

        if (cachedPosts != null && !isCacheDirty) {
            callback.onPostsLoaded(new ArrayList<Post>(cachedPosts.values()));
            return;
        } else {
            if (isCacheDirty) {
                getPostFromRemote(callback);
            } else {
                mLocalPostRepository.getPosts(new LoadPostCallback() {
                    @Override
                    public void onPostsLoaded(List<Post> posts) {
                        refreshCache(posts);
                        callback.onPostsLoaded(posts);
                    }

                    @Override
                    public void onDataNotAvailable(String message) {
                        getPostFromRemote(callback);
                    }
                });
            }
        }
    }

    private void getPostFromRemote(final LoadPostCallback callback) {
        mRemotePostRepository.getPosts(new LoadPostCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                refreshCache(posts);
                refreshLocalDataStorage(posts);
                callback.onPostsLoaded(posts);
            }

            @Override
            public void onDataNotAvailable(String message) {
                callback.onDataNotAvailable(message);
            }
        });
    }

    private void refreshCache(List<Post> posts) {
        if (cachedPosts == null) {
            cachedPosts = new LinkedHashMap<>();
        }

        cachedPosts.clear();

        for (Post post : posts) {
            cachedPosts.put(post.getId(), post);
        }
        isCacheDirty = false;
    }

    private void refreshLocalDataStorage(List<Post> posts) {
        mLocalPostRepository.deleteAllPosts();

        for (Post post : posts) {
            mLocalPostRepository.savePost(post);
        }
    }

    @Override
    public void getPost(@NonNull final String postId, @NonNull final GetPostCallback callback) {
        checkNotNull(postId, "Post id cannot be null");
        checkNotNull(callback, "GetPostCallback cannot be null");

        Post cachedPost = getPostFromCache(postId);

        if (cachedPost != null) {
            callback.onPostLoaded(cachedPost);
            return;
        }

        mLocalPostRepository.getPost(postId, new GetPostCallback() {
            @Override
            public void onPostLoaded(Post post) {
                refreshPostInCache(post);
                callback.onPostLoaded(post);
            }

            @Override
            public void onDataNotAvailable(String message) {
                mRemotePostRepository.getPost(postId, new GetPostCallback() {
                    @Override
                    public void onPostLoaded(Post post) {
                        refreshPostInCache(post);
                        refreshPostInLocalStorage(post);
                        callback.onPostLoaded(post);
                    }

                    @Override
                    public void onDataNotAvailable(String message) {
                        callback.onDataNotAvailable(message);
                    }
                });
            }
        });
    }

    private Post getPostFromCache(String postId) {
        checkNotNull(postId, "Post id cannot be null");

        if (cachedPosts == null || cachedPosts.get(postId) == null)
            return null;
        else
            return cachedPosts.get(postId);
    }

    private void refreshPostInLocalStorage(Post post) {
        mLocalPostRepository.savePost(post);
    }

    private void refreshPostInCache(@NonNull Post post) {
        if (cachedPosts == null) {
            cachedPosts = new LinkedHashMap<>();
        }
        cachedPosts.put(post.getId(), post);
    }

    @Override
    public void savePost(@NonNull Post post) {
        mLocalPostRepository.savePost(post);
        mRemotePostRepository.savePost(post);
        refreshPostInCache(post);
    }

    @Override
    public void refreshPosts() {
        isCacheDirty = true;
    }

    @Override
    public void deletePost(@NonNull String postId) {
        mLocalPostRepository.deletePost(postId);
        mRemotePostRepository.deletePost(postId);

        cachedPosts.remove(postId);
    }

    @Override
    public void deleteAllPosts() {
        mRemotePostRepository.deleteAllPosts();
        mLocalPostRepository.deleteAllPosts();

        if (cachedPosts != null)
            cachedPosts.clear();
    }
}

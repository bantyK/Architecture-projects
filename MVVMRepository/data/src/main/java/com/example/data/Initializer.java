package com.example.data;

import android.content.Context;

import com.example.data.repository.PostRepository;
import com.example.data.repository.local.LocalPostRepository;
import com.example.data.repository.local.PostDatabase;
import com.example.data.repository.remote.RemotePostRepository;
import com.example.executor.AppExecutors;

/**
 * Created by Banty on 15/04/18.
 */
public class Initializer {

    private LocalPostRepository getLocalPostRepository(Context context) {
        PostDatabase postDatabase = PostDatabase.getInstance(context);
        return LocalPostRepository.getInstance(new AppExecutors(), postDatabase.postDao());
    }

    private RemotePostRepository getRemotePostRepository() {
        return RemotePostRepository.getInstance();
    }

    public PostRepository getPostRepository(Context context) {
        return PostRepository.getInstance(getLocalPostRepository(context), getRemotePostRepository());
    }
}

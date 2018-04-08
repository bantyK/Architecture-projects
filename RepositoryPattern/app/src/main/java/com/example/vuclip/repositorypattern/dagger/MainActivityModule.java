package com.example.vuclip.repositorypattern.dagger;

import android.content.Context;

import com.example.vuclip.repositorypattern.data.PostRepository;
import com.example.vuclip.repositorypattern.data.local.LocalPostRepository;
import com.example.vuclip.repositorypattern.data.local.PostDao;
import com.example.vuclip.repositorypattern.data.local.PostDatabase;
import com.example.vuclip.repositorypattern.data.remote.RemotePostRepository;
import com.example.vuclip.repositorypattern.utils.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Banty on 08/04/18.
 */
@Singleton
@Module
public class MainActivityModule {

    final Context mContext;

    public MainActivityModule(Context context) {
        mContext = context;
    }

    @Provides
    public PostRepository providePostRepository(
            RemotePostRepository remotePostRepository, LocalPostRepository localPostRepository) {
        return PostRepository.getInstance(remotePostRepository, localPostRepository);
    }

    @Provides
    public RemotePostRepository provideRemotePostRepository() {
        return RemotePostRepository.getInstance();
    }

    @Provides
    public LocalPostRepository provideLocalPostRepository(PostDao dao, AppExecutors appExecutors) {
        return LocalPostRepository.getInstance(dao, appExecutors);
    }

    @Provides
    public PostDao providePostDao() {
        return PostDatabase.getInstance(mContext)
                .postDao();
    }

    @Provides
    public AppExecutors provideAppExecutor() {
        return new AppExecutors();
    }


}

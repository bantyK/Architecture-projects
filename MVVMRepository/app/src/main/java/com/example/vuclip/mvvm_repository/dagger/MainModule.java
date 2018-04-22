package com.example.vuclip.mvvm_repository.dagger;

import android.content.Context;

import com.example.data.Initializer;
import com.example.data.repository.PostRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Banty on 22/04/18.
 */
@Module
@Singleton
public class MainModule {

    private final Context mContext;

    public MainModule(Context context) {
        mContext = context;
    }

    @Provides
    public PostRepository providePostRepository(Initializer initializer) {
        return initializer.getPostRepository(mContext);
    }

    @Provides
    Initializer provideInitialzer() {
        return new Initializer();
    }
}

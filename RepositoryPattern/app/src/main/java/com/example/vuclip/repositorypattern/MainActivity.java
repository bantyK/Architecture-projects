package com.example.vuclip.repositorypattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.vuclip.repositorypattern.dagger.DaggerAppComponent;
import com.example.vuclip.repositorypattern.dagger.MainActivityModule;
import com.example.vuclip.repositorypattern.data.PostDataRepository;
import com.example.vuclip.repositorypattern.data.PostRepository;
import com.example.vuclip.repositorypattern.model.Post;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Banty on 08/04/18.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    PostRepository mPostRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerAppComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);

        mPostRepository.getPosts(new PostDataRepository.LoadPostsCallback() {
            @Override
            public void onPostLoaded(List<Post> posts) {
                Log.d(TAG, "onPostLoaded: " + posts.size());
            }

            @Override
            public void onPostFailedToLoad() {
                Log.d(TAG, "onPostFailedToLoad: posts failed to load");
            }
        });
    }
}

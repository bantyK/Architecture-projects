package com.example.vuclip.repositorypattern.post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.vuclip.repositorypattern.R;
import com.example.vuclip.repositorypattern.dagger.DaggerPostComponent;
import com.example.vuclip.repositorypattern.dagger.PostModule;
import com.example.vuclip.repositorypattern.data.PostRepository;

import javax.inject.Inject;

/**
 * Created by Banty on 08/04/18.
 */
public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";

    @Inject
    PostRepository mPostRepository;

    PostContract.Presenter mPostPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerPostComponent.builder()
                .postModule(new PostModule(this))
                .build()
                .inject(this);

        PostMvvmFragment fragment =
                (PostMvvmFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = PostMvvmFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFrame, fragment)
                    .commit();
        }



    }
}

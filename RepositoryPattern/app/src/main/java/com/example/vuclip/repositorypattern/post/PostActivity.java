package com.example.vuclip.repositorypattern.post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.vuclip.repositorypattern.R;

/**
 * Created by Banty on 08/04/18.
 */
public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";

    PostPresenter mPostPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PostFragment postFragment =
                (PostFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (postFragment == null) {
            postFragment = PostFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFrame, postFragment)
                    .commit();
        }

        mPostPresenter = new PostPresenter();

    }
}

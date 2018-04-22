package com.example.vuclip.mvvm_repository.postdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.vuclip.mvvm_repository.R;

/**
 * Created by Banty on 22/04/18.
 */
public class PostDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details_activity);

        String postId = getIntent().getStringExtra(PostDetailsFragment.ARG_POST_ID);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.post_detail_fragment_container, PostDetailsFragment.newInstance(postId))
                .commit();
    }
}

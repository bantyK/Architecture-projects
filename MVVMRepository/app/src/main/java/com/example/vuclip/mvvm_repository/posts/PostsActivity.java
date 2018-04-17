package com.example.vuclip.mvvm_repository.posts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.data.Initializer;
import com.example.data.repository.PostRepository;
import com.example.vuclip.mvvm_repository.R;
import com.example.vuclip.mvvm_repository.databinding.PostsModel;

public class PostsActivity extends AppCompatActivity {

    private static final String TAG = "PostsActivity";
    private PostRepository mPostRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PostsModel postsModel = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Initializer repositoryInitializer = new Initializer();
        mPostRepository = repositoryInitializer.getPostRepository(this);

        PostViewModel viewModel = new PostViewModel(mPostRepository);
        postsModel.setPostViewModel(viewModel);

        viewModel.start();

    }
}

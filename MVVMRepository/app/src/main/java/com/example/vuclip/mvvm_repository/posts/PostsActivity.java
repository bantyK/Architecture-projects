package com.example.vuclip.mvvm_repository.posts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.data.Initializer;
import com.example.data.repository.PostRepository;
import com.example.vuclip.mvvm_repository.R;
import com.example.vuclip.mvvm_repository.databinding.PostsModel;

public class PostsActivity extends AppCompatActivity {

    private static final String TAG = "PostsActivity";
    private PostRepository mPostRepository;
    private PostViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PostsModel postsModel = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Initializer repositoryInitializer = new Initializer();
        mPostRepository = repositoryInitializer.getPostRepository(this);

        mViewModel = new PostViewModel(mPostRepository);
        postsModel.setPostViewModel(mViewModel);

        mViewModel.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_posts:
                mPostRepository.refreshPosts();
                mViewModel.start();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

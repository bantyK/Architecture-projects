package com.example.vuclip.mvvm_repository.posts;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.data.Initializer;
import com.example.data.Post;
import com.example.data.repository.PostRepository;
import com.example.vuclip.mvvm_repository.R;
import com.example.vuclip.mvvm_repository.dagger.DaggerMainComponent;
import com.example.vuclip.mvvm_repository.dagger.MainModule;
import com.example.vuclip.mvvm_repository.databinding.PostsModel;
import com.example.vuclip.mvvm_repository.postdetails.PostDetailActivity;
import com.example.vuclip.mvvm_repository.postdetails.PostDetailsFragment;

import javax.inject.Inject;

public class PostsActivity extends AppCompatActivity implements PostInteractor {

    private static final String TAG = "PostsActivity";

    @Inject
    public PostRepository mPostRepository;

    private PostViewModel mViewModel;
    private PostsModel postsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postsModel = DataBindingUtil.setContentView(this, R.layout.activity_main);

        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        mViewModel = new PostViewModel(mPostRepository);
        postsModel.setPostViewModel(mViewModel);

        setupRecyclerView();

        mViewModel.start();
    }

    private void setupRecyclerView() {
        PostsRecyclerAdapter adapter = new PostsRecyclerAdapter(this);
        postsModel.posts.setLayoutManager(new LinearLayoutManager(this));
        postsModel.posts.setAdapter(adapter);
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

    @Override
    public void showPostDetails(Post post) {
        Toast.makeText(this, "post clicked : " + post.getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, PostDetailActivity.class);
        intent.putExtra(PostDetailsFragment.ARG_POST_ID, post.getId());
        startActivity(intent);
    }
}

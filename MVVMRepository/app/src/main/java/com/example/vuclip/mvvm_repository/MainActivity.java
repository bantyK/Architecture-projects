package com.example.vuclip.mvvm_repository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.data.Initialiser;
import com.example.data.Post;
import com.example.data.repository.PostDataSource;
import com.example.data.repository.PostRepository;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialiser repositoryInitialiser = new Initialiser();
        PostRepository postRepository =
                PostRepository.getInstance(
                        repositoryInitialiser.getLocalPostRepository(this),
                        repositoryInitialiser.getRemotePostRepository());

        postRepository.getPost("2", new PostDataSource.GetPostCallback() {
            @Override
            public void onPostLoaded(Post post) {
                Log.d(TAG, "onPostLoaded: title : " + post.getTitle());
                Log.d(TAG, "onPostLoaded: body : " + post.getBody());
            }

            @Override
            public void onDataNotAvailable(String message) {
                Log.d(TAG, "onDataNotAvailable: " + message);
            }
        });
    }
}

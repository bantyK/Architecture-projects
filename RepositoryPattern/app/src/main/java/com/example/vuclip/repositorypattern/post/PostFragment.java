package com.example.vuclip.repositorypattern.post;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuclip.repositorypattern.R;
import com.example.vuclip.repositorypattern.dagger.DaggerPostComponent;
import com.example.vuclip.repositorypattern.model.Post;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Banty on 08/04/18.
 */
public class PostFragment extends Fragment implements PostContract.View {

    private static final String TAG = "PostFragment";

    private PostContract.Presenter mPostPresenter;

    private RecyclerView postRecyclerView;

    PostRecyclerAdapter mPostRecyclerAdapter;

    public static PostFragment newInstance() {

        Bundle args = new Bundle();

        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        postRecyclerView = view.findViewById(R.id.post_recycler_view);
    }

    @Override
    public void showPosts(List<Post> posts) {
        Log.d(TAG, "showPosts: number of posts : " + posts);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        postRecyclerView.setHasFixedSize(true);
        mPostRecyclerAdapter = new PostRecyclerAdapter(posts);
        postRecyclerView.setAdapter(mPostRecyclerAdapter);
    }

    @Override
    public void setPresenter(PostContract.Presenter presenter) {
        mPostPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPostPresenter.start();
    }
}

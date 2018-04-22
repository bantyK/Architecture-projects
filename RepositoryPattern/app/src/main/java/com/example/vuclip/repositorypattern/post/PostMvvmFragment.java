package com.example.vuclip.repositorypattern.post;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuclip.repositorypattern.R;
import com.example.vuclip.repositorypattern.dagger.DaggerPostComponent;
import com.example.vuclip.repositorypattern.dagger.PostModule;
import com.example.vuclip.repositorypattern.data.PostRepository;
import com.example.vuclip.repositorypattern.model.Post;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Banty on 12/04/18.
 */
public class PostMvvmFragment extends Fragment implements PostContract.View {

    private RecyclerView postRecyclerView;

    private PostRecyclerAdapter mPostRecyclerAdapter;

    private PostListViewModel mViewModel;

    @Inject
    PostRepository mPostRepository;

    public static PostMvvmFragment newInstance() {
        Bundle args = new Bundle();
        PostMvvmFragment fragment = new PostMvvmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showPosts(List<Post> posts) {
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        postRecyclerView.setHasFixedSize(true);
        mPostRecyclerAdapter = new PostRecyclerAdapter(posts);
        postRecyclerView.setAdapter(mPostRecyclerAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_post, container, false);
        DaggerPostComponent.builder().postModule(new PostModule(getContext())).build().inject(this);

        mViewModel = ViewModelProviders.of(this, new ViewModelFactory(mPostRepository)).get(PostListViewModel.class);
        mViewModel.getPosts().observe(this, this::showPosts);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        postRecyclerView = view.findViewById(R.id.post_recycler_view);
    }


    @Override
    public void setPresenter(PostContract.Presenter presenter) {
        // not applicable for MVVM architecture
    }
}

class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public final PostRepository mPostRepository;

    ViewModelFactory(PostRepository postRepository) {
        mPostRepository = postRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PostListViewModel(mPostRepository);
    }
}
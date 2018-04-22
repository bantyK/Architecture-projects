package com.example.vuclip.mvvm_repository.postdetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuclip.mvvm_repository.R;
import com.example.vuclip.mvvm_repository.databinding.PostDetailsBinding;

/**
 * Created by Banty on 22/04/18.
 */
public class PostDetailsFragment extends Fragment {
    public static final String ARG_POST_ID = "PostDetailsFragment.POSTID";
    private PostDetailsBinding mBinding;

    public PostDetailsFragment() {

    }

    public static PostDetailsFragment newInstance(String postId) {
        PostDetailsFragment fragment = new PostDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_POST_ID, postId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail,
                container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String postId = getPostId(savedInstanceState);

        setUpViewModel(postId);
    }

    private void setUpViewModel(String postId) {
        PostDetailsViewModel viewModel = new PostDetailsViewModel(postId, getContext());
        mBinding.setPostDetailViewModel(viewModel);
        viewModel.getPost();
    }

    private String getPostId(Bundle bundle) {
        String postId;

        if (bundle == null) {
            postId = getArguments() != null ? getArguments().getString(ARG_POST_ID, "") : "";
        } else {
            postId = bundle.getString(ARG_POST_ID, "");
        }

        if (TextUtils.isEmpty(postId)) {
            throw new IllegalStateException("You either passed a wrong value of post id or you are not" +
                    "using newInstance convenience method for creating the instance of your fragment");
        }

        return postId;
    }
}

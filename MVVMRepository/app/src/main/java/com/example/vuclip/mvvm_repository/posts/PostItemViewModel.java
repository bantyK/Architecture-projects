package com.example.vuclip.mvvm_repository.posts;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;

import com.example.data.Post;

/**
 * Created by Banty on 18/04/18.
 */
public class PostItemViewModel extends BaseObservable {
    private final PostInteractor mPostInteractor;

    public final ObservableField<Post> mPostObservableField = new ObservableField<>();

    public final ObservableField<String> mPostTitleObservableField = new ObservableField<>();

    public final ObservableField<String> mPostBodyObservableField = new ObservableField<>();

    public PostItemViewModel(PostInteractor postInteractor) {
        mPostInteractor = postInteractor;

        mPostObservableField.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Post post = mPostObservableField.get();

                if (post != null) {
                    mPostTitleObservableField.set(post.getTitle());
                    mPostBodyObservableField.set(post.getBody());
                }
            }
        });

    }

    public void postTitleClicked() {
        Post post = getPost();
        if (post != null) {
            mPostInteractor.showPostDetails(post);
        }
    }


    public void setPost(Post post) {
        mPostObservableField.set(post);
    }

    @Nullable
    public Post getPost() {
        if (mPostObservableField.get() != null) {
            return mPostObservableField.get();
        }
        return null;
    }

}

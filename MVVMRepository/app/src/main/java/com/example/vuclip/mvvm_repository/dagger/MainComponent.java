package com.example.vuclip.mvvm_repository.dagger;

import com.example.vuclip.mvvm_repository.postdetails.PostDetailsFragment;
import com.example.vuclip.mvvm_repository.posts.PostsActivity;

import dagger.Component;

/**
 * Created by Banty on 22/04/18.
 */
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(PostsActivity target);

    void inject (PostDetailsFragment target);
}

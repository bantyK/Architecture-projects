package com.example.vuclip.repositorypattern.dagger;

import com.example.vuclip.repositorypattern.post.PostActivity;

import dagger.Component;

/**
 * Created by Banty on 08/04/18.
 */
@Component(modules = {MainActivityModule.class})
public interface AppComponent {
    void inject(PostActivity target);
}

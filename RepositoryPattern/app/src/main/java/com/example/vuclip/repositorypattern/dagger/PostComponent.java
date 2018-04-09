package com.example.vuclip.repositorypattern.dagger;

import com.example.vuclip.repositorypattern.post.PostActivity;

import dagger.Component;

/**
 * Created by Banty on 08/04/18.
 */
@Component(modules = {PostModule.class})
public interface PostComponent {

    void inject(PostActivity target);

}

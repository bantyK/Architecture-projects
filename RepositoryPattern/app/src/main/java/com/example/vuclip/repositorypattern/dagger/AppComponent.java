package com.example.vuclip.repositorypattern.dagger;

import com.example.vuclip.repositorypattern.MainActivity;

import dagger.Component;

/**
 * Created by Banty on 08/04/18.
 */
@Component(modules = {MainActivityModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}

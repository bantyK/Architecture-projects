package com.example.vuclip.repositorypattern;

/**
 * Created by Banty on 08/04/18.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}

package com.example.vuclip.repositorypattern.post;

import com.example.vuclip.repositorypattern.BasePresenter;
import com.example.vuclip.repositorypattern.BaseView;

/**
 * Created by Banty on 08/04/18.
 */
public interface PostContract {

    interface Presenter extends BasePresenter {
        void loadPosts();
    }


    interface View extends BaseView<Presenter> {
        void showPosts();
    }

}

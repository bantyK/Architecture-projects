package com.example.data.repository.remote.retrofit;

import com.example.data.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Banty on 15/04/18.
 */
public interface ApiInterface {
    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts/{postId}")
    Observable<Post> getPost(@Path("postId") String postId);
}

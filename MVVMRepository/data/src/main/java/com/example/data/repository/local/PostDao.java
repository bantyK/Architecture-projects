package com.example.data.repository.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.data.Post;

import java.util.List;

/**
 * Created by Banty on 15/04/18.
 */
@Dao
public interface PostDao {

    /**
     * Get all posts from database.
     */
    @Query("SELECT * FROM posts")
    List<Post> getPosts();

    /**
     * Get post with postId from database.
     */
    @Query("SELECT * FROM posts where postId = :postId")
    Post getPost(String postId);

    /**
     * Insert post into database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(Post post);

    /*
     * Delete all posts from database.
     * */
    @Query("DELETE FROM posts")
    void deletePosts();

    /*
     * Delete post with postId from database.
     */
    @Query("DELETE FROM posts where postId = :postId")
    int deletePost(String postId);
}

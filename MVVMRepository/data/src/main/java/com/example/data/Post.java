package com.example.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by Banty on 15/04/18.
 */
@Entity(tableName = "posts")
public class Post {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "postId")
    @SerializedName("id")
    private final String mId;

    @NonNull
    @ColumnInfo(name = "mTitle")
    @SerializedName("title")
    private final String mTitle;

    @NonNull
    @ColumnInfo(name = "mBody")
    @SerializedName("body")
    private final String mBody;

    /**
     * Constructor to create a new post when post mId is known.
     */
    public Post(@NonNull String id, @NonNull String title, @NonNull String body) {
        this.mId = id;
        this.mTitle = title;
        this.mBody = body;
    }

    /**
     * Constructor to create a new post when mId is not known.
     */
    @Ignore
    public Post(@NonNull String title, @NonNull String body) {
        this(UUID.randomUUID().toString(), title, body);

    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getBody() {
        return mBody;
    }

    @Override
    public String toString() {
        return "Post with mTitle : " + mTitle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Post post = (Post) obj;

        return Objects.equal(mId, post.mId) &&
                Objects.equal(mTitle, post.mTitle) &&
                Objects.equal(mBody, post.mBody);
    }
}

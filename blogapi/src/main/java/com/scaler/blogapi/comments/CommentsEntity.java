package com.scaler.blogapi.comments;

import com.scaler.blogapi.articles.ArticleEntity;
import com.scaler.blogapi.common.BaseEntity;
import com.scaler.blogapi.users.UserEntity;

import javax.persistence.*;

@Entity(name = "comments")
public class CommentsEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    String title;

    @Column(length = 1000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;
}

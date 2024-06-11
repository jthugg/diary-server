package com.github.jthugg.diary.external.data.access.mysql.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class PostDetailEntity {

    protected long id;
    protected long post;
    protected String title;
    protected String body;
    protected Instant createdAt;

    public PostDetailEntity(
            long id,
            long post,
            String title,
            String body
    ) {
        this.id = id;
        this.post = post;
        this.title = title;
        this.body = body;
        this.createdAt = Instant.now();
    }

}

package com.github.jthugg.diary.external.data.access.mysql.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
public class PostEntity implements RemovableEntity {

    protected long id;
    protected long writer;
    @Setter
    protected String title;
    @Setter
    protected String summary;
    @Setter
    protected int scope;
    @Setter
    protected long viewed;
    @Setter
    protected long liked;
    protected Instant createdAt;
    protected Instant removedAt;

    public PostEntity(
            long id,
            long writer,
            String title,
            String summary,
            int scope
    ) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.summary = summary;
        this.scope = scope;
        this.viewed = 0;
        this.liked = 0;
        this.createdAt = Instant.now();
    }

    @Override
    public void remove() {
        this.removedAt = Instant.now();
    }

}

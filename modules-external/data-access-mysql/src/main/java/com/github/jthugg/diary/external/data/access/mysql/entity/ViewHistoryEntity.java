package com.github.jthugg.diary.external.data.access.mysql.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ViewHistoryEntity implements RemovableEntity {

    protected long id;
    protected long member;
    protected long post;
    protected Instant createdAt;
    protected Instant removedAt;

    public ViewHistoryEntity(long id, long member, long post) {
        this.id = id;
        this.member = member;
        this.post = post;
        this.createdAt = Instant.now();
    }

    @Override
    public void remove() {
        this.removedAt = Instant.now();
    }

}

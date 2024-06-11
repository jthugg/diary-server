package com.github.jthugg.diary.external.data.access.mysql.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class FollowEntity implements RemovableEntity {

    protected long id;
    protected long follower;
    protected long followee;
    protected Instant createdAt;
    protected Instant removedAt;

    @Override
    public void remove() {
        this.removedAt = Instant.now();
    }

}

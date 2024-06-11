package com.github.jthugg.diary.external.data.access.mysql.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class MemberProfileEntity {

    protected long id;
    protected long member;
    protected String username;
    protected String bio;
    protected String profileImageUrl;
    protected long followers;
    protected long following;
    protected Instant createdAt;

    public MemberProfileEntity(
            long id,
            long member,
            String username,
            String bio,
            String profileImageUrl,
            long followers,
            long following
    ) {
        this.id = id;
        this.member = member;
        this.username = username;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = Instant.now();
        this.followers = followers;
        this.following = following;
    }

}

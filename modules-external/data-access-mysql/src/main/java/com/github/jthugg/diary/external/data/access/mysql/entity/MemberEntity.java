package com.github.jthugg.diary.external.data.access.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.jdbc.support.KeyHolder;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@FieldNameConstants
public class MemberEntity implements RemovableEntity, AutoIncrementedId {

    @Id
    protected long id;
    protected String oauthProvider;
    protected String oauthIdentifier;
    @Setter
    protected String username;
    protected String memberRole;
    @Setter
    protected String profileImageUrl;
    protected Instant createdAt;
    protected Instant removedAt;

    public MemberEntity(String oauthProvider, String oauthIdentifier) {
        this.oauthProvider = oauthProvider;
        this.oauthIdentifier = oauthIdentifier;
        this.memberRole = "ROLE_USER";
    }

    @Override
    public void remove() {
        this.removedAt = Instant.now();
    }

    @Override
    public void refreshId(KeyHolder keyHolder) {
        this.id = keyHolder.getKey().longValue();
    }

}

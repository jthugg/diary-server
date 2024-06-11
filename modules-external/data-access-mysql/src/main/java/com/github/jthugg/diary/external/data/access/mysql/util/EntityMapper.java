package com.github.jthugg.diary.external.data.access.mysql.util;

import com.github.jthugg.diary.external.data.access.mysql.entity.MemberEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class EntityMapper {

    public static MemberEntity toMember(ResultSet rs, int count) throws SQLException {
        return MemberEntity.builder()
                .id(rs.getLong(MemberEntity.Fields.id))
                .oauthProvider(rs.getString(MemberEntity.Fields.oauthProvider))
                .oauthIdentifier(rs.getString(MemberEntity.Fields.oauthIdentifier))
                .username(rs.getString(MemberEntity.Fields.username))
                .memberRole(rs.getString(MemberEntity.Fields.memberRole))
                .profileImageUrl(rs.getString(MemberEntity.Fields.profileImageUrl))
                .createdAt(rs.getObject(MemberEntity.Fields.createdAt, Instant.class))
                .removedAt(rs.getObject(MemberEntity.Fields.removedAt, Instant.class))
                .build();
    }

}

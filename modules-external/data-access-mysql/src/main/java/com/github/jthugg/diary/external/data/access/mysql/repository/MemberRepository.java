package com.github.jthugg.diary.external.data.access.mysql.repository;

import com.github.jthugg.diary.external.data.access.mysql.entity.MemberEntity;
import com.github.jthugg.diary.external.data.access.mysql.entity.MemberProfileEntity;
import com.github.jthugg.diary.external.data.access.mysql.util.EntityMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class MemberRepository {

    private static final String INSERT
            = "INSERT INTO Member (oauthProvider, oauthIdentifier, username, memberRole) VALUES (?, ?, ?, ?)";
    private static final String INSERT_PROFILE
            = "INSERT INTO MemberProfile (member) VALUES (?)";
    private static final String COUNT_ONE_BY_USERNAME
            = "SELECT COUNT(*) FROM Member WHERE username = ? AND removedAt IS NULL LIMIT 1";
    private static final String SELECT_BY_ID
            = "SELECT * FROM Member WHERE id = ? AND removedAt IS NULL";
    private static final String SELECT_BY_OAUTH_DATA
            = "SELECT * FROM Member WHERE oauthProvider = ? AND oauthIdentifier = ? AND removedAt IS NULL";
    private static final String SELECT_BY_USERNAME
            = "SELECT * FROM Member WHERE username REGEXP ? AND removedAt IS NULL ORDER BY id DESC LIMIT ? OFFSET ?";
    private static final String SELECT_MEMBER_PROFILE_BY_MEMBER_ID
            = "SELECT * FROM MemberProfile WHERE member = ? ORDER BY id DESC LIMIT 1";

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MemberEntity create(String oauthProvider, String oauthIdentifier) {
        MemberEntity member = new MemberEntity(oauthProvider, oauthIdentifier);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT, new String[]{"id"});
            ps.setString(1, member.getOauthProvider());
            ps.setString(2, member.getOauthIdentifier());
            ps.setString(3, member.getUsername());
            ps.setString(4, member.getMemberRole());
            return ps;
        }, keyHolder);
        member.refreshId(keyHolder);
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_PROFILE);
            ps.setLong(1, member.getId());
            return ps;
        });
        return member;
    }

    public boolean existsByUsername(String username) {
        return jdbcTemplate.queryForObject(COUNT_ONE_BY_USERNAME, Integer.class, username) == 1;
    }

    public Optional<MemberEntity> findById(long id) {
        List<MemberEntity> results = jdbcTemplate.query(SELECT_BY_ID, EntityMapper::toMember, id);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.getFirst());
    }

    public Optional<MemberEntity> findByOauthData(String oauthProvider, String oauthIdentifier) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    SELECT_BY_OAUTH_DATA,
                    EntityMapper::toMember,
                    oauthProvider,
                    oauthIdentifier
            ));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    public List<MemberEntity> findByUsername(String keywords, int size, int page) {
        String keyword = keywords.replaceAll(" +", "|");
        int offset = page * size;
        return jdbcTemplate.query(SELECT_BY_USERNAME, EntityMapper::toMember, keyword, size, offset);
    }

}

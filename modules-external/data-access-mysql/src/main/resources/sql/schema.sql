-- define schema here.
-- MySQL dialect

CREATE TABLE Member (
    id BIGINT,
    oauthProvider VARCHAR,
    username VARCHAR,
    role VARCHAR,
    profileImageUrl TEXT,
    createdAt TIMESTAMP(6),
    removedAt TIMESTAMP(6)
);

CREATE TABLE MemberProfile (
    id BIGINT,
    member BIGINT,
    username VARCHAR,
    bio TEXT,
    profileImageUrl TEXT,
    createdAt TIMESTAMP(6),
    removedAt TIMESTAMP(6)
);

CREATE TABLE Follow (
    id BIGINT,
    follower BIGINT, -- if A follows B, follower is A.
    followee BIGINT, -- if A follows B, followee is B.
    createdAt TIMESTAMP(6),
    removedAt TIMESTAMP(6)
);

CREATE TABLE Post (
    id BIGINT,
    writer BIGINT, -- member
    title VARCHAR,
    summary TEXT,
    scope INT,
    viewed BIGINT,
    liked BIGINT,
    createdAt TIMESTAMP(6),
    removedAt TIMESTAMP(6)
);

CREATE TABLE PostDetail (
    id BIGINT,
    post BIGINT,
    title VARCHAR,
    body TEXT,
    createdAt TIMESTAMP(6),
    removedAt TIMESTAMP(6)
);

CREATE TABLE LikeHistory (
    id BIGINT,
    member BIGINT,
    post BIGINT,
    createdAt TIMESTAMP(6),
    removedAt TIMESTAMP(6)
);

CREATE TABLE ViewHistory (
    id BIGINT,
    member BIGINT,
    post BIGINT,
    createdAt TIMESTAMP(6),
    removedAt TIMESTAMP(6)
);

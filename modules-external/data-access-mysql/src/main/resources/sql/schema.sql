-- define schema here.
-- MySQL dialect

CREATE TABLE `Member` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    oauthProvider VARCHAR(10),
    oauthIdentifier VARCHAR(255),
    username VARCHAR(12),
    memberRole VARCHAR(30),
    profileImageUrl TEXT,
    createdAt TIMESTAMP(6) DEFAULT now(),
    removedAt TIMESTAMP(6)
);

CREATE TABLE `MemberProfile` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member BIGINT,
    username VARCHAR(12),
    bio TEXT,
    profileImageUrl TEXT,
    followers BIGINT DEFAULT 0,
    following BIGINT DEFAULT 0,
    createdAt TIMESTAMP(6) DEFAULT now()
);

CREATE TABLE `Follow` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    follower BIGINT, -- if A follows B, follower is A.
    followee BIGINT, -- if A follows B, followee is B.
    createdAt TIMESTAMP(6) DEFAULT now(),
    removedAt TIMESTAMP(6)
);

CREATE TABLE `Post` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    writer BIGINT, -- member
    title VARCHAR(100),
    summary TEXT,
    scope INT,
    viewed BIGINT,
    liked BIGINT,
    createdAt TIMESTAMP(6) DEFAULT now(),
    removedAt TIMESTAMP(6)
);

CREATE TABLE `PostDetail` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post BIGINT,
    title VARCHAR(100),
    body TEXT,
    createdAt TIMESTAMP(6) DEFAULT now()
);

CREATE TABLE `LikeHistory` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member BIGINT,
    post BIGINT,
    createdAt TIMESTAMP(6) DEFAULT now(),
    removedAt TIMESTAMP(6)
);

CREATE TABLE `ViewHistory` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member BIGINT,
    post BIGINT,
    createdAt TIMESTAMP(6) DEFAULT now(),
    removedAt TIMESTAMP(6)
);

-- define data here.
-- MySQL dialect

-- member
INSERT INTO
    Member(oauthProvider, oauthIdentifier, username, memberRole, createdAt)
VALUES
    ('GITHUB', '321634435', 'user01', 'ROLE_NORMAL', now()),
    ('GOOGLE', '645642535', 'user02', 'ROLE_NORMAL', now()),
    ('GITHUB', '12635', 'user03', 'ROLE_NORMAL', now()),
    ('GITHUB', '3634435', 'user04', 'ROLE_NORMAL', now()),
    ('GOOGLE', '3435', 'user05', 'ROLE_NORMAL', now());

INSERT INTO
    MemberProfile(member)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5);
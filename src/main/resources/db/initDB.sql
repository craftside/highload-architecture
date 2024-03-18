DROP TABLE IF EXISTS user_tokens;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id     VARCHAR PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    second_name VARCHAR NOT NULL,
    birthdate   DATE    NOT NULL,
    password    VARCHAR NOT NULL,
    biography   VARCHAR NOT NULL,
    gender      VARCHAR NOT NULL,
    city        VARCHAR NOT NULL
);

CREATE TABLE user_tokens
(
    user_id VARCHAR NOT NULL,
    token   VARCHAR NOT NULL,
    CONSTRAINT user_token_idx UNIQUE (user_id, token),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
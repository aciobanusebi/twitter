CREATE DATABASE IF NOT EXISTS twitter;

CREATE TABLE twitter.USERS (
    ID varchar(255),
    FIRST_NAME varchar(255) NOT NULL,
    LAST_NAME varchar(255) NOT NULL,
    EMAIL varchar(255) NOT NULL,
    PASSWORD varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE twitter.POSTS (
    ID INT AUTO_INCREMENT,
    USER_ID varchar(255) NOT NULL,
    MESSAGE varchar(255) NOT NULL,
    TIMESTAMP BIGINT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE twitter.MENTIONS (
    ID INT AUTO_INCREMENT,
    USER_ID varchar(255) NOT NULL,
    POST_ID INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
    FOREIGN KEY (POST_ID) REFERENCES POSTS(ID)
);

CREATE TABLE twitter.REPLIES (
    POST_ID INT NOT NULL,
    PARENT_POST_ID INT NOT NULL,
    PUBLIC BOOLEAN NOT NULL,
    PRIMARY KEY (POST_ID),
    FOREIGN KEY (POST_ID) REFERENCES POSTS(ID),
    FOREIGN KEY (PARENT_POST_ID) REFERENCES POSTS(ID)
);

CREATE TABLE twitter.FOLLOWS (
    USER_ID varchar(255) NOT NULL,
    FOLLOWING_USER_ID varchar(255) NOT NULL,
    TIMESTAMP BIGINT NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
    FOREIGN KEY (FOLLOWING_USER_ID) REFERENCES USERS(ID),
    UNIQUE(USER_ID, FOLLOWING_USER_ID)
);

CREATE TABLE twitter.LIKES (
    ID int AUTO_INCREMENT,
    USER_ID varchar(255) NOT NULL,
    POST_ID INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
    FOREIGN KEY (POST_ID) REFERENCES POSTS(ID),
    UNIQUE(USER_ID, POST_ID)
);
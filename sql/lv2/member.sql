create table member
(
    id          bigint auto_increment
        primary key,
    email       varchar(255) not null,
    password    varchar(255) not null,
    username    varchar(255) not null,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null
);
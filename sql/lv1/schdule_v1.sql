create table schedule_v1
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    contents    longtext     null,
    title       varchar(255) not null,
    writer      varchar(255) not null
);
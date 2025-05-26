create table schedule_v2
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    contents    varchar(255) not null,
    title       varchar(255) not null,
    member_id   bigint       not null,
    constraint FKai2pgn3m8dkfic6m5bh0wsv5h
        foreign key (member_id) references member (id)
);
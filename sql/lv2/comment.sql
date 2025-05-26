create table comment
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    content     varchar(255) not null,
    member_id   bigint       not null,
    schedule_id bigint       not null,
    constraint FKmrrrpi513ssu63i2783jyiv9m
        foreign key (member_id) references member (id),
    constraint FKt2uq36c5p201a0ghudes927kp
        foreign key (schedule_id) references schedule_v2 (id)
);
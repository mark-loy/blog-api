create table visitor
(
    id int auto_increment comment '自增、主键',
    account_id bigint not null comment '账户id，唯一',
    source int not null comment '登录来源，1.github;2.qq',
    visitor_name varchar(50) not null comment '访客名称',
    avatar_url varchar(512) not null comment '头像url',
    gmt_create bigint not null comment '首次登录时间',
    gmt_modified bigint not null comment '最新登录时间',
    constraint visitor_pk
        primary key (id)
);

create unique index visitor_account_id_uindex
    on visitor (account_id);


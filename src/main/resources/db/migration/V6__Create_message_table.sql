create table message
(
    id int auto_increment comment '主键、自增',
    type int default 1 not null comment '留言类型；1.一级留言；2.回复留言',
    parent_id int default 0 not null comment '父级id；0.没有父级留言',
    visitor_id int not null comment '访客id',
    content varchar(512) not null comment '留言内容',
    state boolean default false not null comment '留言状态；true; false',
    gmt_create bigint not null comment '留言时间',
    constraint message_pk
        primary key (id)
);


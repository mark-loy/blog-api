create table inform
(
    id int auto_increment comment '主键、自增',
    visitor_id int not null comment '通知人',
    admin_id int default 1 not null comment '被通知人id',
    article_id int default 0 not null comment '文章id',
    message_id int default 0 not null comment '留言id',
    type int not null comment '通知类型；1.留言，2.点赞',
    state int default 0 not null comment '通知状态；0.未读；1.已读',
    gmt_create bigint not null comment '通知时间',
    constraint inform_pk
        primary key (id)
);





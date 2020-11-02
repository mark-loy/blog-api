create table visitorlike
(
    id int auto_increment comment '自增、主键',
    visitor_id int not null comment '访客id',
    article_id int not null comment '文章id',
    gmt_create bigint not null comment '点赞时间',
    constraint visitor_pk
        primary key (id)
);



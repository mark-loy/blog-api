create table article
(
    id int auto_increment comment '主键、自增',
    title varchar(50) not null comment '文章标题',
    description varchar(125) not null comment '文章描述',
    show_img varchar(512) comment '展示图片',
    context text not null comment '文章内容',
    admin_id int not null comment '发表人id',
    category_id int not null comment '分类id',
    tags_id varchar(100) not null comment '多个标签id',
    view_count int default 0 null comment '浏览数',
    like_count int default 0 null comment '点赞数',
    gmt_create bigint not null comment '发表时间',
    gmt_modified bigint null comment '最后修改时间',
    constraint article_pk
        primary key (id)
)
    comment '文章表';


create table category
(
    id int auto_increment comment '主键自增',
    category_name varchar(16) not null comment '分类名称',
    article_count int default 0 null comment '文章数',
    gmt_create bigint null comment '创建时间',
    gmt_modified bigint null comment '修改时间',
    constraint category_pk
        primary key (id)
);

create unique index category_category_name_uindex
    on category (category_name);


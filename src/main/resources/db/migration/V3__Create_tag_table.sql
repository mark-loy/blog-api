create table tag
(
    id int auto_increment comment '主键自增',
    tag_name varchar(16) not null comment '标签名',
    article_count int default 0 null comment '文章数',
    gmt_create bigint null comment '创建时间',
    gmt_modified bigint null comment '修改时间',
    constraint tag_pk
        primary key (id)
)
    comment '标签表';

create unique index tag_tag_name_uindex
    on tag (tag_name);


create table user
(
	id int auto_increment comment '自增主键',
	username varchar(16) not null comment '用户名',
	password varchar(50) not null comment '密码',
	email varchar(50) null comment '邮箱',
	pet_name varchar(11) not null comment '昵称',
	avatar varchar(512) null comment '头像',
	article_count int default 0 not null comment '发表文章数',
	state int default 1 not null comment '状态：1.正常，2.异常',
	gmt_create bigint not null comment '创建时间',
	gmt_modified bigint null comment '修改时间',
	constraint user_pk
		primary key (id)
);

create unique index user_username_uindex
	on user (username);


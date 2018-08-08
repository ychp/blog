DROP TABLE IF EXISTS `sky_user`;

CREATE TABLE IF NOT EXISTS `sky_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(256) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `home_page` varchar(256) DEFAULT NULL COMMENT '主页',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '秘钥',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_name` (`name`),
  KEY `idx_user_nick_name` (`nick_name`),
  KEY `idx_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';

DROP TABLE IF EXISTS `sky_user_profile`;

CREATE TABLE IF NOT EXISTS `sky_user_profile` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `home_page` varchar(256) DEFAULT NULL COMMENT '主页',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_profile_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

DROP TABLE IF EXISTS `sky_user_login_log`;

CREATE TABLE IF NOT EXISTS `sky_user_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `ip` varchar(256) NOT NULL COMMENT 'ip地址',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_login_log_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户登录信息表';

DROP TABLE IF EXISTS `sky_category`;

CREATE TABLE IF NOT EXISTS `sky_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '名称',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '分类表';

DROP TABLE IF EXISTS `sky_label`;

CREATE TABLE IF NOT EXISTS `sky_label` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '名称',
  `visible` tinyint(1) NOT NULL COMMENT '是否可见，0.不可见，1.可见',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '标签表';

DROP TABLE IF EXISTS `sky_article`;

CREATE TABLE IF NOT EXISTS `sky_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) NOT NULL COMMENT '标题',
  `category_id` bigint(20) NOT NULL COMMENT '类目ID',
  `category_name` varchar(256) NOT NULL COMMENT '类目名称',
  `synopsis` varchar(256) DEFAULT NULL COMMENT '简介',
  `user_id` bigint(20) DEFAULT NULL COMMENT '作者Id',
  `author` varchar(256) DEFAULT NULL COMMENT '作者',
  `visible` tinyint(1) NOT NULL COMMENT '是否可见，0.不可见，1.可见',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章表';

DROP TABLE IF EXISTS `sky_article_detail`;

CREATE TABLE IF NOT EXISTS `sky_article_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `content` text COMMENT '内容',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_article_detail_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章详情表';

DROP TABLE IF EXISTS `sky_article_summary`;

CREATE TABLE IF NOT EXISTS `sky_article_summary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `popular` int(20) DEFAULT 0 COMMENT '浏览量',
  `like` int(10) DEFAULT 0 COMMENT '点赞数',
  `favorite` int(10) DEFAULT 0 COMMENT '收藏数',
  `comments` int(10) DEFAULT 0 COMMENT '评论数量',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_article_summary_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章数据统计表';

DROP TABLE IF EXISTS `sky_article_label`;

CREATE TABLE IF NOT EXISTS `sky_article_label` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `label_id` bigint(20) NOT NULL COMMENT '标签ID',
  `label_name` varchar(256) NOT NULL COMMENT '标签',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_article_label_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文章数据统计表';

DROP TABLE IF EXISTS `sky_friend_link`;

CREATE TABLE IF NOT EXISTS `sky_friend_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `web_name` VARCHAR(512) NOT NULL COMMENT '网站名称',
  `link` VARCHAR(512) NOT NULL COMMENT '链接',
  `visible` tinyint(1) NOT NULL COMMENT '是否可见，0.不可见，1.可见',
  `priority` int(10) DEFAULT 0 COMMENT '优先级',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '友情链接表';

DROP TABLE IF EXISTS `sky_ip_info`;

CREATE TABLE IF NOT EXISTS `sky_ip_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `province` varchar(128) DEFAULT NULL COMMENT '省份',
  `city` varchar(128) DEFAULT NULL COMMENT '城市',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_ip_info_ip` (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ip信息表';

DROP TABLE IF EXISTS `sky_device_info`;

CREATE TABLE IF NOT EXISTS `sky_device_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `os` varchar(64) DEFAULT NULL COMMENT '系统',
  `browser` varchar(64) DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(64) DEFAULT NULL COMMENT '浏览器版本',
  `device` varchar(64) DEFAULT NULL COMMENT '设备',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设备信息表';

DROP TABLE IF EXISTS `sky_see_log`;

CREATE TABLE IF NOT EXISTS `sky_see_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `url` varchar(256) DEFAULT NULL COMMENT '访问页面',
  `uri` varchar(256) DEFAULT NULL COMMENT '请求',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问记录表';

DROP TABLE IF EXISTS `sky_like_log`;

CREATE TABLE IF NOT EXISTS `sky_like_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(4) DEFAULT NULL COMMENT '类型:1.文章,2.说说,3.照片',
  `aim_id` bigint(20) DEFAULT NULL COMMENT '目标id',
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `url` varchar(256) DEFAULT NULL COMMENT '访问页面',
  `uri` varchar(256) DEFAULT NULL COMMENT '请求',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞记录表';
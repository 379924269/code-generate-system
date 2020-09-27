-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.60-log - MySQL Community Server (GPL)
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for news
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;

-- Dumping structure for table news.config
CREATE TABLE IF NOT EXISTS `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代理ID',
  `ptt_auth_url` varchar(200) DEFAULT NULL COMMENT 'ptt认证服务器地址',
  `news_top_display_count` int(1) DEFAULT '3' COMMENT 'APP置顶新闻显示条数',
  `sync_ptt_user_time` bigint(20) DEFAULT NULL COMMENT '同步ptt用户信息时间，单位：毫秒',
  `cover_rotation_time` int(11) DEFAULT '10' COMMENT '客户端封面轮播时间，单位：秒',
  `show_image` int(1) DEFAULT '0' COMMENT '要闻中置顶显示图片：0：不显示， 1：显示',
  `news_check_flag` int(1) DEFAULT '0' COMMENT '新闻审核标志，1：要审核，0：不需要审核',
  `ptt_server_url` varchar(200) DEFAULT NULL COMMENT 'ptt服务器地址',
  `default_cover_image` varchar(200) DEFAULT '' COMMENT '默认的封面图片，无封面的时候显示',
  `rotation_imgae_display_count` int(2) DEFAULT '6' COMMENT '轮播图片显示数量',
  `title` varchar(50) DEFAULT '' COMMENT '登陆标题',
  `copyright` varchar(100) DEFAULT '' COMMENT '版权',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='配置表';

-- Data exporting was unselected.

-- Dumping structure for table news.manager
CREATE TABLE IF NOT EXISTS `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代理ID',
  `username` varchar(200) NOT NULL COMMENT '用户名称',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `nickname` varchar(200) DEFAULT '' COMMENT '昵称',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `avatar_url` varchar(200) DEFAULT '' COMMENT '头像地址',
  `mobile` varchar(11) DEFAULT '' COMMENT '号码',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_login_time` bigint(20) DEFAULT NULL COMMENT '最后登录时间，单位毫秒',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- Data exporting was unselected.

-- Dumping structure for table news.permission
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代理id',
  `name` varchar(200) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(200) DEFAULT NULL COMMENT '请求访问路径',
  `description` text COMMENT '权限描述',
  `pid` int(1) DEFAULT NULL COMMENT '父权限id',
  `method` varchar(8) DEFAULT NULL COMMENT '请求方法，如get、post等',
  `enable` tinyint(1) unsigned zerofill DEFAULT '1' COMMENT '是否启用，0：禁用， 1：启用',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间，单位：毫秒',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- Data exporting was unselected.

-- Dumping structure for table news.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代理ID',
  `name` varchar(50) DEFAULT NULL COMMENT '管理员类型名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色类型及管理员类型';

-- Data exporting was unselected.

-- Dumping structure for table news.role_permission
CREATE TABLE IF NOT EXISTS `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代理id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `FK_role_permission_role` (`role_id`),
  KEY `FK_role_permission_permission` (`permission_id`),
  CONSTRAINT `FK_role_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_role_permission_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- Data exporting was unselected.

-- Dumping structure for table news.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '代理id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `nickname` varchar(32) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像（头像地址）',
  `secret` varchar(64) NOT NULL COMMENT '密码MD5，保留兼容语音通话',
  `password` varchar(64) NOT NULL COMMENT 'SHA256计算的密码hash值',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱地址，和emm一致，可以用来登录',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码,可以用来登录',
  `created` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间，单位：毫秒',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                        `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(64) DEFAULT '' COMMENT '用户名',
                        `phone` VARCHAR(20) NOT NULL COMMENT '手机号',
                        `password` VARCHAR(20) NOT NULL COMMENT '密码',
                        `email` VARCHAR(60) DEFAULT '' COMMENT '邮箱',
                        `deleted` BIT DEFAULT 0 COMMENT '是否已删除，1 表示已经删除',
                        `create_time` TIMESTAMP COMMENT '创建时间',
                        `create_by` VARCHAR(64) COMMENT '创建人',
                        `update_time` TIMESTAMP COMMENT '更新时间',
                        `update_by` VARCHAR(64) COMMENT '更新人',
                      PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT = 2 CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
                        `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                        `title` VARCHAR(64) DEFAULT '' COMMENT '标题',
                        `description` VARCHAR(200) DEFAULT '' COMMENT '描述',
                        `content` TEXT DEFAULT '' COMMENT '内容',
                        `create_time` TIMESTAMP COMMENT '创建时间',
                        `update_time` TIMESTAMP COMMENT '更新时间',
                        `user_id` BIGINT(20) NOT NULL COMMENT '作者用户ID',
                        PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COMMENT='博客表';

-- ----------------------------
-- Table structure for t_sms_verify
-- ----------------------------
CREATE TABLE `t_sms_verify` (
                        `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                        `phone` CHAR(11) NOT NULL COMMENT '手机号码',
                        `type` tinyint(4) NOT NULL COMMENT '短信类型：1、注册，2、登录；3、找回密码',
                        `receipt_code` VARCHAR(20) NULL COMMENT '验证码回执编号',
                        `verify_code` CHAR(6) NULL COMMENT '短信验证码',
                        `auth_status` tinyint(4) NULL DEFAULT 0 COMMENT '鉴权状态',
                        `create_time` TIMESTAMP NOT NULL COMMENT '验证码创建时间',
                        `platform` VARCHAR(20) NULL COMMENT '平台',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '短信验证码信息';
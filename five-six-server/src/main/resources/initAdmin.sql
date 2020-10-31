use fivesix;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `path` varchar(64) DEFAULT NULL,
      `name` varchar(64) DEFAULT NULL,
      `name_zh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `icon_cls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `component` varchar(64) DEFAULT NULL,
      `parent_id` int(11) DEFAULT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES ('1', '/admin', 'AdminIndex', '首页', 'el-icon-s-home', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('2', '/admin/dashboard', 'DashboardAdmin', '运行情况', null, 'dashboard/admin/index', '1');
INSERT INTO `admin_menu` VALUES ('3', '/admin', 'User', '用户管理', 'el-icon-user', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('4', '/admin', 'Content', '内容管理', 'el-icon-tickets', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('5', '/admin', 'System', '系统配置', 'el-icon-s-tools', 'AdminIndex', '0');
INSERT INTO `admin_menu` VALUES ('6', '/admin/user/profile', 'Profile', '用户信息', null, 'user/UserProfile', '3');
INSERT INTO `admin_menu` VALUES ('7', '/admin/user/role', 'Role', '角色配置', null, 'user/Role', '3');
INSERT INTO `admin_menu` VALUES ('8', '/admin/content/movie', 'MovieManagement', '电影管理', null, 'content/MovieManagement', '4');
INSERT INTO `admin_menu` VALUES ('9', '/admin/content/banner', 'BannerManagement', '广告管理', null, 'content/BannerManagement', '4');
INSERT INTO `admin_menu` VALUES ('10', '/admin/content/article', 'ArticleManagement', '文章管理', null, 'content/ArticleManagement', '4');


-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `name_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES ('1', 'users_management', '用户管理', '/api/admin/user');
INSERT INTO `admin_permission` VALUES ('2', 'roles_management', '角色管理', '/api/admin/role');
INSERT INTO `admin_permission` VALUES ('3', 'content_management', '内容管理', '/api/admin/content');


-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
      `name_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
      `enabled` tinyint(1) DEFAULT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', 'sysAdmin', '系统管理员', '1');
INSERT INTO `admin_role` VALUES ('2', 'contentManager', '内容管理员', '1');
INSERT INTO `admin_role` VALUES ('3', 'visitor', '访客', '1');


-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `rid` int(11) DEFAULT NULL,
   `mid` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES ('1', '2', '9');
INSERT INTO `admin_role_menu` VALUES ('2', '2', '10');
INSERT INTO `admin_role_menu` VALUES ('3', '3', '1');
INSERT INTO `admin_role_menu` VALUES ('4', '3', '2');
INSERT INTO `admin_role_menu` VALUES ('5', '2', '8');
INSERT INTO `admin_role_menu` VALUES ('6', '2', '4');
INSERT INTO `admin_role_menu` VALUES ('7', '1', '1');
INSERT INTO `admin_role_menu` VALUES ('8', '1', '2');
INSERT INTO `admin_role_menu` VALUES ('9', '1', '3');
INSERT INTO `admin_role_menu` VALUES ('10', '1', '6');
INSERT INTO `admin_role_menu` VALUES ('11', '1', '7');
INSERT INTO `admin_role_menu` VALUES ('12', '1', '4');
INSERT INTO `admin_role_menu` VALUES ('13', '1', '8');
INSERT INTO `admin_role_menu` VALUES ('14', '1', '9');
INSERT INTO `admin_role_menu` VALUES ('15', '1', '10');
INSERT INTO `admin_role_menu` VALUES ('16', '1', '5');
INSERT INTO `admin_role_menu` VALUES ('17', '2', '1');
INSERT INTO `admin_role_menu` VALUES ('18', '2', '2');

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission` (
     `id` int(20) NOT NULL AUTO_INCREMENT,
     `rid` int(20) DEFAULT NULL,
     `pid` int(20) DEFAULT NULL,
     PRIMARY KEY (`id`),
     KEY `fk_role_permission_role_1` (`rid`),
     KEY `fk_role_permission_permission_1` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES ('1', '2', '3');
INSERT INTO `admin_role_permission` VALUES ('2', '1', '1');
INSERT INTO `admin_role_permission` VALUES ('3', '1', '2');
INSERT INTO `admin_role_permission` VALUES ('4', '1', '3');


-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `uid` int(11) DEFAULT NULL,
   `rid` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `fk_operator_role_operator_1` (`uid`),
   KEY `fk_operator_role_role_1` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES ('1', '1', '1');
INSERT INTO `admin_user_role` VALUES ('2', '2', '3');
INSERT INTO `admin_user_role` VALUES ('3', '3', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `phone` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `enabled` tinyint(1) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaott', '35b9529f89cfb9b848060ca576237e17', '8O+vDNr2sI3N82BI31fu1A==','12312312312', 'evan_nightly@163.com', '1');
INSERT INTO `user` VALUES ('2', 'visit', '85087738b6c1e1d212683bfafc163853', 'JBba3j5qRykIPJQYTNNH9A==',  '12312312312', '123@123.com', '1');
INSERT INTO `user` VALUES ('3', 'editor', '8583a2d965d6159edbf65c82d871fa3e', 'MZTe7Qwf9QgXBXrZzTIqJQ==',  null, null, '1');
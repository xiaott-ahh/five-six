use fivesix;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `movies`;
DROP TABLE IF EXISTS `category`;

create table `users`(
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` varchar (255) NOT NULL,
    `password` varchar (255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


create table `category` (
    `id` int(11) NOT NULL PRIMARY KEY,
    `type` varchar (255) NOT NULL DEFAULT ''
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;


create table `movies`(
    `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `cover` varchar (255) DEFAULT '',
    `title` varchar (255) NOT NULL DEFAULT '',
    `director` varchar (255) DEFAULT '',
    `language` varchar (255) DEFAULT '',
    `actors` varchar (255) DEFAULT '',
    `nation` varchar (255) DEFAULT '',
    `rate` FLOAT DEFAULT 0,
    `date` varchar(255) DEFAULT '',
    `abs` varchar (255) DEFAULT NULL,
    `cid` int(11) DEFAULT NULL,
    KEY `movie_category_on_cid` (`cid`),
    CONSTRAINT `movie_category_on_cid` FOREIGN KEY (`cid`) REFERENCES `category`(`id`) ON DELETE SET NULL ON UPDATE CASCADE

)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-------
--- records of users
-------

INSERT INTO `users` VALUES (1,'admin','password');

------
--records of category
------
INSERT INTO `category` VALUES (1,'剧情');
INSERT INTO `category` VALUES (2,'爱情');
INSERT INTO `category` VALUES (3,'科幻');
INSERT INTO `category` VALUES (4,'恐怖');
INSERT INTO `category` VALUES (5,'悬疑');
INSERT INTO `category` VALUES (6,'动画');
INSERT INTO `category` VALUES (7,'动作');
INSERT INTO `category` VALUES (8,'喜剧');

-------
--records of movies
-------
INSERT INTO `movies` VALUES (1,'','肖申克的救赎','弗兰克·德拉邦特','英语','蒂姆·罗宾斯 / 摩根·弗里曼 / 鲍勃·冈顿 / 威廉姆·赛德勒','美国',9.7,'1994','',1);
INSERT INTO `movies` VALUES (10,'','喜剧之王','周星驰','粤语','周星驰 / 张柏芝','中国',8.7,'1997','',2);
INSERT INTO `movies` VALUES (13,'','白蛇·缘起','大毛','国语','张喆 / 杨天翔 / 唐小喜 / 刘紫玲 ','中国',7.9,'2019','',6);
INSERT INTO `movies` VALUES (55,'','西游降魔','周星驰','国语','文章 / 舒淇 / 黄渤','中国',7.1,'2013','',8);
INSERT INTO `movies` VALUES (40,'','泰坦尼克号','詹姆斯·卡梅隆','英语','莱昂纳多·迪卡普里奥 / 凯特·温丝莱特','美国',9.4,'1997','',2);
INSERT INTO `movies` VALUES (20,'','怦然心动','罗伯·莱纳','英语','玛德琳·卡罗尔 / 卡兰·麦克奥利菲','美国',9.1,'2010','',2);
INSERT INTO `movies` VALUES (3,'','唐人街探案','陈思诚','国语','刘昊然 / 王宝强','中国',7.6,'2015','',5);
INSERT INTO `movies` VALUES (23,'','看不见的客人','奥里奥尔·保罗','西班牙语','马里奥·卡萨斯 / 阿娜·瓦格纳 / 何塞·科罗纳多','美国',8.8,'2016','',5);

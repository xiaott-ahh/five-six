use fivesix;

/*
DROP TABLE IF EXISTS `movie-category`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `movies`;
DROP TABLE IF EXISTS `categories`;
*/

create table if not exists `categories` (
    `id` int(11) NOT NULL PRIMARY KEY,
    `type` varchar (255) NOT NULL DEFAULT ''
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


create table if not exists `movies`(
    `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `cover` varchar (255) NOT NULL DEFAULT '',
    `title` varchar (255) NOT NULL DEFAULT '',
    `date` varchar (255) NOT NULL DEFAULT '',
    `rate` float DEFAULT 0,
    `director` varchar (255) NOT NULL DEFAULT '',
    `scriptwriter` varchar(255) NOT NULL DEFAULT '',
    `actors` varchar (255) DEFAULT '',
    `district` varchar (255) DEFAULT '',
    `language` varchar (255) DEFAULT '',
    `duration` int(3) DEFAULT 0,
    `abs` varchar (255) DEFAULT NULL,
    UNIQUE (`title`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;


create table if not exists `movie-category` (
    `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `mid` int(11) NOT NULL,
    `cid` int(11) NOT NULL,
    KEY `fk_on_movie_id` (`mid`),
    CONSTRAINT `fk_on_movie_id` FOREIGN KEY (`mid`) REFERENCES `movies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    KEY `fk_on_category_id` (`cid`),
    CONSTRAINT `fk_on_category_id` FOREIGN KEY (`cid`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

--
-- records of users
--
/*
INSERT INTO `users` VALUES (1,'admin','password');

------
--records of category
------
INSERT INTO `categories` VALUES (1,'剧情');
INSERT INTO `categories` VALUES (2,'喜剧');
INSERT INTO `categories` VALUES (3,'动作');
INSERT INTO `categories` VALUES (4,'爱情');
INSERT INTO `categories` VALUES (5,'科幻');
INSERT INTO `categories` VALUES (6,'动画');
INSERT INTO `categories` VALUES (7,'悬疑');
INSERT INTO `categories` VALUES (8,'惊悚');
INSERT INTO `categories` VALUES (9,'恐怖');
INSERT INTO `categories` VALUES (10,'犯罪');
INSERT INTO `categories` VALUES (11,'同性');
INSERT INTO `categories` VALUES (12,'音乐');
INSERT INTO `categories` VALUES (13,'歌舞');
INSERT INTO `categories` VALUES (14,'传记');
INSERT INTO `categories` VALUES (15,'历史');
INSERT INTO `categories` VALUES (16,'战争');
INSERT INTO `categories` VALUES (17,'西部');
INSERT INTO `categories` VALUES (18,'奇幻');
INSERT INTO `categories` VALUES (19,'冒险');
INSERT INTO `categories` VALUES (20,'灾难');
INSERT INTO `categories` VALUES (21,'武侠');
INSERT INTO `categories` VALUES (22,'情色');

-------
--records of movies
-------
INSERT INTO `movies` VALUES (1,'https://img1.doubanio.com/view/photo/raw/public/p2621219978.jpg','姜子牙','2020',6.9,'程腾/李炜','谢茜颖','郑希/杨凝/图特哈蒙/阎么么/季冠霖/姜广涛','中国','汉语普通话',110,'故事发生于封神大战后。姜子牙率领众神伐纣，赢得封神大战胜利，以为可以唤回世间安宁。然而，一切并未结束。一个偶然，姜子牙发现了原来“封神大战”之下酝酿着更大的阴谋。姜子牙开始踏上探寻真相的旅途......');
INSERT INTO `movies` VALUES (2,'https://img9.doubanio.com/view/photo/raw/public/p2563780504.jpg','哪吒之魔童降世','2019',8.4,'饺子','饺子/易巧/魏芸芸','吕艳婷/囧森瑟夫/瀚墨/陈浩/绿绮/张珈铭/杨卫','中国','汉语普通话',110,'天地灵气孕育出一颗能量巨大的混元珠，元始天尊将混元珠提炼成灵珠和魔丸，灵珠投胎为人，助周伐纣时可堪大用；而魔丸则会诞出魔王，为祸人间。元始天尊启动了天劫咒语，3年后天雷将会降临，摧毁魔丸。太乙受命将灵珠托生于陈塘关李靖家的儿子哪吒身上。然而阴差阳错，灵珠和魔丸竟然被掉包。本应是灵珠英雄的哪吒却成了混世大魔王。调皮捣蛋顽劣不堪的哪吒却徒有一颗做英雄的心。然而面对众人对魔丸的误解和即将来临的天雷的降临，哪吒是否命中注定会立地成魔？他将何去何从？');


------
--records of movie-category
------
INSERT INTO `movie-category` VALUES (1,1,1);
INSERT INTO `movie-category` VALUES (2,1,6);
INSERT INTO `movie-category` VALUES (3,1,18);
INSERT INTO `movie-category` VALUES (4,2,1);
INSERT INTO `movie-category` VALUES (5,2,6);
INSERT INTO `movie-category` VALUES (6,2,18);
INSERT INTO `movie-category` VALUES (7,2,2);

 */
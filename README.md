![image](https://img-blog.csdnimg.cn/20201108174935393.png#pic_center)
***
![](https://img.shields.io/badge/framework-Spring-green.svg)  ![](https://img.shields.io/badge/licsense-MIT-orange.svg) ![](https://img.shields.io/badge/release-v1.0.0-blue.svg)
# 项目简介
这是一个前后端分离的电影管理和推荐系统，采用Vue.js + Spring Boot技术栈开发
- 前端项目地址：[前端项目](https://github.com/xiaott-ahh/five-six-vue)
- 后端项目地址：[后端项目](https://github.com/xiaott-ahh/five-six)
# 前台效果
## 主页
主页是网站的门户，进行导航和slogan的展示，提供搜索的功能

![image](https://img-blog.csdnimg.cn/20201108180012940.gif#pic_center)
## 搜索
可以根据关键词进行搜索（导演、演员)，后端目前采用模糊查询的方式返回数据库中的记录

![image](https://img-blog.csdnimg.cn/20201108180440677.gif#pic_center)
## 电影集
该页是电影信息展示和选择界面，仿豆瓣界面设计；可以根据类别、评分、时间进行电影的搜索；目前包含1500+电影信息（也就是爬200部封一次ip吧...）  
爬虫可以参见 [我的豆瓣爬虫](https://github.com/xiaott-ahh/scrapy-douban)

![image](https://img-blog.csdnimg.cn/2020110818093119.gif#pic_center)

## 向我推荐
该界面主要是根据用户的浏览记录和收藏等信息对其进行个性化推荐

![image](https://img-blog.csdnimg.cn/20201108084049948.gif#pic_center)

# 后台效果
后台主要进行用户管理、角色配置以及电影信息管理

![image](https://img-blog.csdnimg.cn/20201108084153284.gif#pic_center)
# 技术架构
![image](https://img-blog.csdnimg.cn/20201108093528383.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDY5Mjc5MQ==,size_16,color_FFFFFF,t_70#pic_center)
注：由于项目目前只是运行在本地，所以没有引入Nginx，部署到服务器中时，可以考虑使用Nginx进行代理
# 主要技术栈
## 前端
- Vue.js
- ElementUI
- axios
## 后端
- Spring Boot
- MyBatis
- Apache Shiro
- Spring Data Redis
## 数据库
- MySQL
- Redis

# 运行
- 后端
1. 克隆项目到本地
```
git clone https://github.com/xiaott-ahh/five-six.git
```
2. 启动Redis服务，默认端口6379，密码为空
3. 创建数据库five-six，初始化脚本在src/main/resources文件夹下，包含电影相关和用户相关两个脚本；数据库初始化由配置类  
DBInitializer完成，切换脚本的路径即可执行不同的脚本，不过电影初始化脚本只包含表结构，是没有数据的，需要结合[爬虫](https://github.com/xiaott-ahh/scrapy-douban)导入
4. 启动FiveSixServerApplication，项目运行在8443端口，端口号、数据库、MyBatis、Redis相关配置在application-dev.yml中
- 前端
1. 克隆项目到本地
```
git clone https://github.com/xiaott-ahh/five-six-vue.git
```
2. 进入项目根目录运行（在Idea中可以右键package.json->show npm script,双击serve，项目默认运行在8080端口  
端口可以在vue.config.js中进行更改，跨域配置也在该文件中）
```
npm run serve
```
3. 前后端都启动服务后，就可以进行交互了，登录用户名：xiaott，密码：123；也可以使用后端中InitAdmin.sql中初始化的其他用户登录，会有不同的角色和访问权限  
注：本项目基于vue-cli3



## 项目简介

novel 是一套基于 Java 技术栈 Spring Boot 3 + Vue 3 开发的前后端分离企业级
小说项目，由小说门户系统、作家后台管理系统、平台后台管理系统等多个子系统构成。包括小说推荐、作品检索、小说排行榜、小说阅读、小说评论、会员中心、作家专区、充值订阅、新闻发布等功能。


## 开发环境

- MySQL 8.0
- Redis 7.0
- Elasticsearch 8.2.0
- RabbitMQ 3.10.2
- JDK 21
- Maven 3.8
- IntelliJ IDEA
- Node 16.14
- Livekit 0.17.0

**注：Elasticsearch、RabbitMQ 和 XXL-JOB 默认关闭，可通过 application.yml 配置文件中相应的`enable`配置属性开启。**

## 后端技术选型

| 技术                  |      版本      | 说明                      | 官网                              |                                                            学习                                                            |
|---------------------|:------------:|-------------------------| ------------------------------------ |:------------------------------------------------------------------------------------------------------------------------:|
| Spring Boot         |    3.3.0     | 容器 + MVC 框架             | [进入](https://spring.io/projects/spring-boot) |                            [进入](https://docs.spring.io/spring-boot/docs/3.0.0/reference/html)                            |
| Spring AI           |    1.0.0-M6     | Spring 官方 AI 框架         | [进入](https://spring.io/projects/spring-ai) |                                                          [进入](https://docs.spring.io/spring-ai/reference/)                                                          |
| MyBatis             |    3.5.9     | ORM 框架                  | [进入](http://www.mybatis.org)               |                                    [进入](https://mybatis.org/mybatis-3/zh/index.html)                                     |
| MyBatis-Plus        |    3.5.3     | MyBatis 增强工具            | [进入](https://baomidou.com/)                |                                         [进入](https://baomidou.com/pages/24112f/)                                         |
| JJWT                |    0.11.5    | JWT 登录支持                | [进入](https://github.com/jwtk/jjwt)         |                                                            -                                                             |
| Lombok              |   1.18.24    | 简化对象封装工具                | [进入](https://github.com/projectlombok/lombok) |                                       [进入](https://projectlombok.org/features/all)                                       |
| Caffeine            |    3.1.0     | 本地缓存支持                  | [进入](https://github.com/ben-manes/caffeine)            |                               [进入](https://github.com/ben-manes/caffeine/wiki/Home-zh-CN)                                |
| Redis               |     7.0      | 分布式缓存支持                 | [进入](https://redis.io)                |                                               [进入](https://redis.io/docs)                                                |
| Redisson            |    3.17.4    | 分布式锁实现                  | [进入](https://github.com/redisson/redisson)               |                            [进入](https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95)                            |
| MySQL               |     8.0      | 数据库服务                   | [进入](https://www.mysql.com)               |                     [进入](https://docs.oracle.com/en-us/iaas/mysql-database/doc/getting-started.html)                     |
| ShardingSphere-JDBC |    5.5.1     | 数据库分库分表支持               | [进入](https://shardingsphere.apache.org)               |                            [进入](https://shardingsphere.apache.org/document/5.1.1/cn/overview)                            |
| Elasticsearch       |    8.2.0     | 搜索引擎服务                  | [进入](https://www.elastic.co)               |                     [进入](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)                     |
| RabbitMQ            |    3.10.2    | 开源消息中间件                 | [进入](https://www.rabbitmq.com)               |                             [进入](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)                              |
| Sentinel            |    1.8.4     | 流量控制组件                  | [进入](https://github.com/alibaba/Sentinel)              |                            [进入](https://github.com/alibaba/Sentinel/wiki/%E4%B8%BB%E9%A1%B5)                             |
| Springdoc-openapi   |    2.0.0     | Swagger 3 接口文档自动生成      | [进入](https://github.com/springdoc/springdoc-openapi)              |                                               [进入](https://springdoc.org/)                                               |
| Spring Boot Admin   |   3.0.0-M1   | 应用管理和监控                 | [进入](https://github.com/codecentric/spring-boot-admin)              |                              [进入](https://codecentric.github.io/spring-boot-admin/3.0.0-M1)                              |
| Tomcat              | 10.1.24      | Spring Boot 默认内嵌 Web 容器 | [进入](https://tomcat.apache.org) |                                       [进入](https://tomcat.apache.org/tomcat-10.1-doc/index.html)                                       |
| Docker              |      -       | 应用容器引擎                  | [进入](https://www.docker.com/)             |                                                            -                                                             |                                                         -                                                             |
| LiveKit              |      -       | 开源 WebRTC 实时音视频服务                  | [进入](https://livekit.com/)             | [进入](https://docs.livekit.io/intro/overview/)       | 



## 编码规范

- 规范方式：严格遵守阿里编码规约。
- 命名统一：简介最大程度上达到了见名知意。
- 分包明确：层级分明可快速定位到代码位置。
- 注释完整：描述性高大量减少了开发人员的代码阅读工作量。
- 工具规范：使用统一jar包避免出现内容冲突。
- 代码整洁：可读性、维护性高。
- 依赖版本：所有依赖均使用当前最新可用版本以便新技术学习。

## 包结构

```
io
 +- github
     +- xxyopen   
        +- novel
            +- NovelApplication.java -- 项目启动类
            |
            +- core -- 项目核心模块，包括各种工具、配置和常量等
            |   +- common -- 业务无关的通用模块
            |   |   +- exception -- 通用异常处理
            |   |   +- constant -- 通用常量   
            |   |   +- req -- 通用请求数据格式封装，例如分页请求数据  
            |   |   +- resp -- 接口响应工具及响应数据格式封装 
            |   |   +- util -- 通用工具   
            |   | 
            |   +- annotation -- 自定义注解类
            |   +- aspect -- Spring AOP 切面
            |   +- auth -- 用户认证授权相关
            |   +- config -- 业务相关配置
            |   +- constant -- 业务相关常量         
            |   +- filter -- 过滤器 
            |   +- interceptor -- 拦截器
            |   +- json -- JSON 相关的包，包括序列化器和反序列化器
            |   +- task -- 定时任务
            |   +- util -- 业务相关工具 
            |   +- wrapper -- 装饰器
            |
            +- dto -- 数据传输对象，包括对各种 Http 请求和响应数据的封装
            |   +- req -- Http 请求数据封装
            |   +- resp -- Http 响应数据封装
            |
            +- dao -- 数据访问层，与底层 MySQL 进行数据交互
            +- manager -- 通用业务处理层，对第三方平台封装、对 Service 层通用能力的下沉以及对多个 DAO 的组合复用 
            +- service -- 相对具体的业务逻辑服务层  
            +- controller -- 主要是处理各种 Http 请求，各类基本参数校验，或者不复用的业务简单处理，返回 JSON 数据等
            |   +- front -- 小说门户相关接口
            |   +- author -- 作家管理后台相关接口
            |   +- admin -- 平台管理后台相关接口
            |   +- app -- app 接口
            |   +- applet -- 小程序接口
            |   +- open -- 开放接口，供第三方调用 
```

## 截图

1. 首页

![img](https://cyyy25100.oss-cn-beijing.aliyuncs.com/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202026-10-31%20173359.png?Expires=1794242603&OSSAccessKeyId=TMP.3KvDGNUSSV4KBpM1DjJ6XsLDre5hNdMm5bVVC2VDNmSRoopzpwpk3r3u7WHTGVpWSoYcCuJWeMhAE4B98uSd82S5gSXndw&Signature=rR62pXIkZMvd7PZRSlbs%2FjdRvPI%3D)

2. 分类索引页

![img](https://oscimg.oschina.net/oscnet/up-d0b2e03129bfae47b8bb96a491b73d383c5.png)

3. 搜索页


![img](https://oscimg.oschina.net/oscnet/up-ed5f689557718924acac76bc3ebca36afcb.png)

4. 排行榜

![img](https://oscimg.oschina.net/oscnet/up-78d5a68586cd92a86c669311f414508f922.png)

5. 详情页

![img](https://oscimg.oschina.net/oscnet/up-8be2495a2869f93626b0c9c1df6f329747a.png)

6. 阅读页

![img](https://oscimg.oschina.net/oscnet/up-517c84148d2db8e11717a8bbecc57fa1be7.png)

7. 用户中心

![img](https://oscimg.oschina.net/oscnet/up-805a30e7a663a3fd5cb39a7ea26bc132a01.png)


9. 作家专区

![img](https://oscimg.oschina.net/oscnet/up-30766372cc7f56480ff1d7d55198204f6ea.png)

10、语音客服

![img](https://cyyy25100.oss-cn-beijing.aliyuncs.com/%E8%AF%AD%E9%9F%B3%E5%AE%A2%E6%9C%8D1.png?Expires=1794242623&OSSAccessKeyId=TMP.3KvDGNUSSV4KBpM1DjJ6XsLDre5hNdMm5bVVC2VDNmSRoopzpwpk3r3u7WHTGVpWSoYcCuJWeMhAE4B98uSd82S5gSXndw&Signature=aguBlvkaIm%2BCgowAonxDccjEwx0%3D)

![img](https://cyyy25100.oss-cn-beijing.aliyuncs.com/%E8%AF%AD%E9%9F%B3%E5%AE%A2%E6%9C%8D2.png?Expires=1794242631&OSSAccessKeyId=TMP.3KvDGNUSSV4KBpM1DjJ6XsLDre5hNdMm5bVVC2VDNmSRoopzpwpk3r3u7WHTGVpWSoYcCuJWeMhAE4B98uSd82S5gSXndw&Signature=FeDwwELKxZIylfzi%2FkACo1PPMxs%3D)

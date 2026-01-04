> 最后更新：2026-01-04 | [返回主目录](../README.md)
# 一、ES详解 - 认知：ElasticSearch基础概念
> 在学习ElasticSearch之前，先简单了解下ES流行度，使用背景，以及相关概念等。
## 1.1 为什么需要学习ElasticSearch
根据<a href='https://db-engines.com/en/ranking'>DB Engine</a>的排名显示，ElasticSearch是最受欢迎的企业级搜索引擎。
下图红色勾选的是我们前面的系列详解的，除此之外你可以看到搜索库ElasticSearch在前十名内：

![1.es-introduce-1-2.png](../../assets/images/06-中间件/elasticsearch/1.es-introduce-1-2.png)

所以为什么要学习ElasticSearch呢？

1、在当前软件行业中，搜索是一个软件系统或平台的基本功能， 学习ElasticSearch就可以为相应的软件打造出良好的搜索体验。

2、其次，ElasticSearch具备非常强的大数据分析能力。虽然Hadoop也可以做大数据分析，但是ElasticSearch的分析能力非常高，具备Hadoop不具备的能力。比如有时候用Hadoop分析一个结果，可能等待的时间比较长。

3、ElasticSearch可以很方便的进行使用，可以将其安装在个人的笔记本电脑，也可以在生产环境中，将其进行水平扩展。

4、国内比较大的互联网公司都在使用，比如小米、滴滴、携程等公司。另外，在腾讯云、阿里云的云平台上，也都有相应的ElasticSearch云产品可以使用。

5、在当今大数据时代，掌握近实时的搜索和分析能力，才能掌握核心竞争力，洞见未来。

## 1.2 什么是ElasticSearch
> ElasticSearch是一款非常强大的、基于Lucene的开源搜索及分析引擎；它是一个实时的分布式搜索分析引擎，它能让你以前所未有的速度和规模，去探索你的数据。
它被用作`全文检索`、`结构化搜索`、`分析`以及这三个功能的组合：

- Wikipedia 使用 Elasticsearch 提供带有高亮片段的全文搜索，还有 search-as-you-type 和 did-you-mean 的建议。
- 卫报 使用 Elasticsearch 将网络社交数据结合到访客日志中，为它的编辑们提供公众对于新文章的实时反馈。
- Stack Overflow 将地理位置查询融入全文检索中去，并且使用 more-like-this 接口去查找相关的问题和回答。
- GitHub 使用 Elasticsearch 对1300亿行代码进行查询。
- ...

除了搜索，结合Kibana、Logstash、Beats开源产品，Elastic Stack（简称ELK）还被广泛运用在大数据近实时分析领域，包括：**日志分析、指标监控、信息安全等**。它可以帮助你**探索海量结构化、非结构化数据，按需创建可视化报表，对监控数据设置报警阈值，通过使用机器学习，自动识别异常状况。**

ElasticSearch是基于Restful WebApi，使用Java语言开发的搜索引擎库类，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。其客户端在Java、C#、PHP、Python等许多语言中都是可用的。

## 1.3 ElasticSearch的由来
> ElasticSearch背后的小故事
许多年前，一个刚结婚的名叫 Shay Banon 的失业开发者，跟着他的妻子去了伦敦，他的妻子在那里学习厨师。 在寻找一个赚钱的工作的时候，为了给他的妻子做一个食谱搜索引擎，他开始使用 Lucene 的一个早期版本。

直接使用 Lucene 是很难的，因此 Shay 开始做一个抽象层，Java 开发者使用它可以很简单的给他们的程序添加搜索功能。 他发布了他的第一个开源项目 Compass。

后来 Shay 获得了一份工作，主要是高性能，分布式环境下的内存数据网格。这个对于高性能，实时，分布式搜索引擎的需求尤为突出， 他决定重写 Compass，把它变为一个独立的服务并取名 Elasticsearch。

第一个公开版本在2010年2月发布，从此以后，Elasticsearch 已经成为了 Github 上最活跃的项目之一，他拥有超过300名 contributors(目前736名 contributors )。 一家公司已经开始围绕 Elasticsearch 提供商业服务，并开发新的特性，但是，Elasticsearch 将永远开源并对所有人可用。

据说，Shay 的妻子还在等着她的食谱搜索引擎…​

## 1.4 为什么不是直接使用Lucene
> ElasticSearch是基于Lucene的，那么为什么不是直接使用Lucene呢？

Lucene 可以说是当下最先进、高性能、全功能的搜索引擎库。

但是 Lucene 仅仅只是一个库。为了充分发挥其功能，你需要使用 Java 并将 Lucene 直接集成到应用程序中。 更糟糕的是，您可能需要获得信息检索学位才能了解其工作原理。Lucene 非常 复杂。

Elasticsearch 也是使用 Java 编写的，它的内部使用 Lucene 做索引与搜索，但是它的目的是使全文检索变得简单，**通过隐藏 Lucene 的复杂性，取而代之的提供一套简单一致的 RESTful API。**

然而，Elasticsearch 不仅仅是 Lucene，并且也不仅仅只是一个全文搜索引擎。 它可以被下面这样准确的形容：

- 一个分布式的实时文档存储，每个字段 可以被索引与搜索
- 一个分布式实时分析搜索引擎
- 能胜任上百个服务节点的扩展，并支持 PB 级别的结构化或者非结构化数据

# 1.5 ElasticSearch的主要功能及应用场景
> 我们在哪些场景下可以使用ES呢？
- 主要功能：

1）海量数据的分布式存储以及集群管理，达到了服务与数据的高可用以及水平扩展；

2）近实时搜索，性能卓越。对结构化、全文、地理位置等类型数据的处理；

3）海量数据的近实时分析（聚合功能）

- 应用场景：

1）网站搜索、垂直搜索、代码搜索；

2）日志管理与分析、安全指标监控、应用性能监控、Web抓取舆情分析；

# 1.6 ElasticSearch的基础概念
> 我们还需对比结构化数据库，看看ES的基础概念，为我们后面学习作铺垫。

- **Near Realtime（NRT） 近实时**。数据提交索引后，立马就可以搜索到。
- **Cluster 集群**，一个集群由一个唯一的名字标识，默认为“elasticsearch”。集群名称非常重要，**具有相同集群名的节点才会组成一个集群**。集群名称可以在配置文件中指定。
- **Node 节点**：存储集群的数据，参与集群的索引和搜索功能。像集群有名字，节点也有自己的名称，默认在启动时会以一个随机的UUID的前七个字符作为节点的名字，你可以为其指定任意的名字。通过集群名在网络中发现同伴组成集群。一个节点也可是集群。
- **Index 索引**: 一个索引是一个文档的集合（等同于solr中的集合）。每个索引有唯一的名字，通过这个名字来操作它。一个集群中可以有任意多个索引。
- **Type 类型**：指在一个索引中，可以索引不同类型的文档，如用户数据、博客数据。从6.0.0 版本起已废弃，一个索引中只存放一类数据。
- **Document 文档**：被索引的一条数据，索引的基本信息单元，以JSON格式来表示。
- **Shard 分片**：在创建一个索引时可以指定分成多少个分片来存储。每个分片本身也是一个功能完善且独立的“索引”，可以被放置在集群的任意节点上。
- **Replication 备份**: 一个分片可以有多个备份（副本）
为了方便理解，作一个ES和数据库的对比

![2.es-introduce-1-3.png](../../assets/images/06-中间件/elasticsearch/2.es-introduce-1-3.png)

# 二、ES详解 - 认知：Elastic Stack生态和场景方案
> 在了解ElaticSearch之后，我们还要了解Elastic背后的生态即我们常说的ELK；与此同时，还会给你展示ElasticSearch的案例场景，让你在学习ES前对它有个全局的印象。
## 2.1 Elastic Stack生态
> Beats + Logstash + ElasticSearch + Kibana

如下是我从官方博客中找到图，这张图展示了ELK生态以及基于ELK的场景（最上方）

![3.es-introduce-1-1.png](../../assets/images/06-中间件/elasticsearch/3.es-introduce-1-1.png)

由于Elastic X-Pack是面向收费的，所以我们不妨也把X-Pack放进去，看看哪些是由X-Pack带来的，在阅读官网文档时将方便你甄别重点：

![4.es-introduce-1-0.png](../../assets/images/06-中间件/elasticsearch/4.es-introduce-1-0.png)

### 2.1.1 Beats
Beats是一个面向**轻量型采集器**的平台，这些采集器可以从边缘机器向Logstash、ElasticSearch发送数据，它是由Go语言进行开发的，运行效率方面比较快。从下图中可以看出，不同Beats的套件是针对不同的数据源。

![5.es-introduce-2-0.png](../../assets/images/06-中间件/elasticsearch/5.es-introduce-2-0.png)

### 2.1.2 Logstash
Logstash是**动态数据收集管道**，拥有可扩展的插件生态系统，支持从不同来源采集数据，转换数据，并将数据发送到不同的存储库中。其能够与ElasticSearch产生强大的协同作用，后被Elastic公司在2013年收购。

它具有如下特性：

1）实时解析和转换数据；

2）可扩展，具有200多个插件；

3）可靠性、安全性。Logstash会通过持久化队列来保证至少将运行中的事件送达一次，同时将数据进行传输加密；

4）监控；

### 2.1.3 ElasticSearch
ElasticSearch对数据进行**搜索、分析和存储**，其是基于JSON的分布式搜索和分析引擎，专门为实现水平可扩展性、高可靠性和管理便捷性而设计的。

它的实现原理主要分为以下几个步骤：

1）首先用户将数据提交到ElasticSearch数据库中；

2）再通过分词控制器将对应的语句分词；

3）将分词结果及其权重一并存入，以备用户在搜索数据时，根据权重将结果排名和打分，将返回结果呈现给用户；

### 2.1.4 Kibana
Kibana实现**数据可视化**，其作用就是在ElasticSearch中进行民航。Kibana能够以图表的形式呈现数据，并且具有可扩展的用户界面，可以全方位的配置和管理ElasticSearch。

Kibana最早的时候是基于Logstash创建的工具，后被Elastic公司在2013年收购。

1）Kibana可以提供各种可视化的图表；

2）可以通过机器学习的技术，对异常情况进行检测，用于提前发现可疑问题；

## 2.2 从日志收集系统看ES Stack的发展
> 我们看下ELK技术栈的演化，通常体现在日志收集系统中。
一个典型的日志系统包括：

（1）收集：能够采集多种来源的日志数据

（2）传输：能够稳定的把日志数据解析过滤并传输到存储系统

（3）存储：存储日志数据

（4）分析：支持 UI 分析

（5）警告：能够提供错误报告，监控机制

### 2.2.1 beats+elasticsearch+kibana
Beats采集数据后，存储在ES中，有Kibana可视化的展示。

![6.es-introduce-2-1.png](../../assets/images/06-中间件/elasticsearch/6.es-introduce-2-1.png)

### 2.2.2 beats+logstath+elasticsearch+kibana

![7.es-introduce-2-2.png](../../assets/images/06-中间件/elasticsearch/7.es-introduce-2-2.png)

该框架是在上面的框架的基础上引入了logstash，引入logstash带来的好处如下：

（1）Logstash具有基于磁盘的自适应缓冲系统，该系统将吸收传入的吞吐量，从而减轻背压。

（2）从其他数据源（例如数据库，S3或消息传递队列）中提取。

（3）将数据发送到多个目的地，例如S3，HDFS或写入文件。

（4）使用条件数据流逻辑组成更复杂的处理管道。

**beats结合logstash带来的优势：**

（1）水平可扩展性，高可用性和可变负载处理：beats和logstash可以实现节点之间的负载均衡，多个logstash可以实现logstash的高可用

（2）消息持久性与至少一次交付保证：使用beats或Winlogbeat进行日志收集时，可以保证至少一次交付。从Filebeat或Winlogbeat到Logstash以及从Logstash到Elasticsearch的两种通信协议都是同步的，并且支持确认。Logstash持久队列提供跨节点故障的保护。对于Logstash中的磁盘级弹性，确保磁盘冗余非常重要。

（3）具有身份验证和有线加密的端到端安全传输：从Beats到Logstash以及从 Logstash到Elasticsearch的传输都可以使用加密方式传递 。与Elasticsearch进行通讯时，有很多安全选项，包括基本身份验证，TLS，PKI，LDAP，AD和其他自定义领域

**增加更多的数据源** 比如：TCP，UDP和HTTP协议是将数据输入Logstash的常用方法

![8.es-introduce-2-3.png](../../assets/images/06-中间件/elasticsearch/8.es-introduce-2-3.png)

### 2.2.3 beats+MQ+logstash+elasticsearch+kibana

![9.es-introduce-2-4.png](../../assets/images/06-中间件/elasticsearch/9.es-introduce-2-4.png)

在如上的基础上我们可以在beats和logstash中间添加一些组件redis、kafka、RabbitMQ等，添加中间件将会有如下好处：

（1）降低对日志所在机器的影响，这些机器上一般都部署着反向代理或应用服务，本身负载就很重了，所以尽可能的在这些机器上少做事；

（2）如果有很多台机器需要做日志收集，那么让每台机器都向Elasticsearch持续写入数据，必然会对Elasticsearch造成压力，因此需要对数据进行缓冲，同时，这样的缓冲也可以一定程度的保护数据不丢失；

（3）将日志数据的格式化与处理放到Indexer中统一做，可以在一处修改代码、部署，避免需要到多台机器上去修改配置；

## 2.3 Elastic Stack最佳实践
> 我们再看下官方开发成员分享的最佳实践。
### 2.3.1 日志收集系统
（PS：就是我们上面阐述的）

基本的日志系统

![10.es-introduce-2-5.png](../../assets/images/06-中间件/elasticsearch/10.es-introduce-2-5.png)

增加数据源，和使用MQ

![11.es-introduce-2-6.png](../../assets/images/06-中间件/elasticsearch/11.es-introduce-2-6.png)

### 2.3.2 Metric收集和APM性能监控

![12.es-introduce-2-7.png](../../assets/images/06-中间件/elasticsearch/12.es-introduce-2-7.png)

### 2.3.3 多数据中心方案
通过冗余实现数据高可用

![13.es-introduce-2-8.png](../../assets/images/06-中间件/elasticsearch/13.es-introduce-2-8.png)

两个数据采集中心（比如采集两个工厂的数据），采集数据后的汇聚

![14.es-introduce-2-9.png](../../assets/images/06-中间件/elasticsearch/14.es-introduce-2-9.png)

数据分散，跨集群的搜索

![15.es-introduce-2-10.png](../../assets/images/06-中间件/elasticsearch/15.es-introduce-2-10.png)

# 三、ES详解 - 安装：ElasticSearch和Kibana安装

> 了解完ElasticSearch基础和Elastic Stack生态后，我们便可以开始学习使用ElastiSearch了。所以本文主要介绍ElasticSearch和Kibana的安装。

## 3.1 官网相关教程
> 安装ElasticSearch还是先要看下官方网站。
<a href='https://www.elastic.co/cn/'>官方网站</a>

<a href='https://www.elastic.co/guide/cn/elasticsearch/guide/current/running-elasticsearch.html'>官方2.x中文教程中安装教程</a>

<a href='https://www.elastic.co/cn/downloads/elasticsearch'>官方ElasticSearch下载地址</a>

<a href='https://www.elastic.co/cn/downloads/kibana'>官方Kibana下载地址</a>

本系列教程基于ElasticSearch 7.x版本。

## 3.2 安装ElasticSearch
> ElasticSearch 是基于Java平台的，所以先要安装Java
- 平台确认

这里我准备了一台Centos7虚拟机, 为方便选择后续安装的版本，所以需要看下系统版本信息。
```sh
[root@pdai-centos ~]# uname -a
Linux pdai-centos 3.10.0-862.el7.x86_64 #1 SMP Fri Apr 20 16:44:24 UTC 2018 x86_64 x86_64 x86_64 GNU/Linux
```
- 安装Java

安装 Elasticsearch 之前，你需要先安装一个较新的版本的 Java，最好的选择是，你可以从 <a href='https://www.java.com/zh-CN/'>www.java.com</a>获得官方提供的最新版本的 Java。安装以后，确认是否安装成功：
```sh
[root@pdai-centos ~]# java --version
openjdk 14.0.2 2020-07-14
OpenJDK Runtime Environment 20.3 (slowdebug build 14.0.2+12)
OpenJDK 64-Bit Server VM 20.3 (slowdebug build 14.0.2+12, mixed mode, sharing)
```
- 下载ElasticSearch

从<a href='https://www.elastic.co/cn/downloads/elasticsearch'>这里</a>下载ElasticSearch

比如可以通过curl下载
```sh
[root@pdai-centos opt]# curl -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.12.0-linux-x86_64.tar.gz
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
```
- 解压
```sh
[root@pdai-centos opt]# tar zxvf /opt/elasticsearch-7.12.0-linux-x86_64.tar.gz 
...
[root@pdai-centos opt]# ll | grep elasticsearch
drwxr-xr-x  9 root root      4096 Mar 18 14:21 elasticsearch-7.12.0
-rw-r--r--  1 root root 327497331 Apr  5 21:05 elasticsearch-7.12.0-linux-x86_64.tar.gz
```
- 增加elasticSearch用户

必须创建一个非root用户来运行ElasticSearch(ElasticSearch5及以上版本，基于安全考虑，强制规定不能以root身份运行。)

如果你使用root用户来启动ElasticSearch，则会有如下错误信息：
```sh
[root@pdai-centos opt]# cd elasticsearch-7.12.0/
[root@pdai-centos elasticsearch-7.12.0]# ./bin/elasticsearch
[2021-04-05T21:36:46,510][ERROR][o.e.b.ElasticsearchUncaughtExceptionHandler] [pdai-centos] uncaught exception in thread [main]
org.elasticsearch.bootstrap.StartupException: java.lang.RuntimeException: can not run elasticsearch as root
        at org.elasticsearch.bootstrap.Elasticsearch.init(Elasticsearch.java:163) ~[elasticsearch-7.12.0.jar:7.12.0]
        at org.elasticsearch.bootstrap.Elasticsearch.execute(Elasticsearch.java:150) ~[elasticsearch-7.12.0.jar:7.12.0]
        at org.elasticsearch.cli.EnvironmentAwareCommand.execute(EnvironmentAwareCommand.java:75) ~[elasticsearch-7.12.0.jar:7.12.0]
        at org.elasticsearch.cli.Command.mainWithoutErrorHandling(Command.java:116) ~[elasticsearch-cli-7.12.0.jar:7.12.0]
        at org.elasticsearch.cli.Command.main(Command.java:79) ~[elasticsearch-cli-7.12.0.jar:7.12.0]
        at org.elasticsearch.bootstrap.Elasticsearch.main(Elasticsearch.java:115) ~[elasticsearch-7.12.0.jar:7.12.0]
        at org.elasticsearch.bootstrap.Elasticsearch.main(Elasticsearch.java:81) ~[elasticsearch-7.12.0.jar:7.12.0]
Caused by: java.lang.RuntimeException: can not run elasticsearch as root
        at org.elasticsearch.bootstrap.Bootstrap.initializeNatives(Bootstrap.java:101) ~[elasticsearch-7.12.0.jar:7.12.0]
        at org.elasticsearch.bootstrap.Bootstrap.setup(Bootstrap.java:168) ~[elasticsearch-7.12.0.jar:7.12.0]
        at org.elasticsearch.bootstrap.Bootstrap.init(Bootstrap.java:397) ~[elasticsearch-7.12.0.jar:7.12.0]
        at org.elasticsearch.bootstrap.Elasticsearch.init(Elasticsearch.java:159) ~[elasticsearch-7.12.0.jar:7.12.0]
        ... 6 more
uncaught exception in thread [main]
java.lang.RuntimeException: can not run elasticsearch as root
        at org.elasticsearch.bootstrap.Bootstrap.initializeNatives(Bootstrap.java:101)
        at org.elasticsearch.bootstrap.Bootstrap.setup(Bootstrap.java:168)
        at org.elasticsearch.bootstrap.Bootstrap.init(Bootstrap.java:397)
        at org.elasticsearch.bootstrap.Elasticsearch.init(Elasticsearch.java:159)
        at org.elasticsearch.bootstrap.Elasticsearch.execute(Elasticsearch.java:150)
        at org.elasticsearch.cli.EnvironmentAwareCommand.execute(EnvironmentAwareCommand.java:75)
        at org.elasticsearch.cli.Command.mainWithoutErrorHandling(Command.java:116)
        at org.elasticsearch.cli.Command.main(Command.java:79)
        at org.elasticsearch.bootstrap.Elasticsearch.main(Elasticsearch.java:115)
        at org.elasticsearch.bootstrap.Elasticsearch.main(Elasticsearch.java:81)
For complete error details, refer to the log at /opt/elasticsearch-7.12.0/logs/elasticsearch.log
2021-04-05 13:36:46,979269 UTC [8846] INFO  Main.cc@106 Parent process died - ML controller exiting
```
以我们增加一个独立的elasticsearch用户来运行
```sh
# 增加elasticsearch用户
[root@pdai-centos elasticsearch-7.12.0]# useradd elasticsearch
[root@pdai-centos elasticsearch-7.12.0]# passwd elasticsearch
Changing password for user elasticsearch.
New password: 
BAD PASSWORD: The password contains the user name in some form
Retype new password: 
passwd: all authentication tokens updated successfully.

# 修改目录权限至新增的elasticsearch用户
[root@pdai-centos elasticsearch-7.12.0]# chown -R elasticsearch /opt/elasticsearch-7.12.0
# 增加data和log存放区，并赋予elasticsearch用户权限
[root@pdai-centos elasticsearch-7.12.0]# mkdir -p /data/es
[root@pdai-centos elasticsearch-7.12.0]# chown -R elasticsearch /data/es
[root@pdai-centos elasticsearch-7.12.0]# mkdir -p /var/log/es
[root@pdai-centos elasticsearch-7.12.0]# chown -R elasticsearch /var/log/es
```
然后修改上述的data和log路径，`vi /opt/elasticsearch-7.12.0/config/elasticsearch.yml`

```sh
# ----------------------------------- Paths ------------------------------------
#
# Path to directory where to store the data (separate multiple locations by comma):
#
path.data: /data/es
#
# Path to log files:
#
path.logs: /var/log/es
```
- **修改Linux系统的限制配置**

1. 修改系统中允许应用最多创建多少文件等的限制权限。Linux默认来说，一般限制应用最多创建的文件是65535个。但是ES至少需要65536的文件创建权限。
2. 修改系统中允许用户启动的进程开启多少个线程。默认的Linux限制root用户开启的进程可以开启任意数量的线程，其他用户开启的进程可以开启1024个线程。必须修改限制数为4096+。因为ES至少需要4096的线程池预备。ES在5.x版本之后，强制要求在linux中不能使用root用户启动ES进程。所以必须使用其他用户启动ES进程才可以。
3. Linux低版本内核为线程分配的内存是128K。4.x版本的内核分配的内存更大。如果虚拟机的内存是1G，最多只能开启3000+个线程数。至少为虚拟机分配1.5G以上的内存。

修改如下配置
```sh
[root@pdai-centos elasticsearch-7.12.0]# vi /etc/security/limits.conf

elasticsearch soft nofile 65536
elasticsearch hard nofile 65536
elasticsearch soft nproc 4096
elasticsearch hard nproc 4096
```
/etc/security/limits.conf` 这个文件配置的不是整个Linux系统的**全局总上限**，而是**针对每个用户或用户组**可以使用的资源上限。

所以，配置 `elasticsearch soft nofile 65536` 的意思是：
*   **用户**：`elasticsearch`
*   **限制类型**：`soft`（软限制，超过会警告，普通用户可自己临时提高，但不能超过硬限制）和 `hard`（硬限制，绝对上限）
*   **资源**：`nofile`（Number of Open Files，包括进程打开的文件、套接字等）
*   **值**：`65536`
*   **整体含义**：允许系统中名为 `elasticsearch` 的这个用户运行的进程（比如ES进程），最多能同时打开65536个文件描述符。


- **启动ElasticSearch**
```sh
[root@pdai-centos elasticsearch-7.12.0]# su elasticsearch
[elasticsearch@pdai-centos elasticsearch-7.12.0]$ ./bin/elasticsearch -d
[2021-04-05T22:03:38,332][INFO ][o.e.n.Node               ] [pdai-centos] version[7.12.0], pid[13197], build[default/tar/78722783c38caa25a70982b5b042074cde5d3b3a/2021-03-18T06:17:15.410153305Z], OS[Linux/3.10.0-862.el7.x86_64/amd64], JVM[AdoptOpenJDK/OpenJDK 64-Bit Server VM/15.0.1/15.0.1+9]
[2021-04-05T22:03:38,348][INFO ][o.e.n.Node               ] [pdai-centos] JVM home [/opt/elasticsearch-7.12.0/jdk], using bundled JDK [true]
[2021-04-05T22:03:38,348][INFO ][o.e.n.Node               ] [pdai-centos] JVM arguments [-Xshare:auto, -Des.networkaddress.cache.ttl=60, -Des.networkaddress.cache.negative.ttl=10, -XX:+AlwaysPreTouch, -Xss1m, -Djava.awt.headless=true, -Dfile.encoding=UTF-8, -Djna.nosys=true, -XX:-OmitStackTraceInFastThrow, -XX:+ShowCodeDetailsInExceptionMessages, -Dio.netty.noUnsafe=true, -Dio.netty.noKeySetOptimization=true, -Dio.netty.recycler.maxCapacityPerThread=0, -Dio.netty.allocator.numDirectArenas=0, -Dlog4j.shutdownHookEnabled=false, -Dlog4j2.disable.jmx=true, -Djava.locale.providers=SPI,COMPAT, --add-opens=java.base/java.io=ALL-UNNAMED, -XX:+UseG1GC, -Djava.io.tmpdir=/tmp/elasticsearch-17264135248464897093, -XX:+HeapDumpOnOutOfMemoryError, -XX:HeapDumpPath=data, -XX:ErrorFile=logs/hs_err_pid%p.log, -Xlog:gc*,gc+age=trace,safepoint:file=logs/gc.log:utctime,pid,tags:filecount=32,filesize=64m, -Xms1894m, -Xmx1894m, -XX:MaxDirectMemorySize=993001472, -XX:G1HeapRegionSize=4m, -XX:InitiatingHeapOccupancyPercent=30, -XX:G1ReservePercent=15, -Des.path.home=/opt/elasticsearch-7.12.0, -Des.path.conf=/opt/elasticsearch-7.12.0/config, -Des.distribution.flavor=default, -Des.distribution.type=tar, -Des.bundled_jdk=true]
```
- **查看安装是否成功**
```sh
[root@pdai-centos ~]# netstat -ntlp | grep 9200
tcp6       0      0 127.0.0.1:9200          :::*                    LISTEN      13549/java          
tcp6       0      0 ::1:9200                :::*                    LISTEN      13549/java          
[root@pdai-centos ~]# curl 127.0.0.1:9200
{
  "name" : "pdai-centos",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "ihttW8b2TfWSkwf_YgPH2Q",
  "version" : {
    "number" : "7.12.0",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "78722783c38caa25a70982b5b042074cde5d3b3a",
    "build_date" : "2021-03-18T06:17:15.410153305Z",
    "build_snapshot" : false,
    "lucene_version" : "8.8.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```
## 3.3 安装Kibana
> Kibana是界面化的查询数据的工具，下载时尽量下载与ElasicSearch一致的版本。
- **下载Kibana**

从<a href='https://www.elastic.co/cn/downloads/kibana'>这里</a>下载Kibana

- **解压**
```sh
[root@pdai-centos opt]# tar -vxzf kibana-7.12.0-linux-x86_64.tar.gz
```
- **使用elasticsearch用户权限**
```sh
[root@pdai-centos opt]# chown -R elasticsearch /opt/kibana-7.12.0-linux-x86_64
#配置Kibana的远程访问
[root@pdai-centos opt]# vi /opt/kibana-7.12.0-linux-x86_64/config/kibana.yml
server.host: 0.0.0.0
```
- **启动**

需要切换至elasticsearch用户
```sh
[root@pdai-centos opt]# su elasticsearch
[elasticsearch@pdai-centos opt]$ cd /opt/kibana-7.12.0-linux-x86_64/
[elasticsearch@pdai-centos kibana-7.12.0-linux-x86_64]$ ./bin/kibana
  log   [22:30:22.185] [info][plugins-service] Plugin "osquery" is disabled.
  log   [22:30:22.283] [warning][config][deprecation] Config key [monitoring.cluster_alerts.email_notifications.email_address] will be required for email notifications to work in 8.0."
  log   [22:30:22.482] [info][plugins-system] Setting up [100] plugins: [taskManager,licensing,globalSearch,globalSearchProviders,banners,code,usageCollection,xpackLegacy,telemetryCollectionManager,telemetry,telemetryCollectionXpack,kibanaUsageCollection,securityOss,share,newsfeed,mapsLegacy,kibanaLegacy,translations,legacyExport,embeddable,uiActionsEnhanced,expressions,charts,esUiShared,bfetch,data,home,observability,console,consoleExtensions,apmOss,searchprofiler,painlessLab,grokdebugger,management,indexPatternManagement,advancedSettings,fileUpload,savedObjects,visualizations,visTypeVislib,visTypeVega,visTypeTimelion,features,licenseManagement,watcher,canvas,visTypeTagcloud,visTypeTable,visTypeMetric,visTypeMarkdown,tileMap,regionMap,visTypeXy,graph,timelion,dashboard,dashboardEnhanced,visualize,visTypeTimeseries,inputControlVis,discover,discoverEnhanced,savedObjectsManagement,spaces,security,savedObjectsTagging,maps,lens,reporting,lists,encryptedSavedObjects,dashboardMode,dataEnhanced,cloud,upgradeAssistant,snapshotRestore,fleet,indexManagement,rollup,remoteClusters,crossClusterReplication,indexLifecycleManagement,enterpriseSearch,beatsManagement,transform,ingestPipelines,eventLog,actions,alerts,triggersActionsUi,stackAlerts,ml,securitySolution,case,infra,monitoring,logstash,apm,uptime]
  log   [22:30:22.483] [info][plugins][taskManager] TaskManager is identified by the Kibana UUID: xxxxxx
  ...
```
如果是后台启动：
```sh
[elasticsearch@pdai-centos kibana-7.12.0-linux-x86_64]$ nohup ./bin/kibana &
```
- **界面访问**

![16.es-install-1.png](../../assets/images/06-中间件/elasticsearch/16.es-install-1.png)

可以导入simple data

![17.es-install-2.png](../../assets/images/06-中间件/elasticsearch/17.es-install-2.png)

- **查看数据**

![18.es-install-3.png](../../assets/images/06-中间件/elasticsearch/18.es-install-3.png)

## 3.4 配置密码访问
> 使用基本许可证时，默认情况下禁用Elasticsearch安全功能。由于我测试环境是放在公网上的，所以需要设置下密码访问。相关文档可以参考<a href='https://www.elastic.co/guide/en/elasticsearch/reference/7.12/security-minimal-setup.html'>这里</a>
1. 停止kibana和elasticsearch服务
2. 将`xpack.security.enabled`设置添加到`ES_PATH_CONF/elasticsearch.yml`文件并将值设置为true
3. 启动elasticsearch (`./bin/elasticsearch -d`)
4. 执行如下密码设置器，`./bin/elasticsearch-setup-passwords interactive`
5. 来设置各个组件的密码
将elasticsearch.username设置添加到`KIB_PATH_CONF/kibana.yml` 文件并将值设置给elastic用户： `elasticsearch.username: "elastic"`
6. 创建kibana keystore, `./bin/kibana-keystore create`
7. 在kibana keystore 中添加密码 `./bin/kibana-keystore add elasticsearch.password`
8. 重启kibana 服务即可 `nohup ./bin/kibana &`

然后就可以使用密码登录了：

![19.es-install-4.png](../../assets/images/06-中间件/elasticsearch/19.es-install-4.png)

## 3.5 针对密码设置的一些补充

#### 3.5.1. 这是给ES设置密码吗？
**是的，但不仅限于ES**。`elasticsearch-setup-passwords`命令会为整个Elastic Stack的多个内置用户设置密码，包括：
- **elastic**：超级管理员，拥有所有权限
- **kibana_system**：Kibana用于连接ES的系统用户（注意：不是`kibana`，而是`kibana_system`）
- **logstash_system**：Logstash系统用户
- **beats_system**：Beats系统用户
- **apm_system**：APM系统用户
- **remote_monitoring_user**：远程监控用户

#### 3.5.2. Kibana的密码和ES的密码一样吗？

这是最重要的概念：
- **ES密码**：指的是Elasticsearch集群中各个用户的密码
- **Kibana密码**：Kibana本身**没有自己的密码**，它只是使用某个ES用户的凭据来连接ES

### 3.5.3 密码流程详解

#### 3.5.3.1 流程示意
```
用户浏览器 → Kibana界面 → (输入elastic用户名+ES密码) → 去ES验证 → 验证通过 → 登录Kibana
```

#### 3.5.3.2 具体配置对应关系
在您的步骤中：
1. **步骤4**：为ES的`elastic`用户设置了密码（比如设为`es123456`）
2. **步骤5**：告诉Kibana使用`elastic`用户连接ES
3. **步骤7**：告诉Kibana`elastic`用户的密码是什么（就是步骤4设置的`es123456`）

### 3.5.4 为什么这样做？

#### 3.5.4.1 安全性架构
```
┌─────────────────┐    ┌─────────────────┐
│    Kibana       │    │   Elasticsearch │
│                 │    │                 │
│ 本身无密码验证    │────▶│ 有完整的用户系统  │
│ 只是一个客户端    │    │                 │
│ 使用ES用户认证    │    │ elastic:密码A   │
└─────────────────┘    │ kibana_system:密码B│
                       └─────────────────┘
```

#### 3.5.4.2 两个关键用户的作用
1. **elastic用户**：用于**人在Kibana界面上登录**
2. **kibana_system用户**：用于**Kibana服务后台连接ES**（在更复杂的生产配置中会用到）

### 3.5.5 正确配置示例

#### 3.5.5.1. 设置ES密码（步骤4）
```bash
$ ./bin/elasticsearch-setup-passwords interactive

# 设置过程中会提示为各个用户设置密码：
Enter password for [elastic]: ********  <-- 设置elastic用户的密码（如：MyEsPwd123!）
Reenter password for [elastic]: ********
Enter password for [kibana_system]: ********  <-- Kibana系统用户的密码
Reenter password for [kibana_system]: ********
# ... 其他用户
```

#### 3.5.5.2. 配置Kibana连接ES（您的步骤5-7）
```yaml
# kibana.yml中配置
elasticsearch.username: "elastic"
# 注意：密码不直接写在yml中，而是存在keystore里
```

```bash
# 将elastic用户的密码存入Kibana keystore
$ ./bin/kibana-keystore add elasticsearch.password
# 提示输入时，输入：MyEsPwd123!(密码必须和es设置的相同，否则验证失败)
```

### 3.5.6 重要提醒

#### 3.5.6.1 常见误区
1. **错误理解**：Kibana有一个单独的"kibana密码"
2. **正确理解**：Kibana使用ES的某个用户（如`elastic`）的密码来连接ES

#### 3.5.6.2 生产环境建议
1. **不要使用elastic用户连接Kibana服务**：应该用`kibana_system`用户
2. **人在Kibana界面上登录时再用elastic用户**
3. **为不同团队成员创建不同用户**，而不是共用elastic用户

### 3.5.7 总结
- **ES密码**：Elasticsearch集群中各用户的密码
- **Kibana密码**：Kibana**本身没有密码**，它使用ES用户的密码来连接ES
- **您在Kibana界面登录时**：输入的是**ES的elastic用户**的密码
- **配置关系**：Kibana服务后台连接ES时，也需要知道某个ES用户的密码（通过keystore存储）

# 四、ES详解 - 入门：查询和聚合的基础使用

> 安装完ElasticSearch 和 Kibana后，为了快速上手，我们通过官网GitHub提供的一个数据进行入门学习，主要包括**查询数据**和**聚合数据**。

## 4.1 入门：从索引文档开始
- **索引一个文档**
```sh
PUT /customer/_doc/1
{
  "name": "John Doe"
}
```
为了方便测试，我们使用kibana的dev tool来进行学习测试：

![20.es-usage-1.png](../../assets/images/06-中间件/elasticsearch/20.es-usage-1.png)

- **查询刚才插入的文档**
```sh
GET /customer/_doc/1
```
![21.es-usage-2.png](../../assets/images/06-中间件/elasticsearch/21.es-usage-2.png)


## 4.2 学习准备：批量索引文档
> ES 还提供了批量操作，比如这里我们可以使用批量操作来插入一些数据，供我们在后面学习使用。

使用批量来批处理文档操作比单独提交请求要快得多，因为它减少了网络往返。

- **下载测试数据**

数据是index为bank，accounts.json <a href='https://github.com/elastic/elasticsearch/blob/v6.8.18/docs/src/test/resources/accounts.json'>下载地址</a>（如果你无法下载，也可以clone ES的官方仓库，选择本文中使用的版本分支，然后进入/docs/src/test/resources/accounts.json目录获取）

数据的格式如下
```json
{
  "account_number": 0,
  "balance": 16623,
  "firstname": "Bradshaw",
  "lastname": "Mckenzie",
  "age": 29,
  "gender": "F",
  "address": "244 Columbus Place",
  "employer": "Euron",
  "email": "bradshawmckenzie@euron.com",
  "city": "Hobucken",
  "state": "CO"
}
```
- **批量插入数据**

将accounts.json拷贝至指定目录，我这里放在/opt/下面,

然后执行
```
curl -H "Content-Type: application/json" -XPOST "localhost:9200/bank/_bulk?pretty&refresh" --data-binary "@/opt/accounts.json"
```
- **查看状态**
```sh
[elasticsearch@pdai-centos root]$ curl "localhost:9200/_cat/indices?v=true" | grep bank
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1524  100  1524    0     0   119k      0 --:--:-- --:--:-- --:--:--  124k
yellow open   bank                            yq3eSlAWRMO2Td0Sl769rQ   1   1       1000            0    379.2kb        379.2kb
[elasticsearch@pdai-centos root]$
```
## 4.3 查询数据
> 我们通过kibana来进行查询测试。
### 4.3.1 查询所有

match_all表示查询所有的数据，sort即按照什么字段排序
```sh
GET /bank/_search
{
  "query": { "match_all": {} },
  "sort": [
    { "account_number": "asc" }
  ]
}
```
- **结果**

![22.es-usage-3.png](../../assets/images/06-中间件/elasticsearch/22.es-usage-3.png)

- **相关字段解释**

  - `took` – Elasticsearch运行查询所花费的时间（以毫秒为单位）
  - `timed_out` –搜索请求是否超时
  - `_shards`- 搜索了多少个碎片，以及成功，失败或跳过了多少个碎片的细目分类。
  - `max_score` – 找到的最相关文档的分数
  - `hits.total.value` - 找到了多少个匹配的文档
  - `hits.sort` - 文档的排序位置（不按相关性得分排序时）
  - `hits._score` - 文档的相关性得分（使用match_all时不适用）
### 4.3.2 分页查询(from+size)
本质上就是from和size两个字段
```sh
GET /bank/_search
{
  "query": { "match_all": {} },
  "sort": [
    { "account_number": "asc" }
  ],
  "from": 10,
  "size": 10
}
```
- **结果**

![23.es-usage-4.png](../../assets/images/06-中间件/elasticsearch/23.es-usage-4.png)

### 4.3.3 指定字段查询：match
如果要在字段中搜索特定字词，可以使用match; 如下语句将查询address 字段中包含 mill 或者 lane的数据
```sh
GET /bank/_search
{
  "query": { "match": { "address": "mill lane" } }
}
```
- **结果**

![24.es-usage-5.png](../../assets/images/06-中间件/elasticsearch/24.es-usage-5.png)

**(由于ES底层是按照分词索引的，所以上述查询结果是address 字段中包含 mill 或者 lane的数据)**

### 4.3.4 查询段落匹配：match_phrase
如果我们希望查询的条件是 address字段中包含 "mill lane"，则可以使用match_phrase
```sh
GET /bank/_search
{
  "query": { "match_phrase": { "address": "mill lane" } }
}
```
- **结果**

![25.es-usage-6.png](../../assets/images/06-中间件/elasticsearch/25.es-usage-6.png)

### 4.3.5 多条件查询: bool
如果要构造更复杂的查询，可以使用bool查询来组合多个查询条件。

例如，以下请求在bank索引中搜索40岁客户的帐户，但不包括居住在爱达荷州（ID）的任何人
```sh
GET /bank/_search
{
  "query": {
    "bool": {
      "must": [
        { "match": { "age": "40" } }
      ],
      "must_not": [
        { "match": { "state": "ID" } }
      ]
    }
  }
}
```
- **结果**

![26.es-usage-7.png](../../assets/images/06-中间件/elasticsearch/26.es-usage-7.png)

`must`,`should`, `must_not` 和 `filter` 都是bool查询的子句。那么`filter`和上述`query`子句有啥区别呢？

### 4.3.6 查询条件：query or filter
先看下如下查询, 在`bool`查询的子句中同时具备query/must 和 filter
```sh
GET /bank/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "state": "ND"
          }
        }
      ],
      "filter": [
        {
          "term": {
            "age": "40"
          }
        },
        {
          "range": {
            "balance": {
              "gte": 20000,
              "lte": 30000
            }
          }
        }
      ]
    }
  }
}
```
- **结果**

![27.es-usage-8.png](../../assets/images/06-中间件/elasticsearch/27.es-usage-8.png)

**两者都可以写查询条件，而且语法也类似。区别在于，query 上下文的条件是用来给文档打分的，匹配越好 _score 越高；filter 的条件只产生两种结果：符合与不符合，后者被过滤掉。**

所以，我们进一步看只包含filter的查询
```sh
GET /bank/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "term": {
            "age": "40"
          }
        },
        {
          "range": {
            "balance": {
              "gte": 20000,
              "lte": 30000
            }
          }
        }
      ]
    }
  }
}
```

结果，显然无_score

![28.es-usage-9.png](../../assets/images/06-中间件/elasticsearch/28.es-usage-9.png)


**想象一下在招聘网站搜索候选人：**

1. Query（查询上下文）
**作用**：类似于"关键词匹配"和"相关性排序"
- 搜索"Java开发工程师"
- 系统会为每个候选人计算匹配度分数（_score）：
  - 简历中"Java"出现10次 → 高分
  - "Java"出现5次 → 中等分  
  - 只有"JSP"没"Java" → 低分
  - 完全没有相关词汇 → 0分
- **结果按分数排序**，最相关的排前面

2. Filter（过滤上下文）
**作用**：类似于"硬性条件筛选"
- 要求：学历="本科"，经验="3年以上"
- **只有两种结果**：
  - 符合条件 → 通过
  - 不符合条件 → 直接被淘汰
- **不计算分数，不影响排序**，只做"是/否"判断

3. 技术区别详解

3.1 Query上下文（您的示例中的`must`部分）
```json
{
  "bool": {
    "must": [  // query上下文
      {
        "match": {
          "state": "ND"
        }
      }
    ]
  }
}
```
**特点**：
- 1. **计算相关性分数（_score）**
- 2. **参与排序**：分数高的排前面
- 3. **不缓存**（默认情况）
- 4. **适合**：全文搜索、相关性排序的场景

3.2 Filter上下文（您的示例中的`filter`部分）
```json
{
  "bool": {
    "filter": [  // filter上下文
      {
        "term": { "age": "40" }
      },
      {
        "range": { "balance": { "gte": 20000, "lte": 30000 } }
      }
    ]
  }
}
```
**特点**：
1. **不计算分数（_score = 0）**
2. **不影响排序**（除非指定了其他排序规则）
3. **自动缓存**：相同过滤条件会缓存，提升性能
4. **适合**：精确匹配、范围过滤、状态筛选

4. 示例的具体工作流程

```json
GET /bank/_search
{
  "query": {
    "bool": {
      "must": [   // 步骤1：计算分数
        { "match": { "state": "ND" } }
      ],
      "filter": [  // 步骤2：过滤，不分先后
        { "term": { "age": "40" } },
        { "range": { "balance": { "gte": 20000, "lte": 30000 } } }
      ]
    }
  }
}
```

5. 执行顺序：
- 1. **先执行filter**：找到所有`age=40`且`balance在20000-30000之间`的文档
   - 不计算分数，纯筛选
   - 结果集被缓存（如果下次同样条件，直接命中）
   
- 2. **再执行query**：在filter结果集中，计算`state匹配"ND"`的相关性分数
   - 计算_score
   - 按分数排序

- 3. **返回结果**：同时满足filter条件和query条件的文档，按query的_score排序

6. 性能对比实验

6.1 场景1：纯Query
```json
GET /bank/_search
{
  "query": {
    "match": { "state": "ND" }
  }
}
```
- 需要为**每个文档**计算匹配度分数
- 不能利用缓存
- 结果按相关性排序

6.2 场景2：纯Filter
```json
GET /bank/_search
{
  "query": {
    "bool": {
      "filter": [
        { "term": { "age": "40" } }
      ]
    }
  }
}
```
- 不需要计算分数（所有文档_score = 0）
- 结果集被缓存
- 多次执行相同filter会很快
- 结果无序（或按其他字段排序）

6.3 场景3：Query+Filter（您示例的最佳实践）
- **性能最佳**：filter快速筛选，query只对少量文档算分
- **结果相关**：既有精确筛选，又有相关性排序

7. 如何选择？

| 场景 | 用Query | 用Filter |
|------|---------|----------|
| 全文搜索（如搜索标题） | ✅ 需要相关性排序 | ❌ |
| 精确匹配（如状态=已发布） | ❌ 浪费计算资源 | ✅ 自动缓存 |
| 范围筛选（如价格>100） | ❌ | ✅ |
| 多条件组合搜索 | ✅ 用于相关性部分 | ✅ 用于精确筛选部分 |
| 需要按匹配度排序 | ✅ | ❌ |
| 需要最高性能 | ❌ | ✅ |

8. 实战建议

8.1. 80/20原则
- **80%** 的条件用filter（精确匹配、范围、状态）
- **20%** 的条件用query（关键词、相关性排序）

8.2. 正确示例
```json
GET /products/_search
{
  "query": {
    "bool": {
      "must": [  // query：用户关心的搜索词
        { "match": { "title": "无线耳机" } },
        { "match": { "description": "降噪" } }
      ],
      "filter": [  // filter：硬性条件
        { "term": { "category": "电子产品" } },
        { "range": { "price": { "lte": 1000 } } },
        { "term": { "in_stock": true } }
      ],
      "should": [  // query：提升相关性的额外条件
        { "match": { "brand": "索尼" } }  // 索尼品牌加分
      ]
    }
  }
}
```

8.3. 常见误区纠正
**错误**：把精确匹配放在query中
```json
// ❌ 错误：term放在query里浪费计算资源
{
  "query": {
    "term": { "status": "published" }
  }
}

// ✅ 正确：term放在filter中
{
  "query": {
    "bool": {
      "filter": [
        { "term": { "status": "published" } }
      ]
    }
  }
}
```

- **Query上下文**：像"搜索引擎" → "找到相关内容并排序"
- **Filter上下文**：像"数据库WHERE" → "筛选符合条件的记录"

**关键记忆点**：
1. **Filter**：要/不要（二进制决策），速度快，可缓存
2. **Query**：好/更好（程度比较），计算分数，用于排序

- `filter`部分确保只返回`age=40`且`balance在20000-30000`的文档
- `must`部分在这些文档中，为`state匹配"ND"`的文档计算分数并排序
- 最终得到：**同时满足filter条件且state相关度最高的文档**

## 4.4 聚合查询：Aggregation
> 我们知道SQL中有group by，在ES中它叫Aggregation，即聚合运算。
### 4.4.1 简单聚合
比如我们希望计算出account每个州的统计数量， 使用`aggs`关键字对`state`字段聚合，被聚合的字段无需对分词统计，所以使用`state.keyword`对整个字段统计

```sh
GET /bank/_search
{
  "size": 0,
  "aggs": {
    "group_by_state": {
      "terms": {
        "field": "state.keyword"
      }
    }
  }
}
```
- **结果**

![29.es-usage-10.png](../../assets/images/06-中间件/elasticsearch/29.es-usage-10.png)

因为无需返回条件的具体数据, 所以设置size=0，返回hits为空。

`doc_count`表示bucket中每个州的数据条数。

### 4.4.2 嵌套聚合
ES还可以处理个聚合条件的嵌套。

比如承接上个例子， 计算每个州的平均结余。涉及到的就是在对state分组的基础上，嵌套计算avg(balance):
```sh
GET /bank/_search
{
  "size": 0,
  "aggs": {
    "group_by_state": {
      "terms": {
        "field": "state.keyword"
      },
      "aggs": {
        "average_balance": {
          "avg": {
            "field": "balance"
          }
        }
      }
    }
  }
}
```
- **结果**

![30.es-usage-11.png](../../assets/images/06-中间件/elasticsearch/30.es-usage-11.png)

### 4.4.3 对聚合结果排序
可以通过在aggs中对嵌套聚合的结果进行排序

比如承接上个例子， 对嵌套计算出的avg(balance)，这里是average_balance，进行排序
```sh
GET /bank/_search
{
  "size": 0,
  "aggs": {
    "group_by_state": {
      "terms": {
        "field": "state.keyword",
        "order": {
          "average_balance": "desc"
        }
      },
      "aggs": {
        "average_balance": {
          "avg": {
            "field": "balance"
          }
        }
      }
    }
  }
}
```
- **结果**

![31.es-usage-12.png](../../assets/images/06-中间件/elasticsearch/31.es-usage-12.png)

## 4.5 补充：聚合查询的详解
### 4.5.1. 查询结构分解

```json
GET /bank/_search
{
  "size": 0,                     // 第一部分：控制结果数量
  "aggs": {                     // 第二部分：聚合开始
    "group_by_state": {         // 第三部分：聚合名称
      "terms": {                // 第四部分：聚合类型
        "field": "state.keyword" // 第五部分：聚合字段
      }
    }
  }
}
```

### 4.5.2. 逐部分详解

#### 第一部分：`"size": 0`
```json
"size": 0
```
- **作用**：不返回任何具体的文档（hits）
- **为什么**：在聚合查询中，我们只关心统计结果，不关心具体的文档
- **对比**：
  - `"size": 10` → 返回前10个文档 + 聚合结果
  - `"size": 0` → 只返回聚合结果，性能更好

#### 第二部分：`"aggs"`
```json
"aggs": {
  // 聚合定义
}
```
- **aggs** = aggregations（聚合）
- **作用**：定义一个或多个聚合操作
- **类似SQL**：`GROUP BY`, `COUNT()`, `SUM()`, `AVG()`等

#### 第三部分：`"group_by_state"`
```json
"group_by_state": {
  // 聚合细节
}
```
- **作用**：给这个聚合起个名字，方便识别结果
- **可以自定义**：比如叫`state_stats`、`state_grouping`等
- **结果中会显示这个名称**：
  ```json
  {
    "aggregations": {
      "group_by_state": {  // 这里就是你的聚合名称
        // 聚合结果
      }
    }
  }
  ```

#### 第四部分：`"terms"`
```json
"terms": {
  "field": "state.keyword"
}
```
- **terms聚合**：按字段的唯一值进行分组
- **类似SQL**：`GROUP BY state`
- **特点**：对字段的每个不同值创建一个"桶"(bucket)，并统计每个桶中的文档数

#### 第五部分：`"field": "state.keyword"`
```json
"field": "state.keyword"
```
这是最关键的部分！需要理解为什么是`.keyword`：

**为什么需要`.keyword`？**

### 4.5.3 问题根源：文本字段的双重特性

在Elasticsearch中，一个`text`类型的字段（如`state`）会被：
1. **分词（analyzed）**：用于全文搜索
   - `"New York"` → 分成`["new", "york"]`两个词条
2. **不分词（keyword）**：用于精确匹配和聚合
   - `"New York"` → 保持完整字符串`"New York"`

### 4.5.4 举例说明

假设数据：
```json
{"state": "New York"}
{"state": "New Mexico"}
{"state": "New York"}
{"state": "Texas"}
```

#### 情况1：如果用`state`（text字段）聚合
```json
"terms": {
  "field": "state"  // 错误！这是text字段
}
```
- `"New York"`会被分词为`["new", "york"]`
- 聚合结果会是：
  ```
  "new" → 3个文档（包含New York和New Mexico）
  "york" → 2个文档
  "mexico" → 1个文档
  "texas" → 1个文档
  ```
- 这明显不是我们想要的！

#### 情况2：如果用`state.keyword`聚合 ✓
```json
"terms": {
  "field": "state.keyword"  // 正确！使用keyword子字段
}
```
- 保持完整的字符串值
- 聚合结果：
  ```
  "New York" → 2个文档
  "New Mexico" → 1个文档
  "Texas" → 1个文档
  ```
- 这才是正确的州统计！

### 4.5.5 等价SQL对比

这个Elasticsearch查询：
```json
GET /bank/_search
{
  "size": 0,
  "aggs": {
    "group_by_state": {
      "terms": {
        "field": "state.keyword"
      }
    }
  }
}
```

等价于SQL：
```sql
SELECT state, COUNT(*) as count
FROM bank
GROUP BY state
ORDER BY count DESC
LIMIT 10  -- 注意：terms聚合默认返回前10个分组
```

### 4.5.6 完整结果示例

```json
{
  "took": 5,
  "timed_out": false,
  "_shards": {
    "total": 1,
    "successful": 1,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": {
      "value": 1000,
      "relation": "eq"
    },
    "max_score": null,
    "hits": []  // 因为size=0，所以这里为空
  },
  "aggregations": {
    "group_by_state": {  // 你的聚合名称
      "doc_count_error_upper_bound": 0,
      "sum_other_doc_count": 743,  // 未显示的文档总数
      "buckets": [  // 分组桶，默认按文档数降序
        {
          "key": "TX",      // 州名
          "doc_count": 30   // 该州的文档数
        },
        {
          "key": "CA",
          "doc_count": 28
        },
        {
          "key": "NY",
          "doc_count": 25
        },
        // ... 默认只显示前10个州
      ]
    }
  }
}
```

### 4.5.7 重要参数

你可以控制聚合行为：

```json
{
  "size": 0,
  "aggs": {
    "group_by_state": {
      "terms": {
        "field": "state.keyword",
        "size": 50,         // 返回50个分组（默认10）
        "order": {
          "_count": "asc"   // 按数量升序排列（默认降序）
        },
        "min_doc_count": 5  // 只显示文档数≥5的分组
      }
    }
  }
}
```

### 4.5.8 实际应用场景

#### 1. 网站分析
```json
// 统计访问量最高的10个页面
{
  "aggs": {
    "top_pages": {
      "terms": {
        "field": "page_url.keyword",
        "size": 10
      }
    }
  }
}
```

#### 2. 电商分析
```json
// 统计销量最高的5个商品分类
{
  "aggs": {
    "top_categories": {
      "terms": {
        "field": "category.keyword",
        "size": 5
      }
    }
  }
}
```

#### 3. 日志分析
```json
// 统计错误日志最多的10个服务
{
  "aggs": {
    "error_by_service": {
      "terms": {
        "field": "service_name.keyword",
        "size": 10
      }
    }
  }
}
```

### 4.5.9 记忆要点

1. **`size: 0`** → 只要聚合结果，不要具体文档
2. **`aggs`** → 聚合操作的开始
3. **自定义名称** → 给聚合起有意义的名字
4. **`terms`** → 按唯一值分组（类似SQL的GROUP BY）
5. **`.keyword`** → 对文本字段聚合时必须使用，避免分词问题

简单说，这个查询的意思是：
**"请统计bank索引中每个州（state）分别有多少个账户，并按账户数从多到少排序，只返回统计结果，不返回具体账户信息。"**

# 五、ES详解 - 索引：索引管理详解























































































































































































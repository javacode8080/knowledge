> 最后更新：2025-12-25 | [返回主目录](../README.md)
# 知识体系

MongoDB学习引入

![1.mongo-x-basic-0.png](../../assets/images/06-中间件/mongodb/1.mongo-x-basic-0.png)

MongoDB生态

![2.mongo-y-echo-11.png](../../assets/images/06-中间件/mongodb/2.mongo-y-echo-11.png)


学习资料
- 网资料
    - <a href='https://www.mongodb.com/'>MongoDB官网</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/'>MongoDB数据库文档</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/tools-and-connectors/'>MongoDB 常用工具</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/crud/'>MongoDB CRUD</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/aggregation/'>MongoDB 聚合</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/data-modeling/'>MongoDB 数据模型</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/core/transactions/'>MongoDB 事务</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/indexes/'>MongoDB 索引</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/security/'>MongoDB 安全</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/replication/'>MongoDB 副本</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/sharding/'>MongoDB 分片</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/administration/'>MongoDB 管理</a>
    - <a href='https://www.mongodb.com/zh-cn/docs/manual/storage/'>MongoDB 存储</a>
- 入门系列
  - <a href='https://www.runoob.com/mongodb/mongodb-tutorial.html'>MongoDB菜鸟教程</a>
  - <a href='https://www.w3cschool.cn/mongodb/mongodb-1uxs37ih.html'>MongoDB入门教程</a>
- 其它
  - <a href='https://cloud.tencent.com/developer/article/1004794'>Mongodb Geo2d索引原理</a>


# 一、Mongo入门 - MongoDB基础概念

## 1.1 什么是NoSQL？
NoSQL是一种非关系型DMS，不需要固定的架构，可以避免joins链接，并且易于扩展。NoSQL数据库用于具有庞大数据存储需求的分布式数据存储。NoSQL用于大数据和实时Web应用程序。例如，像Twitter，Facebook，Google这样的大型公司，每天可能产生TB级的用户数据。

NoSQL数据库代表“**不仅仅是SQL**”或“不是SQL”。虽然NoRELNoSQL会是一个更好的名词。Carl Strozz在1998年引入了NoSQL概念。

传统的RDBMS使用SQL语法来存储和查询数据。相反，NoSQL数据库系统包含可存储结构化，半结构化，非结构化和多态数据的多种数据库技术。

![3.mongo-x-basic-1.png](../../assets/images/06-中间件/mongodb/3.mongo-x-basic-1.png)

### 1.1.1 为什么使用NoSQL？
NoSQL数据库的概念在处理大量数据的互联网巨头（例如Google，Facebook，Amazon等）中变得很流行。使用RDBMS处理海量数据时，系统响应时间变慢。

为了解决此问题，当然可以通过升级现有硬件来“横向扩展”我们的系统。但这个成本很高。

这个问题的替代方案是在负载增加时将数据库负载分配到多个主机上。这种方法称为“横向扩展”。

![4.mongo-x-basic-2.png](../../assets/images/06-中间件/mongodb/4.mongo-x-basic-2.png)

NoSQL数据库是非关系数据库，因此在设计时考虑到Web应用程序，比关系数据库更好地扩展。

### 1.1.2 NoSQL数据库的简要历史
- 1998年-Carlo Strozzi在他的轻量级开源关系数据库中使用术语NoSQL
- 2000-图形数据库Neo4j启动
- 2004年-推出Google BigTable
- 2005年-启动CouchDB
- 2007年-发布有关Amazon Dynamo的研究论文
- 2008年-Facebook开源Cassandra项目
- 2009年-重新引入NoSQL术语
### 1.1.3 NoSQL的功能
- **非关系**
  - NoSQL数据库从不遵循关系模型
  - 切勿为tables 提供固定的固定列记录
  - 使用自包含的聚合或BLOB
  - 不需要对象关系映射和数据规范化
  - 没有复杂的功能，例如查询语言，查询计划者，
  - 参照完整性联接，ACID
- **动态架构**
  - NoSQL数据库是无模式的或具有宽松模式的数据库
  - 不需要对数据架构进行任何形式的定义
  - 提供同一域中的异构数据结构

![5.mongo-x-basic-3.png](../../assets/images/06-中间件/mongodb/5.mongo-x-basic-3.png)

- 简单的API
  - 提供易于使用的界面，用于存储和查询提供的数据
  - API允许进行低级数据操作和选择方法
  - 基于文本的协议，通常与带有JSON的HTTP REST一起使用
  - 多数不使用基于标准的查询语言
  - 支持Web的数据库作为面向互联网的服务运行
- 分布式
  - 可以以分布式方式执行多个NoSQL数据库
  - 提供自动缩放和故障转移功能
  - 通常可牺牲ACID概念来实现可伸缩性和吞吐量
  - 分布式节点之间几乎没有同步复制，多为异步多主复制，对等，HDFS复制
  - 仅提供最终的一致性
  - 无共享架构。这样可以减少协调并提高分布。

![6.mongo-x-basic-4.png](../../assets/images/06-中间件/mongodb/6.mongo-x-basic-4.png)

## 1.2 什么是MongoDB
MongoDB是面向文档的NoSQL数据库，用于大量数据存储。MongoDB是一个在2000年代中期问世的数据库。属于NoSQL数据库的类别。

### 1.2.1 MongoDB功能
每个数据库都包含集合，而集合又包含文档。每个文档可以具有不同数量的字段。每个文档的大小和内容可以互不相同。 文档结构更符合开发人员如何使用各自的编程语言构造其类和对象。开发人员经常会说他们的类不是行和列，而是具有键值对的清晰结构。 从NoSQL数据库的简介中可以看出，行（或在MongoDB中调用的文档）不需要预先定义架构。相反，可以动态创建字段。 MongoDB中可用的数据模型使我们可以更轻松地表示层次结构关系，存储数组和其他更复杂的结构。 可伸缩性– MongoDB环境具有很高的可伸缩性。全球各地的公司已经定义了自己的集群，其中一些集群运行着100多个节点，数据库中包含大约数百万个文档。

### 1.2.2 为什么使用MongoDB
以下是一些为什么应该开始使用MongoDB的原因

- **面向文档的**–由于MongoDB是NoSQL类型的数据库，它不是以关系类型的格式存储数据，而是将数据存储在文档中。这使得MongoDB非常灵活，可以适应实际的业务环境和需求。
- **临时查询**-MongoDB支持按字段，范围查询和正则表达式搜索。可以查询返回文档中的特定字段。
-** 索引**-可以创建索引以提高MongoDB中的搜索性能。MongoDB文档中的任何字段都可以建立索引。
- **复制**-MongoDB可以提供副本集的高可用性。副本集由两个或多个mongo数据库实例组成。每个副本集成员可以随时充当主副本或辅助副本的角色。主副本是与客户端交互并执行所有读/写操作的主服务器。辅助副本使用内置复制维护主数据的副本。当主副本发生故障时，副本集将自动切换到辅助副本，然后它将成为主服务器。
- **负载平衡**-MongoDB使用分片的概念，通过在多个MongoDB实例之间拆分数据来水平扩展。MongoDB可以在多台服务器上运行，以平衡负载或复制数据，以便在硬件出现故障时保持系统正常运行。
### 1.2.3 MongoDB常用术语
下面是MongoDB中使用的一些常用术语

- **_id** – 这是每个MongoDB文档中必填的字段。_id字段表示MongoDB文档中的唯一值。_id字段类似于文档的主键。如果创建的新文档中没有_id字段，MongoDB将自动创建该字段。
- **集合** – 这是MongoDB文档的分组。集合等效于在任何其他RDMS（例如Oracle或MS SQL）中创建的表。集合存在于单个数据库中。从介绍中可以看出，集合不强制执行任何结构。
- **游标** – 这是指向查询结果集的指针。客户可以遍历游标以检索结果。
- **数据库** – 这是像RDMS中那样的集合容器，其中是表的容器。每个数据库在文件系统上都有其自己的文件集。MongoDB服务器可以存储多个数据库。
- **文档** - MongoDB集合中的记录基本上称为文档。文档包含字段名称和值。
- **字段** - 文档中的名称/值对。一个文档具有零个或多个字段。字段类似于关系数据库中的列。

下图显示了带有键值对的字段的示例。如下的例子中，CustomerID和11是文档中定义的键值对之一。

![7.mongo-x-basic-5.png](../../assets/images/06-中间件/mongodb/7.mongo-x-basic-5.png)

## 1.3 MongoDB与RDBMS区别
下表将帮助您更容易理解Mongo中的一些概念


| SQL术语/概念   | MongoDB术语/概念 | 解释/说明                                     |
|----------------|------------------|---------------------------------------------|
| database       | database         | 数据库                                      |
| table          | collection       | 数据库表/集合                               |
| row            | document         | 数据记录行/文档                             |
| column         | field            | 数据字段/域                                 |
| index          | index            | 索引                                        |
| table joins    |                  | 表连接,MongoDB不支持                        |
| primary key    | primary key      | 主键,MongoDB自动将_id字段设置为主键         |

# 二、Mongo入门 - MongoDB整体生态
> 很多人在学习Mongo时仅仅围绕着数据库功能，围绕着CRUD和聚合操作，但是MongoDB其实已经基本形成了它自身的生态了。我们在学习一项技能时一定要跳出使用的本身，要从高一点的格局上了解整个生态，这样会对你构筑知识体系有很大的帮助。

## 2.1 整体生态

![8.mongo-y-echo-11.png](../../assets/images/06-中间件/mongodb/8.mongo-y-echo-11.png)

### 2.1.1 MongoDB Server
MongoDB数据库，包含如下核心功能：

![9.mongo-y-echo-4.png](../../assets/images/06-中间件/mongodb/9.mongo-y-echo-4.png)

- <a href='https://www.mongodb.com/try/download/community'>社区版本</a>

![10.mongo-y-echo-10.png](../../assets/images/06-中间件/mongodb/10.mongo-y-echo-10.png)

- <a href='https://www.mongodb.com/try/download/enterprise'>企业版本</a>

![11.mongo-y-echo-9.png](../../assets/images/06-中间件/mongodb/11.mongo-y-echo-9.png)

### 2.1.2 MongoDB Cloud
MongoDB Cloud

![12.mongo-y-echo-8.png](../../assets/images/06-中间件/mongodb/12.mongo-y-echo-8.png)

### 2.1.3 MongoDB Drivers

![13.mongo-y-echo-6.png](../../assets/images/06-中间件/mongodb/13.mongo-y-echo-6.png)

<a href='https://www.mongodb.com/zh-cn/docs/languages/java/'>驱动相关的文档</a>

![14.mongo-y-echo-7.png](../../assets/images/06-中间件/mongodb/14.mongo-y-echo-7.png)

## 2.2 工具相关
<a href='https://www.mongodb.com/zh-cn/docs/tools-and-connectors/'>MongoDB Tools</a>

![15.mongo-y-echo-5.png](../../assets/images/06-中间件/mongodb/15.mongo-y-echo-5.png)

### 2.2.1 MongoDB Atlas Open Service Broker
Open Service Broker: https://www.openservicebrokerapi.org/

什么是开放式服务代理者(Open Service Broker): https://www.jdon.com/49640

### 2.2.2 MongoDB Connector for BI
MongoDB Connector for BI: https://www.mongodb.com/zh-cn/docs/bi-connector/current/

BI是指Business Intelligence，众所周知的BI工具有Tableau, MicroStrategy和Qlik等；而MongoDB BI Connector充当在mongod或mongos实例与BI工具之间转换查询和数据的层， 这样用户就可以使用SQL创建查询，可视化，图形化和报告等方式来展示MongoDB中的数据。

![16.mongo-y-echo-1.png](../../assets/images/06-中间件/mongodb/16.mongo-y-echo-1.png)

- `MongoDB DB`: 数据的存储
- `BI Connector`: 提供一个关系模式(Schema)，以及BI工具和MongoDB之间转换SQL查询
- `ODBC data source name (DSN)`: 连接配置数据和认证等.
- `BI Tool`: 数据分析和展示工具.
### 2.2.3 MongoDB Charts
 MongoDB Charts: https://www.mongodb.com/zh-cn/docs/charts/

负责MongoDB数据可视化的一个工具。

![17.mongo-y-echo-2.png](../../assets/images/06-中间件/mongodb/17.mongo-y-echo-2.png)

### 2.2.4 MongoDB Command Line Interface (mongocli)
MongoDB Command Line Interface (mongocli): https://www.mongodb.com/zh-cn/docs/mongocli/current/

通过命令行进行管理Mongo的工具，支持Cloud和On-premises，Cloud通过MongoDB Atlas支持；On-premises通过 MongoDB Cloud Manager 和 MongoDB Ops Manager支持。

### 2.2.5 MongoDB Kubernetes Operator
MongoDB Community Kubernetes Operator: https://github.com/mongodb/mongodb-kubernetes-operator

将MongoDB部署到Kubernetes集群的工具。

### 2.2.6 MongoDB Compass
MongoDB Compass: https://www.mongodb.com/zh-cn/docs/compass/

提供的对MongoDB进行界面化管理的工具。

> 需要注意：MongoDB 3.6+ 版本才支持这个工具；对于聚合操作的可视化是它的优势。

### 2.2.7 MongoDB VsCode插件
MongoDB for VS Code: https://www.mongodb.com/zh-cn/docs/mongodb-vscode/

MongoDB VsCode插件

![18.mongo-y-echo-3.png](../../assets/images/06-中间件/mongodb/18.mongo-y-echo-3.png)

### 2.2.8 MongoDB Shell
MongoDB Shell: https://www.mongodb.com/zh-cn/docs/mongodb-shell/

mongosh是一个MongoDB的shell工具。

### 2.2.9 MongoDB Kafka Connector
MongoDB Kafka Connector: https://docs.mongodb.com/kafka-connector/current/

MongoDB连接kafka的工具。

### 2.2.10 MongoDB Spark Connector
MongoDB Spark Connector: https://docs.mongodb.com/spark-connector/current/

MongoDB连接Spark的工具。

### 2.2.11 MongoDB Database Tools
MongoDB Database Tools: https://docs.mongodb.com/database-tools/

- 二进制导入导出
  - `mongodump` Creates a binary export of the contents of a mongod database.
  - `mongorestore` Restores data from a mongodump database dump into a mongod or mongos
  - `bsondump` Converts BSON dump files into JSON.
- 数据导入导出
  - `mongoimport` Imports content from an Extended JSON, CSV, or TSV export file.
  - `mongoexport` Produces a JSON or CSV export of data stored in a mongod instance.
- 诊断工具
  - `mongostat` Provides a quick overview of the status of a currently running mongod or mongos instance.
  - `mongotop` Provides an overview of the time a mongod instance spends reading and writing data.
- GridFS 工具
  - `mongofiles` Supports manipulating files stored in your MongoDB instance in GridFS objects.


## 2.3 应用举例（MongoDB Kafka Connector + MongoDB Spark Connector）

*   **MongoDB Kafka Connector**：主要负责**数据的实时流动**。
*   **MongoDB Spark Connector**：主要负责**对数据的复杂计算与分析**。


---

### 1. MongoDB Kafka Connector：数据的“实时桥梁”

Kafka 是一个高吞吐量的分布式消息队列（事件流平台），常用于构建实时数据管道。MongoDB Kafka Connector 的作用就是在 MongoDB 和 Kafka 之间建立双向的、实时的数据同步。

#### 主要业务应用场景：

**a) 变更数据捕获（CDC）与实时数据集成**
*   **场景**：你有一个核心的用户服务，数据存储在 MongoDB 中。当用户注册、更新资料时，其他系统（如推荐系统、营销系统、数据仓库）需要立刻知道这个变化。
*   **如何工作**：Connector 可以持续监听 MongoDB 的 oplog（操作日志），任何数据的插入、更新、删除都会被抓取，并作为一个消息实时发送到 Kafka 主题。下游的其他服务只需订阅这个 Kafka 主题，就能近乎实时地获取数据变更，而无需直接查询 MongoDB，大大降低了源数据库的压力。
*   **业务价值**：实现微服务架构下的解耦，保证数据的最终一致性，支撑实时推荐、实时风控、实时仪表盘等。

**b) 命令查询责任分离（CQRS）模式**
*   **场景**：你的应用写操作很频繁（如游戏状态更新、物联网传感器数据写入），但读操作模式复杂多样（如复杂的报表查询、全文搜索）。如果都用同一个 MongoDB 实例，写操作会影响读性能。
*   **如何工作**：使用 MongoDB 作为“命令端”（写模型），所有数据变更通过 Kafka Connector 流式导出到 Kafka。然后，再使用其他 Connector 将数据从 Kafka 导入到更适合查询的“查询端”（读模型），比如另一个 MongoDB（专门为查询优化过索引）、Elasticsearch（提供全文搜索）或数据仓库（用于分析）。
*   **业务价值**：读写分离，最大化读写性能，系统扩展性更强。

**c) 数据导出到数据仓库/湖**
*   **场景**：需要将运营数据从 MongoDB 导入到 Snowflake、BigQuery 或 Amazon S3 等数据仓库/数据湖中，进行大规模的历史数据分析。
*   **如何工作**：MongoDB -> Kafka Connector -> Kafka -> （例如）Spark Streaming -> 数据仓库。Kafka 在这里充当了可靠的数据缓冲区，即使数据仓库临时不可用，数据也不会丢失。

---

### 2. MongoDB Spark Connector：数据的“计算引擎”

Spark 是一个强大的分布式计算框架，擅长进行大规模数据的批量处理、流处理、机器学习和图计算。MongoDB Spark Connector 让 Spark 能够轻松地将 MongoDB 作为数据源或数据目的地。

#### 主要业务应用场景：

**a) 复杂数据分析与报表**
*   **场景**：你需要对存储在 MongoDB 中的海量用户行为日志、订单数据进行分析，生成复杂的商业报表（如月度销售趋势、用户留存分析）。
*   **如何工作**：使用 Spark SQL 或 DataFrame API，通过 Spark Connector 直接从 MongoDB 读取数据。利用 Spark 强大的分布式计算能力，执行复杂的数据聚合、连接（Join）和转换操作，最终将结果写回 MongoDB 或生成报表文件。
*   **业务价值**：处理 MongoDB 单机无法胜任的超大规模数据集分析，为决策提供数据支持。

**b) 机器学习与预测分析**
*   **场景**：基于用户的历史购买记录和浏览行为（存在 MongoDB 中），构建一个推荐模型。
*   **如何工作**：Spark 的 MLlib 库提供了丰富的机器学习算法。通过 Spark Connector 从 MongoDB 读取训练数据，在 Spark 集群上进行模型训练。训练好的模型可以用于实时推荐，也可以将用户标签等结果写回 MongoDB。
*   **业务价值**：实现数据驱动的智能应用，如个性化推荐、客户流失预测、欺诈检测等。

**c) 数据清洗、转换与归档（ETL）**
*   **场景**：需要定期清理 MongoDB 中的原始数据，将符合条件的历史数据归档到冷存储，或者将数据格式进行标准化。
*   **如何工作**：编写 Spark ETL 作业，从 MongoDB 读取数据，进行清洗、过滤、格式转换等操作，然后将处理后的干净数据写回 MongoDB 或导出到其他系统。
*   **业务价值**：保证数据质量，优化主数据库的存储成本。

---

### 强强联合：一个完整的业务案例

想象一个**智能电商平台**：

1.  **实时用户行为追踪**：用户在前端App的每一次点击、浏览、加购操作，都实时写入 **MongoDB**（因为MongoDB文档模型灵活，适合存储这种半结构化的行为数据）。
2.  **实时数据流**：**MongoDB Kafka Connector** 监听到这些新的行为数据，立即将其发送到 **Kafka**。
3.  **实时处理与推荐**：
    *   一个实时推荐服务订阅了 Kafka，它能立刻处理当前用户的行为流，实时更新用户兴趣画像，并从商品库中召回最相关的商品，实现“猜你喜欢”的实时更新。
    *   一个风控服务也订阅了 Kafka，实时分析行为模式，检测是否存在刷单、盗号等异常行为。
4.  **离线分析与模型训练**：
    *   每天凌晨，**Spark** 作业通过 **MongoDB Spark Connector** 读取全天积累在 MongoDB 中的完整用户行为数据和订单数据。
    *   Spark 进行复杂的批量计算，生成详细的销售报表、用户留存分析等。
    *   同时，Spark 的机器学习算法利用这些历史数据训练和优化第二天的推荐模型、销量预测模型。

### 总结

| 工具 | 角色 | 核心业务价值 |
| :--- | :--- | :--- |
| **MongoDB** | **操作数据存储** | 支撑应用主业务，提供灵活的文档模型和快速的读写能力。 |
| **MongoDB Kafka Connector** | **实时数据管道** | **解耦**与**流动**，确保数据变更能实时、可靠地通知到其他系统。 |
| **MongoDB Spark Connector** | **批量计算与分析** | **洞察**与**智能**，对海量数据进行复杂计算、机器学习，挖掘深层价值。 |

这三者结合，构成了一个从**实时操作**到**实时流处理**，再到**批量分析与机器学习**的完整、健壮的现代数据平台架构。

# 三、Mongo入门 - 基本使用：安装和CRUD
> 在理解MongoDB基础概念后，本文将介绍MongoDB的安装和最基本的CURD操作。
## 3.1 MongoDB安装
> MongoDB的安装比较简单，这里主要给出官方的下载地址，以及官方安装文档的地址，方便大家直达。

### 3.1.1 一些参考文档
- **官网下载**

<a href ='https://www.mongodb.com/try/download/community'>官网下载</a>

- 官网文档

<a href ='https://www.mongodb.com/zh-cn/docs/manual/administration/install-community/'>官网文档</a>

![19.mongo-x-usage-2.png](../../assets/images/06-中间件/mongodb/19.mongo-x-usage-2.png)

- **菜鸟教程中安装**
<a href='https://www.runoob.com/mongodb/mongodb-window-install.html'>菜鸟教程</a>

### 3.1.2 以Linux为例安装
- **yum源**

创建文件`/etc/yum.repos.d/mongodb-org-6.0.repo`, 加yum源：
```sh
[mongodb-org-6.0]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/8/mongodb-org/6.0/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-6.0.asc
```
- **安装**
```sh
sudo yum install -y mongodb-org
```
- **运行**
```sh
[root@pdai yum.repos.d]# systemctl start mongod
[root@pdai yum.repos.d]# sudo systemctl status mongod
● mongod.service - MongoDB Database Server
   Loaded: loaded (/usr/lib/systemd/system/mongod.service; enabled; vendor preset: disabled)
   Active: active (running) since Tue 2020-07-28 09:59:55 CST; 26s ago
     Docs: https://docs.mongodb.org/manual
  Process: 8868 ExecStart=/usr/bin/mongod $OPTIONS (code=exited, status=0/SUCCESS)
  Process: 8865 ExecStartPre=/usr/bin/chmod 0755 /var/run/mongodb (code=exited, status=0/SUCCESS)
  Process: 8863 ExecStartPre=/usr/bin/chown mongod:mongod /var/run/mongodb (code=exited, status=0/SUCCESS)
  Process: 8862 ExecStartPre=/usr/bin/mkdir -p /var/run/mongodb (code=exited, status=0/SUCCESS)
 Main PID: 8872 (mongod)
   CGroup: /system.slice/mongod.service
           └─8872 /usr/bin/mongod -f /etc/mongod.conf
```

## 3.2 连接和建库
- **连接(注意5.0之前连接是mongo命令，之后版本是mongosh命令)**

我们装的6.0命令是新的
```sh
mongosh --host 127.0.0.1:27017
Current Mongosh Log ID: 694df8512f04515a9b8de665
Connecting to:          mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.5.10
Using MongoDB:          6.0.27
Using Mongosh:          2.5.10

For mongosh info see: https://www.mongodb.com/docs/mongodb-shell/


To help improve our products, anonymous usage data is collected and sent to MongoDB periodically (https://www.mongodb.com/legal/privacy-policy).
You can opt-out by running the disableTelemetry() command.

------
   The server generated these startup warnings when booting
   2025-12-26T10:45:58.206+08:00: Access control is not enabled for the database. Read and write access to data and configuration is unrestricted
   2025-12-26T10:45:58.206+08:00: /sys/kernel/mm/transparent_hugepage/enabled is 'always'. We suggest setting it to 'never' in this binary version
   2025-12-26T10:45:58.206+08:00: vm.max_map_count is too low
------
test> show dbs
admin   40.00 KiB
config  12.00 KiB
local   40.00 KiB
test>
```
- 建库(注意，use一个不存在的数据库时，mongodb会在像这个数据库插入第一条数据的时候创建这个数据库)
```sh
> use testdb;
switched to db testdb
```
## 3.3 CRUD操作
<a href='https://www.mongodb.com/zh-cn/docs/manual/crud/'>官方CRUD文档</a>

### 3.3.1 Insert
- **图例**

![20.mongo-x-usage-3.png](../../assets/images/06-中间件/mongodb/20.mongo-x-usage-3.png)

- **示例**
```sh
testdb> db.inventory.insertOne(
...  { item: "canvas", qty: 100, tags: ["cotton"], size: { h: 28, w: 35.5, uom: "cm" } }
... )
{
  acknowledged: true,
  insertedId: ObjectId('694dfbeeca1979530f8de666')
}

testdb> db.inventory.insertMany([
...     { item: "journal", qty: 25, tags: ["blank", "red"], size: { h: 14, w: 21, uom: "cm" } },
...    { item: "mat", qty: 85, tags: ["gray"], size: { h: 27.9, w: 35.5, uom: "cm" } },
...     { item: "mousepad", qty: 25, tags: ["gel", "blue"], size: { h: 19, w: 22.85, uom: "cm" } }
...  ])
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('694dfc6eca1979530f8de667'),
    '1': ObjectId('694dfc6eca1979530f8de668'),
    '2': ObjectId('694dfc6eca1979530f8de669')
  }
}

testdb> db.inventory.find( {} )
[
  {
    _id: ObjectId('694dfbeeca1979530f8de666'),
    item: 'canvas',
    qty: 100,
    tags: [ 'cotton' ],
    size: { h: 28, w: 35.5, uom: 'cm' }
  },
  {
    _id: ObjectId('694dfc6eca1979530f8de667'),
    item: 'journal',
    qty: 25,
    tags: [ 'blank', 'red' ],
    size: { h: 14, w: 21, uom: 'cm' }
  },
  {
    _id: ObjectId('694dfc6eca1979530f8de668'),
    item: 'mat',
    qty: 85,
    tags: [ 'gray' ],
    size: { h: 27.9, w: 35.5, uom: 'cm' }
  },
  {
    _id: ObjectId('694dfc6eca1979530f8de669'),
    item: 'mousepad',
    qty: 25,
    tags: [ 'gel', 'blue' ],
    size: { h: 19, w: 22.85, uom: 'cm' }
  }
]

```
- **更多文档资料**

<a href='https://www.mongodb.com/zh-cn/docs/manual/tutorial/insert-documents/'>官方相关文档</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.insert/#db.collection.insert'>官方相关示例 - Insert</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.insertone/'>官方相关示例 - InsertOne</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.insertMany/'>官方相关示例 - InsertMany</a>

### 3.3.2 Query
- **图例**

![21.mongo-x-usage-4.png](../../assets/images/06-中间件/mongodb/21.mongo-x-usage-4.png)

- **示例**
```sh
> db.inventory.find( {} )
{ "_id" : ObjectId("5f1f8a9a099483199e74737c"), "item" : "canvas", "qty" : 100, "tags" : [ "cotton" ], "size" : { "h" : 28, "w" : 35.5, "uom" : "cm" } }
{ "_id" : ObjectId("5f1f8aa8099483199e74737d"), "item" : "journal", "qty" : 25, "tags" : [ "blank", "red" ], "size" : { "h" : 14, "w" : 21, "uom" : "cm" } }
{ "_id" : ObjectId("5f1f8aa8099483199e74737e"), "item" : "mat", "qty" : 85, "tags" : [ "gray" ], "size" : { "h" : 27.9, "w" : 35.5, "uom" : "cm" } }
{ "_id" : ObjectId("5f1f8aa8099483199e74737f"), "item" : "mousepad", "qty" : 25, "tags" : [ "gel", "blue" ], "size" : { "h" : 19, "w" : 22.85, "uom" : "cm" } }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a78"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a79"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "A" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7a"), "item" : "paper", "qty" : 100, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "D" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7b"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7c"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "A" }
> db.inventory.find( { status: "D" } )
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7a"), "item" : "paper", "qty" : 100, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "D" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7b"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
> db.inventory.find( { status: { $in: [ "A", "D" ] } } )
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a78"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a79"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "A" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7a"), "item" : "paper", "qty" : 100, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "D" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7b"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7c"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "A" }
> db.inventory.find( { status: "A", qty: { $lt: 30 } } )
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a78"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
> db.inventory.find( { $or: [ { status: "A" }, { qty: { $lt: 30 } } ] } )
{ "_id" : ObjectId("5f1f8aa8099483199e74737d"), "item" : "journal", "qty" : 25, "tags" : [ "blank", "red" ], "size" : { "h" : 14, "w" : 21, "uom" : "cm" } }
{ "_id" : ObjectId("5f1f8aa8099483199e74737f"), "item" : "mousepad", "qty" : 25, "tags" : [ "gel", "blue" ], "size" : { "h" : 19, "w" : 22.85, "uom" : "cm" } }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a78"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a79"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "A" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7c"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "A" }
> db.inventory.find( {
...      status: "A",
...      $or: [ { qty: { $lt: 30 } }, { item: /^p/ } ]
... } )
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a78"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f94de4326f1d6a51d3a7c"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "A" }
>
```
- **更多文档资料**

<a href='https://www.mongodb.com/zh-cn/docs/manual/tutorial/query-documents/'>官方相关文档</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.find/'>官方相关示例 - find</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.findOne/'>官方相关示例 - findOne</a>

<a href='mongodb.com/zh-cn/docs/manual/reference/method/db.collection.findandmodify/'>官方相关示例 - findAndModify</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.findoneanddelete/'>官方相关示例 - findOneAndDelete</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.findoneandreplace/'>官方相关示例 - findOneAndReplace</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.findoneandupdate/'>官方相关示例 - findOneAndUpdate</a>

### 3.3.3 Update
- **图例**

![22.mongo-x-usage-5.png](../../assets/images/06-中间件/mongodb/22.mongo-x-usage-5.png)

- **示例**
```sh
> db.inventory.insertMany( [
...    { item: "canvas", qty: 100, size: { h: 28, w: 35.5, uom: "cm" }, status: "A" },
...    { item: "journal", qty: 25, size: { h: 14, w: 21, uom: "cm" }, status: "A" },
...    { item: "mat", qty: 85, size: { h: 27.9, w: 35.5, uom: "cm" }, status: "A" },
...    { item: "mousepad", qty: 25, size: { h: 19, w: 22.85, uom: "cm" }, status: "P" },
...    { item: "notebook", qty: 50, size: { h: 8.5, w: 11, uom: "in" }, status: "P" },
...    { item: "paper", qty: 100, size: { h: 8.5, w: 11, uom: "in" }, status: "D" },
...    { item: "planner", qty: 75, size: { h: 22.85, w: 30, uom: "cm" }, status: "D" },
...    { item: "postcard", qty: 45, size: { h: 10, w: 15.25, uom: "cm" }, status: "A" },
...    { item: "sketchbook", qty: 80, size: { h: 14, w: 21, uom: "cm" }, status: "A" },
...    { item: "sketch pad", qty: 95, size: { h: 22.85, w: 30.5, uom: "cm" }, status: "A" }
... ] );
{
        "acknowledged" : true,
        "insertedIds" : [
                ObjectId("5f1f96cf4326f1d6a51d3a7d"),
                ObjectId("5f1f96cf4326f1d6a51d3a7e"),
                ObjectId("5f1f96cf4326f1d6a51d3a7f"),
                ObjectId("5f1f96cf4326f1d6a51d3a80"),
                ObjectId("5f1f96cf4326f1d6a51d3a81"),
                ObjectId("5f1f96cf4326f1d6a51d3a82"),
                ObjectId("5f1f96cf4326f1d6a51d3a83"),
                ObjectId("5f1f96cf4326f1d6a51d3a84"),
                ObjectId("5f1f96cf4326f1d6a51d3a85"),
                ObjectId("5f1f96cf4326f1d6a51d3a86")
        ]
}
> db.inventory.updateOne(
...    { item: "paper" },
...    {
...      $set: { "size.uom": "cm", status: "P" },
...      $currentDate: { lastModified: true }
...    }
... )
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }
> db.inventory.find( {} )
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7d"), "item" : "canvas", "qty" : 100, "size" : { "h" : 28, "w" : 35.5, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7e"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7f"), "item" : "mat", "qty" : 85, "size" : { "h" : 27.9, "w" : 35.5, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a80"), "item" : "mousepad", "qty" : 25, "size" : { "h" : 19, "w" : 22.85, "uom" : "cm" }, "status" : "P" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a81"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "P" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a82"), "item" : "paper", "qty" : 100, "size" : { "h" : 8.5, "w" : 11, "uom" : "cm" }, "status" : "P", "lastModified" : ISODate("2020-07-28T03:09:17.014Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a83"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a84"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a85"), "item" : "sketchbook", "qty" : 80, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a86"), "item" : "sketch pad", "qty" : 95, "size" : { "h" : 22.85, "w" : 30.5, "uom" : "cm" }, "status" : "A" }
```
- **updateMany**
```sh
> db.inventory.updateMany(
...    { "qty": { $lt: 50 } },
...    {
...      $set: { "size.uom": "in", status: "P" },
...      $currentDate: { lastModified: true }
...    }
... )
{ "acknowledged" : true, "matchedCount" : 3, "modifiedCount" : 3 }
> db.inventory.find( {} )
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7d"), "item" : "canvas", "qty" : 100, "size" : { "h" : 28, "w" : 35.5, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7e"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.391Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7f"), "item" : "mat", "qty" : 85, "size" : { "h" : 27.9, "w" : 35.5, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a80"), "item" : "mousepad", "qty" : 25, "size" : { "h" : 19, "w" : 22.85, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.391Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a81"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "P" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a82"), "item" : "paper", "qty" : 100, "size" : { "h" : 8.5, "w" : 11, "uom" : "cm" }, "status" : "P", "lastModified" : ISODate("2020-07-28T03:09:17.014Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a83"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a84"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.392Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a85"), "item" : "sketchbook", "qty" : 80, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a86"), "item" : "sketch pad", "qty" : 95, "size" : { "h" : 22.85, "w" : 30.5, "uom" : "cm" }, "status" : "A" }
>
```
- replace one
```sh
> db.inventory.replaceOne(
...    { item: "paper" },
...    { item: "paper", instock: [ { warehouse: "A", qty: 60 }, { warehouse: "B", qty: 40 } ] }
... )
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }
> db.inventory.find( {} )
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7d"), "item" : "canvas", "qty" : 100, "size" : { "h" : 28, "w" : 35.5, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7e"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.391Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7f"), "item" : "mat", "qty" : 85, "size" : { "h" : 27.9, "w" : 35.5, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a80"), "item" : "mousepad", "qty" : 25, "size" : { "h" : 19, "w" : 22.85, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.391Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a81"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "P" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a82"), "item" : "paper", "instock" : [ { "warehouse" : "A", "qty" : 60 }, { "warehouse" : "B", "qty" : 40 } ] }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a83"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a84"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.392Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a85"), "item" : "sketchbook", "qty" : 80, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a86"), "item" : "sketch pad", "qty" : 95, "size" : { "h" : 22.85, "w" : 30.5, "uom" : "cm" }, "status" : "A" }
>
```
- **更多文档资料**

<a href='https://www.mongodb.com/zh-cn/docs/manual/tutorial/update-documents/'>官方相关文档</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.update/'>官方相关示例 - update</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.updateone/'>官方相关示例 - updateOne</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.updatemany/'>官方相关示例 - updateMany</a>

### 3.3.4 Delete
- **图例**

![23.mongo-x-usage-6.png](../../assets/images/06-中间件/mongodb/23.mongo-x-usage-6.png)

- **示例**
```sh
> db.inventory.deleteMany({ status : "A" })
{ "acknowledged" : true, "deletedCount" : 4 }
> db.inventory.find( {} )
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a7e"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.391Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a80"), "item" : "mousepad", "qty" : 25, "size" : { "h" : 19, "w" : 22.85, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.391Z") }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a81"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "P" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a82"), "item" : "paper", "instock" : [ { "warehouse" : "A", "qty" : 60 }, { "warehouse" : "B", "qty" : 40 } ] }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a83"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("5f1f96cf4326f1d6a51d3a84"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "in" }, "status" : "P", "lastModified" : ISODate("2020-07-28T04:33:50.392Z") }
```
- **更多文档资料**

<a href='https://www.mongodb.com/zh-cn/docs/manual/tutorial/remove-documents/'>官方相关文档</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.remove/'>官方相关示例 - remove</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.deleteone/'>官方相关示例 - deleteOne</a>

<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.deletemany/'>官方相关示例 - deleteMany</a>

### 3.3.5 BulkWrite(操作批量化)
本质是就是将上述的操作批量化。
```sh
try {
   db.characters.bulkWrite(
      [
         { insertOne :
            {
               "document" :
               {
                  "_id" : 4, "char" : "Dithras", "class" : "barbarian", "lvl" : 4
               }
            }
         },
         { insertOne :
            {
               "document" :
               {
                  "_id" : 5, "char" : "Taeln", "class" : "fighter", "lvl" : 3
               }
            }
         },
         { updateOne :
            {
               "filter" : { "char" : "Eldon" },
               "update" : { $set : { "status" : "Critical Injury" } }
            }
         },
         { deleteOne :
            { "filter" : { "char" : "Brisbane"} }
         },
         { replaceOne :
            {
               "filter" : { "char" : "Meldane" },
               "replacement" : { "char" : "Tanys", "class" : "oracle", "lvl" : 4 }
            }
         }
      ]
   );
}
catch (e) {
   print(e);
}
```
# 四、Mongo入门 - 基本使用：索引和聚合
> 在了解MongoDB的基本CRUD操作后，常用的其它操作还有对字段的索引以及对字段的聚合操作。
## 4.1 聚合 - Aggregation Pipline
> 类似于将SQL中的group by + order by + left join ... 等操作管道化。

### 4.1.1 常规使用
- **图例理解**

![24.mongo-x-usage-11.png](../../assets/images/06-中间件/mongodb/24.mongo-x-usage-11.png)

- **准备数据**
```sh
> db.orders.insertMany( [
...     { _id: 1, cust_id: "abc1", ord_date: ISODate("2012-11-02T17:04:11.102Z"), status: "A", amount: 50 },
...     { _id: 2, cust_id: "xyz1", ord_date: ISODate("2013-10-01T17:04:11.102Z"), status: "A", amount: 100 },
...     { _id: 3, cust_id: "xyz1", ord_date: ISODate("2013-10-12T17:04:11.102Z"), status: "D", amount: 25 },
...     { _id: 4, cust_id: "xyz1", ord_date: ISODate("2013-10-11T17:04:11.102Z"), status: "D", amount: 125 },
...     { _id: 5, cust_id: "abc1", ord_date: ISODate("2013-11-12T17:04:11.102Z"), status: "A", amount: 25 }
... ] );
{ "acknowledged" : true, "insertedIds" : [ 1, 2, 3, 4, 5 ] }
> db.orders.find({})
{ "_id" : 1, "cust_id" : "abc1", "ord_date" : ISODate("2012-11-02T17:04:11.102Z"), "status" : "A", "amount" : 50 }
{ "_id" : 2, "cust_id" : "xyz1", "ord_date" : ISODate("2013-10-01T17:04:11.102Z"), "status" : "A", "amount" : 100 }
{ "_id" : 3, "cust_id" : "xyz1", "ord_date" : ISODate("2013-10-12T17:04:11.102Z"), "status" : "D", "amount" : 25 }
{ "_id" : 4, "cust_id" : "xyz1", "ord_date" : ISODate("2013-10-11T17:04:11.102Z"), "status" : "D", "amount" : 125 }
{ "_id" : 5, "cust_id" : "abc1", "ord_date" : ISODate("2013-11-12T17:04:11.102Z"), "status" : "A", "amount" : 25 }
>
```
- **聚合操作**
```sh
> db.orders.aggregate([
...                      { $match: { status: "A" } },
...                      { $group: { _id: "$cust_id", total: { $sum: "$amount" } } },
...                      { $sort: { total: -1 } }
...                    ])
{ "_id" : "xyz1", "total" : 100 }
{ "_id" : "abc1", "total" : 75 }
```
- 官网还有两个例子：

  - <a href='https://www.mongodb.com/zh-cn/docs/manual/tutorial/aggregation-complete-examples/'>Aggregation with the Zip Code Data Set</a>
  - <a href='https://www.mongodb.com/zh-cn/docs/manual/tutorial/aggregation-complete-examples/'>Aggregation with User Preference Data</a>

### 4.1.2 Pipline操作
MongoDB的聚合管道（Pipline）将MongoDB文档在一个阶段（Stage）处理完毕后将结果传递给下一个阶段（Stage）处理。**阶段（Stage）操作是可以重复的。**

表达式：处理输入文档并输出。表达式是无状态的，只能用于计算当前聚合管道的文档，不能处理其它的文档。

这里我们介绍一下聚合框架中常用的几个Stages：

- `$project`：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。
- `$match`：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。
- `$limit`：用来限制MongoDB聚合管道返回的文档数。
- `$skip`：在聚合管道中跳过指定数量的文档，并返回余下的文档。
- `$unwind`：将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
- `$group`：将集合中的文档分组，可用于统计结果。
- `$sort`：将输入文档排序后输出。
- `$geoNear`：输出接近某一地理位置的有序文档。
- `$bucket`: 分组（分桶）计算。
- `$facet` : 多次分组计算。
- `$out`: 将结果集输出，必须是Pipline最后一个Stage。

**举几个例子**

- **$project**
```sh
> db.orders.aggregate(
...     { $project : {
...         _id : 0 , // 默认不显示_id
...         cust_id : 1 ,
...         status : 1
...     }});
{ "cust_id" : "abc1", "status" : "A" }
{ "cust_id" : "xyz1", "status" : "A" }
{ "cust_id" : "xyz1", "status" : "D" }
{ "cust_id" : "xyz1", "status" : "D" }
{ "cust_id" : "abc1", "status" : "A" }
>
```
- **$skip**
```sh 
 db.orders.aggregate(
...     { $skip : 4 });
{ "_id" : 5, "cust_id" : "abc1", "ord_date" : ISODate("2013-11-12T17:04:11.102Z"), "status" : "A", "amount" : 25 }
>
```
- **$unwind**
```sh
> db.inventory2.insertOne({ "_id" : 1, "item" : "ABC1", sizes: [ "S", "M", "L"] })
{ "acknowledged" : true, "insertedId" : 1 }
> db.inventory2.aggregate( [ { $unwind : "$sizes" } ] )
{ "_id" : 1, "item" : "ABC1", "sizes" : "S" }
{ "_id" : 1, "item" : "ABC1", "sizes" : "M" }
{ "_id" : 1, "item" : "ABC1", "sizes" : "L" }
```
- **$bucket**
```sh
> db.artwork.insertMany([
... { "_id" : 1, "title" : "The Pillars of Society", "artist" : "Grosz", "year" : 1926,
...     "price" : NumberDecimal("199.99") },
... { "_id" : 2, "title" : "Melancholy III", "artist" : "Munch", "year" : 1902,
...     "price" : NumberDecimal("280.00") },
... { "_id" : 3, "title" : "Dancer", "artist" : "Miro", "year" : 1925,
...     "price" : NumberDecimal("76.04") },
... { "_id" : 4, "title" : "The Great Wave off Kanagawa", "artist" : "Hokusai",
...     "price" : NumberDecimal("167.30") },
... { "_id" : 5, "title" : "The Persistence of Memory", "artist" : "Dali", "year" : 1931,
...     "price" : NumberDecimal("483.00") },
... { "_id" : 6, "title" : "Composition VII", "artist" : "Kandinsky", "year" : 1913,
...     "price" : NumberDecimal("385.00") },
... { "_id" : 7, "title" : "The Scream", "artist" : "Munch", "year" : 1893 },
... { "_id" : 8, "title" : "Blue Flower", "artist" : "O'Keefe", "year" : 1918,
...     "price" : NumberDecimal("118.42") }
... ])
{
        "acknowledged" : true,
        "insertedIds" : [
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8
        ]
}
> db.artwork.find({})
{ "_id" : 1, "title" : "The Pillars of Society", "artist" : "Grosz", "year" : 1926, "price" : NumberDecimal("199.99") }
{ "_id" : 2, "title" : "Melancholy III", "artist" : "Munch", "year" : 1902, "price" : NumberDecimal("280.00") }
{ "_id" : 3, "title" : "Dancer", "artist" : "Miro", "year" : 1925, "price" : NumberDecimal("76.04") }
{ "_id" : 4, "title" : "The Great Wave off Kanagawa", "artist" : "Hokusai", "price" : NumberDecimal("167.30") }
{ "_id" : 5, "title" : "The Persistence of Memory", "artist" : "Dali", "year" : 1931, "price" : NumberDecimal("483.00") }
{ "_id" : 6, "title" : "Composition VII", "artist" : "Kandinsky", "year" : 1913, "price" : NumberDecimal("385.00") }
{ "_id" : 7, "title" : "The Scream", "artist" : "Munch", "year" : 1893 } // 注意这里没有price，聚合结果中为Others
{ "_id" : 8, "title" : "Blue Flower", "artist" : "O'Keefe", "year" : 1918, "price" : NumberDecimal("118.42") }
> db.artwork.aggregate( [
...   {
...     $bucket: {
...       groupBy: "$price",
...       boundaries: [ 0, 200, 400 ],
...       default: "Other",
...       output: {
...         "count": { $sum: 1 },
...         "titles" : { $push: "$title" }
...       }
...     }
...   }
... ] )
{ "_id" : 0, "count" : 4, "titles" : [ "The Pillars of Society", "Dancer", "The Great Wave off Kanagawa", "Blue Flower" ] }
{ "_id" : 200, "count" : 2, "titles" : [ "Melancholy III", "Composition VII" ] }
{ "_id" : "Other", "count" : 2, "titles" : [ "The Persistence of Memory", "The Scream" ] }
```
- **$bucket + $facet**

**非常常用！**
```sh
db.artwork.aggregate( [
  {
    $facet: {
      "price": [
        {
          $bucket: {
              groupBy: "$price",
              boundaries: [ 0, 200, 400 ],
              default: "Other",
              output: {
                "count": { $sum: 1 },
                "artwork" : { $push: { "title": "$title", "price": "$price" } }
              }
          }
        }
      ],
      "year": [
        {
          $bucket: {
            groupBy: "$year",
            boundaries: [ 1890, 1910, 1920, 1940 ],
            default: "Unknown",
            output: {
              "count": { $sum: 1 },
              "artwork": { $push: { "title": "$title", "year": "$year" } }
            }
          }
        }
      ]
    }
  }
] )

// 输出
{
  "year" : [
    {
      "_id" : 1890,
      "count" : 2,
      "artwork" : [
        {
          "title" : "Melancholy III",
          "year" : 1902
        },
        {
          "title" : "The Scream",
          "year" : 1893
        }
      ]
    },
    {
      "_id" : 1910,
      "count" : 2,
      "artwork" : [
        {
          "title" : "Composition VII",
          "year" : 1913
        },
        {
          "title" : "Blue Flower",
          "year" : 1918
        }
      ]
    },
    {
      "_id" : 1920,
      "count" : 3,
      "artwork" : [
        {
          "title" : "The Pillars of Society",
          "year" : 1926
        },
        {
          "title" : "Dancer",
          "year" : 1925
        },
        {
          "title" : "The Persistence of Memory",
          "year" : 1931
        }
      ]
    },
    {
      // Includes the document without a year, e.g., _id: 4
      "_id" : "Unknown",
      "count" : 1,
      "artwork" : [
        {
          "title" : "The Great Wave off Kanagawa"
        }
      ]
    }
  ],
      "price" : [
    {
      "_id" : 0,
      "count" : 4,
      "artwork" : [
        {
          "title" : "The Pillars of Society",
          "price" : NumberDecimal("199.99")
        },
        {
          "title" : "Dancer",
          "price" : NumberDecimal("76.04")
        },
        {
          "title" : "The Great Wave off Kanagawa",
          "price" : NumberDecimal("167.30")
        },
        {
          "title" : "Blue Flower",
          "price" : NumberDecimal("118.42")
        }
      ]
    },
    {
      "_id" : 200,
      "count" : 2,
      "artwork" : [
        {
          "title" : "Melancholy III",
          "price" : NumberDecimal("280.00")
        },
        {
          "title" : "Composition VII",
          "price" : NumberDecimal("385.00")
        }
      ]
    },
    {
      // Includes the document without a price, e.g., _id: 7
      "_id" : "Other",
      "count" : 2,
      "artwork" : [
        {
          "title" : "The Persistence of Memory",
          "price" : NumberDecimal("483.00")
        },
        {
          "title" : "The Scream"
        }
      ]
    }
  ]
}
```
聚合操作使用的比较频繁，在实际的工作中可以参考官方文档 - <a href='https://www.mongodb.com/zh-cn/docs/manual/reference/mql/aggregation-stages/'>Aggregation Pipeline Stages</a>。

![25.mongo-x-usage-17.png](../../assets/images/06-中间件/mongodb/25.mongo-x-usage-17.png)

### 4.1.3 Aggregation Options参数
> 举一个explain参数为例，更多的相关Options可以参考官方文档，<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/method/db.collection.aggregate/'>Aggregrate相关配置</a>

- **explain**
```sh
> db.orders.aggregate(
...                      [
...                        { $match: { status: "A" } },
...                        { $group: { _id: "$cust_id", total: { $sum: "$amount" } } },
...                        { $sort: { total: -1 } }
...                      ],
...                      {
...                        explain: true
...                      }
...                    )
{
        "serverInfo" : {
                "host" : "pdai",
                "port" : 27017,
                "version" : "3.6.19",
                "gitVersion" : "41b289ff734a926e784d6ab42c3129f59f40d5b4"
        },
        "stages" : [
                {
                        "$cursor" : {
                                "query" : {
                                        "status" : "A"
                                },
                                "fields" : {
                                        "amount" : 1,
                                        "cust_id" : 1,
                                        "_id" : 0
                                },
                                "queryPlanner" : {
                                        "plannerVersion" : 1,
                                        "namespace" : "testdb.orders",
                                        "indexFilterSet" : false,
                                        "parsedQuery" : {
                                                "status" : {
                                                        "$eq" : "A"
                                                }
                                        },
                                        "winningPlan" : {
                                                "stage" : "COLLSCAN",
                                                "filter" : {
                                                        "status" : {
                                                                "$eq" : "A"
                                                        }
                                                },
                                                "direction" : "forward"
                                        },
                                        "rejectedPlans" : [ ]
                                }
                        }
                },
                {
                        "$group" : {
                                "_id" : "$cust_id",
                                "total" : {
                                        "$sum" : "$amount"
                                }
                        }
                },
                {
                        "$sort" : {
                                "sortKey" : {
                                        "total" : -1
                                }
                        }
                }
        ],
        "ok" : 1
}
```
## 4.2 聚合 - Map Reduce
- **图例理解**

![26.mongo-x-usage-12.png](../../assets/images/06-中间件/mongodb/26.mongo-x-usage-12.png)

### 4.2.1 官网给了个例子
- **准备数据**
```sh
{
     _id: ObjectId("50a8240b927d5d8b5891743c"),
     cust_id: "abc123",
     ord_date: new Date("Oct 04, 2012"),
     status: 'A',
     price: 25,
     items: [ { sku: "mmm", qty: 5, price: 2.5 },
              { sku: "nnn", qty: 5, price: 2.5 } ]
}
```
- **计算每个顾客总花费：**

**map**
```sh
var mapFunction1 = function() {
                       emit(this.cust_id, this.price);
                   };
```
**reduce**
```sh
var reduceFunction1 = function(keyCustId, valuesPrices) {
                          return Array.sum(valuesPrices);
                      };
```
**out**
```sh
db.orders.mapReduce(
                     mapFunction1,
                     reduceFunction1,
                     { out: "map_reduce_example" }
                   )
```
- **计算每个订单中Items的均价**

**map**
```sh
var mapFunction2 = function() {
                       for (var idx = 0; idx < this.items.length; idx++) {
                           var key = this.items[idx].sku;
                           var value = {
                                         count: 1,
                                         qty: this.items[idx].qty
                                       };
                           emit(key, value);
                       }
                    };
```
**reduce**
```
var reduceFunction2 = function(keySKU, countObjVals) {
                     reducedVal = { count: 0, qty: 0 };

                     for (var idx = 0; idx < countObjVals.length; idx++) {
                         reducedVal.count += countObjVals[idx].count;
                         reducedVal.qty += countObjVals[idx].qty;
                     }

                     return reducedVal;
                  };
```
**finalize**
```sh
var finalizeFunction2 = function (key, reducedVal) {

                       reducedVal.avg = reducedVal.qty/reducedVal.count;

                       return reducedVal;

                    };
```
## 4.3 索引
索引即为提升查询等的效率，默认是对_id进行索引的。

### 4.3.1 图例理解
以对users中score进行索引时查询的效果

![27.mongo-x-usage-13.png](../../assets/images/06-中间件/mongodb/27.mongo-x-usage-13.png)

### 4.3.2 索引的类型
对于索引，这里简单介绍下常用的类型，其它类型和例子可以参考<a href='https://www.mongodb.com/zh-cn/docs/manual/indexes/'>官网文档 - 索引</a>

- **单一索引**

![28.mongo-x-usage-14.png](../../assets/images/06-中间件/mongodb/28.mongo-x-usage-14.png)

- **复合索引**

![29.mongo-x-usage-15.png](../../assets/images/06-中间件/mongodb/29.mongo-x-usage-15.png)

- **多键索引**

![30.mongo-x-usage-16.png](../../assets/images/06-中间件/mongodb/30.mongo-x-usage-16.png)

### 4.3.3 对索引的操作
- 查看集合索引
```sh
db.col.getIndexes()
```
- 查看集合索引大小
```sh
db.col.totalIndexSize()
```
- 删除集合所有索引
```sh
db.col.dropIndexes()
```
- 删除集合指定索引
```sh
db.col.dropIndex("索引名称")
```
# 五、Mongo入门 - 基本使用：效率工具
> 本文将主要介绍常用的MongoDB的工具，这些工具可以极大程度的提升你的效率。

## 5.1 官方MongoDB Compass
> 推荐使用MongoDB Compass，所以详细截几个图给大家看看。

MongoDB Compass Community由MongoDB开发人员开发，这意味着更高的可靠性和兼容性。它为MongoDB提供GUI mongodb工具，以探索数据库交互；具有完整的CRUD功能并提供可视方式。借助内置模式可视化，用户可以分析文档并显示丰富的结构。为了监控服务器的负载，它提供了数据库操作的实时统计信息。就像MongoDB一样，Compass也有两个版本，一个是Enterprise（付费），社区可以免费使用。适用于Linux，Mac或Windows。官网<a href='https://www.mongodb.com/try/download/compass'>下载地址</a>

- **查询**

**展示列表**

![31.mongo-x-usage-18.png](../../assets/images/06-中间件/mongodb/31.mongo-x-usage-18.png)

**特色：按条件查询**

![32.mongo-x-usage-19.png](../../assets/images/06-中间件/mongodb/32.mongo-x-usage-19.png)

**特色：转化为查询语言**

![33.mongo-x-usage-22.png](../../assets/images/06-中间件/mongodb/33.mongo-x-usage-22.png)

- **聚合**

**特色：可以可视化的添加pipleline中的Stage**

![34.mongo-x-usage-20.png](../../assets/images/06-中间件/mongodb/34.mongo-x-usage-20.png)

- **索引**

![35.mongo-x-usage-24.png](../../assets/images/06-中间件/mongodb/35.mongo-x-usage-24.png)

- **Explain**

![36.mongo-x-usage-25.png](../../assets/images/06-中间件/mongodb/36.mongo-x-usage-25.png)

- **Schema**

![37.mongo-x-usage-21.png](../../assets/images/06-中间件/mongodb/37.mongo-x-usage-21.png)

- **校验**

![38.mongo-x-usage-26.png](../../assets/images/06-中间件/mongodb/38.mongo-x-usage-26.png)


## 5.2 NoSQLBooster
NoSQLBooster是MongoDB CLI界面中非常流行的GUI工具。它正式名称为MongoBooster。NoSQLBooster是一个跨平台，它自带一些mongodb工具来管理数据库和监控服务器。这个Mongodb工具包括服务器监控工具，Visual Explain Plan，查询构建器，SQL查询，ES2017语法支持等。它有免费，个人和商业版本，当然，免费版本有一些功能限制。NoSQLBooster也可用于Windows，MacOS和Linux。支持 sql，免费版 支持 CSV、JSON 导入，但不支持 CSV、JSON 导出。集成了 mongodump/mongorestore，支持监控，和查询优化。 <a href='https://nosqlbooster.com/downloads'>下载链接</a>

![39.mongo-x-usage-22.webp](../../assets/images/06-中间件/mongodb/39.mongo-x-usage-22.webp)

## 5.3 Robot3T
Robo 3T前身是Robomongo。支持Windows，MacOS和Linux系统。Robo 3T 1.3为您提供了对MongoDB 4.0和SCRAM-SHA-256（升级的mongo shell）的支持，支持从MongoDB SRV连接字符串导入，以及许多其他修复和改进。大家也可以找到之前的Robomongo，完全免费的版本使用。<a href='https://robomongo.org/download'>下载地址</a>

![39.mongo-x-usage-23.png](../../assets/images/06-中间件/mongodb/39.mongo-x-usage-23.png)


**早前的Robomongo**

![40.mongo-x-usage-28.png](../../assets/images/06-中间件/mongodb/40.mongo-x-usage-28.png)

## 5.4 Vs Code plugin
对于喜欢超级轻量级的，以及vscode粉来说，MongoDB vscode插件可以考虑下：

![41.mongo-x-usage-27.png](../../assets/images/06-中间件/mongodb/41.mongo-x-usage-27.png)

## 5.5 其它
> 因为其它不常用，且上面的几个工具已经足够了，这里只是给出其它工具的列表：

- <a href='https://severalnines.com/'>ClusterControl</a>
  - 还要注册！它不仅支持MongoDB，还支持MySQL，MySQL复制，MySQL NDB集群，Galera集群，MariaDB，PostgreSQL，TimescaleDB，Docker和ProxySQL。
  - 为数据库基础架构提供全自动安全性，该基础架构具有单个图形用户界面，可操作和自动化MongoDB和MySQL数据库环境。它可通过YUM/APT提供回购，适用于Linux平台（RedHat，Centos，Ubuntu或Debian）
- <a href='http://mms.litixsoft.de/index.php?lang=de/'>Mongo Management Studio(已失效)</a>
  - 目前看只更新到v3.4
- <a href='https://www.nosqlclient.com/'>NoSQL Client</a>
  - 这里有个<a href='https://www.nosqlclient.com/demo/'>demo</a>
- <a href ='https://developer.aliyun.com/article/721720'>更多可以参考这里</a>

# 六、Mongo入门 - 基本使用：Java API































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































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
> 本文为低优先级，只是向你介绍下MongoDB提供的原生的JavaAPI；而大多数公司使用Spring框架，会使用Spring Data对MongoDB原生API的封装，比如JPA，MongoTemplate等。

## 6.1 MongoDB Driver
```xml
<!-- https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver -->
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongo-java-driver</artifactId>
    <version>3.12.6</version>
</dependency>
```
## 6.2 代码测试
例子请参考 <a href='https://mongodb.github.io/mongo-java-driver/3.12/driver/getting-started/quick-start/'>mongo-java-driver</a> 例子
```java
private static final String MONGO_HOST = "xxx.xxx.xxx.xxx";

    private static final Integer MONGO_PORT = 27017;

    private static final String MONGO_DB = "testdb";


    public static void main(String args[]) {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MONGO_DB);
            System.out.println("Connect to database successfully");

            // 创建Collection
            mongoDatabase.createCollection("test");
            System.out.println("create collection");

            // 获取collection
            MongoCollection<Document> collection = mongoDatabase.getCollection("test");

            // 插入document
            Document doc = new Document("name", "MongoDB")
                    .append("type", "database")
                    .append("count", 1)
                    .append("info", new Document("x", 203).append("y", 102));
            collection.insertOne(doc);

            // 统计count
            System.out.println(collection.countDocuments());

            // query - first
            Document myDoc = collection.find().first();
            System.out.println(myDoc.toJson());

            // query - loop all
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }
            } finally {
                cursor.close();
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
```
# 七、Mongo入门 - 基本使用：Spring集成
> 本文为主要介绍Spring Data对MongoDB原生API的封装，比如Spring-data-mongo，MongoTemplate等。以及原生API和Spring data系列之间的关系。
## 7.1 Spring Data 与 MongoDB
> 在初学使用者而言，常会分不清Spring-data-jpa, spring-data-mongo, springboot-data-mongo-starter以及mongo-driver之间的关联关系， 本节将带你理解它们之间的关系。

### 7.1.1 Spring Data的层次结构
首先让我们回顾下Spring runtime体系：

![42.mongo-x-usage-spring-4.png](../../assets/images/06-中间件/mongodb/42.mongo-x-usage-spring-4.png)

Spring Data是基于Spring runtime体系的：

> 下面这个图能够直观反映出它们之间的依赖关系，以及包中类之间的以来关系。

![43.mongo-x-usage-spring-5.png](../../assets/images/06-中间件/mongodb/43.mongo-x-usage-spring-5.png)

### 7.1.2 springboot-data-mongo层次结构

我们通过引入`springboot-data-mongo-starter`包来看它们之间的层次结构：

![44.mongo-x-usage-spring-1.png](../../assets/images/06-中间件/mongodb/44.mongo-x-usage-spring-1.png)

## 7.2 mongodb+Java用法
所以通过上面分析我们可以得到基于mongodb+Java的常见用法：

### 7.2.1 使用方式及依赖包的引入
- 引入`mongodb-driver`, 使用最原生的方式通过Java调用mongodb提供的Java driver;
- 引入`spring-data-mongo`, 自行配置使用`spring data` 提供的对MongoDB的封装
  - 使用`MongoTemplate` 的方式
  - 使用`MongoRespository` 的方式
- 引入`spring-data-mongo-starter`, 采用`spring autoconfig`机制自动装配，然后再使用`MongoTemplate`或者`MongoRespository`方式。
### 7.2.2 具体使用中文档的参考
<a href='[spring-data/mongodb 官方的参考文档](https://docs.spring.io/spring-data/mongodb/docs/3.0.3.RELEASE/reference/html/#preface)'>spring-data/mongodb 官方的参考文档</a>

![45.mongo-x-usage-spring-2.png](../../assets/images/06-中间件/mongodb/45.mongo-x-usage-spring-2.png)

### 7.2.3 一些案例的参考
- **原生方式**

前文我们展示的Java通过mongodb-driver操作mongodb示例。

官方mongo-java-driver 例子

- **spring-data-mongo**

<a href='https://spring.io/projects/spring-data-mongodb/#samples'>官方spring-data-mongodb 例子</a>

![46.mongo-x-usage-spring-3.png](../../assets/images/06-中间件/mongodb/46.mongo-x-usage-spring-3.png)

# 八、Mongo进阶 - MongoDB体系结构
> 上面章节已经对MongoDB生态中工具以及使用有了基础，后续文章我们将开始理解MongoDB是如何支撑这些功能的。我们将从最基本的MongoDB的体系结构开始介绍，主要包括MongoDB的包结构，MongoDB的数据逻辑结构，MongoDB的数据文件结构。其中围绕着MongoDB的数据文件结构，将为我们后续介绍MongoDB的存储引擎详解打下基础。

## 8.1 MongoDB包组件结构
> 主要是MongoDB数据库服务以及一些工具。

![47.mongo-y-arch-1.png](../../assets/images/06-中间件/mongodb/47.mongo-y-arch-1.png)

回顾下我们在MongoDB生态中展示的<a href='https://www.mongodb.com/zh-cn/docs/database-tools/'>MongoDB Database Tools</a>

- 二进制导入导出
  - `mongodump` Creates a binary export of the contents of a mongod database.
  - `mongorestore` Restores data from a mongodump database dump into a mongod or mongos
  - bsondump Converts BSON dump files into JSON.
- 数据导入导出
  - `mongoimport` Imports content from an Extended JSON, CSV, or TSV export file.
  - `mongoexport` Produces a JSON or CSV export of data stored in a mongod instance.
- 诊断工具
  - `mongostat` Provides a quick overview of the status of a currently running mongod or mongos instance.
  - `mongotop` Provides an overview of the time a mongod instance spends reading and writing data.
- GridFS 工具
  - `mongofiles` Supports manipulating files stored in your MongoDB instance in GridFS objects.

除了上述没有列举到，还有：

  - `mongoperf`: mongoDB自带工具，用于评估磁盘随机IO性能。

包组件可以在官网<a href='https://www.mongodb.com/zh-cn/docs/manual/reference/program/#mongodb-package-components'>MongoDB Package Components</a>找到详细的用法。

![48.mongo-y-arch-3.png](../../assets/images/06-中间件/mongodb/48.mongo-y-arch-3.png)

其中最主要的程序当然是mongod（数据库服务），mongod在不同的部署方案中（单机部署，副本集部署，分片集群部署），通过不同的配置，可以扮演多种不同的角色：

- 在单机部署中扮演 数据库服务器（提供所有读写功能）
- 在副本集部署中，通过配置，可以部署为 primary节点（主服务器，负责写数据，也可以提供查询）、secondary节点（从服务器，它从主节点复制数据，也可以提供查询）、以及arbiter节点（仲裁节点，不保存数据，主要用于参与选举投票）
- 在分片集群中，除了在每个分片中扮演上述角色外，还扮演着配置服务器的角色（存储有分片集群的所有元数据信息，mongos的数据路由分发等都要依赖于它）

在一台服务器上，可以启动多个mongod服务。但在实际生产部署中，通常还是建议一台服务器部署一个mongod实例，这样不仅减少资源竞争，而且服务器故障也不会同时影响到多个服务。

## 8.2 MongoDB数据逻辑结构
MongoDB 数据逻辑结构分为数据库（database）、集合（collection）、文档（document）三层 :

- 一个mongod实例中允许创建多个数据库。
- 一个数据库中允许创建多个集合（集合相当于关系型数据库的表）。
- 一个集合则是由若干个文档构成（文档相当于关系型数据库的行，是MongoDB中数据的基本单元）。

![49.mongo-y-arch-2.png](../../assets/images/06-中间件/mongodb/49.mongo-y-arch-2.png)

### 8.2.1 数据库
> 一个数据库中可以创建多个集合，原则上我们通常把逻辑相近的集合都放在一个数据库中，当然出于性能或者数据量的关系，也可能进行拆分。
在MongoDB中有几个内建的数据库：

- `admin` admin库主要存放有数据库帐号相关信息。
- `local` local数据库永远不会被复制到从节点，可以用来存储限于本地单台服务器的任意集合副本集的配置信息、oplog就存储在local库中。
重要的数据不要存储在local库，因为没有冗余副本，如果这个节点故障，存储在local库的数据就无法正常使用了。
- `config` config数据库用于分片集群环境，存放了分片相关的元数据信息。
- `test` MongoDB默认创建的一个测试库，连接mongod服务时，如果不指定连接的具体数据库，默认就会连接到test库。

![50.mongo-y-arch-4.png](../../assets/images/06-中间件/mongodb/50.mongo-y-arch-4.png)

### 8.2.2 集合
> 集合由若干条文档记录构成。
- 前面介绍MongoDB的时候提到过，集合是**schema-less**的（无模式或动态模式），这意味着集合不需要在读写数据前创建模式就可以使用，集合中的文档也可以拥有不同的字段，随时可以任意增减某个文档的字段。
在集合上可以对文档进行增删改查以及进行聚合操作。
- 在集合上还可以对文档中的字段创建索引。
- 除了一般的集合外，还可以创建一种叫做**定容集合（capped collection）**类型的集合，这种集合与一般集合主要的区别是，它可以限制集合的容量大小，在数据写满的时候，又可以从头开始覆盖最开始的文档进行循环写入。
- **副本集**就是利用这种类型的集合作为oplog，记录primary节点上的写操作，并且**同步到从节点重放，以实现主副节点数据复制的功能**。
### 8.2.3 文档
> 文档是MongoDB中数据的基本存储单元，它以一种叫做BSON文档的结构表示。BSON，即Binary JSON，多个键及其关联的值有序地存放在其中，类似映射，散列或字典。
- 文**档中的键/值对是有序的，不同序则是不同文档。并且键是区分大小写的，否则也为不同文档。**
- 文档的键是字符串，而值除了字符串，还可以是int, long, double，boolean，子文档，数组等多种类型。
- 文档中**不能有重复的键**。
- 每个文档都有一个默认的**_id键**，它相当于关系型数据库中的主键，这个键的值在同一个集合中必须是唯一的，**_id键值默认是ObjectId类型**，在插入文档的时候，如果用户不设置文档的_id值得话，MongoDB会自动生成生成一个唯一的ObjectId值进行填充。
## 8.3 MongoDB数据库文件
> 注意
> 
> MongoDB数据库文件和MongoDB存储的引擎有直接关系，MongoDB一共提供了三种存储引擎：WiredTiger，MMAPV1和In Memory；在MongoDB3.2之前采用的是MMAPV1; 后续版本v3.2开始默认采用WiredTiger； 且在v4.2版本中移除了MMAPV1的引擎。

在后续文章中，还将对MongoDB存储引擎进行详解。

### 8.3.1 MongoDB - MMAPv1引擎下的数据库文件
> 由于v3.0后续版本已经弃用了，所以这里不会详细介绍。
- journal 日志文件
- namespace 表名文件
- data 数据及索引文件
```sh
db
|------journal
           |----_j.0
           |----_j.1
           |----lsn
|------local
           |----local.ns
           |----local.0
           |----local.1
|------mydb
           |----mydb.ns
           |----mydb.0
           |----mydb.1
```
如果感兴趣可以参看 <a href='https://www.mongodb.com/zh-cn/docs/manual/storage/'>官方文档 - MMAPv1 Storage Engine</a>

如果你希望详解了解MongoDB MMAP的引擎（源码级别），你可以参考这篇<a href='https://cloud.tencent.com/developer/article/1004385'>MongoDB Mmap 引擎分析</a>

![51.mongo-y-arch-5.png](../../assets/images/06-中间件/mongodb/51.mongo-y-arch-5.png)

### 8.3.2 MongoDB - WiredTiger
> MongoDB v3.2已经将WiredTiger设置为了默认的存储引擎

![52.mongo-y-arch-6.png](../../assets/images/06-中间件/mongodb/52.mongo-y-arch-6.png)


- `collection-*.wt` 存储collection的数据
- `index-*.wt` 存储索引的数据
- `WiredTiger` 存储基本配置信息
- `WiredTiger.wt` 存储所有其它collection的元数据信息
- `WiredTiger.lock` 存储进程ID，用于防止多个进程连接同一个Wiredtiger数据库
- `WiredTiger.turtle` 存储WiredTiger.wt的元数据信息
- `journal` 存储Write ahead log

后续的文章将对WiredTiger存储引擎进行详解。

# 九、Mongo进阶 - 原理和WiredTiger引擎

WiredTiger从被MongoDB收购到成为MongoDB的默认存储引擎的一年半得到了迅猛的发展，也逐步被外部熟知。WiredTiger（以下简称WT）是一个优秀的单机数据库存储引擎，它拥有诸多的特性，既支持BTree索引，也支持LSM Tree索引，支持行存储和列存储，实现ACID级别事务、支持大到4G的记录等。WT的产生不是因为这些特性，而是和计算机发展的现状息息相关。

现代计算机近20年来CPU的计算能力和内存容量飞速发展，但磁盘的访问速度并没有得到相应的提高，WT就是在这样的一个情况下研发出来，它设计了充分利用CPU并行计算的内存模型的无锁并行框架，使得WT引擎在多核CPU上的表现优于其他存储引擎。针对磁盘存储特性，WT实现了一套基于BLOCK/Extent的友好的磁盘访问算法，使得WT在数据压缩和磁盘I/O访问上优势明显。实现了基于snapshot技术的ACID事务，snapshot技术大大简化了WT的事务模型，摒弃了传统的事务锁隔离又同时能保证事务的ACID。WT根据现代内存容量特性实现了一种基于Hazard Pointer 的LRU cache模型，充分利用了内存容量的同时又能拥有很高的事务读写并发。

<a href='https://mongoing.com/archives/2540'>MongoDB Wiredtiger存储引擎实现原理</a>

<a href='https://mongoing.com/archives/5367'>MongoDB 存储引擎 WiredTiger 原理解析</a>

<a href='https://github.com/wiredtiger/wiredtiger/wiki/Block-Manager-Overview'>Block Manager Overview</a> 


| 特性维度 | **MMAPv1 存储引擎** | **WiredTiger 存储引擎** | **对比分析与影响** |
| :--- | :--- | :--- | :--- |
| **存储机制与锁粒度** | **集合级锁**。任何写操作都会锁住整个集合。`in-place update`策略，文档更新时若空间不足，会触发昂贵的文档搬迁和索引更新。 | **文档级锁**。写操作只锁定单个文档，并发能力极强。使用**写时复制** 和 **MVCC** 。更新文档时，先在磁盘新位置写入新版本，再更新一个指向新版本的指针。 | **这是最核心的改进**。MMAP的集合级锁在写入频繁的场景下是灾难性的，并发线程会严重阻塞。WiredTiger的文档级锁和MVCC使得读写、写写（针对不同文档）可以高度并发，性能呈数量级提升。 |
| **数据压缩** | **不支持压缩**。数据以磁盘格式原样加载到内存，占用大量内存和磁盘空间。 | **支持多级压缩**（默认使用Snappy块压缩）。可以单独为**集合数据**和**索引**选择压缩算法（如zlib, zstd）。 | **WiredTiger大幅节省存储成本**。在SSD时代，CPU压缩/解压的速度远快于磁盘IO。压缩后通常能节省50%-80%的存储空间，同时降低IO压力，提升缓存效率。这是MMAP无法比拟的硬件利用率优势。 |
| **内存与缓存管理** | **依赖操作系统页面缓存**。MongoDB进程将数据文件映射到内存，具体哪些数据在物理内存中由操作系统LRU算法决定。 | **独立的内部缓存**（默认为可用内存的50%）。WiredTiger自己管理缓存，使用LRU算法，并理解数据结构（B-Tree）。数据在缓存中是**解压后的格式**，在写入磁盘前进行压缩。 | **WiredTiger的控制更精细高效**。独立缓存避免了与系统其他进程竞争内存，且解压态缓存使得热数据的访问速度极快。MMAP的依赖OS策略则显得粗放且不可控。 |
| **日志与崩溃恢复** | 使用`journal`日志保证持久性。但其写操作（如文档搬迁）可能比较重，影响恢复速度。 | 同样使用`journal`，但其**预写日志记录的是操作日志**，而不是完整的数据页，通常更高效。结合MVCC和检查点机制，恢复速度更快、更可预测。 | WiredTiger的恢复机制更现代、更健壮，在意外断电等场景下，能提供更快、更可靠的数据恢复。 |
| **磁盘空间与碎片** | **高碎片化问题严重**。由于`in-place update`和文档搬迁，会产生大量不可重复使用的磁盘碎片，需要定期运行`compact`或`repairDatabase`来回收空间，这个过程会阻塞数据库且耗时极长。 | **几乎无碎片问题**。采用写时复制，老空间会被新版本释放，并由WiredTiger的底层存储管理器高效回收和复用。后台清理线程自动处理，无需昂贵的离线整理操作。 | **运维的噩梦 vs 运维的福音**。MMAP的碎片问题在生产中是一个巨大的运维负担。WiredTiger基本消除了这个问题，使数据库能长期稳定运行。 |
| **扩展性** | 主要通过分片进行水平扩展。在单个节点上，受限于锁和IO模型，纵向扩展收益有限。 | 在单个节点上就能利用多核CPU、大内存和高速SSD实现极高的并发性能，为水平分片提供了更强大的单节点基础。 | WiredTiger的架构是为**横向扩展**和**纵向扩展**同时优化的，能更好地利用现代硬件资源。 |

# 十、Mongo进阶 - WT引擎：数据结构

> MongoDB的WiredTiger存储引擎背后采用了什么样的数据结构呢？本文将从`常见引擎数据结构`，`典型B-Tree数据结构`，`磁盘数据结构`, `内存数据结构`, `Page数据结构`等方面详解介绍。
## 10.1 存储引擎及常见数据结构
**存储引擎要做的事情无外乎是将磁盘上的数据读到内存并返回给应用，或者将应用修改的数据由内存写到磁盘上**。如何设计一种高效的数据结构和算法是所有存储引擎要考虑的根本问题，目前大多数流行的存储引擎是基于B-Tree或LSM(Log Structured Merge 日志结构化合并) Tree这两种数据结构来设计的。

- B-Tree

像Oracle、SQL Server、DB2、MySQL (InnoDB)和PostgreSQL这些传统的关系数据库依赖的底层存储引擎是基于B-Tree开发的；

- LSM Tree

像Cassandra、Elasticsearch (Lucene)、Google Bigtable、Apache HBase、LevelDB和RocksDB这些当前比较流行的NoSQL数据库存储引擎是基于LSM开发的。

- 插件式兼容上述两种

当然有些数据库采用了插件式的存储引擎架构，实现了Server层和存储引擎层的解耦，可以支持多种存储引擎，如MySQL既可以支持B-Tree结构的`InnoDB`存储引擎，还可以支持LSM结构的`RocksDB`存储引擎。

> 对于MongoDB来说，也采用了插件式存储引擎架构，底层的WiredTiger存储引擎还可以支持B-Tree和LSM两种结构组织数据,但MongoDB在使用WiredTiger作为存储引擎时，**目前默认配置是使用了B-Tree结构。**

因此，本章后面的内容将以B-Tree为核心来分析MongoDB是如何将文档数据在磁盘和内存间进行流传以及WiredTiger存储引擎的其它高级特性。

## 10.2 B-tree 和 LSM 的对比

存储引擎的核心命题，正是在于如何高效地组织磁盘上的数据，以平衡**读、写、空间放大**这三者之间的永恒矛盾。B-Tree和LSM-Tree是当今最主流的两种设计哲学，它们做出了截然不同的权衡。

下面我将从多个维度对它们进行详细对比，并总结其优劣和适用场景。

---

### 10.2.1 核心设计哲学对比

| 特性 | **B-Tree (B+树为主)** | **LSM-Tree (日志结构合并树)** |
| :--- | :--- | :--- |
| **核心理念** | **就地更新**。数据存储在固定位置的页中，更新直接在原位置进行（或通过标记删除+追加）。 | **异地更新 / 追加写入**。所有写入（包括增、删、改）都转化为顺序写入的新记录，旧数据通过后台合并过程清理。 |
| **数据结构** | 一个平衡的、分层的、节点大小固定（如4KB, 16KB）的树状结构。叶子节点存储有序的数据或索引。 | 多个层次化的有序结构（通常称为SSTable）。最新的数据在**内存表**中，之后被顺序刷到**磁盘上的多层文件**中。 |
| **写入流程** | 1. 查找数据所在页。<br>2. 将页读入内存（若不在缓存）。<br>3. **在内存中修改页**。<br>4. 将页**写回原磁盘位置**（随机写）。<br>5. 可能涉及页分裂、树平衡等操作。 | 1. 写入先进入**内存中的有序结构**（MemTable），如跳表。<br>2. MemTable写满后，**冻结**并转换为不可变的SSTable文件，**顺序写入**磁盘（L0层）。<br>3. 后台线程将多个小的SSTable**合并**成更大的、有序的SSTable，并下推到更深层（如L1, L2...）。 |
| **读取流程** | 从根节点开始，通过索引二分查找，只需几次磁盘I/O（树的高度决定）即可定位到目标数据所在的精确页。 | 1. 先查内存中的MemTable。<br>2. 再**从新到旧**逐层查询磁盘上的SSTable文件（L0 -> L1 -> L2...）。<br>3. 可能需要查询多个SSTable文件，但每个文件内部有序，可通过布隆过滤器快速跳过不包含该键的文件。 |
| **删除流程** | **标记删除**：在数据页中标记记录为删除，或使用“墓碑”记录。空间回收复杂（如B+树的记录删除）。 | **追加写入一个“墓碑”记录**。在后台合并时，墓碑记录会覆盖或清除旧的键值对。 |
| **空间放大** | 较低。由于是就地更新，没有多份数据副本（但存在因页分裂、删除产生的内部碎片）。 | 较高。在合并发生前，同一键的多个版本（旧值、新值、墓碑）会同时存在，造成暂时性的空间放大。 |
| **关键操作** | **读取、随机写入、事务**。 | **顺序写入、后台合并、范围查询**。 |

---

### 10.2.2 优势与劣势深度分析

#### 10.2.2.1 **B-Tree 的优势**
1.  **卓越的读取性能**：读路径确定，通常只需O(log N)次I/O，且是点查和范围查询的理想选择（叶子节点链表）。
2.  **低延迟、可预测的查询**：每次查询的延迟相对稳定，没有LSM那种由后台合并引发的长尾延迟。
3.  **原生支持复杂事务**：基于页锁或多版本并发控制（MVCC）可以相对直接地实现ACID事务（如MySQL InnoDB），因为数据有“唯一真实位置”。
4.  **空间放大可控**：没有长期的数据冗余。

#### 10.2.2.2 **B-Tree 的劣势**
1.  **写入放大严重**：一次小的写入（如更新一个字段）可能引发**读取页 -> 修改页 -> 写回整个页（随机写）**，同时可能触发昂贵的**页分裂和树再平衡**操作。在SSD上，这会浪费写入寿命。
2.  **对随机写入不友好**：随机写入导致磁盘磁头频繁寻道（HDD时代）或写磨损不均（SSD时代）。
3.  **需要精细的并发控制**：高并发写入时，对树节点（尤其是根节点、中间节点）的锁竞争激烈，需要复杂的锁机制（如锁耦合、乐观锁）来优化。
4.  **存储空间碎片化**：多次更新和删除后，页内和页间会产生碎片，需要定期进行`OPTIMIZE TABLE`或`VACUUM`来重组数据，这是一个重量级操作。

#### 10.2.2.3 **LSM-Tree 的优势**
1.  **恐怖的写入吞吐量**：这是其最核心的优势。所有写入都是**顺序追加**，完全避免了随机写。在HDD上，顺序写带宽远高于随机写；在SSD上，顺序写对闪存更友好，能发挥其最大带宽。
2.  **更好的压缩率**：SSTable文件是不可变的，可以轻松应用高压缩比算法（如ZSTD），且压缩在后台合并时进行，不影响前端写入。
3.  **天然消除写放大（在理想情况下）**：后台合并过程将大量小写入批量处理为顺序大写入，减少了SSD的实际写入量（但带来了读放大和空间放大）。
4.  **简化并发控制**：MemTable写入通常只需一个互斥锁或使用无锁结构。不可变的SSTable文件可以被安全地并发读取。

#### 10.2.2.4 **LSM-Tree 的劣势**
1.  **读取性能相对较差（尤其是点查）**：可能需要检查多个层次的文件，虽然布隆过滤器极大缓解了此问题，但仍比B-Tree的一次精准定位慢。这被称为**读放大**。
2.  **范围查询可能更慢**：如果键的范围跨多个SSTable文件，需要多路归并，比遍历B+树叶子链表更复杂。
3.  **后台合并带来的问题**：
    *   **写放大**：数据在合并过程中被反复重写。
    *   **资源竞争**：合并是CPU和I/O密集型操作，可能与前端读写争抢资源，导致**查询延迟出现长尾尖峰**，不可预测性增加。
    *   **配置复杂**：合并策略（大小分层、水平分层）、层数、触发条件等参数需要精心调优，对不同负载很敏感。
4.  **空间放大**：在合并间隔期内，存在多版本数据，占用额外空间。

---

### 10.2.3 总结与适用场景

| 场景 | **推荐数据结构** | **理由** |
| :--- | :--- | :--- |
| **读密集、点查和范围查询为主**<br>（如OLTP核心业务库、内容管理系统） | **B-Tree** | 读延迟低且稳定，事务支持成熟。 |
| **写密集、读相对较少**<br>（如物联网传感器数据、应用日志、实时分析数据摄入） | **LSM-Tree** | 写入吞吐量是核心指标，顺序写入优势巨大。 |
| **存储成本敏感，需要高压缩**<br>（如归档数据、监控历史数据） | **LSM-Tree** | 不可变文件便于进行高效压缩。 |
| **延迟敏感，要求响应时间可预测**<br>（如在线交易、实时交互应用） | **B-Tree** | 避免后台合并带来的查询延迟抖动。 |
| **SSD存储环境** | **两者皆可，但考量不同** | B-Tree仍需面对随机写放大问题；LSM-Tree的顺序写对SSD更友好，但需注意合并带来的写放大对SSD寿命的影响。 |
| **需要高效更新和删除** | **B-Tree** | 原地更新更直接。LSM-Tree的删除需要墓碑和合并，是延迟生效的。 |

**现代数据库的融合趋势**：
许多现代存储引擎并非纯正的B-Tree或LSM，而是吸取双方优点：
*   **WiredTiger (B-Tree变体)**： 使用**写时复制**的B-Tree，更新不直接覆盖原页，而是写入新页并更新父节点指针。这减少了锁争用，并使得压缩和快照更容易实现，是B-Tree架构的一种优化。
*   **Fractal Tree/Tokutek**： 在B-Tree的非叶子节点中加入“消息缓冲区”，将小写入批量下推，减少了随机I/O，吸收了LSM批量处理的思路。
*   **带有Buffer Pool的LSM**： 一些LSM实现会使用更大的内存缓冲区或Block Cache来缓解读延迟问题。

## 10.3 典型B-Tree数据结构
> B-Tree是为磁盘或其它辅助存储设备而设计的一种数据结构，目的是为了在查找数据的过程中减少磁盘I/O的次数。
一个典型的B-Tree结构如下图所示：

![53.mongo-y-ds-1.jpg](../../assets/images/06-中间件/mongodb/53.mongo-y-ds-1.jpg)

在整个B-Tree中，从上往下依次为Root结点、内部结点和叶子结点，每个结点就是一个Page，数据以Page为单位在内存和磁盘间进行调度，每个Page的大小决定了相应结点的分支数量，每条索引记录会包含一个数据指针，指向一条数据记录所在文件的偏移量。

如上图，假设每个结点100个分支，那么所有叶子结点合起来可以包含100万个键值（等于100100100）。通常情况下Root结点和内部结点的Page会驻留在内存中，所以查找一条数据可能只需2次磁盘I/O。但随着数据不断的插入、删除，会涉及到B-Tree结点的分裂、位置提升及合并等操作，因此维护一个B-Tree的平衡也是比较耗时的。

## 10.4 WiredTiger数据文件在磁盘上的数据结构
对于WiredTiger存储引擎来说，集合所在的数据文件和相应的索引文件都是按B-Tree结构来组织的，**不同之处在于数据文件对应的B-Tree叶子结点上除了存储键名外（keys），还会存储真正的集合数据（values）**，所以数据文件的存储结构也可以认为是一种B+Tree，其整体结构如下图所示：

![54.mongo-y-ds-2.jpg](../../assets/images/06-中间件/mongodb/54.mongo-y-ds-2.jpg)

从上图可以看到，B+ Tree中的leaf page包含一个页头（page header）、块头（block header）和真正的数据（key/value），其中页头定义了页的类型、页中实际载荷数据的大小、页中记录条数等信息；块头定义了此页的checksum、块在磁盘上的寻址位置等信息。

WiredTiger有一个块设备管理的模块，用来为page分配block。如果要定位某一行数据（key/value）的位置，可以先通过block的位置找到此page（相对于文件起始位置的偏移量），再通过page找到行数据的相对位置，最后可以得到行数据相对于文件起始位置的偏移量offsets。由于offsets是一个8字节大小的变量，所以WiredTiger磁盘文件的大小，其最大值可以非常大(264bit)。

## 10.5 WiredTiger内存上的基础数据结构
WiredTiger会按需将磁盘的数据**以page为单位加载到内存**，同时在内存会构造相应的B-Tree来存储这些数据。**为了高效的支撑CRUD等操作以及将内存里面发生变化的数据持久化到磁盘上**，WiredTiger也会在内存里面维护其它几种数据结构，如下图所示：

![55.mongo-y-ds-3.jpg](../../assets/images/06-中间件/mongodb/55.mongo-y-ds-3.jpg)

上图是WiredTiger在内存里面的大概布局图，通过它我们可梳理清楚存储引擎是如何将数据加载到内存，然后如何通过相应数据结构来支持查询、插入、修改操作的。

- 内存里面B-Tree包含三种类型的page，即rootpage、internal page和leaf page，前两者包含指向其子页的page index指针，不包含集合中的真正数据，leaf page包含集合中的真正数据即keys/values和指向父页的home指针；
- 内存上的leaf page会维护一个`WT_ROW`结构的数组变量，将保存从磁盘leaf page读取的keys/values值，每一条记录还有一个cell_offset变量，表示这条记录在page上的偏移量；
- 内存上的leaf page会维护一个`WT_UPDATE`结构的数组变量，每条被修改的记录都会有一个数组元素与之对应，如果某条记录被多次修改，则会将所有修改值以链表形式保存。
- 内存上的leaf page会维护一个`WT_INSERT_HEAD`结构的数组变量，具体插入的data会保存在`WT_INSERT_HEAD`结构中的`WT_UPDATE`属性上，且通过key属性的offset和size可以计算出此条记录待插入的位置；同时，为了提高寻找待插入位置的效率，每个`WT_INSERT_HEAD`变量以跳转链表的形式构成。

下图是一个跳转链表的插入示例：

![56.mongo-y-ds-4.jpg](../../assets/images/06-中间件/mongodb/56.mongo-y-ds-4.jpg)

假如现在插入一个16，最终结果如下：

![57.mongo-y-ds-5.jpg](../../assets/images/06-中间件/mongodb/57.mongo-y-ds-5.jpg)

如果是一个普通的链表，寻找合适的插入位置时，需要经过：

开始结点->2->5->8->10->20的比较；

对于跳转链表来说只需经过：开始结点->5->10->20的比较，可以看到比在普通链表上寻找插入位置时需要的比较步骤少，所以，**通过跳转链表的数据结构能够提升插入操作的效率。**

## 10.6 page的其它数据结构
对于一个面向行存储的leaf page来说，包含的数据结构除了上面提到的`WT_ROW`（keys/values）、`WT_UPDATE`（修改数据）、`WT_INSERT_HEAD`（插入数据）外，还有如下几种重要的数据结构：

- WT_PAGE_MODIFY：

保存page上事务、脏数据字节大小等与page修改相关的信息；

- read_gen：

page的read generation值作为evict page时使用，具体来说对应page在LRU队列中的位置，决定page被evict server选中淘汰出去的先后顺序。

- WT_PAGE_LOOKASIDE：

page关联的lookasidetable数据。当对一个page进行reconcile时，如果系统中还有之前的读操作正在访问此page上修改的数据，则会将这些数据保存到lookasidetable；当page再被读时，可以利用lookasidetable中的数据重新构建内存page.

- WT_ADDR：

page被成功reconciled后，对应的磁盘上块的地址，将按这个地址将page写到磁盘，块是最小磁盘上文件的最小分配单元，一个page可能有多个块。

- checksum：

page的校验和，如果page从磁盘读到内存后没有任何修改，比较checksum可以得到相等结果，那么后续reconcile这个page时，不会将这个page的再重新写入磁盘。

# 十一、Mongo进阶 - WT引擎：Page生命周期

> 通过前文我们了解到数据以page为单位加载到cache; 有必要系统的分析一页page的生命周期、状态以及相关参数的配置，这对后续MongoDB的性能调优和故障问题的定位和解决有帮助。

## 11.1 为什么要了解Page生命周期
通过前文我们了解到数据以page为单位加载到cache、cache里面又会生成各种不同类型的page及为不同类型的page分配不同大小的内存、eviction触发机制和reconcile动作都发生在page上、page大小持续增加时会被分割成多个小page，所有这些操作都是围绕一个page来完成的。

因此，有必要系统的分析一页page的生命周期、状态以及相关参数的配置，这对后续MongoDB的性能调优和故障问题的定位和解决有帮助。

## 11.2 Page的生命周期
Page的典型生命周期如下图所示：

![58.mongo-y-page-1.png](../../assets/images/06-中间件/mongodb/58.mongo-y-page-1.png)

- 第一步：pages从磁盘读到内存；

- 第二步：pages在内存中被修改；

- 第三步：被修改的脏pages在内存被reconcile，完成后将discard这些pages。

- 第四步：pages被选中，加入淘汰队列，等待被evict线程淘汰出内存；

- 第五步：evict线程会将“干净“的pages直接从内存丢弃（因为相对于磁盘page来说没做任何修改），将经过reconcile处理后的磁盘映像写到磁盘再丢弃“脏的”pages。

pages的状态是在不断变化的，因此，对于读操作来说，它首先会检查pages的状态是否为`WT_REF_MEM`，然后设置一个hazard指针指向要读的pages，如果刷新后，pages的状态仍为`WT_REF_MEM`，读操作才能继续处理。

与此同时，evict线程想要淘汰pages时，它会先锁住pages，即将pages的状态设为`WT_REF_LOCKED`，然后检查pages上是否有读操作设置的hazard指针，如有，说明还有线程正在读这个page则停止evict，重新将page的状态设置为`WT_REF_MEM`；如果没有，则pages被淘汰出去。

## 11.3 Page的各种状态
针对一页page的每一种状态，详细描述如下：

- **WT_REF_DISK：** 初始状态，page在磁盘上的状态，必须被读到内存后才能使用，当page被evict后，状态也会被设置为这个。

- **WT_REF_DELETED：** page在磁盘上，但是已经从内存B-Tree上删除，当我们不在需要读某个leaf page时，可以将其删除。

- **WT_REF_LIMBO：** page的映像已经被加载到内存，但page上还有额外的修改数据在lookasidetable上没有被加载到内存。

- **WT_REF_LOOKASIDE：** page在磁盘上，但是在lookasidetable也有与此page相关的修改内容，在page可读之前，也需要加载这部分内容。

当对一个page进行reconcile时，如果系统中还有之前的读操作正在访问此page上修改的数据，则会将这些数据保存到lookasidetable；当page再被读时，可以利用lookasidetable中的数据重新构建内存page。

- **WT_REF_LOCKED：** 当page被evict时，会将page锁住，其它线程不可访问。

- **WT_REF_MEM：** page已经从磁盘读到内存，并且能正常访问。

- **WT_REF_READING：** page正在被某个线程从磁盘读到内存，其它的读线程等待它被读完，不需要重复去读。

- **WT_REF_SPLIT：** 当page变得过大时，会被split，状态设为WT_REF_SPLIT，原来指向的page不再被使用。

## 11.4 Page的大小参数
无论将数据从磁盘读到内存，还是从内存写到磁盘，都是以page为单位调度的，但是在磁盘上一个page到底多大？是否是最小分割单元？以及内存里面的各种page的大小对存储引擎的性能是否有影响？本节将围绕这些问题，分析与page大小相关的参数是如何影响存储引擎性能的。 总的来说，涉及到的关键参数和默认值如下表所示：

| 参数名称 | 默认配置值 | 含义与作用 | 说明 |
|---------|-----------|-----------|------|
| **allocation_size** | 4KB | 磁盘上最小分配单元 | 文件系统分配磁盘空间的最小单位。WiredTiger会按此大小对齐分配，减少磁盘碎片。 |
| **memory_page_max** | 5MB | 内存中允许的最大page值 | 单个页面在内存中的最大尺寸。超过此大小的页面会被分割。 |
| **internal_page_max** | 4KB | 磁盘上允许的最大internal page值 | 内部节点（非叶子节点）在磁盘上的最大大小。控制索引层级和搜索深度。 |
| **leaf_page_max** | 32KB | 磁盘上允许的最大leaf page值 | 叶子节点（存储实际数据）在磁盘上的最大大小。影响单页存储的数据量。 |
| **internal_key_max** | `internal_page_max / 10` | internal page上允许的最大key值 | 单个内部节点中存储的最大键值大小。防止过大键值影响索引性能。 |
| **leaf_key_max** | `leaf_page_max / 10` | leaf page上允许的最大key值 | 单个叶子节点中存储的最大键值大小。控制键值对在页面中的分布。 |
| **leaf_key_value** | `leaf_page_max / 2` | leaf page上允许的最大value值 | 单个叶子节点中存储的最大value值大小。影响大文档的存储方式。 |
| **split_pct** | 75% | reconciled的page的分割百分比 | 触发页面分裂的阈值。当页面使用率超过此百分比时，可能会触发分裂。 |


详细说明如下：

- **allocation_size：**

MongoDB磁盘文件的最小分配单元（由WiredTiger自带的块管理模块来分配），一个page的可以由一个或多个这样的单元组成；默认值是4KB，与主机操作系统虚拟内存页的大小相当，大多数场景下不需要修改这个值。

- **memory_page_max：**

WiredTigerCache里面一个内存page随着不断插入修改等操作，允许增长达到的最大值，默认值为5MB。当一个内存page达到这个最大值时，将会被split成较小的内存pages且通过reconcile将这些pages写到磁盘pages，一旦完成写到磁盘，这些内存pages将从内存移除。

需要注意的是：split和reconcile这两个动作都需要获得page的排它锁，导致应用程序在此page上的其它写操作会等待，因此设置一个合理的最大值，对系统的性能也很关键。

如果值太大，虽然spilt和reconcile发生的机率减少，但一旦发生这样的动作，持有排它锁的时间会较长，导致应用程序的插入或修改操作延迟增大；

如果值太小，虽然单次持有排它锁的时间会较短，但是会导致spilt和reconcile发生的机率增加。

- **internal_page_max：**

磁盘上internalpage的最大值，默认为4KB。随着reconcile进行，internalpage超过这个值时，会被split成多个pages。

这个值的大小会影响磁盘上B-Tree的深度和internalpage上key的数量，如果太大，则internalpage上的key的数量会很多，通过遍历定位到正确leaf page的时间会增加；如果太小，则B-Tree的深度会增加，也会影响定位到正确leaf page的时间。

- **leaf_page_max：**

磁盘上leaf page的最大值，默认为32KB。随着reconcile进行，leaf page超过这个值时，会被split成多个pages。

这个值的大小会影响磁盘的I/O性能，因为我们在从磁盘读取数据时，总是期望一次I/O能多读取一点数据，所以希望把这个参数调大；但是太大，又会造成读写放大，因为读出来的很多数据可能后续都用不上。

- **internal_key_max：**

internalpage上允许的最大key值，默认大小为internalpage初始值的1/10，如果超过这个值，将会额外存储。导致读取key时需要额外的磁盘I/O。

- **leaf_key_max：**

leaf page上允许的最大key值，默认大小为leaf page初始值的1/10，如果超过这个值，将会额外存储。导致读取key时需要额外的磁盘I/O。

- **leaf_value_max：**

leaf page上允许的最大value值（保存真正的集合数据），默认大小为leaf page初始值的1/2，如果超过这个值，将会额外存储。导致读取value时需要额外的磁盘I/O。

- **split_pct：**

内存里面将要被reconciled的 page大小与internal_page_max或leaf_page_max值的百分比，默认值为75%，如果内存里面被reconciled的page能够装进一个单独的磁盘page上，则不会发生spilt，否则按照该百分比值*最大允许的page值分割新page的大小。

## 11.5 **WiredTiger存储引擎内存管理机制** 的详细描述
### 11.5.1 核心概念解释

#### 11.5.1.1 **Page（页面/页）**
- 数据库管理的基本单位，通常是固定大小的数据块（如4KB、16KB）
- 包含部分索引键或文档数据
- 类似于操作系统的内存页，但这是数据库层面的抽象

#### 11.5.1.2 **状态术语解释**

| 术语 | 含义 |
|------|------|
| **WT_REF_MEM** | 页面当前在内存中，可以被正常读取和修改 |
| **WT_REF_LOCKED** | 页面被锁定，通常由evict线程设置，表示正在进行淘汰操作 |
| **Hazard指针** | 并发编程中的保护机制，用于标记"我正在使用这个页面" |

### 11.5.2 页面生命周期的五个步骤详解

#### 11.5.2.1 **第一步：从磁盘加载到内存**
```
磁盘 → 内存
```
- 当需要访问某个数据时，WiredTiger会从磁盘读取对应的页面到内存缓存中
- 此时页面状态变为 **WT_REF_MEM**（在内存中可用）
- 这是"干净"页面（与磁盘内容一致）

#### 11.5.2.2 **第二步：在内存中被修改**
```
内存页面 → 被应用程序修改 → 成为"脏页"
```
- 应用程序对数据进行插入、更新或删除操作
- 这些修改只在内存中进行，还未写入磁盘
- 此时页面成为"脏页"（dirty page）——内存内容与磁盘不一致

#### 11.5.2.3 **第三步：Reconcile（调和/写回磁盘）**
```
内存脏页 → 转换为磁盘格式 → 准备写入磁盘
```
- **Reconcile**：将内存中修改过的页面转换为可以写入磁盘的格式
- 完成后，这个页面在内存中的使命完成，可以被丢弃
- 但注意：这里的"写入磁盘"可能只是准备就绪，不一定立即物理写入

#### 11.5.2.4 **第四步：加入淘汰队列**
```
页面被选中 → 放入淘汰队列
```
- WiredTiger的内存缓存有限，需要淘汰一些页面腾出空间
- 选择算法（如LRU）决定哪些页面应该被淘汰
- 页面被标记为准备淘汰状态

#### 11.5.2.5 **第五步：Evict（驱逐/淘汰出内存）**
```
Evict线程处理淘汰：
1. 干净页面：直接丢弃（因为磁盘有相同副本）
2. 脏页面：先执行reconcile写入磁盘，然后丢弃
```
- **Evict线程**：专门负责淘汰页面的后台线程
- 淘汰前需要确保数据一致性

### 11.5.3 并发访问控制的精妙设计

#### 11.5.3.1 **问题场景**
- 读线程正在读取一个页面
- 同时，Evict线程想要淘汰这个页面
- 如何避免数据竞争？

#### 11.5.3.2 **解决方案：Hazard指针 + 状态检查**

##### 11.5.3.2.1 *读操作的保护流程：**
```c
1. 检查页面状态是否为 WT_REF_MEM（在内存中）
2. 如果是，设置 hazard 指针指向这个页面
3. 再次检查页面状态（双重检查）
4. 如果状态仍然是 WT_REF_MEM，安全地进行读取
5. 读取完成后，清除 hazard 指针
```

##### 11.5.3.2.2 **Evict线程的淘汰流程：**
```c
1. 锁住页面：将状态设为 WT_REF_LOCKED
2. 检查页面上是否有 hazard 指针（是否还有线程在读取）
3. 如果有 hazard 指针：
   - 取消淘汰操作
   - 将状态恢复为 WT_REF_MEM
   - 页面继续留在内存中
4. 如果没有 hazard 指针：
   - 安全地执行淘汰
   - 如果是脏页，先写入磁盘再丢弃
```

### 11.5.4 图解流程

```
┌─────────────────────────────────────────────────────────────┐
│                     页面生命周期                              │
├─────────────────────────────────────────────────────────────┤
│  磁盘加载      内存修改      Reconcile      淘汰队列        Evict    │
│    ↓             ↓             ↓             ↓             ↓      │
│  WT_REF_MEM → 脏页状态 → 磁盘准备就绪 → 待淘汰状态 → 从内存移除 │
│                                                            │
│                并发控制机制：                              │
│                读线程：设置hazard指针保护                  │
│                Evict线程：检查hazard指针决定是否淘汰       │
└─────────────────────────────────────────────────────────────┘
```

### 11.5.5 为什么需要这样设计？

#### 11.5.5.1 **1. 内存有限性**
- 服务器内存有限，不能无限缓存所有数据
- 需要智能地决定哪些数据留在内存，哪些淘汰

#### 11.5.5.2 **2. 数据一致性**
- 确保正在被读取的数据不会被突然删除
- 避免程序读到无效或正在被修改的数据

#### 11.5.5.3 **3. 性能优化**
- **减少磁盘I/O**：通过缓存热点数据
- **异步淘汰**：Evict线程在后台工作，不阻塞前台操作
- **批量写入**：脏页可以批量写入磁盘，提高效率

#### 11.5.5.4 **4. 并发安全**
- 多线程同时访问相同页面时的安全性
- 避免"使用已释放内存"的严重错误

### 11.5.6 现实世界的类比

想象一个图书馆：

1. **从仓库取书（磁盘→内存）**：读者要的书不在书架上，从仓库取出
2. **读者做笔记（内存修改）**：读者在书上做标记（变"脏"了）
3. **图书管理员整理（Reconcile）**：把读者的笔记整理到正本
4. **放入待归还区（淘汰队列）**：准备放回仓库的书
5. **放回仓库（Evict）**：但如果有读者还在看，就不能收走

**Hazard指针就像**：读者在书里夹一张纸条"我正在看这本书，请勿收回"

### 11.5.7 WiredTiger的独特优势

1. **精细的锁控制**：页面级而不是集合/表级
2. **写时复制（Copy-on-Write）**：修改不覆盖原页，而是创建新页
3. **检查点机制**：定期将脏页批量写入磁盘，减少崩溃恢复时间
4. **可配置的缓存**：可以设置缓存大小，自动管理

这种设计让WiredTiger在**高并发写入**场景下表现优异，多个线程可以同时修改不同的页面而不会相互阻塞，同时保证了数据的一致性和内存的高效使用。

## 11.6 Page无锁及压缩
https://blog.csdn.net/weixin_45583158/article/details/100143033

# 十二、Mongo进阶 - WT引擎：checkpoint原理

> Checkpoint主要有两个目的: 一是将内存里面发生修改的数据写到数据文件进行持久化保存，确保数据一致性；二是实现数据库在某个时刻意外发生故障，再次启动时，缩短数据库的恢复时间，WiredTiger存储引擎中的Checkpoint模块就是来实现这个功能的。

## 12.1 为什么要理解Checkpoint
总的来说，Checkpoint主要有两个目的：

- 一是将内存里面发生修改的数据写到数据文件进行持久化保存，确保数据一致性；
- 二是实现数据库在某个时刻意外发生故障，再次启动时，缩短数据库的恢复时间，WiredTiger存储引擎中的Checkpoint模块就是来实现这个功能的。
## 12.2 Checkpoint包含的关键信息
本质上来说，Checkpoint相当于一个日志，记录了上次Checkpoint后相关数据文件的变化。

一个Checkpoint包含关键信息如下图所示：

![59.mongo-x-checkpoint-1.png](../../assets/images/06-中间件/mongodb/59.mongo-x-checkpoint-1.png)

每个checkpoint包含一个root page、三个指向磁盘具体位置上pages的列表以及磁盘上文件的大小。

我们可以通过WiredTiger提供的wt命令工具（工具需要单独编译，下一篇会讲解如何编译安装wt工具）查看每个checkpoints具体信息。

例如，在dbPath指定的data目录下执行如下命令：
```sh
wt list -c
```
输出集合对应数据文件和索引文件的checkpoints信息：

如数据文件file:collection-7-16963667508695721.wt的checkpoint信息：
```sh
WiredTigerCheckpoint.1:Sat Apr 11 08:35:59 2020 (size 8 KB)
       file-size: 16 KB, checkpoint-size: 4 KB
               offset, size, checksum
       root   : 8192, 4096, 3824871989 (0xe3faea35)
       alloc  : 12288, 4096, 4074814944 (0xf2e0bde0)
       discard : 0, 0, 0 (0)
       avail  : 0, 0, 0 (0)
```
如索引文件file:index-8-16963667508695721.wt的checkpoint信息：
```sh
WiredTigerCheckpoint.1:Sat Apr 11 08:35:59 2020 (size 8 KB)
       file-size: 16 KB, checkpoint-size: 4 KB
               offset, size, checksum
       root   : 8192, 4096, 997122142 (0x3b6ee05e)
       alloc  : 12288, 4096, 4074814944 (0xf2e0bde0)
       discard : 0, 0, 0 (0)
       avail  : 0, 0, 0 (0)
```
详细字段信息描述如下：

- root page：

包含rootpage的大小（size），在文件中的位置（offset），校验和（checksum），创建一个checkpoint时，会生成一个新root page。

- allocated list pages：

用于记录最后一次checkpoint之后，在这次checkpoint执行时，由WiredTiger块管理器新分配的pages，会记录每个新分配page的size，offset和checksum。

- discarded list pages：

用于记录最后一次checkpoint之后，在这次checkpoint执行时，丢弃的不在使用的pages，会记录每个丢弃page的size，offset和checksum。

- available list pages：

在这次checkpoint执行时，所有由WiredTiger块管理器分配但还没有被使用的pages；当删除一个之前创建的checkpoint时，它所附带的可用pages将合并到最新的这个checkpoint的可用列表上，也会记录每个可用page的size，offset和checksum。

- file size： 在这次checkpoint执行后，磁盘上数据文件的大小。

## 12.3 Checkpoint执行的完整流程
Checkpoint是数据库中一个比较耗资源的操作，何时触发执行以及以什么样的流程执行是本节要研究的内容，如下所述：

执行流程：

一个checkpoint典型执行流程如下图所述：

![60.mongo-x-checkpoint-2.png](../../assets/images/06-中间件/mongodb/60.mongo-x-checkpoint-2.png)

流程描述如下：

- 查询集合数据时，会打开集合对应的数据文件并读取其最新checkpoint数据；

- 集合文件会按checkponit信息指定的大小（file size）被truncate掉，所以系统发生意外故障，恢复时可能会丢失checkponit之后的数据（如果没有开启Journal）；

- 在内存构造一棵包含root page的live tree，表示这是当前可以修改的checkpoint结构，用来跟踪后面写操作引起的文件变化；其它历史的checkpoint信息只能读，可以被删除；

- 内存里面的page随着增删改查被修改后，写入并需分配新的磁盘page时，将会从livetree中的available列表中选取可用的page供其使用。随后，这个新的page被加入到checkpoint的allocated列表中；

- 如果一个checkpoint被删除时，它所包含的allocated和discarded两个列表信息将被合并到最新checkpoint的对应列表上；任何不再需要的磁盘pages，也会将其引用添加到live tree的available列表中；

- 当新的checkpoint生成时，会重新刷新其allocated、available、discard三个列表中的信息，并计算此时集合文件的大小以及rootpage的位置、大小、checksum等信息，将这些信息作checkpoint元信息写入文件；

- 生成的checkpoint默认名称为WiredTigerCheckpoint，如果不明确指定其它名称，则新check point将自动取代上一次生成的checkpoint。

## 12.4 Checkpoint执行的触发时机
触发checkpoint执行，通常有如下几种情况：

- 按一定时间周期：默认60s，执行一次checkpoint；
- 按一定日志文件大小：当Journal日志文件大小达到2GB（如果已开启），执行一次checkpoint；
- 任何打开的数据文件被修改，关闭时将自动执行一次checkpoint。

注意：checkpoint是一个相当重量级的操作，当对集合文件执行checkpoint时，会在文件上获得一个排它锁，其它需要等待此锁的操作，可能会出现EBUSY的错误。

## 12.5 checkpoint+journal是WT实现持久化架构的基础
WiredTiger（以及所有现代数据库）通过多种机制来防止这种情况发生。

让我详细解释WiredTiger的持久化保障机制：

## 一、核心问题：崩溃导致数据丢失

```text
客户端写入 → 内存页修改(脏页) → 尚未写入磁盘 → 服务器崩溃 → 数据丢失❌
```

###  二、解决方案：WiredTiger的持久化架构

### # **1. 预写日志 (Write-Ahead Logging, WAL)**

这是**最关键**的机制。WiredTiger不直接依赖内存页写入磁盘来保证持久性，而是使用**重做日志 (Redo Log)**。

```text
标准流程：
1. 客户端发起写操作
2. WiredTiger先将操作记录到 **Journal（日志）** 文件中
3. 日志刷新到磁盘（确保持久化）
4. 然后在内存中修改页面（创建脏页）
5. 脏页异步写入数据文件
```

### # **2. 详细流程：双重保障**

```python
def process_write_operation(data):
    # 步骤1: 记录日志（同步或异步）
    log_entry = create_log_entry(data, operation="INSERT")
    fsync(journal_file, log_entry)  # 确保日志落盘
    
    # 步骤2: 内存修改
    page = get_page_from_cache(key)
    page.modify(data)
    mark_page_as_dirty(page)  # 标记为脏页
    
    # 步骤3: 返回客户端成功
    return "Write acknowledged"
    
    # 步骤4: 后台异步处理
    # - Evict线程将脏页写入数据文件
    # - 创建检查点
```

## # 三、WiredTiger的持久化组件

####  **Journal（日志文件）**
- **位置**：`journal/` 目录下的 `WiredTigerLog.*` 文件
- **作用**：记录所有修改操作的重做日志
- **特性**：
  - 循环写入，文件大小固定
  - 默认每个100MB，保留一定数量的文件
  - 记录逻辑操作而非完整页面

### **检查点 (Checkpoint)**
- **作用**：定期将内存中的脏页批量写入数据文件
- **频率**：
  - 默认60秒一次
  - 或日志达到2GB时触发
  - 可以手动触发
- **优点**：
  - 减少恢复时间
  - 批量写入提高效率
  - 创建一致性快照

### 四、崩溃恢复过程

当MongoDB重新启动时：

```text
崩溃恢复流程：
1. 找到最后一个有效的检查点
2. 从检查点开始重放日志文件中的操作
3. 将数据库恢复到崩溃前的状态
4. 清理未完成的事务
```

#### **恢复示例**
```
时间轴：
T0: 检查点C1创建（数据文件状态一致）
T1: 操作O1写入日志，内存修改
T2: 操作O2写入日志，内存修改  
T3: 服务器崩溃
T4: 重启，从C1开始恢复
T5: 重放O1、O2的日志记录
T6: 数据库恢复到T3时刻状态
```

### 五、配置选项与数据安全级别

#### **1. Journal提交方式**
```javascript
// MongoDB写入关注级别
db.collection.insert(data, {
  writeConcern: {
    w: 1,                    // 确认写入主节点
    j: true,                 // true=等待日志刷新，false=不等待
    wtimeout: 5000           // 超时时间
  }
})
```

#### **2. 日志配置参数**
```yaml
mongod配置:
  storage:
    journal:
      enabled: true          # 启用日志（默认）
      commitIntervalMs: 100  # 日志组提交间隔(毫秒)
      # 或使用 durability 配置
```

#### **3. 持久性级别**
| 配置 | 数据安全性 | 性能影响 |
|------|-----------|----------|
| `j:true` | **最高**，每次写入等待日志刷新 | 性能最低，延迟高 |
| `j:false` | **较低**，依靠定期刷新 | 性能最高，延迟低 |
| `w:majority` | **集群安全**，多节点确认 | 中等性能影响 |

### 六、实际示例：不同场景下的行为

### **场景1：默认配置（最安全）**
```bash
# mongod启动（默认）
mongod --dbpath /data/db

# 此时：
# 1. journal.enabled = true
# 2. 写操作默认 j:false（不等待日志刷新）
# 3. 但日志每100ms或每100MB刷新一次

# 这意味着：
# - 最多丢失100ms内的写入
# - 性能与安全的良好平衡
```

#### **场景2：最高安全性**
```javascript
// 每个写操作都等待日志刷新
db.runCommand({
  insert: "orders",
  documents: [{ item: "book", price: 20 }],
  writeConcern: { j: true, w: 1 }
});

// 或者修改默认写关注
db.adminCommand({
  setDefaultRWConcern: 1,
  defaultWriteConcern: { w: "majority", j: true }
});
```

#### **场景3：禁用日志（不推荐生产）**
```bash
# 仅用于测试或可丢失数据的场景
mongod --dbpath /data/db --nojournal

# 风险：
# - 系统崩溃 = 数据丢失
# - 只能依赖检查点（默认60秒）
# - 最多丢失60秒数据
```

### 七、性能与安全的权衡

#### **Benchmark对比**
| 配置 | 写入吞吐量 | 平均延迟 | 数据安全性 |
|------|-----------|----------|-----------|
| 无日志 | 10,000 ops/sec | 5ms | 最低（丢失60秒数据） |
| 日志异步 | 8,000 ops/sec | 8ms | 中等（丢失≤100ms数据） |
| 日志同步(j:true) | 3,000 ops/sec | 35ms | 最高（基本不丢失） |

#### **优化建议**
1. **使用SSD**：大幅提升日志写入性能
2. **独立日志磁盘**：日志和数据文件分开存储
3. **调整提交间隔**：根据业务需求平衡
4. **使用副本集**：多节点冗余，更高的可用性

### 八、副本集提供的额外保护

在生产环境中，通常使用MongoDB副本集：

```text
写入流程：
1. 客户端写入主节点
2. 主节点写入本地日志
3. 主节点将操作复制到从节点
4. 从节点确认后，主节点返回客户端
5. 数据在多个节点上有副本

即使主节点完全损坏：
1. 自动选举新主节点
2. 数据可以从从节点恢复
3. 几乎没有数据丢失
```

### 九、故障模拟与恢复测试

#### **测试崩溃恢复**
```bash
# 1. 插入测试数据
mongo test --eval "for(i=0;i<1000;i++){db.data.insert({x:i})}"

# 2. 模拟崩溃（kill -9）
pkill -9 mongod

# 3. 重启MongoDB
mongod --dbpath /data/db --fork --logpath /var/log/mongodb.log

# 4. 检查数据完整性
mongo test --eval "printjson(db.data.count())"
```

#### **监控日志状态**
```javascript
// 查看日志统计
db.serverStatus().wiredTiger.log;

// 输出示例：
{
  "log bytes of payload data": 123456789,
  "log flush operations": 1234,
  "log force write operations": 567,
  "log force write operations skipped": 0,
  "log records compressed": 80,      // 压缩比例
  "log scan operations": 0,
  "log scan records requiring two reads": 0,
  "log sync operations": 12345,
  "log sync time duration (usecs)": 4567890
}
```

### 十、总结：WiredTiger如何确保数据不丢失

#### **多层防御机制**
1. **第一层：预写日志**
   - 所有修改先记录日志
   - 日志定期或按配置刷新到磁盘

2. **第二层：检查点**
   - 定期将脏页批量写入数据文件
   - 减少恢复时需要重放的日志量

3. **第三层：副本集（生产环境）**
   - 多节点冗余
   - 自动故障转移

4. **第四层：备份策略**
   - 定期物理备份
   - 逻辑备份
   - 时间点恢复

#### **关键要点**
- **WiredTiger不是靠内存页直接写入磁盘来保证持久性**，而是依赖**日志**
- **日志是同步的，数据文件写入是异步的**
- **可以配置不同的持久性级别**来平衡性能和安全
- **对于关键业务，总是使用 `j:true` 或副本集**
- **定期测试恢复流程**，确保备份有效

## 12.6 持久化机制对比 -- WAL vs AOF


| 特性 | **传统数据库 (MySQL/MongoDB)** | **Redis** |
|------|------------------------------|-----------|
| **数据模型** | 磁盘为主，内存为辅 | 内存为主，磁盘为辅 |
| **设计目标** | 数据安全第一，ACID事务 | 极致性能，低延迟 |
| **持久化时机** | **WAL：先写日志，再修改内存** | **AOF：先修改内存，后写日志** |
| **性能优先级** | 数据安全 > 性能 | 性能 > 数据安全 |
| **使用场景** | 持久存储、事务处理 | 缓存、会话、实时计算 |


# 十三、Mongo进阶 - WT引擎：事务实现

> 在本文中，我们主要针对WT引擎的事务来展开分析，来看看它的事务是如何实现的。

## 13.1 理解本文需要有哪些基础
> 以下是基础，可以参考本网张其它文章。
- 什么是事务？
- 什么是ACID？
- 什么是MVCC？
- 什么是事务快照？
- 什么是redo log?

## 13.2 WT的事务构造
知道了基本的事务概念和ACID后，来看看WT引擎是怎么来实现事务和ACID的。要了解实现先要知道它的事务的构造和使用相关的技术，WT在实现事务的时使用主要是使用了三个技术：`snapshot(事务快照)`、`MVCC (多版本并发控制)`和r`edo log(重做日志)`，为了实现这三个技术，它还定义了一个基于这三个技术的事务对象和全局事务管理器。事务对象描述如下
```c
wt_transaction{

    transaction_id:    本次事务的**全局唯一的ID**，用于标示事务修改数据的版本号

    snapshot_object:   当前事务开始或者操作时刻其他正在执行且并未提交的事务集合,用于事务隔离

    operation_array:   本次事务中已执行的操作列表,用于事务回滚。

    redo_log_buf:      操作日志缓冲区。用于事务提交后的持久化

    state:             事务当前状态

}
```
### 13.2.1  WT的多版本并发控制
WT中的MVCC是基于key/value中value值的链表，这个链表单元中存储有当先版本操作的事务ID和操作修改后的值。描述如下：
```c
wt_mvcc{

    transaction_id:    本次修改事务的ID

    value:             本次修改后的值

}
```
WT中的数据修改都是在这个链表中进行append操作，每次对值做修改都是append到链表头上，每次读取值的时候读是从链表头根据值对应的修改事务transaction_id和本次读事务的snapshot来判断是否可读，如果不可读，向链表尾方向移动，直到找到读事务能都的数据版本。样例如下：

![61.mongo-y-trans-1.png](../../assets/images/06-中间件/mongodb/61.mongo-y-trans-1.png)

上图中，事务T0发生的时刻最早，T5发生的时刻最晚。T1/T2/T4是对记录做了修改。那么在mvcc list当中就会增加3个版本的数据，分别是11/12/14。如果事务都是基于snapshot级别的隔离，T0只能看到T0之前提交的值10，读事务T3访问记录时它能看到的值是11，T5读事务在访问记录时，由于T4未提交，它也只能看到11这个版本的值。这就是WT 的MVCC基本原理。

### 13.2.2 WT事务snapshot
上面多次提及事务的snapshot，那到底什么是事务的snapshot呢？其实就是事务开始或者进行操作之前对整个WT引擎内部正在执行或者将要执行的事务进行一次截屏，保存当时整个引擎所有事务的状态，确定哪些事务是对自己见的，哪些事务都自己是不可见。说白了就是一些列事务ID区间。WT引擎整个事务并发区间示意图如下：

![62.mongo-y-trans-2.png](../../assets/images/06-中间件/mongodb/62.mongo-y-trans-2.png)

WT引擎中的snapshot_oject是有一个最小执行事务snap_min、一个最大事务snap max和一个处于[snap_min, snap_max]区间之中所有正在执行的写事务序列组成。如果上图在T6时刻对系统中的事务做一次snapshot，那么产生的
```c
snapshot_object = {

     snap_min=T1,

     snap_max=T5,

     snap_array={T1, T4, T5},

};
```
那么T6能访问的事务修改有两个区间：所有小于T1事务的修改[0, T1)和[snap_min,snap_max]区间已经提交的事务T2的修改。换句话说，凡是出现在snap_array中或者事务ID大于snap_max的事务的修改对事务T6是不可见的。如果T1在建立snapshot之后提交了，T6也是不能访问到T1的修改。这个就是snapshot方式隔离的基本原理。

### 13.2.3 全局事务管理器
通过上面的snapshot的描述，我们可以知道要创建整个系统事务的快照截屏，就需要一个全局的事务管理来进行事务截屏时的参考，在WT引擎中是如何定义这个全局事务管理器的呢？在CPU多核多线程下，它是如何来管理事务并发的呢？下面先来分析它的定义：
```c
wt_txn_global{

     current_id:       全局写事务ID产生种子,一直递增

     oldest_id:        系统中最早产生且还在执行的写事务ID

     transaction_array: 系统事务对象数组，保存系统中所有的事务对象

     scan_count:     正在扫描transaction_array数组的线程事务数，用于建立snapshot过程的无锁并发

}
```
transaction_array保存的是图2正在执行事务的区间的事务对象序列。在建立snapshot时，会对整个transaction_array做扫描，确定snap_min/snap_max/snap_array这三个参数和更新oldest_id,在扫描的过程中，凡是transaction_id不等于WT_TNX_NONE都认为是在执行中且有修改操作的事务，直接加入到snap_array当中。整个过程是一个无锁操作过程,这个过程如下：

![63.mongo-y-trans-3.png](../../assets/images/06-中间件/mongodb/63.mongo-y-trans-3.png)

创建snapshot截屏的过程在WT引擎内部是非常频繁，尤其是在大量自动提交型的短事务执行的情况下，由创建snapshot动作引起的CPU竞争是非常大的开销，所以这里WT并没有使用spin lock ,而是采用了上图的一个无锁并发设计，这种设计遵循了我们开始说的并发设计原则。

### 13.2.4 事务ID
从WT引擎创建事务snapshot的过程中现在可以确定，snapshot的对象是有写操作的事务，纯读事务是不会被snapshot的，因为snapshot的目的是隔离mvcc list中的记录，通过MVCC中value的事务ID与读事务的snapshot进行版本读取，与读事务本身的ID是没有关系。在WT引擎中，开启事务时，引擎会将一个WT_TNX_NONE( = 0)的事务ID设置给开启的事务，**当它第一次对事务进行写时，会在数据修改前通过全局事务管理器中的current_id来分配一个全局唯一的事务ID**。这个过程也是通过CPU的CAS_ADD原子操作完成的无锁过程。

## 13.3 WT的事务过程
一般事务是两个阶段：**事务执行**和**事务提交**。在事务执行前，我们需要先创建事务对象并开启它，然后才开始执行，如果执行遇到冲突和或者执行失败，我们需要回滚事务(rollback)。如果执行都正常完成，最后只需要提交(commit)它即可。从上面的描述可以知道事务过程有：创建`开启`、`执行`、`提交`和`回滚`。那么从这几个过程中来分析WT是怎么实现这几个过程的。

### 13.3.1 事务开启
WT事务开启过程中，首先会为事务创建一个事务对象并把这个对象加入到全局事务管理器当中，然后通过事务配置信息确定事务的隔离级别和`redo log`的刷盘方式并将事务状态设为执行状态，最后判断如果隔离级别是ISOLATION_SNAPSHOT(snapshot级的隔离)，在本次事务执行前创建一个系统并发事务的snapshot截屏。至于为什么要在事务执行前创建一个snapshot，在后面WT事务隔离章节详细介绍。

### 13.3.2 事务执行
事务在执行阶段，如果是读操作，不做任何记录，因为读操作不需要回滚和提交。如果是写操作，WT会对每个写操作做详细的记录。在上面介绍的事务对象(wt_transaction)中有两个成员，一个是操作`operation_array`，一个是`redo_log_buf`。这两个成员是来记录修改操作的详细信息，在`operation_array`的数组单元中，包含了一个指向MVCC list对应修改版本值的指针。那么详细的更新操作流程如下：

- 创建一个mvcclist中的值单元对象(update)

- 根据事务对象的transactionid和事务状态判断是否为本次事务创建了写的事务ID，如果没有，为本次事务分配一个事务ID，并将事务状态设成`HAS_TXN_ID`状态。

- 将本次事务的ID设置到update单元中作为mvcc版本号。

- 创建一个operation对象，并将这个对象的值指针指向update,并将这个operation加入到本次事务对象的operation_array

- 将update单元加入到mvcc list的链表头上。

- 写入一条redo log到本次事务对象的redo_log_buf当中。

示意图如下：

![64.mongo-y-trans-4.png](../../assets/images/06-中间件/mongodb/64.mongo-y-trans-4.png)

### 13.3.3 事务提交
WT引擎对事务的提交过程比较简单，先将要提交的事务对象中的redo_log_buf中的数据写入到redo log file(重做日志文件)中，并将redo log file持久化到磁盘上。清除提交事务对象的snapshot object,再将提交的事务对象中的transaction_id设置为WT_TNX_NONE，保证其他事务在创建系统事务snapshot时本次事务的状态是已提交的状态。

### 13.3.4 事务回滚
WT引擎对事务的回滚过程也比较简单，先遍历整个operation_array，对每个数组单元对应update的事务id设置以为一个WT_TXN_ABORTED（= uint64_max），标示mvcc 对应的修改单元值被回滚，在其他读事务进行mvcc读操作的时候，跳过这个放弃的值即可。整个过程是一个无锁操作，高效、简洁。

## 13.4 WT的事务隔离
传统的数据库事务隔离分为:`Read-Uncommited(未提交读)`、`Read-Commited(提交读)`、`Repeatable-Read(可重复读)`和`Serializable(串行化)`，WT引擎并没有按照传统的事务隔离实现这四个等级，而是基于snapshot的特点实现了自己的`Read-Uncommited`、`Read-Commited`和一种叫做`snapshot-Isolation`(快照隔离)的事务隔离方式。在WT中不管是选用的是那种事务隔离方式，它都是基于系统中执行事务的快照截屏来实现的。那来看看WT是怎么实现上面三种方式的。

![65.mongo-y-trans-5.png](../../assets/images/06-中间件/mongodb/65.mongo-y-trans-5.png)

### 13.4.1 Read-uncommited
Read-Uncommited(未提交读)隔离方式的事务在读取数据时总是读取到系统中最新的修改，哪怕是这个修改事务还没有提交一样读取，这其实就是一种脏读。WT引擎在实现这个隔方式时，**就是将事务对象中的snap_object.snap_array置为空即可**，那么在读取MVCC list中的版本值时，总是读取到MVCC list链表头上的第一个版本数据。举例说明，在图5中，如果T0/T3/T5的事务隔离级别设置成Read-uncommited的话，那么T1/T3/T5在T5时刻之后读取系统的值时，读取到的都是14。一般数据库不会设置成这种隔离方式，它违反了事务的ACID特性。可能在一些注重性能且对脏读不敏感的场景会采用，例如网页cache。

### 13.4.2 Read-Commited
Read-Commited(提交读)隔离方式的事务在读取数据时总是读取到系统中最新提交的数据修改，这个修改事务一定是提交状态。这种隔离级别可能在一个长事务多次读取一个值的时候前后读到的值可能不一样，这就是经常提到的“幻象读”。在WT引擎实现read-commited隔离方式就是事务在执行每个操作前都对系统中的事务做一次截屏，然后在这个截屏上做读写。还是来看图5，T5事务在T4事务提交之前它进行读取前做事务
```c
snapshot={

    snap_min=T2,

    snap_max=T4,

    snap_array={T2,T4},

}; 
```
在读取MVCC list时，12和14修个对应的事务T2/T4都出现在snap_array中，只能再向前读取11，11是T1的修改，而且T1 没有出现在snap_array，说明T1已经提交，那么就返回11这个值给T5。

之后事务T2提交，T5在它提交之后再次读取这个值，会再做一次
```c
snapshot={

     snap_min=T4,

     snap_max=T4,

     snap_array={T4},

}
```
这时在读取MVCC list中的版本时，就会读取到最新的提交修改12。

### 13.4.3 Snapshot- Isolation
Snapshot-Isolation（快照隔离）隔离方式是读事务开始时看到的最后提交的值版本修改，这个值在整个读事务执行过程只会看到这个版本，不管这个值在这个读事务执行过程被其他事务修改了几次，这种隔离方式不会出现“幻象读”。WT在实现这个隔离方式很简单，在事务开始时对系统中正在执行的事务做一个snapshot,这个snapshot一直沿用到事务提交或者回滚。还是来看图5，T5事务在开始时，对系统中的执行的写事务做
```c
snapshot={

    snap_min=T2,

    snap_max=T4,

    snap_array={T2,T4}

}
```
那么在他读取值时读取到的是11。即使是T2完成了提交，但T5的snapshot执行过程不会更新，T5读取到的依然是11。

这种隔离方式的写比较特殊，就是如果有对事务看不见的数据修改，那么本事务尝试修改这个数据时会失败回滚，这样做的目的是防止忽略不可见的数据修改。

通过上面对三种事务隔离方式的分析，WT并没有使用传统的事务独占锁和共享访问锁来保证事务隔离，而是通过对系统中写事务的snapshot截屏来实现。这样做的目的是在保证事务隔离的情况下又能提高系统事务并发的能力。

## 13.5 WT的事务日志
通过上面的分析可以知道WT在事务的修改都是在内存中完成的，事务提交时也不会将修改的MVCC list当中的数据刷入磁盘，那么WT是怎么保证事务提交的结果永久保存呢？WT引擎在保证事务的持久可靠问题上是通过redo log（重做操作日志）的方式来实现的，在本文的事务执行和事务提交阶段都有提到写操作日志。WT的操作日志是一种基于K/V操作的逻辑日志，它的日志不是基于btree page的物理日志。说的通俗点就是将修改数据的动作记录下来，例如：插入一个key= 10,value= 20的动作记录在成:
```c
{

    Operation = insert,(动作)

    Key = 10,

    Value = 20

};
```
将动作记录的数据以append追加的方式写入到wt_transaction对象中redo_log_buf中，等到事务提交时将这个redo_log_buf中的数据已同步写入的方式写入到WT的重做日志的磁盘文件中。如果数据库程序发生异常或者崩溃，可以通过上一个checkpoint(检查点)位置重演磁盘上这个磁盘文件来恢复已经提交的事务来保证事务的持久性。根据上面的描述，有几个问题需要搞清楚：

- 操作日志格式怎么设计?

-  在事务并发提交时，各个事务的日志是怎么写入磁盘的？

- 日志是怎么重演的？它和checkpoint的关系是怎样的？

在分析这三个问题前先来看WT是怎么管理重做日志文件的，在WT引擎中定义一个叫做LSN序号结构，操作日志对象是通过LSN来确定存储的位置的，LSN就是LogSequence Number(日志序列号)，它在WT的定义是文件序号加文件偏移，
```c
wt_lsn{

    file:      文件序号，指定是在哪个日志文件中

    offset:    文件内偏移位置，指定日志对象文件内的存储文开始位置

}。
```
WT就是通过这个LSN来管理重做日志文件的。

### 13.5.1 日志格式
WT引擎的操作日志对象（以下简称为logrec）对应的是提交的事务，事务的每个操作被记录成一个logop对象，一个logrec包含多个logop，logrec是一个通过精密序列化事务操作动作和参数得到的一个二进制buffer，这个buffer的数据是通过事务和操作类型来确定其格式的。

WT中的日志分为4类：分别是建立checkpoint的操作日志(LOGREC_CHECKPOINT)、普通事务操作日志(LOGREC_COMMIT)、btree page同步刷盘的操作日志(LOGREC_FILE_SYNC)和提供给引擎外部使用的日志(LOGREC_MESSAGE)。这里介绍和执行事务密切先关的LOGREC_COMMIT，这类日志里面由根据K/V的操作方式分为：LOG_PUT(增加或者修改K/V操作)、LOG_REMOVE(单KEY删除操作)和范围删除日志,这几种操作都会记录操作时的key，根据操作方式填写不同的其他参数，例如：update更新操作，就需要将value填上。除此之外，日志对象还会携带btree的索引文件ID、提交事务的ID等，整个logrec和logop的关系结构图如下：

![66.mongo-y-trans-6.png](../../assets/images/06-中间件/mongodb/66.mongo-y-trans-6.png)

对于上图中的logrec header中的为什么会出现两个长度字段：logrec磁盘上的空间长度和在内存中的长度，因为logrec在刷入磁盘之前会进行空间压缩，那么磁盘上的长度和内存中的长度就不一样了。压缩是根据系统配置可选的。

### 13.5.2 WAL与日志写并发
WT引擎在采用WAL（Write-Ahead Log）方式写入日志，WAL通俗点说就是说在事务所有修改提交前需要将其对应的操作日志写入磁盘文件。在事务执行的介绍小节中我们介绍是在什么时候写日志的，这里我们来分析事务日志是怎么写入到磁盘上的，整个写入过程大致分为下面几个阶段：

- 事务在执行第一个写操作时，先会在事务对象（wt_transaction）中的redo_log_buf的缓冲区上创建一个logrec对象，并将logrec中的事务类型设置成LOGREC_COMMIT。

- 然后在事务执行的每个写操作前生成一个logop对象，并加入到事务对应的logrec中。

- 在事务提交时，把logrec对应的内容整体写入到一个全局log对象的slot buffer中并等待写完成信号。

- Slot buffer会根据并发情况合并同时发生的提交事务的logrec，然后将合并的日志内容同步刷入磁盘（sync file），最后告诉这个slot buffer对应所有的事务提交刷盘完成。

- 提交事务的日志完成，事务的执行结果也完成了持久化。

整个过程的示意图如下：

![67.mongo-y-trans-7.png](../../assets/images/06-中间件/mongodb/67.mongo-y-trans-7.png)

WT为了减少日志刷盘造成写IO，对日志刷盘操作做了大量的优化，实现一种类似MySQL组提交的刷盘方式。这种刷盘方式会将同时发生提交的事务日志合并到一个slotbuffer中，先完成合并的事务线程会同步等待一个完成刷盘信号，最后完成日志数据合并的事务线程将slotbuffer中的所有日志数据sync到磁盘上并通知在这个slotbuffer中等待其他事务线程刷盘完成。并发事务的logrec合并到slot buffer中的过程是一个完全无锁的过程，这减少了必要的CPU竞争和操作系统上下文切换。

为了这个无锁设计WT在全局的log管理中定义了一个acitve_ready_slot和一个slot_pool数组结构，大致如下定义：
```c
     wt_log{

     . . .

     active_slot:       准备就绪且可以作为合并logrec的slotbuffer对象

     slot_pool:         系统所有slot buffer对象数组，包括：正在合并的、准备合并和闲置的slot buffer。

}
```
slot buffer对象是一个动态二进制数组，可以根据需要进行扩大。定义如下:
```c
wt_log_slot{

. . .

state:             当前slot的状态,ready/done/written/free这几个状态

buf:          缓存合并logrec的临时缓冲区

group_size:        需要提交的数据长度

slot_start_offset: 合并的logrec存入log file中的偏移位置

     . . .

}
```
通过一个例子来说明这个无锁过程，假如在系统中slot_pool中的slot个数为16，设置的slotbuffer大小为4KB,当前log管理器中的active_slot的slot_start_offset=0,有4个事务（T1、T2、T3、T4）同时发生提交，他们对应的日志对象分别是logrec1、logrec2、logrec3和logrec4。

Logrec1 size = 1KB, logrec2 szie =2KB, logrec3 size =2KB, logrec4 size =5KB。他们合并和写入的过程如下：

- T1事务在提交时，先会从全局的log对象中的active_slot发起一次JION操作，JION过程就是向active_slot申请自己的合并位置和空间，logrec1_size + slot_start_offset < slot_size并且slot处于ready状态，那T1事务的合并位置就是active_slot[0, 1KB],slot_group_size = 1KB

- 这时T2同时发生提交也要合并logrec,也重复第1部JION操作，那么它申请到的位置就是active_slot[1KB, 3KB], slot_group_size = 3KB。

- 在T1事务JION完成后，它会判断自己是第一个JION这个active_slot的事务，判断条件就是返回的写入位置slot_offset=0。如果是第一个它立即会将active_slot的状态从ready状态置为done状态，并未后续的事务从slot_pool中获取一个空闲的active_slot_new来顶替自己合并数据的工作。

- 与此同时T2事务JION完成之后，它也是进行这个过程的判断，T2发现自己不是第一个，那么它将会等待T1将active_slot置为done.

- T1和T2都获取到了自己在active_slot中的写入位置，active_slot的状态置为done时，T1和T2分别将自己的logrec写入到对应buffer位置。加入在这里T1比T2先将数据写入完成。那么T1就会等待一个slot_buffer完全刷入磁盘的信号，而T2写入完成后会将slot_buffer中的数据写入log文件，并对log文件做sync刷入磁盘的操作，最高发送信号告诉T1同步刷盘完成，T1和T2各自返回，事务提交过程的日志刷盘操作完成。

那这里有几种其他的情况，假如在第2步运行的完成后，T3也进行JION操作，这个时候

- slot_size(4KB) < slot_group_size（3KB）+ logrec_size(2KB).那么T3不JION当时的active_slot，而是自旋等待active_slot_new顶替active_slot后再JION到active_slot_new。

- 如果在第2步时，T4也提交，因为logrec4(5KB)> slot_size(4KB),那么T4就不会进行JION操作，而是直接将自己的logrec数据写入log文件，并做sync刷盘返回。在返回前因为发现有logrec4大小的日志数据无法合并，全局log对象会试图将slot buffer的大小放大两倍，这样做的目的是尽量让下面的事务提交日志能进行slot合并写。

> WT引擎之所以引入slot日志合并写的原因就是为了减少磁盘的I/O访问，通过无锁的操作，减少全局日志缓冲区的竞争。

## 13.6 WT的事务恢复
从上面关于事务日志和MVCC list相关描述我们知道，事务的redo log主要是防止内存中已经提交的事务修改丢失，但如果所有的修改都存在内存中，随着时间和写入的数据越来越多，内存就会不够用，这个时候就需要将内存中的修改数据写入到磁盘上，一般在WT中是将整个BTREE上的page做一次checkpoint并写入磁盘。WT中的checkpoint是一个append方式管理的，也就是说WT会保存多个checkpoint版本。不管从哪个版本的checkpoint开始度可以通过重演redo log来恢复内存中已提交的事务修改。整个重演过程就是就是简单的对logrec中各个操作的执行。这里值得提一下的是因为WT保存多个版本的checkpoint,那么它会将checkpoint做为一种元数据写入到元数据表中，元数据表也会有自己的checkpoint和redo log，但是保存元数据表的checkpoint是保存在WiredTiger.wt文件中，系统重演普通表的提交事务之前，先会重演元数据事务提交修改。后面单独用一个篇幅来说明btree、checkpoint和元数据表的关系和实现。

WT的redo log是通过配置开启或者关闭的，MongoDB并没有使用WT的redolog来保证事务修改不丢，而是采用了WT的checkpoint和MongoDB复制集的功能结合来保证数据的完成性的。大致的细节是如果某个mongoDB实例宕机了，重启后通过MongoDB的复制协议将自己最新checkpoint后面的修改从其他的MongoDB实例复制过来。

## 13.7 后记
虽然WT实现了多操作事务模型，然而MongoDB并没有提供事务，这或许和MongoDB本身的架构和产品定位有关系。但是MongoDB利用了WT的短事务的隔离性实现了文档级行锁，对MongoDB来说这是大大的进步。

可以说WT在事务的实现上另辟蹊径，整个事务系统的实现没有用繁杂的事务锁，而是使用snapshot和MVCC这两个技术轻松的而实现了事务的ACID，这种实现也大大提高了事务执行的并发性。除此之外，WT在各个事务模块的实现多采用无锁并发，充分利用CPU的多核能力来减少资源竞争和I/O操作，可以说WT在实现上是有很大创新的。通过对WiredTiger的源码分析和测试，也让我获益良多，不仅仅了解了数据库存储引擎的最新技术，也对CPU和内存相关的并发编程有了新的理解，很多的设计模式和并发程序架构可以直接借鉴到现实中的项目和产品中。

# 十四、Mongo进阶 - WT引擎：缓存机制
> WT 在设计 LRU Cache 时采用分段扫描标记和 hazard pointer 的淘汰机制，在 WT 内部称这种机制叫 eviction Cache 或者 WT Cache，其设计目标是充分利用现代计算机超大内存容量来提高事务读写并发。

## 14.1 为什么会需要理解eviction cache
从mongoDB 3.0版本引入WiredTiger存储引擎(以下称为WT)以来，一直有同学反应在高速写入数据时WT引擎会间歇性写挂起，有时候写延迟达到了几十秒，这确实是个严重的问题。引起这类问题的关键在于WT的LRU cache的设计模型，WT在设计LRU cache时采用分段扫描标记和hazardpointer的淘汰机制，在WT内部称这种机制叫eviction cache或者WT cache，其设计目标是充分利用现代计算机超大内存容量来提高事务读写并发。在高速不间断写时内存操作是非常快的，但是内存中的数据最终必须写入到磁盘上，将页数据（page）由内存中写入磁盘上是需要写入时间，必定会和应用程序的高速不间断写产生竞争，这在任何数据库存储引擎都是无法避免的，只是由于WT利用大内存和写无锁的特性，让这种不平衡现象更加显著。下图是一位网名叫chszs同学对mongoDB 3.0和3.2版本测试高速写遇到的hang现象.

![68.mongo-y-cache-1.png](../../assets/images/06-中间件/mongodb/68.mongo-y-cache-1.png)

从上图可以看出，数据周期性出现了hang现象，笔者在单独对WT进行高并发顺序写时遇到的情况和上图基本一致，有时候挂起长达20秒。针对此问题我结合WT源码和调试测试进行了分析，基本得出的结论如下：

- WT引擎的eviction cache实现时没有考虑lru cache的分级淘汰，只是通过扫描btree来标记，这使得它和一些独占式btree操作(例如：checkpoint)容易发生竞争。

- WTbtree的checkpoint机制设计存在bug，在大量并发写事务发生时，checkpoint需要很长时间才能完成，造成刷入磁盘的数据很大，写盘时间很长。容易引起cache 满而挂起所有的读写操作。

- WT引擎的redo log文件超过1GB大小后就会另外新建一个新的redo log文件来继续存储新的日志，在操作系统层面上新建一个文件的是需要多次I/O操作，一旦和checkpoint数据刷盘操作同时发生，所有的写也就挂起了。

要彻底弄清楚这几个问题，就需要对从WT引擎的eviction cache原理来剖析，通过分析原理找到解决此类问题的办法。先来看eviction cache是怎么实现的，为什么要这么实现。

## 14.2 eviction cache原理
eviction cache是一个LRU cache，即页面置换算法缓冲区，LRU cache最早出现的地方是操作系统中关于虚拟内存和物理内存数据页的置换实现，后被数据库存储引擎引入解决内存和磁盘不对等的问题。所以LRU cache主要是解决内存与数据大小不对称的问题，让最近将要使用的数据缓存在cache中，把最迟使用的数据淘汰出内存，这是LRU置换算法的基本原则。但程序代码是无法预测未来的行为，只能根据过去数据页的情况来确定，一般我们认为过去经常使用的数据比不常用的数据未来被访问的概率更高，很多LRU cache大部分是基于这个规则来设计。

WT的eviction cache也不例外的遵循了这个LRU原理，不过WT的eviction cache对数据页采用的是分段局部扫描和淘汰，而不是对内存中所有的数据页做全局管理。基本思路是一个线程阶段性的去扫描各个btree，并把btree可以进行淘汰的数据页添加到一个lru queue中，当queue填满了后记录下这个过程当前的btree对象和btree的位置（这个位置是为了作为下次阶段性扫描位置）,然后对queue中的数据页按照访问热度排序，最后各个淘汰线程按照淘汰优先级淘汰queue中的数据页，整个过程是周期性重复。WT的这个evict过程涉及到多个eviction thread和hazard pointer技术。

**WT的evict过程都是以page为单位做淘汰，而不是以K/V。这一点和memcache、redis等常用的缓存LRU不太一样，因为在磁盘上数据的最小描述单位是page block，而不是记录。**

### 14.2.1 eviction线程模型
从上面的介绍可以知道WT引擎的对page的evict过程是个多线程协同操作过程，WT在设计的时候采用一种叫做leader-follower的线程模型，模型示意图如下:

![69.mongo-y-cache-2.png.jpeg](../../assets/images/06-中间件/mongodb/69.mongo-y-cache-2.png.jpeg)

Leader thread负责周期性扫描所有内存中的btree索引树，将符合evict条件的page索引信息填充到eviction queue，当填充queue满时，暂停扫描并记录下最后扫描的btree对象和btree上的位置，然后对queue中的page按照事务的操作次数和访问次做一次淘汰评分，再按照评分从小到大做排序。也就是说最评分越小的page越容易淘汰。下个扫描阶段的起始位置就是上个扫描阶段的结束位置，这样能保证在若干个阶段后所有内存中的page都被扫描过一次，这是为了公平性。这里必须要说明的是一次扫描很可能只是扫描内存一部分btree对象，而不是全部，所以我对这个过程称为阶段性扫描（evict pass），它不是对整个内存中的page做评分排序。**这个阶段性扫描的间隔时间是100毫秒**，而触发这个evict pass的条件就是WT cache管理的内存超出了设置的阈值，这个在后面的**eviction cache管理**的内存小节中详细介绍。

在evict pass后，如果evction queue中有等待淘汰的page存在就会触发一个操作系统信号来激活follower thread来进行evict page工作。虽然evict pass的间隔时间通常是100毫秒，这里有个问题就是当WT cache的内存触及上限并且有大量写事务发生时，读写事务线程在事务开始时会唤醒leader thread和follower thread,这就会产生大量的操作系统上下文切换，系统性能急剧下降。好在WT-2.8版本修复了这个问题，leader follower通过抢锁来成为leader,通过多线程信号合并和周期性唤醒来follower,而且leader thread也承担evict page的工作，可以避免大部分的线程唤醒和上下文切换。是不是有点像Nginx的网络模型？

### 14.2.2 hazard pointer
hazard pointer是一个无锁并发技术，其应用场景是单个线程写和多个线程读的场景，大致的原理是这样的，每个读的线程设计一个与之对应的无锁数组用于标记这个线程引用的hazard pointer对象。读线程的步骤如下：

- 读线程在访问某个hazard pointer对象时，先将在自己的标记数组中标记访问的对象。

- 读线程在访问完毕某个hazard pointer对象时，将其对应的标记从标记数组中删除。

写线程的步骤大致是这样的，写线程如果需要对某个hazard pointer对象写时，先判断所有读线程是否标记了这个对象，如果标记了，放弃写。如果未标记，进行写。

关于hazard pointer理论可以访问https://www.research.ibm.com/people/m/michael/ieeetpds-2004.pdf

Hazardpointer是怎样应用在WT中呢？我们这样来看待这个事情，把内存page的读写看做hazard pointer的读操作，把page从内存淘汰到磁盘上的过程看做hazard pointer的写操作，这样瞬间就能明白为什么WT在页的操作上可以不遵守The FIX Rules规则，而是采用无锁并发的页操作。要达到这种访问方式有个条件就是内存中page本身的结构要支持lock free访问。从上面的描述可以看出evict page的过程中首先要做一次hazard pointer写操作检查，而后才能进行page的reconcile和数据落盘。

hazard pointer并发技术的应用是整个WT存储引擎的关键，它关系到btree结构、internal page的构造、事务线程模型、事务并发等实现。Hazard pointer使得WT不依赖The Fix Rules规则，也让WT的btree结构更加灵活多变。

Hazard pointer是比较新的无锁编程模式，可以应用在很多地方，笔者曾在一个高并发媒体服务器上用到这个技术，以后有机会把里面的技术细节分享出来。

## 14.3 eviction cache管理的内存
eviction cache其实就是内存管理和page淘汰系统，目标就是为了使得管辖的内存不超过物理内存的上限，而触发淘汰evict page动作的基础依据就是内存上限。eviction cache管理的内存就是内存中page的内存空间，page的内存分为几部分：

- 从磁盘上读取到已经刷盘的数据，在page中称作disk buffer。如果WT没有开启压缩且使用的MMAP方式读写磁盘，这个disk buffer的数据大小是不计在WT eviction cache管理范围之内的。如果是开启压缩，会将从MMAP读取到的page数据解压到一个WT 分配的内存中，这个新分配的内存是计在WT eviction cache中的。

- Page在内存中新增的修改事务数据内存空间，计入在eviction cache中。

- Page基本的数据结构所有的内存空间，计入在eviction cache中。

WT在统计page的内存总量是通过一个footprint机制来统计两项数据，一项是总的内存使用量mem_size，一项是增删改造成的脏页数据总量dirty_mem_size。统计方式很简单，就是每次对页进行载入、增删改、分裂和销毁时对上面两项数据做原子增加或者减少计数，这样可以精确计算到当前系统中WT引擎内存占用量。假设引擎外部配置最大内存空间为cache_size,内存上限触发evict的比例为80%，内存脏页上限触发evict的比例为75%.那么系统触发evict pass操作的条件为：
```
mem_size> cache_size * 80%
```
或者
```
dirty_mem_size> cache_size * 75%
```
满足这个条件leader线程就会进行evict pass阶段性扫描并填充eivction queue,最后驱使follower线程进行evict page操作。

### 14.3.1 evict pass策略
前面介绍过evict pass是一个阶段性扫描的过程，整个过程分为扫描阶段、评分排序阶段和evict调度阶段。扫描阶段是通过扫描内存中btree，检查btree在内存中的page对象是否可以进行淘汰。扫描步骤如下：

- 根据上次evict pass最后扫描的btree和它对应扫描的位置最为本次evict pass的起始位置,如果当前扫描的btree被其他事务线程设成独占访问方式，跳过当前btree扫描下个btree对象。

- 进行btree遍历扫描，如果page满足淘汰条件，将page的索引对象添加到evict queue中，淘汰条件为：

  - 如果page是数据页，必须page当前最新的修改事务必须早以evict pass事务。
  - 如果page是btree内部索引页，必须page当前最新的修改事务必须早以evict pass事务且当前处于evict queue中的索引页对象不多于10个。
  - 当前btree不处于正建立checkpoint状态
- 如果本次evict pass当前的btree有超过100个page在evict queue中或者btree处于正在建立checkpoint时，结束这个btree的扫描，切换到下一个btree继续扫描。

- 如果evict queue填充满时或者本次扫描遍历了所有btree，结束本次evict pass。

PS:在开始evict pass时，evict queue可能存在有上次扫描且未淘汰出内存的page,那么这次evict pass一定会让queue填满（大概400个page）。

评分排序阶段是在evict pass后进行的，当queue中有page时，会根据每个page当前的访问次数、page类型和淘汰失败次数等计算一个淘汰评分，然后按照评分从小打到进行快排，排序完成后，会根据queue中最大分数和最小分数计算一个淘汰边界evict_throld，queue中所有大于evict_throld的page不列为淘汰对象。
**
WT为了让btree索引页尽量保存在内存中，在评分的时候索引页的分值会加上1000000分，让btree索引页免受淘汰。**

evict pass最后会做个判断，如果有follower线程存在，用系统信号唤醒follower进行evict page。如果系统中没有follower，leader线程进行eivct page操作。这个模型在WT-2.81版本已经修改成抢占模式。

## 14.3.2 evict page过程
evictpage其实就是将evict queue中的page数据先写入到磁盘文件中，然后将内存中的page对象销毁回收。整个evict page也分为三个阶段：`从evict queue中获取page对象`、`hazard pointer判断`和`page的reconcile过程`，整个过程的步骤如下：

- 从evict queue头开始获取page，如果发现page的索引对象不为空，对page进行LOCKED原子性标记防止其他读事务线程引用并将page的索引从queue中删除。

- 对淘汰的page进行hazard pointer，如果有其他线程对page标记hazard pointer, page不能被evict出内存，将page的评分加100.

- 如果没有其他线程对page标记hazard pointer,对page进行reconcile并销毁page内存中的对象。

evict page的过程大部分是由follower thread来执行，这个在上面的线程模型一节中已经描述过。但在一个读写事务开始之前，会先检查WT cache是否有足够的内存空间进行事务执行，如果WT cache的内存容量触及上限阈值，事务执行线程会尝试去执行evict page工作，如果evict page失败，会进行线程堵塞等待直到 WT cache有执行读写事务的内存空间（是不是读写挂起了？）。这种状况一般出现在正在建立checkpoint的时候，那么checkpoint是怎么引起这个现象的呢？下面来分析缘由。

## 14.4 eviction cache与checkpoint之间的事
众所周知，建立checkpoint的过程是将内存中所有的脏页（dirty page）同步刷入磁盘上并将redo log的重演位置设置到最后修改提交事务的redo log位置，相对于WT引擎来说，就是将eviction cache中的所有脏页数据刷入磁盘但并不将内存中的page淘汰出内存。这个过程其实和正常的evict过程是冲突的，而且checkpoint过程中需要更多的内存完成这项工作，这使得在一个高并发写的数据库中有可能出现挂起的状况发生。为了更好的理解整个问题的细节，我们先来看看WT checkpoint的原理和过程。

### 14.4.1 btree的checkpoint
WT引擎中的btree建立checkpoint过程还是比较复杂的，过程的步骤也比较多，而且很多步骤会涉及到索引、日志、事务和磁盘文件等。我以WT-2.7(mongoDB 3.2)版本为例子，checkpoint大致的步骤如下图：

![70.mongo-y-cache-3.png](../../assets/images/06-中间件/mongodb/70.mongo-y-cache-3.png)

在上图中，其中绿色的部分是在开始checkpoint事务之前会将所有的btree的脏页写入文件OS cache中，如果在高速写的情况下，写的速度接近也reconcile的速度，那么这个过程将会持续很长时间，也就是说OS cache中会存在大量未落盘的数据。而且在WT中btree采用的copy on write(写时复制)和extent技术，这意味OS cache中的文件数据大部分是在磁盘上是连续存储的，那么在绿色框最后一个步骤会进行同步刷盘，**这个时候如果OS cache的数据量很大就会造成这个操作长时间占用磁盘I/O**。这个过程是会把所有提交的事务修改都进行reconcile落盘操作。

在上图的紫色是真正开始checkpoint事务的步骤，**这里需要解释的是由于前面绿色步骤刷盘时间会比较长，在这个时间范围里会有新的写事务发生，也就意味着会新的脏页，checkpint必须把最新提交的事务修改落盘而且还要防止btree的分裂，这个时候就会获得btree的独占排他式访问，**这时 eviction cache不能对这个btree上的页进行evict操作（在这种情况下是不是容易造成WT cache满而挂起读写事务？）。

PS:WT-2.8版本之后对checkpoint改动非常大，主要是针对上面两点做了拆分，防止读写事务挂起发生，但大体过程是差不多的。

### 14.4.2 写挂起
通过前面的分析大概知道写挂起的原因了，主要引起挂起的现象主要是因为写内存的速度远远高于写磁盘的速度。先来看一份内存和磁盘读写的速度的数据吧。顺序读写的对比：

![71.mongo-y-cache-4.png](../../assets/images/06-中间件/mongodb/71.mongo-y-cache-4.png)

从上图可以看出，SATA磁盘的顺序读写1MB数据大概需要8ms, SSD相对快一点，大概只需2ms.但内存的读写远远大于磁盘的速度。SATA的随机读取算一次I/O时间，大概在8ms 到10ms，SSD的随机读写时间比较快，大概0.1ms。

我们来分析checkpoint时挂起读写事务的几种情况，假设系统在高速写某一张表（每秒以100MB/S的速度写入），每1分钟做一次checkpoint。那么1分钟后开始进行图3中绿色的步骤，这个步骤会在这一分钟之内写入的脏数据压缩先后写入到OS cache中，OS Cache可能存有近2GB的数据。这2GB的sync刷到磁盘上的时间至少需要10 ~ 20秒，而且磁盘I/O是被这个同步刷盘的任务占用了。这个时候有可能发生几件事情：

- 外部的写事务还在继续，事务提交时需要写redo log文件，这个时候磁盘I/O被占用了，写事务挂起等待。

- 外部的读写事务还在继续，redo log文件满了，需要新建一个新的redo log文件，但是新建文件需要多次随机I/O操作，磁盘I/O暂时无法调度来创建文件，所有写事务挂起。

- 外部读写事务线程还在继续，因为WT cache触发上限阈值需要evict page。Evict page时也会调用reconcile将page写入OS cache,但这个文件的OS cache正在进行sync，evict page只能等sync完成才能写入OS cache，evict page线程挂起，其他读写事务在开始时会判断是否有足够的内存进行事务执行，如果没有足够内存，所有读写事务挂起。

这三种情况是因为阶段性I/O被耗光而造成读写事务挂起的。

在图3紫色步骤中，checkpoint事务开始后会先获得btree的独占排他访问方式，这意味这个btree对象上的page不能进行evict,如果这个btree索引正在进行高速写入，有可能让checkpoint过程中数据页的reconcile时间很长，从而耗光WT cache内存造成读写事务挂起现象，这个现象极为在测试中极为少见（碰见过两次）。 要解决这几个问题只要解决内存和磁盘I/O不对等的问题就可以了。

## 14.5 内存和磁盘I/O的权衡
引起写挂起问题的原因多种多样，但归根结底是因为内存和磁盘速度不对称的问题。因为WT的设计原则就是让数据尽量利用现代计算机的超大内存，可是内存中的脏数据在checkpoint时需要同步写入磁盘造成瞬间I/O很高，这是矛盾的。要解决这些问题个人认为有以下几个途径：

- 将MongoDB的WT版本升级到2.8，2.8版本对evict queue模型做了分级，尽量避免evict page过程中堵塞问题,2.8的checkpoint机制不在是分为预前刷盘和checkpoint刷盘，而是采用逐个对btree直接做checkpoint刷盘，缓解了OS cache缓冲太多的文件脏数据问题。

- 试试direct I/O或许会有不同的效果，WT是支持direct I/O模式。笔者试过direct I/O模式，让WT cache彻底接管所有的物理内存管理，写事务的并发会比MMAP模式少10%，但没有出现过超过1秒的写延迟问题。

- 尝试将WT cache设小点，大概设置成整个内存的1/4左右。这种做法是可以缓解OS cache中瞬间缓存太多文件脏数据的问题，但会引起WT cache频繁evict page和频繁的leader-follower线程上下文切换。而且这种机制也依赖于OS page cache的刷盘周期，周期太长效果不明显。

- 用多个磁盘来存储，redo log文件放在一个单独的机械磁盘上，数据放在单独一个磁盘上，避免redo log与checkpoint刷盘发生竞争。

- 有条件的话，换成将磁盘换成SSD吧。这一点比较难，mongoDB现在也大量使用在OLAP和大数据存储，而高速写的场景都发生这些场景，成本是个问题。如果是OLTP建议用SSD。

这些方法只能缓解读写事务挂起的问题，不能说彻底解决这个问题，WT引擎发展很快，开发团队正对WT eviction cache和checkpoint正在做优化，这个问题慢慢变得不再是问题，尤其是WT-2.8版本，大量的模型和代码优化都是集中在这个问题上。

## 14.6 后记
WT的eviction cache可能有很多不完善的地方，也确实给我们在使用的过程造成了一些困挠，应该用中立的角度去看待它。可以说它的读写并发速度是其他数据库引擎不能比的，正是由于它很快，才会有写挂起的问题，因为磁盘的速度就那么快。以上的分析和建议或许对碰到类似问题的同学有用。

WT团队的研发速度也很快，每年会发布2 到3个版本，这类问题是他们正在重点解决的问题。在国内也有很多mongoDB这方面相关的专家，他们在解决此类问题有非常丰富的经验，也可以请求他们来帮忙解决这类问题。









































































































































































































































































































































































































































































































































































































































































































































































































































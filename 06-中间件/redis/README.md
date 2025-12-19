> 最后更新：2025-12-18 | [返回主目录](../README.md)
# 知识体系
![1.db-redis-overview.png](../../assets/images/06-中间件/redis/1.db-redis-overview.png)

# 一、Redis入门 - Redis概念和基础
> Redis是一种支持key-value等多种数据结构的存储系统。可用于缓存，事件发布或订阅，高速队列等场景。支持网络，提供字符串，哈希，列表，队列，集合结构直接存取，基于内存，可持久化。

## 1.1 什么是Redis

Redis是一款内存高速缓存数据库。Redis全称为：Remote Dictionary Server（远程数据服务），使用C语言编写，Redis是一个key-value存储系统（键值存储系统），支持丰富的数据类型，如：String、list、set、zset、hash。

Redis是一种支持key-value等多种数据结构的存储系统。可用于缓存，事件发布或订阅，高速队列等场景。支持网络，提供字符串，哈希，列表，队列，集合结构直接存取，基于内存，可持久化。

**官方资料**

Redis官网:http://redis.io/

Redis官方文档:http://redis.io/documentation

Redis教程:http://www.w3cschool.cn/redis/redis-intro.html

Redis下载:http://redis.io/download

## 1.2 为什么要使用Redis
> 一个产品的使用场景肯定是需要根据产品的特性，先列举一下Redis的特点：

- **读写性能优异**
  - Redis能读的速度是110000次/s,写的速度是81000次/s （测试条件见下一节）。
- **数据类型丰富**
  - Redis支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作。
- **原子性**
  - Redis的所有操作都是原子性的，同时Redis还支持对几个操作全并后的原子性执行。
- **丰富的特性**
  - Redis支持 publish/subscribe, 通知, key 过期等特性。
- **持久化**
  - Redis支持RDB, AOF等持久化方式
- **发布订阅**
  - Redis支持发布/订阅模式
- **分布式**
  - Redis Cluster

（PS: 具体再结合下面的使用场景理解下）

> 下面是官方的bench-mark根据如下条件获得的性能测试**（读的速度是110000次/s,写的速度是81000次/s）**

- 测试完成了50个并发执行100000个请求。
- 设置和获取的值是一个256字节字符串。
- Linux box是运行Linux 2.6,这是X3320 Xeon 2.5 ghz。
- 文本执行使用loopback接口(127.0.0.1)。
## 1.3 Redis的使用场景
> redis应用场景总结

redis平时我们用到的地方蛮多的，下面就了解的应用场景做个总结：

### 1.3.1 热点数据的缓存
缓存是Redis最常见的应用场景，之所有这么使用，主要是因为Redis读写性能优异。而且逐渐有取代memcached，成为首选服务端缓存的组件。而且，Redis内部是支持事务的，在使用时候能有效保证数据的一致性。

作为缓存使用时，一般有两种方式保存数据：

- 读取前，先去读Redis，如果没有数据，读取数据库，将数据拉入Redis。
- 插入数据时，同时写入Redis。

方案一：实施起来简单，但是有两个需要注意的地方：

- 避免缓存击穿。（数据库没有就需要命中的数据，导致Redis一直没有数据，而一直命中数据库。）
- 数据的实时性相对会差一点。

方案二：数据实时性强，但是开发时不便于统一处理。

当然，两种方式根据实际情况来适用。如：方案一适用于对于数据实时性要求不是特别高的场景。方案二适用于字典表、数据量不大的数据存储。

### 1.3.2 限时业务的运用
redis中可以使用expire命令设置一个键的生存时间，到时间后redis会删除它。利用这一特性可以运用在限时的优惠活动信息、手机验证码等业务场景。

### 1.3.3 计数器相关问题
redis由于incrby命令可以实现原子性的递增，所以可以运用于高并发的秒杀活动、分布式序列号的生成、具体业务还体现在比如限制一个手机号发多少条短信、一个接口一分钟限制多少请求、一个接口一天限制调用多少次等等。

### 1.3.4 分布式锁
这个主要利用redis的setnx命令进行，setnx："set if not exists"就是如果不存在则成功设置缓存同时返回1，否则返回0 ，这个特性在很多后台中都有所运用，因为我们服务器是集群的，定时任务可能在两台机器上都会运行，所以在定时任务中首先 通过setnx设置一个lock， 如果成功设置则执行，如果没有成功设置，则表明该定时任务已执行。 当然结合具体业务，我们可以给这个lock加一个过期时间，比如说30分钟执行一次的定时任务，那么这个过期时间设置为小于30分钟的一个时间就可以，这个与定时任务的周期以及定时任务执行消耗时间相关。

在分布式锁的场景中，主要用在比如秒杀系统等。

### 1.3.5 延时操作
比如在订单生产后我们占用了库存，10分钟后去检验用户是否真正购买，如果没有购买将该单据设置无效，同时还原库存。 由于redis自2.8.0之后版本提供Keyspace Notifications功能，允许客户订阅Pub/Sub频道，以便以某种方式接收影响Redis数据集的事件。 所以我们对于上面的需求就可以用以下解决方案，我们在订单生产时，设置一个key，同时设置10分钟后过期， 我们在后台实现一个监听器，监听key的实效，监听到key失效时将后续逻辑加上。

当然我们也可以利用rabbitmq、activemq等消息中间件的延迟队列服务实现该需求。


**Keyspace Notifications 允许客户端通过 Redis 的发布/订阅（Pub/Sub）机制，来接收 Redis 数据库中键（key）的某些事件（event）的发生**。

你可以把它理解为 Redis 数据库的“**事件监听系统**”或“**触发器**”。当指定的操作（如键的增、删、改、过期等）发生时，Redis 会主动向一个特定的频道（Channel）发布一条消息。你的应用程序只需要订阅这些频道，就能实时感知到这些变化。

在没有这个功能之前，如果你想监控一个键何时被删除或过期，只能通过**轮询（polling）** 的方式，即不断地查询这个键是否存在。这种方式效率低下，会带来不必要的网络开销和 Redis 服务器压力。

**Keyspace Notifications 提供了事件驱动的解决方案**，避免了轮询，只有在事件真正发生时才会通知客户端。这在很多场景下非常有用：

1.  **缓存失效同步**：当某个缓存键因过期或被删除而失效时，立即通知应用层，以便从数据库重新加载数据。
2.  **分布式锁的自动释放监控**：监控用于分布式锁的键是否因过期而释放，以便处理异常情况。
3.  **数据一致性**：当主数据库的数据发生变化时，通过删除 Redis 中的对应缓存键来保证缓存一致性。Keyspace Notifications 可以通知其他服务缓存已失效。
4.  **审计与日志**：记录所有对重要键的操作，用于安全审计。
5.  **触发业务逻辑**：例如，用户认证令牌（token）过期时，自动执行清理或通知用户重新登录。

Redis 将通知分为两大类，通过两种不同的频道发布：

*   **键空间通知（Keyspace notification）**：
    *   **频道格式**：`__keyspace@<db>__:<key>`
    *   **关注点**：**某个键上发生了什么事件**。
    *   **消息内容**：事件的名字（如 `del`, `expire`, `set`）。
    *   **示例**：监听键 `mykey` 在数据库 0 上的事件。如果对 `mykey` 执行了 `DEL` 命令，订阅者会：
        *   **收到频道**：`__keyspace@0__:mykey`
        *   **收到消息**：`del`

*   **键事件通知（Key-event notification）**：
    *   **频道格式**：`__keyevent@<db>__:<event>`
    *   **关注点**：**发生某个事件的是哪个键**。
    *   **消息内容**：触发事件的键的名字。
    *   **示例**：监听数据库 0 中所有的删除事件。如果键 `mykey` 被删除，订阅者会：
        *   **收到频道**：`__keyevent@0__:del`
        *   **收到消息**：`mykey`


事件由一个字符表示，常见的包括：

*   **`g`**：通用事件，如 `DEL`, `EXPIRE`, `RENAME` 等。
*   **`$`**：字符串（String）相关命令的事件，如 `SET`, `APPEND` 等。
*   **`l`**：列表（List）相关命令的事件。
*   **`s`**：集合（Set）相关命令的事件。
*   **`h`**：哈希（Hash）相关命令的事件。
*   **`z`**：有序集合（Sorted Set）相关命令的事件。
*   **`x`**：过期事件（`EXPIRE` 命令产生的）。
*   **`e`**：驱逐事件（因内存不足，被 `maxmemory-policy` 淘汰）。
*   **`A`**：`g$lshzxe` 的别名，代表所有事件。

**特别注意过期事件**：
*   当键被 `EXPIRE` 设置过期时间时，会产生一个 `expire` 事件。
*   当键**真正因过期而被删除**时，会产生一个 `expired` 事件。我们通常更关心 `expired` 事件。

**如何配置和使用**

- 1. 开启功能（重要！）

**该功能默认是关闭的**，因为它会消耗额外的 CPU 资源。你需要通过修改 Redis 配置文件 `redis.conf` 或使用 `CONFIG SET` 命令来开启。

**配置参数：`notify-keyspace-events`**

*   **在 `redis.conf` 中配置**：
    ```bash
    notify-keyspace-events "Ex" # 例如，只开启过期事件
    ```
*   **在线动态配置（重启后失效）**：
    ```bash
    127.0.0.1:6379> CONFIG SET notify-keyspace-events "AKE"
    ```
    *   `"AKE"` 表示开启所有类型的 Keyspace 和 Key-event 通知（最全的配置）。
    *   `"Ex"` 表示只开启键事件（Key-event）通知中的过期（expired）事件。这是最常见的配置之一。

- 2. 客户端订阅示例

假设我们开启了过期事件（`notify-keyspace-events "Ex"`），我们可以用 `redis-cli` 来订阅所有数据库的键过期事件：

1.  **打开一个终端，订阅频道**（监听所有数据库的 `expired` 事件）：
    ```bash
    # 终端 1: 订阅者
    127.0.0.1:6379> PSUBSCRIBE __keyevent@*__:expired
    Reading messages... (press Ctrl-C to quit)
    1) "psubscribe"
    2) "__keyevent@*__:expired"
    3) (integer) 1
    ```

2.  **打开另一个终端，设置一个会过期的键**：
    ```bash
    # 终端 2: 操作者
    127.0.0.1:6379> SET my_session_token "some_data" EX 5 # 设置键，5秒后过期
    OK
    ```

3.  **观察第一个终端**，5 秒后你会看到如下消息：
    ```bash
    # 终端 1: 接收到的消息
    1) "pmessage"                # 消息类型（模式订阅）
    2) "__keyevent@*__:expired"  # 订阅的模式
    3) "__keyevent@0__:expired"  # 实际触发的完整频道名（数据库0的expired事件）
    4) "my_session_token"        # 消息内容：过期的键名
    ```
    这样，你的应用就知道 `my_session_token` 这个键已经过期了。


### 1.3.6 排行榜相关问题
关系型数据库在排行榜方面查询速度普遍偏慢，所以可以借助redis的SortedSet进行热点数据的排序。

比如点赞排行榜，做一个SortedSet, 然后以用户的openid作为上面的username, 以用户的点赞数作为上面的score, 然后针对每个用户做一个hash, 通过zrangebyscore就可以按照点赞数获取排行榜，然后再根据username获取用户的hash信息，这个当时在实际运用中性能体验也蛮不错的。

### 1.3.7 点赞、好友等相互关系的存储
Redis 利用集合的一些命令，比如求交集、并集、差集等。

在微博应用中，每个用户关注的人存在一个集合中，就很容易实现求两个人的共同好友功能。

### 1.3.8 简单队列
由于Redis有list push和list pop这样的命令，所以能够很方便的执行队列操作。

## 补充：Spring Boot 集成 Redis Keyspace Notifications 功能

Spring Boot 集成 Redis Keyspace Notifications 功能非常方便，主要通过 `Spring Data Redis` 来实现。核心是利用 **`RedisMessageListenerContainer`** 来订阅特定频道并处理事件。

---

### 一、项目依赖 (pom.xml)

首先，确保 `pom.xml` 包含了 Spring Data Redis 的 starter 依赖。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

如果使用的是 Lettuce 连接池（推荐），可以额外添加：

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```

---

### 二、Redis 配置 (application.yml)

配置 Redis 连接信息和连接池（可选）。

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: # 如果有密码则填写
    database: 0
    lettuce:
      pool:
        max-active: 8
        max-wait: -1
        max-idle: 8
        min-idle: 0
```

---

### 三、核心集成代码

#### 第1步：开启 Redis 的 Keyspace Notifications 功能

这是最关键的一步！**该功能默认关闭**。有两种方式开启：

**方式A：通过 Redis CLI 动态配置（重启 Redis 后失效）**
```bash
# 连接到 Redis
redis-cli

# 开启所有键空间和键事件通知（最全，但开销大，用于测试）
127.0.0.1:6379> CONFIG SET notify-keyspace-events "AKE"

# 或者，更常用的：只开启键事件通知中的过期事件（最实用）
127.0.0.1:6379> CONFIG SET notify-keyspace-events "Ex"
```

**方式B：通过修改 Redis 配置文件（永久生效）**
找到 `redis.conf` 文件，添加或修改以下行：
```conf
# 开启过期事件通知
notify-keyspace-events "Ex"
```
然后重启 Redis 服务。

#### 第2步：创建 Redis 配置类

这个配置类负责创建消息监听容器，并订阅我们感兴趣的事件频道。

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

@Configuration
public class RedisListenerConfig {

    /**
     * 创建消息监听容器
     *
     * @param connectionFactory Redis 连接工厂
     * @return RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 定义监听的主题（Topic）
        // 这里我们订阅所有数据库（@*）的键过期事件（__keyevent@*__:expired）
        Topic topic = new PatternTopic("__keyevent@*__:expired");

        // 添加消息监听器，并指定接收消息的方法名 "handleMessage"
        container.addMessageListener(new RedisKeyExpirationListener(), topic);
        return container;
    }
}
```

#### 第3步：创建具体的消息监听器

这个类负责处理当订阅的频道有消息发布时，具体的业务逻辑。

```java
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Redis 键过期事件监听器
 * 实现 MessageListener 接口
 */
@Slf4j
@Component
public class RedisKeyExpirationListener implements MessageListener {

    /**
     * 处理接收到的过期事件消息
     *
     * @param message 消息内容，其中 message.getBody() 就是过期的那个 key
     * @param pattern 模式（频道模式）
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 获取过期的 key
        String expiredKey = new String(message.getBody());
        // 获取事件发生的频道
        String channel = new String(message.getChannel());

        log.info("监听到 Redis 键过期事件：");
        log.info("频道 [{}]", channel);
        log.info("过期的键 [{}]", expiredKey);

        // === 在这里编写你的核心业务逻辑 ===
        handleExpiredKey(expiredKey);
    }

    /**
     * 根据过期的 key 执行相应的业务逻辑
     *
     * @param expiredKey 过期的键
     */
    private void handleExpiredKey(String expiredKey) {
        try {
            // 示例1：处理用户会话过期
            if (expiredKey.startsWith("USER_SESSION:")) {
                String userId = expiredKey.replace("USER_SESSION:", "");
                log.info("用户 {} 的会话已过期，执行清理逻辑...", userId);
                // 例如：通知用户、清理相关资源等
                // userService.cleanupAfterSessionExpired(userId);
            }

            // 示例2：处理订单超时未支付
            if (expiredKey.startsWith("ORDER_TIMEOUT:")) {
                String orderId = expiredKey.replace("ORDER_TIMEOUT:", "");
                log.info("订单 {} 支付超时，自动取消订单...", orderId);
                // 例如：将订单状态更新为“已取消”
                // orderService.cancelOrderDueToTimeout(orderId);
            }

            // 示例3：处理分布式锁自动释放
            if (expiredKey.startsWith("LOCK:")) {
                log.info("分布式锁 {} 因过期而释放", expiredKey);
                // 可以记录日志或执行一些补偿逻辑
            }

        } catch (Exception e) {
            // 一定要捕获异常，避免监听器因异常而退出
            log.error("处理过期键 [{}] 的业务逻辑时发生错误：", expiredKey, e);
        }
    }
}
```

---

### 四、测试代码

可以写一个简单的 Controller 或单元测试来验证功能。

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置一个会过期的键，用于测试监听器
     * 访问： http://localhost:8080/set/key/5
     */
    @GetMapping("/set/{key}/{ttl}")
    public String setKeyWithTtl(@PathVariable String key, @PathVariable Long ttl) {
        // 设置一个键，并指定过期时间（单位：秒）
        stringRedisTemplate.opsForValue().set("ORDER_TIMEOUT:" + key, "some_value", ttl, java.util.concurrent.TimeUnit.SECONDS);
        return String.format("键 'ORDER_TIMEOUT:%s' 已设置，%d 秒后过期。请查看控制台日志。", key, ttl);
    }
}
```

**测试步骤：**
1.  启动 Spring Boot 应用。
2.  确保 Redis 的 `notify-keyspace-events` 已正确配置（通过 `CONFIG GET notify-keyspace-events` 命令检查）。
3.  在浏览器中访问 `http://localhost:8080/set/order123/10`。
4.  观察应用控制台，10 秒后将看到类似的日志输出：
    ```
    监听到 Redis 键过期事件：
    频道 [__keyevent@0__:expired]
    过期的键 [ORDER_TIMEOUT:order123]
    订单 order123 支付超时，自动取消订单...
    ```

---

### 五、重要注意事项（生产环境必读）

1.  **事件丢失问题**：Redis 的 Pub/Sub 是**不可靠**的。如果服务在事件发生期间重启或网络中断，会丢失事件。**不要用它来做核心金融业务或强一致性要求的场景**。
2.  **性能开销**：监听器是单线程顺序处理的。如果过期事件非常频繁，可能导致处理堆积。确保你的 `handleExpiredKey` 方法执行速度足够快，或者使用异步处理。
3.  **消息重复**：在某些网络分区或重连情况下，**有可能收到重复的消息**。的业务逻辑最好能做到幂等（即处理多次和处理一次的效果相同）。
4.  **异步处理改进**：为了不阻塞监听线程，可以将接收到的消息放入一个内部队列（如 `Disruptor` 或 `BlockingQueue`），然后由工作线程池异步处理。

**改进示例（异步处理）：**
```java
@Component
public class RedisKeyExpirationListener implements MessageListener {

    // 创建一个专用的线程池来处理过期事件
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = new String(message.getBody());
        // 提交到线程池异步执行，不阻塞 Redis 的订阅线程
        executorService.submit(() -> {
            handleExpiredKey(expiredKey);
        });
    }
    // ... handleExpiredKey 方法不变
}
```

# 二、Redis入门 - 数据类型：5种基础数据类型详解
![2.db-redis-object-2-2.png](../../assets/images/06-中间件/redis/2.db-redis-object-2-2.png)

> Redis所有的key（键）都是字符串。我们在谈基础数据结构时，讨论的是存储值的数据类型，主要包括常见的5种数据类型，分别是：String、List、Set、Zset、Hash

## 2.1 Redis数据结构简介
Redis基础文章非常多，关于基础数据结构类型，我推荐你先看下<a href ='https://redis.io/docs/latest/develop/data-types/'>官方网站</a>内容，然后再看下面的小结

首先对redis来说，所有的key（键）都是字符串。我们在谈基础数据结构时，讨论的是存储值的数据类型，主要包括常见的5种数据类型，分别是：String、List、Set、Zset、Hash。

![3.db-redis-ds-1.jpeg](../../assets/images/06-中间件/redis/3.db-redis-ds-1.jpeg)



| 结构类型       | 结构存储的值                                     | 结构的读写能力                                                                                                 |
|----------------|--------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| **String字符串**   | 可以是字符串、整数或浮点数                         | 对整个字符串或字符串的一部分进行操作；对整数或浮点数进行自增或自减操作                                         |
| **List列表**       | 一个链表，链表上的每个节点都包含一个字符串         | 对链表的两端进行push和pop操作，读取单个或多个元素；根据值查找或删除元素                                       |
| **Set集合**        | 包含字符串的无序集合                             | 字符串的集合，包含基础的方法有看是否存在添加、获取、删除；还包含计算交集、并集、差集等                         |
| **Hash散列**       | 包含键值对的无序散列表                           | 包含方法有添加、获取、删除单个元素                                                                           |
| **Zset有序集合**   | 和散列一样，用于存储键值对；字符串成员与浮点数分数之间的有序映射；元素的排列顺序由分数的大小决定 | 包含方法有添加、获取、删除单个元素以及根据分值范围或成员来获取元素                                             |


## 2.2 基础数据结构详解
> 内容其实比较简单，我觉得理解的重点在于这个结构怎么用，能够用来做什么？所以我在梳理时，围绕**图例，命令，执行和场景**来阐述。
### 2.2.1 String字符串
> String是redis中最基本的数据类型，一个key对应一个value。

String类型是二进制安全的，意思是 redis 的 string 可以包含任何数据。如数字，字符串，jpg图片或者序列化的对象。

- 图例

下图是一个String类型的实例，其中键为hello，值为world

![4.db-redis-ds-3.png](../../assets/images/06-中间件/redis/4.db-redis-ds-3.png)

- 命令使用

| 命令   | 简述                     | 使用               |
|--------|--------------------------|--------------------|
| GET    | 获取存储在给定键中的值     | GET name           |
| SET    | 设置存储在给定键中的值     | SET name value     |
| DEL    | 删除存储在给定键中的值     | DEL name           |
| INCR   | 将键存储的值加1           | INCR key           |
| DECR   | 将键存储的值减1           | DECR key           |
| INCRBY | 将键存储的值加上整数       | INCRBY key amount  |
| DECRBY | 将键存储的值减去整数       | DECRBY key amount  |

- 命令执行
```sh
127.0.0.1:6379> set hello world
OK
127.0.0.1:6379> get hello
"world"
127.0.0.1:6379> del hello
(integer) 1
127.0.0.1:6379> get hello
(nil)
127.0.0.1:6379> set counter 2
OK
127.0.0.1:6379> get counter
"2"
127.0.0.1:6379> incr counter
(integer) 3
127.0.0.1:6379> get counter
"3"
127.0.0.1:6379> incrby counter 100
(integer) 103
127.0.0.1:6379> get counter
"103"
127.0.0.1:6379> decr counter
(integer) 102
127.0.0.1:6379> get counter
"102"
```
- 实战场景
  - **缓存：** 经典使用场景，把常用信息，字符串，图片或者视频等信息放到redis中，redis作为缓存层，mysql做持久化层，降低mysql的读写压力。
  - **计数器：**redis是单线程模型，一个命令执行完才会执行下一个，同时数据可以一步落地到其他的数据源。
  - **session：**常见方案spring session + redis实现session共享，
### 2.2.2 List列表
> Redis中的List其实就是链表（Redis用双端链表实现List）。

使用List结构，我们可以轻松地实现最新消息排队功能（比如新浪微博的TimeLine）。List的另一个应用就是消息队列，可以利用List的 PUSH 操作，将任务存放在List中，然后工作线程再用 POP 操作将任务取出进行执行。

- 图例

![5.db-redis-ds-5.png](../../assets/images/06-中间件/redis/5.db-redis-ds-5.png)

- 命令使用

| 命令   | 简述                                                                       | 使用               |
|--------|----------------------------------------------------------------------------|--------------------|
| RPUSH  | 将给定值推入到列表右端                                                   | RPUSH key value    |
| LPUSH  | 将给定值推入到列表左端                                                   | LPUSH key value    |
| RPOP   | 从列表的右端弹出一个值，并返回被弹出的值                                   | RPOP key           |
| LPOP   | 从列表的左端弹出一个值，并返回被弹出的值                                   | LPOP key           |
| LRANGE | 获取列表在给定范围上的所有值                                             | LRANGE key 0 -1    |
| LINDEX | 通过索引获取列表中的元素。你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。 | LINDEX key index   |
| LTRIM | 修剪列表，只保留指定范围内的元素 | LTRIM key start stop |

- 使用列表的技巧
  - lpush+lpop=Stack(栈)
  - lpush+rpop=Queue（队列）
  - lpush+ltrim=Capped Collection（有限集合）
  - lpush+rpop=Message Queue（消息队列）

- 命令执行
```sh
127.0.0.1:6379> lpush mylist 1 2 ll ls mem
(integer) 5
127.0.0.1:6379> lrange mylist 0 -1
1) "mem"
2) "ls"
3) "ll"
4) "2"
5) "1"
127.0.0.1:6379> lindex mylist -1
"1"
127.0.0.1:6379> lindex mylist 10        # index不在 mylist 的区间范围内
(nil)
```
- 实战场景
  - **微博TimeLine:** 有人发布微博，用lpush加入时间轴，展示新的列表信息。
  - **消息队列**

### 2.2.3 Set集合
> Redis 的 Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。

Redis 中集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。

- 图例

![6.db-redis-ds-7.png](../../assets/images/06-中间件/redis/6.db-redis-ds-7.png)

- 命令使用

| 命令     | 简述                             | 使用                 |
|----------|----------------------------------|----------------------|
| SADD     | 向集合添加一个或多个成员         | SADD key value       |
| SCARD    | 获取集合的成员数                 | SCARD key            |
| SMEMBERS | 返回集合中的所有成员             | SMEMBERS key member  |
| SISMEMBER| 判断 member 元素是否是集合 key 的成员 | SISMEMBER key member |

其它一些集合操作，请参考这里 https://www.runoob.com/redis/redis-sets.html

- 命令执行
```sh
127.0.0.1:6379> sadd myset hao hao1 xiaohao hao
(integer) 3
127.0.0.1:6379> smembers myset
1) "xiaohao"
2) "hao1"
3) "hao"
127.0.0.1:6379> sismember myset hao
(integer) 1
```
- 实战场景
  - **标签（tag）**,给用户添加标签，或者用户给消息添加标签，这样有同一标签或者类似标签的可以给推荐关注的事或者关注的人。
  - **点赞，或点踩，收藏等**，可以放到set中实现
### 2.2.4 Hash散列
Redis hash 是一个 string 类型的 field（字段） 和 value（值） 的映射表，hash 特别适合用于存储对象。

- 图例

![7.db-redis-ds-4.png](../../assets/images/06-中间件/redis/7.db-redis-ds-4.png)

- 命令使用

| 命令   | 简述                                     | 使用                     |
|--------|------------------------------------------|--------------------------|
| HSET   | 添加键值对                               | HSET hash-key sub-key1 value1 |
| HGET   | 获取指定散列键的值                       | HGET hash-key key1        |
| HGETALL| 获取散列中包含的所有键值对               | HGETALL hash-key          |
| HDEL   | 如果给定键存在于散列中，那么就移除这个键 | HDEL hash-key sub-key1    |

- 命令执行
```sh
127.0.0.1:6379> hset user name1 hao
(integer) 1
127.0.0.1:6379> hset user email1 hao@163.com
(integer) 1
127.0.0.1:6379> hgetall user
1) "name1"
2) "hao"
3) "email1"
4) "hao@163.com"
127.0.0.1:6379> hget user user
(nil)
127.0.0.1:6379> hget user name1
"hao"
127.0.0.1:6379> hset user name2 xiaohao
(integer) 1
127.0.0.1:6379> hset user email2 xiaohao@163.com
(integer) 1
127.0.0.1:6379> hgetall user
1) "name1"
2) "hao"
3) "email1"
4) "hao@163.com"
5) "name2"
6) "xiaohao"
7) "email2"
8) "xiaohao@163.com"
```
- 实战场景
  - 缓存： 能直观，相比string更节省空间，的维护缓存信息，如用户信息，视频信息等。
### 2.2.5 Zset有序集合
> Redis 有序集合和集合一样也是 string 类型元素的集合,且不允许重复的成员。不同的是每个元素都会关联一个 double 类型的分数。redis 正是通过分数来为集合中的成员进行从小到大的排序。

有序集合的成员是唯一的, 但分数(score)却可以重复。有序集合是通过两种数据结构实现：

压缩列表(ziplist): ziplist是为了提高存储效率而设计的一种特殊编码的双向链表。它可以存储字符串或者整数，存储整数时是采用整数的二进制而不是字符串形式存储。它能在O(1)的时间复杂度下完成list两端的push和pop操作。但是因为每次操作都需要重新分配ziplist的内存，所以实际复杂度和ziplist的内存使用量相关
跳跃表(zSkiplist): 跳跃表的性能可以保证在查找，删除，添加等操作的时候在对数期望时间内完成，这个性能是可以和平衡树来相比较的，而且在实现方面比平衡树要优雅，这是采用跳跃表的主要原因。跳跃表的复杂度是O(log(n))。
- 图例

![8.db-redis-ds-8.png](../../assets/images/06-中间件/redis/8.db-redis-ds-8.png)

- 命令使用

| 命令   | 简述                                     | 使用                     |
|--------|------------------------------------------|--------------------------|
| ZADD   | 将一个带有给定分值的成员添加到有序集合里面 | ZADD zset-key 178 member1 |
| ZRANGE | 根据元素在有序集合中所处的位置，从有序集合中获取多个元素 | ZRANGE zset-key 0-1 withscores |
| ZREM   | 如果给定元素成员存在于有序集合中，那么就移除这个元素 | ZREM zset-key member1 |

更多命令请参考这里 https://www.runoob.com/redis/redis-sorted-sets.html

- 命令执行
```sh
127.0.0.1:6379> zadd myscoreset 100 hao 90 xiaohao
(integer) 2
127.0.0.1:6379> ZRANGE myscoreset 0 -1
1) "xiaohao"
2) "hao"
127.0.0.1:6379> ZSCORE myscoreset hao
"100"
```
- 实战场景
  - **排行榜:** 有序集合经典使用场景。例如小说视频等网站需要对用户上传的小说视频做排行榜，榜单可以按照用户关注数，更新时间，字数等打分，做排行。
# 三、Redis入门 - 数据类型：3种特殊类型详解
> Redis除了上文中5种基础数据类型，还有三种特殊的数据类型，分别是 HyperLogLogs（基数统计）， Bitmaps (位图) 和 geospatial （地理位置）。

## 3.1 HyperLogLogs（基数统计）
> Redis 2.8.9 版本更新了 Hyperloglog 数据结构！

- 什么是基数？

举个例子，A = {1, 2, 3, 4, 5}， B = {3, 5, 6, 7, 9}；那么基数（不重复的元素）= 1, 2, 3, 4, 5, 6, 7, 9； （允许容错，即可以接受一定误差）

- HyperLogLogs 基数统计用来解决什么问题？

这个结构可以非常省内存的去统计各种计数，比如注册 IP 数、每日访问 IP 数、页面实时UV、在线用户数，共同好友数等。

- 它的优势体现在哪？

一个大型的网站，每天 IP 比如有 100 万，粗算一个 IP 消耗 15 字节，那么 100 万个 IP 就是 15M。而 HyperLogLog 在 Redis 中每个键占用的内容都是 12K，理论存储近似接近 2^64 个值，不管存储的内容是什么，它一个基于基数估算的算法，只能比较准确的估算出基数，可以使用少量固定的内存去存储并识别集合中的唯一元素。而且这个估算的基数并不一定准确，是一个带有 0.81% 标准错误的近似值（对于可以接受一定容错的业务场景，比如IP数统计，UV等，是可以忽略不计的）。

- 相关命令使用
```sh
127.0.0.1:6379> pfadd key1 a b c d e f g h i	# 创建第一组元素
(integer) 1
127.0.0.1:6379> pfcount key1					# 统计元素的基数数量
(integer) 9
127.0.0.1:6379> pfadd key2 c j k l m e g a		# 创建第二组元素
(integer) 1
127.0.0.1:6379> pfcount key2
(integer) 8
127.0.0.1:6379> pfmerge key3 key1 key2			# 合并两组：key1 key2 -> key3 并集
OK
127.0.0.1:6379> pfcount key3
(integer) 13
```

- 说明
  - HyperLogLogs的每个key对应的值都不会重复的，重复写入只会保留一个
  - 两个HyperLogLogs的key合并也是去重，重复的只保留一个
  - HyperLogLogs存储无法查看具体的内容只能统计个数
## 3.2 Bitmap （位存储）
> Bitmap 即位图数据结构，都是操作二进制位来进行记录，只有0 和 1 两个状态。

- 用来解决什么问题？

比如：统计用户信息，活跃，不活跃！ 登录，未登录！ 打卡，不打卡！ **两个状态的，都可以使用 Bitmaps！**

如果存储一年的打卡状态需要多少内存呢？ 365 天 = 365 bit 1字节 = 8bit 46 个字节左右！

- 相关命令使用

使用bitmap 来记录 周一到周日的打卡！ 周一：1 周二：0 周三：0 周四：1 ......
```sh
127.0.0.1:6379> setbit sign 0 1
(integer) 0
127.0.0.1:6379> setbit sign 1 1
(integer) 0
127.0.0.1:6379> setbit sign 2 0
(integer) 0
127.0.0.1:6379> setbit sign 3 1
(integer) 0
127.0.0.1:6379> setbit sign 4 0
(integer) 0
127.0.0.1:6379> setbit sign 5 0
(integer) 0
127.0.0.1:6379> setbit sign 6 1
(integer) 0
```
查看某一天是否有打卡！
```sh
127.0.0.1:6379> getbit sign 3
(integer) 1
127.0.0.1:6379> getbit sign 5
(integer) 0
```
统计操作，统计 打卡的天数！
```sh
127.0.0.1:6379> bitcount sign # 统计这周的打卡记录，就可以看到是否有全勤！
(integer) 3
```
## 3.3 geospatial (地理位置)
> Redis 的 Geo 在 Redis 3.2 版本就推出了! 这个功能可以推算地理位置的信息: 两地之间的距离, 方圆几里的人

### 3.3.1 geoadd
> 添加地理位置
```sh
127.0.0.1:6379> geoadd china:city 118.76 32.04 nanjing 112.55 37.86 taiyuan 123.43 41.80 shenyang
(integer) 3
127.0.0.1:6379> geoadd china:city 144.05 22.52 shengzhen 120.16 30.24 hangzhou 108.96 34.26 xian
(integer) 3
```
**规则**

两级无法直接添加，我们一般会下载城市数据(这个网址可以查询 GEO： http://www.jsons.cn/lngcode)！

- 有效的经度从-180度到180度。
- 有效的纬度从-85.05112878度到85.05112878度。
```sh
# 当坐标位置超出上述指定范围时，该命令将会返回一个错误。
127.0.0.1:6379> geoadd china:city 39.90 116.40 beijin
(error) ERR invalid longitude,latitude pair 39.900000,116.400000
```
### 3.3.2 geopos
> 获取指定的成员的经度和纬度
```sh
127.0.0.1:6379> geopos china:city taiyuan manjing
1) 1) "112.54999905824661255"
   1) "37.86000073876942196"
2) 1) "118.75999957323074341"
   1) "32.03999960287850968"
```
获得当前定位, 一定是一个坐标值!

### 3.3.3 geodist
> 如果不存在, 返回空

单位如下

- m
- km
- mi 英里
- ft 英尺
```sh
127.0.0.1:6379> geodist china:city taiyuan shenyang m
"1026439.1070"
127.0.0.1:6379> geodist china:city taiyuan shenyang km
"1026.4391"
```
### 3.3.4 georadius
> 附近的人 ==> 获得所有附近的人的地址, 定位, 通过半径来查询

获得指定数量的人
```sh
127.0.0.1:6379> georadius china:city 110 30 1000 km			以 100,30 这个坐标为中心, 寻找半径为1000km的城市
1) "xian"
2) "hangzhou"
3) "manjing"
4) "taiyuan"
127.0.0.1:6379> georadius china:city 110 30 500 km
1) "xian"
127.0.0.1:6379> georadius china:city 110 30 500 km withdist
1) 1) "xian"
   1) "483.8340"
127.0.0.1:6379> georadius china:city 110 30 1000 km withcoord withdist count 2
1) 1) "xian"
   1) "483.8340"
   2) 1) "108.96000176668167114"
      1) "34.25999964418929977"
2) 1) "manjing"
   1) "864.9816"
   2) 1) "118.75999957323074341"
      1) "32.03999960287850968"
```
参数 key 经度 纬度 半径 单位 [显示结果的经度和纬度] [显示结果的距离] [显示的结果的数量]

### 3.3.5 georadiusbymember
> 显示与指定成员一定半径范围内的其他成员
```sh
127.0.0.1:6379> georadiusbymember china:city taiyuan 1000 km
1) "manjing"
2) "taiyuan"
3) "xian"
127.0.0.1:6379> georadiusbymember china:city taiyuan 1000 km withcoord withdist count 2
1) 1) "taiyuan"
   2) "0.0000"
   3) 1) "112.54999905824661255"
      2) "37.86000073876942196"
2) 1) "xian"
   2) "514.2264"
   3) 1) "108.96000176668167114"
      2) "34.25999964418929977"
```
参数与 georadius 一样

### 3.3.6 geohash(较少使用)
> 该命令返回11个字符的hash字符串
```sh
127.0.0.1:6379> geohash china:city taiyuan shenyang
1) "ww8p3hhqmp0"
2) "wxrvb9qyxk0"
```
将二维的经纬度转换为一维的字符串, 如果两个字符串越接近, 则距离越近

### 3.3.7 底层
> geo底层的实现原理实际上就是Zset, 我们可以通过Zset命令来操作geo
```sh
127.0.0.1:6379> type china:city
zset
```
查看全部元素 删除指定的元素
```sh
127.0.0.1:6379> zrange china:city 0 -1 withscores
 1) "xian"
 2) "4040115445396757"
 3) "hangzhou"
 4) "4054133997236782"
 5) "manjing"
 6) "4066006694128997"
 7) "taiyuan"
 8) "4068216047500484"
 9) "shenyang"
1)  "4072519231994779"
2)  "shengzhen"
3)  "4154606886655324"
127.0.0.1:6379> zrem china:city manjing
(integer) 1
127.0.0.1:6379> zrange china:city 0 -1
1) "xian"
2) "hangzhou"
3) "taiyuan"
4) "shenyang"
5) "shengzhen"
```
# 四、Redis入门 - 数据类型：Stream详解
> Redis5.0 中还增加了一个数据类型Stream，它借鉴了Kafka的设计，是一个新的强大的支持多播的可持久化的消息队列。

用过Redis做消息队列的都了解，基于Reids的消息队列实现有很多种，例如：

- **PUB/SUB，订阅/发布模式**
  - 但是发布订阅模式是无法持久化的，如果出现网络断开、Redis 宕机等，消息就会被丢弃；
- 基于**List LPUSH+BRPOP** 或者 基于**Sorted-Set**的实现
  - 支持了持久化，但是不支持多播，分组消费等

为什么上面的结构无法满足广泛的MQ场景？ 这里便引出一个核心的问题：如果我们期望设计一种数据结构来实现消息队列，最重要的就是要理解**设计一个消息队列需要考虑什么**？初步的我们很容易想到

- 消息的生产
- 消息的消费
  - 单播和多播（多对多）
  - 阻塞和非阻塞读取
- 消息有序性
- 消息的持久化

其它还要考虑啥嗯？借助美团技术团队的一篇文章，<a href='https://tech.meituan.com/2016/07/01/mq-design.html'>消息队列设计精要</a>中的图

![9.db-redis-stream-1.png](../../assets/images/06-中间件/redis/9.db-redis-stream-1.png)

我们不妨看看Redis考虑了哪些设计？

- 消息ID的序列化生成
- 消息遍历
- 消息的阻塞和非阻塞读取
- 消息的分组消费
- 未完成消息的处理
- 消息队列监控
- ...
> 这也是我们需要理解Stream的点，但是结合上面的图，我们也应该理解Redis Stream也是一种超轻量MQ并没有完全实现消息队列所有设计要点，这决定着它适用的场景。

## 4.1 Stream详解
> 经过梳理总结，我认为从以下几个大的方面去理解Stream是比较合适的，总结如下：

- Stream的结构设计
- 生产和消费
  - 基本的增删查改
  - 单一消费者的消费
  - 消费组的消费
- 监控状态
### 4.1.1 Stream的结构
每个 Stream 都有唯一的名称，它就是 Redis 的 key，在我们首次使用 xadd 指令追加消息时自动创建。

![10.db-redis-stream-2.png](../../assets/images/06-中间件/redis/10.db-redis-stream-2.png)

上图解析：

- `Consumer Group` ：消费组，使用 XGROUP CREATE 命令创建，一个消费组有多个消费者(Consumer), 这些消费者之间是竞争关系。
- `last_delivered_id` ：游标，每个消费组会有个游标 `last_delivered_id`，任意一个消费者读取了消息都会使游标 `last_delivered_id` 往前移动。
- `pending_ids` ：消费者(Consumer)的状态变量，作用是维护消费者的未确认的 id。 `pending_ids` 记录了当前已经被客户端读取的消息，但是还没有 ack (Acknowledge character：确认字符)。如果客户端没有ack，这个变量里面的消息ID会越来越多，一旦某个消息被ack，它就开始减少。这个pending_ids变量在Redis官方被称之为PEL，也就是Pending Entries List，这是一个很核心的数据结构，它用来确保客户端至少消费了消息一次，而不会在网络传输的中途丢失了没处理。

此外我们还需要理解两点：

- `消息ID`: 消息ID的形式是timestampInMillis-sequence，例如1527846880572-5，它表示当前的消息在毫米时间戳1527846880572时产生，并且是该毫秒内产生的第5条消息。消息ID可以由服务器自动生成，也可以由客户端自己指定，但是形式必须是整数-整数，而且必须是后面加入的消息的ID要大于前面的消息ID。
- `消息内容`: 消息内容就是键值对，形如hash结构的键值对，这没什么特别之处。

### 4.1.2 增删改查
消息队列相关命令：

- XADD - 添加消息到末尾
- XTRIM - 对流进行修剪，限制长度
- XDEL - 删除消息
- XLEN - 获取流包含的元素数量，即消息长度
- XRANGE - 获取消息列表，会自动过滤已经删除的消息
- XREVRANGE - 反向获取消息列表，ID 从大到小
- XREAD - 以阻塞或非阻塞方式获取消息列表
```sh
# *号表示服务器自动生成ID，后面顺序跟着一堆key/value
127.0.0.1:6379> xadd codehole * name laoqian age 30  #  名字叫laoqian，年龄30岁
1527849609889-0  # 生成的消息ID
127.0.0.1:6379> xadd codehole * name xiaoyu age 29
1527849629172-0
127.0.0.1:6379> xadd codehole * name xiaoqian age 1
1527849637634-0
127.0.0.1:6379> xlen codehole
(integer) 3
127.0.0.1:6379> xrange codehole - +  # -表示最小值, +表示最大值
127.0.0.1:6379> xrange codehole - +
1) 1) 1527849609889-0
   1) 1) "name"
      1) "laoqian"
      2) "age"
      3) "30"
2) 1) 1527849629172-0
   1) 1) "name"
      1) "xiaoyu"
      2) "age"
      3) "29"
3) 1) 1527849637634-0
   1) 1) "name"
      1) "xiaoqian"
      2) "age"
      3) "1"
127.0.0.1:6379> xrange codehole 1527849629172-0 +  # 指定最小消息ID的列表
1) 1) 1527849629172-0
   2) 1) "name"
      2) "xiaoyu"
      3) "age"
      4) "29"
2) 1) 1527849637634-0
   2) 1) "name"
      2) "xiaoqian"
      3) "age"
      4) "1"
127.0.0.1:6379> xrange codehole - 1527849629172-0  # 指定最大消息ID的列表
1) 1) 1527849609889-0
   2) 1) "name"
      2) "laoqian"
      3) "age"
      4) "30"
2) 1) 1527849629172-0
   2) 1) "name"
      2) "xiaoyu"
      3) "age"
      4) "29"
127.0.0.1:6379> xdel codehole 1527849609889-0
(integer) 1
127.0.0.1:6379> xlen codehole  # 长度不受影响
(integer) 3
127.0.0.1:6379> xrange codehole - +  # 被删除的消息没了
1) 1) 1527849629172-0
   2) 1) "name"
      2) "xiaoyu"
      3) "age"
      4) "29"
2) 1) 1527849637634-0
   2) 1) "name"
      2) "xiaoqian"
      3) "age"
      4) "1"
127.0.0.1:6379> del codehole  # 删除整个Stream
(integer) 1
```
### 4.1.3 独立消费
我们可以在不定义消费组的情况下进行Stream消息的独立消费，当Stream没有新消息时，甚至可以阻塞等待。Redis设计了一个单独的消费指令xread，可以将Stream当成普通的消息队列(list)来使用。使用xread时，我们可以完全忽略消费组(Consumer Group)的存在，就好比Stream就是一个普通的列表(list)。
```sh
# 从Stream头部读取两条消息
127.0.0.1:6379> xread count 2 streams codehole 0-0
1) 1) "codehole"
   2) 1) 1) 1527851486781-0
         2) 1) "name"
            2) "laoqian"
            3) "age"
            4) "30"
      2) 1) 1527851493405-0
         2) 1) "name"
            2) "yurui"
            3) "age"
            4) "29"
# 从Stream尾部读取一条消息，毫无疑问，这里不会返回任何消息
127.0.0.1:6379> xread count 1 streams codehole $
(nil)
# 从尾部阻塞等待新消息到来，下面的指令会堵住，直到新消息到来
127.0.0.1:6379> xread block 0 count 1 streams codehole $
# 我们从新打开一个窗口，在这个窗口往Stream里塞消息
127.0.0.1:6379> xadd codehole * name youming age 60
1527852774092-0
# 再切换到前面的窗口，我们可以看到阻塞解除了，返回了新的消息内容
# 而且还显示了一个等待时间，这里我们等待了93s
127.0.0.1:6379> xread block 0 count 1 streams codehole $
1) 1) "codehole"
   2) 1) 1) 1527852774092-0
         2) 1) "name"
            2) "youming"
            3) "age"
            4) "60"
(93.11s)
```
客户端如果想要使用xread进行顺序消费，一定要记住当前消费到哪里了，也就是返回的消息ID。下次继续调用xread时，将上次返回的最后一个消息ID作为参数传递进去，就可以继续消费后续的消息。

block 0表示永远阻塞，直到消息到来，block 1000表示阻塞1s，如果1s内没有任何消息到来，就返回nil
```sh
127.0.0.1:6379> xread block 1000 count 1 streams codehole $
(nil)
(1.07s)
```
### 4.1.4 消费组消费
- 消费组消费图

![11.db-redis-stream-3.png](../../assets/images/06-中间件/redis/11.db-redis-stream-3.png)

- 相关命令：

  - XGROUP CREATE - 创建消费者组
  - XREADGROUP GROUP - 读取消费者组中的消息
  - XACK - 将消息标记为"已处理"
  - XGROUP SETID - 为消费者组设置新的最后递送消息ID
  - XGROUP DELCONSUMER - 删除消费者
  - XGROUP DESTROY - 删除消费者组
  - XPENDING - 显示待处理消息的相关信息
  - XCLAIM - 转移消息的归属权
  - XINFO - 查看流和消费者组的相关信息；
  - XINFO GROUPS - 打印消费者组的信息；
  - XINFO STREAM - 打印流信息
- 创建消费组

Stream通过xgroup create指令创建消费组(Consumer Group)，需要传递起始消息ID参数用来初始化last_delivered_id变量。
```sh
127.0.0.1:6379> xgroup create codehole cg1 0-0  #  表示从头开始消费
OK
# $表示从尾部开始消费，只接受新消息，当前Stream消息会全部忽略
127.0.0.1:6379> xgroup create codehole cg2 $
OK
127.0.0.1:6379> xinfo stream codehole  # 获取Stream信息
 1) length
 2) (integer) 3  # 共3个消息
 3) radix-tree-keys
 4) (integer) 1
 5) radix-tree-nodes
 6) (integer) 2
 7) groups
 8) (integer) 2  # 两个消费组
 9) first-entry  # 第一个消息
10) 1) 1527851486781-0
    2) 1) "name"
       2) "laoqian"
       3) "age"
       4) "30"
11) last-entry  # 最后一个消息
12) 1) 1527851498956-0
    2) 1) "name"
       2) "xiaoqian"
       3) "age"
       4) "1"
127.0.0.1:6379> xinfo groups codehole  # 获取Stream的消费组信息
1) 1) name
   2) "cg1"
   3) consumers
   4) (integer) 0  # 该消费组还没有消费者
   5) pending
   6) (integer) 0  # 该消费组没有正在处理的消息
2) 1) name
   2) "cg2"
   3) consumers  # 该消费组还没有消费者
   4) (integer) 0
   5) pending
   6) (integer) 0  # 该消费组没有正在处理的消息
```
- 消费组消费
Stream提供了xreadgroup指令可以进行消费组的组内消费，需要提供消费组名称、消费者名称和起始消息ID。它同xread一样，也可以阻塞等待新消息。读到新消息后，对应的消息ID就会进入消费者的PEL(正在处理的消息)结构里，客户端处理完毕后使用xack指令通知服务器，本条消息已经处理完毕，该消息ID就会从PEL中移除。
```sh
# >号表示从当前消费组的last_delivered_id后面开始读
# 每当消费者读取一条消息，last_delivered_id变量就会前进
127.0.0.1:6379> xreadgroup GROUP cg1 c1 count 1 streams codehole >
1) 1) "codehole"
   2) 1) 1) 1527851486781-0
         2) 1) "name"
            2) "laoqian"
            3) "age"
            4) "30"
127.0.0.1:6379> xreadgroup GROUP cg1 c1 count 1 streams codehole >
1) 1) "codehole"
   2) 1) 1) 1527851493405-0
         2) 1) "name"
            2) "yurui"
            3) "age"
            4) "29"
127.0.0.1:6379> xreadgroup GROUP cg1 c1 count 2 streams codehole >
1) 1) "codehole"
   2) 1) 1) 1527851498956-0
         2) 1) "name"
            2) "xiaoqian"
            3) "age"
            4) "1"
      2) 1) 1527852774092-0
         2) 1) "name"
            2) "youming"
            3) "age"
            4) "60"
# 再继续读取，就没有新消息了
127.0.0.1:6379> xreadgroup GROUP cg1 c1 count 1 streams codehole >
(nil)
# 那就阻塞等待吧
127.0.0.1:6379> xreadgroup GROUP cg1 c1 block 0 count 1 streams codehole >
# 开启另一个窗口，往里塞消息
127.0.0.1:6379> xadd codehole * name lanying age 61
1527854062442-0
# 回到前一个窗口，发现阻塞解除，收到新消息了
127.0.0.1:6379> xreadgroup GROUP cg1 c1 block 0 count 1 streams codehole >
1) 1) "codehole"
   2) 1) 1) 1527854062442-0
         2) 1) "name"
            2) "lanying"
            3) "age"
            4) "61"
(36.54s)
127.0.0.1:6379> xinfo groups codehole  # 观察消费组信息
1) 1) name
   2) "cg1"
   3) consumers
   4) (integer) 1  # 一个消费者
   5) pending
   6) (integer) 5  # 共5条正在处理的信息还有没有ack
2) 1) name
   2) "cg2"
   3) consumers
   4) (integer) 0  # 消费组cg2没有任何变化，因为前面我们一直在操纵cg1
   5) pending
   6) (integer) 0
# 如果同一个消费组有多个消费者，我们可以通过xinfo consumers指令观察每个消费者的状态
127.0.0.1:6379> xinfo consumers codehole cg1  # 目前还有1个消费者
1) 1) name
   2) "c1"
   3) pending
   4) (integer) 5  # 共5条待处理消息
   5) idle
   6) (integer) 418715  # 空闲了多长时间ms没有读取消息了
# 接下来我们ack一条消息
127.0.0.1:6379> xack codehole cg1 1527851486781-0
(integer) 1
127.0.0.1:6379> xinfo consumers codehole cg1
1) 1) name
   2) "c1"
   3) pending
   4) (integer) 4  # 变成了5条
   5) idle
   6) (integer) 668504
# 下面ack所有消息
127.0.0.1:6379> xack codehole cg1 1527851493405-0 1527851498956-0 1527852774092-0 1527854062442-0
(integer) 4
127.0.0.1:6379> xinfo consumers codehole cg1
1) 1) name
   2) "c1"
   3) pending
   4) (integer) 0  # pel空了
   5) idle
   6) (integer) 745505
```
### 4.1.5 信息监控
Stream提供了XINFO来实现对服务器信息的监控，可以查询：

- 查看队列信息
```sh
127.0.0.1:6379> Xinfo stream mq
 1) "length"
 2) (integer) 7
 3) "radix-tree-keys"
 4) (integer) 1
 5) "radix-tree-nodes"
 6) (integer) 2
 7) "groups"
 8) (integer) 1
 9) "last-generated-id"
10) "1553585533795-9"
11) "first-entry"
12) 1) "1553585533795-3"
    2) 1) "msg"
       2) "4"
13) "last-entry"
14) 1) "1553585533795-9"
    2) 1) "msg"
       2) "10"
```
- 消费组信息
```sh
127.0.0.1:6379> Xinfo groups mq
1) 1) "name"
   1) "mqGroup"
   2) "consumers"
   3) (integer) 3
   4) "pending"
   5) (integer) 3
   6) "last-delivered-id"
   7) "1553585533795-4"
```
- 消费者组成员信息
```sh
127.0.0.1:6379> XINFO CONSUMERS mq mqGroup
1) 1) "name"
   1) "consumerA"
   2) "pending"
   3) (integer) 1
   4) "idle"
   5) (integer) 18949894
2) 1) "name"
   1) "consumerB"
   2) "pending"
   3) (integer) 1
   4) "idle"
   5) (integer) 3092719
3) 1) "name"
   1) "consumerC"
   2) "pending"
   3) (integer) 1
   4) "idle"
   5) (integer) 23683256
```
至此，消息队列的操作说明大体结束！

## 4.2 更深入理解
> 我们结合MQ中常见问题，看Redis是如何解决的，来进一步理解Redis。

### 4.2.1 Stream用在什么样场景
可用作时通信等，大数据分析，异地数据备份等

![12.db-redis-stream-4.png](../../assets/images/06-中间件/redis/12.db-redis-stream-4.png)

客户端可以平滑扩展，提高处理能力

![13.db-redis-stream-5.png](../../assets/images/06-中间件/redis/13.db-redis-stream-5.png)

### 4.2.2 消息ID的设计是否考虑了时间回拨的问题？
> 在 分布式算法 - ID算法设计中, 一个常见的问题就是时间回拨问题，那么Redis的消息ID设计中是否考虑到这个问题呢？

XADD生成的1553439850328-0，就是Redis生成的消息ID，由两部分组成:**时间戳-序号**。时间戳是毫秒级单位，是生成消息的Redis服务器时间，它是个64位整型（int64）。序号是在这个毫秒时间点内的消息序号，它也是个64位整型。

可以通过multi批处理，来验证序号的递增：
```sh
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> XADD memberMessage * msg one
QUEUED
127.0.0.1:6379> XADD memberMessage * msg two
QUEUED
127.0.0.1:6379> XADD memberMessage * msg three
QUEUED
127.0.0.1:6379> XADD memberMessage * msg four
QUEUED
127.0.0.1:6379> XADD memberMessage * msg five
QUEUED
127.0.0.1:6379> EXEC
1) "1553441006884-0"
2) "1553441006884-1"
3) "1553441006884-2"
4) "1553441006884-3"
5) "1553441006884-4"
```
由于一个redis命令的执行很快，所以可以看到在同一时间戳内，是通过序号递增来表示消息的。

为了保证消息是有序的，因此Redis生成的ID是单调递增有序的。由于ID中包含时间戳部分，为了避免服务器时间错误而带来的问题（例如服务器时间延后了），Redis的每个Stream类型数据都维护一个latest_generated_id属性，用于记录最后一个消息的ID。若发现当前时间戳退后（小于latest_generated_id所记录的），则采用时间戳不变而序号递增的方案来作为新消息ID（这也是序号为什么使用int64的原因，保证有足够多的的序号），从而保证ID的单调递增性质。

强烈建议使用Redis的方案生成消息ID，因为这种时间戳+序号的单调递增的ID方案，几乎可以满足你全部的需求。但同时，记住ID是支持自定义的，别忘了！

### 4.2.3 消费者崩溃带来的会不会消息丢失问题?
为了解决组内消息读取但处理期间消费者崩溃带来的消息丢失问题，STREAM 设计了 Pending 列表，用于记录读取但并未处理完毕的消息。命令XPENDIING 用来获消费组或消费内消费者的未处理完毕的消息。演示如下：

```sh
127.0.0.1:6379> XPENDING mq mqGroup # mpGroup的Pending情况
1) (integer) 5 # 5个已读取但未处理的消息
2) "1553585533795-0" # 起始ID
3) "1553585533795-4" # 结束ID
4) 1) 1) "consumerA" # 消费者A有3个
      2) "3"
   2) 1) "consumerB" # 消费者B有1个
      2) "1"
   3) 1) "consumerC" # 消费者C有1个
      2) "1"

127.0.0.1:6379> XPENDING mq mqGroup - + 10 # 使用 start end count 选项可以获取详细信息
1) 1) "1553585533795-0" # 消息ID
   2) "consumerA" # 消费者
   3) (integer) 1654355 # 从读取到现在经历了1654355ms，IDLE
   4) (integer) 5 # 消息被读取了5次，delivery counter
2) 1) "1553585533795-1"
   2) "consumerA"
   3) (integer) 1654355
   4) (integer) 4
# 共5个，余下3个省略 ...

127.0.0.1:6379> XPENDING mq mqGroup - + 10 consumerA # 在加上消费者参数，获取具体某个消费者的Pending列表
1) 1) "1553585533795-0"
   2) "consumerA"
   3) (integer) 1641083
   4) (integer) 5
# 共3个，余下2个省略 ...
```
每个Pending的消息有4个属性：

- 消息ID
- 所属消费者
- IDLE，已读取时长
- delivery counter，消息被读取次数

上面的结果我们可以看到，我们之前读取的消息，都被记录在Pending列表中，说明全部读到的消息都没有处理，仅仅是读取了。那如何表示消费者处理完毕了消息呢？使用命令 XACK 完成告知消息处理完成，演示如下：
```sh
127.0.0.1:6379> XACK mq mqGroup 1553585533795-0 # 通知消息处理结束，用消息ID标识
(integer) 1

127.0.0.1:6379> XPENDING mq mqGroup # 再次查看Pending列表
1) (integer) 4 # 已读取但未处理的消息已经变为4个
2) "1553585533795-1"
3) "1553585533795-4"
4) 1) 1) "consumerA" # 消费者A，还有2个消息处理
      2) "2"
   2) 1) "consumerB"
      2) "1"
   3) 1) "consumerC"
      2) "1"
127.0.0.1:6379>
```
有了这样一个Pending机制，就意味着在某个消费者读取消息但未处理后，消息是不会丢失的。等待消费者再次上线后，可以读取该Pending列表，就可以继续处理该消息了，保证消息的有序和不丢失。

### 4.2.4 消费者彻底宕机后如何转移给其它消费者处理？
> 还有一个问题，就是若某个消费者宕机之后，没有办法再上线了，那么就需要将该消费者Pending的消息，转给其他的消费者处理，就是消息转移。

消息转移的操作时将某个消息转移到自己的Pending列表中。使用语法XCLAIM来实现，需要设置组、转移的目标消费者和消息ID，同时需要提供IDLE（已被读取时长），只有超过这个时长，才能被转移。演示如下：
```sh
# 当前属于消费者A的消息1553585533795-1，已经15907,787ms未处理了
127.0.0.1:6379> XPENDING mq mqGroup - + 10
1) 1) "1553585533795-1"
   2) "consumerA"
   3) (integer) 15907787
   4) (integer) 4

# 转移超过3600s的消息1553585533795-1到消费者B的Pending列表
127.0.0.1:6379> XCLAIM mq mqGroup consumerB 3600000 1553585533795-1
1) 1) "1553585533795-1"
   2) 1) "msg"
      2) "2"

# 消息1553585533795-1已经转移到消费者B的Pending中。
127.0.0.1:6379> XPENDING mq mqGroup - + 10
1) 1) "1553585533795-1"
   2) "consumerB"
   3) (integer) 84404 # 注意IDLE，被重置了
   4) (integer) 5 # 注意，读取次数也累加了1次
```
以上代码，完成了一次消息转移。转移除了要指定ID外，还需要指定IDLE，保证是长时间未处理的才被转移。被转移的消息的IDLE会被重置，用以保证不会被重复转移，以为可能会出现将过期的消息同时转移给多个消费者的并发操作，设置了IDLE，则可以避免后面的转移不会成功，因为IDLE不满足条件。例如下面的连续两条转移，第二条不会成功。
```sh
127.0.0.1:6379> XCLAIM mq mqGroup consumerB 3600000 1553585533795-1
127.0.0.1:6379> XCLAIM mq mqGroup consumerC 3600000 1553585533795-1
```
这就是消息转移。至此我们使用了一个Pending消息的ID，所属消费者和IDLE的属性，还有一个属性就是消息被读取次数，delivery counter，该属性的作用由于统计消息被读取的次数，包括被转移也算。这个属性主要用在判定是否为错误数据上。
### 4.2.5 坏消息问题，Dead Letter，死信问题
正如上面所说，如果某个消息，不能被消费者处理，也就是不能被XACK，这是要长时间处于Pending列表中，即使被反复的转移给各个消费者也是如此。此时该消息的delivery counter就会累加（上一节的例子可以看到），当累加到某个我们预设的临界值时，我们就认为是坏消息（也叫死信，DeadLetter，无法投递的消息），由于有了判定条件，我们将坏消息处理掉即可，删除即可。删除一个消息，使用XDEL语法，演示如下：
```sh
# 删除队列中的消息
127.0.0.1:6379> XDEL mq 1553585533795-1
(integer) 1
# 查看队列中再无此消息
127.0.0.1:6379> XRANGE mq - +
1) 1) "1553585533795-0"
   2) 1) "msg"
      2) "1"
2) 1) "1553585533795-2"
   2) 1) "msg"
      2) "3"
```
注意本例中，并没有删除Pending中的消息因此你查看Pending，消息还会在。可以执行XACK标识其处理完毕！
# 五、Redis进阶 - 数据结构：对象机制详解
> 我们在前文已经阐述了Redis 5种基础数据类型详解，分别是字符串(string)、列表(list)、哈希(hash)、集合(set)、有序集合(zset)，以及5.0版本中Redis Stream结构详解；那么这些基础类型的底层是如何实现的呢？Redis的每种对象其实都由**对象结构(redisObject)** 与 **对应编码的数据结构**组合而成, 本文主要介绍对象结构(redisObject) 部分。

## 5.1 引入:从哪里开始学习底层？


带着这个问题我们来着手理解底层设计，首先看下图：

![2.db-redis-object-2-2.png](../../assets/images/06-中间件/redis/2.db-redis-object-2-2.png)

它反映了Redis的每种对象其实都由**对象结构(redisObject) 与 对应编码的数据结构**组合而成，而每种对象类型对应若干编码方式，不同的编码方式所对应的底层数据结构是不同的。

所以，我们需要从几个个角度来着手底层研究：

- **对象设计机制**: 对象结构(redisObject)
- **编码类型和底层数据结构**: 对应编码的数据结构

## 5.2 为什么Redis会设计redisObject对象
> 为什么Redis会设计redisObject对象？

在redis的命令中，用于对键进行处理的命令占了很大一部分，而对于键所保存的值的类型（键的类型），键能执行的命令又各不相同。如： `LPUSH` 和 `LLEN` 只能用于列表键, 而 `SADD` 和 `SRANDMEMBER` 只能用于集合键, 等等; 另外一些命令, 比如 `DEL`、 `TTL` 和 `TYPE`, 可以用于任何类型的键；但是要正确实现这些命令, 必须为不同类型的键设置不同的处理方式: 比如说, 删除一个列表键和删除一个字符串键的操作过程就不太一样。

以上的描述说明, **Redis 必须让每个键都带有类型信息, 使得程序可以检查键的类型, 并为它选择合适的处理方式.**

比如说， 集合类型就可以由字典和整数集合两种不同的数据结构实现， 但是， 当用户执行 ZADD 命令时， 他/她应该不必关心集合使用的是什么编码， 只要 Redis 能按照 ZADD 命令的指示， 将新元素添加到集合就可以了。

这说明, **操作数据类型的命令除了要对键的类型进行检查之外, 还需要根据数据类型的不同编码进行多态处理.**

为了解决以上问题,**Redis 构建了自己的类型系统**, 这个系统的主要功能包括:

- redisObject 对象.
- 基于 redisObject 对象的类型检查.
- 基于 redisObject 对象的显式多态函数.
- 对 redisObject 进行分配、共享和销毁的机制.
## 5.3 redisObject数据结构
redisObject 是 Redis 类型系统的核心, 数据库中的每个键、值, 以及 Redis 本身处理的参数, 都表示为这种数据类型.
```c
/*
 * Redis 对象
 */
typedef struct redisObject {

    // 类型
    unsigned type:4;

    // 编码方式
    unsigned encoding:4;

    // LRU - 24位, 记录最末一次访问时间（相对于lru_clock）; 或者 LFU（最少使用的数据：8位频率，16位访问时间）
    unsigned lru:LRU_BITS; // LRU_BITS: 24

    // 引用计数
    int refcount;

    // 指向底层数据结构实例
    void *ptr;

} robj;
```
下图对应上面的结构

![14.db-redis-object-1.png](../../assets/images/06-中间件/redis/14.db-redis-object-1.png)

**其中type、encoding和ptr是最重要的三个属性。**

- **type记录了对象所保存的值的类型**，它的值可能是以下常量中的一个：
```c
/*
* 对象类型
*/
#define OBJ_STRING 0 // 字符串
#define OBJ_LIST 1 // 列表
#define OBJ_SET 2 // 集合
#define OBJ_ZSET 3 // 有序集
#define OBJ_HASH 4 // 哈希表
```
- **encoding记录了对象所保存的值的编码**，它的值可能是以下常量中的一个：
```c
/*
* 对象编码
*/
#define OBJ_ENCODING_RAW 0     /* Raw representation */
#define OBJ_ENCODING_INT 1     /* Encoded as integer */
#define OBJ_ENCODING_HT 2      /* Encoded as hash table */
#define OBJ_ENCODING_ZIPMAP 3  /* 注意：版本2.6后不再使用. */
#define OBJ_ENCODING_LINKEDLIST 4 /* 注意：不再使用了，旧版本2.x中String的底层之一. */
#define OBJ_ENCODING_ZIPLIST 5 /* Encoded as ziplist */
#define OBJ_ENCODING_INTSET 6  /* Encoded as intset */
#define OBJ_ENCODING_SKIPLIST 7  /* Encoded as skiplist */
#define OBJ_ENCODING_EMBSTR 8  /* Embedded sds string encoding */
#define OBJ_ENCODING_QUICKLIST 9 /* Encoded as linked list of ziplists */
#define OBJ_ENCODING_STREAM 10 /* Encoded as a radix tree of listpacks */
```
- **ptr是一个指针，指向实际保存值的数据结构**，这个数据结构由type和encoding属性决定。举个例子， 如果一个redisObject 的type 属性为`OBJ_LIST`， encoding 属性为`OBJ_ENCODING_QUICKLIST`，那么这个对象就是一个Redis 列表(List)，它的值保存在一个QuickList的数据结构内，而ptr 指针就指向quicklist的对象；

下图展示了redisObject 、Redis 所有数据类型、Redis 所有编码方式以及底层数据结构之间的关系（pdai：从6.0版本中梳理而来）：

![14.db-redis-object-1.png](../../assets/images/06-中间件/redis/14.db-redis-object-1.png)

> 注意：`OBJ_ENCODING_ZIPMAP`没有出现在图中，因为在redis2.6开始，它不再是任何数据类型的底层结构(虽然还有zipmap.c的代码); `OBJ_ENCODING_LINKEDLIST`也不支持了，相关代码也删除了。

- **lru属性: 记录了对象最后一次被命令程序访问的时间**

**空转时长**：当前时间减去键的值对象的lru时间，就是该键的空转时长。Object idletime命令可以打印出给定键的空转时长

如果服务器打开了maxmemory选项，并且服务器用于回收内存的算法为volatile-lru或者allkeys-lru，那么当服务器占用的内存数超过了maxmemory选项所设置的上限值时，空转时长较高的那部分键会优先被服务器释放，从而回收内存。

## 5.4 命令的类型检查和多态
> 那么Redis是如何处理一条命令的呢？

**当执行一个处理数据类型命令的时候，redis执行以下步骤**

- 根据给定的key，在数据库字典中查找和他相对应的redisObject，如果没找到，就返回NULL；
- 检查redisObject的type属性和执行命令所需的类型是否相符，如果不相符，返回类型错误；
- 根据redisObject的encoding属性所指定的编码，选择合适的操作函数来处理底层的数据结构；
- 返回数据结构的操作结果作为命令的返回值。

比如现在执行LPOP命令：

![15.db-redis-object-3.png](../../assets/images/06-中间件/redis/15.db-redis-object-3.png)

## 5.5 对象共享
> redis一般会把一些常见的值放到一个共享对象中，这样可使程序避免了重复分配的麻烦，也节约了一些CPU时间。

**redis预分配的值对象如下：**

- 各种命令的返回值，比如成功时返回的OK，错误时返回的ERROR，命令入队事务时返回的QUEUE，等等
- 包括0 在内，小于REDIS_SHARED_INTEGERS的所有整数（REDIS_SHARED_INTEGERS的默认值是10000）

![16.db-redis-object-4.png](../../assets/images/06-中间件/redis/16.db-redis-object-4.png)

> 注意：共享对象只能被字典和双向链表这类能带有指针的数据结构使用。像整数集合和压缩列表这些只能保存字符串、整数等自包含的内存数据结构,就无法使用共享对象。

**为什么redis不共享列表对象、哈希对象、集合对象、有序集合对象，只共享字符串对象？**

- 列表对象、哈希对象、集合对象、有序集合对象，本身可以包含字符串对象，复杂度较高。
- 如果共享对象是保存字符串对象，那么验证操作的复杂度为O(1)
- 如果共享对象是保存字符串值的字符串对象，那么验证操作的复杂度为O(N)
- 如果共享对象是包含多个值的对象，其中值本身又是字符串对象，即其它对象中嵌套了字符串对象，比如列表对象、哈希对象，那么验证操作的复杂度将会是O(N的平方)

如果对复杂度较高的对象创建共享对象，需要消耗很大的CPU，用这种消耗去换取内存空间，是不合适的

## 5.6 引用计数以及对象的消毁
> redisObject中有refcount属性，是对象的引用计数，显然计数0那么就是可以回收。

- 每个redisObject结构都带有一个refcount属性，指示这个对象被引用了多少次；
- 当新创建一个对象时，它的refcount属性被设置为1；
- 当对一个对象进行共享时，redis将这个对象的refcount加一；
- 当使用完一个对象后，或者消除对一个对象的引用之后，程序将对象的refcount减一；
- 当对象的refcount降至0 时，这个RedisObject结构，以及它引用的数据结构的内存都会被释放。

## 5.7 小结
- redis使用自己实现的对象机制(redisObject)来实现类型判断、命令多态和基于引用次数的垃圾回收；
- redis会预分配一些常用的数据对象，并通过共享这些对象来减少内存占用，和避免频繁的为小对象分配内存。
# 六、Redis进阶 - 数据结构：底层数据结构详解
> 我们在前文已经阐述了Redis 5种基础数据类型详解，分别是字符串(string)、列表(list)、哈希(hash)、集合(set)、有序集合(zset)；那么这些基础类型的底层是如何实现的呢？Redis的每种对象其实都由**对象结构(redisObject) 与 对应编码的数据结构**组合而成, 前文是第一部分**对象机制详解**, 本文主要介绍**底层数据结构** 部分。（在这特别提下，大多数人构建知识体系去读源码是不太现实的，这时候我认为更为重要的是，理解为何会出现这样的设计，知道它解决了什么问题，然后对应了解相关知识点，最终在一些应用场景时可以以此来通过调整存储的类型进一步提升优化性能）。
## 6.1 底层数据结构引入
在对对象机制（redisObject）有了初步认识之后，我们便可以继续理解如下的底层数据结构部分：

![17.db-redis-object-2-3.png](../../assets/images/06-中间件/redis/17.db-redis-object-2-3.png)

- 简单动态字符串 - sds
- 压缩列表 - ZipList
- 快表 - QuickList
- 字典/哈希表 - Dict
- 整数集 - IntSet
- 跳表 - ZSkipList
## 6.2 简单动态字符串 - sds
> Redis 是用 C 语言写的，但是对于Redis的字符串，却不是 C 语言中的字符串（即以空字符’\0’结尾的字符数组），它是自己构建了一种名为 **简单动态字符串（simple dynamic string,SDS）**的抽象类型，并将 SDS 作为 Redis的默认字符串表示。

### 6.2.1 SDS 定义
> 这是一种用于存储二进制数据的一种结构, 具有动态扩容的特点. 其实现位于src/sds.h与src/sds.c中。

- **SDS的总体概览**如下图:

![18.db-redis-ds-x-3.png](../../assets/images/06-中间件/redis/18.db-redis-ds-x-3.png)

其中`sdshd`r是头部, `buf`是真实存储用户数据的地方. 另外注意, 从命名上能看出来, 这个数据结构除了能存储二进制数据, 显然是用于设计作为字符串使用的, 所以在buf中, 用户数据后总跟着一个\0. 即图中 `"数据" + "\0" 是为所谓的buf`。

如下是6.0源码中sds相关的结构：

- **如下是6.0源码中sds相关的结构：**

![19.db-redis-ds-x-1.png](../../assets/images/06-中间件/redis/19.db-redis-ds-x-1.png)

通过上图我们可以看到，SDS有五种不同的头部. 其中sdshdr5实际并未使用到. 所以实际上有四种不同的头部, 分别如下:

![20..db-redis-ds-x-2.png](../../assets/images/06-中间件/redis/20..db-redis-ds-x-2.png)

其中：

- `len` 保存了SDS保存字符串的长度
- `buf[]` 数组用来保存字符串的每个元素
- `alloc` 分别以uint8, uint16, uint32, uint64表示整个SDS, 除过头部与末尾的\0, 剩余的字节数.
- `flags` 始终为一字节, 以低三位标示着头部的类型, 高5位未使用.
### 6.2.2 为什么使用SDS
> 为什么不使用C语言字符串实现，而是使用 SDS呢？这样实现有什么好处？

- **常数复杂度获取字符串长度**

由于 len 属性的存在，我们获取 SDS 字符串的长度只需要读取 len 属性，时间复杂度为 O(1)。而对于 C 语言，获取字符串的长度通常是经过遍历计数来实现的，时间复杂度为 O(n)。通过 `strlen key` 命令可以获取 key 的字符串长度。

- **杜绝缓冲区溢出**

我们知道在 C 语言中使用 `strcat` 函数来进行两个字符串的拼接，一旦没有分配足够长度的内存空间，就会造成缓冲区溢出。而对于 SDS 数据类型，在进行字符修改的时候，会首先根据记录的 len 属性检查内存空间是否满足需求，如果不满足，会进行相应的空间扩展，然后在进行修改操作，所以不会出现缓冲区溢出。

- **减少修改字符串的内存重新分配次数**

C语言由于不记录字符串的长度，所以如果要修改字符串，必须要重新分配内存（先释放再申请），因为如果没有重新分配，字符串长度增大时会造成内存缓冲区溢出，字符串长度减小时会造成内存泄露。

而对于SDS，由于`len`属性和`alloc`属性的存在，对于修改字符串SDS实现了**空间预分配**和**惰性空间释放**两种策略：

1、**空间预分配**：对字符串进行空间扩展的时候，扩展的内存比实际需要的多，这样可以减少连续执行字符串增长操作所需的内存重分配次数。

2、**惰性空间释放**：对字符串进行缩短操作时，程序不立即使用内存重新分配来回收缩短后多余的字节，而是使用 alloc 属性将这些字节的数量记录下来，等待后续使用。（当然SDS也提供了相应的API，当我们有需要时，也可以手动释放这些未使用的空间。）

- **二进制安全**

因为C字符串以空字符作为字符串(`\0`)结束的标识，而对于一些二进制文件（如图片等），内容可能包括空字符串(`\0`)，因此C字符串无法正确存取；而所有 SDS 的API 都是以处理二进制的方式来处理 buf 里面的元素，并且 SDS 不是以空字符串来判断是否结束，而是以 len 属性表示的长度来判断字符串是否结束。

- **兼容部分 C 字符串函数**

虽然 SDS 是二进制安全的，但是一样遵从每个字符串都是以空字符串(`\0`)结尾的惯例，这样可以重用 C 语言库`<string.h>`中的一部分函数。

### 6.2.3 空间预分配补进一步理解
当执行追加操作时，比如现在给`key=‘Hello World’`的字符串后追加`‘ again!’`则这时的len=18，free由0变成了18，此时的`buf='Hello World again!\0....................'`(.表示空格)，也就是buf的内存空间是`18+18+1=37个`字节，其中`‘\0’`占1个字节redis给字符串多分配了18个字节的预分配空间，所以下次还有append追加的时候，如果预分配空间足够，就无须在进行空间分配了。在当前版本中，当新字符串的长度小于1M时，redis会分配他们所需大小一倍的空间，当大于1M的时候，就为他们额外多分配1M的空间。

**思考：这种分配策略会浪费内存资源吗？**

答：执行过APPEND 命令的字符串会带有额外的预分配空间，这些预分配空间不会被释放，除非该字符串所对应的键被删除，或者等到关闭Redis 之后，再次启动时重新载入的字符串对象将不会有预分配空间。因为执行APPEND 命令的字符串键数量通常并不多，占用内存的体积通常也不大，所以这一般并不算什么问题。另一方面，如果执行APPEND 操作的键很多，而字符串的体积又很大的话，那可能就需要修改Redis 服务器，让它定时释放一些字符串键的预分配空间，从而更有效地使用内存。

### 6.2.4 小结
redis的字符串表示为sds，而不是C字符串（以\0结尾的char*）， 它是Redis 底层所使用的字符串表示，它被用在几乎所有的Redis 模块中。可以看如下对比：

![21.redis-ds-2.png](../../assets/images/06-中间件/redis/21.redis-ds-2.png)

一般来说，SDS 除了保存数据库中的字符串值以外，SDS 还可以作为缓冲区（buffer）：包括 AOF 模块中的AOF缓冲区以及客户端状态中的输入缓冲区。

## 6.3 压缩列表 - ZipList
> ziplist是为了提高存储效率而设计的一种特殊编码的双向链表。它可以存储字符串或者整数，存储整数时是采用整数的二进制而不是字符串形式存储。它能在O(1)的时间复杂度下完成list两端的push和pop操作。但是因为每次操作都需要重新分配ziplist的内存，所以实际复杂度和ziplist的内存使用量相关。

### 6.3.1 ziplist结构
先看下6.0中对应的源码和介绍

![22.db-redis-ds-x-5.png](../../assets/images/06-中间件/redis/22.db-redis-ds-x-5.png)

整个ziplist在内存中的存储格式如下：

![23.db-redis-ds-x-6.png](../../assets/images/06-中间件/redis/23.db-redis-ds-x-6.png)

- `zlbytes`字段的类型是uint32_t, 这个字段中存储的是整个ziplist所占用的内存的字节数
- `zltail`字段的类型是uint32_t, 它指的是ziplist中最后一个entry的偏移量. 用于快速定位最后一个entry, 以快速完成pop等操作
- `zllen`字段的类型是uint16_t, 它指的是整个ziplit中entry的数量. 这个值只占2bytes（16位）: 如果ziplist中entry的数目小于65535(2的16次方), 那么该字段中存储的就是实际entry的值. 若等于或超过65535, 那么该字段的值固定为65535, 但实际数量需要一个个entry的去遍历所有entry才能得到.
- `zlend`是一个终止字节, 其值为全F, 即0xff. ziplist保证任何情况下, 一个entry的首字节都不会是255
### 6.3.2 Entry结构
那么entry是什么结构呢？

- **先看下源码中相关介绍**

![24.db-redis-ds-x-7.png](../../assets/images/06-中间件/redis/24.db-redis-ds-x-7.png)

**第一种情况**：一般结构 `<prevlen> <encoding> <entry-data>`

`prevlen`：前一个entry的大小，编码方式见下文；

`encoding`：不同的情况下值不同，用于表示当前entry的类型和长度；

`entry-data`：真是用于存储entry表示的数据；

**第二种情况**：在entry中存储的是int类型时，encoding和entry-data会合并在encoding中表示，此时没有entry-data字段；

redis中，在存储数据时，会先尝试将string转换成int存储，节省空间；

此时entry结构：`<prevlen> <encoding>`

- **prevlen编码**

当前一个元素长度小于254（255用于zlend,这就是前面所说的每个entry都保证不以255开头）的时候，prevlen长度为1个字节，值即为前一个entry的长度，如果长度大于等于254的时候，prevlen用5个字节表示，第一字节设置为254，后面4个字节存储一个小端的无符号整型，表示前一个entry的长度；
```sh
<prevlen from 0 to 253> <encoding> <entry>      //长度小于254结构
0xFE <4 bytes unsigned little endian prevlen> <encoding> <entry>   //长度大于等于254
```
- **encoding编码**
encoding的长度和值根据保存的是int还是string，还有数据的长度而定；

前两位用来表示类型，当为“11”时，表示entry存储的是int类型，其它表示存储的是string；

**存储string时：**

`|00pppppp|` ：此时encoding长度为1个字节，该字节的后六位表示entry中存储的string长度，因为是6位，所以entry中存储的string长度不能超过63；

`|01pppppp|qqqqqqqq|` 此时encoding长度为两个字节；此时encoding的后14位用来存储string长度，长度不能超过16383；

`|10000000|qqqqqqqq|rrrrrrrr|ssssssss|ttttttt|` 此时encoding长度为5个字节，后面的4个字节用来表示encoding中存储的字符串长度，长度不能超过2^32 - 1;

**存储int时：**

`|11000000|` encoding为3个字节，后2个字节表示一个int16；

`|11010000|` encoding为5个字节，后4个字节表示一个int32;

`|11100000|` encoding 为9个字节，后8字节表示一个int64;

`|11110000|` encoding为4个字节，后3个字节表示一个有符号整型；

`|11111110|` encoding为2字节，后1个字节表示一个有符号整型；

`|1111xxxx|` encoding长度就只有1个字节，xxxx表示一个0 - 12的整数值；

`|11111111|` 还记得zlend么？

- **源码中数据结构支撑**

你可以看到为了操作上的简易实际还增加了几个属性
```c
/* We use this function to receive information about a ziplist entry.
 * Note that this is not how the data is actually encoded, is just what we
 * get filled by a function in order to operate more easily. */
typedef struct zlentry {
    unsigned int prevrawlensize; /* Bytes used to encode the previous entry len*/
    unsigned int prevrawlen;     /* Previous entry len. */
    unsigned int lensize;        /* Bytes used to encode this entry type/len.
                                    For example strings have a 1, 2 or 5 bytes
                                    header. Integers always use a single byte.*/
    unsigned int len;            /* Bytes used to represent the actual entry.
                                    For strings this is just the string length
                                    while for integers it is 1, 2, 3, 4, 8 or
                                    0 (for 4 bit immediate) depending on the
                                    number range. */
    unsigned int headersize;     /* prevrawlensize + lensize. */
    unsigned char encoding;      /* Set to ZIP_STR_* or ZIP_INT_* depending on
                                    the entry encoding. However for 4 bits
                                    immediate integers this can assume a range
                                    of values and must be range-checked. */
    unsigned char *p;            /* Pointer to the very start of the entry, that
                                    is, this points to prev-entry-len field. */
} zlentry;
```
- `prevrawlensize` 表示 previous_entry_length字段的长度
- `prevrawlen` 表示 previous_entry_length字段存储的内容
- `lensize` 表示 encoding字段的长度
- `len` 表示数据内容长度
- `headersize` 表示当前元素的首部长度，即previous_entry_length字段长度与encoding字段长度之和
- `encoding` 表示数据类型
- `p` 表示当前元素首地址

### 6.3.3 为什么ZipList特别省内存
> 所以只有理解上面的Entry结构，我们才会真正理解ZipList为什么是特别节省内存的数据结构。

- ziplist节省内存是相对于普通的list来说的，如果是普通的数组，那么它每个元素占用的内存是一样的且取决于最大的那个元素（很明显它是需要预留空间的）；
- 所以ziplist在设计时就很容易想到要尽量让每个元素按照实际的内容大小存储，所以增加encoding字段，针对不同的encoding来细化存储大小；
- 这时候还需要解决的一个问题是遍历元素时如何定位下一个元素呢？在普通数组中每个元素定长，所以不需要考虑这个问题；但是ziplist中每个data占据的内存不一样，所以为了解决遍历，需要增加记录上一个元素的length，所以增加了prelen字段。

**为什么我们去研究ziplist特别节省内存的数据结构？**在实际应用中，大量存储字符串的优化是需要你对底层的数据结构有一定的理解的，而ziplist在场景优化的时候也被考虑采用的首选。

### 6.3.4 ziplist的缺点
最后我们再看看它的一些缺点：

- ziplist也不预留内存空间, 并且在移除结点后, 也是立即缩容, 这代表每次写操作都会进行内存分配操作.
- 结点如果扩容, 导致结点占用的内存增长, 并且超过254字节的话, 可能会导致链式反应: 其后一个结点的entry.prevlen需要从一字节扩容至五字节. **最坏情况下, 第一个结点的扩容, 会导致整个ziplist表中的后续所有结点的entry.prevlen字段扩容.** 虽然这个内存重分配的操作依然只会发生一次, 但代码中的时间复杂度是o(N)级别, 因为链式扩容只能一步一步的计算. 但这种情况的概率十分的小, 一般情况下链式扩容能连锁反映五六次就很不幸了. 之所以说这是一个蛋疼问题, 是因为, 这样的坏场景下, 其实时间复杂度并不高: 依次计算每个entry新的空间占用, 也就是o(N), 总体占用计算出来后, 只执行一次内存重分配, 与对应的memmove操作, 就可以了.

## 6.4 快表 - QuickList
> quicklist这个结构是Redis在3.2版本后新加的, 之前的版本是list(即linkedlist)， 用于String数据类型中。

它是一种以ziplist为结点的双端链表结构. 宏观上, quicklist是一个链表, 微观上, 链表中的每个结点都是一个ziplist。

### 6.4.1 quicklist结构
如下是6.0源码中quicklist相关的结构：
```c
/* Node, quicklist, and Iterator are the only data structures used currently. */

/* quicklistNode is a 32 byte struct describing a ziplist for a quicklist.
 * We use bit fields keep the quicklistNode at 32 bytes.
 * count: 16 bits, max 65536 (max zl bytes is 65k, so max count actually < 32k).
 * encoding: 2 bits, RAW=1, LZF=2.
 * container: 2 bits, NONE=1, ZIPLIST=2.
 * recompress: 1 bit, bool, true if node is temporarry decompressed for usage.
 * attempted_compress: 1 bit, boolean, used for verifying during testing.
 * extra: 10 bits, free for future use; pads out the remainder of 32 bits */
typedef struct quicklistNode {
    struct quicklistNode *prev;
    struct quicklistNode *next;
    unsigned char *zl;
    unsigned int sz;             /* ziplist size in bytes */
    unsigned int count : 16;     /* count of items in ziplist */
    unsigned int encoding : 2;   /* RAW==1 or LZF==2 */
    unsigned int container : 2;  /* NONE==1 or ZIPLIST==2 */
    unsigned int recompress : 1; /* was this node previous compressed? */
    unsigned int attempted_compress : 1; /* node can't compress; too small */
    unsigned int extra : 10; /* more bits to steal for future usage */
} quicklistNode;

/* quicklistLZF is a 4+N byte struct holding 'sz' followed by 'compressed'.
 * 'sz' is byte length of 'compressed' field.
 * 'compressed' is LZF data with total (compressed) length 'sz'
 * NOTE: uncompressed length is stored in quicklistNode->sz.
 * When quicklistNode->zl is compressed, node->zl points to a quicklistLZF */
typedef struct quicklistLZF {
    unsigned int sz; /* LZF size in bytes*/
    char compressed[];
} quicklistLZF;

/* Bookmarks are padded with realloc at the end of of the quicklist struct.
 * They should only be used for very big lists if thousands of nodes were the
 * excess memory usage is negligible, and there's a real need to iterate on them
 * in portions.
 * When not used, they don't add any memory overhead, but when used and then
 * deleted, some overhead remains (to avoid resonance).
 * The number of bookmarks used should be kept to minimum since it also adds
 * overhead on node deletion (searching for a bookmark to update). */
typedef struct quicklistBookmark {
    quicklistNode *node;
    char *name;
} quicklistBookmark;


/* quicklist is a 40 byte struct (on 64-bit systems) describing a quicklist.
 * 'count' is the number of total entries.
 * 'len' is the number of quicklist nodes.
 * 'compress' is: -1 if compression disabled, otherwise it's the number
 *                of quicklistNodes to leave uncompressed at ends of quicklist.
 * 'fill' is the user-requested (or default) fill factor.
 * 'bookmakrs are an optional feature that is used by realloc this struct,
 *      so that they don't consume memory when not used. */
typedef struct quicklist {
    quicklistNode *head;
    quicklistNode *tail;
    unsigned long count;        /* total count of all entries in all ziplists */
    unsigned long len;          /* number of quicklistNodes */
    int fill : QL_FILL_BITS;              /* fill factor for individual nodes */
    unsigned int compress : QL_COMP_BITS; /* depth of end nodes not to compress;0=off */
    unsigned int bookmark_count: QL_BM_BITS;
    quicklistBookmark bookmarks[];
} quicklist;

typedef struct quicklistIter {
    const quicklist *quicklist;
    quicklistNode *current;
    unsigned char *zi;
    long offset; /* offset in current ziplist */
    int direction;
} quicklistIter;

typedef struct quicklistEntry {
    const quicklist *quicklist;
    quicklistNode *node;
    unsigned char *zi;
    unsigned char *value;
    long long longval;
    unsigned int sz;
    int offset;
} quicklistEntry;
```
这里定义了6个结构体:

- `quicklistNode,` 宏观上, quicklist是一个链表, 这个结构描述的就是链表中的结点. 它通过zl字段持有底层的ziplist. 简单来讲, 它描述了一个ziplist实例
- `quicklistLZF,` ziplist是一段连续的内存, 用LZ4算法压缩后, 就可以包装成一个quicklistLZF结构. 是否压缩quicklist中的每个ziplist实例是一个可配置项. 若这个配置项是开启的, 那么quicklistNode.zl字段指向的就不是一个ziplist实例, 而是一个压缩后的quicklistLZF实例
- `quicklistBookmark,` 在quicklist尾部增加的一个书签，它只有在大量节点的多余内存使用量可以忽略不计的情况且确实需要分批迭代它们，才会被使用。当不使用它们时，它们不会增加任何内存开销。
- `quicklist.` 这就是一个双链表的定义. head, tail分别指向头尾指针. len代表链表中的结点. count指的是整个quicklist中的所有ziplist中的entry的数目. fill字段影响着每个链表结点中ziplist的最大占用空间, compress影响着是否要对每个ziplist以LZ4算法进行进一步压缩以更节省内存空间.
- `quicklistIter`是一个迭代器
- `quicklistEntry`是对ziplist中的entry概念的封装. quicklist作为一个封装良好的数据结构, 不希望使用者感知到其内部的实现, 所以需要把ziplist.entry的概念重新包装一下.
### 6.4.2 quicklist内存布局图
quicklist的内存布局图如下所示:

![25.db-redis-ds-x-4.png](../../assets/images/06-中间件/redis/25.db-redis-ds-x-4.png)

### 6.4.3 quicklist更多额外信息
下面是有关quicklist的更多额外信息:

- `quicklist.fill`的值影响着每个链表结点中, ziplist的长度.
1. 当数值为负数时, 代表以字节数限制单个ziplist的最大长度. 具体为:
2. -1 不超过4kb
3. -2 不超过 8kb
4. -3 不超过 16kb
5. -4 不超过 32kb
6. -5 不超过 64kb
7. 当数值为正数时, 代表以entry数目限制单个ziplist的长度. 值即为数目. 由于该字段仅占16位, 所以以entry数目限制ziplist的容量时, 最大值为2^15个
- `quicklist.compress`的值影响着quicklistNode.zl字段指向的是原生的ziplist, 还是经过压缩包装后的quicklistLZF
1. 0 表示不压缩, zl字段直接指向ziplist
2. 1 表示quicklist的链表头尾结点不压缩, 其余结点的zl字段指向的是经过压缩后的quicklistLZF
3. 2 表示quicklist的链表头两个, 与末两个结点不压缩, 其余结点的zl字段指向的是经过压缩后的quicklistLZF
4. 以此类推, 最大值为2^16
- `quicklistNode.encoding`字段, 以指示本链表结点所持有的ziplist是否经过了压缩. 1代表未压缩, 持有的是原生的ziplist, 2代表压缩过
- `quicklistNode.container`字段指示的是每个链表结点所持有的数据类型是什么. 默认的实现是ziplist, 对应的该字段的值是2, 目前Redis没有提供其它实现. 所以实际上, 该字段的值恒为2
- `quicklistNode.recompress`字段指示的是当前结点所持有的ziplist是否经过了解压. 如果该字段为1即代表之前被解压过, 且需要在下一次操作时重新压缩.

quicklist的具体实现代码篇幅很长, 这里就不贴代码片断了, 从内存布局上也能看出来, 由于每个结点持有的ziplist是有上限长度的, 所以在与操作时要考虑的分支情况比较多。

quicklist有自己的优点, 也有缺点, 对于使用者来说, 其使用体验类似于线性数据结构, list作为最传统的双链表, 结点通过指针持有数据, 指针字段会耗费大量内存. ziplist解决了耗费内存这个问题. 但引入了新的问题: 每次写操作整个ziplist的内存都需要重分配. quicklist在两者之间做了一个平衡. 并且使用者可以通过自定义quicklist.fill, 根据实际业务情况, 经验主义调参.

## 6.5 字典/哈希表 - Dict
> 本质上就是哈希表, 这个在很多语言中都有，对于开发人员人员来说比较熟悉，这里就简单介绍下。

### 6.5.1 数据结构
哈希表结构定义：
```c
typedef struct dictht{
    //哈希表数组
    dictEntry **table;
    //哈希表大小
    unsigned long size;
    //哈希表大小掩码，用于计算索引值
    //总是等于 size-1
    unsigned long sizemask;
    //该哈希表已有节点的数量
    unsigned long used;
 
}dictht
```
哈希表是由数组 table 组成，table 中每个元素都是指向 dict.h/dictEntry 结构，dictEntry 结构定义如下：
```c
typedef struct dictEntry{
     //键
     void *key;
     //值
     union{
          void *val;
          uint64_tu64;
          int64_ts64;
     }v;
 
     //指向下一个哈希表节点，形成链表
     struct dictEntry *next;
}dictEntry
```
key 用来保存键，val 属性用来保存值，值可以是一个指针，也可以是uint64_t整数，也可以是int64_t整数。

注意这里还有一个指向下一个哈希表节点的指针，我们知道哈希表最大的问题是存在哈希冲突，如何解决哈希冲突，有**开放地址法和链地址法**。这里采用的便是链地址法，通过next这个指针可以将多个哈希值相同的键值对连接在一起，用来**解决哈希冲突。**

![26.db-redis-ds-x-13.png](../../assets/images/06-中间件/redis/26.db-redis-ds-x-13.png)

1. **链地址法（Chaining）**
- **定义**：链地址法是一种解决哈希冲突的方法，其中每个哈希桶（bucket）不直接存储单个键值对，而是存储一个链表（或其他集合结构）。当多个键的哈希值相同时，它们会被放入同一个桶中，并通过指针连接起来（如您提到的 `next` 指针）。
- **工作原理**：
  - 插入时：计算键的哈希值，找到对应桶。如果桶为空，直接插入；如果桶已占用，则将新键值对添加到链表的头部或尾部。
  - 查找时：计算哈希值，遍历对应桶中的链表，直到找到匹配的键。
  - 删除时：类似查找，找到后从链表中移除节点。
- **优点**：
  - 简单易实现，冲突处理灵活。
  - 平均性能较好，尤其在负载因子（元素数量/桶数量）较高时，仍能保持较好效率。
  - 适合动态数据，链表可以轻松扩展。
- **缺点**：
  - 需要额外内存存储指针（链表节点开销）。
  - 如果链表过长，查找性能可能退化为 O(n)，但可通过优化（如使用平衡树代替链表）缓解。
- **例子**：Redis 的哈希表使用链地址法，每个桶是一个双向链表（早期版本）或结合跳表（新版本优化）。

---

2. **开放地址法（Open Addressing）**
- **定义**：开放地址法是一种解决哈希冲突的方法，其中所有键值对都直接存储在哈希表的数组（桶数组）中，没有额外的链表。当发生冲突时，系统会按照某种探测序列（如线性探测、二次探测）在数组中寻找下一个空闲桶。
- **工作原理**：
  - 插入时：计算键的哈希值，找到对应桶。如果桶被占用，则根据探测规则（如线性探测：检查下一个桶）继续寻找空闲桶，直到找到空位插入。
  - 查找时：类似插入，从哈希值对应的桶开始，沿探测序列检查每个桶，直到找到键或遇到空桶（表示键不存在）。
  - 删除时：不能直接删除，否则会破坏探测序列。通常使用“标记删除”（如设置特殊标志），避免后续查找中断。
- **常见的探测方法**：
  - **线性探测（Linear Probing）**：冲突时，顺序检查下一个桶（如 index+1, index+2...）。
  - **二次探测（Quadratic Probing）**：冲突时，检查桶的偏移量为二次方（如 index+1², index+2²...）。
  - **双重哈希（Double Hashing）**：使用第二个哈希函数计算步长。
- **优点**：
  - 内存开销小，不需要指针，所有数据存储在连续数组中，缓存友好。
  - 适合内存受限的场景。
- **缺点**：
  - 容易产生“聚集”现象（尤其是线性探测），导致性能下降。
  - 负载因子必须较低（通常 <0.7），否则查找效率骤降。
  - 删除操作复杂，需要特殊处理。
- **例子**：Java 的 `HashMap` 在早期版本中使用开放地址法，但现代实现多改用链地址法；一些嵌入式系统或数据库索引可能仍用开放地址法。****

### 6.5.2 一些要点
- **哈希算法**Redis计算哈希值和索引值方法如下：
```sh
#1、使用字典设置的哈希函数，计算键 key 的哈希值
hash = dict->type->hashFunction(key);

#2、使用哈希表的sizemask属性和第一步得到的哈希值，计算索引值
index = hash & dict->ht[x].sizemask;
```
- **解决哈希冲突：**这个问题上面我们介绍了，方法是链地址法。通过字典里面的 *next 指针指向下一个具有相同索引值的哈希表节点。

- **扩容和收缩：**当哈希表保存的键值对太多或者太少时，就要通过 rerehash(重新散列)来对哈希表进行相应的扩展或者收缩。具体步骤：

1、如果执行扩展操作，会基于原哈希表创建一个大小等于 ht[0].used*2n 的哈希表（也就是每次扩展都是根据原哈希表已使用的空间扩大一倍创建另一个哈希表）。相反如果执行的是收缩操作，每次收缩是根据已使用空间缩小一倍创建一个新的哈希表。

2、重新利用上面的哈希算法，计算索引值，然后将键值对放到新的哈希表位置上。

3、所有键值对都迁徙完毕后，释放原哈希表的内存空间。

- **触发扩容的条件：**

1、服务器目前没有执行 BGSAVE 命令或者 BGREWRITEAOF 命令，并且负载因子大于等于1。

2、服务器目前正在执行 BGSAVE 命令或者 BGREWRITEAOF 命令，并且负载因子大于等于5。

ps：负载因子 = 哈希表已保存节点数量 / 哈希表大小。

- **渐近式 rehash**

什么叫渐进式 rehash？也就是说扩容和收缩操作不是一次性、集中式完成的，而是分多次、渐进式完成的。如果保存在Redis中的键值对只有几个几十个，那么 rehash 操作可以瞬间完成，但是如果键值对有几百万，几千万甚至几亿，那么要一次性的进行 rehash，势必会造成Redis一段时间内不能进行别的操作。所以Redis采用渐进式 rehash,这样在进行渐进式rehash期间，字典的删除查找更新等操作可能会在两个哈希表上进行，第一个哈希表没有找到，就会去第二个哈希表上进行查找。但是进行 增加操作，一定是在新的哈希表上进行的。

## 6.6 整数集 - IntSet
> 整数集合（intset）是集合类型的底层实现之一，当一个集合只包含整数值元素，并且这个集合的元素数量不多时，Redis 就会使用整数集合作为集合键的底层实现。

### 6.6.1 intset结构
首先看源码结构
```c
typedef struct intset {
    uint32_t encoding;
    uint32_t length;
    int8_t contents[];
} intset;
```
- `encoding` 表示编码方式，的取值有三个：INTSET_ENC_INT16, INTSET_ENC_INT32, INTSET_ENC_INT64

- `length` 代表其中存储的整数的个数

- `contents` 指向实际存储数值的连续内存区域, 就是一个数组；整数集合的每个元素都是 contents 数组的一个数组项（item），**各个项在数组中按值得大小从小到大有序排序**，且数组中不包含任何重复项。（虽然 intset 结构将 contents 属性声明为 int8_t 类型的数组，但实际上 contents 数组并不保存任何 int8_t 类型的值，contents 数组的真正类型取决于 encoding 属性的值）

### 6.6.2 内存布局图
其内存布局如下图所示

![27.db-redis-ds-x-8.png](../../assets/images/06-中间件/redis/27.db-redis-ds-x-8.png)

我们可以看到，content数组里面每个元素的数据类型是由encoding来决定的，那么如果原来的数据类型是int16, 当我们再插入一个int32类型的数据时怎么办呢？这就是下面要说的intset的升级。

### 6.6.3 整数集合的升级
当在一个int16类型的整数集合中插入一个int32类型的值，整个集合的所有元素都会转换成32类型。 整个过程有三步：

- 根据新元素的类型（比如int32），扩展整数集合底层数组的空间大小，并为新元素分配空间。

- 将底层数组现有的所有元素都转换成与新元素相同的类型， 并将类型转换后的元素放置到正确的位上， 而且在放置元素的过程中， 需要继续维持底层数组的有序性质不变。

- 最后改变encoding的值，length+1。

**那么如果我们删除掉刚加入的int32类型时，会不会做一个降级操作呢？**

不会。主要还是减少开销的权衡。

## 6.7 跳表 - ZSkipList
> 跳跃表结构在 Redis 中的运用场景只有一个，那就是作为有序列表 (Zset) 的使用。跳跃表的性能可以保证在查找，删除，添加等操作的时候在对数期望时间内完成，这个性能是可以和平衡树来相比较的，而且在实现方面比平衡树要优雅，这就是跳跃表的长处。跳跃表的缺点就是需要的存储空间比较大，属于利用空间来换取时间的数据结构。

### 6.7.1 什么是跳跃表
> 跳跃表要解决什么问题呢？如果你一上来就去看它的实现，你很难理解设计的本质，所以先要看它的设计要解决什么问题。

对于于一个单链表来讲，即便链表中存储的数据是有序的，如果我们要想在其中查找某个数据，也只能从头到尾遍历链表。这样查找效率就会很低，时间复杂度会很高，是 O(n)。比如查找12，需要7次查找

![28.db-redis-ds-x-9.png](../../assets/images/06-中间件/redis/28.db-redis-ds-x-9.png)

如果我们增加如下两级索引，那么它搜索次数就变成了3次

![29.db-redis-ds-x-10.png](../../assets/images/06-中间件/redis/29.db-redis-ds-x-10.png)

### 6.7.2 Redis跳跃表的设计
redis跳跃表并没有在单独的类(比如skplist.c)中定义，而是其定义在server.h中, 如下:
```c
/* ZSETs use a specialized version of Skiplists */
typedef struct zskiplistNode {
    sds ele;
    double score;
    struct zskiplistNode *backward;
    struct zskiplistLevel {
        struct zskiplistNode *forward;
        unsigned int span;
    } level[];
} zskiplistNode;

typedef struct zskiplist {
    struct zskiplistNode *header, *tail;
    unsigned long length;
    int level;
} zskiplist;
```
其内存布局如下图:

![30.db-redis-ds-x-11.png](../../assets/images/06-中间件/redis/30.db-redis-ds-x-11.png)

**zskiplist的核心设计要点**

- **头节点**不持有任何数据, 且其level[]的长度为32
- **每个结点**
  - `ele`字段，持有数据，是sds类型
  - `score`字段, 其标示着结点的得分, 结点之间凭借得分来判断先后顺序, 跳跃表中的结点按结点的得分升序排列.
  - `backward`指针, 这是原版跳跃表中所没有的. 该指针指向结点的前一个紧邻结点.
  - `level`字段, 用以记录所有结点(除过头节点外)；每个结点中最多持有32个zskiplistLevel结构. 实际数量在结点创建时, 按幂次定律随机生成(不超过32). 每个zskiplistLevel中有两个字段
    - `forward`字段指向比自己得分高的某个结点(不一定是紧邻的), 并且, 若当前zskiplistLevel实例在level[]中的索引为X, 则其forward字段指向的结点, 其level[]字段的容量至少是X+1. 这也是上图中, 为什么forward指针总是画的水平的原因.
    - `span`字段代表forward字段指向的结点, 距离当前结点的距离. 紧邻的两个结点之间的距离定义为1.
### 6.7.3 为什么不用平衡树或者哈希表
- **为什么不是平衡树，先看下作者的回答**

https://news.ycombinator.com/item?id=1171423
```sh
There are a few reasons:

They are not very memory intensive. It's up to you basically. Changing parameters about the probability of a node to have a given number of levels will make then less memory intensive than btrees.
A sorted set is often target of many ZRANGE or ZREVRANGE operations, that is, traversing the skip list as a linked list. With this operation the cache locality of skip lists is at least as good as with other kind of balanced trees.

They are simpler to implement, debug, and so forth. For instance thanks to the skip list simplicity I received a patch (already in Redis master) with augmented skip lists implementing ZRANK in O(log(N)). It required little changes to the code.

About the Append Only durability & speed, I don't think it is a good idea to optimize Redis at cost of more code and more complexity for a use case that IMHO should be rare for the Redis target (fsync() at every command). Almost no one is using this feature even with ACID SQL databases, as the performance hint is big anyway.
About threads: our experience shows that Redis is mostly I/O bound. I'm using threads to serve things from Virtual Memory. The long term solution to exploit all the cores, assuming your link is so fast that you can saturate a single core, is running multiple instances of Redis (no locks, almost fully scalable linearly with number of cores), and using the "Redis Cluster" solution that I plan to develop in the future.
```
简而言之就是实现简单且达到了类似效果。

- **skiplist与平衡树、哈希表的比较**

来源于：https://www.jianshu.com/p/8ac45fd01548

skiplist和各种平衡树（如AVL、红黑树等）的元素是有序排列的，而哈希表不是有序的。因此，在哈希表上只能做单个key的查找，不适宜做范围查找。所谓范围查找，指的是查找那些大小在指定的两个值之间的所有节点。

在做范围查找的时候，平衡树比skiplist操作要复杂。在平衡树上，我们找到指定范围的小值之后，还需要以中序遍历的顺序继续寻找其它不超过大值的节点。如果不对平衡树进行一定的改造，这里的中序遍历并不容易实现。而在skiplist上进行范围查找就非常简单，只需要在找到小值之后，对第1层链表进行若干步的遍历就可以实现。

平衡树的插入和删除操作可能引发子树的调整，逻辑复杂，而skiplist的插入和删除只需要修改相邻节点的指针，操作简单又快速。

从内存占用上来说，skiplist比平衡树更灵活一些。一般来说，平衡树每个节点包含2个指针（分别指向左右子树），而skiplist每个节点包含的指针数目平均为1/(1-p)，具体取决于参数p的大小。如果像Redis里的实现一样，取p=1/4，那么平均每个节点包含1.33个指针，比平衡树更有优势。

查找单个key，skiplist和平衡树的时间复杂度都为O(log n)，大体相当；而哈希表在保持较低的哈希值冲突概率的前提下，查找时间复杂度接近O(1)，性能更高一些。所以我们平常使用的各种Map或dictionary结构，大都是基于哈希表实现的。

从算法实现难度上来比较，skiplist比平衡树要简单得多。
# 七、Redis进阶 - 数据结构：redis对象与编码(底层结构)对应关系详解
## 7.1 redis对象与编码(底层结构)对应关系引入
在对**对象机制详解** 和 **底层数据结构** 有了初步认识之后，我们便可以继续理解它们是怎么对应的：

![31.db-redis-object-2-4.png](../../assets/images/06-中间件/redis/31.db-redis-object-2-4.png)

## 7.2 字符串对象
> 字符串是Redis最基本的数据类型，不仅所有key都是字符串类型，其它几种数据类型构成的元素也是字符串。注意字符串的长度不能超过512M。

- **编码**

字符串对象的编码可以是int，raw或者embstr。

- `int 编码`：保存的是可以用 long 类型表示的整数值。
- `embstr 编码`：保存长度小于44字节的字符串（redis3.2版本之前是39字节，之后是44字节）。
- `raw 编码`：保存长度大于44字节的字符串（redis3.2版本之前是39字节，之后是44字节）。

![32.db-redis-x-object-4.png](../../assets/images/06-中间件/redis/32.db-redis-x-object-4.png)

由上可以看出，int 编码是用来保存整数值，而embstr是用来保存短字符串，raw编码是用来保存长字符串。

- **内存布局**

字符串对象支持三种编码方式: RAW, INT, EMBSTR, 三种方式的内存布局分别如下:

![33.db-redis-ds-x-21.png](../../assets/images/06-中间件/redis/33.db-redis-ds-x-21.png)

- **raw 和 embstr 的区别**

其实 embstr 编码是专门用来保存短字符串的一种优化编码，raw 和 embstr 的区别：

embstr与raw都使用redisObject和sds保存数据，区别在于，embstr的使用只分配一次内存空间（因此redisObject和sds是连续的），而raw需要分配两次内存空间（分别为redisObject和sds分配空间）。因此与raw相比，embstr的好处在于创建时少分配一次空间，删除时少释放一次空间，以及对象的所有数据连在一起，寻找方便。而embstr的坏处也很明显，如果字符串的长度增加需要重新分配内存时，整个redisObject和sds都需要重新分配空间，因此redis中的embstr实现为只读。

ps：**Redis中对于浮点数类型也是作为字符串保存的，在需要的时候再将其转换成浮点数类型。**

- **编码的转换**

当 int 编码保存的值不再是整数，或大小超过了long的范围时，自动转化为raw。

对于 embstr 编码，由于 Redis 没有对其编写任何的修改程序（embstr 是只读的），在对embstr对象进行修改时，都会先转化为raw再进行修改，因此，只要是修改embstr对象，修改后的对象一定是raw的，无论是否达到了44个字节。

## 7.3 列表对象
> list 列表，它是简单的字符串列表，按照插入顺序排序，你可以添加一个元素到列表的头部（左边）或者尾部（右边），它的底层实际上是个链表结构。

- **编码**

列表对象的编码是quicklist。 (之前版本中有linked和ziplist这两种编码。进一步的, 目前Redis定义的10个对象编码方式宏名中, 有两个被完全闲置了, 分别是: `OBJ_ENCODING_ZIPMAP`与`OBJ_ENCODING_LINKEDLIST`。 从Redis的演进历史上来看, 前者是后续可能会得到支持的编码值（代码还在）, 后者则应该是被彻底淘汰了)

- **内存布局**

列表对象的内存布局如下图所示:

![34.db-redis-ds-x-22.png](../../assets/images/06-中间件/redis/34.db-redis-ds-x-22.png)

## 7.4 哈希对象
> 哈希对象的键是一个字符串类型，值是一个键值对集合。

- **编码**

哈希对象的编码可以是 ziplist 或者 hashtable；对应的底层实现有两种, 一种是ziplist, 一种是dict。

两种编码**内存布局**分别如下:

![35.db-redis-ds-x-23.png](../../assets/images/06-中间件/redis/35.db-redis-ds-x-23.png)

上图中不严谨的地方有:

1. ziplist中每个entry, 除了键与值本身的二进制数据, 还包括其它字段, 图中没有画出来
2. dict底层可能持有两个dictht实例
3. 没有画出dict的哈希冲突

需要注意的是: 当采用HT编码, 即使用dict作为哈希对象的底层数据结构时, 键与值均是以sds的形式存储的.

- **举例说明**

当使用ziplist，也就是压缩列表作为底层实现时，新增的键值对是保存到压缩列表的表尾。比如执行以下命令：
```sh
hset profile name "Tom"
hset profile age 25
hset profile career "Programmer"
```
如果使用ziplist，profile 存储如下：

![36.db-redis-x-object-9.png](../../assets/images/06-中间件/redis/36.db-redis-x-object-9.png)

当使用 hashtable 编码时，上面命令存储如下：

![37.db-redis-x-object-10.png](../../assets/images/06-中间件/redis/37.db-redis-x-object-10.png)

hashtable 编码的哈希表对象底层使用字典数据结构，哈希对象中的每个键值对都使用一个字典键值对。

在前面介绍压缩列表时，我们介绍过压缩列表是Redis为了节省内存而开发的，是由一系列特殊编码的连续内存块组成的顺序型数据结构，相对于字典数据结构，压缩列表用于元素个数少、元素长度小的场景。其优势在于集中存储，节省空间。

- **编码转换**

和上面列表对象使用 ziplist 编码一样，当同时满足下面两个条件时，使用ziplist（压缩列表）编码：

1、列表保存元素个数小于512个

2、每个元素长度小于64字节

不能满足这两个条件的时候使用 hashtable 编码。以上两个条件也可以通过Redis配置文件zset-max-ziplist-entries 选项和 zset-max-ziplist-value 进行修改。

## 7.5 集合对象
> 集合对象 set 是 string 类型（整数也会转换成string类型进行存储）的无序集合。注意集合和列表的区别：集合中的元素是无序的，因此不能通过索引来操作元素；集合中的元素不能有重复。

- **编码**

集合对象的编码可以是 intset 或者 hashtable; 底层实现有两种, 分别是intset和dict。 显然当使用intset作为底层实现的数据结构时, 集合中存储的只能是数值数据, 且必须是整数; 而当使用dict作为集合对象的底层实现时, 是将数据全部存储于dict的键中, 值字段闲置不用.

集合对象的内存布局如下图所示:

![38.db-redis-ds-x-24.png](../../assets/images/06-中间件/redis/38.db-redis-ds-x-24.png)

- **举例说明**
```sh
SADD numbers 1 3 5
```

![39.db-redis-x-object-11.png](../../assets/images/06-中间件/redis/39.db-redis-x-object-11.png)

```sh
SADD Dfruits "apple" "banana" "cherry"
```

![40.db-redis-x-object-12.png](../../assets/images/06-中间件/redis/40.db-redis-x-object-12.png)

- **编码转换**

当集合同时满足以下两个条件时，使用 intset 编码：

1、集合对象中所有元素都是整数

2、集合对象所有元素数量不超过512

不能满足这两个条件的就使用 hashtable 编码。第二个条件可以通过配置文件的 set-max-intset-entries 进行配置。

## 7.6 有序集合对象
> 和上面的集合对象相比，有序集合对象是有序的。与列表使用索引下标作为排序依据不同，有序集合为每个元素设置一个分数（score）作为排序依据。

- **编码**

有序集合的底层实现依然有两种, 一种是使用ziplist作为底层实现, 另外一种比较特殊, 底层使用了两种数据结构: dict与skiplist. 前者对应的编码值宏为ZIPLIST, 后者对应的编码值宏为SKIPLIST

使用ziplist来实现在序集合很容易理解, 只需要在ziplist这个数据结构的基础上做好排序与去重就可以了. 使用zskiplist来实现有序集合也很容易理解, Redis中实现的这个跳跃表似乎天然就是为了实现有序集合对象而实现的, 那么为什么还要辅助一个dict实例呢? 我们先看来有序集合对象在这两种编码方式下的内存布局, 然后再做解释:

首先是编码为ZIPLIST时, 有序集合的内存布局如下:

![41.db-redis-ds-x-25.png](../../assets/images/06-中间件/redis/41.db-redis-ds-x-25.png)

然后是编码为SKIPLIST时, 有序集合的内存布局如下:

![42.db-redis-ds-x-26.png](../../assets/images/06-中间件/redis/42.db-redis-ds-x-26.png)

说明：其实有序集合单独使用字典或跳跃表其中一种数据结构都可以实现，但是这里使用两种数据结构组合起来，原因是假如我们单独使用 字典，虽然能以 O(1) 的时间复杂度查找成员的分值，但是因为字典是以无序的方式来保存集合元素，所以每次进行范围操作的时候都要进行排序；假如我们单独使用跳跃表来实现，虽然能执行范围操作，但是查找操作有 O(1)的复杂度变为了O(logN)。因此Redis使用了两种数据结构来共同实现有序集合。

- **举例说明**
```sh
ZADD price 8.5 apple 5.0 banana 6.0 cherry
```

![43.db-redis-x-object-13.png](../../assets/images/06-中间件/redis/43.db-redis-x-object-13.png)

![44.db-redis-x-object-14.png](../../assets/images/06-中间件/redis/44.db-redis-x-object-14.png)

- **编码转换**

当有序集合对象同时满足以下两个条件时，对象使用 ziplist 编码：

1、保存的元素数量小于128；

2、保存的所有元素长度都小于64字节。

不能满足上面两个条件的使用 skiplist 编码。以上两个条件也可以通过Redis配置文件`zset-max-ziplist-entries` 选项和 `zset-max-ziplist-value` 进行修改。
# 八、Redis进阶 - 持久化：RDB和AOF机制详解
> 为了防止数据丢失以及服务重启时能够恢复数据，Redis支持数据的持久化，主要分为两种方式，分别是RDB和AOF; 当然实际场景下还会使用这两种的混合模式。

## 8.1 Redis持久化简介
> 从两个点，我们来了解下Redis持久化

### 8.1.1 为什么需要持久化？
Redis是个基于内存的数据库。那服务一旦宕机，内存中的数据将全部丢失。通常的解决方案是从后端数据库恢复这些数据，但后端数据库有性能瓶颈，如果是大数据量的恢复，1、会对数据库带来巨大的压力，2、数据库的性能不如Redis。导致程序响应慢。所以对Redis来说，实现数据的持久化，避免从后端数据库中恢复数据，是至关重要的。

### 8.1.2 Redis持久化有哪些方式呢？为什么我们需要重点学RDB和AOF？
从严格意义上说，Redis服务提供四种持久化存储方案：`RDB、AOF、虚拟内存（VM）和　DISKSTORE`。虚拟内存（VM）方式，从Redis Version 2.4开始就被官方明确表示不再建议使用，Version 3.2版本中更找不到关于虚拟内存（VM）的任何配置范例，Redis的主要作者Salvatore Sanfilippo还专门写了一篇论文，来反思Redis对虚拟内存（VM）存储技术的支持问题。

至于DISKSTORE方式，是从Redis Version 2.8版本开始提出的一个存储设想，到目前为止Redis官方也没有在任何stable版本中明确建议使用这用方式。在Version 3.2版本中同样找不到对于这种存储方式的明确支持。从网络上能够收集到的各种资料来看，DISKSTORE方式和RDB方式还有着一些千丝万缕的联系，不过各位读者也知道，除了官方文档以外网络资料很多就是大抄。

最关键的是目前官方文档上能够看到的Redis对持久化存储的支持明确的就只有两种方案（https://redis.io/topics/persistence）：RDB和AOF。所以本文也只会具体介绍这两种持久化存储方案的工作特定和配置要点。

## 8.2 RDB 持久化
> RDB 就是 Redis DataBase 的缩写，中文名为快照/内存快照，RDB持久化是把当前进程数据生成快照保存到磁盘上的过程，由于是某一时刻的快照，那么快照中的值要早于或者等于内存中的值。

### 8.2.1 触发方式
> 触发rdb持久化的方式有2种，分别是**手动触发**和**自动触发**。

#### 8.2.1.1 手动触发
> 手动触发分别对应save和bgsave命令

- **save命令**：阻塞当前Redis服务器，直到RDB过程完成为止，对于内存 比较大的实例会造成长时间阻塞，线上环境不建议使用

- **bgsave命令**：Redis进程执行fork操作创建子进程，RDB持久化过程由子 进程负责，完成后自动结束。阻塞只发生在fork阶段，一般时间很短

bgsave流程图如下所示

![45.redis-x-rdb-1.png](../../assets/images/06-中间件/redis/45.redis-x-rdb-1.png)

具体流程如下：

- redis客户端执行bgsave命令或者自动触发bgsave命令；
- 主进程判断当前是否已经存在正在执行的子进程，如果存在，那么主进程直接返回；
- 如果不存在正在执行的子进程，那么就fork一个新的子进程进行持久化数据，fork过程是阻塞的，fork操作完成后主进程即可执行其他操作；
- 子进程先将数据写入到临时的rdb文件中，待快照数据写入完成后再原子替换旧的rdb文件；
- 同时发送信号给主进程，通知主进程rdb持久化完成，主进程更新相关的统计信息（info Persitence下的rdb_*相关选项）。
#### 8.2.1.2 自动触发
> 在以下4种情况时会自动触发

- redis.conf中配置`save m n`，即在m秒内有n次修改时，自动触发bgsave生成rdb文件；

- 主从复制时，从节点要从主节点进行全量复制时也会触发bgsave操作，生成当时的快照发送到从节点；

- 执行debug reload命令重新加载redis时也会触发bgsave操作；

- 默认情况下执行shutdown命令时，如果没有开启aof持久化，那么也会触发bgsave操作；

### 8.2.2 redis.conf中配置RDB
**快照周期**：内存快照虽然可以通过技术人员手动执行SAVE或BGSAVE命令来进行，但生产环境下多数情况都会设置其周期性执行条件。

- **Redis中默认的周期新设置**
```sh
# 周期性执行条件的设置格式为
save <seconds> <changes>

# 默认的设置为：
save 900 1
save 300 10
save 60 10000

# 以下设置方式为关闭RDB快照功能
save ""
```
以上三项默认信息设置代表的意义是：

- 如果900秒内有1条Key信息发生变化，则进行快照；

- 如果300秒内有10条Key信息发生变化，则进行快照；

- 如果60秒内有10000条Key信息发生变化，则进行快照。读者可以按照这个规则，根据自己的实际请求压力进行设置调整。

- 其它相关配置
```sh
# 文件名称
dbfilename dump.rdb

# 文件保存路径
dir /home/work/app/redis/data/

# 如果持久化出错，主进程是否停止写入
stop-writes-on-bgsave-error yes

# 是否压缩
rdbcompression yes

# 导入时是否检查
rdbchecksum yes
```
`dbfilename`：RDB文件在磁盘上的名称。

`dir`：RDB文件的存储路径。默认设置为“./”，也就是Redis服务的主目录。

`stop-writes-on-bgsave-error`：上文提到的在快照进行过程中，主进程照样可以接受客户端的任何写操作的特性，是指在快照操作正常的情况下。如果快照操作出现异常（例如操作系统用户权限不够、磁盘空间写满等等）时，Redis就会禁止写操作。这个特性的主要目的是使运维人员在第一时间就发现Redis的运行错误，并进行解决。一些特定的场景下，您可能需要对这个特性进行配置，这时就可以调整这个参数项。该参数项默认情况下值为yes，如果要关闭这个特性，指定即使出现快照错误Redis一样允许写操作，则可以将该值更改为no。

`rdbcompression`：该属性将在字符串类型的数据被快照到磁盘文件时，启用LZF压缩算法。Redis官方的建议是请保持该选项设置为yes，因为“it’s almost always a win”。

`rdbchecksum`：从RDB快照功能的version 5 版本开始，一个64位的CRC冗余校验编码会被放置在RDB文件的末尾，以便对整个RDB文件的完整性进行验证。这个功能大概会多损失10%左右的性能，但获得了更高的数据可靠性。所以如果您的Redis服务需要追求极致的性能，就可以将这个选项设置为no。

### 8.2.3 RDB 更深入理解
> 我们通过几个实战问题来深入理解RDB

- **由于生产环境中我们为Redis开辟的内存区域都比较大（例如6GB），那么将内存中的数据同步到硬盘的过程可能就会持续比较长的时间，而实际情况是这段时间Redis服务一般都会收到数据写操作请求。那么如何保证数据一致性呢？**

RDB中的核心思路是Copy-on-Write，来保证在进行快照操作的这段时间，需要压缩写入磁盘上的数据在内存中不会发生变化。在正常的快照操作中，一方面Redis主进程会fork一个新的快照进程专门来做这个事情，这样保证了Redis服务不会停止对客户端包括写请求在内的任何响应。另一方面这段时间发生的数据变化会以副本的方式存放在另一个新的内存区域，待快照操作结束后才会同步到原来的内存区域。

举个例子：如果主线程对这些数据也都是读操作（例如图中的键值对 A），那么，主线程和 bgsave 子进程相互不影响。但是，如果主线程要修改一块数据（例如图中的键值对 C），那么，这块数据就会被复制一份，生成该数据的副本。然后，bgsave 子进程会把这个副本数据写入 RDB 文件，而在这个过程中，主线程仍然可以直接修改原来的数据。

![46.redis-x-aof-42.jpg](../../assets/images/06-中间件/redis/46.redis-x-aof-42.jpg)

- **在进行快照操作的这段时间，如果发生服务崩溃怎么办？**

很简单，在没有将数据全部写入到磁盘前，这次快照操作都不算成功。如果出现了服务崩溃的情况，将以上一次完整的RDB快照文件作为恢复内存数据的参考。也就是说，在快照操作过程中不能影响上一次的备份数据。Redis服务会在磁盘上创建一个临时文件进行数据操作，待操作成功后才会用这个临时文件替换掉上一次的备份。

- **可以每秒做一次快照吗？**

对于快照来说，所谓“连拍”就是指连续地做快照。这样一来，快照的间隔时间变得很短，即使某一时刻发生宕机了，因为上一时刻快照刚执行，丢失的数据也不会太多。但是，这其中的快照间隔时间就很关键了。

如下图所示，我们先在 T0 时刻做了一次快照，然后又在 T0+t 时刻做了一次快照，在这期间，数据块 5 和 9 被修改了。如果在 t 这段时间内，机器宕机了，那么，只能按照 T0 时刻的快照进行恢复。此时，数据块 5 和 9 的修改值因为没有快照记录，就无法恢复了。 　　 

![47.redis-x-rdb-2.jpg](../../assets/images/06-中间件/redis/47.redis-x-rdb-2.jpg)

所以，要想尽可能恢复数据，t 值就要尽可能小，t 越小，就越像“连拍”。那么，t 值可以小到什么程度呢，比如说是不是可以每秒做一次快照？毕竟，每次快照都是由 bgsave 子进程在后台执行，也不会阻塞主线程。

这种想法其实是错误的。虽然 bgsave 执行时不阻塞主线程，但是，**如果频繁地执行全量快照，也会带来两方面的开销：**

- 一方面，频繁将全量数据写入磁盘，会给磁盘带来很大压力，多个快照竞争有限的磁盘带宽，前一个快照还没有做完，后一个又开始做了，容易造成恶性循环。
- 另一方面，bgsave 子进程需要通过 fork 操作从主线程创建出来。虽然，子进程在创建后不会再阻塞主线程，但是，fork 这个创建过程本身会阻塞主线程，而且主线程的内存越大，阻塞时间越长。如果频繁 fork 出 bgsave 子进程，这就会频繁**阻塞主线程**了。

那么，有什么其他好方法吗？此时，我们可以做增量快照，就是指做了一次全量快照后，后续的快照只对修改的数据进行快照记录，这样可以避免每次全量快照的开销。这个比较好理解。

但是它需要我们使用额外的元数据信息去记录哪些数据被修改了，这会带来额外的空间开销问题。那么，还有什么方法既能利用 RDB 的快速恢复，又能以较小的开销做到尽量少丢数据呢？且看后文中4.0版本中引入的RDB和AOF的混合方式。

### 8.2.4 RDB优缺点
- **优点**
  - RDB文件是某个时间节点的快照，默认使用LZF算法进行压缩，压缩后的文件体积远远小于内存大小，适用于备份、全量复制等场景；
  - Redis加载RDB文件恢复数据要远远快于AOF方式；
- **缺点**
  - RDB方式实时性不够，无法做到秒级的持久化；
  - 每次调用bgsave都需要fork子进程，fork子进程属于重量级操作，频繁执行成本较高；
  - RDB文件是二进制的，没有可读性，AOF文件在了解其结构的情况下可以手动修改或者补全；
  - 版本兼容RDB文件问题；

针对RDB不适合实时持久化的问题，Redis提供了AOF持久化方式来解决

























































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































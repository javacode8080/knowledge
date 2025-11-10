> 最后更新：2025-11-10 | [返回主目录](../README.md)

# 一、概述
> 首先， 从Spring框架的整体架构和组成对整体框架有个认知。
- Spring基础 - Spring和Spring框架组成
  - Spring是什么？它是怎么诞生的？有哪些主要的组件和核心功能呢? 通过这几个问题帮助你构筑Spring和Spring Framework的整体认知。

![1.spring-framework-introduce-8.png](../../assets/images/04-主流框架/spring/1.spring-framework-introduce-8.png)
> 其次，通过案例引出Spring的核心（IoC和AOP），同时对IoC和AOP进行案例使用分析。
- Spring基础 - Spring简单例子引入Spring的核心
  - 上文中我们简单介绍了Spring和Spring Framework的组件，那么这些Spring Framework组件是如何配合工作的呢？本文主要承接上文，向你展示Spring Framework组件的典型应用场景和基于这个场景设计出的简单案例，并以此引出Spring的核心要点，比如IOC和AOP等；在此基础上还引入了不同的配置方式， 如XML，Java配置和注解方式的差异。

![2.spring-framework-helloworld-2.png](../../assets/images/04-主流框架/spring/2.spring-framework-helloworld-2.png)
- Spring基础 - Spring核心之控制反转(IOC)
  - 在Spring基础 - Spring简单例子引入Spring的核心中向你展示了IoC的基础含义，同时以此发散了一些IoC相关知识点; 本节将在此基础上进一步解读IOC的含义以及IOC的使用方式

![3.spring-framework-ioc-2.png](../../assets/images/04-主流框架/spring/3.spring-framework-ioc-2.png)
- Spring基础 - Spring核心之面向切面编程(AOP)
  - 在Spring基础 - Spring简单例子引入Spring的核心中向你展示了AOP的基础含义，同时以此发散了一些AOP相关知识点; 本节将在此基础上进一步解读AOP的含义以及AOP的使用方式。

![4.spring-framework-aop-2.png](../../assets/images/04-主流框架/spring/4.spring-framework-aop-2.png)
> 基于Spring框架和IOC，AOP的基础，为构建上层web应用，需要进一步学习SpringMVC。
- Spring基础 - SpringMVC请求流程和案例
  - 前文我们介绍了Spring框架和Spring框架中最为重要的两个技术点（IOC和AOP），那我们如何更好的构建上层的应用呢（比如web 应用），这便是SpringMVC；Spring MVC是Spring在Spring Container Core和AOP等技术基础上，遵循上述Web MVC的规范推出的web开发框架，目的是为了简化Java栈的web开发。 本文主要介绍SpringMVC的请求流程和基础案例的编写和运行。

![5.spring-springframework-mvc-5.png](../../assets/images/04-主流框架/spring/5.spring-springframework-mvc-5.png)
> Spring进阶 - IoC，AOP以及SpringMVC的源码分析
- Spring进阶 - Spring IOC实现原理详解之IOC体系结构设计
  - 在对IoC有了初步的认知后，我们开始对IOC的实现原理进行深入理解。本文将帮助你站在设计者的角度去看IOC最顶层的结构设计

![6.spring-framework-ioc-source-71.png](../../assets/images/04-主流框架/spring/6.spring-framework-ioc-source-71.png)
- Spring进阶 - Spring IOC实现原理详解之IOC初始化流程
  - 上文，我们看了IOC设计要点和设计结构；紧接着这篇，我们可以看下源码的实现了：Spring如何实现将资源配置（以xml配置为例）通过加载，解析，生成BeanDefination并注册到IoC容器中的

![7.spring-framework-ioc-source-8.png](../../assets/images/04-主流框架/spring/7.spring-framework-ioc-source-8.png)
- Spring进阶 - Spring IOC实现原理详解之Bean实例化(生命周期,循环依赖等)
  - 上文，我们看了IOC设计要点和设计结构；以及Spring如何实现将资源配置（以xml配置为例）通过加载，解析，生成BeanDefination并注册到IoC容器中的；容器中存放的是Bean的定义即BeanDefinition放到beanDefinitionMap中，本质上是一个`ConcurrentHashMap<String, Object>`；并且BeanDefinition接口中包含了这个类的Class信息以及是否是单例等。那么如何从BeanDefinition中实例化Bean对象呢，这是本文主要研究的内容？
![8.spring-framework-ioc-source-102.png](../../assets/images/04-主流框架/spring/8.spring-framework-ioc-source-102.png)
- Spring进阶 - Spring AOP实现原理详解之切面实现
  - 前文，我们分析了Spring IOC的初始化过程和Bean的生命周期等，而Spring AOP也是基于IOC的Bean加载来实现的。本文主要介绍Spring AOP原理解析的切面实现过程(将切面类的所有切面方法根据使用的注解生成对应Advice，并将Advice连同切入点匹配器和切面类等信息一并封装到Advisor，为后续交给代理增强实现做准备的过程)。

![9.spring-springframework-aop-4.png](../../assets/images/04-主流框架/spring/9.spring-springframework-aop-4.png)
- Spring进阶 - Spring AOP实现原理详解之AOP代理上文
  - 我们介绍了Spring AOP原理解析的切面实现过程(将切面类的所有切面方法根据使用的注解生成对应Advice，并将Advice连同切入点匹配器和切面类等信息一并封装到Advisor)。本文在此基础上继续介绍，代理（cglib代理和JDK代理）的实现过程。

![10.spring-springframework-aop-51.png](../../assets/images/04-主流框架/spring/10.spring-springframework-aop-51.png)
- Spring进阶 - Spring AOP实现原理详解之Cglib代理实现
  - 我们在前文中已经介绍了SpringAOP的切面实现和创建动态代理的过程，那么动态代理是如何工作的呢？本文主要介绍Cglib动态代理的案例和SpringAOP实现的原理。

![11.spring-springframework-aop-63.png](../../assets/images/04-主流框架/spring/11.spring-springframework-aop-63.png)

- Spring进阶 - Spring AOP实现原理详解之JDK代理实现
  - 上文我们学习了SpringAOP Cglib动态代理的实现，本文主要是SpringAOP JDK动态代理的案例和实现部分。

![12.spring-springframework-aop-71.png](../../assets/images/04-主流框架/spring/12.spring-springframework-aop-71.png)
- Spring进阶 - SpringMVC实现原理之DispatcherServlet初始化的过程
  - 前文我们有了IOC的源码基础以及SpringMVC的基础，我们便可以进一步深入理解SpringMVC主要实现原理，包含DispatcherServlet的初始化过程和DispatcherServlet处理请求的过程的源码解析。本文是第一篇：DispatcherServlet的初始化过程的源码解析。

![13.spring-springframework-mvc-23.png](../../assets/images/04-主流框架/spring/13.spring-springframework-mvc-23.png)
- Spring进阶 - SpringMVC实现原理之DispatcherServlet处理请求的过程
  - 前文我们有了IOC的源码基础以及SpringMVC的基础，我们便可以进一步深入理解SpringMVC主要实现原理，包含DispatcherServlet的初始化过程和DispatcherServlet处理请求的过程的源码解析。本文是第二篇：DispatcherServlet处理请求的过程的源码解析。

![14.spring-springframework-mvc-30.png](../../assets/images/04-主流框架/spring/14.spring-springframework-mvc-30.png)
# 二、Spring基础 - Spring和Spring框架组成
## 2.1 什么是Spring?
> 首先，Spring是什么？它是怎么诞生的？它的诞生是为了解决什么问题？
### 2.1.1 Spring的起源
> 百度百科中关于Spring的起源介绍如下：

要谈Spring的历史，就要先谈J2EE。J2EE应用程序的广泛实现是在1999年和2000年开始的，它的出现带来了诸如事务管理之类的核心中间层概念的标准化，但是在实践中并没有获得绝对的成功，因为开发效率，开发难度和实际的性能都令人失望。

曾经使用过**EJB开发JAVA EE应用**的人，一定知道，在EJB开始的学习和应用非常的艰苦，很多东西都不能一下子就很容易的理解。EJB要严格地实现各种不同类型的接口，类似的或者重复的代码大量存在。而配置也是复杂和单调，同样使用JNDI进行对象查找的代码也是单调而枯燥。虽然有一些开发工作随着xdoclet的出现，而有所缓解，但是学习EJB的高昂代价，和极低的开发效率，极高的资源消耗，都造成了EJB的使用困难。而Spring出现的初衷就是为了解决类似的这些问题。

**Spring的一个最大的目的就是使JAVA EE开发更加容易**。同时，Spring之所以与Struts、Hibernate等单层框架不同，是因为Spring致力于提供一个以统一的、高效的方式构造整个应用，并且可以将单层框架以最佳的组合揉和在一起建立一个连贯的体系。可以说Spring是一个提供了更完善开发环境的一个框架，可以为POJO(Plain Ordinary Java Object)对象提供企业级的服务。

Spring的形成，最初来自Rod Jahnson所著的一本很有影响力的书籍<a href = 'https://item.jd.com/68619611892.html'>《Expert One-on-One J2EE Design and Development》</a>，就是在这本书中第一次出现了Spring的一些核心思想，该书出版于2002年。
### 2.1.2 Spring的特性和优势
> Spring Framework有哪些特性，用了这个框架对开发而言有什么好处呢？

从Spring 框架的**特性**来看：
- 非侵入式：基于Spring开发的应用中的对象可以不依赖于Spring的API
- 控制反转：IOC——Inversion of Control，指的是将对象的创建权交给 Spring 去创建。使用 Spring 之前，对象的创建都是由我们自己在代码中new创建。而使用 Spring 之后。对象的创建都是给了 Spring 框架。
- 依赖注入：DI——Dependency Injection，是指依赖的对象不需要手动调用 setXX 方法去设置，而是通过配置赋值。
- 面向切面编程：Aspect Oriented Programming——AOP
- 容器：Spring 是一个容器，因为它包含并且管理应用对象的生命周期
- 组件化：Spring 实现了使用简单的组件配置组合成一个复杂的应用。在 Spring 中可以使用XML和Java注解组合这些对象。
- 一站式：在 IOC 和 AOP 的基础上可以整合各种企业应用的开源框架和优秀的第三方类库（实际上 Spring 自身也提供了表现层的 SpringMVC 和持久层的 Spring JDBC）

从使用Spring 框架的**好处**看：
- Spring 可以使开发人员使用 POJOs 开发企业级的应用程序。只使用 POJOs 的好处是你不需要一个 EJB 容器产品，比如一个应用程序服务器，但是你可以选择使用一个健壮的 servlet 容器，比如 Tomcat 或者一些商业产品。
- Spring 在一个单元模式中是有组织的。即使包和类的数量非常大，你只要担心你需要的，而其它的就可以忽略了。
- Spring 不会让你白费力气做重复工作，它真正的利用了一些现有的技术，像 ORM 框架、日志框架、JEE、Quartz 和 JDK 计时器，其他视图技术。
- 测试一个用 Spring 编写的应用程序很容易，因为环境相关的代码被移动到这个框架中。此外，通过使用 JavaBean-style POJOs，它在使用依赖注入注入测试数据时变得更容易。
- Spring 的 web 框架是一个设计良好的 web MVC 框架，它为比如 Structs 或者其他工程上的或者不怎么受欢迎的 web 框架提供了一个很好的供替代的选择。MVC 模式导致应用程序的不同方面(输入逻辑，业务逻辑和UI逻辑)分离，同时提供这些元素之间的松散耦合。模型(Model)封装了应用程序数据，通常它们将由 POJO 类组成。视图(View)负责渲染模型数据，一般来说它生成客户端浏览器可以解释 HTML 输出。控制器(Controller)负责处理用户请求并构建适当的模型，并将其传递给视图进行渲染。
- Spring 对 JavaEE 开发中非常难用的一些 API（JDBC、JavaMail、远程调用等），都提供了封装，使这些API应用难度大大降低。
- 轻量级的 IOC 容器往往是轻量级的，例如，特别是当与 EJB 容器相比的时候。这有利于在内存和 CPU 资源有限的计算机上开发和部署应用程序。
- Spring 提供了一致的事务管理接口，可向下扩展到（使用一个单一的数据库，例如）本地事务并扩展到全局事务（例如，使用 JTA）
### 2.1.3 Spring有哪些组件?
下图来自，官方文档 Spring-framework 5.0；需要注意的是，虽然这个图来源于Spring Framwork5.0 M4 版本，但是它依然是V4版本的图，比如Spring 5版本中的web模块已经去掉了Portlet模块，新增了WebFlux模块等。
![1.spring-framework-introduce-8.png](../../assets/images/04-主流框架/spring/1.spring-framework-introduce-8.png)

上图中包含了 Spring 框架的所有模块，这些模块可以满足一切企业级应用开发的需求，在开发过程中可以根据需求有选择性地使用所需要的模块。下面分别对这些模块的作用进行简单介绍（并且结合SpringFramework5.x源码模块帮助你对应好各模块关系）。
#### 2.1.3.1 Core Container（Spring的核心容器）

Spring 的核心容器是其他模块建立的基础，由 Beans 模块、Core 核心模块、Context 上下文模块和 SpEL 表达式语言模块组成，没有这些核心容器，也不可能有 AOP、Web 等上层的功能。具体介绍如下。

- `Beans 模块`：提供了框架的基础部分，包括控制反转和依赖注入。
- `Core 核心模块`：封装了 Spring 框架的底层部分，包括资源访问、类型转换及一些常用工具类。
- `Context 上下文模块`：建立在 Core 和 Beans 模块的基础之上，集成 Beans 模块功能并添加资源绑定、数据验证、国际化、Java EE 支持、容器生命周期、事件传播等。ApplicationContext 接口是上下文模块的焦点。
- `SpEL 模块`：提供了强大的表达式语言支持，支持访问和修改属性值，方法调用，支持访问及修改数组、容器和索引器，命名变量，支持算数和逻辑运算，支持从 Spring 容器获取 Bean，它也支持列表投影、选择和一般的列表聚合等。

对应的源码模块如下：
![15.spring-framework-introduce-9.png](../../assets/images/04-主流框架/spring/15.spring-framework-introduce-9.png)
#### 2.1.3.2 Data Access/Integration（数据访问／集成）
数据访问／集成层包括 JDBC、ORM、OXM、JMS 和 Transactions 模块，具体介绍如下。

- `JDBC 模块`：提供了一个 JDBC 的样例模板，使用这些模板能消除传统冗长的 JDBC 编码还有必须的事务控制，而且能享受到 Spring 管理事务的好处。
- `ORM 模块`：提供与流行的“对象-关系”映射框架无缝集成的 API，包括 JPA、JDO、Hibernate 和 MyBatis 等。而且还可以使用 Spring 事务管理，无需额外控制事务。
- `OXM 模块`：提供了一个支持 Object /XML 映射的抽象层实现，如 JAXB、Castor、XMLBeans、JiBX 和 XStream。将 Java 对象映射成 XML 数据，或者将XML 数据映射成 Java 对象。
- `JMS 模块`：指 Java 消息服务，提供一套 “消息生产者、消息消费者”模板用于更加简单的使用 JMS，JMS 用于用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。
- `Transactions 事务模块`：支持编程和声明式事务管理。

对应的源码模块如下：
![16.spring-framework-introduce-10.png](../../assets/images/04-主流框架/spring/16.spring-framework-introduce-10.png)
#### 2.1.3.3 Web模块
Spring 的 Web 层包括 Web、Servlet、WebSocket 和 Webflux 组件，具体介绍如下。
- `Web 模块`：提供了基本的 Web 开发集成特性，例如多文件上传功能、使用的 Servlet 监听器的 IOC 容器初始化以及 Web 应用上下文。
- `Servlet 模块`：提供了一个 Spring MVC Web 框架实现。Spring MVC 框架提供了基于注解的请求资源注入、更简单的数据绑定、数据验证等及一套非常易用的 JSP 标签，完全无缝与 Spring 其他技术协作。
- `WebSocket 模块`：提供了简单的接口，用户只要实现响应的接口就可以快速的搭建 WebSocket Server，从而实现双向通讯。
- `Webflux 模块`： Spring WebFlux 是 Spring Framework 5.x中引入的新的响应式web框架。与Spring MVC不同，它不需要Servlet API，是完全异步且非阻塞的，并且通过Reactor项目实现了Reactive Streams规范。Spring WebFlux 用于创建基于事件循环执行模型的完全异步且非阻塞的应用程序。

此外Spring4.x中还有Portlet 模块，在Spring 5.x中已经移除
- `Portlet 模块`：提供了在 Portlet 环境中使用 MVC 实现，类似 Web-Servlet 模块的功能。

对应的源码模块如下：
![17.spring-framework-introduce-11.png](../../assets/images/04-主流框架/spring/17.spring-framework-introduce-11.png)
#### 2.1.3.4 AOP、Aspects、Instrumentation和Messaging
在 Core Container 之上是 AOP、Aspects 等模块，具体介绍如下：
- `AOP 模块`：提供了面向切面编程实现，提供比如日志记录、权限控制、性能统计等通用功能和业务逻辑分离的技术，并且能动态的把这些功能添加到需要的代码中，这样各司其职，降低业务逻辑和通用功能的耦合。
- `Aspects 模块`：提供与 AspectJ 的集成，是一个功能强大且成熟的面向切面编程（AOP）框架。
- `Instrumentation 模块`：提供了类工具的支持和类加载器的实现，可以在特定的应用服务器中使用。
- `messaging 模块`：Spring 4.0 以后新增了消息（Spring-messaging）模块，该模块提供了对消息传递体系结构和协议的支持。
- `jcl 模块`： Spring 5.x中新增了日志框架集成的模块。

对应的源码模块如下：
![18.spring-framework-introduce-12.png](../../assets/images/04-主流框架/spring/18.spring-framework-introduce-12.png)
#### 2.1.3.5 Test模块
Test 模块：Spring 支持 Junit 和 TestNG 测试框架，而且还额外提供了一些基于 Spring 的测试功能，比如在测试 Web 框架时，模拟 Http 请求的功能。

包含Mock Objects, TestContext Framework, Spring MVC Test, WebTestClient。

对应的源码模块如下：
![19.spring-framework-introduce-13.png](../../assets/images/04-主流框架/spring/19.spring-framework-introduce-13.png)
## 2.2 为什么用Spring?
> 那么为什么用Spring呢？来看看<a href='https://spring.io/why-spring'>官网</a>对这个问题的回答
## 2.3 学习Spring时参考哪些资料呢？
### 2.3.1 Spring 的官方项目和教程
官方的项目和教程，地址在<a href='https://spring.io/projects/spring-framework'>这里</a>，在学习Spring时，一定要把它当做生态体系，而是不是一个简单的开发框架。
![20.spring-framework-introduce-4.png](../../assets/images/04-主流框架/spring/20.spring-framework-introduce-4.png)
### 2.3.2 Spring 的归档文档
官方提供了系统性的文档的FTP，你可以在<a href ='https://docs.spring.io/spring-framework/docs/'>这里</a>找到所有历史版本的PDF/HTML版本。
![21.spring-framework-introduce-1.png](../../assets/images/04-主流框架/spring/21.spring-framework-introduce-1.png)

可以看到很多系统性的文档，包括上面引用的图，
![22.spring-framework-introduce-3.png](../../assets/images/04-主流框架/spring/22.spring-framework-introduce-3.png)
![23.spring-framework-introduce-2.png](../../assets/images/04-主流框架/spring/23.spring-framework-introduce-2.png)
### 2.3.3 Spring 的官方Github
Spring官方的GitHub在<a href = 'https://github.com/spring-projects/spring-framework'>这里</a>，它包含着Spring-framework的源码，如果你感兴趣，可以从这里clone代码进行阅读。
![24.spring-framework-introduce-5.png](../../assets/images/04-主流框架/spring/24.spring-framework-introduce-5.png)
# 三 Spring基础 - Spring简单例子引入Spring要点
## 3.1 Spring框架如何应用
> 上文中，我们展示了Spring和Spring Framework的组件, 这里对于开发者来说有几个问题：
> 
> 首先，对于Spring进阶，直接去看IOC和AOP，存在一个断层，所以需要整体上构建对Spring框架认知上进一步深入，这样才能构建知识体系。
> 
> 其次，很多开发者入门都是从Spring Boot开始的，他对Spring整体框架底层，以及发展历史不是很了解； 特别是对于一些老旧项目维护和底层bug分析没有全局观。
> 
> 再者，Spring代表的是一种框架设计理念，需要全局上理解Spring Framework组件是如何配合工作的，需要理解它设计的初衷和未来趋势。

如下是官方在解释Spring框架的常用场景的图
![2.spring-framework-helloworld-2.png](../../assets/images/04-主流框架/spring/2.spring-framework-helloworld-2.png)

我加上一些注释后，是比较好理解的；引入这个图，重要的原因是为后面设计一个案例帮助你构建认知。

## 3.2 设计一个Spring的Hello World
> 结合上面的使用场景，设计一个查询用户的案例的两个需求，来看Spring框架帮我们简化了什么开发工作:
> 
> 查询用户数据 - 来看DAO+POJO-> Service 的初始化和装载。
> 
> 给所有Service的查询方法记录日志
- 创建一个Maven的Java项目
![25.spring-framework-helloworld-3.png](../../assets/images/04-主流框架/spring/25.spring-framework-helloworld-3.png)
- 引入Spring框架的POM依赖，以及查看这些依赖之间的关系
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>tech.pdai</groupId>
    <artifactId>001-spring-framework-demo-helloworld-xml</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <spring.version>5.3.9</spring.version>
        <aspectjweaver.version>1.9.6</aspectjweaver.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectjweaver.version}</version>
        </dependency>
    </dependencies>

</project>
```
![26.spring-framework-helloworld-4.png](../../assets/images/04-主流框架/spring/26.spring-framework-helloworld-4.png)
- POJO - User
```java
package tech.pdai.springframework.entity;

/**
 * @author pdai
 */
public class User {

    /**
     * user's name.
     */
    private String name;

    /**
     * user's age.
     */
    private int age;

    /**
     * init.
     *
     * @param name name
     * @param age  age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```
- DAO 获取 POJO， UserDaoServiceImpl (mock 数据)
```java
package tech.pdai.springframework.dao;

import java.util.Collections;
import java.util.List;

import tech.pdai.springframework.entity.User;

/**
 * @author pdai
 */
public class UserDaoImpl {

    /**
     * init.
     */
    public UserDaoImpl() {
    }

    /**
     * mocked to find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return Collections.singletonList(new User("pdai", 18));
    }
}
```
并增加daos.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="userDao" class="tech.pdai.springframework.dao.UserDaoImpl">
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>
    <!-- more bean definitions for data access objects go here -->
</beans>
```
- 业务层 UserServiceImpl（调用DAO层）
```java
package tech.pdai.springframework.service;

import java.util.List;

import tech.pdai.springframework.dao.UserDaoImpl;
import tech.pdai.springframework.entity.User;

/**
 * @author pdai
 */
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    private UserDaoImpl userDao;

    /**
     * init.
     */
    public UserServiceImpl() {
    }

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return this.userDao.findUserList();
    }

    /**
     * set dao.
     *
     * @param userDao user dao
     */
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
```
并增加services.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- services -->
    <bean id="userService" class="tech.pdai.springframework.service.UserServiceImpl">
        <property name="userDao" ref="userDao"/>
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>
    <!-- more bean definitions for services go here -->
</beans>
```
- 拦截所有service中的方法，并输出记录
```java
package tech.pdai.springframework.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author pdai
 */
@Aspect
public class LogAspect {

    /**
     * aspect for every methods under service package.
     */
    @Around("execution(* tech.pdai.springframework.service.*.*(..))")
    public Object businessService(ProceedingJoinPoint pjp) throws Throwable {
        // get attribute through annotation
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        System.out.println("execute method: " + method.getName());

        // continue to process
        return pjp.proceed();
    }

}
```
并增加aspects.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd
 http://www.springframework.org/schema/aop
 http://www.springframework.org/schema/aop/spring-aop.xsd
 http://www.springframework.org/schema/context
 http://www.springframework.org/schema/context/spring-context.xsd
">

    <context:component-scan base-package="tech.pdai.springframework" />

    <aop:aspectj-autoproxy/>

    <bean id="logAspect" class="tech.pdai.springframework.aspect.LogAspect">
        <!-- configure properties of aspect here as normal -->
    </bean>
    <!-- more bean definitions for data access objects go here -->
</beans>
```
- 组装App
```java
package tech.pdai.springframework;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tech.pdai.springframework.entity.User;
import tech.pdai.springframework.service.UserServiceImpl;

/**
 * @author pdai
 */
public class App {

    /**
     * main interfaces.
     *
     * @param args args
     */
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aspects.xml", "daos.xml", "services.xml");

        // retrieve configured instance
        UserServiceImpl service = context.getBean("userService", UserServiceImpl.class);

        // use configured instance
        List<User> userList = service.findUserList();

        // print info from beans
        userList.forEach(a -> System.out.println(a.getName() + "," + a.getAge()));
    }
}
```
- 整体结构和运行app
![27.spring-framework-helloworld-5.png](../../assets/images/04-主流框架/spring/27.spring-framework-helloworld-5.png)

## 3.3 这个例子体现了Spring的哪些核心要点
### 3.3.1 控制反转 - IOC
来看第一个需求：查询用户（service通过调用dao查询pojo），本质上如何创建User/Dao/Service等；
- 如果没有Spring框架，我们需要自己创建User/Dao/Service等，比如：
```java
UserDaoImpl userDao = new UserDaoImpl();
UserSericeImpl userService = new UserServiceImpl();
userService.setUserDao(userDao);
List<User> userList = userService.findUserList();
```
- 有了Spring框架，可以将原有Bean的创建工作转给框架, 需要用时从Bean的容器中获取即可，这样便简化了开发工作

Bean的创建和使用分离了。
```java
// create and configure beans
ApplicationContext context =
        new ClassPathXmlApplicationContext("aspects.xml", "daos.xml", "services.xml");

// retrieve configured instance
UserServiceImpl service = context.getBean("userService", UserServiceImpl.class);

// use configured instance
List<User> userList = service.findUserList();
```
![28.spring-framework-helloworld-6.png](../../assets/images/04-主流框架/spring/28.spring-framework-helloworld-6.png)

更进一步，你便能理解为何会有如下的知识点了：
- Spring框架管理这些Bean的创建工作，即由用户管理Bean转变为框架管理Bean，这个就叫**控制反转Inversion of Control (IoC)**
- Spring 框架托管创建的Bean放在哪里呢？ 这便是**IoC Container**;
- Spring 框架为了更好让用户配置Bean，必然会引入不同方式来配置Bean？ 这便是xml配置，Java配置，注解配置等
- 支持Spring 框架既然接管了Bean的生成，必然需要管理整个Bean的生命周期等；
- 应用程序代码从Ioc Container中获取依赖的Bean，注入到应用程序中，这个过程叫**依赖注入(Dependency Injection，DI)**； 所以说控制反转是通过依赖注入实现的，其实它们是同一个概念的不同角度描述。通俗来说就是**IoC是设计思想，DI是实现方式**
- 在依赖注入时，有哪些方式呢？这就是构造器方式，@Autowired, @Resource, @Qualifier... 同时Bean之间存在依赖（可能存在先后顺序问题，以及**循环依赖问题**等）
### 3.3.2 面向切面 - AOP
> 来看第二个需求：给Service所有方法调用**添加日志（调用方法时）**，本质上是解耦问题；
- 如果没有Spring框架，我们需要在每个service的方法中都添加记录日志的方法，比如：
```java
/**
* find user list.
*
* @return user list
*/
public List<User> findUserList() {
    System.out.println("execute method findUserList");
    return this.userDao.findUserList();
}
```
- 有了Spring框架，通过@Aspect注解 定义了切面，这个切面中定义了拦截所有service中的方法，并记录日志； 可以明显看到，框架将日志记录和业务需求的代码解耦了，不再是侵入式的了
```java
/**
* aspect for every methods under service package.
*/
@Around("execution(* tech.pdai.springframework.service.*.*(..))")
public Object businessService(ProceedingJoinPoint pjp) throws Throwable {
    // get attribute through annotation
    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
    System.out.println("execute method: " + method.getName());

    // continue to process
    return pjp.proceed();
}
```
更进一步，你便能理解为何会有如下的知识点了：
- Spring 框架通过定义切面, 通过拦截切点实现了不同业务模块的解耦，这个就叫**面向切面编程 - Aspect Oriented Programming (AOP)**
- 为什么@Aspect注解使用的是aspectj的jar包呢？这就引出了**Aspect4J和Spring AOP的历史渊源**，只有理解了Aspect4J和Spring的渊源才能理解有些注解上的兼容设计
- 如何支持更多拦截方式来实现解耦， 以满足更多场景需求呢？ 这就是@Around, @Pointcut... 等的设计
- 那么Spring框架又是如何实现AOP的呢？ 这就引入**代理技术，分静态代理和动态代理**，动态代理又包含**JDK代理和CGLIB代理**等
## 3.4 Spring框架设计如何逐步简化开发的
> 通过上述的框架介绍和例子，已经初步知道了Spring设计的两个大的要点：IOC和AOP；从框架的设计角度而言，更为重要的是简化开发，比如提供更为便捷的配置Bean的方式，直至0配置（即约定大于配置）。这里我将通过Spring历史版本的发展，和SpringBoot的推出等，来帮你理解Spring框架是如何逐步简化开发的
### 3.4.1 Java 配置方式改造
在前文的例子中， 通过xml配置方式实现的，这种方式实际上比较麻烦； 我通过Java配置进行改造：

- User，UserDaoImpl, UserServiceImpl，LogAspect不用改
- 将原通过.xml配置转换为Java配置
```java
package tech.pdai.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tech.pdai.springframework.aspect.LogAspect;
import tech.pdai.springframework.dao.UserDaoImpl;
import tech.pdai.springframework.service.UserServiceImpl;

/**
 * @author pdai
 */
@EnableAspectJAutoProxy
@Configuration
public class BeansConfig {

    /**
     * @return user dao
     */
    @Bean("userDao")
    public UserDaoImpl userDao() {
        return new UserDaoImpl();
    }

    /**
     * @return user service
     */
    @Bean("userService")
    public UserServiceImpl userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        return userService;
    }

    /**
     * @return log aspect
     */
    @Bean("logAspect")
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
```
- 在App中加载BeansConfig的配置
```java
package tech.pdai.springframework;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tech.pdai.springframework.config.BeansConfig;
import tech.pdai.springframework.entity.User;
import tech.pdai.springframework.service.UserServiceImpl;

/**
 * @author pdai
 */
public class App {

    /**
     * main interfaces.
     *
     * @param args args
     */
    public static void main(String[] args) {
        // create and configure beans
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        // retrieve configured instance
        UserServiceImpl service = context.getBean("userService", UserServiceImpl.class);

        // use configured instance
        List<User> userList = service.findUserList();

        // print info from beans
        userList.forEach(a -> System.out.println(a.getName() + "," + a.getAge()));
    }
}
```
- 整体结构和运行app
![29.spring-framework-helloworld-7.png](../../assets/images/04-主流框架/spring/29.spring-framework-helloworld-7.png)
### 3.4.2 注解配置方式改造
更进一步，Java 5开始提供注解支持，Spring 2.5 开始完全支持基于注解的配置并且也支持JSR250 注解。在Spring后续的版本发展倾向于通过注解和Java配置结合使用.
- BeanConfig 不再需要Java配置
```java
package tech.pdai.springframework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author pdai
 */
@Configuration
@EnableAspectJAutoProxy
public class BeansConfig {

}
```
- UserDaoImpl 增加了 @Repository注解
```java
/**
 * @author pdai
 */
@Repository
public class UserDaoImpl {

    /**
     * mocked to find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return Collections.singletonList(new User("pdai", 18));
    }
}
```
- UserServiceImpl 增加了@Service 注解，并通过@Autowired注入userDao.
```java
/**
 * @author pdai
 */
@Service
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    @Autowired
    private UserDaoImpl userDao;

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return userDao.findUserList();
    }

}
```
- 在App中扫描tech.pdai.springframework包
```java
package tech.pdai.springframework;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tech.pdai.springframework.entity.User;
import tech.pdai.springframework.service.UserServiceImpl;

/**
 * @author pdai
 */
public class App {

    /**
     * main interfaces.
     *
     * @param args args
     */
    public static void main(String[] args) {
        // create and configure beans
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "tech.pdai.springframework");

        // retrieve configured instance
        UserServiceImpl service = context.getBean(UserServiceImpl.class);

        // use configured instance
        List<User> userList = service.findUserList();

        // print info from beans
        userList.forEach(a -> System.out.println(a.getName() + "," + a.getAge()));
    }
}
```
- 整体结构和运行app
![30.spring-framework-helloworld-9.png](../../assets/images/04-主流框架/spring/30.spring-framework-helloworld-9.png)
### 3.4.3 SpringBoot托管配置
Springboot实际上通过约定大于配置的方式，使用xx-starter统一的对Bean进行默认初始化，用户只需要很少的配置就可以进行开发了。

这个因为很多开发者都是从SpringBoot开始着手开发的，所以这个比较好理解。我们需要的是将知识点都串联起来，构筑认知体系。
## 3.5 结合Spring历史版本和SpringBoot看发展
最后结合Spring历史版本总结下它的发展：（这样是不是能够帮助你在整体上构建了知识体系的认知了呢？）
![31.spring-framework-helloworld-8.png](../../assets/images/04-主流框架/spring/31.spring-framework-helloworld-8.png)
# 四、Spring基础 - Spring核心之控制反转(IOC)
## 4.1 引入
> 我们在Spring基础 - Spring简单例子引入Spring的核心中向你展示了IoC的基础含义，同时以此发散了一些IoC相关知识点。
- Spring框架管理这些Bean的创建工作，即由用户管理Bean转变为框架管理Bean，这个就叫**控制反转Inversion of Control (IoC)**
- Spring 框架托管创建的Bean放在哪里呢？ 这便是**IoC Container**;
- Spring 框架为了更好让用户配置Bean，必然会引入不同方式来配置Bean？ 这便是xml配置，Java配置，注解配置等
- 支持Spring 框架既然接管了Bean的生成，必然需要管理整个Bean的生命周期等；
- 应用程序代码从Ioc Container中获取依赖的Bean，注入到应用程序中，这个过程叫**依赖注入(Dependency Injection，DI)**； 所以说控制反转是通过依赖注入实现的，其实它们是同一个概念的不同角度描述。通俗来说就是**IoC是设计思想，DI是实现方式**
- 在依赖注入时，有哪些方式呢？这就是构造器方式，@Autowired, @Resource, @Qualifier... 同时Bean之间存在依赖（可能存在先后顺序问题，以及**循环依赖问题**等）

本节将在此基础上进一步解读IOC的含义以及IOC的使用方式；后续的文章还将深入IOC的实现原理：
- Spring进阶- Spring IOC实现原理详解之IOC体系结构设计
- Spring进阶- Spring IOC实现原理详解之IOC初始化流程
- Spring进阶- Spring IOC实现原理详解之Bean的注入和生命周期
## 4.2 如何理解IoC
如果你有精力看英文，首推 Martin Fowler大师的 <a href = 'https://www.martinfowler.com/articles/injection.html'>Inversion of Control Containers and the Dependency Injection pattern</a>；其次IoC作为一种设计思想，不要过度解读，而是应该简化理解，所以我这里也整合了 张开涛早前的博客<a href = 'https://www.iteye.com/blog/jinnianshilongnian-1413846'>IoC基础</a>并加入了自己的理解。
### 4.2.1 Spring Bean是什么
> IoC Container管理的是Spring Bean， 那么Spring Bean是什么呢？

Spring里面的bean就类似是定义的一个组件，而这个组件的作用就是实现某个功能的，这里所定义的bean就相当于给了你一个更为简便的方法来调用这个组件去实现你要完成的功能。
### 4.2.2 IoC是什么
> Ioc—Inversion of Control，即“控制反转”，**不是什么技术，而是一种设计思想**。在Java开发中，Ioc意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。

我们来深入分析一下：
- 谁控制谁，控制什么？

传统Java SE程序设计，我们直接在对象内部通过new进行创建对象，是程序主动去创建依赖对象；而IoC是有专门一个容器来创建这些对象，即由Ioc容器来控制对象的创建；谁控制谁？当然是IoC 容器控制了对象；控制什么？那就是主要控制了外部资源获取（不只是对象包括比如文件等）。
- 为何是反转，哪些方面反转了?

有反转就有正转，传统应用程序是由我们自己在对象中主动控制去直接获取依赖对象，也就是正转；而反转则是由容器来帮忙创建及注入依赖对象；为何是反转？因为由容器帮我们查找及注入依赖对象，对象只是被动的接受依赖对象，所以是反转；哪些方面反转了？依赖对象的获取被反转了。
- 用图例说明一下?

传统程序设计下，都是主动去创建相关对象然后再组合起来：
![32.spring-framework-ioc-1.png](../../assets/images/04-主流框架/spring/32.spring-framework-ioc-1.png)

当有了IoC/DI的容器后，在客户端类中不再主动去创建这些对象了，如图

![33.spring-framework-ioc-2.png](../../assets/images/04-主流框架/spring/33.spring-framework-ioc-2.png)
### 4.2.3 IoC能做什么
> **IoC 不是一种技术，只是一种思想**，一个重要的面向对象编程的法则，它能指导我们如何设计出松耦合、更优良的程序。

传统应用程序都是由我们在类内部主动创建依赖对象，从而导致类与类之间高耦合，难于测试；**有了IoC容器后，把创建和查找依赖对象的控制权交给了容器，由容器进行注入组合对象，所以对象与对象之间是 松散耦合，这样也方便测试，利于功能复用，更重要的是使得程序的整个体系结构变得非常灵活。**

其实IoC对编程带来的最大改变不是从代码上，而是从思想上，发生了“主从换位”的变化。应用程序原本是老大，要获取什么资源都是主动出击，但是在IoC/DI思想中，应用程序就变成被动的了，被动的等待IoC容器来创建并注入它所需要的资源了。

IoC很好的体现了面向对象设计法则之一——**好莱坞法则：“别找我们，我们找你”**；即由IoC容器帮对象找相应的依赖对象并注入，而不是由对象主动去找。

### 4.2.4 IoC和DI是什么关系
> 控制反转是通过依赖注入实现的，其实它们是同一个概念的不同角度描述。通俗来说就是**IoC是设计思想，DI是实现方式。**

DI—Dependency Injection，即依赖注入：组件之间依赖关系由容器在运行期决定，形象的说，即由容器动态的将某个依赖关系注入到组件之中。依赖注入的目的并非为软件系统带来更多功能，而是为了提升组件重用的频率，并为系统搭建一个灵活、可扩展的平台。通过依赖注入机制，我们只需要通过简单的配置，而无需任何代码就可指定目标需要的资源，完成自身的业务逻辑，而不需要关心具体的资源来自何处，由谁实现。

我们来深入分析一下：
- 谁依赖于谁？

当然是应用程序依赖于IoC容器；
- 为什么需要依赖？

应用程序需要IoC容器来提供对象需要的外部资源；
- 谁注入谁？

很明显是IoC容器注入应用程序某个对象，应用程序依赖的对象；
- 注入了什么？

就是注入某个对象所需要的外部资源（包括对象、资源、常量数据）。
- IoC和DI有什么关系呢？

其实它们是同一个概念的不同角度描述，由于控制反转概念比较含糊（可能只是理解为容器控制对象这一个层面，很难让人想到谁来维护对象关系），所以2004年大师级人物Martin Fowler又给出了一个新的名字：“依赖注入”，相对IoC 而言，“依赖注入”明确描述了“被注入对象依赖IoC容器配置依赖对象”。通俗来说就是IoC是设计思想，DI是实现方式。
## 4.3 Ioc 配置的三种方式
> 在Spring基础 - Spring简单例子引入Spring的核心已经给出了三种配置方式，这里再总结下；总体上目前的主流方式是 注解 + Java 配置。
### 4.3.1 xml 配置
顾名思义，就是将bean的信息配置.xml文件里，通过Spring加载文件为我们创建bean。这种方式出现很多早前的SSM项目中，将第三方类库或者一些配置工具类都以这种方式进行配置，主要原因是由于第三方类不支持Spring注解。
- 优点： 可以使用于任何场景，结构清晰，通俗易懂
- 缺点： 配置繁琐，不易维护，枯燥无味，扩展性差

**举例：**
1. 配置xx.xml文件
2. 声明命名空间和配置bean
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- services -->
    <bean id="userService" class="tech.pdai.springframework.service.UserServiceImpl">
        <property name="userDao" ref="userDao"/>
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>
    <!-- more bean definitions for services go here -->
</beans>
```
### 4.3.2 Java 配置
将类的创建交给我们配置的JavcConfig类来完成，Spring只负责维护和管理，采用纯Java创建方式。其本质上就是把在XML上的配置声明转移到Java配置类中
- 优点：适用于任何场景，配置方便，因为是纯Java代码，扩展性高，十分灵活
- 缺点：由于是采用Java类的方式，声明不明显，如果大量配置，可读性比较差

**举例：**
1. 创建一个配置类， 添加@Configuration注解声明为配置类
2. 创建方法，方法上加上@bean，该方法用于创建实例并返回，该实例创建后会交给spring管理，方法名建议与实例名相同（首字母小写）。注：实例类不需要加任何注解
```java
/**
 * @author pdai
 */
@Configuration
public class BeansConfig {

    /**
     * @return user dao
     */
    @Bean("userDao")
    public UserDaoImpl userDao() {
        return new UserDaoImpl();
    }

    /**
     * @return user service
     */
    @Bean("userService")
    public UserServiceImpl userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        return userService;
    }
}
```
### 4.3.3 注解配置
通过在类上加注解的方式，来声明一个类交给Spring管理，Spring会自动扫描带有@Component，@Controller，@Service，@Repository这四个注解的类，然后帮我们创建并管理，前提是需要先配置Spring的注解扫描器。
- 优点：开发便捷，通俗易懂，方便维护。
- 缺点：具有局限性，对于一些第三方资源，无法添加注解。只能采用XML或JavaConfig的方式配置

**举例：**
1. 对类添加@Component相关的注解，比如@Controller，@Service，@Repository
2. 设置ComponentScan的basePackage, 比如`<context:component-scan base-package='tech.pdai.springframework'>`, 或者`@ComponentScan("tech.pdai.springframework")`注解，或者`new AnnotationConfigApplicationContext("tech.pdai.springframework")`指定扫描的basePackage.
```java
/**
 * @author pdai
 */
@Service
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    @Autowired
    private UserDaoImpl userDao;

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return userDao.findUserList();
    }

}
```
## 4.4 依赖注入的三种方式
> 常用的注入方式主要有三种：构造方法注入（Construct注入），setter注入，字段(属性)注入
### 4.4.1 setter方式
- 在**XML配置方式**中，property都是setter方式注入，比如下面的xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- services -->
    <bean id="userService" class="tech.pdai.springframework.service.UserServiceImpl">
        <property name="userDao" ref="userDao"/>
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>
    <!-- more bean definitions for services go here -->
</beans>
```
本质上包含两步：

1. 第一步，需要new UserServiceImpl()创建对象, 所以需要默认构造函数
2. 第二步，调用setUserDao()函数注入userDao的值, 所以需要setUserDao()函数

所以对应的service类是这样的：
```java
/**
 * @author pdai
 */
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    private UserDaoImpl userDao;

    /**
     * init.
     */
    public UserServiceImpl() {
    }

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return this.userDao.findUserList();
    }

    /**
     * set dao.
     *
     * @param userDao user dao
     */
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
```
- 在注解和Java配置方式下
```java
/**
 * @author pdai
 */
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    private UserDaoImpl userDao;

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return this.userDao.findUserList();
    }

    /**
     * set dao.
     *
     * @param userDao user dao
     */
    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
```
在Spring3.x刚推出的时候，推荐使用注入的就是这种, 但是这种方式比较麻烦，所以在Spring4.x版本中推荐构造函数注入。

### 4.4.2 构造函数
- 在**XML配置方式**中，`<constructor-arg>`是通过构造函数参数注入，比如下面的xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- services -->
    <bean id="userService" class="tech.pdai.springframework.service.UserServiceImpl">
        <constructor-arg name="userDao" ref="userDao"/>
        <!-- additional collaborators and configuration for this bean go here -->
    </bean>
    <!-- more bean definitions for services go here -->
</beans>
```
本质上是new UserServiceImpl(userDao)创建对象, 所以对应的service类是这样的：
```java
/**
 * @author pdai
 */
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    private final UserDaoImpl userDao;

    /**
     * init.
     * @param userDaoImpl user dao impl
     */
    public UserServiceImpl(UserDaoImpl userDaoImpl) {
        this.userDao = userDaoImpl;
    }

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return this.userDao.findUserList();
    }

}
```
- 在注解和Java配置方式下
```java
/**
 * @author pdai
 */
 @Service
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    private final UserDaoImpl userDao;

    /**
     * init.
     * @param userDaoImpl user dao impl
     */
    @Autowired // 这里@Autowired也可以省略
    public UserServiceImpl(final UserDaoImpl userDaoImpl) {
        this.userDao = userDaoImpl;
    }

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return this.userDao.findUserList();
    }

}
```
在Spring4.x版本中推荐的注入方式就是这种， 具体原因看后续章节。

### 4.4.3 字段(属性)注入
以@Autowired（自动注入）注解注入为例，字段注入（也称为属性注入）是将依赖直接注入到类的字段上，而不需要通过构造器或setter方法。Spring默认按类型（byType）进行匹配，但也可以通过注解调整匹配策略。

- **byType**：默认方式，Spring会根据字段的类型在容器中查找匹配的bean。如果容器中只有一个与字段类型一致的bean，则注入成功；如果找到多个同类型bean，Spring会抛出异常，此时需要结合@Qualifier注解按名称区分。
- **byName**：如果需要按名称注入，可以在@Autowired基础上添加@Qualifier注解，指定bean的名称（即bean的id）。字段注入本身不直接支持基于setter方法名的byName匹配，但@Qualifier可以实现类似效果。

字段注入的优点是代码简洁，但缺点是隐藏了依赖关系，不利于单元测试和代码的可维护性。因此，Spring官方推荐优先使用构造器注入。

示例代码：
```java
/**
 * @author pdai
 */
@Service
public class UserServiceImpl {

    /**
     * user dao impl. 通过@Autowired直接注入字段，默认按类型匹配。
     */
    @Autowired
    private UserDaoImpl userDao;

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return userDao.findUserList();
    }

}
```

如果存在多个同类型bean，需要使用@Qualifier按名称注入：
```java
@Service
public class UserServiceImpl {

    @Autowired
    @Qualifier("userDaoImpl") // 指定bean名称，按名称注入
    private UserDao userDao;

    // ... 其他代码
}
```
## 4.5 IoC和DI使用问题小结
> 这里总结下实际开发中会遇到的一些问题：

### 4.5.1 为什么推荐构造器注入方式？
先来看看Spring在文档里怎么说：
> The Spring team generally advocates constructor injection as it enables one to implement application components as immutable objects and to ensure that required dependencies are not null. Furthermore constructor-injected components are always returned to client (calling) code in a fully initialized state.
> 
> Spring 团队通常提倡构造函数注入，因为它允许将应用程序组件实现为不可变对象，并确保所需的依赖项不为空。此外，构造函数注入的组件始终以完全初始化的状态返回给客户端（调用）代码。

简单的翻译一下：这个构造器注入的方式**能够保证注入的组件不可变，并且确保需要的依赖不为空**。此外，**构造器注入的依赖总是能够在返回客户端（组件）代码的时候保证完全初始化的状态**。

下面来简单的解释一下：
- **依赖不可变**：其实说的就是final关键字。
- **依赖不为空（省去了我们对其检查）**：当要实例化UserServiceImpl的时候，由于自己实现了有参数的构造函数，所以不会调用默认构造函数，那么就需要Spring容器传入所需要的参数，所以就两种情况：1、有该类型的参数->传入，OK 。2：无该类型的参数->报错。
- **完全初始化的状态**：这个可以跟上面的依赖不为空结合起来，向构造器传参之前，要确保注入的内容不为空，那么肯定要调用依赖组件的构造方法完成实例化。而在Java类加载实例化的过程中，构造方法是最后一步（之前如果有父类先初始化父类，然后自己的成员变量，最后才是构造方法），所以返回来的都是初始化之后的状态。

所以通常是这样的
```java
/**
 * @author pdai
 */
 @Service
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    private final UserDaoImpl userDao;

    /**
     * init.
     * @param userDaoImpl user dao impl
     */
    public UserServiceImpl(final UserDaoImpl userDaoImpl) {
        this.userDao = userDaoImpl;
    }

}
```
如果使用setter注入，缺点显而易见，对于IOC容器以外的环境，除了使用反射来提供它需要的依赖之外，**无法复用该实现类**。而且将一直是个潜在的隐患，因为你不调用将一直无法发现NPE的存在。
```java
// 这里只是模拟一下，正常来说我们只会暴露接口给客户端，不会暴露实现。
UserServiceImpl userService = new UserServiceImpl();
userService.findUserList(); // -> NullPointerException, 潜在的隐患
```
**循环依赖的问题**：使用field注入可能会导致循环依赖，即A里面注入B，B里面又注入A：
```java
public class A {
    @Autowired
    private B b;
}

public class B {
    @Autowired
    private A a;
}
```
如果使用构造器注入，在spring项目启动的时候，就会抛出：BeanCurrentlyInCreationException：Requested bean is currently in creation: Is there an unresolvable circular reference？从而提醒你避免循环依赖，如果是field注入的话，启动的时候不会报错，在使用那个bean的时候才会报错。
### 4.5.2 我在使用构造器注入方式时注入了太多的类导致Bad Smell怎么办？
比如当你一个Controller中注入了太多的Service类，Sonar会给你提示相关告警
![34.spring-framework-ioc-3.png](../../assets/images/04-主流框架/spring/34.spring-framework-ioc-3.png)

对于这个问题，说明你的类当中有太多的责任，那么你要好好想一想是不是自己违反了类的**单一性职责原则**，从而导致有这么多的依赖要注入。
### 4.5.3 @Autowired和@Resource以及@Inject等注解注入有何区别？
#### 4.5.3.1 @Autowired
- Autowired注解源码

在Spring 2.5 引入了 @Autowired 注解
```java
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
  boolean required() default true;
}
```
从Autowired注解源码上看，可以使用在下面这些地方：
```java
@Target(ElementType.CONSTRUCTOR) #构造函数
@Target(ElementType.METHOD) #方法
@Target(ElementType.PARAMETER) #方法参数
@Target(ElementType.FIELD) #字段、枚举的常量
@Target(ElementType.ANNOTATION_TYPE) #注解
```
还有一个value属性，默认是true。
- 简单总结：
1. @Autowired是Spring自带的注解，通过AutowiredAnnotationBeanPostProcessor 类实现的依赖注入
2. @Autowired可以作用在CONSTRUCTOR、METHOD、PARAMETER、FIELD、ANNOTATION_TYPE
3. @Autowired默认是根据类型（byType ）进行自动装配的
4. 如果有多个类型一样的Bean候选者，需要指定按照名称（byName ）进行装配，则需要配合@Qualifier。
5. 指定名称后，如果Spring IOC容器中没有对应的组件bean抛出NoSuchBeanDefinitionException。也可以将@Autowired中required配置为false，如果配置为false之后，当没有找到相应bean的时候，系统不会抛异常
- 简单使用代码：

在字段属性上。
```java
@Autowired
private HelloDao helloDao;
```
或者
```java
private HelloDao helloDao;
public HelloDao getHelloDao() {
 return helloDao;
}
@Autowired
public void setHelloDao(HelloDao helloDao) {
 this.helloDao = helloDao;
}
```
或者
```java
private HelloDao helloDao;
//@Autowired
public HelloServiceImpl(@Autowired HelloDao helloDao) {
 this.helloDao = helloDao;
}
// 构造器注入也可不写@Autowired，也可以注入成功。
```
将@Autowired写在被注入的成员变量上，setter或者构造器上，就不用再xml文件中配置了。

如果有多个类型一样的Bean候选者，则默认根据设定的属性名称进行获取。如 HelloDao 在Spring中有 helloWorldDao 和 helloDao 两个Bean候选者。
```java
@Autowired
private HelloDao helloDao;
```
首先根据类型获取，发现多个HelloDao，然后根据helloDao进行获取，如果要获取限定的其中一个候选者，结合@Qualifier进行注入。
```java
@Autowired
@Qualifier("helloWorldDao")
private HelloDao helloDao;
```
注入名称为helloWorldDao 的Bean组件。@Qualifier("XXX") 中的 XX是 Bean 的名称，所以 @Autowired 和 @Qualifier 结合使用时，自动注入的策略就从 byType 转变成 byName 了。

多个类型一样的Bean候选者，也可以@Primary进行使用，设置首选的组件，也就是默认优先使用哪一个。

注意：使用@Qualifier 时候，如何设置的指定名称的Bean不存在，则会抛出异常，如果防止抛出异常，可以使用：
```java
@Qualifier("xxxxyyyy")
@Autowired(required = false)
private HelloDao helloDao;
```
在SpringBoot中也可以使用@Bean+@Autowired进行组件注入，将@Autowired加到参数上，其实也可以省略。
```java
@Bean
public Person getPerson(@Autowired Car car){
 return new Person();
}
// @Autowired 其实也可以省略
```
#### 4.5.3.2 @Resource
- Resource注解源码
```java
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface Resource {
    String name() default "";
    // 其他省略
}
```
从Resource注解源码上看，可以使用在下面这些地方：
```java
@Target(ElementType.TYPE) #接口、类、枚举、注解
@Target(ElementType.FIELD) #字段、枚举的常量
@Target(ElementType.METHOD) #方法
```
name 指定注入指定名称的组件。
- 简单总结：
1. @Resource是JSR250规范的实现，在javax.annotation包下
2. @Resource可以作用TYPE、FIELD、METHOD上
3. @Resource是默认根据属性名称进行自动装配的，如果有多个类型一样的Bean候选者，则可以通过name进行指定进行注入
4. 在Spring容器解析@Resource注解时，使用的后置处理器为CommonAnnotationBeanPostProcessor
5.  默认是按照组件名称进行装配的，根据@Resource注解name属性的名称去容器中查找，如果name没有指定，则根据标注了@Resource的属性名称去判断容器中是否存在该名称的bean，如果不存在，则会走和@Autowired一样的逻辑，这个时候就会支持@Primary注解（有的博客说不支持，那么错误的，源码不会骗人）。
- 简单使用代码：
```java
@Component
public class SuperMan {
    @Resource
    private Car car;
}
```
按照属性名称 car 注入容器中的组件。如果容器中BMW还有BYD两种类型组件。指定加入BMW。如下代码：
```java
@Component
public class SuperMan {
    @Resource(name = "BMW")
    private Car car;
}
```
name 的作用类似 @Qualifier

#### 4.5.3.3 @Inject
- Inject注解源码
```java
@Target({ METHOD, CONSTRUCTOR, FIELD })
@Retention(RUNTIME)
@Documented
public @interface Inject {}
```
从Inject注解源码上看，可以使用在下面这些地方：
```java
@Target(ElementType.CONSTRUCTOR) #构造函数
@Target(ElementType.METHOD) #方法
@Target(ElementType.FIELD) #字段、枚举的常量
```
- 简单总结：
1. @Inject是JSR330 (Dependency Injection for Java)中的规范，需要导入javax.inject.Inject jar包 ，才能实现注入
2. @Inject可以作用CONSTRUCTOR、METHOD、FIELD上
3. @Inject是根据类型进行自动装配的，如果需要按名称进行装配，则需要配合@Named；
4. .在Spring容器解析@Inject注解时，使用的后置处理器和@Autowired是一样的，都是AutowiredAnnotationBeanPostProcessor。
5. 由于@Inject注解没有属性，在加载所需bean失败时，会报错
- 简单使用代码：
```java
@Inject
private Car car;
```
指定加入BMW组件。
```java
@Inject
@Named("BMW")
private Car car;
```
@Named 的作用类似 @Qualifier！

#### 总结
1. @Autowired是Spring自带的，@Resource是JSR250规范实现的，@Inject是JSR330规范实现的
2. @Autowired、@Inject用法基本一样，不同的是@Inject没有required属性
3. @Autowired、@Inject是默认按照类型匹配的，@Resource是按照名称匹配的
4. @Autowired如果需要按照名称匹配需要和@Qualifier一起使用，@Inject和@Named一起使用，@Resource则通过name进行指定

如果你还期望源码层理解，我给你找了一篇文章Spring源码分析<a href = 'https://blog.csdn.net/qq_35634181/article/details/104802906'>@Autowired、@Resource注解的区别</a>
# 五、Spring基础 - Spring核心之面向切面编程(AOP)
## 5.1 引入
> 我们在Spring基础 - Spring简单例子引入Spring的核心中向你展示了AOP的基础含义，同时以此发散了一些AOP相关知识点。
- Spring 框架通过定义切面, 通过拦截切点实现了不同业务模块的解耦，这个就叫**面向切面编程 - Aspect Oriented Programming (AOP)**
- 为什么@Aspect注解使用的是aspectj的jar包呢？这就引出了**Aspect4J和Spring AOP的历史渊源**，只有理解了Aspect4J和Spring的渊源才能理解有些注解上的兼容设计
- 如何支持更多拦截方式来实现解耦， 以满足更多场景需求呢？ 这就是@Around, @Pointcut... 等的设计
- 那么Spring框架又是如何实现AOP的呢？ 这就引入**代理技术，分静态代理和动态代理**，动态代理又包含**JDK代理和CGLIB代理**等

本节将在此基础上进一步解读AOP的含义以及AOP的使用方式；后续的文章还将深入AOP的实现原理：
- Spring进阶 - Spring AOP实现原理详解之切面实现
- Spring进阶 - Spring AOP实现原理详解之AOP代理
## 5.2 如何理解AOP
> AOP的本质也是为了解耦，它是一种设计思想； 在理解时也应该简化理解。
### 5.2.1 AOP是什么
> AOP为Aspect Oriented Programming的缩写，意为：面向切面编程

AOP最早是AOP联盟的组织提出的,指定的一套规范,spring将AOP的思想引入框架之中,通过**预编译方式和运行期间动态代理**实现程序的统一维护的一种技术,
- 先来看一个例子， 如何给如下UserServiceImpl中所有方法添加进入方法的日志，
```java
/**
 * @author pdai
 */
public class UserServiceImpl implements IUserService {

    /**
     * find user list.
     *
     * @return user list
     */
    @Override
    public List<User> findUserList() {
        System.out.println("execute method： findUserList");
        return Collections.singletonList(new User("pdai", 18));
    }

    /**
     * add user
     */
    @Override
    public void addUser() {
        System.out.println("execute method： addUser");
        // do something
    }

}
```
我们将记录日志功能解耦为日志切面，它的目标是解耦。进而引出AOP的理念：就是将分散在各个业务逻辑代码中相同的代码通过**横向切割**的方式抽取到一个独立的模块中！
![35.spring-framework-aop-4.png](../../assets/images/04-主流框架/spring/35.spring-framework-aop-4.png)

OOP面向对象编程，针对业务处理过程的实体及其属性和行为进行抽象封装，以获得更加清晰高效的逻辑单元划分。而AOP则是针对业务处理过程中的切面进行提取，它所面对的是处理过程的某个步骤或阶段，以获得逻辑过程的中各部分之间低耦合的隔离效果。这两种设计思想在目标上有着本质的差异。
![4.spring-framework-aop-2.png](../../assets/images/04-主流框架/spring/4.spring-framework-aop-2.png)
### 5.2.2 AOP术语
> 首先让我们从一些重要的AOP概念和术语开始。**这些术语不是Spring特有的**。
- `连接点（Jointpoint）`：表示需要在程序中插入横切关注点的扩展点，连接点可能是**类初始化、方法执行、方法调用、字段调用或处理异常**等等，`Spring只支持方法执行连接点`，在AOP中表示为**在哪里干**；
- `切入点（Pointcut）`： 选择一组相关连接点的模式，即可以认为**连接点的集合**，`Spring支持perl5正则表达式和AspectJ切入点模式，Spring默认使用AspectJ语法`，在AOP中表示为**在哪里干的集合**；
- `通知（Advice）`：在连接点上执行的行为，通知提供了在AOP中需要在切入点所选择的连接点处进行扩展现有行为的手段；包括前置通知（before advice）、后置通知(after advice)、环绕通知（around advice），在`Spring中通过代理模式实现AOP`，并通过拦截器模式以环绕连接点的拦截器链织入通知；在AOP中表示为**干什么**；
- `方面/切面（Aspect）`：横切关注点的模块化，比如上边提到的日志组件。可以认为是**通知、引入和切入点的组合**；`在Spring中可以使用Schema和@AspectJ方式进行组织实现`；在AOP中表示为**在哪干和干什么集合**；
- `引入（inter-type declaration）`：也称为内部类型声明，为已有的类添加额外新的字段或方法，`Spring允许引入新的接口（必须对应一个实现）到所有被代理对象（目标对象）`, 在AOP中表示为**干什么（引入什么）**；
- `目标对象（Target Object）`：需要被织入横切关注点的对象，即该对象是切入点选择的对象，需要被通知的对象，从而也可称为被通知对象；由于Spring AOP 通过代理模式实现，从而这个对象永远是被代理对象，在AOP中表示为**对谁干**；
- `织入（Weaving）`：把切面连接到其它的应用程序类型或者对象上，并创建一个被通知的对象。这些可以在编译时（例如使用AspectJ编译器），类加载时和运行时完成。`Spring和其他纯Java AOP框架一样，在运行时完成织入`。在AOP中表示为**怎么实现的**；
- `AOP代理（AOP Proxy）`：AOP框架使用代理模式创建的对象，从而实现在连接点处插入通知（即应用切面），就是通过代理来对目标对象应用切面。`在Spring中，AOP代理可以用JDK动态代理或CGLIB代理实现`，而通过拦截器模型应用切面。在AOP中表示为**怎么实现的一种典型方式**；
> 通知类型：
- `前置通知（Before advice）`：在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。
- `后置通知（After returning advice）`：在某连接点正常完成后执行的通知：例如，一个方法没有抛出任何异常，正常返回。
- `异常通知（After throwing advice）`：在方法抛出异常退出时执行的通知。
- `最终通知（After (finally) advice）`：当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
- `环绕通知（Around Advice）`：包围一个连接点的通知，如方法调用。这是最强大的一种通知类型。环绕通知可以在方法调用前后完成自定义的行为。它也会选择是否继续执行连接点或直接返回它自己的返回值或抛出异常来结束执行。

环绕通知是最常用的通知类型。和AspectJ一样，Spring提供所有类型的通知，我们推荐你使用尽可能简单的通知类型来实现需要的功能。例如，如果你只是需要一个方法的返回值来更新缓存，最好使用后置通知而不是环绕通知，尽管环绕通知也能完成同样的事情。用最合适的通知类型可以使得编程模型变得简单，并且能够避免很多潜在的错误。比如，你不需要在JoinPoint上调用用于环绕通知的proceed()方法，就不会有调用的问题。
> 我们把这些术语串联到一起，方便理解
![36.spring-framework-aop-3.png](../../assets/images/04-主流框架/spring/36.spring-framework-aop-3.png)
## 5.3 Spring AOP和AspectJ是什么关系
- 首先AspectJ是什么？

AspectJ是一个java实现的AOP框架，它能够对java代码进行AOP编译（一般在编译期进行），让java代码具有AspectJ的AOP功能（当然需要特殊的编译器）

可以这样说AspectJ是目前实现AOP框架中最成熟，功能最丰富的语言，更幸运的是，AspectJ与java程序完全兼容，几乎是无缝关联，因此对于有java编程基础的工程师，上手和使用都非常容易。
- 其次，为什么需要理清楚Spring AOP和AspectJ的关系？

我们看下@Aspect以及增强的几个注解，为什么不是Spring包，而是来源于aspectJ呢？
![37.spring-framework-aop-5.png](../../assets/images/04-主流框架/spring/37.spring-framework-aop-5.png)
- Spring AOP和AspectJ是什么关系？
1. AspectJ是更强的AOP框架，是实际意义的AOP标准；
2. Spring为何不写类似AspectJ的框架？ Spring AOP使用纯Java实现, 它不需要专门的编译过程, 它一个重要的原则就是无侵入性（non-invasiveness）; Spring 小组完全有能力写类似的框架，只是Spring AOP从来没有打算通过提供一种全面的AOP解决方案来与AspectJ竞争。Spring的开发小组相信无论是基于代理（proxy-based）的框架如Spring AOP或者是成熟的框架如AspectJ都是很有价值的，他们之间应该是互补而不是竞争的关系。
3. Spring小组喜欢@AspectJ注解风格更胜于Spring XML配置； 所以在Spring 2.0使用了和AspectJ 5一样的注解，并使用AspectJ来做切入点解析和匹配。但是，AOP在运行时仍旧是纯的Spring AOP，并不依赖于AspectJ的编译器或者织入器（weaver）。
4. Spring 2.5对AspectJ的支持：在一些环境下，增加了对AspectJ的装载时编织支持，同时提供了一个新的bean切入点。
- 更多关于AspectJ？

了解AspectJ应用到java代码的过程（这个过程称为织入），对于织入这个概念，可以简单理解为aspect(切面)应用到目标函数(类)的过程。

对于这个过程，一般分为**动态织入**和**静态织入**：

1. 动态织入的方式是在运行时动态将要增强的代码织入到目标类中，这样往往是通过动态代理技术完成的，如Java JDK的动态代理(Proxy，底层通过反射实现)或者CGLIB的动态代理(底层通过继承实现)，`Spring AOP采用的就是基于运行时增强的代理技术`
2. ApectJ采用的就是静态织入的方式。ApectJ主要采用的是编译期织入，在这个期间使用AspectJ的acj编译器(类似javac)把aspect类编译成class字节码后，在java目标类编译时织入，即先编译aspect类再编译目标类。
![38.spring-framework-aop-6.png](../../assets/images/04-主流框架/spring/38.spring-framework-aop-6.png)

| 特性         | Spring AOP       | AspectJ          |
|--------------|------------------|------------------|
| **织入时机** | 运行时（动态）   | 主要编译时/加载时（静态） |
| **支持范围** | 方法级别         | 全范围（方法、字段等） |
| **性能**     | 较低（代理开销） | 较高（无运行时开销） |
| **依赖性**   | 依赖 Spring 容器 | 可独立使用       |
| **配置复杂度** | **极简**。在 Spring Boot 中，只需添加 `@EnableAspectJAutoProxy`（通常已默认开启）即可开始使用。 | **复杂**。需要显式配置织入器，并可能改变项目的构建流程。 |
| **构建/编译依赖** | **无额外依赖**。利用 Spring IoC 容器和动态代理，与标准 Java 编译流程完全一致。 | **需要额外的织入步骤**。必须引入 AspectJ 的织入器来修改字节码。 |
| **打包部署** | **无影响**。与普通 Spring 应用打包方式完全相同。 | **有影响**。需要确保织入过程在编译时或加载时正确完成，可能会增加部署复杂度。 |

**一言以蔽之：Spring AOP 提供了“开箱即用”的便利，而 AspectJ 提供了“专业强大”的功能，但需要付出配置和学习的成本。**
## 5.4 AOP的配置方式
> Spring AOP 支持对XML模式和基于@AspectJ注解的两种配置方式。
### 5.4.1 XML Schema配置方式
Spring提供了使用"aop"命名空间来定义一个切面，我们来看个例子：
- 定义目标类
```java
package tech.pdai.springframework.service;

/**
 * @author pdai
 */
public class AopDemoServiceImpl {

    public void doMethod1() {
        System.out.println("AopDemoServiceImpl.doMethod1()");
    }

    public String doMethod2() {
        System.out.println("AopDemoServiceImpl.doMethod2()");
        return "hello world";
    }

    public String doMethod3() throws Exception {
        System.out.println("AopDemoServiceImpl.doMethod3()");
        throw new Exception("some exception");
    }
}
```
- 定义切面类
```java
package tech.pdai.springframework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author pdai
 */
public class LogAspect {

    /**
     * 环绕通知.
     *
     * @param pjp pjp
     * @return obj
     * @throws Throwable exception
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = pjp.proceed();
        System.out.println("环绕通知: 退出方法");
        return o;
    }

    /**
     * 前置通知.
     */
    public void doBefore() {
        System.out.println("前置通知");
    }

    /**
     * 后置通知.
     *
     * @param result return val
     */
    public void doAfterReturning(String result) {
        System.out.println("后置通知, 返回值: " + result);
    }

    /**
     * 异常通知.
     *
     * @param e exception
     */
    public void doAfterThrowing(Exception e) {
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    /**
     * 最终通知.
     */
    public void doAfter() {
        System.out.println("最终通知");
    }

}
```
- XML配置AOP
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd
 http://www.springframework.org/schema/aop
 http://www.springframework.org/schema/aop/spring-aop.xsd
 http://www.springframework.org/schema/context
 http://www.springframework.org/schema/context/spring-context.xsd
">

    <context:component-scan base-package="tech.pdai.springframework" />

    <aop:aspectj-autoproxy/>

    <!-- 目标类 -->
    <bean id="demoService" class="tech.pdai.springframework.service.AopDemoServiceImpl">
        <!-- configure properties of bean here as normal -->
    </bean>

    <!-- 切面 -->
    <bean id="logAspect" class="tech.pdai.springframework.aspect.LogAspect">
        <!-- configure properties of aspect here as normal -->
    </bean>

    <aop:config>
        <!-- 配置切面 -->
        <aop:aspect ref="logAspect">
            <!-- 配置切入点 -->
            <aop:pointcut id="pointCutMethod" expression="execution(* tech.pdai.springframework.service.*.*(..))"/>
            <!-- 环绕通知 -->
            <aop:around method="doAround" pointcut-ref="pointCutMethod"/>
            <!-- 前置通知 -->
            <aop:before method="doBefore" pointcut-ref="pointCutMethod"/>
            <!-- 后置通知；returning属性：用于设置后置通知的第二个参数的名称，类型是Object -->
            <aop:after-returning method="doAfterReturning" pointcut-ref="pointCutMethod" returning="result"/>
            <!-- 异常通知：如果没有异常，将不会执行增强；throwing属性：用于设置通知第二个参数的的名称、类型-->
            <aop:after-throwing method="doAfterThrowing" pointcut-ref="pointCutMethod" throwing="e"/>
            <!-- 最终通知 -->
            <aop:after method="doAfter" pointcut-ref="pointCutMethod"/>
        </aop:aspect>
    </aop:config>

    <!-- more bean definitions for data access objects go here -->
</beans>
```
- 测试类
```java
/**
  * main interfaces.
  *
  * @param args args
  */
public static void main(String[] args) {
    // create and configure beans
    ApplicationContext context = new ClassPathXmlApplicationContext("aspects.xml");

    // retrieve configured instance
    AopDemoServiceImpl service = context.getBean("demoService", AopDemoServiceImpl.class);

    // use configured instance
    service.doMethod1();
    service.doMethod2();
    try {
        service.doMethod3();
    } catch (Exception e) {
        // e.printStackTrace();
    }
}
```
- 输出结果
```java
-----------------------
环绕通知: 进入方法
前置通知
AopDemoServiceImpl.doMethod1()
环绕通知: 退出方法
最终通知
-----------------------
环绕通知: 进入方法
前置通知
AopDemoServiceImpl.doMethod2()
环绕通知: 退出方法
最终通知
后置通知, 返回值: hello world
-----------------------
环绕通知: 进入方法
前置通知
AopDemoServiceImpl.doMethod3()
最终通知
异常通知, 异常: some exception
```
### 5.4.2 AspectJ注解方式
基于XML的声明式AspectJ存在一些不足，需要在Spring配置文件配置大量的代码信息，为了解决这个问题，Spring 使用了@AspectJ框架为AOP的实现提供了一套注解。


| 注解名称 | 用途说明 | 关键属性及说明 |
|---------|---------|--------------|
| **@Aspect** | 定义一个切面类 | 无特殊属性，标记类为切面 |
| **@Pointcut** | 定义切入点表达式 | `value`：切入点表达式<br>需搭配空方法体方法签名 |
| **@Before** | 定义前置通知（目标方法执行前） | `value`：指定切入点表达式 |
| **@AfterReturning** | 定义后置通知（目标方法正常返回后） | `value/pointcut`：切入点表达式<br>`returning`：接收返回值的形参名 |
| **@Around** | 定义环绕通知（包裹目标方法） | `value`：指定切入点表达式 |
| **@AfterThrowing** | 定义异常通知（目标方法抛出异常时） | `value/pointcut`：切入点表达式<br>`throwing`：接收异常的形参名 |
| **@After** | 定义最终通知（无论是否异常都会执行） | `value`：指定切入点表达式 |
| **@DeclareParents** | 定义引介通知（为类添加新接口） | `value`：目标类型模式<br>`defaultImpl`：默认实现类 |

补充说明：

- **@Pointcut** 需要配合方法签名使用，方法返回类型必须为 `void`，方法体为空
- 所有通知注解都支持直接定义切入点表达式或引用已有的 `@Pointcut` 定义
- **@Around** 是最强大的通知类型，可以控制是否执行目标方法以及返回值处理


> Spring AOP的实现方式是动态织入，动态织入的方式是在运行时动态将要增强的代码织入到目标类中，这样往往是通过动态代理技术完成的；如Java JDK的动态代理(Proxy，底层通过反射实现)或者CGLIB的动态代理(底层通过继承实现)，Spring AOP采用的就是基于运行时增强的代理技术。
#### 5.4.2.1 接口使用JDK代理
- 定义接口
```java
/**
 * Jdk Proxy Service.
 *
 * @author pdai
 */
public interface IJdkProxyService {

    void doMethod1();

    String doMethod2();

    String doMethod3() throws Exception;
}
```
- 实现类
```java
/**
 * @author pdai
 */
@Service
public class JdkProxyDemoServiceImpl implements IJdkProxyService {

    @Override
    public void doMethod1() {
        System.out.println("JdkProxyServiceImpl.doMethod1()");
    }

    @Override
    public String doMethod2() {
        System.out.println("JdkProxyServiceImpl.doMethod2()");
        return "hello world";
    }

    @Override
    public String doMethod3() throws Exception {
        System.out.println("JdkProxyServiceImpl.doMethod3()");
        throw new Exception("some exception");
    }
}
```
- 定义切面
```java
package tech.pdai.springframework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author pdai
 */
@EnableAspectJAutoProxy
@Component
@Aspect
public class LogAspect {

    /**
     * define point cut.
     */
    @Pointcut("execution(* tech.pdai.springframework.service.*.*(..))")
    private void pointCutMethod() {
    }


    /**
     * 环绕通知.
     *
     * @param pjp pjp
     * @return obj
     * @throws Throwable exception
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = pjp.proceed();
        System.out.println("环绕通知: 退出方法");
        return o;
    }

    /**
     * 前置通知.
     */
    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置通知");
    }


    /**
     * 后置通知.
     *
     * @param result return val
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        System.out.println("后置通知, 返回值: " + result);
    }

    /**
     * 异常通知.
     *
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    /**
     * 最终通知.
     */
    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("最终通知");
    }

}
```
- 输出
```java
-----------------------
环绕通知: 进入方法
前置通知
JdkProxyServiceImpl.doMethod1()
最终通知
环绕通知: 退出方法
-----------------------
环绕通知: 进入方法
前置通知
JdkProxyServiceImpl.doMethod2()
后置通知, 返回值: hello world
最终通知
环绕通知: 退出方法
-----------------------
环绕通知: 进入方法
前置通知
JdkProxyServiceImpl.doMethod3()
异常通知, 异常: some exception
最终通知
```
#### 5.4.2.2 非接口使用Cglib代理
- 类定义
```java
/**
 * Cglib proxy.
 *
 * @author pdai
 */
@Service
public class CglibProxyDemoServiceImpl {

    public void doMethod1() {
        System.out.println("CglibProxyDemoServiceImpl.doMethod1()");
    }

    public String doMethod2() {
        System.out.println("CglibProxyDemoServiceImpl.doMethod2()");
        return "hello world";
    }

    public String doMethod3() throws Exception {
        System.out.println("CglibProxyDemoServiceImpl.doMethod3()");
        throw new Exception("some exception");
    }
}
```
- 切面定义

和上面相同

- 输出
```java
-----------------------
环绕通知: 进入方法
前置通知
CglibProxyDemoServiceImpl.doMethod1()
最终通知
环绕通知: 退出方法
-----------------------
环绕通知: 进入方法
前置通知
CglibProxyDemoServiceImpl.doMethod2()
后置通知, 返回值: hello world
最终通知
环绕通知: 退出方法
-----------------------
环绕通知: 进入方法
前置通知
CglibProxyDemoServiceImpl.doMethod3()
异常通知, 异常: some exception
最终通知
```
## 5.5 AOP使用问题小结
### 5.5.1 切入点（pointcut）的申明规则?

Spring AOP 用户可能会经常使用 execution切入点指示符。执行表达式的格式如下：
```java
execution（modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern（param-pattern） throws-pattern?）
```
- `ret-type-pattern` 返回类型模式, `name-pattern`名字模式和`param-pattern`参数模式是必选的， 其它部分都是可选的。返回类型模式决定了方法的返回类型必须依次匹配一个连接点。 你会使用的最频繁的返回类型模式是`*`，它代表了匹配任意的返回类型。
- `declaring-type-pattern`, 一个全限定的类型名将只会匹配返回给定类型的方法。
- `name-pattern` 名字模式匹配的是方法名。 你可以使用`*`通配符作为所有或者部分命名模式。
- `param-pattern` 参数模式稍微有点复杂：`()`匹配了一个不接受任何参数的方法， 而`(..)`匹配了一个接受任意数量参数的方法（零或者更多）。 模式`(*)`匹配了一个接受一个任何类型的参数的方法。 模式`(*,String)`匹配了一个接受两个参数的方法，第一个可以是任意类型， 第二个则必须是String类型。

对应到我们上面的例子：
![39.spring-framework-aop-7.png](../../assets/images/04-主流框架/spring/39.spring-framework-aop-7.png)

下面给出一些通用切入点表达式的例子。
```java
// 任意公共方法的执行：
execution（public * *（..））

// 任何一个名字以“set”开始的方法的执行：
execution（* set*（..））

// AccountService接口定义的任意方法的执行：
execution（* com.xyz.service.AccountService.*（..））

// 在service包中定义的任意方法的执行：
execution（* com.xyz.service.*.*（..））

// 在service包或其子包中定义的任意方法的执行：
execution（* com.xyz.service..*.*（..））

// 在service包中的任意连接点（在Spring AOP中只是方法执行）：
within（com.xyz.service.*）

// 在service包或其子包中的任意连接点（在Spring AOP中只是方法执行）：
within（com.xyz.service..*）

// 实现了AccountService接口的代理对象的任意连接点 （在Spring AOP中只是方法执行）：
this（com.xyz.service.AccountService）// 'this'在绑定表单中更加常用

// 实现AccountService接口的目标对象的任意连接点 （在Spring AOP中只是方法执行）：
target（com.xyz.service.AccountService） // 'target'在绑定表单中更加常用

// 任何一个只接受一个参数，并且运行时所传入的参数是Serializable 接口的连接点（在Spring AOP中只是方法执行）
args（java.io.Serializable） // 'args'在绑定表单中更加常用; 请注意在例子中给出的切入点不同于 execution(* *(java.io.Serializable))： args版本只有在动态运行时候传入参数是Serializable时才匹配，而execution版本在方法签名中声明只有一个 Serializable类型的参数时候匹配。

// 目标对象中有一个 @Transactional 注解的任意连接点 （在Spring AOP中只是方法执行）
@target（org.springframework.transaction.annotation.Transactional）// '@target'在绑定表单中更加常用

// 任何一个目标对象声明的类型有一个 @Transactional 注解的连接点 （在Spring AOP中只是方法执行）：
@within（org.springframework.transaction.annotation.Transactional） // '@within'在绑定表单中更加常用

// 任何一个执行的方法有一个 @Transactional 注解的连接点 （在Spring AOP中只是方法执行）
@annotation（org.springframework.transaction.annotation.Transactional） // '@annotation'在绑定表单中更加常用

// 任何一个只接受一个参数，并且运行时所传入的参数类型具有@Classified 注解的连接点（在Spring AOP中只是方法执行）
@args（com.xyz.security.Classified） // '@args'在绑定表单中更加常用

// 任何一个在名为'tradeService'的Spring bean之上的连接点 （在Spring AOP中只是方法执行）
bean（tradeService）

// 任何一个在名字匹配通配符表达式'*Service'的Spring bean之上的连接点 （在Spring AOP中只是方法执行）
bean（*Service）
```
此外Spring 支持如下三个逻辑运算符来组合切入点表达式
```java
&&：要求连接点同时匹配两个切入点表达式
||：要求连接点匹配任意个切入点表达式
!:：要求连接点不匹配指定的切入点表达式
```
### 5.5.2 多种增强通知的顺序？
如果**同一个切面中有多个通知(前置通知、后置通知)想要在同一连接点**运行会发生什么？Spring AOP遵循跟AspectJ一样的优先规则来确定通知执行的顺序。 在“进入”连接点的情况下，最高优先级的通知会先执行（所以给定的两个前置通知中，优先级高的那个会先执行）。 在“退出”连接点的情况下，最高优先级的通知会最后执行。（所以给定的两个后置通知中， 优先级高的那个会第二个执行）。
```java
// 切面
@Aspect
public class LoggingAspect {
    @Before("execution(* UserService.doSomething(..))")
    public void logBefore() {
        System.out.println("日志记录 - 前置通知");
    }
    @AfterReturning("execution(* UserService.doSomething(..))")
    public void logAfter() {
        System.out.println("日志记录 - 后置通知");
    }
}
// 执行结果当调用 doSomething() 时：
// 日志记录 - 前置通知（优先级高先执行）
// ... 目标方法执行 ...
// 日志记录 - 后置通知（优先级高后执行）
```
所有的通知的执行顺序
![40.AOP各种通知之间的执行顺序.png](../../assets/images/04-主流框架/spring/40.AOP各种通知之间的执行顺序.png)


当**定义在不同的切面里的两个通知都需要在一个相同的连接点**中运行， 那么除非你指定，否则执行的顺序是未知的。你可以通过指定优先级来控制执行顺序。 在标准的Spring方法中可以在切面类中实现org.springframework.core.Ordered 接口或者用Order注解做到这一点。在两个切面中， Ordered.getValue()方法返回值（或者注解值）较低的那个有更高的优先级。
```java
// 切面1：高优先级
@Aspect
@Order(1)
public class LoggingAspect {
    @Before("execution(* UserService.doSomething(..))")
    public void logBefore() {
        System.out.println("日志记录 - 前置通知");
    }
    @AfterReturning("execution(* UserService.doSomething(..))")
    public void logAfter() {
        System.out.println("日志记录 - 后置通知");
    }
}

// 切面2：低优先级
@Aspect
@Order(2)
public class SecurityAspect {
    @Before("execution(* UserService.doSomething(..))")
    public void checkSecurity() {
        System.out.println("安全检查 - 前置通知");
    }
    @AfterReturning("execution(* UserService.doSomething(..))")
    public void auditAfter() {
        System.out.println("安全审计 - 后置通知");
    }
}

// 执行结果当调用 doSomething() 时：
// 日志记录 - 前置通知（优先级高先执行）
// 安全检查 - 前置通知（优先级低后执行）
// ... 目标方法执行 ...
// 安全审计 - 后置通知（优先级低先执行）
// 日志记录 - 后置通知（优先级高后执行）
```
当**定义在相同的切面里的两个通知都需要在一个相同的连接点**中运行， 执行的顺序是未知的（因为这里没有方法通过反射javac编译的类来获取声明顺序）。 考虑在每个切面类中按连接点压缩这些通知方法到一个通知方法，或者重构通知的片段到各自的切面类中 - 它能在切面级别进行排序。
```java
@Aspect
public class MonitorAspect {
    // 两个通知在同一切面中，顺序不确定
    @Before("execution(* UserService.update(..))")
    public void monitorPerformance() {
        System.out.println("性能监控");
    }

    @Before("execution(* UserService.update(..))")
    public void monitorBusiness() {
        System.out.println("业务监控");
    }
}

// 执行时，可能先输出"性能监控"再输出"业务监控"，也可能相反！
```

**解决方案**：
- **方案1（合并通知）**：将两个通知合并为一个方法，在内部控制顺序。
  ```java
  @Before("execution(* UserService.update(..))")
  public void combinedMonitor() {
      monitorPerformance();
      monitorBusiness();
  }
  ```
- **方案2（拆分切面）**：将每个通知移到独立的切面类，然后用 `@Order` 控制顺序：
  ```java
  @Aspect
  @Order(1)
  public class PerformanceAspect { ... }
  
  @Aspect
  @Order(2)
  public class BusinessAspect { ... }
  ```
### 5.5.3 Spring AOP 和 AspectJ 之间的关键区别？
AspectJ可以做Spring AOP干不了的事情，**它(AspectJ)是AOP编程的完全解决方案，Spring AOP则致力于解决企业级开发中最普遍的AOP（方法织入）**。

下表总结了 Spring AOP 和 AspectJ 之间的关键区别:
| 比较方面 | Spring AOP | AspectJ |
|----------|------------|---------|
| **实现基础** | 在纯 Java 中实现 | 使用 Java 编程语言的扩展实现 |
| **编译过程** | 不需要单独的编译过程 | 除非设置 LTW（ Load-Time Weaving-加载时织入），否则需要 AspectJ 编译器 (ajc) |
| **织入方式** | 只能使用运行时织入 | 运行时织入不可用，支持编译时、编译后和加载时织入 |
| **编织粒度** | 功能不强，仅支持方法级编织 | 更强大，可以编织字段、方法、构造函数、静态初始值设定项、最终类/方法等...... |
| **适用对象** | 只能在由 Spring 容器管理的 bean 上实现 | 可以在所有域对象上实现 |
| **切入点支持** | 仅支持方法执行切入点 | 支持所有切入点 |
| **织入机制** | 代理是由目标对象创建的，并且切面应用在这些代理上 | 在执行应用程序之前（在运行时前），各方面直接在代码中进行织入 |
| **性能** | 比 AspectJ 慢多了 | 更好的性能 |
| **易用性** | 易于学习和应用 | 相对于 Spring AOP 来说更复杂 |

### 5.5.4 Spring AOP还是完全用AspectJ？
以下Spring官方的回答：（总结来说就是 **Spring AOP更易用，AspectJ更强大**）。
- Spring AOP比完全使用AspectJ更加简单， 因为它不需要引入AspectJ的编译器／织入器到你开发和构建过程中。 如果你**仅仅需要在Spring bean上通知执行操作，那么Spring AOP是合适的选择**。
- 如果你需要通知domain对象或其它没有在Spring容器中管理的任意对象，那么**你需要使用AspectJ**。
- 如果你想通知除了简单的方法执行之外的连接点（如：调用连接点、字段get或set的连接点等等）， 也**需要使用AspectJ**。

当使用AspectJ时，你可以选择使用AspectJ语言（也称为“代码风格”）或@AspectJ注解风格。 如果切面在你的设计中扮演一个很大的角色，并且你能在Eclipse等IDE中使用AspectJ Development Tools (AJDT)， 那么首选AspectJ语言 :- 因为该语言专门被设计用来编写切面，所以会更清晰、更简单。如果你没有使用 Eclipse等IDE，或者在你的应用中只有很少的切面并没有作为一个主要的角色，你或许应该考虑使用@AspectJ风格 并在你的IDE中附加一个普通的Java编辑器，并且在你的构建脚本中增加切面织入（链接）的段落。
# 六、Spring基础 - SpringMVC请求流程和案例
## 6.1 引入
前文我们介绍了Spring框架和Spring框架中最为重要的两个技术点（IOC和AOP），同时我们也通过几个Demo应用了Core Container中的包
![41.spring-springframework-mvc-3.png](../../assets/images/04-主流框架/spring/41.spring-springframework-mvc-3.png)

Demo中core container中包使用如下
![42.spring-springframework-mvc-2.png](../../assets/images/04-主流框架/spring/42.spring-springframework-mvc-2.png)

那么问题是，我们如何更好的构建上层的应用呢？比如web 应用？
![43.spring-springframework-mvc-1.png](../../assets/images/04-主流框架/spring/43.spring-springframework-mvc-1.png)

针对上层的Web应用，SpringMVC诞生了，它也是Spring技术栈中最为重要的一个框架。

**所以为了更好的帮助你串联整个知识体系，我列出了几个问题，通过如下几个问题帮你深入浅出的构建对SpringMVC的认知。**

- Java技术栈的Web应用是如何发展的？
- 什么是MVC，什么是SpringMVC？
- SpringMVC主要的请求流程是什么样的？
- SpringMVC中还有哪些组件？
- 如何编写一个简单的SpringMVC程序呢？
## 6.2 什么是MVC
> MVC英文是Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写，一种软件设计规范。本质上也是一种解耦。

用一种业务逻辑、数据、界面显示分离的方法，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。MVC被独特的发展起来用于映射传统的输入、处理和输出功能在一个逻辑的图形化用户界面的结构中。
![44.spring-springframework-mvc-4.png](../../assets/images/04-主流框架/spring/44.spring-springframework-mvc-4.png)

- `Model（模型）`是应用程序中用于处理应用程序数据逻辑的部分。通常模型对象负责在数据库中存取数据。
- `View（视图）`是应用程序中处理数据显示的部分。通常视图是依据模型数据创建的。
- `Controller（控制器）`是应用程序中处理用户交互的部分。通常控制器负责从视图读取数据，控制用户输入，并向模型发送数据。
## 6.3 什么是Spring MVC
> 简单而言，Spring MVC是Spring在Spring Container Core和AOP等技术基础上，遵循上述Web MVC的规范推出的web开发框架，目的是为了简化Java栈的web开发。

Spring Web MVC 是一种基于Java 的实现了Web MVC 设计模式的请求驱动类型的轻量级Web 框架，即使用了MVC 架构模式的思想，将 web 层进行职责解耦，基于请求驱动指的就是使用请求-响应模型，框架的目的就是帮助我们简化开发，Spring Web MVC 也是要简化我们日常Web开发的。

**相关特性如下：**
- 让我们能非常简单的设计出干净的Web 层和薄薄的Web 层；
- 进行更简洁的Web 层的开发；
- 天生与Spring 框架集成（如IoC 容器、AOP 等）；
- 提供强大的约定大于配置的契约式编程支持；
- 能简单的进行Web 层的单元测试；
- 支持灵活的URL 到页面控制器的映射；
- 非常容易与其他视图技术集成，如 Velocity、FreeMarker 等等，因为模型数据不放在特定的 API 里，而是放在一个 Model 里（Map 数据结构实现，因此很容易被其他框架使用）；
- 非常灵活的数据验证、格式化和数据绑定机制，能使用任何对象进行数据绑定，不必实现特定框架的API；
- 提供一套强大的JSP 标签库，简化JSP 开发；
- 支持灵活的本地化、主题等解析；
- 更加简单的异常处理；
- 对静态资源的支持；
- 支持Restful 风格。
## 6.4 Spring MVC的请求流程
> Spring Web MVC 框架也是一个基于请求驱动的Web 框架，并且也使用了前端控制器模式来进行设计，再根据请求映射 规则分发给相应的页面控制器（动作/处理器）进行处理。
### 6.4.1 核心架构的具体流程步骤
> 首先让我们整体看一下Spring Web MVC 处理请求的流程：
![45.spring-springframework-mvc-5.png](../../assets/images/04-主流框架/spring/45.spring-springframework-mvc-5.png)

**核心架构的具体流程步骤**如下：
1. `首先用户发送请求——>DispatcherServlet`，前端控制器收到请求后自己不进行处理，而是委托给其他的解析器进行 处理，作为统一访问点，进行全局的流程控制；
2. `DispatcherServlet——>HandlerMapping`， HandlerMapping 将会把请求映射为 HandlerExecutionChain 对象（包含一 个Handler 处理器（页面控制器）对象、多个HandlerInterceptor 拦截器）对象，通过这种策略模式，很容易添加新 的映射策略；
3. `DispatcherServlet——>HandlerAdapter`，HandlerAdapter 将会把处理器包装为适配器，从而支持多种类型的处理器， 即适配器设计模式的应用，从而很容易支持很多类型的处理器；
4. `HandlerAdapter——>处理器功能处理方法的调用`，HandlerAdapter 将会根据适配的结果调用真正的处理器的功能处 理方法，完成功能处理；并返回一个ModelAndView 对象（包含模型数据、逻辑视图名）；
5. `ModelAndView 的逻辑视图名`——> ViewResolver，ViewResolver 将把逻辑视图名解析为具体的View，通过这种策 略模式，很容易更换其他视图技术；
6. `View——>渲染`，View 会根据传进来的Model 模型数据进行渲染，此处的Model 实际是一个Map 数据结构，因此 很容易支持其他视图技术；
7. `返回控制权给DispatcherServlet`，由DispatcherServlet 返回响应给用户，到此一个流程结束。
### 6.4.2 对上述流程的补充
> 上述流程只是核心流程，这里我们再补充一些其它组件：
#### 6.4.2.1 Filter(ServletFilter)
进入Servlet前可以有preFilter, Servlet处理之后还可有postFilter
![46.spring-springframework-mvc-8.png](../../assets/images/04-主流框架/spring/46.spring-springframework-mvc-8.png)
#### 6.4.2.2 LocaleResolver
在视图解析/渲染时，还需要考虑国际化(Local)，显然这里需要有LocaleResolver.
![47.spring-springframework-mvc-6.png](../../assets/images/04-主流框架/spring/47.spring-springframework-mvc-6.png)
#### 6.4.2.3 ThemeResolver
如何控制视图样式呢？SpringMVC中还设计了ThemeSource接口和ThemeResolver，包含一些静态资源的集合(样式及图片等)，用来控制应用的视觉风格。
![48.spring-springframework-mvc-9.png](../../assets/images/04-主流框架/spring/48.spring-springframework-mvc-9.png)
#### 6.4.2.4 对于文件的上传请求？(MultipartResolver)
对于常规请求上述流程是合理的，但是如果是文件的上传请求，那么就不太一样了；所以这里便出现了MultipartResolver。
![49.spring-springframework-mvc-7.png](../../assets/images/04-主流框架/spring/49.spring-springframework-mvc-7.png)
### 6.4.3 Spring MVC案例
> 这里主要向你展示一个基本的SpringMVC例子，后文中将通过以Debug的方式分析源码。

本例子中主要文件和结构如下：
![50.spring-springframework-mvc-14.png](../../assets/images/04-主流框架/spring/50.spring-springframework-mvc-14.png)
#### 6.4.3.1 Maven包引入
主要引入spring-webmvc包（spring-webmvc包中已经包含了Spring Core Container相关的包），以及servlet和jstl（JSP中使用jstl）的包。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>tech-pdai-spring-demos</artifactId>
        <groupId>tech.pdai</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>011-spring-framework-demo-springmvc</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <spring.version>5.3.9</spring.version>
        <servlet.version>4.0.1</servlet.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>${servlet.version}</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>
    </dependencies>

</project>
```
#### 6.4.3.2 业务代码的编写
- User实体
```java
package tech.pdai.springframework.springmvc.entity;

/**
 * @author pdai
 */
public class User {

    /**
     * user's name.
     */
    private String name;

    /**
     * user's age.
     */
    private int age;

    /**
     * init.
     *
     * @param name name
     * @param age  age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```
- Dao
```java
package tech.pdai.springframework.springmvc.dao;

import org.springframework.stereotype.Repository;
import tech.pdai.springframework.springmvc.entity.User;

import java.util.Collections;
import java.util.List;

/**
 * @author pdai
 */
@Repository
public class UserDaoImpl {

    /**
     * mocked to find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return Collections.singletonList(new User("pdai", 18));
    }
}
```
- Service
```java
package tech.pdai.springframework.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.pdai.springframework.springmvc.dao.UserDaoImpl;
import tech.pdai.springframework.springmvc.entity.User;

import java.util.List;

/**
 * @author pdai
 */
@Service
public class UserServiceImpl {

    /**
     * user dao impl.
     */
    @Autowired
    private UserDaoImpl userDao;

    /**
     * find user list.
     *
     * @return user list
     */
    public List<User> findUserList() {
        return userDao.findUserList();
    }

}
```
- Controller
```java
package tech.pdai.springframework.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.pdai.springframework.springmvc.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * User Controller.
 *
 * @author pdai
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * find user list.
     *
     * @param request  request
     * @param response response
     * @return model and view
     */
    @RequestMapping("/user")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dateTime", new Date());
        modelAndView.addObject("userList", userService.findUserList());
        modelAndView.setViewName("userList"); // views目录下userList.jsp
        return modelAndView;
    }
}
```
#### 6.4.3.3 webapp下的web.xml
（创建上图的文件结构）

webapp下的web.xml如下：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <display-name>SpringFramework - SpringMVC Demo @pdai</display-name>

    <servlet>
        <servlet-name>springmvc-demo</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 通过初始化参数指定SpringMVC配置文件的位置和名称 -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc-demo</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```
#### 6.4.3.4 springmvc.xml
web.xml中我们配置初始化参数contextConfigLocation，路径是classpath:springmvc.xml
```xml
<init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:springmvc.xml</param-value>
</init-param>
```
在resources目录下创建
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!-- 扫描注解 -->
    <context:component-scan base-package="tech.pdai.springframework.springmvc"/>

    <!-- 静态资源处理 -->
    <mvc:default-servlet-handler/>

    <!-- 开启注解 -->
    <mvc:annotation-driven/>

    <!-- 视图解析器 -->
    <bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```
#### 6.4.3.5 JSP视图
- 创建userList.jsp
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>User List</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

</head>
<body>
    <div class="container">
        <c:if test="${!empty userList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                </tr>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
```
#### 6.4.3.6 部署测试
> 我们通过IDEA的tomcat插件来进行测试

- 下载Tomcat：<a href = 'https://downloads.apache.org/tomcat/'>tomcat地址</a>

- 下载后给tomcat/bin执行文件赋权
```sh
pdai@MacBook-Pro pdai % cd apache-tomcat-9.0.62 
pdai@MacBook-Pro apache-tomcat-9.0.62 % cd bin 
pdai@MacBook-Pro bin % ls
bootstrap.jar			makebase.sh
catalina-tasks.xml		setclasspath.bat
catalina.bat			setclasspath.sh
catalina.sh			shutdown.bat
ciphers.bat			shutdown.sh
ciphers.sh			startup.bat
commons-daemon-native.tar.gz	startup.sh
commons-daemon.jar		tomcat-juli.jar
configtest.bat			tomcat-native.tar.gz
configtest.sh			tool-wrapper.bat
daemon.sh			tool-wrapper.sh
digest.bat			version.bat
digest.sh			version.sh
makebase.bat
pdai@MacBook-Pro bin % chmod 777 *.sh
pdai@MacBook-Pro bin % 
```
- 配置Run Congfiuration
![51.spring-springframework-mvc-15.png](../../assets/images/04-主流框架/spring/51.spring-springframework-mvc-15.png)
- 添加Tomcat Server - Local
![52.spring-springframework-mvc-16.png](../../assets/images/04-主流框架/spring/52.spring-springframework-mvc-16.png)
- 将我们下载的Tomcat和Tomcat Server - Local关联
![53.spring-springframework-mvc-17.png](../../assets/images/04-主流框架/spring/53.spring-springframework-mvc-17.png)
- 在Deploy中添加我们的项目
![54.spring-springframework-mvc-18.png](../../assets/images/04-主流框架/spring/54.spring-springframework-mvc-18.png)
- 运行和管理Tomcat Sever（注意context路径）
![55.spring-springframework-mvc-19.png](../../assets/images/04-主流框架/spring/55.spring-springframework-mvc-19.png)
- 运行后访问我们的web程序页面（注意context路径）
![56.spring-springframework-mvc-20.png](../../assets/images/04-主流框架/spring/56.spring-springframework-mvc-20.png)


全面学习springMVC参考<a href='https://www.pdai.tech/files/kaitao-springMVC.pdf'>跟开涛学 SpringMVC</a>
# 七、Spring进阶- Spring IOC实现原理详解之IOC体系结构设计
## 7.1 站在设计者的角度考虑设计IOC容器
> 如果让你来设计一个IoC容器，你会怎么设计？我们初步的通过这个问题，来帮助我们更好的理解IOC的设计。

在设计时，首先需要考虑的是IOC容器的功能（输入和输出）, 承接前面的文章，我们初步的画出IOC容器的整体功能。
![57.spring-framework-ioc-source-7.png](../../assets/images/04-主流框架/spring/57.spring-framework-ioc-source-7.png)

在此基础上，我们初步的去思考，如果作为一个IOC容器的设计者，主体上应该包含哪几个部分：

- 加载Bean的配置（比如xml配置） 
  - 比如不同类型资源的加载，解析成生成统一Bean的定义
- 根据Bean的定义加载生成Bean的实例，并放置在Bean容器中 
  - 比如Bean的依赖注入，Bean的嵌套，Bean存放（缓存）等
- 除了基础Bean外，还有常规针对企业级业务的特别Bean 
  - 比如国际化Message，事件Event等生成特殊的类结构去支撑
- 对容器中的Bean提供统一的管理和调用 
  - 比如用工厂模式管理，提供方法根据名字/类的类型等从容器中获取Bean
- ...
## 7.2 Spring IoC的体系结构设计
> 那么我们来看下Spring设计者是如何设计IoC并解决这些问题的。
### 7.2.1 BeanFactory和BeanRegistry：IOC容器功能规范和Bean的注册
> Spring Bean的创建是典型的工厂模式，这一系列的Bean工厂，也即IOC容器为开发者管理对象间的依赖关系提供了很多便利和基础服务，在Spring中有许多的IOC容器的实现供用户选择和使用，这是IOC容器的基础；在顶层的结构设计主要围绕着BeanFactory和xxxRegistry进行：
> - BeanFactory： 工厂模式定义了IOC容器的基本功能规范
> - BeanRegistry： 向IOC容器手工注册 BeanDefinition 对象的方法

其相互关系如下：
![58.spring-framework-ioc-source-2.png](../../assets/images/04-主流框架/spring/58.spring-framework-ioc-source-2.png)

我们再通过几个问题来辅助理解。

#### 7.2.1.1 BeanFactory定义了IOC 容器基本功能规范？

**BeanFactory作为最顶层的一个接口类，它定义了IOC容器的基本功能规范**，BeanFactory 有三个子类：ListableBeanFactory、HierarchicalBeanFactory 和AutowireCapableBeanFactory。我们看下BeanFactory接口：
```java
public interface BeanFactory {    
      
    //用于取消引用实例并将其与FactoryBean创建的bean区分开来。例如，如果命名的bean是FactoryBean，则获取将返回Factory，而不是Factory返回的实例。
    String FACTORY_BEAN_PREFIX = "&"; 
        
    //根据bean的名字和Class类型等来得到bean实例    
    Object getBean(String name) throws BeansException;    
    Object getBean(String name, Class requiredType) throws BeansException;    
    Object getBean(String name, Object... args) throws BeansException;
    <T> T getBean(Class<T> requiredType) throws BeansException;
    <T> T getBean(Class<T> requiredType, Object... args) throws BeansException;

    //返回指定bean的Provider
    <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType);
    <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType);

    //检查工厂中是否包含给定name的bean，或者外部注册的bean
    boolean containsBean(String name);

    //检查所给定name的bean是否为单例/原型
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;
    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

    //判断所给name的类型与type是否匹配
    boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;
    boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException;

    //获取给定name的bean的类型
    @Nullable
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;

    //返回给定name的bean的别名
    String[] getAliases(String name);
     
}
```
#### 7.2.1.2 BeanFactory为何要定义这么多层次的接口？定义了哪些接口？
主要是为了**区分在 Spring 内部在操作过程中对象的传递和转化过程中，对对象的数据访问所做的限制。**
- `ListableBeanFactory`：该接口定义了访问容器中 Bean 基本信息的若干方法，如查看Bean 的个数、获取某一类型 Bean 的配置名、查看容器中是否包括某一 Bean 等方法；
- `HierarchicalBeanFactory`：父子级联 IoC 容器的接口，子容器可以通过接口方法访问父容器； 通过 HierarchicalBeanFactory 接口， Spring 的 IoC 容器可以建立父子层级关联的容器体系，子容器可以访问父容器中的 Bean，但父容器不能访问子容器的 Bean。Spring 使用父子容器实现了很多功能，比如在 Spring MVC 中，展现层 Bean 位于一个子容器中，而业务层和持久层的 Bean 位于父容器中。这样，展现层 Bean 就可以引用业务层和持久层的 Bean，而业务层和持久层的 Bean 则看不到展现层的 Bean。
- `ConfigurableBeanFactory`：是一个重要的接口，增强了 IoC 容器的可定制性，它定义了设置类装载器、属性编辑器、容器初始化后置处理器等方法；
- `ConfigurableListableBeanFactory`: ListableBeanFactory 和 ConfigurableBeanFactory的融合；
- `AutowireCapableBeanFactory`：定义了将容器中的 Bean 按某种规则（如按名字匹配、按类型匹配等）进行自动装配的方法；
#### 7.2.1.3 如何将Bean注册到BeanFactory中？BeanRegistry
Spring 配置文件中每一个`<bean>`节点元素在 Spring 容器里都通过一个 `BeanDefinition` 对象表示，它描述了 Bean 的配置信息。而 `BeanDefinitionRegistry` 接口提供了向容器手工注册 `BeanDefinition`对象的方法。
### 7.2.2 BeanDefinition：各种Bean对象及其相互的关系
> Bean对象存在依赖嵌套等关系，所以设计者设计了BeanDefinition，它用来对Bean对象及关系定义；我们在理解时只需要抓住如下三个要点：
> - BeanDefinition 定义了各种Bean对象及其相互的关系
> - BeanDefinitionReader 这是BeanDefinition的解析器
> - BeanDefinitionHolder 这是BeanDefination的包装类，用来存储BeanDefinition，name以及aliases等。
#### 7.2.2.1 BeanDefinition

SpringIOC容器管理了我们定义的各种Bean对象及其相互的关系，Bean对象在Spring实现中是以BeanDefinition来描述的，其继承体系如下
![59.spring-framework-ioc-source-3.png](../../assets/images/04-主流框架/spring/59.spring-framework-ioc-source-3.png)
#### 7.2.2.2 BeanDefinitionReader

Bean 的解析过程非常复杂，功能被分的很细，因为这里需要被扩展的地方很多，必须保证有足够的灵活性，以应对可能的变化。Bean 的解析主要就是对 Spring 配置文件的解析。这个解析过程主要通过下图中的类完成：
![60.spring-framework-ioc-source-5.png](../../assets/images/04-主流框架/spring/60.spring-framework-ioc-source-5.png)
#### 7.2.2.3 BeanDefinitionHolder

BeanDefinitionHolder 这是BeanDefination的包装类，用来存储BeanDefinition，name以及aliases等
![61.spring-framework-ioc-source-4.png](../../assets/images/04-主流框架/spring/61.spring-framework-ioc-source-4.png)
## 7.3 ApplicationContext：IOC接口设计和实现
> IoC容器的接口类是ApplicationContext，很显然它必然继承BeanFactory对Bean规范（最基本的ioc容器的实现）进行定义。而ApplicationContext表示的是应用的上下文，除了对Bean的管理外，还至少应该包含了
> - **访问资源**： 对不同方式的Bean配置（即资源）进行加载。(实现ResourcePatternResolver接口)
> - **国际化**: 支持信息源，可以实现国际化。（实现MessageSource接口）
> - **应用事件**: 支持应用事件。(实现ApplicationEventPublisher接口)
### 7.3.1 ApplicationContext接口的设计
我们来看下ApplicationContext整体结构
![62.spring-framework-ioc-source-51.png](../../assets/images/04-主流框架/spring/62.spring-framework-ioc-source-51.png)
- `HierarchicalBeanFactory 和 ListableBeanFactory`： ApplicationContext 继承了 HierarchicalBeanFactory 和 ListableBeanFactory 接口，在此基础上，还通过多个其他的接口扩展了 BeanFactory 的功能：
- `ApplicationEventPublisher`：让容器拥有发布应用上下文事件的功能，包括容器启动事件、关闭事件等。实现了 ApplicationListener 事件监听接口的 Bean 可以接收到容器事件 ， 并对事件进行响应处理 。 在 ApplicationContext 抽象实现类AbstractApplicationContext 中，我们可以发现存在一个 ApplicationEventMulticaster，它负责保存所有监听器，以便在容器产生上下文事件时通知这些事件监听者。
- `MessageSource`：为应用提供 i18n 国际化消息访问的功能；
- `ResourcePatternResolver` ： 所 有 ApplicationContext 实现类都实现了类似于PathMatchingResourcePatternResolver 的功能，可以通过带前缀的 Ant 风格的资源文件路径装载 Spring 的配置文件。
- `LifeCycle`：该接口是 Spring 2.0 加入的，该接口提供了 start()和 stop()两个方法，主要用于控制异步处理过程。在具体使用时，该接口同时被 ApplicationContext 实现及具体 Bean 实现， ApplicationContext 会将 start/stop 的信息传递给容器中所有实现了该接口的 Bean，以达到管理和控制 JMX、任务调度等目的。
### 7.3.2 ApplicationContext接口的实现
在考虑ApplicationContext接口的实现时，关键的点在于，不同Bean的配置方式（比如xml,groovy,annotation等）有着不同的资源加载方式，这便衍生除了众多ApplicationContext的实现类。
![63.spring-framework-ioc-source-61.png](../../assets/images/04-主流框架/spring/63.spring-framework-ioc-source-61.png)

- 第一，从类结构设计上看， 围绕着是否需要Refresh容器衍生出两个抽象类：
  - GenericApplicationContext： 是初始化的时候就创建容器，往后的每次refresh都不会更改
  - AbstractRefreshableApplicationContext： AbstractRefreshableApplicationContext及子类的每次refresh都是先清除已有(如果不存在就创建)的容器，然后再重新创建；AbstractRefreshableApplicationContext及子类无法做到GenericApplicationContext**混合搭配从不同源头获取bean的定义信息**
- 第二， 从加载的源来看（比如xml,groovy,annotation等）， 衍生出众多类型的ApplicationContext, 典型比如:
  - `FileSystemXmlApplicationContext`： 从文件系统下的一个或多个xml配置文件中加载上下文定义，也就是说系统盘符中加载xml配置文件。
  - `ClassPathXmlApplicationContext`： 从类路径下的一个或多个xml配置文件中加载上下文定义，适用于xml配置的方式。
  - `AnnotationConfigApplicationContext`： 从一个或多个基于java的配置类中加载上下文定义，适用于java注解的方式。
  - `ConfigurableApplicationContext`： 扩展于 ApplicationContext，它新增加了两个主要的方法： refresh()和 close()，让 ApplicationContext 具有启动、刷新和关闭应用上下文的能力。在应用上下文关闭的情况下调用 refresh()即可启动应用上下文，在已经启动的状态下，调用 refresh()则清除缓存并重新装载配置信息，而调用close()则可关闭应用上下文。这些接口方法为容器的控制管理带来了便利，但作为开发者，我们并不需要过多关心这些方法。
- 第三， 更进一步理解：
  - *计者在设计时`AnnotationConfigApplicationContext`为什么是继承GenericApplicationContext？* 因为基于注解的配置，是不太会被运行时修改的，这意味着不需要进行动态Bean配置和刷新容器，所以只需要GenericApplicationContext。
  - 而基于XML这种配置文件，这种文件是容易修改的，需要动态性刷新Bean的支持，所以XML相关的配置必然继承AbstractRefreshableApplicationContext； 且存在多种xml的加载方式（位置不同的设计），所以必然会设计出AbstractXmlApplicationContext, 其中包含对XML配置解析成BeanDefination的过程。
  - 那么细心的你从上图可以发现`AnnotationWebConfigApplicationContext`却是继承了AbstractRefreshableApplicationContext而不是GenericApplicationContext， *为什么AnnotationWebConfigApplicationContext继承自AbstractRefreshableApplicationContext呢 ？* 因为用户可以通过ApplicationContextInitializer来设置contextInitializerClasses（context-param / init-param）， 在这种情况下用户倾向于刷新Bean的，所以设计者选择让AnnotationWebConfigApplicationContext继承了AbstractRefreshableApplicationContext。（如下是源码中Spring设计者对它的解释）
```java
 * <p>As an alternative to setting the "contextConfigLocation" parameter, users may
 * implement an {@link org.springframework.context.ApplicationContextInitializer
 * ApplicationContextInitializer} and set the
 * {@linkplain ContextLoader#CONTEXT_INITIALIZER_CLASSES_PARAM "contextInitializerClasses"}
 * context-param / init-param. In such cases, users should favor the {@link #refresh()}
 * and {@link #scan(String...)} methods over the {@link #setConfigLocation(String)}
 * method, which is primarily for use by {@code ContextLoader}.
```



| 比较方面 | AnnotationConfigApplicationContext | AnnotationConfigWebApplicationContext |
|---------|-----------------------------------|--------------------------------------|
| **应用场景** | 独立的Java应用程序、桌面应用、单元测试等非Web环境 | Web应用程序（Servlet容器环境） |
| **继承关系** | 继承自 `GenericApplicationContext` | 继承自 `GenericWebApplicationContext`，间接继承自 `GenericApplicationContext` |
| **环境支持** | 标准Java环境 | Web环境，支持Servlet API、Web作用域等 |
| **配置方式** | 基于注解的配置类（@Configuration） | 基于注解的配置类（@Configuration），同时支持Web相关配置 |
| **作用域支持** | 支持单例（singleton）、原型（prototype）作用域 | 额外支持Web作用域：request、session、application |
| **初始化方式** | 直接通过构造函数注册配置类：<br>`new AnnotationConfigApplicationContext(AppConfig.class)` | 可通过web.xml配置或编程方式初始化，支持上下文参数 |
| **典型使用场景** | Spring Boot命令行应用、单元测试、桌面应用 | Spring MVC Web应用、Spring Boot Web应用 |
| **依赖** | 只需要Spring核心容器 | 需要Spring Web模块（spring-web） |
| **配置文件支持** | 主要支持Java配置类，也可结合Properties文件 | 支持Java配置类，同时可读取web.xml参数、初始化参数等 |

我们把之前的设计要点和设计结构结合起来看：
![64.spring-framework-ioc-source-71.png](../../assets/images/04-主流框架/spring/64.spring-framework-ioc-source-71.png)

到此，基本可以帮助你**从顶层构建对IoC容器的设计理解，而不是过早沉溺于代码的细节;**
# 八、Spring进阶- Spring IOC实现原理详解之IOC初始化流程
## 8.1 引入
上文，我们看了IOC设计要点和设计结构；紧接着这篇，我们可以看下源码的实现了：Spring如何实现将资源配置（以xml配置为例）通过加载，解析，生成BeanDefination并注册到IoC容器中的（就是我们圈出来的部分）
![65.spring-framework-ioc-source-73.png](../../assets/images/04-主流框架/spring/65.spring-framework-ioc-source-73.png)
## 8.2 如何将Bean从XML配置中解析后放到IoC容器中的？
> 本文的目标就是分析Spring如何实现将资源配置（以xml配置为例）通过加载，解析，生成BeanDefination并注册到IoC容器中的。
### 8.2.1 初始化的入口
对于xml配置的Spring应用，在main()方法中实例化ClasspathXmlApplicationContext即可创建一个IoC容器。我们可以从这个构造方法开始，探究一下IoC容器的初始化过程。
```java
 // create and configure beans
ApplicationContext context = new ClassPathXmlApplicationContext("aspects.xml", "daos.xml", "services.xml");
```
```java
public ClassPathXmlApplicationContext(String... configLocations) throws BeansException {
    this(configLocations, true, (ApplicationContext)null);
}

public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, @Nullable ApplicationContext parent) throws BeansException {
    // 设置Bean资源加载器
    super(parent);

    // 设置配置路径
    this.setConfigLocations(configLocations);

    // 初始化容器
    if (refresh) {
        this.refresh();
    }
}
```
### 8.2.2 设置资源解析器和环境
调用父类容器AbstractApplicationContext的构造方法`(super(parent)方法)`为容器设置好Bean资源加载器
```java
public AbstractApplicationContext(@Nullable ApplicationContext parent) {
    // 默认构造函数初始化容器id, name, 状态 以及 资源解析器
    this();

    // 将父容器的Environment合并到当前容器
    this.setParent(parent);
}
```
通过AbstractApplicationContext默认构造函数初始化容器id, name, 状态 以及 资源解析器
```java
public AbstractApplicationContext() {
    this.logger = LogFactory.getLog(this.getClass());
    this.id = ObjectUtils.identityToString(this);
    this.displayName = ObjectUtils.identityToString(this);
    this.beanFactoryPostProcessors = new ArrayList();
    this.active = new AtomicBoolean();
    this.closed = new AtomicBoolean();
    this.startupShutdownMonitor = new Object();
    this.applicationStartup = ApplicationStartup.DEFAULT;
    this.applicationListeners = new LinkedHashSet();
    this.resourcePatternResolver = this.getResourcePatternResolver();
}
// Spring资源加载器
protected ResourcePatternResolver getResourcePatternResolver() {
    return new PathMatchingResourcePatternResolver(this);
}
```
通过AbstractApplicationContext的setParent(parent)方法将父容器的Environment合并到当前容器
```java
public void setParent(@Nullable ApplicationContext parent) {
    this.parent = parent;
    if (parent != null) {
        Environment parentEnvironment = parent.getEnvironment();
        if (parentEnvironment instanceof ConfigurableEnvironment) {
            this.getEnvironment().merge((ConfigurableEnvironment)parentEnvironment);
        }
    }
}
```
### 8.2.3 设置配置路径
在设置容器的资源加载器之后，接下来ClassPathXmlApplicationContext执行setConfigLocations方法通过调用其父类AbstractRefreshableConfigApplicationContext的方法进行对Bean定义资源文件的定位
```java
public void setConfigLocations(@Nullable String... locations) {
    if (locations != null) {
        Assert.noNullElements(locations, "Config locations must not be null");
        this.configLocations = new String[locations.length];

        for(int i = 0; i < locations.length; ++i) {
            // 解析配置路径
            this.configLocations[i] = this.resolvePath(locations[i]).trim();
        }
    } else {
        this.configLocations = null;
    }
}
protected String resolvePath(String path) {
    // 从上一步Environment中解析
    return this.getEnvironment().resolveRequiredPlaceholders(path);
}
```
### 8.2.4 初始化的主体流程
Spring IoC容器对Bean定义资源的载入是从refresh()函数开始的，`refresh()是一个模板方法`，refresh()方法的作用是：在创建IoC容器前，如果已经有容器存在，则需要把已有的容器销毁和关闭，以保证在refresh之后使用的是新建立起来的IoC容器。refresh的作用类似于对IoC容器的重启，在新建立好的容器中对容器进行初始化，对Bean定义资源进行载入。

首先需要明白什么是`模板方法:(“骨架不变，细节可变”)`：

在软件设计中，**模板方法模式（Template Method Pattern）** 是一种行为设计模式，它定义了一个操作中的算法骨架，而将一些步骤延迟到子类中实现。这样，子类可以在不改变算法结构的情况下重新定义算法的某些特定步骤。

- 关键特点：
  - **算法骨架固定**：父类（或抽象类）中定义一个模板方法，该方法包含了一系列步骤（这些步骤可以是抽象的或具有默认实现）。
  - **步骤可定制**：子类通过覆盖某些步骤方法来改变具体行为，但整个算法的流程由父类控制。
  - **避免代码重复**：通过将通用流程放在父类中，子类只需关注差异部分。

- 以 Spring 中的 `refresh()` 方法为例：
在 Spring 框架中，`AbstractApplicationContext` 类的 `refresh()` 方法就是一个典型的模板方法。它定义了 Spring 容器启动或刷新的标准流程，包括以下步骤：
1. **准备刷新**（`prepareRefresh()`）
2. **获取 Bean 工厂**（`obtainFreshBeanFactory()`）
3. **准备 Bean 工厂**（`prepareBeanFactory()`）
4. **后处理 Bean 工厂**（`postProcessBeanFactory()`）
5. **调用 Bean 工厂后处理器**（`invokeBeanFactoryPostProcessors()`）
6. **注册 Bean 后处理器**（`registerBeanPostProcessors()`）
7. **初始化消息源**（`initMessageSource()`）
8. **初始化事件广播器**（`initApplicationEventMulticaster()`）
9. **初始化其他特殊 Bean**（`onRefresh()`）
10. **注册事件监听器**（`registerListeners()`）
11. **完成 Bean 工厂初始化**（`finishBeanFactoryInitialization()`）
12. **完成刷新**（`finishRefresh()`）

这些步骤中，有些是抽象方法（需要子类实现），有些有默认实现。子类（如 `AnnotationConfigApplicationContext`）可以覆盖特定步骤来定制行为，但整体刷新流程由父类固定。


```java
@Override
public void refresh() throws BeansException, IllegalStateException {
    synchronized (this.startupShutdownMonitor) {
        StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");

        // Prepare this context for refreshing.
        prepareRefresh();

        // Tell the subclass to refresh the internal bean factory.
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

        // Prepare the bean factory for use in this context.
        prepareBeanFactory(beanFactory);

        try {
            // Allows post-processing of the bean factory in context subclasses.
            postProcessBeanFactory(beanFactory);

            StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
            // Invoke factory processors registered as beans in the context.
            invokeBeanFactoryPostProcessors(beanFactory);

            // Register bean processors that intercept bean creation.
            registerBeanPostProcessors(beanFactory);
            beanPostProcess.end();

            // Initialize message source for this context.
            initMessageSource();

            // Initialize event multicaster for this context.
            initApplicationEventMulticaster();

            // Initialize other special beans in specific context subclasses.
            onRefresh();

            // Check for listener beans and register them.
            registerListeners();

            // Instantiate all remaining (non-lazy-init) singletons.
            finishBeanFactoryInitialization(beanFactory);

            // Last step: publish corresponding event.
            finishRefresh();
        }

        catch (BeansException ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("Exception encountered during context initialization - " +
                        "cancelling refresh attempt: " + ex);
            }

            // Destroy already created singletons to avoid dangling resources.
            destroyBeans();

            // Reset 'active' flag.
            cancelRefresh(ex);

            // Propagate exception to caller.
            throw ex;
        }

        finally {
            // Reset common introspection caches in Spring's core, since we
            // might not ever need metadata for singleton beans anymore...
            resetCommonCaches();
            contextRefresh.end();
        }
    }
}
```
这里的设计上是一个非常典型的资源类加载处理型的思路，头脑中需要形成如下图的顶层思路（而不是只停留在流水式的方法上面）：
- 模板方法设计模式，模板方法中使用典型的**钩子方法**
- 将**具体的初始化加载方法**插入到钩子方法之间
- 将初始化的阶段封装，用来记录当前初始化到什么阶段；常见的设计是xxxPhase/xxxStage；
- 资源加载初始化有失败等处理，必然是try/catch/finally...
![66.spring-framework-ioc-source-8.png](../../assets/images/04-主流框架/spring/66.spring-framework-ioc-source-8.png)
#### 8.2.4.1 初始化BeanFactory之obtainFreshBeanFactory
AbstractApplicationContext的obtainFreshBeanFactory()方法调用子类容器的refreshBeanFactory()方法，启动容器载入Bean定义资源文件的过程，代码如下：
```java
protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
    // 这里使用了委派设计模式，父类定义了抽象的refreshBeanFactory()方法，具体实现调用子类容器的refreshBeanFactory()方法
    refreshBeanFactory();
    return getBeanFactory();
}
```
AbstractApplicationContext类中只抽象定义了refreshBeanFactory()方法，容器真正调用的是其子类AbstractRefreshableApplicationContext实现的refreshBeanFactory()方法; 在创建IoC容器前，如果已经有容器存在，则需要把已有的容器销毁和关闭，以保证在refresh之后使用的是新建立起来的IoC容器。方法的源码如下：
```java
protected final void refreshBeanFactory() throws BeansException {
    // 如果已经有容器存在，则需要把已有的容器销毁和关闭，以保证在refresh之后使用的是新建立起来的IoC容器
    if (hasBeanFactory()) {
        destroyBeans();
        closeBeanFactory();
    }
    try {
        // 创建DefaultListableBeanFactory，并调用loadBeanDefinitions(beanFactory)装载bean定义
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        beanFactory.setSerializationId(getId());
        customizeBeanFactory(beanFactory); // 对IoC容器进行定制化，如设置启动参数，开启注解的自动装配等 
        loadBeanDefinitions(beanFactory); // 调用载入Bean定义的方法，主要这里又使用了一个委派模式，在当前类中只定义了抽象的loadBeanDefinitions方法，具体的实现调用子类容器  
        this.beanFactory = beanFactory;
    }
    catch (IOException ex) {
        throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
    }
}
```
#### 8.2.4.2 初始化BeanFactory之loadBeanDefinitions
AbstractRefreshableApplicationContext中只定义了抽象的loadBeanDefinitions方法，容器真正调用的是其子类AbstractXmlApplicationContext对该方法的实现，AbstractXmlApplicationContext的主要源码如下：
```java
protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
    // 创建XmlBeanDefinitionReader，即创建Bean读取器，并通过回调设置到容器中去，容器使用该读取器读取Bean定义资源  
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

    // 配置上下文的环境，资源加载器、解析器
    beanDefinitionReader.setEnvironment(this.getEnvironment());
    beanDefinitionReader.setResourceLoader(this);
    beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this)); // 为Bean读取器设置SAX xml解析器

    // 允许子类自行初始化（比如校验机制），并提供真正的加载方法
    initBeanDefinitionReader(beanDefinitionReader); // 当Bean读取器读取Bean定义的Xml资源文件时，启用Xml的校验机制  
    loadBeanDefinitions(beanDefinitionReader);
}

protected void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws BeansException, IOException {
    // 加载XML配置方式里的Bean定义的资源
    Resource[] configResources = getConfigResources();
    if (configResources != null) {
        reader.loadBeanDefinitions(configResources);
    }
    // 加载构造函数里配置的Bean配置文件，即{"aspects.xml", "daos.xml", "services.xml"}
    String[] configLocations = getConfigLocations();
    if (configLocations != null) {
        reader.loadBeanDefinitions(configLocations);
    }
}
```
Xml Bean读取器(XmlBeanDefinitionReader)调用其父类AbstractBeanDefinitionReader的 reader.loadBeanDefinitions方法读取Bean定义资源。

由于我们使用ClassPathXmlApplicationContext作为例子分析，因此getConfigResources的返回值为null，因此程序执行reader.loadBeanDefinitions(configLocations)分支。
#### 8.2.4.3 AbstractBeanDefinitionReader读取Bean定义资源
AbstractBeanDefinitionReader的loadBeanDefinitions方法源码如下：
```java
@Override
public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
    return loadBeanDefinitions(location, null);
}

public int loadBeanDefinitions(String location, @Nullable Set<Resource> actualResources) throws BeanDefinitionStoreException {
    ResourceLoader resourceLoader = getResourceLoader();
    if (resourceLoader == null) {
        throw new BeanDefinitionStoreException(
                "Cannot load bean definitions from location [" + location + "]: no ResourceLoader available");
    }

    // 模式匹配类型的解析器，这种方式是加载多个满足匹配条件的资源
    if (resourceLoader instanceof ResourcePatternResolver) {
        try {
            // 获取到要加载的资源
            Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location);
            int count = loadBeanDefinitions(resources); // 委派调用其子类XmlBeanDefinitionReader的方法，实现加载功能  
            if (actualResources != null) {
                Collections.addAll(actualResources, resources);
            }
            if (logger.isTraceEnabled()) {
                logger.trace("Loaded " + count + " bean definitions from location pattern [" + location + "]");
            }
            return count;
        }
        catch (IOException ex) {
            throw new BeanDefinitionStoreException(
                    "Could not resolve bean definition resource pattern [" + location + "]", ex);
        }
    }
    else {
        // 只能通过绝对路径URL加载单个资源.
        Resource resource = resourceLoader.getResource(location);
        int count = loadBeanDefinitions(resource);
        if (actualResources != null) {
            actualResources.add(resource);
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Loaded " + count + " bean definitions from location [" + location + "]");
        }
        return count;
    }
}
```
从对AbstractBeanDefinitionReader的loadBeanDefinitions方法源码分析可以看出该方法做了以下两件事：
- 首先，调用资源加载器的获取资源方法resourceLoader.getResource(location)，获取到要加载的资源。
- 其次，真正执行加载功能是其子类XmlBeanDefinitionReader的loadBeanDefinitions方法。
#### 8.2.4.4 XmlBeanDefinitionReader加载Bean定义资源
继续看子类XmlBeanDefinitionReader的loadBeanDefinitions(Resource …)方法看到代表bean文件的资源定义以后的载入过程。
```java
/**
    * 本质上是加载XML配置的Bean。
    * @param inputSource the SAX InputSource to read from
    * @param resource the resource descriptor for the XML file
    */
protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
        throws BeanDefinitionStoreException {

    try {
        Document doc = doLoadDocument(inputSource, resource); // 将Bean定义资源转换成Document对象
        int count = registerBeanDefinitions(doc, resource);
        if (logger.isDebugEnabled()) {
            logger.debug("Loaded " + count + " bean definitions from " + resource);
        }
        return count;
    }
    catch (BeanDefinitionStoreException ex) {
        throw ex;
    }
    catch (SAXParseException ex) {
        throw new XmlBeanDefinitionStoreException(resource.getDescription(),
                "Line " + ex.getLineNumber() + " in XML document from " + resource + " is invalid", ex);
    }
    catch (SAXException ex) {
        throw new XmlBeanDefinitionStoreException(resource.getDescription(),
                "XML document from " + resource + " is invalid", ex);
    }
    catch (ParserConfigurationException ex) {
        throw new BeanDefinitionStoreException(resource.getDescription(),
                "Parser configuration exception parsing XML from " + resource, ex);
    }
    catch (IOException ex) {
        throw new BeanDefinitionStoreException(resource.getDescription(),
                "IOException parsing XML document from " + resource, ex);
    }
    catch (Throwable ex) {
        throw new BeanDefinitionStoreException(resource.getDescription(),
                "Unexpected exception parsing XML document from " + resource, ex);
    }
}

// 使用配置的DocumentLoader加载XML定义文件为Document.
protected Document doLoadDocument(InputSource inputSource, Resource resource) throws Exception {
    return this.documentLoader.loadDocument(inputSource, getEntityResolver(), this.errorHandler,
            getValidationModeForResource(resource), isNamespaceAware());
}
```
通过源码分析，载入Bean定义资源文件的最后一步是将Bean定义资源转换为Document对象，该过程由documentLoader实现
#### 8.2.4.5 DocumentLoader将Bean定义资源转换为Document对象
DocumentLoader将Bean定义资源转换成Document对象的源码如下：
```java
// 使用标准的JAXP将载入的Bean定义资源转换成document对象
@Override
public Document loadDocument(InputSource inputSource, EntityResolver entityResolver,
        ErrorHandler errorHandler, int validationMode, boolean namespaceAware) throws Exception {

    // 创建文件解析器工厂
    DocumentBuilderFactory factory = createDocumentBuilderFactory(validationMode, namespaceAware);
    if (logger.isTraceEnabled()) {
        logger.trace("Using JAXP provider [" + factory.getClass().getName() + "]");
    }
    // 创建文档解析器
    DocumentBuilder builder = createDocumentBuilder(factory, entityResolver, errorHandler);
    return builder.parse(inputSource); // 解析
}

protected DocumentBuilderFactory createDocumentBuilderFactory(int validationMode, boolean namespaceAware)
        throws ParserConfigurationException {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(namespaceAware);

    // 设置解析XML的校验
    if (validationMode != XmlValidationModeDetector.VALIDATION_NONE) {
        factory.setValidating(true);
        if (validationMode == XmlValidationModeDetector.VALIDATION_XSD) {
            // Enforce namespace aware for XSD...
            factory.setNamespaceAware(true);
            try {
                factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
            }
            catch (IllegalArgumentException ex) {
                ParserConfigurationException pcex = new ParserConfigurationException(
                        "Unable to validate using XSD: Your JAXP provider [" + factory +
                        "] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? " +
                        "Upgrade to Apache Xerces (or Java 1.5) for full XSD support.");
                pcex.initCause(ex);
                throw pcex;
            }
        }
    }

    return factory;
}
```
该解析过程调用JavaEE标准的JAXP标准进行处理。

至此Spring IoC容器根据定位的Bean定义资源文件，将其加载读入并转换成为Document对象过程完成。

接下来我们要继续分析Spring IoC容器将载入的Bean定义资源文件转换为Document对象之后，是如何将其解析为Spring IoC管理的Bean对象并将其注册到容器中的。
#### 8.2.4.6 XmlBeanDefinitionReader解析载入的Bean定义资源文件
XmlBeanDefinitionReader类中的doLoadBeanDefinitions方法是从特定XML文件中实际载入Bean定义资源的方法，该方法在载入Bean定义资源之后将其转换为Document对象，接下来调用registerBeanDefinitions启动Spring IoC容器对Bean定义的解析过程，registerBeanDefinitions方法源码如下：
```java
// 按照Spring的Bean语义要求将Bean定义资源解析并转换为容器内部数据结构 
public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException {
    BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
    int countBefore = getRegistry().getBeanDefinitionCount();
    // 解析过程入口，这里使用了委派模式，具体的解析实现过程由实现类DefaultBeanDefinitionDocumentReader完成  
    documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
    return getRegistry().getBeanDefinitionCount() - countBefore;  // 返回此次解析了多少个对象
}

// 创建BeanDefinitionDocumentReader对象，解析Document对象  
protected BeanDefinitionDocumentReader createBeanDefinitionDocumentReader() {
    return BeanUtils.instantiateClass(this.documentReaderClass);
}

/**
    * Create the {@link XmlReaderContext} to pass over to the document reader.
    */
public XmlReaderContext createReaderContext(Resource resource) {
    return new XmlReaderContext(resource, this.problemReporter, this.eventListener,
            this.sourceExtractor, this, getNamespaceHandlerResolver());
}
```
Bean定义资源的载入解析分为以下两个过程：
- 首先，通过调用XML解析器将Bean定义资源文件转换得到Document对象，但是这些Document对象并没有按照Spring的Bean规则进行解析。这一步是载入的过程
- 其次，在完成通用的XML解析之后，按照Spring的Bean规则对Document对象进行解析。

按照Spring的Bean规则对Document对象解析的过程是在接口BeanDefinitionDocumentReader的实现类DefaultBeanDefinitionDocumentReader中实现的。
#### 8.2.4.7 DefaultBeanDefinitionDocumentReader对Bean定义的Document对象解析
BeanDefinitionDocumentReader接口通过registerBeanDefinitions方法调用其实现类DefaultBeanDefinitionDocumentReader对Document对象进行解析，解析的代码如下：
```java
@Override
public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext) {
    this.readerContext = readerContext;
    doRegisterBeanDefinitions(doc.getDocumentElement());
}

// 注册<beans/>配置的Beans
@SuppressWarnings("deprecation")  // for Environment.acceptsProfiles(String...)
protected void doRegisterBeanDefinitions(Element root) {
    // Any nested <beans> elements will cause recursion in this method. In
    // order to propagate and preserve <beans> default-* attributes correctly,
    // keep track of the current (parent) delegate, which may be null. Create
    // the new (child) delegate with a reference to the parent for fallback purposes,
    // then ultimately reset this.delegate back to its original (parent) reference.
    // this behavior emulates a stack of delegates without actually necessitating one.
    BeanDefinitionParserDelegate parent = this.delegate;
    this.delegate = createDelegate(getReaderContext(), root, parent);

    if (this.delegate.isDefaultNamespace(root)) {
        String profileSpec = root.getAttribute(PROFILE_ATTRIBUTE);
        if (StringUtils.hasText(profileSpec)) {
            String[] specifiedProfiles = StringUtils.tokenizeToStringArray(
                    profileSpec, BeanDefinitionParserDelegate.MULTI_VALUE_ATTRIBUTE_DELIMITERS);
            // We cannot use Profiles.of(...) since profile expressions are not supported
            // in XML config. See SPR-12458 for details.
            if (!getReaderContext().getEnvironment().acceptsProfiles(specifiedProfiles)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Skipped XML bean definition file due to specified profiles [" + profileSpec +
                            "] not matching: " + getReaderContext().getResource());
                }
                return;
            }
        }
    }

    preProcessXml(root);
    parseBeanDefinitions(root, this.delegate); // 从Document的根元素开始进行Bean定义的Document对象  
    postProcessXml(root);

    this.delegate = parent;
}
```
#### 8.2.4.8 BeanDefinitionParserDelegate解析Bean定义资源文件生成BeanDefinition
```java
/**
    * Parse the elements at the root level in the document:
    * "import", "alias", "bean".
    * @param root the DOM root element of the document
    */
protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
    if (delegate.isDefaultNamespace(root)) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                if (delegate.isDefaultNamespace(ele)) {
                    parseDefaultElement(ele, delegate);
                }
                else {
                    delegate.parseCustomElement(ele);
                }
            }
        }
    }
    else {
        delegate.parseCustomElement(root);
    }
}

private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
      
    // 如果元素节点是<Import>导入元素，进行导入解析
    if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) {
        importBeanDefinitionResource(ele);
    }
    // 如果元素节点是<Alias>别名元素，进行别名解析 
    else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) {
        processAliasRegistration(ele);
    }
    // 如果元素节点<Bean>元素, 按照Spring的Bean规则解析元素  
    else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
        processBeanDefinition(ele, delegate);
    }
    // 如果元素节点<Beans>元素，即它是嵌套类型的
    else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) {
        // 递归解析
        doRegisterBeanDefinitions(ele);
    }
}
```
解析Bean生成BeanDefinitionHolder的方法
```java
/**
    * Process the given bean element, parsing the bean definition
    * and registering it with the registry.
    */
protected void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {
    BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
    if (bdHolder != null) {
        bdHolder = delegate.decorateBeanDefinitionIfRequired(ele, bdHolder);
        try {
            // 注册最终的装饰实例
            BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry());
        }
        catch (BeanDefinitionStoreException ex) {
            getReaderContext().error("Failed to register bean definition with name '" +
                    bdHolder.getBeanName() + "'", ele, ex);
        }
        // Send registration event.
        getReaderContext().fireComponentRegistered(new BeanComponentDefinition(bdHolder));
    }
}
```
（这里就不展开了，无非就是解析XML各种元素，来生成BeanDefinition）
#### 8.2.4.9 解析过后的BeanDefinition在IoC容器中的注册
Document对象的解析后得到封装BeanDefinition的BeanDefinitionHolder对象，然后调用BeanDefinitionReaderUtils的registerBeanDefinition方法向IoC容器注册解析的Bean，BeanDefinitionReaderUtils的注册的源码如下：
```java
// 通过BeanDefinitionRegistry将BeanDefinitionHolder注册到BeanFactory
public static void registerBeanDefinition(
        BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry)
        throws BeanDefinitionStoreException {

    // Register bean definition under primary name.
    String beanName = definitionHolder.getBeanName();
    registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());

    // Register aliases for bean name, if any.
    String[] aliases = definitionHolder.getAliases();
    if (aliases != null) {
        for (String alias : aliases) {
            registry.registerAlias(beanName, alias);
        }
    }
}
```
当调用BeanDefinitionReaderUtils向IoC容器注册解析的BeanDefinition时，真正完成注册功能的是DefaultListableBeanFactory。
#### 8.2.4.9 DefaultListableBeanFactory向IoC容器注册解析后的BeanDefinition
IOC容器本质上就是一个beanDefinitionMap， 注册即将BeanDefinition put到map中
```java
/** Map of bean definition objects, keyed by bean name. */
private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

/** Map from bean name to merged BeanDefinitionHolder. */
private final Map<String, BeanDefinitionHolder> mergedBeanDefinitionHolders = new ConcurrentHashMap<>(256);


@Override
public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
        throws BeanDefinitionStoreException {

    Assert.hasText(beanName, "Bean name must not be empty");
    Assert.notNull(beanDefinition, "BeanDefinition must not be null");

    if (beanDefinition instanceof AbstractBeanDefinition) {
        try {
            ((AbstractBeanDefinition) beanDefinition).validate();
        }
        catch (BeanDefinitionValidationException ex) {
            throw new BeanDefinitionStoreException(beanDefinition.getResourceDescription(), beanName,
                    "Validation of bean definition failed", ex);
        }
    }

    BeanDefinition existingDefinition = this.beanDefinitionMap.get(beanName);
    // 如果已经注册
    if (existingDefinition != null) {
        // 检查是否可以覆盖
        if (!isAllowBeanDefinitionOverriding()) {
            throw new BeanDefinitionOverrideException(beanName, beanDefinition, existingDefinition);
        }
        else if (existingDefinition.getRole() < beanDefinition.getRole()) {
            // e.g. was ROLE_APPLICATION, now overriding with ROLE_SUPPORT or ROLE_INFRASTRUCTURE
            if (logger.isInfoEnabled()) {
                logger.info("Overriding user-defined bean definition for bean '" + beanName +
                        "' with a framework-generated bean definition: replacing [" +
                        existingDefinition + "] with [" + beanDefinition + "]");
            }
        }
        else if (!beanDefinition.equals(existingDefinition)) {
            if (logger.isDebugEnabled()) {
                logger.debug("Overriding bean definition for bean '" + beanName +
                        "' with a different definition: replacing [" + existingDefinition +
                        "] with [" + beanDefinition + "]");
            }
        }
        else {
            if (logger.isTraceEnabled()) {
                logger.trace("Overriding bean definition for bean '" + beanName +
                        "' with an equivalent definition: replacing [" + existingDefinition +
                        "] with [" + beanDefinition + "]");
            }
        }
        // 覆盖
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }
    else {
        if (hasBeanCreationStarted()) {
            // Cannot modify startup-time collection elements anymore (for stable iteration)
            synchronized (this.beanDefinitionMap) {
                this.beanDefinitionMap.put(beanName, beanDefinition);
                List<String> updatedDefinitions = new ArrayList<>(this.beanDefinitionNames.size() + 1);
                updatedDefinitions.addAll(this.beanDefinitionNames);
                updatedDefinitions.add(beanName);
                this.beanDefinitionNames = updatedDefinitions;
                removeManualSingletonName(beanName);
            }
        }
        else {
            // Still in startup registration phase
            this.beanDefinitionMap.put(beanName, beanDefinition);
            this.beanDefinitionNames.add(beanName);
            removeManualSingletonName(beanName);
        }
        //重置所有已经注册过的BeanDefinition的缓存  
        this.frozenBeanDefinitionNames = null;
    }

    if (existingDefinition != null || containsSingleton(beanName)) {
        resetBeanDefinition(beanName);
    }
    else if (isConfigurationFrozen()) {
        clearByTypeCache();
    }
}
```
至此，Bean定义资源文件中配置的Bean被解析过后，已经注册到IoC容器中，被容器管理起来，真正完成了IoC容器初始化所做的全部工作。现 在IoC容器中已经建立了整个Bean的配置信息，这些BeanDefinition信息已经可以使用，并且可以被检索，IoC容器的作用就是对这些注册的Bean定义信息进行处理和维护。这些的注册的Bean定义信息是IoC容器控制反转的基础，正是有了这些注册的数据，容器才可以进行依赖注入。
## 8.3 总结
现在通过上面的代码，总结一下IOC容器初始化的基本步骤：
![67.spring-framework-ioc-source-9.png](../../assets/images/04-主流框架/spring/67.spring-framework-ioc-source-9.png)

- 初始化的入口在容器实现中的 refresh()调用来完成
- 对 bean 定义载入 IOC 容器使用的方法是 loadBeanDefinition,其中的大致过程如下：
  - 通过 ResourceLoader 来完成资源文件位置的定位，DefaultResourceLoader 是默认的实现，同时上下文本身就给出了 ResourceLoader 的实现，可以从类路径，文件系统, URL 等方式来定为资源位置。如果是 XmlBeanFactory作为 IOC 容器，那么需要为它指定 bean 定义的资源，也就是说 bean 定义文件时通过抽象成 Resource 来被 IOC 容器处理的
  - 通过 BeanDefinitionReader来完成定义信息的解析和 Bean 信息的注册, 往往使用的是XmlBeanDefinitionReader 来解析 bean 的 xml 定义文件 - 实际的处理过程是委托给 BeanDefinitionParserDelegate 来完成的，从而得到 bean 的定义信息，这些信息在 Spring 中使用 BeanDefinition 对象来表示 - 这个名字可以让我们想到loadBeanDefinition,RegisterBeanDefinition 这些相关的方法 - 他们都是为处理 BeanDefinitin 服务的
  - 容器解析得到 BeanDefinition 以后，需要把它在 IOC 容器中注册，这由 IOC 实现 BeanDefinitionRegistry 接口来实现。注册过程就是在 IOC 容器内部维护的一个HashMap 来保存得到的 BeanDefinition 的过程。这个 HashMap 是 IoC 容器持有 bean 信息的场所，以后对 bean 的操作都是围绕这个HashMap 来实现的.
- 然后我们就可以通过 BeanFactory 和 ApplicationContext 来享受到 Spring IOC 的服务了,在使用 IOC 容器的时候，我们注意到除了少量粘合代码，绝大多数以正确 IoC 风格编写的应用程序代码完全不用关心如何到达工厂，因为容器将把这些对象与容器管理的其他对象钩在一起。基本的策略是把工厂放到已知的地方，最好是放在对预期使用的上下文有意义的地方，以及代码将实际需要访问工厂的地方。 Spring 本身提供了对声明式载入 web 应用程序用法的应用程序上下文,并将其存储在ServletContext 中的框架实现。






























































































































































































































































































































































































































































































































































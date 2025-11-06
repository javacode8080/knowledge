> 最后更新：2025-11-05 | [返回主目录](../README.md)

# 一、什么是Servlet容器？

## 开篇引言
您好！欢迎进入Servlet容器的世界。在这一章，我们将从最基础的概念开始，详细解释Servlet容器是什么。如果您是Java Web开发的新手，可能会觉得“容器”这个词有些抽象，甚至神秘——但别担心，我会用简单易懂的语言，结合生动比喻，带您一步步揭开它的面纱。本章的重点是理解Servlet容器的抽象定义和核心角色，而不是具体技术实现（如Tomcat或Jetty）。学完这一章，您将能清晰回答：Servlet容器到底是什么？它为什么被称为“容器”？以及它在Web应用中扮演什么角色？

**本章学习目标**：
- 掌握Servlet容器的基本定义和核心功能。
- 通过比喻理解容器与Servlet的关系。
- 认识到学习抽象概念的重要性，为后续学习打下基础。

让我们从最基本的定义开始。

---

## 1.1 基本定义：Servlet容器是什么？
Servlet容器（也称为Web容器）是一个软件框架，它遵循Java Servlet规范（如Jakarta EE标准），为Java Servlet提供运行时环境。简单来说，它是一个“托管平台”，负责管理Servlet的生命周期、处理HTTP请求和响应，并充当Web服务器与业务代码之间的桥梁。

### 1.1.1 核心角色：桥梁与管理者
Servlet容器在Web应用中扮演两个关键角色：
- **桥梁角色**：它连接了客户端（如浏览器）和您的Java代码（Servlet）。当客户端发送HTTP请求时，容器接收请求，并将其转发给相应的Servlet；Servlet处理完成后，容器再将响应返回给客户端。这就像邮局系统：您写信（请求），邮局（容器）负责分拣和投递，最终送到收件人（Servlet）手中。
- **管理者角色**：容器控制Servlet的整个生命周期——从创建、服务到销毁。它还提供额外服务，如会话管理、线程池和安全性，确保Web应用稳定运行。

### 1.1.2 遵循规范：为什么标准很重要？
Servlet容器不是任意软件，而是严格遵循Java Servlet规范（例如，最新版本是Jakarta Servlet 6.0）。规范定义了一套标准接口（如`Servlet`、`ServletRequest`、`ServletResponse`），所有兼容的容器（如Tomcat、Jetty）都必须实现这些接口。这意味着：
- **可移植性**：您的Servlet代码可以在任何遵守规范的容器上运行，无需修改。
- **一致性**：无论底层容器如何，Servlet的行为都是可预测的。
规范确保了Java Web生态的统一性，让开发者能专注于业务逻辑，而不是底层差异。

### 1.1.3 核心功能概览
Servlet容器的主要功能包括：
- **生命周期管理**：控制Servlet的初始化（init）、服务（service）和销毁（destroy）。
- **请求处理**：解析HTTP请求，路由到正确的Servlet。
- **并发支持**：通过线程池处理多个请求同时到达的情况。
- **会话管理**：维护用户状态（如登录信息），通常通过Cookie或URL重写实现。
- **资源处理**：服务静态文件（如HTML、图片）或动态内容。
这些功能是抽象的，具体实现由容器负责。例如，您不需要手动创建线程——容器会自动处理。

### 1.1.4 与其他术语的区别
有时人们会混淆Servlet容器与相关概念，这里简单澄清：
- **Web服务器 vs. Servlet容器**：Web服务器（如Apache或Nginx）主要处理HTTP协议和静态内容，而Servlet容器专为Java Servlet设计，能处理动态内容。现代容器（如Tomcat）常集成了Web服务器功能。
- **应用服务器 vs. Servlet容器**：应用服务器（如WildFly）是更全面的平台，包含Servlet容器、EJB支持等。Servlet容器是应用服务器的子集，更轻量。
理解这些区别有助于您选择合适的工具。

**小结**：Servlet容器是一个标准化平台，它通过规范接口管理Servlet，简化了Web开发。接下来，我们用比喻来让这个概念更直观。

---

## 1.2 生动比喻：舞台与演员——让抽象概念变得具体
为了帮助您形象化理解，我将使用一个比喻：**Servlet容器就像舞台和导演，而Servlet就像演员**。这个比喻能简化复杂概念，让您快速抓住精髓。

### 1.2.1 比喻详解：舞台、导演和演员
- **Servlet容器（舞台和导演）**：
  - **舞台**：提供演出所需的环境，如灯光、音响和布景。在Web世界中，这对应容器的运行时环境——它监听端口（如8080），准备好处理请求。
  - **导演**：负责调度和管理演员。导演决定何时让演员上场、如何表演，以及何时谢幕。容器就像导演：它控制Servlet的生命周期（初始化、服务、销毁），并确保每个请求被正确路由。
  
- **Servlet（演员）**：
  - **演员**：执行具体表演，根据剧本（您的业务代码）行动。演员不关心舞台如何搭建或灯光如何调节——他们只专注自己的角色。同样，Servlet只处理业务逻辑（如计算数据、访问数据库），而不必操心网络通信或线程管理。

### 1.2.2 比喻在实践中的映射
让我们通过一个简单例子映射这个比喻：
- **场景**：用户通过浏览器访问一个网站（如查看订单列表）。
- **过程**：
  1. **请求到达**：用户点击链接，HTTP请求发送到服务器。这就像观众入场，期待演出。
  2. **容器接收（舞台准备）**：容器（如Tomcat）监听端口，接收请求。导演（容器）检查节目单（URL映射），确定哪个演员（Servlet）该上场。
  3. **Servlet表演（演员行动）**：容器调用相应的Servlet（如OrderServlet），传递请求信息。Servlet执行代码（如查询数据库），生成响应。演员只需按剧本表演，不用自己打灯光。
  4. **返回响应（演出结束）**：容器将Servlet的结果返回给用户。导演确保演出顺利，演员谢幕。

这个比喻突出了容器的“服务性”：它处理杂事，让您（开发者）专注创意部分（业务逻辑）。如果您跳过容器，直接写Web服务器代码，就好比演员自己搭建舞台——可能出错，且效率低下。

---

## 1.3 为什么保持抽象学习很重要？——打好基础的关键
在学习Servlet容器时，您可能会 tempted 直接上手Tomcat或Jetty，但我强烈建议先聚焦抽象概念。这是因为抽象学习能带来长期收益，避免“只见树木，不见森林”的陷阱。

### 1.3.1 提高可移植性：一次学习，多处应用
- **核心好处**：Servlet规范是标准，而Tomcat、Jetty等只是具体实现。如果您先理解规范（如生命周期接口），就能轻松切换容器。例如，如果项目从Tomcat迁移到Undertow，您的代码几乎无需改动，因为底层接口相同。
- **现实例子**：就像学习驾驶——您先掌握交通规则（抽象），然后可以开任何品牌的车（具体实现）。如果只学开特定车型，换车时就得重新适应。
- **避免供应商锁定**：企业环境中，容器选择可能因成本、性能而变化。抽象知识让您保持灵活性。

### 1.3.2 打下坚实基础：理解原理，而非表面
- **深入本质**：抽象学习帮助您理解“为什么”，而不是“怎么做”。例如，知道容器如何管理会话，能让您优化应用性能（如减少内存使用）。
- **调试能力**：当应用出现问题时（如内存泄漏），如果您了解容器原理，就能快速定位是容器配置问题还是代码问题。否则，可能浪费时间在表面现象上。
- **支持进阶学习**：现代框架（如Spring MVC）都构建在Servlet容器之上。抽象知识是学习这些框架的基石——如果您直接跳入Spring，而不懂容器，会遇到很多“黑箱”困惑。

### 1.3.3 学习效率：避免信息过载
- **简化入门**：具体容器（如Tomcat）有大量配置和特性，初学者容易 overwhelmed。抽象概念先剥离细节，让您专注核心。
- **循序渐进**：先掌握通用原理，再探索具体实现，符合认知规律。这就像先学数学公式，再解应用题。
- **常见误区提醒**：许多新手过早比较Tomcat vs. Jetty的性能，但如果不理解共性，比较毫无意义。抽象学习能帮您避免这种误区。

### 1.3.4 长远价值：适应技术演变
- **Web技术不断进化**：从传统Web到微服务、云原生，Servlet容器依然相关（如Spring Boot内嵌Tomcat）。抽象概念是永恒的，而具体工具可能过时。
- **培养架构思维**：理解抽象能提升您的设计能力，例如，如何将应用解耦为可复用模块。

**小结**：保持抽象学习，不是拖延实践，而是为了更高效、更深入地掌握知识。它让您从“使用者”升级为“理解者”。

---

## 本章总结
通过这一章，您已经详细了解了Servlet容器的核心定义、比喻和抽象学习的重要性。让我们回顾关键点：
- **Servlet容器是什么**：它是一个遵循规范的软件框架，管理Servlet生命周期和处理HTTP请求，充当Web服务器与业务代码的桥梁。
- **生动比喻**：用“舞台与演员”的比喻，容器是舞台和导演，Servlet是演员，突出了容器的服务角色。
- **抽象学习的重要性**：提高可移植性、打下基础、提升学习效率，并适应技术变化。

现在，您应该对Servlet容器有了扎实的概念基础。在下一章，我们将探讨为什么Servlet容器会被发明出来——从历史角度理解其必要性。
# 二、为什么需要Servlet容器？——历史背景与必要性

## 开篇引言
您好！在这一章，我们将深入探讨Servlet容器规范是如何建立的，以及为什么今天的Java服务（无论是传统应用还是现代微服务）都依赖于类似Tomcat这样的Servlet容器来启动和运行。我会从历史背景出发，用通俗的语言解释抽象概念，避免陷入具体技术细节。学完这一章，您将明白：Servlet容器并非偶然出现，而是Web进化中的必然产物，它解决了关键问题，并成为Java生态的基石。

**本章学习目标**：
- 了解早期Web开发的挑战，理解Servlet规范建立的驱动力。
- 掌握Servlet容器如何通过标准化解决耦合和性能问题。
- 认清为什么现代Java服务无法脱离Servlet容器（即使表面上看不到它）。

让我们回到Web的“童年时代”，看看没有Servlet容器时，开发是怎样的。

---

## 2.1 早期Web开发的挑战：CGI时代的问题与痛点
在1990年代末和2000年代初，Web应用刚开始兴起。Java作为一门新兴语言，试图进入Web领域，但早期方式存在严重缺陷。主要采用CGI（通用网关接口）或其他自定义方案，导致以下问题：

### 2.1.1 高耦合：代码与服务器绑定，移植性差
- **现实比喻**：想象您写了一本书，但只能在一家特定印刷厂出版——如果换厂，整本书都得重写。早期Java Web应用就是这样：代码直接与Web服务器（如Apache）耦合。开发者需要针对特定服务器的API编程，导致应用无法轻松迁移到其他环境。
- **技术细节**：例如，一个应用可能依赖Apache的模块系统，如果切换到IIS或Nginx，代码几乎要推倒重来。这违反了软件工程的“解耦”原则，增加了维护成本。

### 2.1.2 性能低下：资源浪费严重
- **CGI的缺陷**：CGI是当时的主流技术，它为每个HTTP请求启动一个新的操作系统进程。想象一下，每次有用户点击网页，服务器就“生出一个新工人”来处理，完成后立即“辞退”——这导致进程创建和销毁的开销巨大，内存和CPU资源浪费严重。
- **Java的瓶颈**：虽然Java本身有线程优势，但早期Java Web方案（如通过本地代码集成）并未充分利用。并发请求多了，服务器容易崩溃，响应时间变慢，用户体验差。

### 2.1.3 开发复杂：开发者需处理底层杂事
- **手动劳动**：开发者不仅要写业务逻辑，还得操心网络通信、线程管理、会话存储等底层任务。这就像开车时，您不仅得方向盘，还得手动调节引擎转速——分散注意力，容易出错。
- **缺乏标准**：没有统一接口，每个项目都自定义一套方案，团队协作困难，代码复用率低。

**小结**：这些挑战催生了变革需求——Java社区需要一种更高效、标准化的Web开发方式。接下来，我们看Servlet规范如何革命性地解决这些问题。

---

## 2.2 Servlet规范的革命：标准化如何改变游戏规则
为了解决上述问题，Java社区（最初由Sun Microsystems领导）推出了Servlet规范。这不是一次小修小补，而是一次范式转移，核心思想是：**通过抽象接口，将Web服务器与业务逻辑分离**。让我们分解这一革命。

### 2.2.1 Servlet规范的建立：时间线与核心目标
- **时间背景**：Servlet规范最早于1997年提出（Servlet 1.0），随后融入Java EE（企业版）体系。关键版本如Servlet 2.3（引入过滤器）和Servlet 3.0（支持注解），不断演进。
- **核心目标**：
  - **标准化**：定义一套通用API（如`javax.servlet.*`包，现为`jakarta.servlet.*`），让所有兼容容器都遵循相同接口。
  - **解耦**：业务代码（Servlet）只依赖规范接口，不关心底层服务器实现。
  - **性能优化**：引入线程池、连接复用等机制，提升并发能力。

### 2.2.2 如何解决早期问题？——抽象概念的威力
- **解耦与可移植性**：Servlet规范定义了`Servlet`、`ServletRequest`、`ServletResponse`等接口。您的代码只与这些接口交互，而容器（如Tomcat）负责实现它们。这就像USB标准：您只需买一个USB设备，它能在任何电脑上工作，而不用关心电脑品牌。因此，应用可以轻松在Tomcat、Jetty或Undertow间迁移。
- **性能提升**：容器管理Servlet生命周期和线程池。例如，Servlet实例只需初始化一次，后续请求复用同一实例，避免了CGI的进程开销。容器自动处理并发，您无需手动写线程代码。
- **开发简化**：规范提供了内置功能，如会话管理（HttpSession）、安全性（web.xml配置），让开发者专注业务逻辑。这降低了入门门槛，提高了生产力。

### 2.2.3 规范的成功：生态系统的形成
Servlet规范成为Java EE的核心部分，催生了JSP（JavaServer Pages）、WebSocket等技术。它促进了容器市场的繁荣——Tomcat、Jetty等实现涌现，各自优化性能，但都遵守同一规范。这种“标准驱动创新”的模式，确保了Java Web的长期活力。

**小结**：Servlet规范通过抽象和标准化，解决了耦合、性能和复杂度问题。但为什么今天它依然不可或缺？我们进入现实意义部分。

---

## 2.3 现实意义：为什么现代Java服务依赖Servlet容器启动运行？
您可能会问：现在有Spring Boot、微服务框架，它们似乎“隐藏”了Servlet容器，为什么我们仍离不开它？答案在于，Servlet容器是底层基础，即使表面被封装，它仍在背后默默工作。以下是详细解释。

### 2.3.1 底层基础：Servlet容器是Java Web的“操作系统”
- **比喻**：就像手机应用依赖于iOS或Android系统，Java Web应用（包括Spring MVC、JAX-RS服务）都构建在Servlet容器之上。Spring Boot等框架只是“包装纸”，它们内嵌了Tomcat或Jetty，简化了配置，但核心仍是Servlet容器在处理HTTP请求。
- **技术事实**：当您启动一个Spring Boot应用，它默认内嵌Tomcat。应用启动时，Tomcat容器初始化，加载Servlet（如DispatcherServlet），并监听端口。没有容器，您的代码无法直接响应Web请求。

### 2.3.2 标准化优势：确保兼容性和稳定性
- **跨平台与云原生**：Servlet规范让Java应用能在任何环境（本地服务器、Docker容器、云平台）运行。例如，Kubernetes中的Java服务通常使用Tomcat镜像，因为规范保证了行为一致。
- **企业级需求**：会话集群、负载均衡、安全性等功能，都依赖容器实现。如果您自己从头写，会重复造轮子，且容易出错。

### 2.3.3 性能与维护：容器处理复杂任务
- **并发管理**：现代应用需处理高并发（如电商秒杀）。容器通过线程池和非阻塞IO（如Servlet 3.1的异步支持）优化性能，而开发者无需深入底层。
- **生命周期管理**：容器确保资源（如数据库连接）在应用启动时初始化，关闭时清理，避免了内存泄漏。
- **为什么不能绕过？**：理论上，您可以用纯Java写一个HTTP服务器，但这相当于重新实现Servlet容器——成本高、易出bug，且失去生态支持。实践表明，依赖成熟容器是更优解。

### 2.3.4 现代框架的“错觉”：Spring Boot为例
- Spring Boot让您用`@RestController`注解快速开发，感觉不到Tomcat。但如果您查看依赖（如spring-boot-starter-web），它会引入Tomcat。Boot的“开箱即用”正是建立在Servlet容器之上。
- **类比**：开车时，您只需踩油门，不用关心引擎内部——但引擎必不可少。同样，Servlet容器是Web应用的“引擎”。

**小结**：Servlet容器通过历史积累的解决方案，成为Java服务的基石。即使框架进化，它的抽象价值不变——这就是为什么所有Java Web服务都间接或直接依赖它。

---

## 本章总结
通过这一章，您看到了Servlet容器规范建立的必然性：它从早期Web的混乱中诞生，通过标准化解决了耦合、性能和复杂度问题。今天，它之所以不可替代，是因为：
- **历史遗产**：它解决了根本问题，并被广泛采纳。
- **抽象威力**：规范接口让代码可移植，生态繁荣。
- **现实需求**：现代应用依赖其稳定性和性能优化。

理解这一点，您就能明白：学习Servlet容器抽象概念，不是过时，而是掌握Java Web的“根”。在下一章，我们将探讨容器如何具体工作，巩固您的理解。

**行动建议**：思考一下您用的Java框架（如Spring），尝试找出它内嵌的Servlet容器（如查看pom.xml或gradle文件）。这将帮助您连接抽象与现实。
# 三、Servlet容器如何工作？——核心功能解析

## 开篇引言
您好！在前两章中，我们了解了Servlet容器的定义和存在意义。现在，让我们进入最核心的部分：Servlet容器到底是如何工作的？这一章将深入解析容器内部的运行机制，我会用通俗易懂的方式讲解抽象原理，避免涉及具体容器的实现细节。即使您是初学者，只要具备基本的Java知识，也能跟上我的讲解。我们将重点分析容器的三大核心功能：生命周期管理、请求处理流程和附加服务。学完这一章，您将能清晰描述一个HTTP请求是如何从客户端到达您的Servlet，并返回响应的完整过程。

**本章学习目标**：
- 理解Servlet容器如何管理Servlet的生命周期
- 掌握请求处理的完整流程和核心组件
- 了解容器提供的增值服务及其重要性
- 建立Servlet容器工作的心理模型

让我们从最重要的生命周期管理开始。

---

## 3.1 生命周期管理：Servlet的"生老病死"
Servlet容器对Servlet生命周期的管理是其最核心的功能之一。这种管理确保了资源的高效使用和应用的稳定性。让我们详细解析这个过程的每个阶段。

### 3.1.1 初始化阶段：Servlet的"出生"
当Servlet容器启动或第一个请求到达时，它会初始化Servlet：

**触发时机**：
- **容器启动时加载**：如果web.xml中配置了`<load-on-startup>`，容器启动就会初始化Servlet
- **第一次请求时加载**：没有配置启动加载的Servlet，在首次请求时初始化

**初始化过程**：
1. **类加载**：容器加载Servlet类文件到内存中
2. **实例化**：调用默认构造函数创建Servlet实例
3. **调用init()方法**：容器调用Servlet的init()方法，传入ServletConfig对象
4. **就绪状态**：初始化完成后，Servlet进入就绪状态，等待处理请求

**设计优势**：
- **懒加载机制**：避免一次性加载所有Servlet，节省内存资源
- **单例模式**：每个Servlet类通常只有一个实例，多个请求共享同一实例
- **异常处理**：如果init()失败，容器会标记该Servlet不可用

### 3.1.2 服务阶段：Servlet的"工作生涯"
一旦初始化完成，Servlet就进入服务阶段，这是其主要的工作状态：

**请求处理循环**：
1. **接收请求**：容器接收HTTP请求
2. **创建请求/响应对象**：为每个请求创建新的ServletRequest和ServletResponse实例
3. **调用service()方法**：容器调用Servlet的service()方法
4. **业务逻辑执行**：您的Servlet代码处理请求
5. **清理资源**：请求处理完成后，容器回收请求/响应对象

**线程安全考虑**：
- Servlet实例是共享的，因此您的service()方法必须是线程安全的
- 避免使用实例变量存储请求特定数据
- 容器通过为每个请求创建独立的请求/响应对象来隔离状态

### 3.1.3 销毁阶段：Servlet的"退休"
当容器需要关闭或重新加载应用时，会优雅地销毁Servlet：

**触发条件**：
- 容器正常关闭
- Web应用被重新加载
- 容器检测到内存不足等情况

**销毁过程**：
1. **停止新请求**：容器停止向该Servlet分发新请求
2. **等待处理完成**：等待当前正在处理的请求完成
3. **调用destroy()方法**：容器调用Servlet的destroy()方法
4. **资源清理**：Servlet释放所有占用的资源（如数据库连接、文件句柄）
5. **垃圾回收**：Servlet实例被标记为可垃圾回收

**重要意义**：
这种生命周期管理确保了资源的正确分配和释放，避免了内存泄漏和资源耗尽问题。

---

## 3.2 请求处理流程：从客户端到Servlet的旅程
现在让我们跟踪一个HTTP请求在Servlet容器中的完整处理过程。这个过程就像快递配送系统：请求是包裹，容器是智能分拣中心，Servlet是最终处理站。

### 3.2.1 请求接收与解析阶段
**步骤1：接收网络请求**
- 容器在配置的端口（默认8080）监听HTTP请求
- 当客户端（浏览器）发起请求时，容器接受TCP连接
- 容器读取原始的HTTP请求数据

**步骤2：解析HTTP协议**
- 容器解析请求行（方法、URL、协议版本）
- 解析请求头（Content-Type、Cookie、User-Agent等）
- 解析请求体（如果是POST/PUT请求）

**步骤3：创建封装对象**
- 容器创建HttpServletRequest对象，封装所有请求信息
- 创建HttpServletResponse对象，用于构建响应
- 这些对象为Servlet提供了统一的API接口

### 3.2.2 请求路由与分发阶段
**URL模式匹配**：
容器根据URL路径决定由哪个Servlet处理请求。匹配规则包括：
- 精确匹配：URL完全匹配Servlet映射
- 路径匹配：最长路径前缀匹配
- 扩展名匹配：基于文件扩展名
- 默认Servlet：处理其他Servlet不匹配的请求

**路由决策过程**：
```
请求URL → 容器路由引擎 → 匹配web.xml或注解 → 确定目标Servlet
```

**过滤器链执行**：
在请求到达Servlet之前，可能经过多个过滤器：
```
请求 → 过滤器1 → 过滤器2 → ... → 过滤器N → Servlet
```
每个过滤器都可以对请求进行预处理或对响应进行后处理。

### 3.2.3 Servlet执行阶段
**service()方法分发**：
容器根据HTTP方法调用相应的doXXX()方法：
- GET → doGet()
- POST → doPost()
- PUT → doPut()
- DELETE → doDelete()

**业务逻辑执行**：
在您的Servlet方法中，您可以：
- 读取请求参数
- 访问会话信息
- 调用业务逻辑（数据库操作、计算等）
- 设置响应内容和状态

### 3.2.4 响应返回阶段
**响应构建**：
- Servlet通过HttpServletResponse对象设置状态码、头信息、内容
- 可以生成HTML、JSON、XML等各种格式的响应

**响应发送**：
- 容器将响应数据转换为HTTP协议格式
- 通过网络连接发送回客户端
- 记录访问日志用于监控和分析

**连接管理**：
- 对于HTTP/1.1，容器可能保持连接用于后续请求
- 对于HTTP/2，容器管理更复杂的多路复用连接

---

## 3.3 附加功能：容器提供的"增值服务"
除了核心的请求处理，Servlet容器还提供了一系列重要的增值服务，这些服务大大简化了Web应用的开发。

### 3.3.1 会话管理：维持用户状态
**会话创建与维护**：
- 容器为每个用户会话创建唯一的HttpSession对象
- 通过Cookie（JSESSIONID）或URL重写跟踪会话
- 会话超时自动失效，释放资源

**会话存储**：
- 容器负责会话数据的存储和检索
- 支持分布式环境下的会话复制
- 提供会话事件监听机制

### 3.3.2 并发处理：高效应对多用户访问
**线程池管理**：
- 容器维护线程池处理并发请求
- 避免为每个请求创建新线程的开销
- 可配置线程池大小，优化资源使用

**异步处理支持**：
- Servlet 3.0+支持异步请求处理
- 长时间操作不阻塞请求线程
- 提高系统吞吐量和响应能力

### 3.3.3 安全性管理：保护应用安全
**认证机制**：
- 支持BASIC、FORM、DIGEST等多种认证方式
- 集成容器管理的用户数据库或LDAP

**授权控制**：
- 基于URL模式的访问控制
- 角色-based权限管理
- SSL/TLS支持保障数据传输安全

### 3.3.4 静态资源服务
**文件服务**：
- 容器可以直接服务HTML、CSS、JavaScript、图片等静态文件
- 支持缓存控制、压缩等优化
- MIME类型自动识别

### 3.3.5 JSP支持
**JSP编译与执行**：
- 容器将JSP页面编译为Servlet
- 管理JSP页面的生命周期
- 提供JSP标签库支持

---

## 3.4 抽象模型图：构建完整心理模型
基于以上分析，我们可以构建一个Servlet容器工作的抽象心理模型：

```
客户端请求
    ↓
HTTP监听器（端口监听）
    ↓
请求解析器（协议解析）
    ↓
    ┌─ 静态资源？ ─→ 静态资源处理器
路由决策器 ─┼─ 匹配Servlet？ ─→ 过滤器链 ─→ 目标Servlet
    └─ 默认处理？ ─→ 默认Servlet
    ↓
响应构建器
    ↓
响应发送器
    ↓
客户端响应
```

**关键组件交互**：
1. **连接器(Connector)**：处理网络通信
2. **容器引擎(Container)**：管理Servlet生命周期和请求处理
3. **会话管理器(Session Manager)**：维护用户状态
4. **安全管理器(Security Manager)**：处理认证授权

**数据流特点**：
- 请求/响应对象是线程隔离的
- Servlet实例是共享的
- 会话数据是用户隔离的

---

## 本章总结
通过这一章的详细解析，您现在应该对Servlet容器的工作原理有了清晰的认识。让我们回顾关键要点：

### 核心收获：
1. **生命周期管理**：容器精确控制每个Servlet的初始化、服务和销毁过程，确保资源高效利用
2. **请求处理流程**：从接收请求到返回响应，容器处理了所有底层细节，为Servlet提供干净的编程接口
3. **增值服务**：会话管理、并发处理、安全性等附加功能让开发者能专注于业务逻辑

### 实践意义：
- 理解这些原理有助于您编写更高效的Servlet代码
- 当遇到性能问题时，您能更准确地定位是容器配置问题还是代码问题
- 为学习高级主题（如Spring MVC、微服务）打下坚实基础

# 四、如何实现一个简单的Servlet容器？——基于规范的最小实现指南

## 开篇引言
您好！在前三章中，我们深入探讨了Servlet容器的定义、历史背景和工作原理。现在，让我们进入一个更动手的环节：如果您想自己实现一个简单的Servlet容器，基于Servlet规范，需要做哪些事情？这一章将结合Servlet规范（以Jakarta Servlet为例），详细解析实现一个最小可运行Servlet容器的关键步骤。我会用通俗的语言解释抽象概念，避免陷入复杂代码，而是聚焦于核心原理和最小必要组件。即使您没有实际实现过容器，也能通过本章理解其内部机制。学完这一章，您将能回答：实现一个基本Servlet容器至少需要哪些组件？以及如何让您的Web项目运行在自建容器上。

**本章学习目标**：
- 理解Servlet规范对容器的核心要求。
- 掌握实现Servlet容器的关键组件和流程。
- 学会如何测试和集成自建容器。
- 认识到实现容器的挑战和价值，加深对抽象概念的理解。

请注意，实现一个生产级容器（如Tomcat）非常复杂，但我们可以从最小可行产品（MVP）开始。让我们从规范要求出发。

---

## 4.1 理解Servlet规范的最小要求：规范规定了什么？
在动手之前，我们必须先理解Servlet规范（如Jakarta Servlet 6.0）对容器的基本要求。规范定义了一套标准接口和行为，任何兼容的容器都必须实现这些。以下是实现一个最小容器的核心规范要求。

### 4.1.1 核心接口实现：容器必须提供的抽象
Servlet规范定义了关键接口，容器需要实现这些接口的具体类：
- **Servlet接口**：包括`init(ServletConfig config)`、`service(ServletRequest req, ServletResponse res)`、`destroy()`方法。容器必须能调用这些方法。
- **ServletRequest和ServletResponse接口**：容器需要创建这些对象，封装HTTP请求和响应数据。例如，ServletRequest提供获取参数的方法，ServletResponse提供写入响应的方法。
- **ServletConfig接口**：用于传递初始化参数给Servlet。
- **ServletContext接口**：代表整个Web应用上下文，提供共享资源访问（如全局属性）。

**为什么这些接口重要？**  
它们确保了您的Servlet代码只依赖标准接口，而不是具体容器。如果您实现这些接口，您的容器就能运行任何遵守规范的Servlet。

### 4.1.2 生命周期管理：规范要求的必须行为
规范明确规定了Servlet生命周期的管理：
- **初始化**：容器必须在Servlet首次使用前调用其`init()`方法，且只调用一次。
- **服务**：对于每个请求，容器必须调用`service()`方法，并传入新的ServletRequest和ServletResponse实例。
- **销毁**：当容器关闭或应用卸载时，必须调用`destroy()`方法进行清理。

**最小要求**：您的容器至少需要实现单线程下的生命周期管理，但生产环境需要并发支持。

### 4.1.3 部署描述符支持：web.xml或注解解析
规范要求容器能解析Web应用的配置：
- **web.xml文件**：传统方式，容器需要读取`WEB-INF/web.xml`来映射URL到Servlet。
- **注解支持**：现代方式（Servlet 3.0+），如`@WebServlet`注解，容器需扫描类路径识别Servlet。

**对于最小实现**：您可以先支持简单的web.xml解析或硬编码映射，以简化开发。

### 4.1.4 其他可选但重要的功能
规范还包括会话管理、过滤器、监听器等，但最小实现可以暂缓这些：
- **会话管理（HttpSession）**：生产环境需要，但MVP可跳过。
- **并发处理**：规范要求线程安全，但最小实现可先假设单线程。
- **错误处理**：规范定义了错误页面，但MVP可只处理基本异常。

**小结**：规范的核心是生命周期和请求处理接口。您的容器只要实现这些，就能运行简单Servlet。接下来，我们看具体实现步骤。

---

## 4.2 实现HTTP服务器基础：构建容器的"耳朵"和"嘴巴"
Servlet容器本质是一个特殊的HTTP服务器。第一步是实现一个能监听HTTP请求并返回响应的基础服务器。这就像建造房子的地基——没有它，一切无从谈起。

### 4.2.1 选择网络编程方式：基于Java的简单实现
您可以用Java标准库实现一个基本的HTTP服务器：
- **使用ServerSocket**：这是最底层的方式，监听端口，接受TCP连接。
- **示例逻辑**：
  1. 创建ServerSocket，绑定到端口（如8080）。
  2. 循环接受客户端连接。
  3. 读取HTTP请求数据（字符串解析）。
  4. 解析请求方法、URL、头部等。
  5. 构建HTTP响应并发送。

**简单代码概念（非完整代码）**：
```java
// 伪代码示例：基本HTTP服务器框架
ServerSocket serverSocket = new ServerSocket(8080);
while (true) {
    Socket clientSocket = serverSocket.accept();
    // 解析HTTP请求
    // 处理请求（将来路由到Servlet）
    // 发送HTTP响应
    clientSocket.close();
}
```

**注意**：这非常基础，无法处理并发或复杂协议。对于MVP，可以先假设单线程请求。

### 4.2.2 解析HTTP协议：将原始数据转换为规范对象
容器需要将原始的HTTP字节流解析为结构化数据，以便创建ServletRequest对象：
- **解析请求行**：如`GET /hello HTTP/1.1`，提取方法、路径、协议。
- **解析头部**：如`Content-Type: application/json`，存储为键值对。
- **解析体部**：对于POST请求，读取内容体。

**挑战**：HTTP协议有细节（如分块传输、编码），但MVP可先支持简单请求（如GET/POST）。

### 4.2.3 创建请求和响应封装对象
基于解析的数据，实现规范的ServletRequest和ServletResponse接口：
- **自定义Request类**：实现ServletRequest，存储URL、参数、头部等。
- **自定义Response类**：实现ServletResponse，提供写入响应的方法（如设置状态码、头、体）。

**示例**：您的Response类可能有一个`write(String content)`方法，内部将内容转换为HTTP格式发送。

**为什么重要？**  
这些对象是容器与Servlet之间的桥梁。Servlet只与这些接口交互，而不关心底层网络细节。

---

## 4.3 实现Servlet生命周期管理：容器的"大脑"
这是核心部分：容器必须管理Servlet的加载、初始化和调用。让我们分步实现。

### 4.3.1 Servlet加载与类加载机制
容器需要动态加载Servlet类：
- **类加载器使用**：使用Java的ClassLoader来加载WEB-INF/classes或JAR文件中的Servlet类。
- **示例流程**：
  1. 根据配置（如web.xml），确定Servlet类名。
  2. 使用Class.forName()加载类。
  3. 通过反射创建实例（调用默认构造函数）。

**注意安全性**：生产容器需要安全管理器，但MVP可忽略。

### 4.3.2 初始化过程：调用init()方法
一旦Servlet实例创建，容器必须初始化它：
- **创建ServletConfig**：实现一个简单的ServletConfig，传递初始化参数（如从web.xml）。
- **调用init()**：调用Servlet的init(config)方法。
- **错误处理**：如果init失败，容器应标记该Servlet不可用。

**关键点**：init()只调用一次，容器需要维护Servlet实例的引用。

### 4.3.3 服务调用：处理请求时调用service()
对于每个请求，容器：
1. 创建新的ServletRequest和ServletResponse实例（确保线程隔离）。
2. 调用Servlet的service(request, response)方法。
3. 在service()完成后，容器负责发送响应。

**线程安全**：MVP可单线程处理，但规范要求支持并发。简单实现可以用synchronized块，但性能差。

### 4.3.4 销毁处理：优雅关闭
当容器停止时：
- 遍历所有已加载的Servlet。
- 调用每个Servlet的destroy()方法。
- 释放资源（如关闭连接）。

**重要性**：避免资源泄漏，确保优雅退出。

---

## 4.4 实现请求路由和处理：容器的"交通警察"
容器需要将传入的请求路由到正确的Servlet。这涉及URL映射和过滤器链（可选）。

### 4.4.1 URL映射机制：从请求路径到Servlet
根据规范，容器支持多种映射方式：
- **路径映射**：如URL `/app/hello` 映射到HelloServlet。
- **扩展映射**：如`*.jsp`映射到JSPServlet。
- **默认Servlet**：处理不匹配其他映射的请求。

**最小实现**：可以硬编码一个映射表，例如：
- `/hello` → com.example.HelloServlet
- 通过解析web.xml或扫描注解来动态构建映射。

### 4.4.2 请求分发流程
完整流程：
1. 接收HTTP请求。
2. 解析URL，查找匹配的Servlet。
3. 如果找到，调用该Servlet的service()方法。
4. 如果未找到，返回404错误或使用默认Servlet。

**过滤器支持**：规范要求过滤器链，但MVP可跳过。如果需要，可以在调用Servlet前执行过滤器。

### 4.4.3 处理静态资源
规范要求容器能服务静态文件（如HTML、图片）：
- **简单方式**：检查请求路径是否对应物理文件（如webapp目录下），直接读取并返回。
- **与Servlet协调**：通常，静态资源由默认Servlet处理。

**MVP建议**：先专注于动态Servlet请求，静态资源可后期添加。

---

## 4.5 测试和集成：让您的项目运行在自建容器上
实现基本功能后，需要测试容器是否能运行真实Servlet项目。

### 4.5.1 创建测试Servlet
编写一个简单的Servlet来验证容器：
```java
// 示例：简单的HelloWorld Servlet
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.getWriter().write("Hello from my container!");
    }
}
```

### 4.5.2 集成步骤
1. **部署Servlet**：将Servlet类文件放在容器的类路径中（如模拟WEB-INF/classes）。
2. **配置映射**：通过web.xml或硬编码注册Servlet。
3. **启动容器**：运行您的服务器代码，监听端口。
4. **测试访问**：用浏览器或curl访问`http://localhost:8080/hello`，检查响应。

### 4.5.3 常见问题与调试
- **类加载错误**：确保类路径正确。
- **协议解析错误**：检查HTTP请求格式。
- **生命周期问题**：验证init()和destroy()调用。

**迭代改进**：从简单案例开始，逐步添加功能（如会话、并发）。

## 4.6 详细解析：请求路由的完整过程

### 4.6.1 第一步：建立"地址簿" - URL映射表

想象一下，您的容器需要维护一个"电话簿"，记录着每个URL地址对应哪个Servlet类。这个映射关系的建立有两种主要方式：

**方式一：解析web.xml（传统方式）**
```xml
<!-- web.xml 示例 -->
<servlet>
    <servlet-name>UserServlet</servlet-name>
    <servlet-class>com.example.UserServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>UserServlet</servlet-name>
    <url-pattern>/user/*</url-pattern>
</servlet-mapping>
```

**方式二：扫描注解（现代方式）**
```java
@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    // Servlet代码
}
```

**容器启动时的映射表构建过程：**
1. **扫描阶段**：容器启动时，扫描WEB-INF/web.xml和所有类的注解
2. **构建映射表**：在内存中建立类似这样的数据结构：
```java
Map<String, String> urlToServletMap = new HashMap<>();
urlToServletMap.put("/user/*", "com.example.UserServlet");
urlToServletMap.put("/product/*", "com.example.ProductServlet");
urlToServletMap.put("*.jsp", "JspServlet"); // 默认映射
```

这样，容器就有了一个"路由表"，知道哪个URL模式对应哪个Servlet类。

### 4.6.2 第二步：请求到达时的"查号服务" - URL匹配

当HTTP请求到达时，容器执行以下匹配逻辑：

**请求示例**：`GET /user/profile HTTP/1.1`

**匹配算法流程**：
```
1. 提取URL路径："/user/profile"
2. 查找精确匹配：先找完全相等的"/user/profile"
3. 如果没有，查找最长路径匹配：匹配"/user/*"模式
4. 如果还没有，查找扩展名匹配
5. 最后使用默认Servlet
```

**代码层面的简单实现**：
```java
public String findServletForUrl(String requestUrl) {
    // 1. 精确匹配
    if (urlToServletMap.containsKey(requestUrl)) {
        return urlToServletMap.get(requestUrl);
    }
    
    // 2. 路径匹配（查找最长前缀）
    String longestMatch = null;
    for (String urlPattern : urlToServletMap.keySet()) {
        if (urlPattern.endsWith("/*")) {
            String prefix = urlPattern.substring(0, urlPattern.length() - 2);
            if (requestUrl.startsWith(prefix) && 
                (longestMatch == null || prefix.length() > longestMatch.length())) {
                longestMatch = urlPattern;
            }
        }
    }
    if (longestMatch != null) {
        return urlToServletMap.get(longestMatch);
    }
    
    // 3. 返回默认Servlet
    return "DefaultServlet";
}
```

### 4.6.3 第三步：Servlet实例管理 - 单例与生命周期

找到Servlet类名后，容器需要获取实际的Servlet实例：

**实例管理策略**：
```java
public class SimpleContainer {
    private Map<String, Servlet> servletInstances = new HashMap<>();
    
    public Servlet getServletInstance(String servletClassName) {
        // 检查是否已经存在实例
        if (servletInstances.containsKey(servletClassName)) {
            return servletInstances.get(servletClassName);
        }
        
        // 如果没有，创建新实例并初始化
        try {
            Class<?> servletClass = Class.forName(servletClassName);
            Servlet servlet = (Servlet) servletClass.newInstance();
            
            // 调用init()方法进行初始化
            ServletConfig config = createServletConfig(servletClassName);
            servlet.init(config);
            
            // 缓存实例供后续使用
            servletInstances.put(servletClassName, servlet);
            return servlet;
        } catch (Exception e) {
            throw new RuntimeException("无法加载Servlet: " + servletClassName, e);
        }
    }
}
```

**关键点**：每个Servlet类通常只有一个实例（单例模式），多个请求共享同一个实例。

### 4.6.4 第四步：方法调用 - 反射与HTTP方法分发

这是最精彩的部分：容器如何知道调用Servlet的哪个方法？

**HttpServlet的service()方法逻辑**：
当您继承HttpServlet时，它已经实现了智能的请求分发：

```java
// 这是HttpServlet内部的简化逻辑（不是您需要写的）
public class HttpServlet implements Servlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String method = req.getMethod(); // "GET", "POST"等
        
        if ("GET".equals(method)) {
            doGet(req, resp);
        } else if ("POST".equals(method)) {
            doPost(req, resp);
        } else if ("PUT".equals(method)) {
            doPut(req, resp);
        }
        // ... 其他方法
    }
}
```

**容器的调用流程**：
```java
public void processRequest(HttpServletRequest request, HttpServletResponse response) {
    // 1. 找到对应的Servlet实例
    String servletClassName = findServletForUrl(request.getRequestURI());
    Servlet servlet = getServletInstance(servletClassName);
    
    // 2. 调用service()方法
    // 注意：这里传入的是HttpServletRequest/HttpServletResponse
    // HttpServlet的service()方法会自动根据HTTP方法调用相应的doXXX()方法
    servlet.service(request, response);
}
```

### 4.6.5 完整流程示例

让我们通过一个具体例子跟踪整个过程：

**场景**：用户访问 `GET /user/list HTTP/1.1`

**容器内部执行流程**：
```
1. 📨 接收请求：容器在端口8080接收到HTTP请求
2. 🔍 解析请求：解析出方法="GET", URL路径="/user/list"
3. 🗺️ URL匹配：查找映射表，发现"/user/list"匹配模式"/user/*"
4. 📋 映射结果："/user/*" → "com.example.UserServlet"
5. 👤 获取实例：检查UserServlet是否已实例化，如果没有则：
   - 加载UserServlet类
   - 调用new UserServlet()创建实例
   - 调用servlet.init(config)进行初始化
6. 📞 方法调用：调用servlet.service(request, response)
   - service()方法内部检查HTTP方法是"GET"
   - 自动调用doGet(request, response)
7. 💻 业务执行：执行您编写的doGet()方法中的代码
8. 📤 返回响应：将响应发送回客户端
```

### 4.6.6 为什么这种设计很巧妙？

1. **职责分离**：容器负责路由和管理，Servlet专注业务逻辑
2. **扩展性**：添加新的URL映射只需修改配置，不需要改容器代码
3. **标准化**：所有Servlet都遵循相同的接口，可以互换使用
4. **性能优化**：Servlet实例复用避免了重复创建的开销

---

## 本章总结
通过这一章，我们详细探讨了基于Servlet规范实现一个最小Servlet容器的关键步骤。让我们回顾核心要点：

### 实现最小容器的必须组件：
1. **HTTP服务器基础**：能监听端口、解析HTTP协议、封装请求/响应对象。
2. **生命周期管理**：加载Servlet类、调用init()、service()、destroy()方法。
3. **请求路由**：实现URL到Servlet的映射，正确分发请求。
4. **规范接口实现**：至少实现ServletRequest、ServletResponse、ServletConfig等核心接口。

**Q: 自定义Servlet容器怎么知道转发到哪个Servlet代码？**
A: 通过预先建立的URL映射表（来自web.xml或注解），容器在启动时就知道了每个URL模式对应哪个Servlet类。请求到达时，容器用匹配算法查找对应的Servlet类名。

**Q: 怎么知道调用里面的哪些方法？**
A: 容器只调用Servlet的service()方法。HttpServlet的service()方法内部会根据HTTP请求方法（GET/POST等）自动调用相应的doGet()/doPost()等方法。这是HttpServlet类已经实现好的逻辑，您无需关心。

**关键洞察**：容器实际上很"笨"，它只是按照预设的映射规则和标准的接口调用流程工作。真正的"智能"在于Servlet规范的设计和HttpServlet的基础实现。

希望这个详细解释解决了您的疑惑！如果您想让我用代码示例进一步说明某个具体环节，请告诉我。

### 为什么这个练习有价值？
- **加深理解**：亲手实现能巩固您对容器工作原理的认识。
- **调试能力**：当使用成熟容器出问题时，您能更快定位原因。
- **教育意义**：不要用于生产，但作为学习工具极佳。

### 挑战与限制：
- 最小实现缺乏性能（无并发）、安全性、高级功能（如JSP）。
- 生产环境请使用Tomcat等成熟容器。

### 行动建议：
尝试用Java写一个最简单的容器：只支持一个硬编码的Servlet，处理GET请求。然后逐步扩展。这将让抽象概念变得具体。

在下一章，我们可以讨论如何深入学习Servlet容器，或探讨现代框架如何构建在容器之上。如果您有具体问题或想继续某个主题，请告诉我！

**思考题**：如果您的容器需要支持多个Web应用（每个应用有独立的ServletContext），您会如何设计？这涉及类加载器和隔离问题。

# 五、传统Servlet与Spring框架的深度对比及Spring Boot自动配置

## 5.1 传统Servlet开发模式详解

### 5.1.1 基本开发模式

在纯粹的Servlet开发中，每个功能端点都需要创建一个独立的Servlet类：

```java
// 用户管理Servlet
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // 手动解析参数
        String action = req.getParameter("action");
        String userId = req.getParameter("id");
        
        // 根据action参数进行路由（手动if-else判断）
        if ("list".equals(action)) {
            List<User> users = userService.getAllUsers();
            // 手动设置响应格式
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.write(new Gson().toJson(users));
        } else if ("detail".equals(action)) {
            User user = userService.getUserById(userId);
            // 手动处理视图渲染
            req.setAttribute("user", user);
            req.getRequestDispatcher("/user-detail.jsp").forward(req, resp);
        }
        // 更多if-else分支...
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // 类似的重复代码...
    }
}
```

### 5.1.2 配置文件依赖

每个Servlet都需要在web.xml中显式配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         version="3.1">
    
    <!-- 用户管理Servlet配置 -->
    <servlet>
        <servlet-name>UserServlet</servlet-name>
        <servlet-class>com.example.UserServlet</servlet-class>
        <init-param>
            <param-name>config</param-name>
            <param-value>user-config.properties</param-value>
        </init-param>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>UserServlet</servlet-name>
        <url-pattern>/user</url-pattern>
    </servlet-mapping>
    
    <!-- 商品管理Servlet配置 -->
    <servlet>
        <servlet-name>ProductServlet</servlet-name>
        <servlet-class>com.example.ProductServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>ProductServlet</servlet-name>
        <url-pattern>/product</url-pattern>
    </servlet-mapping>
    
    <!-- 更多Servlet配置... -->
</web-app>
```

### 5.1.3 传统模式的痛点分析

1. **代码重复严重**：每个Servlet都需要重复的参数解析、异常处理代码
2. **配置繁琐**：每新增一个功能都要修改web.xml
3. **URL设计不灵活**：URL模式受限，难以实现RESTful风格
4. **测试困难**：Servlet严重依赖容器环境，单元测试复杂
5. **关注点混合**：业务逻辑与基础设施代码混杂

## 5.2 Spring MVC框架的革命性改进

### 5.2.1 前端控制器模式

Spring MVC采用单一入口点的设计：

```java
// Spring的核心 - DispatcherServlet（框架提供）
public class DispatcherServlet extends FrameworkServlet {
    
    // 核心分发方法
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 1. 查找匹配的处理器
        HandlerExecutionChain mappedHandler = getHandler(request);
        
        // 2. 获取处理器适配器
        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
        
        // 3. 执行拦截器前置处理
        if (!mappedHandler.applyPreHandle(request, response)) {
            return;
        }
        
        // 4. 实际调用处理器方法（关键步骤！）
        ModelAndView mv = ha.handle(request, response, mappedHandler.getHandler());
        
        // 5. 应用默认视图名
        applyDefaultViewName(request, mv);
        
        // 6. 执行拦截器后置处理
        mappedHandler.applyPostHandle(request, response, mv);
        
        // 7. 处理分发结果
        processDispatchResult(request, response, mappedHandler, mv, null);
    }
}
```

### 5.2.2 现代Spring开发模式

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // 获取用户列表 - RESTful风格
    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String department) {
        if (department != null) {
            return userService.getUsersByDepartment(department);
        }
        return userService.getAllUsers();
    }
    
    // 获取用户详情
    @GetMapping("/{userId}")
    public User getUserDetail(@PathVariable String userId) {
        return userService.getUserById(userId);
    }
    
    // 创建用户
    @PostMapping
    public User createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(request);
    }
    
    // 导出用户统计Excel（您提到的例子）
    @PostMapping("/exportExcel")
    public void exportUserExcel(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                               HttpServletResponse response) throws IOException {
        userService.exportUserExcel(startDate, endDate, response);
    }
}
```

### 5.2.3 Spring MVC的核心组件架构

```
HTTP Request
     ↓
DispatcherServlet (前端控制器)
     ↓
HandlerMapping (URL到方法的映射)
     ↓
HandlerAdapter (参数解析适配器)
     ↓
Controller Method (您的业务方法)
     ↓
ViewResolver (视图解析器)
     ↓
HTTP Response
```

## 5.3 传统Servlet vs Spring MVC详细对比

### 5.3.1 开发方式对比

| 方面 | 传统Servlet | Spring MVC |
|------|-------------|------------|
| **URL映射** | web.xml配置或@WebServlet注解 | @GetMapping, @PostMapping等注解 |
| **参数解析** | 手动从HttpServletRequest获取 | 自动绑定到方法参数 |
| **类型转换** | 手动转换(String→int等) | 自动转换(@DateTimeFormat等) |
| **异常处理** | try-catch或配置error-page | @ControllerAdvice统一处理 |
| **视图技术** | JSP直接渲染 | 多种视图技术支持(Thymeleaf等) |
| **测试难度** | 需要模拟Servlet容器 | 纯POJO，易于单元测试 |

### 5.3.2 代码量对比示例

**传统Servlet方式（约50行代码）**：
```java
public class UserExportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 手动解析参数
            String startDateStr = req.getParameter("startDate");
            String endDateStr = req.getParameter("endDate");
            
            // 手动类型转换
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);
            
            // 业务调用
            UserService service = new UserService();
            service.exportUserExcel(startDate, endDate, resp);
            
        } catch (Exception e) {
            // 手动异常处理
            resp.setStatus(500);
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }
}
```

**Spring MVC方式（约10行代码）**：
```java
@PostMapping("/exportExcel")
public void exportUserExcel(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                           HttpServletResponse response) throws IOException {
    userService.exportUserExcel(startDate, endDate, response);
}
```

### 5.3.3 架构思想对比

**传统Servlet：过程式编程**
- 关注"怎么做"（How）
- 命令式、步骤化
- 基础设施与业务逻辑耦合

**Spring MVC：声明式编程**  
- 关注"做什么"（What）
- 声明意图，框架处理实现
- 关注点分离，业务逻辑纯净

## 5.4 Spring Boot中DispatcherServlet的自动配置机制

### 5.4.1 为什么不需要web.xml？

Spring Boot通过以下机制取代了传统的web.xml配置：

1. **Servlet 3.0+ 规范**：支持编程式配置，无需web.xml
2. **自动配置**：基于条件化配置自动装配组件
3. **约定优于配置**：提供合理的默认值

### 5.4.2 DispatcherServlet自动配置流程

#### 步骤1：自动配置类触发

Spring Boot通过`spring-boot-autoconfigure`模块中的自动配置类实现：

```java
// Spring Boot自动配置的核心机制
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)  // 条件：Servlet环境
@ConditionalOnClass(DispatcherServlet.class)        // 条件：DispatcherServlet类存在
@AutoConfigureAfter(ServletWebServerFactoryAutoConfiguration.class)
public class DispatcherServletAutoConfiguration {
    
    // 配置DispatcherServlet Bean
    @Bean(name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    @ConditionalOnBean(MultipartConfigElement.class)
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        // 设置各种配置参数...
        dispatcherServlet.setDispatchOptionsRequest(true);
        return dispatcherServlet;
    }
    
    // 注册DispatcherServlet到Servlet容器
    @Bean(name = DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
    @ConditionalOnBean(value = DispatcherServlet.class, name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServletRegistrationBean dispatcherServletRegistration(
            DispatcherServlet dispatcherServlet) {
        
        // 创建注册Bean，映射路径为"/"
        DispatcherServletRegistrationBean registration = 
            new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        
        registration.setName(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
        registration.setLoadOnStartup(1);  // 启动时加载
        
        // 可以自定义Multipart配置等
        return registration;
    }
}
```

#### 步骤2：Servlet容器自动嵌入

Spring Boot自动嵌入Tomcat、Jetty或Undertow：

```java
// ServletWebServerFactoryAutoConfiguration - 自动配置内嵌服务器
@Configuration
@ConditionalOnClass(Servlet.class)
@EnableConfigurationProperties(ServerProperties.class)
public class ServletWebServerFactoryAutoConfiguration {
    
    @Bean
    @ConditionalOnClass(name = "org.apache.catalina.startup.Tomcat")
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
```

#### 步骤3：完整的自动配置流程

```
应用启动
    ↓
SpringApplication.run()
    ↓
检测到Servlet环境 → 触发ServletWebServerFactoryAutoConfiguration
    ↓
创建内嵌Tomcat实例
    ↓
触发DispatcherServletAutoConfiguration
    ↓
创建DispatcherServlet Bean
    ↓
创建DispatcherServletRegistrationBean
    ↓
将DispatcherServlet注册到内嵌Tomcat，映射路径为"/"
    ↓
启动内嵌服务器，监听指定端口
```

### 5.4.3 自定义配置方式

虽然Spring Boot提供了自动配置，但仍然支持自定义：

**方式1：通过application.properties配置**
```properties
# 自定义DispatcherServlet路径映射
spring.mvc.servlet.path=/api/*

# 自定义服务器端口
server.port=8081

# 自定义Servlet相关参数
server.servlet.context-path=/myapp
```

**方式2：编程式配置**
```java
@Configuration
public class WebConfig {
    
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistration(
            DispatcherServlet dispatcherServlet) {
        
        DispatcherServletRegistrationBean registration = 
            new DispatcherServletRegistrationBean(dispatcherServlet, "/api/*");
        registration.setName("customDispatcher");
        registration.setLoadOnStartup(1);
        return registration;
    }
}
```

**方式3：完全自定义DispatcherServlet**
```java
@Bean
public DispatcherServlet dispatcherServlet() {
    DispatcherServlet servlet = new DispatcherServlet();
    
    // 自定义配置
    servlet.setThrowExceptionIfNoHandlerFound(true);
    servlet.setDispatchOptionsRequest(false);
    
    return servlet;
}
```

## 5.5 设计哲学演进总结

### 5.5.1 从传统到现代的演进路径

```
传统Servlet (1999)
    ↓ 手动配置、代码重复
Struts等早期框架 (2001)
    ↓ 配置仍然繁琐
Spring MVC (2004)  
    ↓ 注解驱动、配置简化
Spring Boot (2014)
    ↓ 自动配置、开箱即用
```

### 5.5.2 核心价值提升

1. **开发效率**：从每功能50行代码 → 10行代码
2. **维护性**：从分散配置 → 集中声明
3. **可测试性**：从容器依赖 → 纯POJO测试
4. **部署简化**：从WAR包部署 → 可执行JAR

### 5.5.3 现代Web开发最佳实践

通过Spring Boot的自动配置机制，开发者可以：
- 零XML配置启动完整Web应用
- 专注于业务逻辑而非基础设施
- 享受生产级特性（监控、健康检查等）
- 快速响应需求变化

## 5.6 总结

本章深入对比了传统Servlet开发与Spring MVC框架的差异，并详细解析了Spring Boot中DispatcherServlet的自动配置机制。关键要点：

1. **传统Servlet开发**虽然直接但繁琐，适合理解底层原理
2. **Spring MVC框架**通过前端控制器模式大幅简化开发
3. **Spring Boot自动配置**彻底消除了配置工作，实现开箱即用
4. **演进趋势**是从命令式编程向声明式编程，从显式配置向约定优于配置

理解这一演进过程有助于我们既掌握底层原理，又能高效运用现代框架，在遇到问题时能够快速定位根本原因。

# 六、Servlet容器详解 - Tomcat
## 6.1 知识体系
- 结构图
![结构图](../../assets/images/04-主流框架/Servlet容器/1.tomcat-x-design-2-1.jpeg)
- 初始化和启动流程
![初始化和启动流程](../../assets/images/04-主流框架/Servlet容器/2.tomcat-x-start-1.png)
## 6.2 Tomcat - 如何设计一个简单的web容器
> 在学习Tomcat前，很多人先入为主的对它的认知是巨复杂的；所以第一步，在学习它之前，要打破这种观念，我们通过学习如何设计一个最基本的web容器来看它需要考虑什么；进而在真正学习Tomcat时，多把重点放在它的顶层设计上，而不是某一块代码上, 思路永远比具体实现重要的多。
### 6.2.1 基础认知：如何实现服务器和客户端（浏览器）的交互
> 客户端和服务器端之间的交互式通过Socket来实现的，它属于应用层的协议。
#### 6.2.1.1 Socket
Socket是网络连接的一个端点。套接字使得一个应用可以从网络中读取和写入数据。放在两 个不同计算机上的两个应用可以通过连接发送和接受字节流。为了从你的应用发送一条信息到另 一个应用，你需要知道另一个应用的 IP 地址和套接字端口。在 Java 里边，套接字指的是`java.net.Socket`类。

要创建一个套接字，你可以使用 Socket 类众多构造方法中的一个。其中一个接收主机名称 和端口号:
```java
public Socket (java.lang.String host, int port)
```
在这里主机是指远程机器名称或者 IP 地址，端口是指远程应用的端口号。例如，要连接 yahoo.com 的 80 端口，你需要构造以下的 Socket 对象:
```java
new Socket ("yahoo.com", 80);
```
一旦你成功创建了一个 Socket 类的实例，你可以使用它来发送和接受字节流。要发送字节 流，你首先必须调用Socket类的getOutputStream方法来获取一个`java.io.OutputStream`对象。 要 发 送 文 本 到 一 个 远 程 应 用 ， 你 经 常 要 从 返 回 的 OutputStream 对 象 中 构 造 一 个 java.io.PrintWriter 对象。要从连接的另一端接受字节流，你可以调用 Socket 类的 getInputStream 方法用来返回一个`java.io.InputStream`对象。
![3.tomcat-x-design-6.png](../../assets/images/04-主流框架/Servlet容器/3.tomcat-x-design-6.png)
#### 6.2.1.2 SeverSocket
Socket 类代表一个**客户端套接字**，即任何时候你想连接到一个远程服务器应用的时候你构造的套接字，现在，假如你想实施一个服务器应用，例如一个 HTTP 服务器或者 FTP 服务器，你需要一种不同的做法。这是因为你的服务器必须随时待命，因为它不知道一个客户端应用什么时候会尝试去连接它。为了让你的应用能随时待命，你需要使用`java.net.ServerSocket`类。这是**服务器套接字**的实现。

`ServerSocket` 和 `Socket` 不同，服务器套接字的角色是等待来自客户端的连接请求。**一旦服务器套接字获得一个连接请求，它创建一个 Socket 实例来与客户端进行通信。**

要创建一个服务器套接字，你需要使用 ServerSocket 类提供的四个构造方法中的一个。你 需要指定 IP 地址和服务器套接字将要进行监听的端口号。通常，IP 地址将会是 127.0.0.1，也 就是说，服务器套接字将会监听本地机器。服务器套接字正在监听的 IP 地址被称为是绑定地址。 服务器套接字的另一个重要的属性是 backlog，这是服务器套接字开始拒绝传入的请求之前，传 入的连接请求的最大队列长度。

其中一个 ServerSocket 类的构造方法如下所示:
```java
public ServerSocket(int port, int backLog, InetAddress bindingAddress);
```
### 6.2.2 一个简单web容器的设计和实现：对静态资源
> 准备，这个例子来源于《How Tomcat Works》, 可以从这里下载源码.注意：当你跑如下程序时，可能会由于浏览器新版本不再支持的HTTP 0.9协议，而造成浏览器页面没有返回信息。 

#### 6.2.2.1 组件设计
根据上述的基础，我们可以看到，我们只需要提供三个最基本的类，分别是：
- Request - 表示请求，这里表示浏览器发起的HTTP请求
- HttpServer - 表示处理请求的服务器，同时这里使用我们上面铺垫的ServerSocket
- Reponse - 表示处理请求后的响应， 这里表示服务器对HTTP请求的响应结果
![4.tomcat-x-design-1.png](../../assets/images/04-主流框架/Servlet容器/4.tomcat-x-design-1.png)
#### 6.2.2.2 组件实现
从上图中我们可以看到，组织这几个类的入口在Server的启动方法中，即main方法中, 所以我们透过main方法从Server类进行分析：
- Server是如何启动的？
```java
public class HttpServer {

  // 存放静态资源的位置
  public static final String WEB_ROOT =
    System.getProperty("user.dir") + File.separator  + "webroot";

  // 关闭Server的请求
  private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

  // 是否关闭Server
  private boolean shutdown = false;

  // 主入口
  public static void main(String[] args) {
    HttpServer server = new HttpServer();
    server.await();
  }

  public void await() {
    // 启动ServerSocket
    ServerSocket serverSocket = null;
    int port = 8080;
    try {
      serverSocket =  new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    }
    catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // 循环等待一个Request请求
    while (!shutdown) {
      Socket socket = null;
      InputStream input = null;
      OutputStream output = null;
      try {
        // 创建socket
        socket = serverSocket.accept();
        input = socket.getInputStream();
        output = socket.getOutputStream();

        // 封装input至request, 并处理请求
        Request request = new Request(input);
        request.parse();

        // 封装output至response
        Response response = new Response(output);
        response.setRequest(request);
        response.sendStaticResource();

        // 关闭socket
        socket.close();

        // 如果接受的是关闭请求，则设置关闭监听request的标志
        shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
      }
      catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
```
- Request请求是如何封装和处理的？
```java
public class Request {

  private InputStream input;
  private String uri;

  // 初始化Request
  public Request(InputStream input) {
    this.input = input;
  }

  // 处理request的方法
  public void parse() {
    // 从socket中读取字符
    StringBuffer request = new StringBuffer(2048);
    int i;
    byte[] buffer = new byte[2048];
    try {
      i = input.read(buffer);
    }
    catch (IOException e) {
      e.printStackTrace();
      i = -1;
    }
    for (int j=0; j<i; j++) {
      request.append((char) buffer[j]);
    }
    System.out.print(request.toString());

    // 获得两个空格之间的内容, 这里将是HttpServer.WEB_ROOT中静态文件的文件名称
    uri = parseUri(request.toString());
  }

  private String parseUri(String requestString) {
    int index1, index2;
    index1 = requestString.indexOf(' ');
    if (index1 != -1) {
      index2 = requestString.indexOf(' ', index1 + 1);
      if (index2 > index1)
        return requestString.substring(index1 + 1, index2);
    }
    return null;
  }

  public String getUri() {
    return uri;
  }

}
```
- Response中响应了什么？
```java
public class Response {

  private static final int BUFFER_SIZE = 1024;
  Request request;
  OutputStream output;

  public Response(OutputStream output) {
    this.output = output;
  }

  // response中封装了request，以便获取request中的请求参数
  public void setRequest(Request request) {
    this.request = request;
  }

  public void sendStaticResource() throws IOException {
    byte[] bytes = new byte[BUFFER_SIZE];
    FileInputStream fis = null;
    try {
      // 读取文件内容
      File file = new File(HttpServer.WEB_ROOT, request.getUri());
      if (file.exists()) {
        fis = new FileInputStream(file);
        int ch = fis.read(bytes, 0, BUFFER_SIZE);
        while (ch!=-1) {
          output.write(bytes, 0, ch);
          ch = fis.read(bytes, 0, BUFFER_SIZE);
        }
      }
      else {
        // 文件不存在时，输出404信息
        String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
          "Content-Type: text/html\r\n" +
          "Content-Length: 23\r\n" +
          "\r\n" +
          "<h1>File Not Found</h1>";
        output.write(errorMessage.getBytes());
      }
    }
    catch (Exception e) {
      // thrown if cannot instantiate a File object
      System.out.println(e.toString() );
    }
    finally {
      if (fis!=null)
        fis.close();
    }
  }
}
```
- 启动输出

当我们run上面HttpServer中的main方法之后，我们就可以打开浏览器http://localhost:8080, 后面添加参数看返回webroot目录中静态文件的内容了(比如这里我加了hello.txt文件到webroot下，并访问http://localhost:8080/hello.txt)。
![5.tomcat-x-design-5.png](../../assets/images/04-主流框架/Servlet容器/5.tomcat-x-design-5.png)
![6.tomcat-x-design-4.png](../../assets/images/04-主流框架/Servlet容器/6.tomcat-x-design-4.png)
### 6.2.3 一个简单web容器的设计和实现：对Servelet
上面这个例子是不是很简单？是否打破了对一个简单http服务器的认知，减少了对它的恐惧。

但是上述的例子中只处理了静态资源，我们如果要处理Servlet呢？
#### 6.2.3.1 组件设计
不难发现，我们只需要在HttpServer只需要请求的处理委托给ServletProcessor, 让它接受请求，并处理Response即可。
![7.tomcat-x-design-2.png](../../assets/images/04-主流框架/Servlet容器/7.tomcat-x-design-2.png)
#### 6.2.3.2 组件实现
- 在HttpServer中
```java
public void await() {
    //....

        // create Response object
        Response response = new Response(output);
        response.setRequest(request);

        // 不再有response自己处理
        //response.sendStaticResource();

        // 而是如果以/servlet/开头，则委托ServletProcessor处理
        if (request.getUri().startsWith("/servlet/")) {
          ServletProcessor1 processor = new ServletProcessor1();
          processor.process(request, response);
        } else {
          // 原有的静态资源处理
          StaticResourceProcessor processor = new StaticResourceProcessor();
          processor.process(request, response);
        }

    // ....
  }
```
- ServletProcessor 如何处理的？
```java
public class ServletProcessor1 {

  public void process(Request request, Response response) {

    // 获取servlet名字
    String uri = request.getUri();
    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
    
    // 初始化URLClassLoader
    URLClassLoader loader = null;
    try {
      // create a URLClassLoader
      URL[] urls = new URL[1];
      URLStreamHandler streamHandler = null;
      File classPath = new File(Constants.WEB_ROOT);
      // the forming of repository is taken from the createClassLoader method in
      // org.apache.catalina.startup.ClassLoaderFactory
      String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString() ;
      // the code for forming the URL is taken from the addRepository method in
      // org.apache.catalina.loader.StandardClassLoader class.
      urls[0] = new URL(null, repository, streamHandler);
      loader = new URLClassLoader(urls);
    } catch (IOException e) {
      System.out.println(e.toString() );
    }

    // 用classLoader加载上面的servlet
    Class myClass = null;
    try {
      myClass = loader.loadClass(servletName);
    }
    catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    }

    // 将加载到的class转成Servlet，并调用service方法处理
    Servlet servlet = null;
    try {
      servlet = (Servlet) myClass.newInstance();
      servlet.service((ServletRequest) request, (ServletResponse) response);
    } catch (Exception e) {
      System.out.println(e.toString());
    } catch (Throwable e) {
      System.out.println(e.toString());
    }

  }
}
```
- Repsonse
```java
public class PrimitiveServlet implements Servlet {

  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }

  public void service(ServletRequest request, ServletResponse response)
    throws ServletException, IOException {
    System.out.println("from service");
    PrintWriter out = response.getWriter();
    out.println("Hello. Roses are red.");
    out.print("Violets are blue.");
  }

  public void destroy() {
    System.out.println("destroy");
  }

  public String getServletInfo() {
    return null;
  }
  public ServletConfig getServletConfig() {
    return null;
  }

}
```
- 访问 URL
![8.tomcat-x-design-8.png](../../assets/images/04-主流框架/Servlet容器/8.tomcat-x-design-8.png)

#### 6.2.3.3 利用外观模式改造
上述代码存在一个问题，
```java
// 将加载到的class转成Servlet，并调用service方法处理
    Servlet servlet = null;
    try {
      servlet = (Servlet) myClass.newInstance();
      servlet.service((ServletRequest) request, (ServletResponse) response);
    } catch (Exception e) {
      System.out.println(e.toString());
    } catch (Throwable e) {
      System.out.println(e.toString());
    }
```
这里直接处理将request和response传给servlet处理是不安全的，因为request可以向下转型为Request类，从而ServeletRequest便具备了访问Request中方法的能力。
```java
public class Request implements ServletRequest {
  // 一些public方法
}
public class Response implements ServletResponse {

}
```


- 先看一个简单的例子来理解

```java
// 假设我们有这样的类结构
interface Animal {
    void eat();
}

class Dog implements Animal {
    public void eat() { System.out.println("狗吃骨头"); }
    public void bark() { System.out.println("汪汪叫"); }  // Dog特有的方法
}

// 使用场景
public class Test {
    public static void main(String[] args) {
        // 向上转型：Dog → Animal
        Animal animal = new Dog();  // 编译时类型是Animal，运行时类型是Dog
        
        // 调用接口方法 - 正常
        animal.eat();  // 输出"狗吃骨头"
        
        // 尝试调用Dog特有方法 - 编译错误！
        // animal.bark();  // 编译错误：Animal接口没有bark()方法
        
        // 但是！我们可以向下转型回来
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;  // 向下转型成功！
            dog.bark();  // 现在可以调用特有方法了，输出"汪汪叫"
        }
    }
}
```

- 应用到Servlet场景

  -  当前的不安全情况

    ```java
    // 容器内部实现
    public class Request implements ServletRequest {
        // 标准方法
        public String getParameter(String name) { return "value"; }
        
        // 容器内部方法（危险！）
        public void setInternalState(String state) { 
            System.out.println("修改内部状态：" + state); 
        }
    }

    // 容器调用Servlet时的代码
    Request realRequest = new Request();  // 创建真实的Request对象
    Servlet servlet = new MaliciousServlet();

    // 关键步骤：向上转型为接口类型
    ServletRequest interfaceRequest = realRequest;  // Request → ServletRequest

    // 传递给Servlet
    servlet.service(interfaceRequest, response);
    ```

**问题就在这里**：虽然传递的是`ServletRequest`接口类型，但**实际对象仍然是Request类的实例**！

- Servlet开发者可以这样攻击：

```java
public class MaliciousServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 检查运行时类型
        System.out.println("req的实际类型：" + req.getClass().getName());
        // 输出：com.example.Request （这就是问题所在！）
        
        // 因为实际类型是Request，所以可以向下转型
        if (req instanceof Request) {  // 这个检查会通过！
            Request realRequest = (Request) req;  // 转型成功！
            
            // 现在可以调用容器的内部方法了
            realRequest.setInternalState("我被黑客修改了！");
        }
    }
}
```

- 为什么转型不会报错？

因为**Java的转型检查是基于运行时类型**的：

```java
Object obj = "我是字符串";      // 运行时类型是String
String str = (String) obj;     // 转型成功，因为obj实际上是String

Object obj2 = new Integer(100);
String str2 = (String) obj2;   // 运行时报错：ClassCastException
```

在您提到的不安全代码中：
- `req`的**编译时类型**是`ServletRequest`（接口）
- `req`的**运行时类型**是`Request`（具体类）
- `(Request) req`转型会成功，因为运行时类型匹配

- 解决方案：使用外观模式切断这种联系

```java
// 安全版本：使用外观类
public class RequestFacade implements ServletRequest {
    private ServletRequest request;
    
    public RequestFacade(ServletRequest request) {
        this.request = request;
    }
    
    // 只委托标准方法
    public String getParameter(String name) {
        return request.getParameter(name);
    }
    // ... 其他标准方法
}

// 容器调用时改为：
Request realRequest = new Request();
RequestFacade facade = new RequestFacade(realRequest);  // 包装一层

Servlet servlet = new MaliciousServlet();
servlet.service(facade, response);  // 传递外观对象
```

- 现在攻击会失败：

```java
public class MaliciousServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("req的实际类型：" + req.getClass().getName());
        // 输出：com.example.RequestFacade （不再是Request！）
        
        // 尝试转型会失败
        if (req instanceof Request) {  // 这个检查不会通过！
            Request realRequest = (Request) req;  // 永远不会执行到这里
        }
        
        // 即使强行转型也会报错
        try {
            Request realRequest = (Request) req;  // ClassCastException!
        } catch (ClassCastException e) {
            System.out.println("转型失败！无法访问容器内部方法");
        }
    }
}
```
- `关键理解：强转不改变对象本身`
**在Java中，类型转换只是改变引用的类型，不改变对象的实际类型：**

解决的方法便是通过外观模式进行改造：
![9.tomcat-x-design-3.png](../../assets/images/04-主流框架/Servlet容器/9.tomcat-x-design-3.png)
- RequestFacade为例
```java
public class RequestFacade implements ServletRequest {

  private ServletRequest request = null;

  public RequestFacade(Request request) {
    this.request = request;
  }

  /* implementation of the ServletRequest*/
  public Object getAttribute(String attribute) {
    return request.getAttribute(attribute);
  }

  public Enumeration getAttributeNames() {
    return request.getAttributeNames();
  }

  public String getRealPath(String path) {
    return request.getRealPath(path);
  }

...
```
- Process中由传入外观类
```java
Servlet servlet = null;
RequestFacade requestFacade = new RequestFacade(request); // 转换成外观类
ResponseFacade responseFacade = new ResponseFacade(response);// 转换成外观类
try {
  servlet = (Servlet) myClass.newInstance();
  servlet.service((ServletRequest) requestFacade, (ServletResponse) responseFacade);
}
catch (Exception e) {
  System.out.println(e.toString());
}
catch (Throwable e) {
  System.out.println(e.toString());
}
```
## 6.3 Tomcat - 理解Tomcat架构设计
> 前文我们已经介绍了一个简单的Servlet容器是如何设计出来，我们就可以开始正式学习Tomcat了，在学习开始，我们有必要站在高点去看看Tomcat的架构设计
### 6.3.1 Tomcat和Catalina是什么关系？
> Tomcat的前身为Catalina，Catalina又是一个轻量级的Servlet容器

Tomcat的前身为Catalina，Catalina又是一个轻量级的Servlet容器。在美国，catalina是一个很美的小岛。所以Tomcat作者的寓意可能是想把Tomcat设计成一个优雅美丽且轻量级的web服务器。Tomcat从4.x版本开始除了作为支持Servlet的容器外，额外加入了很多的功能，比如：jsp、el、naming等等，所以说Tomcat不仅仅是Catalina。
### 6.3.2 什么是Servlet？
> 所谓Servlet，其实就是Sun为了让Java能实现动态可交互的网页，从而进入Web编程领域而制定的一套标准！

在互联网兴起之初，当时的Sun公司（后面被Oracle收购）已然看到了这次机遇，于是设计出了Applet来对Web应用的支持。不过事实却并不是预期那么得好，Sun悲催地发现Applet并没有给业界带来多大的影响。经过反思，Sun就想既然机遇出现了，市场前景也非常不错，总不能白白放弃了呀，怎么办呢？于是又投入精力去搞一套规范出来，这时Servlet诞生了！

一个Servlet主要做下面三件事情：

- 创建并填充Request对象，包括：URI、参数、method、请求头信息、请求体信息等
- 创建Response对象
- 执行业务逻辑，将结果通过Response的输出流输出到客户端

**Servlet没有main方法，所以，如果要执行，则需要在一个容器里面才能执行，这个容器就是为了支持Servlet的功能而存在，Tomcat其实就是一个Servlet容器的实现。**

### 6.3.3 Tomcat 总结架构
下图应该是网上能找的最好的关于Tomcat的架构图了， 我们来看下它的构成：
![1.tomcat-x-design-2-1.jpeg](../../assets/images/04-主流框架/Servlet容器/1.tomcat-x-design-2-1.jpeg)
#### 6.3.3.1 从组件的角度看
- `Server`: 表示服务器，它提供了一种优雅的方式来启动和停止整个系统，不必单独启停连接器和容器；它是Tomcat构成的顶级构成元素，所有一切均包含在Server中；
- `Service`: 表示服务，Server可以运行多个服务。比如一个Tomcat里面可运行订单服务、支付服务、用户服务等等；Server的实现类StandardServer可以包含一个到多个Services, Service的实现类为StandardService调用了容器(Container)接口，其实是调用了Servlet Engine(引擎)，而且StandardService类中也指明了该Service归属的Server;
- `Container`: 表示容器，可以看做Servlet容器；引擎(Engine)、主机(Host)、上下文(Context)和Wraper均继承自Container接口，所以它们都是容器。
    - Engine -- 引擎
    - Host -- 主机
    - Context -- 上下文
    - Wrapper -- 包装器
- `Connector`: 表示连接器, **它将Service和Container连接起来**，首先它需要注册到一个Service，它的作用就是把来自客户端的请求转发到Container(容器)，这就是它为什么称作连接器, 它支持的协议如下：
  - 支持AJP协议
  - 支持Http协议
  - 支持Https协议
- `Service内部`还有各种支撑组件，下面简单罗列一下这些组件
  - Manager -- 管理器，用于管理会话
  - SessionLogger -- 日志器，用于管理日志
  - Loader -- 加载器，和类加载有关，只会开放给Context所使用
  - Pipeline -- 管道组件，配合Valve实现过滤器功能
  - Valve -- 阀门组件，配合Pipeline实现过滤器功能
  - Realm -- 认证授权组件
#### 6.3.3.2 从配置和模块对应角度
> 上述模块的理解不是孤立的，它直接映射为Tomcat的server.xml配置，让我们联系起来看
![10.tomcat-x-design-2-3.jpg](../../assets/images/04-主流框架/Servlet容器/10.tomcat-x-design-2-3.jpg)
1. server.xml - **容器级配置**
```xml
<!-- server.xml 示例 -->
<Server port="8005" shutdown="SHUTDOWN">
    <Service name="Catalina">
        <Connector port="8080" protocol="HTTP/1.1"/>
        <Engine name="Catalina" defaultHost="localhost">
            <Host name="localhost" appBase="webapps">
                <Context path="/myapp" docBase="/path/to/myapp"/>
            </Host>
        </Engine>
    </Service>
</Server>
```
**作用范围**：整个Tomcat服务器
**配置内容**：
- 服务器端口、关闭命令
- 连接器配置（HTTP、AJP等）
- 引擎、虚拟主机设置
- 全局资源（数据源、JNDI等）
- 阀门（Valve）和监听器

2. web.xml - **应用级配置**
```xml
<!-- web.xml 示例 -->
<web-app>
    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.example.MyServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/myservlet</url-pattern>
    </servlet-mapping>
    
    <filter>
        <filter-name>MyFilter</filter-name>
        <filter-class>com.example.MyFilter</filter-class>
    </filter>
</web-app>
```
**作用范围**：单个Web应用
**配置位置**：
- `CATALINA_HOME/conf/web.xml` - 全局默认配置
- `WEB-INF/web.xml` - 每个应用的特定配置
#### 6.3.3.3 从一个完整请求的角度来看
> 通过一个完整的HTTP请求，我们还需要把它贯穿起来

假设来自客户的请求为：http://localhost:8080/test/index.jsp 请求被发送到本机端口8080，被在那里侦听的Coyote HTTP/1.1 Connector,然后
- Connector把该请求交给它所在的Service的Engine来处理，并等待Engine的回应
- Engine获得请求localhost:8080/test/index.jsp，匹配它所有虚拟主机Host
- Engine匹配到名为localhost的Host(即使匹配不到也把请求交给该Host处理，因为该Host被定义为该Engine的默认主机)
- localhost Host获得请求/test/index.jsp，匹配它所拥有的所有Context
- Host匹配到路径为/test的Context(如果匹配不到就把该请求交给路径名为""的Context去处理)
- path="/test"的Context获得请求/index.jsp，在它的mapping table中寻找对应的servlet
- Context匹配到URL PATTERN为*.jsp的servlet，对应于JspServlet类，构造HttpServletRequest对象和HttpServletResponse对象，作为参数调用JspServlet的doGet或doPost方法
- Context把执行完了之后的HttpServletResponse对象返回给Host
- Host把HttpServletResponse对象返回给Engine
- Engine把HttpServletResponse对象返回给Connector
- Connector把HttpServletResponse对象返回给客户browser
#### 6.3.3.4 从源码的设计角度看
> 从功能的角度将Tomcat源代码分成5个子模块，分别是:
- `Jsper模块`: 这个子模块负责jsp页面的解析、jsp属性的验证，同时也负责将jsp页面动态转换为java代码并编译成class文件。在Tomcat源代码中，凡是属于org.apache.jasper包及其子包中的源代码都属于这个子模块;
- `Servlet和Jsp模块`: 这个子模块的源代码属于javax.servlet包及其子包，如我们非常熟悉的javax.servlet.Servlet接口、javax.servet.http.HttpServlet类及javax.servlet.jsp.HttpJspPage就位于这个子模块中;
- `Catalina模块`: 这个子模块包含了所有以org.apache.catalina开头的java源代码。该子模块的任务是规范了Tomcat的总体架构，定义了Server、Service、Host、Connector、Context、Session及Cluster等关键组件及这些组件的实现，**这个子模块大量运用了Composite（组合）设计模式**。同时也规范了Catalina的启动及停止等事件的执行流程。从代码阅读的角度看，这个子模块应该是我们阅读和学习的重点。
- `Connector模块`: 如果说上面三个子模块实现了Tomcat应用服务器的话，那么这个子模块就是Web服务器的实现。所谓连接器(Connector)就是一个连接客户和应用服务器的桥梁，它接收用户的请求，并把用户请求包装成标准的Http请求(包含协议名称，请求头Head，请求方法是Get还是Post等等)。同时，这个子模块还按照标准的Http协议，负责给客户端发送响应页面，比如在请求页面未发现时，connector就会给客户端浏览器发送标准的Http 404错误响应页面。
- `Resource模块`: 这个子模块包含一些资源文件，如Server.xml及Web.xml配置文件。严格说来，这个子模块不包含java源代码，但是它还是Tomcat编译运行所必需的。
#### 6.3.3.5 从后续深入理解的角度
- 基于组件的架构

我们知道组成Tomcat的是各种各样的组件，每个组件各司其职，组件与组件之间有明确的职责划分，同时组件与组件之间又通过一定的联系相互通信。Tomcat整体就是一个个组件的堆砌！
- 基于JMX

我们在后续阅读Tomcat源码的时候，会发现代码里充斥着大量的类似于下面的代码。
```java
Registry.getRegistry(null, null).invoke(mbeans, "init", false);
Registry.getRegistry(null, null).invoke(mbeans, "start", false);
```
而这实际上就是通过JMX来管理相应对象的代码。这儿我们不会详细讲述什么是JMX，我们只是简单地说明一下JMX的概念，参考JMX百度百科。
> JMX（Java Management Extensions，即Java管理扩展）是一个为应用程序、设备、系统等植入管理功能的框架。JMX可以跨越一系列异构操作系统平台、系统体系结构和网络传输协议，灵活的开发无缝集成的系统、网络和服务管理应用。
- 基于生命周期

如果我们查阅各个组件的源代码，会发现绝大多数组件实现了Lifecycle接口，这也就是我们所说的基于生命周期。生命周期的各个阶段的触发又是基于事件的方式。

### 补充
#### 1.Composite（组合）设计模式

##### 核心思想

Composite模式的核心思想是：**将对象组合成树形结构以表示“部分-整体”的层次结构。它使得用户对单个对象和组合对象的使用具有一致性。**

简单来说，就是**用相同的方式处理单个对象和由多个对象组成的组合对象**。

---

##### 一个生动的比喻：文件系统

要理解Composite模式，最好的例子就是计算机的**文件系统**：

- **单个对象**：**文件（File）**。它是树结构中的**叶子节点（Leaf）**，不能再包含其他东西。
- **组合对象**：**文件夹（Directory）**。它是树结构中的**树枝节点（Composite）**，里面可以包含文件（叶子），也可以包含其他文件夹（树枝），从而形成树形结构。
- **一致性操作**：无论是文件还是文件夹，你都可以执行一些共同的操作，比如：
    - `getSize()`：获取大小（文件返回自身大小，文件夹递归返回内部所有内容的大小之和）。
    - `open()` / `doubleClick()`：打开（文件是打开内容，文件夹是展开列表）。
    - `delete()`：删除。

对你来说，你不需要关心你操作的是文件还是文件夹，你只需要执行“打开”或“获取大小”这个命令即可。这就是Composite模式带来的巨大好处。

---

##### 模式的三大角色

Composite模式通常包含三个关键角色，我们用文件系统的例子来对应：

1.  **组件（Component）接口**
    - 这是整个模式的核心。它定义了叶子节点和树枝节点的**共同接口**（即共同的操作方法）。
    - **对应例子**：定义一个 `FileSystemNode` 接口，里面有 `getSize()`, `print()` 等方法。

2.  **叶子（Leaf）类**
    - 表示树结构中的末端节点（没有子节点）。它实现了组件接口。
    - **对应例子**：`File` 类，实现 `FileSystemNode` 接口。它的 `getSize()` 返回自己的大小，`print()` 打印自己的文件名。

3.  **复合（Composite）类**
    - 表示有子节点的复杂组件。它包含一个子组件（可以是Leaf，也可以是另一个Composite）的集合，并实现了在组件接口中定义的方法。这些方法通常会委托给它的子组件去执行（比如递归调用）。
    - **对应例子**：`Directory` 类，实现 `FileSystemNode` 接口。它内部有一个 `List<FileSystemNode>` 来存放文件和子文件夹。它的 `getSize()` 会遍历所有子节点，将它们的 `getSize()` 结果相加。

---

##### 代码示例

下面我们用Java代码来实现上面提到的文件系统例子。

```java
import java.util.ArrayList;
import java.util.List;

// 1. 组件接口
interface FileSystemNode {
    String getName();
    long getSize();
    void print(String indent);
}

// 2. 叶子节点 - 文件
class File implements FileSystemNode {
    private String name;
    private long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size; // 文件直接返回自身大小
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "📄 " + name + " (" + size + " bytes)");
    }
}

// 3. 复合节点 - 文件夹
class Directory implements FileSystemNode {
    private String name;
    private List<FileSystemNode> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    // 关键方法：向文件夹中添加节点（文件或子文件夹）
    public void add(FileSystemNode node) {
        children.add(node);
    }

    // 关键方法：从文件夹中移除节点
    public void remove(FileSystemNode node) {
        children.remove(node);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        // 核心：递归计算所有子节点的大小之和
        for (FileSystemNode child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "📁 " + name + "/");
        // 核心：递归打印所有子节点，并增加缩进以示层次
        String newIndent = indent + "    ";
        for (FileSystemNode child : children) {
            child.print(newIndent);
        }
    }
}

// 4. 客户端使用
public class CompositeDemo {
    public static void main(String[] args) {
        // 创建文件
        File file1 = new File("readme.txt", 1024);
        File file2 = new File("image.png", 204800);
        File file3 = new File("script.py", 5120);

        // 创建子文件夹和根文件夹
        Directory subDir = new Directory("Documents");
        Directory rootDir = new Directory("MyComputer");

        // 构建树形结构
        subDir.add(file1);
        subDir.add(file2);

        rootDir.add(subDir);
        rootDir.add(file3);

        // 客户端统一对待所有节点，无需判断是文件还是文件夹
        System.out.println("=== 打印结构 ===");
        rootDir.print(""); // 根目录缩进为空

        System.out.println("\n=== 计算总大小 ===");
        System.out.println("Total size of '" + rootDir.getName() + "': " + rootDir.getSize() + " bytes");

        // 也可以单独操作子文件夹
        System.out.println("\n=== 子文件夹信息 ===");
        System.out.println("Size of '" + subDir.getName() "': " + subDir.getSize() + " bytes");
    }
}
```

**输出结果：**
```
=== 打印结构 ===
📁 MyComputer/
    📁 Documents/
        📄 readme.txt (1024 bytes)
        📄 image.png (204800 bytes)
    📄 script.py (5120 bytes)

=== 计算总大小 ===
Total size of 'MyComputer': 210944 bytes

=== 子文件夹信息 ===
Size of 'Documents': 205824 bytes
```

---

##### 优点

1.  **简化客户端代码**：客户端可以一致地处理单个对象和组合对象，无需关心自己处理的是何种对象，大大简化了代码。
2.  **易于增加新类型的组件**：要增加一个新的节点类型（比如“快捷方式”），只需要实现组件接口即可，符合“开闭原则”。
3.  **可以递归组合**：可以构建出非常复杂的树形结构。

##### 缺点

1.  **设计抽象性高**：要求过度抽象化。让组合节点和叶子节点具有完全一致的接口有时会很困难，因为有些操作对叶子节点可能没有意义（比如给一个“文件”添加子节点）。需要在叶子节点的相关方法中做特殊处理（如抛出异常）。

##### 实际应用场景

-   **GUI开发中的容器和组件**：例如，一个 `Window`（复合）可以包含 `Button`（叶子）、`TextField`（叶子），也可以包含另一个 `Panel`（复合）。
-   **菜单系统**：菜单（复合）可以包含菜单项（叶子），也可以包含子菜单（复合）。
-   **公司组织架构**：部门（复合）由员工（叶子）和子部门（复合）组成。
-   **任何树形数据结构**：如XML/HTML文档解析、表达式解析（表达式由子表达式和操作数组成）。

##### 总结

**Composite模式通过将部分与整体的关系用树形结构表示，并提供一致的操作接口，使得客户端代码能够以统一的方式处理简单元素和复杂元素。** 当你发现业务模型中存在明显的“部分-整体”层次关系，并且你希望忽略层次差异统一对待时，就应该考虑使用Composite模式。

#### 2. JMX（Java Management Extensions，Java管理扩展）

##### 什么是 JMX？

**JMX** 是 Java 管理扩展的缩写，它提供了一个标准的方式来**监控和管理 Java 应用程序、系统对象、设备和服务**。简单来说，JMX 就是 Java 平台的**管理和监控框架**。

##### 核心思想
JMX 的核心思想是：**将应用程序的管理功能封装成标准的 MBean（管理 Bean），然后通过统一的接口来访问和操作这些管理功能。**

---

##### 为什么需要 JMX？

想象一下这样的场景：
- 你的线上服务运行中，你想**动态修改日志级别**而不重启服务
- 你想**实时查看线程池状态**、数据库连接池使用情况
- 你想**动态调整缓存大小**、开关某些功能
- 你想**监控应用性能指标**（QPS、响应时间等）

**传统做法**：需要写很多监控接口，或者重启应用。
**JMX 做法**：通过标准的管理工具（如 JConsole）直接操作，无需额外开发。

---

##### JMX 的三层架构

JMX 架构分为三个层次：

###### 1. **Instrumentation Level（ instrumentation 层）**
- **核心**：**MBean（Managed Bean）**
- 作用：将资源（如应用组件）包装成可管理的对象
- 包含：Standard MBean、Dynamic MBean、Open MBean、Model MBean

###### 2. **Agent Level（代理层）**
- **核心**：**MBeanServer**
- 作用：MBean 的注册中心和操作中介
- 包含：MBeanServer、Agent Services

###### 3. **Remote Management Level（远程管理层）**
- **核心**：**Connectors 和 Adaptors**
- 作用：提供远程访问能力
- 例如：RMI Connector、HTML Adaptor

---

##### 核心概念详解

###### 1. **MBean（管理 Bean）**

MBean 是 JMX 的核心，代表一个可管理的资源。有几种类型：

###### **Standard MBean（最常用）**
```java
// 1. 定义 MBean 接口（命名规范：类名 + MBean）
public interface CalculatorMBean {
    // 属性（可读可写）
    void setMemory(int memory);
    int getMemory();
    
    // 操作（方法）
    int add(int a, int b);
    String getStatus();
}

// 2. 实现 MBean 接口
public class Calculator implements CalculatorMBean {
    private int memory = 0;
    
    @Override
    public void setMemory(int memory) {
        this.memory = memory;
    }
    
    @Override
    public int getMemory() {
        return memory;
    }
    
    @Override
    public int add(int a, int b) {
        return a + b;
    }
    
    @Override
    public String getStatus() {
        return "Running, memory: " + memory;
    }
}
```

###### **MXBean（推荐使用）**
```java
// MXBean 接口（命名规范：类名 + MXBean）
public interface SystemConfigMXBean {
    int getThreadCount();
    void setThreadCount(int count);
    String getSchemaName();
}

// 实现类
public class SystemConfig implements SystemConfigMXBean {
    private int threadCount = 100;
    private String schemaName = "default";
    
    @Override
    public int getThreadCount() { return threadCount; }
    
    @Override
    public void setThreadCount(int count) { 
        this.threadCount = count; 
    }
    
    @Override
    public String getSchemaName() { return schemaName; }
}
```

**MXBean 的优势**：更好的兼容性，支持复杂类型。

###### 2. **MBeanServer（MBean 服务器）**

MBeanServer 是 MBean 的注册中心和操作入口：

```java
import javax.management.*;
import java.lang.management.ManagementFactory;

public class JMXDemo {
    public static void main(String[] args) throws Exception {
        // 获取 MBeanServer
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        
        // 创建 ObjectName（唯一标识 MBean）
        ObjectName name = new ObjectName("com.example:type=SystemConfig");
        
        // 创建 MBean 实例并注册
        SystemConfig mbean = new SystemConfig();
        mbs.registerMBean(mbean, name);
        
        // 保持程序运行，以便通过管理工具连接
        System.out.println("JMX Server started...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
```

---

##### 实际应用示例：数据库连接池监控

让我们看一个更实际的例子：

```java
import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicInteger;

// MXBean 接口
public interface ConnectionPoolMXBean {
    int getActiveConnections();
    int getIdleConnections();
    int getMaxPoolSize();
    void setMaxPoolSize(int size);
    double getUsagePercentage();
    void resetStatistics();
}

// 连接池实现（简化版）
public class ConnectionPool implements ConnectionPoolMXBean {
    private final AtomicInteger activeConnections = new AtomicInteger(0);
    private final AtomicInteger idleConnections = new AtomicInteger(10);
    private volatile int maxPoolSize = 100;
    
    @Override
    public int getActiveConnections() {
        return activeConnections.get();
    }
    
    @Override
    public int getIdleConnections() {
        return idleConnections.get();
    }
    
    @Override
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
    
    @Override
    public void setMaxPoolSize(int size) {
        if (size > 0) {
            this.maxPoolSize = size;
            System.out.println("Max pool size changed to: " + size);
        }
    }
    
    @Override
    public double getUsagePercentage() {
        return (double) activeConnections.get() / maxPoolSize * 100;
    }
    
    @Override
    public void resetStatistics() {
        activeConnections.set(0);
        System.out.println("Statistics reset");
    }
    
    // 模拟业务方法
    public void getConnection() {
        if (activeConnections.get() < maxPoolSize) {
            activeConnections.incrementAndGet();
            idleConnections.decrementAndGet();
        }
    }
    
    public void releaseConnection() {
        activeConnections.decrementAndGet();
        idleConnections.incrementAndGet();
    }
}

// 启动 JMX 服务
public class ConnectionPoolJMXServer {
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        
        // 注册连接池 MBean
        ConnectionPool pool = new ConnectionPool();
        ObjectName poolName = new ObjectName("com.example:type=ConnectionPool,name=DatabasePool");
        mbs.registerMBean(pool, poolName);
        
        System.out.println("ConnectionPool JMX MBean registered!");
        System.out.println("Connect with JConsole to manage the connection pool");
        
        // 模拟使用
        while (true) {
            pool.getConnection();
            Thread.sleep(1000);
            pool.releaseConnection();
            Thread.sleep(1000);
        }
    }
}
```

---

##### 如何使用 JMX 管理工具

###### 1. **JConsole（Java 自带）**
```bash
# 启动应用时开启 JMX
java -Dcom.sun.management.jmxremote \
     -Dcom.sun.management.jmxremote.port=9999 \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -Dcom.sun.management.jmxremote.ssl=false \
     YourApplication

# 然后运行 jconsole 连接 localhost:9999
jconsole
```

###### 2. **JVisualVM（更强大）**
```bash
jvisualvm
```

###### 3. **通过代码访问**
```java
import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXClient {
    public static void main(String[] args) throws Exception {
        // 连接 JMX 服务
        JMXServiceURL url = new JMXServiceURL(
            "service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(url);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        
        // 访问 MBean
        ObjectName name = new ObjectName("com.example:type=ConnectionPool,name=DatabasePool");
        
        // 获取属性
        Integer activeConnections = (Integer) connection.getAttribute(name, "ActiveConnections");
        System.out.println("Active connections: " + activeConnections);
        
        // 调用操作
        connection.invoke(name, "resetStatistics", null, null);
        
        connector.close();
    }
}
```

---

##### Spring Boot 中的 JMX

Spring Boot 自动配置了 JMX 支持：

```java
@Component
@ManagedResource(objectName = "com.example:type=BusinessMetrics")
public class BusinessMetrics {
    private final AtomicInteger requestCount = new AtomicInteger(0);
    
    @ManagedAttribute(description = "Total request count")
    public int getRequestCount() {
        return requestCount.get();
    }
    
    @ManagedOperation(description = "Reset counters")
    public void reset() {
        requestCount.set(0);
    }
    
    public void incrementRequest() {
        requestCount.incrementAndGet();
    }
}
```

在 `application.properties` 中配置：
```properties
# 启用 JMX
spring.jmx.enabled=true
# JMX 域名
spring.jmx.default-domain=myapp
```

---

##### JMX 的优势和应用场景

###### 优势：
1. **标准化**：Java 平台标准，工具生态丰富
2. **动态性**：运行时修改配置，无需重启
3. **远程管理**：支持远程监控和操作
4. **集成性好**：与各种监控系统集成

###### 典型应用场景：
- **应用服务器监控**（Tomcat、WebLogic 等）
- **中间件管理**（缓存、消息队列）
- **性能监控**（线程池、连接池、内存使用）
- **动态配置**（日志级别、功能开关）
- **业务指标监控**（QPS、错误率等）

---

##### 总结

**JMX 是 Java 平台的标准化管理和监控框架**，它通过：

1. **MBean** 将资源包装成可管理对象
2. **MBeanServer** 提供统一的注册和操作中心  
3. **Connector/Adaptor** 支持远程访问

**核心价值**：让应用程序的监控和管理变得标准化、动态化和可视化。

在实际开发中，JMX 是构建可观测系统的重要工具，特别适合需要运行时管理和监控的企业级应用。

您可以把 JMX 架构想象成一个 **“标准化的管理插座”**：

- **您的应用**：提供了一个**标准插座（MBeanServer + MBean）**
- **各种管理工具**：都带有**标准插头（JMX 客户端）**

只要插座符合规范，任何带标准插头的电器（工具）都能即插即用。

---

- 外部工具如何“即插即用”

1. **自动发现和连接**
当您启动应用并开启 JMX 远程访问后（例如使用 `-Dcom.sun.management.jmxremote` 参数），支持 JMX 的工具只需要知道应用的 **主机名** 和 **JMX 端口**，就能自动：
- **发现**所有已注册的 MBean
- **读取**它们的属性（getter 方法）
- **展示**操作方法（非 getter/setter 的方法）
- **动态调用**这些操作

2. **统一的展示界面**
工具会根据 MBean 的元数据自动生成管理界面：

- **属性面板**：自动将 `getXXX()` 方法识别为可读属性，将 `setXXX()` 方法识别为可写属性
- **操作按钮**：将其他公共方法显示为可执行的操作按钮
- **类型识别**：自动处理基本类型、集合、复合对象等

3. **实时监控和交互**
- **实时读取**：工具可以定期轮询属性值，显示实时曲线图
- **动态修改**：用户可以在界面中直接修改属性值（触发 setter 方法）
- **即时操作**：点击按钮即可调用 MBean 的方法

- 具体工具演示

 **JConsole（Java 自带）**
当您用 JConsole 连接后，它会自动展示：

1. **MBean 标签页** → 按域名组织所有 MBean
2. **选择您的 MBean**（如 `com.example:type=ConnectionPool`） →
   - **属性**：显示 `ActiveConnections`, `IdleConnections`, `MaxPoolSize` 等，可编辑的会有输入框
   - **操作**：显示 `resetStatistics()` 等方法，点击即可执行

 **JVisualVM（安装 MBean 插件后）**
提供更丰富的界面：
- **图表展示**：属性值随时间变化的曲线图
- **操作日志**：记录所有的属性修改和方法调用
- **更友好的界面**：表格形式展示属性，大按钮执行操作

**专业监控系统**
如 **Prometheus + JMX Exporter**：
```yaml
# jmx_exporter 配置文件
rules:
- pattern: "com.example:type=ConnectionPool,name=DatabasePool"
  name: "connection_pool_$1"
  attributes:
    ActiveConnections: 
      alias: "active_connections"
      type: "gauge"
    MaxPoolSize:
      alias: "max_pool_size" 
      type: "gauge"
```

这样就能将 JMX 数据自动转换为 Prometheus 指标，在 Grafana 中展示漂亮的监控面板。
## 6.4 Tomcat - 源码分析准备和分析入口
### 6.4.1 源代码下载和编译
首先是去官网下载Tomcat的源代码和二进制安装包，我这里分析最新的<a href = 'https://tomcat.apache.org/download-90.cgi'>Tomcat9.0.39稳定版本</a>
### 6.4.2 下载二进制包和源码
> 下载二进制包的主要目的在于，让我们回顾一下包中的内容；其次，在我们后面通过源码包编译后，以方便和二进制包进行对比。
- 下载两个包
![11.tomcat-x-sourcecode-2.png](../../assets/images/04-主流框架/Servlet容器/11.tomcat-x-sourcecode-2.png)
- 查看二进制包中主要模块
![12.tomcat-x-sourcecode-3.png](../../assets/images/04-主流框架/Servlet容器/12.tomcat-x-sourcecode-3.png)
### 6.4.3 编译源码
- 导入IDEA
![13.tomcat-x-sourcecode-4.png](../../assets/images/04-主流框架/Servlet容器/13.tomcat-x-sourcecode-4.png)
- 使用ant编译
![14.tomcat-x-sourcecode-1.png](../../assets/images/04-主流框架/Servlet容器/14.tomcat-x-sourcecode-1.png)

### 6.4.4 理解编译后模块
> 这里有两点要注意下：第一：在编译完之后，编译输出到哪里了呢？第二：编译后的结果是不是和我们下载的二进制文件对的上呢？
- 编译的输出在哪里
![15.tomcat-x-sourcecode-5.png](../../assets/images/04-主流框架/Servlet容器/15.tomcat-x-sourcecode-5.png)
- 编译的输出结果是否对的上，很显然和上面的二进制包一致
![16.tomcat-x-sourcecode-6.png](../../assets/images/04-主流框架/Servlet容器/16.tomcat-x-sourcecode-6.png)
### 6.4.5 从启动脚本定位Tomcat源码入口
#### 6.4.5.1 startup.bat
> 当我们初学tomcat的时候, 肯定先要学习怎样启动tomcat. 在tomcat的bin目录下有两个启动tomcat的文件, 一个是startup.bat, 它用于windows环境下启动tomcat; 另一个是startup.sh, 它用于linux环境下tomcat的启动. 两个文件中的逻辑是一样的, 我们只分析其中的startup.bat.
- startup.bat的源码: **startup.bat文件实际上就做了一件事情: 启动catalina.bat.**
```sh
@echo off
rem Licensed to the Apache Software Foundation (ASF) under one or more
rem contributor license agreements.  See the NOTICE file distributed with
rem this work for additional information regarding copyright ownership.
rem The ASF licenses this file to You under the Apache License, Version 2.0
rem (the "License"); you may not use this file except in compliance with
rem the License.  You may obtain a copy of the License at
rem
rem     http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS,
rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
rem See the License for the specific language governing permissions and
rem limitations under the License.

rem ---------------------------------------------------------------------------
rem Start script for the CATALINA Server
rem ---------------------------------------------------------------------------

setlocal

rem Guess CATALINA_HOME if not defined
set "CURRENT_DIR=%cd%"
if not "%CATALINA_HOME%" == "" goto gotHome
set "CATALINA_HOME=%CURRENT_DIR%"
if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
cd ..
set "CATALINA_HOME=%cd%"
cd "%CURRENT_DIR%"
:gotHome
if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
echo The CATALINA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome

set "EXECUTABLE=%CATALINA_HOME%\bin\catalina.bat"

rem Check that target executable exists
if exist "%EXECUTABLE%" goto okExec
echo Cannot find "%EXECUTABLE%"
echo This file is needed to run this program
goto end
:okExec

rem Get remaining unshifted command line arguments and save them in the
set CMD_LINE_ARGS=
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs

call "%EXECUTABLE%" start %CMD_LINE_ARGS%

:end
```
- 当然如果你感兴趣，不妨也可以看下上面脚本的含义
  - .bat文件中@echo是打印指令, 用于控制台输出信息, rem是注释符.
  - 跳过开头的注释, 我们来到配置CATALINA_HOME的代码段, 执行startup.bat文件首先会设置CATALINA_HOME.
    ```sh
    set "CURRENT_DIR=%cd%"
    if not "%CATALINA_HOME%" == "" goto gotHome
    set "CATALINA_HOME=%CURRENT_DIR%"
    if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
    cd ..
    set "CATALINA_HOME=%cd%"
    cd "%CURRENT_DIR%"
    :gotHome
    if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
    echo The CATALINA_HOME environment variable is not defined correctly
    echo This environment variable is needed to run this program
    goto end
    :okHome
    ```
  - 先通过set指令把当前目录设置到一个名为CURRENT_DIR的变量中,
  - 如果系统中配置过CATALINA_HOME则跳到gotHome代码段. 正常情况下我们的电脑都没有配置CATALINA_HOME, 所以往下执行, 把当前目录设置为CATALINA_HOME.
  - 然后判断CATALINA_HOME目录下是否存在catalina.bat文件, 如果存在就跳到okHome代码块.
  - 在okHome中, 会把catalina.bat文件的的路径赋给一个叫EXECUTABLE的变量, 然后会进一步判断这个路径是否存在, 存在则跳转到okExec代码块, 不存在的话会在控制台输出一些错误信息.
  - 在okExec中, 会把setArgs代码块的返回结果赋值给CMD_LINE_ARGS变量, 这个变量用于存储启动参数.
  - setArgs中首先会判断是否有参数, (if ""%1""==""""判断第一个参数是否为空), 如果没有参数则相当于参数项为空. 如果有参数则循环遍历所有的参数(每次拼接第一个参数).
  - 最后执行call "%EXECUTABLE%" start %CMD_LINE_ARGS%, 也就是说执行catalina.bat文件, 如果有参数则带上参数.
> 这样看来, 在windows下启动tomcat未必一定要通过startup.bat, 用catalina.bat start也是可以的.
#### 6.4.5.2 catalina.bat
- 跳过开头的注释, 我们来到下面的代码段:
```sh
setlocal

rem Suppress Terminate batch job on CTRL+C
if not ""%1"" == ""run"" goto mainEntry
if "%TEMP%" == "" goto mainEntry
if exist "%TEMP%\%~nx0.run" goto mainEntry
echo Y>"%TEMP%\%~nx0.run"
if not exist "%TEMP%\%~nx0.run" goto mainEntry
echo Y>"%TEMP%\%~nx0.Y"
call "%~f0" %* <"%TEMP%\%~nx0.Y"
rem Use provided errorlevel
set RETVAL=%ERRORLEVEL%
del /Q "%TEMP%\%~nx0.Y" >NUL 2>&1
exit /B %RETVAL%
:mainEntry
del /Q "%TEMP%\%~nx0.run" >NUL 2>&1
```
- 大多情况下我们启动tomcat都没有设置参数, 所以直接跳到mainEntry代码段, 删除了一个临时文件后, 继续往下执行.
```sh
rem Guess CATALINA_HOME if not defined
set "CURRENT_DIR=%cd%"
if not "%CATALINA_HOME%" == "" goto gotHome
set "CATALINA_HOME=%CURRENT_DIR%"
if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
cd ..
set "CATALINA_HOME=%cd%"
cd "%CURRENT_DIR%"
:gotHome

if exist "%CATALINA_HOME%\bin\catalina.bat" goto okHome
echo The CATALINA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome

rem Copy CATALINA_BASE from CATALINA_HOME if not defined
if not "%CATALINA_BASE%" == "" goto gotBase
set "CATALINA_BASE=%CATALINA_HOME%"
```
- 可以看到这段代码与startup.bat中开头的代码相似, 在确定CATALINA_HOME下有catalina.bat后把CATALINA_HOME赋给变量CATALINA_BASE.
```sh
rem Get standard environment variables
if not exist "%CATALINA_BASE%\bin\setenv.bat" goto checkSetenvHome
call "%CATALINA_BASE%\bin\setenv.bat"
goto setenvDone
:checkSetenvHome
if exist "%CATALINA_HOME%\bin\setenv.bat" call "%CATALINA_HOME%\bin\setenv.bat"
:setenvDone

rem Get standard Java environment variables
if exist "%CATALINA_HOME%\bin\setclasspath.bat" goto okSetclasspath
echo Cannot find "%CATALINA_HOME%\bin\setclasspath.bat"
echo This file is needed to run this program
goto end
:okSetclasspath
call "%CATALINA_HOME%\bin\setclasspath.bat" %1
if errorlevel 1 goto end

rem Add on extra jar file to CLASSPATH
rem Note that there are no quotes as we do not want to introduce random
rem quotes into the CLASSPATH
if "%CLASSPATH%" == "" goto emptyClasspath
set "CLASSPATH=%CLASSPATH%;"
:emptyClasspath
set "CLASSPATH=%CLASSPATH%%CATALINA_HOME%\bin\bootstrap.jar"
```
> 上面这段代码依次执行了setenv.bat和setclasspath.bat文件, 目的是获得CLASSPATH, 相信会Java的同学应该都会在配置环境变量时都配置过classpath, 系统拿到classpath路径后把它和CATALINA_HOME拼接在一起, 最终定位到一个叫bootstrap.jar的文件. 虽然后面还有很多代码, 但是这里必须暂停提示一下: bootstrap.jar将是我们启动tomcat的环境.
- 接下来从gotTmpdir代码块到noEndorsedVar代码块进行了一些配置, 由于不是主要内容暂且跳过.
```sh
echo Using CATALINA_BASE:   "%CATALINA_BASE%"
echo Using CATALINA_HOME:   "%CATALINA_HOME%"
echo Using CATALINA_TMPDIR: "%CATALINA_TMPDIR%"
if ""%1"" == ""debug"" goto use_jdk
echo Using JRE_HOME:        "%JRE_HOME%"
goto java_dir_displayed
:use_jdk
echo Using JAVA_HOME:       "%JAVA_HOME%"
:java_dir_displayed
echo Using CLASSPATH:       "%CLASSPATH%"

set _EXECJAVA=%_RUNJAVA%
set MAINCLASS=org.apache.catalina.startup.Bootstrap
set ACTION=start
set SECURITY_POLICY_FILE=
set DEBUG_OPTS=
set JPDA=

if not ""%1"" == ""jpda"" goto noJpda
set JPDA=jpda
if not "%JPDA_TRANSPORT%" == "" goto gotJpdaTransport
set JPDA_TRANSPORT=dt_socket
:gotJpdaTransport
if not "%JPDA_ADDRESS%" == "" goto gotJpdaAddress
set JPDA_ADDRESS=8000
:gotJpdaAddress
if not "%JPDA_SUSPEND%" == "" goto gotJpdaSuspend
set JPDA_SUSPEND=n
:gotJpdaSuspend
if not "%JPDA_OPTS%" == "" goto gotJpdaOpts
set JPDA_OPTS=-agentlib:jdwp=transport=%JPDA_TRANSPORT%,address=%JPDA_ADDRESS%,server=y,suspend=%JPDA_SUSPEND%
:gotJpdaOpts
shift
:noJpda

if ""%1"" == ""debug"" goto doDebug
if ""%1"" == ""run"" goto doRun
if ""%1"" == ""start"" goto doStart
if ""%1"" == ""stop"" goto doStop
if ""%1"" == ""configtest"" goto doConfigTest
if ""%1"" == ""version"" goto doVersion
```
- 接下来, 我们能看到一些重要的信息, 其中的重点是:
```sh
set _EXECJAVA=%_RUNJAVA%, 设置了jdk中bin目录下的java.exe文件路径.
set MAINCLASS=org.apache.catalina.startup.Bootstrap, 设置了tomcat的启动类为Bootstrap这个类. (后面会分析这个类)
set ACTION=start设置tomcat启动
```
> 大家可以留意这些参数, 最后执行tomcat的启动时会用到.

```sh
if not ""%1"" == ""jpda"" goto noJpda
set JPDA=jpda
if not "%JPDA_TRANSPORT%" == "" goto gotJpdaTransport
set JPDA_TRANSPORT=dt_socket
:gotJpdaTransport
if not "%JPDA_ADDRESS%" == "" goto gotJpdaAddress
set JPDA_ADDRESS=8000
:gotJpdaAddress
if not "%JPDA_SUSPEND%" == "" goto gotJpdaSuspend
set JPDA_SUSPEND=n
:gotJpdaSuspend
if not "%JPDA_OPTS%" == "" goto gotJpdaOpts
set JPDA_OPTS=-agentlib:jdwp=transport=%JPDA_TRANSPORT%,address=%JPDA_ADDRESS%,server=y,suspend=%JPDA_SUSPEND%
:gotJpdaOpts
shift
:noJpda

if ""%1"" == ""debug"" goto doDebug
if ""%1"" == ""run"" goto doRun
if ""%1"" == ""start"" goto doStart
if ""%1"" == ""stop"" goto doStop
if ""%1"" == ""configtest"" goto doConfigTest
if ""%1"" == ""version"" goto doVersion
```
- 接着判断第一个参数是否是jpda, 是则进行一些设定. 而正常情况下第一个参数是start, 所以跳过这段代码. 接着会判断第一个参数的内容, 根据判断, 我们会跳到doStart代码段. (有余力的同学不妨看下debug, run等启动方式)
```sh
:doStart
shift
if "%TITLE%" == "" set TITLE=Tomcat
set _EXECJAVA=start "%TITLE%" %_RUNJAVA%
if not ""%1"" == ""-security"" goto execCmd
shift
echo Using Security Manager
set "SECURITY_POLICY_FILE=%CATALINA_BASE%\conf\catalina.policy"
goto execCmd
```
可以看到doStart中无非也是设定一些参数, 最终会跳转到execCmd代码段
```sh
:execCmd
rem Get remaining unshifted command line arguments and save them in the
set CMD_LINE_ARGS=
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs
```
> 可以看到这段代码也是在拼接参数, 把参数拼接到一个叫CMD_LINE_ARGS的变量中, 接下来就是catalina最后的一段代码了.
```sh
rem Execute Java with the applicable properties
if not "%JPDA%" == "" goto doJpda
if not "%SECURITY_POLICY_FILE%" == "" goto doSecurity
%_EXECJAVA% %LOGGING_CONFIG% %LOGGING_MANAGER% %JAVA_OPTS% %CATALINA_OPTS% %DEBUG_OPTS% -D%ENDORSED_PROP%="%JAVA_ENDORSED_DIRS%" -classpath "%CLASSPATH%" -Dcatalina.base="%CATALINA_BASE%" -Dcatalina.home="%CATALINA_HOME%" -Djava.io.tmpdir="%CATALINA_TMPDIR%" %MAINCLASS% %CMD_LINE_ARGS% %ACTION%
goto end
:doSecurity
%_EXECJAVA% %LOGGING_CONFIG% %LOGGING_MANAGER% %JAVA_OPTS% %CATALINA_OPTS% %DEBUG_OPTS% -D%ENDORSED_PROP%="%JAVA_ENDORSED_DIRS%" -classpath "%CLASSPATH%" -Djava.security.manager -Djava.security.policy=="%SECURITY_POLICY_FILE%" -Dcatalina.base="%CATALINA_BASE%" -Dcatalina.home="%CATALINA_HOME%" -Djava.io.tmpdir="%CATALINA_TMPDIR%" %MAINCLASS% %CMD_LINE_ARGS% %ACTION%
goto end
:doJpda
if not "%SECURITY_POLICY_FILE%" == "" goto doSecurityJpda
%_EXECJAVA% %LOGGING_CONFIG% %LOGGING_MANAGER% %JAVA_OPTS% %JPDA_OPTS% %CATALINA_OPTS% %DEBUG_OPTS% -D%ENDORSED_PROP%="%JAVA_ENDORSED_DIRS%" -classpath "%CLASSPATH%" -Dcatalina.base="%CATALINA_BASE%" -Dcatalina.home="%CATALINA_HOME%" -Djava.io.tmpdir="%CATALINA_TMPDIR%" %MAINCLASS% %CMD_LINE_ARGS% %ACTION%
goto end
:doSecurityJpda
%_EXECJAVA% %LOGGING_CONFIG% %LOGGING_MANAGER% %JAVA_OPTS% %JPDA_OPTS% %CATALINA_OPTS% %DEBUG_OPTS% -D%ENDORSED_PROP%="%JAVA_ENDORSED_DIRS%" -classpath "%CLASSPATH%" -Djava.security.manager -Djava.security.policy=="%SECURITY_POLICY_FILE%" -Dcatalina.base="%CATALINA_BASE%" -Dcatalina.home="%CATALINA_HOME%" -Djava.io.tmpdir="%CATALINA_TMPDIR%" %MAINCLASS% %CMD_LINE_ARGS% %ACTION%
goto end

:end
```
- 跳过前面两行判断后, 来到了关键语句:
```sh
%_EXECJAVA% %LOGGING_CONFIG% %LOGGING_MANAGER% %JAVA_OPTS% %CATALINA_OPTS% %DEBUG_OPTS% -D%ENDORSED_PROP%="%JAVA_ENDORSED_DIRS%" -classpath "%CLASSPATH%" -Dcatalina.base="%CATALINA_BASE%" -Dcatalina.home="%CATALINA_HOME%" -Djava.io.tmpdir="%CATALINA_TMPDIR%" %MAINCLASS% %CMD_LINE_ARGS% %ACTION%
```
> _EXECJAVA也就是_RUNJAVA, 也就是平时说的java指令, 但在之前的doStart代码块中把_EXECJAVA改为了start "%TITLE%" %_RUNJAVA%, 所以系统会另启一个命令行窗口, 名字叫Tomcat. 在拼接一系列参数后, 我们会看见%MAINCLASS%, 也就是org.apache.catalina.startup.Bootstrap启动类, 拼接完启动参数后, 最后拼接的是%ACTION%, 也就是start.
#### 6.4.5.3 总结:
- **catalina.bat最终执行了Bootstrap类中的main方法.**
- 我们可以通过设定不同的参数让tomcat以不同的方式运行. 在ide中我们是可以选择debug等模式启动tomcat的, 也可以为其配置参数, 在catalina.bat中我们看到了启动tomcat背后的运作流程
## 6.5 Tomcat - 启动过程：初始化和启动流程
### 6.5.1 总体流程
我们看下整体的初始化和启动的流程，在**理解的时候可以直接和Tomcat架构设计中组件关联上**：
![17.tomcat-x-start-1.png](../../assets/images/04-主流框架/Servlet容器/17.tomcat-x-start-1.png)
### 6.5.2 Bootstrap主入口？
Tomcat源码就从它的main方法开始。Tomcat的main方法在org.apache.catalina.startup.Bootstrap 里。 如下代码我们就是创建一个 Bootstrap 对象，调用它的 init 方法初始化，然后根据启动参数，分别调用 Bootstrap 对象的不同方法。
```java
public final class Bootstrap {
    ……
    
    /**
     * Daemon object used by main.
     */
    private static final Object daemonLock = new Object();
    
    ……
    
    
   /**
     * Main method and entry point when starting Tomcat via the provided
     * scripts.
     *
     * @param args Command line arguments to be processed
     */
    public static void main(String args[]) {
        // 创建一个 Bootstrap 对象，调用它的 init 方法初始化
        synchronized (daemonLock) {
            if (daemon == null) {
                // Don't set daemon until init() has completed
                Bootstrap bootstrap = new Bootstrap();
                try {
                    bootstrap.init();
                } catch (Throwable t) {
                    handleThrowable(t);
                    t.printStackTrace();
                    return;
                }
                daemon = bootstrap;
            } else {
                // When running as a service the call to stop will be on a new
                // thread so make sure the correct class loader is used to
                // prevent a range of class not found exceptions.
                Thread.currentThread().setContextClassLoader(daemon.catalinaLoader);
            }
        }

        // 根据启动参数，分别调用 Bootstrap 对象的不同方法
        try {
            String command = "start"; // 默认是start
            if (args.length > 0) {
                command = args[args.length - 1];
            }

            if (command.equals("startd")) {
                args[args.length - 1] = "start";
                daemon.load(args);
                daemon.start();
            } else if (command.equals("stopd")) {
                args[args.length - 1] = "stop";
                daemon.stop();
            } else if (command.equals("start")) {
                daemon.setAwait(true);
                daemon.load(args);
                daemon.start();
                if (null == daemon.getServer()) {
                    System.exit(1);
                }
            } else if (command.equals("stop")) {
                daemon.stopServer(args);
            } else if (command.equals("configtest")) {
                daemon.load(args);
                if (null == daemon.getServer()) {
                    System.exit(1);
                }
                System.exit(0);
            } else {
                log.warn("Bootstrap: command \"" + command + "\" does not exist.");
            }
        } catch (Throwable t) {
            // Unwrap the Exception for clearer error reporting
            if (t instanceof InvocationTargetException &&
                    t.getCause() != null) {
                t = t.getCause();
            }
            handleThrowable(t);
            t.printStackTrace();
            System.exit(1);
        }

    }
    
    ……
}
```
### 6.5.3 Bootstrap如何初始化Catalina的？
我们用`Sequence Diagram`插件来看main方法的时序图，但是可以发现它并没有帮我们画出Bootstrap初始化Catalina的过程，这和上面的组件初始化不符合？
![18.tomcat-x-start-2.png](../../assets/images/04-主流框架/Servlet容器/18.tomcat-x-start-2.png)

让我们带着这个看下Catalina的初始化的
```java
/**
  * 初始化守护进程
  * 
  * @throws Exception Fatal initialization error
  */
public void init() throws Exception {

    // 初始化classloader（包括catalinaLoader），下文将具体分析
    initClassLoaders();

    // 设置当前的线程的contextClassLoader为catalinaLoader
    Thread.currentThread().setContextClassLoader(catalinaLoader);

    SecurityClassLoad.securityClassLoad(catalinaLoader);

    // 通过catalinaLoader加载Catalina，并初始化startupInstance 对象
    if (log.isDebugEnabled())
        log.debug("Loading startup class");
    Class<?> startupClass = catalinaLoader.loadClass("org.apache.catalina.startup.Catalina");
    Object startupInstance = startupClass.getConstructor().newInstance();

    // 通过反射调用了setParentClassLoader 方法
    if (log.isDebugEnabled())
        log.debug("Setting startup class properties");
    String methodName = "setParentClassLoader";
    Class<?> paramTypes[] = new Class[1];
    paramTypes[0] = Class.forName("java.lang.ClassLoader");
    Object paramValues[] = new Object[1];
    paramValues[0] = sharedLoader;
    Method method =
        startupInstance.getClass().getMethod(methodName, paramTypes);
    method.invoke(startupInstance, paramValues);

    catalinaDaemon = startupInstance;

}
```
通过上面几行关键代码的注释，我们就可以看出Catalina是如何初始化的。这里还留下一个问题，tomcat为什么要初始化不同的classloader呢？我们将在下文进行详解。
## 6.6 Tomcat - 启动过程:类加载机制详解
### 6.6.1 Tomcat初始化了哪些classloader
在Bootstrap中我们可以看到有如下三个classloader
```java
ClassLoader commonLoader = null;
ClassLoader catalinaLoader = null;
ClassLoader sharedLoader = null;
```
### 6.6.2 如何初始化的呢？
```java
private void initClassLoaders() {
    try {
        // commonLoader初始化
        commonLoader = createClassLoader("common", null);
        if (commonLoader == null) {
            // no config file, default to this loader - we might be in a 'single' env.
            commonLoader = this.getClass().getClassLoader();
        }
        // catalinaLoader初始化, 父classloader是commonLoader
        catalinaLoader = createClassLoader("server", commonLoader);
        // sharedLoader初始化
        sharedLoader = createClassLoader("shared", commonLoader);
    } catch (Throwable t) {
        handleThrowable(t);
        log.error("Class loader creation threw exception", t);
        System.exit(1);
    }
}
```
> 可以看出，catalinaLoader 和 sharedLoader 的 parentClassLoader 是 commonLoader。
### 6.6.3 如何创建classLoader的？
不妨再看下如何创建的？
```java
private ClassLoader createClassLoader(String name, ClassLoader parent)
    throws Exception {

    String value = CatalinaProperties.getProperty(name + ".loader");
    if ((value == null) || (value.equals("")))
        return parent;

    value = replace(value);

    List<Repository> repositories = new ArrayList<>();

    String[] repositoryPaths = getPaths(value);

    for (String repository : repositoryPaths) {
        // Check for a JAR URL repository
        try {
            @SuppressWarnings("unused")
            URL url = new URL(repository);
            repositories.add(new Repository(repository, RepositoryType.URL));
            continue;
        } catch (MalformedURLException e) {
            // Ignore
        }

        // Local repository
        if (repository.endsWith("*.jar")) {
            repository = repository.substring
                (0, repository.length() - "*.jar".length());
            repositories.add(new Repository(repository, RepositoryType.GLOB));
        } else if (repository.endsWith(".jar")) {
            repositories.add(new Repository(repository, RepositoryType.JAR));
        } else {
            repositories.add(new Repository(repository, RepositoryType.DIR));
        }
    }

    return ClassLoaderFactory.createClassLoader(repositories, parent);
}
```
方法的逻辑也比较简单就是从 catalina.property文件里找 common.loader, shared.loader, server.loader 对应的值，然后构造成Repository 列表，再将Repository 列表传入ClassLoaderFactory.createClassLoader 方法，ClassLoaderFactory.createClassLoader 返回的是 URLClassLoader，而Repository 列表就是这个URLClassLoader 可以加载的类的路径。 在catalina.property文件里
```sh
common.loader="${catalina.base}/lib","${catalina.base}/lib/*.jar","${catalina.home}/lib","${catalina.home}/lib/*.jar"
server.loader=
shared.loader=
```
其中 shared.loader, server.loader 是没有值的，createClassLoader 方法里如果没有值的话，就返回传入的 parent ClassLoader，也就是说，commonLoader,catalinaLoader,sharedLoader 其实是一个对象。在Tomcat之前的版本里，这三个是不同的URLClassLoader对象。
```java
Class<?> startupClass = catalinaLoader.loadClass("org.apache.catalina.startup.Catalina");
        Object startupInstance = startupClass.getConstructor().newInstance();
```
初始化完三个ClassLoader对象后，init() 方法就使用 catalinaClassLoader 加载了org.apache.catalina.startup.Catalina 类，并创建了一个对象，然后通过反射调用这个对象的 setParentClassLoader 方法，传入的参数是 sharedClassLoader。最后吧这个 Catania 对象复制给 catalinaDaemon 属性。
### 6.6.4 为什么Tomcat的类加载器也不是双亲委派模型
> 我们知道，Java默认的类加载机制是通过双亲委派模型来实现的，而Tomcat实现的方式又和双亲委派模型有所区别。

**原因在于一个Tomcat容器允许同时运行多个Web程序，每个Web程序依赖的类又必须是相互隔离的。**因此，如果Tomcat使用双亲委派模式来加载类的话，将导致Web程序依赖的类变为共享的。

举个例子，假如我们有两个Web程序，一个依赖A库的1.0版本，另一个依赖A库的2.0版本，他们都使用了类xxx.xx.Clazz，其实现的逻辑因类库版本的不同而结构完全不同。那么这两个Web程序的其中一个必然因为加载的Clazz不是所使用的Clazz而出现问题！而这对于开发来说是非常致命的！
### 6.6.5 Tomcat类加载机制是怎么样的呢
> 既然Tomcat的类加载机器不同于双亲委派模式，那么它又是一种怎样的模式呢？
我们在这里一定要看下官网提供的<a href = 'https://tomcat.apache.org/tomcat-9.0-doc/class-loader-howto.html'>类加载的文档</a>
![19.tomcat-x-classloader-1.png](../../assets/images/04-主流框架/Servlet容器/19.tomcat-x-classloader-1.png)

结合经典的类加载机制，我们完整的看下Tomcat类加载图
![20.tomcat-x-classloader-2.png](../../assets/images/04-主流框架/Servlet容器/20.tomcat-x-classloader-2.png)

- Common类加载器，负责加载Tomcat和Web应用都复用的类 
  - Catalina类加载器，负责加载Tomcat专用的类，而这些被加载的类在Web应用中将不可见
  - Shared类加载器，负责加载Tomcat下所有的Web应用程序都复用的类，而这些被加载的类在Tomcat中将不可见 
    - WebApp类加载器，负责加载具体的某个Web应用程序所使用到的类，而这些被加载的类在Tomcat和其他的Web应用程序都将不可见
    - Jsp类加载器，每个jsp页面一个类加载器，不同的jsp页面有不同的类加载器，方便实现jsp页面的热插拔

同样的，我们可以看到通过ContextClassLoader（上下文类加载器）的setContextClassLoader来传入自己实现的类加载器
```java
public void init() throws Exception {

  initClassLoaders();

  // 看这里
  Thread.currentThread().setContextClassLoader(catalinaLoader);

  SecurityClassLoad.securityClassLoad(catalinaLoader);
...
```
### 6.6.6 WebApp类加载器
> 到这儿，我们隐隐感觉到少分析了点什么！没错，就是WebApp类加载器。整个启动过程分析下来，我们仍然没有看到这个类加载器。它又是在哪儿出现的呢？

我们知道WebApp类加载器是Web应用私有的，而每个Web应用其实算是一个Context，那么我们通过Context的实现类应该可以发现。在Tomcat中，Context的默认实现为StandardContext，我们看看这个类的startInternal()方法，在这儿我们发现了我们感兴趣的WebApp类加载器。
```java
protected synchronized void startInternal() throws LifecycleException {
    if (getLoader() == null) {
        WebappLoader webappLoader = new WebappLoader(getParentClassLoader());
        webappLoader.setDelegate(getDelegate());
        setLoader(webappLoader);
    }
}
```
入口代码非常简单，就是webappLoader不存在的时候创建一个，并调用setLoader方法。我们接着分析setLoader
```java
public void setLoader(Loader loader) {

    Lock writeLock = loaderLock.writeLock();
    writeLock.lock();
    Loader oldLoader = null;
    try {
        // Change components if necessary
        oldLoader = this.loader;
        if (oldLoader == loader)
            return;
        this.loader = loader;

        // Stop the old component if necessary
        if (getState().isAvailable() && (oldLoader != null) &&
            (oldLoader instanceof Lifecycle)) {
            try {
                ((Lifecycle) oldLoader).stop();
            } catch (LifecycleException e) {
                log.error("StandardContext.setLoader: stop: ", e);
            }
        }

        // Start the new component if necessary
        if (loader != null)
            loader.setContext(this);
        if (getState().isAvailable() && (loader != null) &&
            (loader instanceof Lifecycle)) {
            try {
                ((Lifecycle) loader).start();
            } catch (LifecycleException e) {
                log.error("StandardContext.setLoader: start: ", e);
            }
        }
    } finally {
        writeLock.unlock();
    }

    // Report this property change to interested listeners
    support.firePropertyChange("loader", oldLoader, loader);
}
```
这儿，我们感兴趣的就两行代码：
```java
((Lifecycle) oldLoader).stop(); // 旧的加载器停止
((Lifecycle) loader).start(); // 新的加载器启动
```
### 6.6.7 为什么tomcat要打破双亲委派机制
一个Tomcat实例（Server）可以部署多个独立的Web应用。这个类加载机制是实现"应用隔离"的关键。

- 传统双亲委派模型的问题：

如果使用标准模型，所有Web应用共享同一个类加载器，会导致：

1. 类冲突：应用A需要Library v1.0，应用B需要Library v2.0，后者会覆盖前者
2. 资源泄漏：一个应用卸载时，如果其他应用还在使用共享库，库无法被GC回收
3. 安全风险：恶意应用可以覆盖其他应用的关键类
- Tomcat解决方案的优势：

1. 完美的应用隔离

- 每个Web应用有自己的类加载器，加载自己WEB-INF下的类库
- 应用A的Library v1.0和应用B的Library v2.0可以共存，互不影响
- 应用卸载时，其类加载器及加载的所有类都可以被GC回收
- 灵活的类可见性控制

2. 通过delegate属性控制委托行为：
- false（默认）：优先从自身加载，实现隔离
- true：优先委托给父加载器，适合需要严格安全控制的场景
3. 共享与隔离的平衡

- 需要共享的类（如Servlet API）由父加载器加载，所有应用共用同一份
- 需要隔离的类（如业务实现）由各自的应用类加载器加载

| 场景 | 主要类加载器 | 父加载器 | 目的 |
|------|-------------|----------|------|
| 普通JAR（`java -jar`） | `AppClassLoader` | `PlatformClassLoader` | 加载类路径上的类 |
| Spring Boot Fat Jar（`java -jar`） | `LaunchedURLClassLoader` | `AppClassLoader` | 解决嵌套JAR加载 |
| Tomcat Web应用 | `WebappClassLoader` | `Common ClassLoader` | 应用隔离 |
| IDE中直接运行Spring Boot | `AppClassLoader` | `PlatformClassLoader` | 标准开发环境 |
## 6.7 Tomcat - 启动过程:Catalina的加载
### 6.7.1 Catalina的引入
>通过前两篇文章，我们知道了Tomcat的类加载机制和整体的组件加载流程；我们也知道通过Bootstrap初始化的catalinaClassLoader加载了Catalina，那么进而引入了一个问题就是Catalina是如何加载的呢？加载了什么呢？
- 先回顾下整个流程，和我们分析的阶段
![21.tomcat-x-catalina-1.png](../../assets/images/04-主流框架/Servlet容器/21.tomcat-x-catalina-1.png)
- 看下Bootstrap中Load的过程
```java
param = null;
    } else {
        paramTypes = new Class[1];
        paramTypes[0] = arguments.getClass();
        param = new Object[1];
        param[0] = arguments;
    }
    Method method =
        catalinaDaemon.getClass().getMethod(methodName, paramTypes); 
    if (log.isDebugEnabled()) {
        log.debug("Calling startup class " + method);
    }
    method.invoke(catalinaDaemon, param);// 本质上就是调用catalina的load方法
}
```
### 6.7.2 Catalina的加载
上一步，我们知道catalina load的触发，因为有参数所以是load(String[])方法。我们进而看下这个load方法做了什么？

- load(String[])本质上还是调用了load方法
```java
/*
  * Load using arguments
  */
public void load(String args[]) {

    try {
        if (arguments(args)) { // 处理命令行的参数
            load();
        }
    } catch (Exception e) {
        e.printStackTrace(System.out);
    }
}
```
- load加载过程本质上是初始化Server的实例
```java
/**
  * Start a new server instance.
  */
public void load() {

    // 如果已经加载则退出
    if (loaded) {
        return;
    }
    loaded = true;

    long t1 = System.nanoTime();

    // （已经弃用）
    initDirs();

    // Before digester - it may be needed
    initNaming();

    // 解析 server.xml
    parseServerXml(true);
    Server s = getServer();
    if (s == null) {
        return;
    }

    getServer().setCatalina(this);
    getServer().setCatalinaHome(Bootstrap.getCatalinaHomeFile());
    getServer().setCatalinaBase(Bootstrap.getCatalinaBaseFile());

    // Stream redirection
    initStreams();

    // 启动Server
    try {
        getServer().init();
    } catch (LifecycleException e) {
        if (Boolean.getBoolean("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE")) {
            throw new java.lang.Error(e);
        } else {
            log.error(sm.getString("catalina.initError"), e);
        }
    }

    if(log.isInfoEnabled()) {
        log.info(sm.getString("catalina.init", Long.toString(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t1))));
    }
}
```
总体流程如下：
![22.tomcat-x-catalina-2.png](../../assets/images/04-主流框架/Servlet容器/22.tomcat-x-catalina-2.png)
- initDirs

已经弃用了，Tomcat10会删除这个方法。
```java
/**
  * @deprecated unused. Will be removed in Tomcat 10 onwards.
  */
@Deprecated
protected void initDirs() {
}
```
- initNaming

设置额外的系统变量
```java
protected void initNaming() {
  // Setting additional variables
  if (!useNaming) {
      log.info(sm.getString("catalina.noNaming"));
      System.setProperty("catalina.useNaming", "false");
  } else {
      System.setProperty("catalina.useNaming", "true");
      String value = "org.apache.naming";
      String oldValue =
          System.getProperty(javax.naming.Context.URL_PKG_PREFIXES);
      if (oldValue != null) {
          value = value + ":" + oldValue;
      }
      System.setProperty(javax.naming.Context.URL_PKG_PREFIXES, value);
      if( log.isDebugEnabled() ) {
          log.debug("Setting naming prefix=" + value);
      }
      value = System.getProperty
          (javax.naming.Context.INITIAL_CONTEXT_FACTORY);
      if (value == null) {
          System.setProperty
              (javax.naming.Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.naming.java.javaURLContextFactory");
      } else {
          log.debug("INITIAL_CONTEXT_FACTORY already set " + value );
      }
  }
}
```
- Server.xml的解析

分三大块，下面的代码还是很清晰的:
```java
protected void parseServerXml(boolean start) {
    // Set configuration source
    ConfigFileLoader.setSource(new CatalinaBaseConfigurationSource(Bootstrap.getCatalinaBaseFile(), getConfigFile()));
    File file = configFile();

    if (useGeneratedCode && !Digester.isGeneratedCodeLoaderSet()) {
        // Load loader
        String loaderClassName = generatedCodePackage + ".DigesterGeneratedCodeLoader";
        try {
            Digester.GeneratedCodeLoader loader =
                    (Digester.GeneratedCodeLoader) Catalina.class.getClassLoader().loadClass(loaderClassName).newInstance();
            Digester.setGeneratedCodeLoader(loader);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.info(sm.getString("catalina.noLoader", loaderClassName), e);
            } else {
                log.info(sm.getString("catalina.noLoader", loaderClassName));
            }
            // No loader so don't use generated code
            useGeneratedCode = false;
        }
    }

    // 初始化server.xml的位置
    File serverXmlLocation = null;
    String xmlClassName = null;
    if (generateCode || useGeneratedCode) {
        xmlClassName = start ? generatedCodePackage + ".ServerXml" : generatedCodePackage + ".ServerXmlStop";
    }
    if (generateCode) {
        if (generatedCodeLocationParameter != null) {
            generatedCodeLocation = new File(generatedCodeLocationParameter);
            if (!generatedCodeLocation.isAbsolute()) {
                generatedCodeLocation = new File(Bootstrap.getCatalinaHomeFile(), generatedCodeLocationParameter);
            }
        } else {
            generatedCodeLocation = new File(Bootstrap.getCatalinaHomeFile(), "work");
        }
        serverXmlLocation = new File(generatedCodeLocation, generatedCodePackage);
        if (!serverXmlLocation.isDirectory() && !serverXmlLocation.mkdirs()) {
            log.warn(sm.getString("catalina.generatedCodeLocationError", generatedCodeLocation.getAbsolutePath()));
            // Disable code generation
            generateCode = false;
        }
    }

    // 用 SAXParser 来解析 xml，解析完了之后，xml 里定义的各种标签就有对应的实现类对象了
    ServerXml serverXml = null;
    if (useGeneratedCode) {
        serverXml = (ServerXml) Digester.loadGeneratedClass(xmlClassName);
    }

    if (serverXml != null) {
        serverXml.load(this);
    } else {
        try (ConfigurationSource.Resource resource = ConfigFileLoader.getSource().getServerXml()) {
            // Create and execute our Digester
            Digester digester = start ? createStartDigester() : createStopDigester();
            InputStream inputStream = resource.getInputStream();
            InputSource inputSource = new InputSource(resource.getURI().toURL().toString());
            inputSource.setByteStream(inputStream);
            digester.push(this);
            if (generateCode) {
                digester.startGeneratingCode();
                generateClassHeader(digester, start);
            }
            digester.parse(inputSource);
            if (generateCode) {
                generateClassFooter(digester);
                try (FileWriter writer = new FileWriter(new File(serverXmlLocation,
                        start ? "ServerXml.java" : "ServerXmlStop.java"))) {
                    writer.write(digester.getGeneratedCode().toString());
                }
                digester.endGeneratingCode();
                Digester.addGeneratedClass(xmlClassName);
            }
        } catch (Exception e) {
            log.warn(sm.getString("catalina.configFail", file.getAbsolutePath()), e);
            if (file.exists() && !file.canRead()) {
                log.warn(sm.getString("catalina.incorrectPermissions"));
            }
        }
    }
}
```
- initStreams

替换掉System.out, System.err为自定义的PrintStream
```java
protected void initStreams() {
    // Replace System.out and System.err with a custom PrintStream
    System.setOut(new SystemLogHandler(System.out));
    System.setErr(new SystemLogHandler(System.err));
}
```
### 6.7.3 Catalina 的启动
在 load 方法之后，Tomcat 就初始化了一系列的组件，接着就可以调用 start 方法进行启动了。
```java
/**
  * Start a new server instance.
  */
public void start() {

    if (getServer() == null) {
        load();
    }

    if (getServer() == null) {
        log.fatal(sm.getString("catalina.noServer"));
        return;
    }

    long t1 = System.nanoTime();

    // Start the new server
    try {
        getServer().start();
    } catch (LifecycleException e) {
        log.fatal(sm.getString("catalina.serverStartFail"), e);
        try {
            getServer().destroy();
        } catch (LifecycleException e1) {
            log.debug("destroy() failed for failed Server ", e1);
        }
        return;
    }

    long t2 = System.nanoTime();
    if(log.isInfoEnabled()) {
        log.info(sm.getString("catalina.startup", Long.valueOf((t2 - t1) / 1000000)));
    }

    // Register shutdown hook
    if (useShutdownHook) {
        if (shutdownHook == null) {
            shutdownHook = new CatalinaShutdownHook();
        }
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        // If JULI is being used, disable JULI's shutdown hook since
        // shutdown hooks run in parallel and log messages may be lost
        // if JULI's hook completes before the CatalinaShutdownHook()
        LogManager logManager = LogManager.getLogManager();
        if (logManager instanceof ClassLoaderLogManager) {
            ((ClassLoaderLogManager) logManager).setUseShutdownHook(
                    false);
        }
    }

    if (await) {
        await();
        stop();
    }
}
```
上面这段代码，逻辑非常简单，首先确定 getServer() 方法不为 null ，也就是确定 server 属性不为null，而 server 属性是在 load 方法就初始化了。

整段代码的核心就是 try-catch 里的 getServer().start() 方法了，也就是调用 Server 对象的 start() 方法来启动 Tomcat。本篇文章就先不对 Server 的 start() 方法进行解析了，下篇文章会单独讲。

### 6.7.4 Catalina 的关闭
调用完 Server#start 方法之后，注册了一个ShutDownHook，也就是 CatalinaShutdownHook 对象，
```java
/**
  * Shutdown hook which will perform a clean shutdown of Catalina if needed.
  */
protected class CatalinaShutdownHook extends Thread {

  @Override
  public void run() {
      try {
          if (getServer() != null) {
              Catalina.this.stop();
          }
      } catch (Throwable ex) {
          ExceptionUtils.handleThrowable(ex);
          log.error(sm.getString("catalina.shutdownHookFail"), ex);
      } finally {
          // If JULI is used, shut JULI down *after* the server shuts down
          // so log messages aren't lost
          LogManager logManager = LogManager.getLogManager();
          if (logManager instanceof ClassLoaderLogManager) {
              ((ClassLoaderLogManager) logManager).shutdown();
          }
      }
  }
}
```
CatalinaShutdownHook 的逻辑也简单，就是调用 Catalina 对象的 stop 方法来停止 tomcat。

最后就进入 if 语句了，await 是在 Bootstrap 里调用的时候设置为 true 的，也就是本文开头的时候提到的三个方法中的一个。await 方法的作用是停住主线程，等待用户输入shutdown 命令之后，停止等待，之后 main 线程就调用 stop 方法来停止Tomcat。
```java
/**
  * Stop an existing server instance.
  */
public void stop() {

    try {
        // Remove the ShutdownHook first so that server.stop()
        // doesn't get invoked twice
        if (useShutdownHook) {
            Runtime.getRuntime().removeShutdownHook(shutdownHook);

            // If JULI is being used, re-enable JULI's shutdown to ensure
            // log messages are not lost
            LogManager logManager = LogManager.getLogManager();
            if (logManager instanceof ClassLoaderLogManager) {
                ((ClassLoaderLogManager) logManager).setUseShutdownHook(
                        true);
            }
        }
    } catch (Throwable t) {
        ExceptionUtils.handleThrowable(t);
        // This will fail on JDK 1.2. Ignoring, as Tomcat can run
        // fine without the shutdown hook.
    }

    // Shut down the server
    try {
        Server s = getServer();
        LifecycleState state = s.getState();
        if (LifecycleState.STOPPING_PREP.compareTo(state) <= 0
                && LifecycleState.DESTROYED.compareTo(state) >= 0) {
            // Nothing to do. stop() was already called
        } else {
            s.stop();
            s.destroy();
        }
    } catch (LifecycleException e) {
        log.error(sm.getString("catalina.stopError"), e);
    }

}
```
Catalina 的 stop 方法主要逻辑是调用 Server 对象的 stop 方法。

### 6.7.5 聊聊关闭钩子
上面我们看到CatalinaShutdownHook, 这里有必要谈谈JVM的关闭钩子。
```java
if (shutdownHook == null) {
    shutdownHook = new CatalinaShutdownHook();
}
Runtime.getRuntime().addShutdownHook(shutdownHook);
```
关闭钩子是指通过**Runtime.addShutdownHook注册的但尚未开始的线程**。这些钩子可以用于**实现服务或者应用程序的清理工作**，例如删除临时文件，或者清除无法由操作系统自动清除的资源。

JVM既可以正常关闭，也可以强行关闭。正常关闭的触发方式有多种，包括：当最后一个“正常（非守护）”线程结束时，或者当调用了System.exit时，或者通过其他特定于平台的方法关闭时（例如发送了SIGINT信号或者键入Ctrl-C）。

在**正常关闭中，JVM首先调用所有已注册的关闭钩子**。JVM并不能保证关闭钩子的调用顺序。在关闭应用程序线程时，如果有（守护或者非守护）线程仍然在执行，那么这些线程接下来将与关闭进程并发执行。当所有的关闭钩子都执行结束时，如果runFinalizersOnExit为true【通过Runtime.runFinalizersOnExit(true)设置】，那么JVM将运行这些Finalizer（对象重写的finalize方法），然后再停止。JVM不会停止或中断任何在关闭时仍然运行的应用程序线程。当JVM最终结束时，这些线程将被强行结束。如果关闭钩子或者Finalizer没有执行完成，那么正常关闭进程“挂起”并且JVM必须被强行关闭。**当JVM被强行关闭时，只是关闭JVM，并不会运行关闭钩子**（举个例子，类似于电源都直接拔了，还怎么做其它动作呢？）。

下面是一个简单的示例：
```java
public class T {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		//启用退出JVM时执行Finalizer
		Runtime.runFinalizersOnExit(true);
		MyHook hook1 = new MyHook("Hook1");
		MyHook hook2 = new MyHook("Hook2");
		MyHook hook3 = new MyHook("Hook3");
		
		//注册关闭钩子
		Runtime.getRuntime().addShutdownHook(hook1);
		Runtime.getRuntime().addShutdownHook(hook2);
		Runtime.getRuntime().addShutdownHook(hook3);
		
		//移除关闭钩子
		Runtime.getRuntime().removeShutdownHook(hook3);
		
		//Main线程将在执行这句之后退出
		System.out.println("Main Thread Ends.");
	}
}

class MyHook extends Thread {
	private String name;
	public MyHook (String name) {
		this.name = name;
		setName(name);
	}
	public void run() {
		System.out.println(name + " Ends.");
	}
	//重写Finalizer，将在关闭钩子后调用
	protected void finalize() throws Throwable {
		System.out.println(name + " Finalize.");
	}
}

```
和（可能的）执行结果（因为JVM不保证关闭钩子的调用顺序，因此结果中的第二、三行可能出现相反的顺序）：
```java
Main Thread Ends.
Hook2 Ends.
Hook1 Ends.
Hook3 Finalize.
Hook2 Finalize.
Hook1 Finalize.
```
可以看到，main函数执行完成，首先输出的是Main Thread Ends，接下来执行关闭钩子，输出Hook2 Ends和Hook1 Ends。这两行也可以证实：JVM确实不是以注册的顺序来调用关闭钩子的。而由于hook3在调用了addShutdownHook后，接着对其调用了removeShutdownHook将其移除，于是hook3在JVM退出时没有执行，因此没有输出Hook3 Ends。

另外，由于MyHook类实现了finalize方法，而main函数中第一行又通过Runtime.runFinalizersOnExit(true)打开了退出JVM时执行Finalizer的开关，于是3个hook对象的finalize方法被调用，输出了3行Finalize。

注意，多次调用addShutdownHook来注册同一个关闭钩子将会抛出IllegalArgumentException:
```sh
Exception in thread "main" java.lang.IllegalArgumentException: Hook previously registered
	at java.lang.ApplicationShutdownHooks.add(ApplicationShutdownHooks.java:72)
	at java.lang.Runtime.addShutdownHook(Runtime.java:211)
	at T.main(T.java:12)
```
另外，从JavaDoc中得知：**一旦JVM关闭流程开始，就只能通过调用halt方法来停止该流程，也不可能再注册或移除关闭钩子了，这些操作将导致抛出IllegalStateException。**

如果在关闭钩子中关闭应用程序的公共的组件，如日志服务，或者数据库连接等，像下面这样：
```java
Runtime.getRuntime().addShutdownHook(new Thread() {
	public void run() {
		try { 
			LogService.this.stop();
		} catch (InterruptedException ignored){
			//ignored
		}
	}
});
```
由于**关闭钩子将并发执行，因此在关闭日志时可能导致其他需要日志服务的关闭钩子产生问题。为了避免这种情况，可以使关闭钩子不依赖那些可能被应用程序或其他关闭钩子关闭的服务。**实现这种功能的一种方式是对所有服务使用同一个关闭钩子（而不是每个服务使用一个不同的关闭钩子），并且在该关闭钩子中执行一系列的关闭操作。这确保了关闭操作在单个线程中串行执行，从而避免了在关闭操作之前出现竞态条件或死锁等问题。

- 使用场景

通过Hook实现临时文件清理
```java
public class test {

  public static void main(String[] args) {
      try {
          Thread.sleep(20000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
          public void run() {
              System.out.println("auto clean temporary file");
          }
      }));
  }
}
```
## 6.8 Tomcat - 组件生命周期管理:LifeCycle
> 我从以下几方面，帮助你构建基于上下文的知识体系和理解为什么要理解组件的生命周期管理（LifeCycle）。
- Server及其它组件
![23.tomcat-x-lifecycle-1.png](../../assets/images/04-主流框架/Servlet容器/23.tomcat-x-lifecycle-1.png)
- Server后续组件生命周期及初始化
![24.tomcat-x-lifecycle-2.png](../../assets/images/04-主流框架/Servlet容器/24.tomcat-x-lifecycle-2.png)
- Server的依赖结构
![25.tomcat-x-lifecycle-3.png](../../assets/images/04-主流框架/Servlet容器/25.tomcat-x-lifecycle-3.png)
### 6.8.1 LifeCycle接口
> 理解Lifecycle主要有两点：第一是三类接口方法；第二是状态机。
#### 6.8.1.1 一个标准的LifeCycle有哪些方法？

分三类去看：
```java
public interface Lifecycle {
    /** 第1类：针对监听器 **/
    // 添加监听器
    public void addLifecycleListener(LifecycleListener listener);
    // 获取所以监听器
    public LifecycleListener[] findLifecycleListeners();
    // 移除某个监听器
    public void removeLifecycleListener(LifecycleListener listener);
    
    /** 第2类：针对控制流程 **/
    // 初始化方法
    public void init() throws LifecycleException;
    // 启动方法
    public void start() throws LifecycleException;
    // 停止方法，和start对应
    public void stop() throws LifecycleException;
    // 销毁方法，和init对应
    public void destroy() throws LifecycleException;
    
    /** 第3类：针对状态 **/
    // 获取生命周期状态
    public LifecycleState getState();
    // 获取字符串类型的生命周期状态
    public String getStateName();
}
```
#### 6.8.1.2 LifeCycle状态机有哪些状态？
Tomcat 给各个组件定义了一些生命周期中的状态
- 在枚举类 LifecycleState 里
```java
public enum LifecycleState {
    NEW(false, null),
    INITIALIZING(false, Lifecycle.BEFORE_INIT_EVENT),
    INITIALIZED(false, Lifecycle.AFTER_INIT_EVENT),
    STARTING_PREP(false, Lifecycle.BEFORE_START_EVENT),
    STARTING(true, Lifecycle.START_EVENT),
    STARTED(true, Lifecycle.AFTER_START_EVENT),
    STOPPING_PREP(true, Lifecycle.BEFORE_STOP_EVENT),
    STOPPING(false, Lifecycle.STOP_EVENT),
    STOPPED(false, Lifecycle.AFTER_STOP_EVENT),
    DESTROYING(false, Lifecycle.BEFORE_DESTROY_EVENT),
    DESTROYED(false, Lifecycle.AFTER_DESTROY_EVENT),
    FAILED(false, null);

    private final boolean available;
    private final String lifecycleEvent;

    private LifecycleState(boolean available, String lifecycleEvent) {
        this.available = available;
        this.lifecycleEvent = lifecycleEvent;
    }
    ……
}
```
- 它们之间的关系是怎么样的呢？

在Lifecycle.java源码中有相关的注释：
![26.tomcat-x-lifecycle-5.png](../../assets/images/04-主流框架/Servlet容器/26.tomcat-x-lifecycle-5.png)

看不太清楚的可以看下图：
![27.tomcat-x-lifecycle-4.jpeg](../../assets/images/04-主流框架/Servlet容器/27.tomcat-x-lifecycle-4.jpeg)
### 6.8.2 LifecycleBase - LifeCycle的基本实现
> LifecycleBase是Lifecycle的基本实现。Tomcat 组件都实现 LifeCycle(extends LifecycleMBeanBase)
#### 6.8.2.1 监听器相关
生命周期监听器保存在一个线程安全的CopyOnWriteArrayList中。所以add和remove都是直接调用此List的相应方法。 findLifecycleListeners返回的是一个数组，为了线程安全，所以这儿会生成一个新数组。
```java
private final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();

@Override
public void addLifecycleListener(LifecycleListener listener) {
    lifecycleListeners.add(listener);
}
@Override
public LifecycleListener[] findLifecycleListeners() {
    return lifecycleListeners.toArray(new LifecycleListener[0]);
}
@Override
public void removeLifecycleListener(LifecycleListener listener) {
    lifecycleListeners.remove(listener);
}
```
#### 6.8.2.2 生命周期相关
- init
```java
@Override
public final synchronized void init() throws LifecycleException {
    // 非NEW状态，不允许调用init()方法
    if (!state.equals(LifecycleState.NEW)) {
        invalidTransition(Lifecycle.BEFORE_INIT_EVENT);
    }

    try {
        // 初始化逻辑之前，先将状态变更为`INITIALIZING`
        setStateInternal(LifecycleState.INITIALIZING, null, false);
        // 初始化，该方法为一个abstract方法，需要组件自行实现
        initInternal();
        // 初始化完成之后，状态变更为`INITIALIZED`
        setStateInternal(LifecycleState.INITIALIZED, null, false);
    } catch (Throwable t) {
        // 初始化的过程中，可能会有异常抛出，这时需要捕获异常，并将状态变更为`FAILED`
        ExceptionUtils.handleThrowable(t);
        setStateInternal(LifecycleState.FAILED, null, false);
        throw new LifecycleException(
                sm.getString("lifecycleBase.initFail",toString()), t);
    }
}
```
我们再来看看invalidTransition方法，该方法直接抛出异常。
```java
private void invalidTransition(String type) throws LifecycleException {
    String msg = sm.getString("lifecycleBase.invalidTransition", type,
            toString(), state);
    throw new LifecycleException(msg);
}
```
setStateInternal方法用于维护状态，同时在状态转换成功之后触发事件。为了状态的可见性，所以state声明为volatile类型的。
```java
private volatile LifecycleState state = LifecycleState.NEW;。

private synchronized void setStateInternal(LifecycleState state,
        Object data, boolean check) throws LifecycleException {
    if (log.isDebugEnabled()) {
        log.debug(sm.getString("lifecycleBase.setState", this, state));
    }

    // 是否校验状态
    if (check) {
        // Must have been triggered by one of the abstract methods (assume
        // code in this class is correct)
        // null is never a valid state
        // state不允许为null
        if (state == null) {
            invalidTransition("null");
            // Unreachable code - here to stop eclipse complaining about
            // a possible NPE further down the method
            return;
        }

        // Any method can transition to failed
        // startInternal() permits STARTING_PREP to STARTING
        // stopInternal() permits STOPPING_PREP to STOPPING and FAILED to
        // STOPPING
        if (!(state == LifecycleState.FAILED ||
                (this.state == LifecycleState.STARTING_PREP &&
                        state == LifecycleState.STARTING) ||
                (this.state == LifecycleState.STOPPING_PREP &&
                        state == LifecycleState.STOPPING) ||
                (this.state == LifecycleState.FAILED &&
                        state == LifecycleState.STOPPING))) {
            // No other transition permitted
            invalidTransition(state.name());
        }
    }

    // 设置状态
    this.state = state;
    // 触发事件
    String lifecycleEvent = state.getLifecycleEvent();
    if (lifecycleEvent != null) {
        fireLifecycleEvent(lifecycleEvent, data);
    }
}
```
设置完 state 的状态之后，就触发该状态的事件了，通知事件监听器
```java
/**
 * The list of registered LifecycleListeners for event notifications.
 */
private final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();


protected void fireLifecycleEvent(String type, Object data) {
    LifecycleEvent event = new LifecycleEvent(this, type, data);
    for (LifecycleListener listener : lifecycleListeners) {
        listener.lifecycleEvent(event);
    }
}
```
这里的 LifecycleListener 对象是在 Catalina 对象解析 server.xml 文件时就已经创建好并加到 lifecycleListeners 里的。这个不是特别重要就不细讲了。
- start
```java
@Override
public final synchronized void start() throws LifecycleException {
    // `STARTING_PREP`、`STARTING`和`STARTED时，将忽略start()逻辑
    if (LifecycleState.STARTING_PREP.equals(state) || LifecycleState.STARTING.equals(state) ||
            LifecycleState.STARTED.equals(state)) {

        if (log.isDebugEnabled()) {
            Exception e = new LifecycleException();
            log.debug(sm.getString("lifecycleBase.alreadyStarted", toString()), e);
        } else if (log.isInfoEnabled()) {
            log.info(sm.getString("lifecycleBase.alreadyStarted", toString()));
        }

        return;
    }

    // `NEW`状态时，执行init()方法
    if (state.equals(LifecycleState.NEW)) {
        init();
    }

    // `FAILED`状态时，执行stop()方法
    else if (state.equals(LifecycleState.FAILED)) {
        stop();
    }

    // 不是`INITIALIZED`和`STOPPED`时，则说明是非法的操作
    else if (!state.equals(LifecycleState.INITIALIZED) &&
            !state.equals(LifecycleState.STOPPED)) {
        invalidTransition(Lifecycle.BEFORE_START_EVENT);
    }

    try {
        // start前的状态设置
        setStateInternal(LifecycleState.STARTING_PREP, null, false);
        // start逻辑，抽象方法，由组件自行实现
        startInternal();
        // start过程中，可能因为某些原因失败，这时需要stop操作
        if (state.equals(LifecycleState.FAILED)) {
            // This is a 'controlled' failure. The component put itself into the
            // FAILED state so call stop() to complete the clean-up.
            stop();
        } else if (!state.equals(LifecycleState.STARTING)) {
            // Shouldn't be necessary but acts as a check that sub-classes are
            // doing what they are supposed to.
            invalidTransition(Lifecycle.AFTER_START_EVENT);
        } else {
            // 设置状态为STARTED
            setStateInternal(LifecycleState.STARTED, null, false);
        }
    } catch (Throwable t) {
        // This is an 'uncontrolled' failure so put the component into the
        // FAILED state and throw an exception.
        ExceptionUtils.handleThrowable(t);
        setStateInternal(LifecycleState.FAILED, null, false);
        throw new LifecycleException(sm.getString("lifecycleBase.startFail", toString()), t);
    }
}
```
- stop
```java
@Override
public final synchronized void stop() throws LifecycleException {
    // `STOPPING_PREP`、`STOPPING`和STOPPED时，将忽略stop()的执行
    if (LifecycleState.STOPPING_PREP.equals(state) || LifecycleState.STOPPING.equals(state) ||
            LifecycleState.STOPPED.equals(state)) {

        if (log.isDebugEnabled()) {
            Exception e = new LifecycleException();
            log.debug(sm.getString("lifecycleBase.alreadyStopped", toString()), e);
        } else if (log.isInfoEnabled()) {
            log.info(sm.getString("lifecycleBase.alreadyStopped", toString()));
        }

        return;
    }

    // `NEW`状态时，直接将状态变更为`STOPPED`
    if (state.equals(LifecycleState.NEW)) {
        state = LifecycleState.STOPPED;
        return;
    }

    // stop()的执行，必须要是`STARTED`和`FAILED`
    if (!state.equals(LifecycleState.STARTED) && !state.equals(LifecycleState.FAILED)) {
        invalidTransition(Lifecycle.BEFORE_STOP_EVENT);
    }

    try {
        // `FAILED`时，直接触发BEFORE_STOP_EVENT事件
        if (state.equals(LifecycleState.FAILED)) {
            // Don't transition to STOPPING_PREP as that would briefly mark the
            // component as available but do ensure the BEFORE_STOP_EVENT is
            // fired
            fireLifecycleEvent(BEFORE_STOP_EVENT, null);
        } else {
            // 设置状态为STOPPING_PREP
            setStateInternal(LifecycleState.STOPPING_PREP, null, false);
        }

        // stop逻辑，抽象方法，组件自行实现
        stopInternal();

        // Shouldn't be necessary but acts as a check that sub-classes are
        // doing what they are supposed to.
        if (!state.equals(LifecycleState.STOPPING) && !state.equals(LifecycleState.FAILED)) {
            invalidTransition(Lifecycle.AFTER_STOP_EVENT);
        }
        // 设置状态为STOPPED
        setStateInternal(LifecycleState.STOPPED, null, false);
    } catch (Throwable t) {
        ExceptionUtils.handleThrowable(t);
        setStateInternal(LifecycleState.FAILED, null, false);
        throw new LifecycleException(sm.getString("lifecycleBase.stopFail",toString()), t);
    } finally {
        if (this instanceof Lifecycle.SingleUse) {
            // Complete stop process first
            setStateInternal(LifecycleState.STOPPED, null, false);
            destroy();
        }
    }
}
```
- destory
```java
@Override
public final synchronized void destroy() throws LifecycleException {
    // `FAILED`状态时，直接触发stop()逻辑
    if (LifecycleState.FAILED.equals(state)) {
        try {
            // Triggers clean-up
            stop();
        } catch (LifecycleException e) {
            // Just log. Still want to destroy.
            log.warn(sm.getString(
                    "lifecycleBase.destroyStopFail", toString()), e);
        }
    }

    // `DESTROYING`和`DESTROYED`时，忽略destroy的执行
    if (LifecycleState.DESTROYING.equals(state) ||
            LifecycleState.DESTROYED.equals(state)) {

        if (log.isDebugEnabled()) {
            Exception e = new LifecycleException();
            log.debug(sm.getString("lifecycleBase.alreadyDestroyed", toString()), e);
        } else if (log.isInfoEnabled() && !(this instanceof Lifecycle.SingleUse)) {
            // Rather than have every component that might need to call
            // destroy() check for SingleUse, don't log an info message if
            // multiple calls are made to destroy()
            log.info(sm.getString("lifecycleBase.alreadyDestroyed", toString()));
        }

        return;
    }

    // 非法状态判断
    if (!state.equals(LifecycleState.STOPPED) &&
            !state.equals(LifecycleState.FAILED) &&
            !state.equals(LifecycleState.NEW) &&
            !state.equals(LifecycleState.INITIALIZED)) {
        invalidTransition(Lifecycle.BEFORE_DESTROY_EVENT);
    }

    try {
        // destroy前状态设置
        setStateInternal(LifecycleState.DESTROYING, null, false);
       // 抽象方法，组件自行实现
        destroyInternal();
        // destroy后状态设置
        setStateInternal(LifecycleState.DESTROYED, null, false);
    } catch (Throwable t) {
        ExceptionUtils.handleThrowable(t);
        setStateInternal(LifecycleState.FAILED, null, false);
        throw new LifecycleException(
                sm.getString("lifecycleBase.destroyFail",toString()), t);
    }
}
```
#### 6.8.2.3 用了什么设计模式？
从上述源码看得出来，LifecycleBase是使用了状态机+模板模式来实现的。模板方法有下面这几个：
```java
// 初始化方法
protected abstract void initInternal() throws LifecycleException;
// 启动方法
protected abstract void startInternal() throws LifecycleException;
// 停止方法
protected abstract void stopInternal() throws LifecycleException;
// 销毁方法
protected abstract void destroyInternal() throws LifecycleException;
```
## 6.9 Tomcat - 组件拓展管理:JMX和MBean
### 6.9.1 为什么要了解JMX
我们在上文中讲Lifecycle和相关组件时，你会发现其实还设计一块就是左侧的JMX和MBean的实现，即LifecycleMBeanBase.
![28.tomcat-x-jmx-1.jpg](../../assets/images/04-主流框架/Servlet容器/28.tomcat-x-jmx-1.jpg)

### 6.9.2 什么是JMX和MBean
> JMX是java1.5中引入的新特性。JMX全称为“Java Management Extension”，即Java管理扩展。

JMX(Java Management Extensions)是一个为应用程序植入管理功能的框架。JMX是一套标准的代理和服务，实际上，用户可以在任何Java应用程序中使用这些代理和服务实现管理。它使用了最简单的一类javaBean，使用有名的MBean，其内部包含了数据信息，这些信息可能是程序配置信息、模块信息、系统信息、统计信息等。MBean可以操作可读可写的属性、直接操作某些函数。

**应用场景**：中间件软件WebLogic的管理页面就是基于JMX开发的，而JBoss则整个系统都基于JMX构架，我们今天讲的Tomcat也是基于JMX开发而来的。

我们看下**JMX的结构**
![29.tomcat-x-jmx-2.png](../../assets/images/04-主流框架/Servlet容器/29.tomcat-x-jmx-2.png)
- `Probe Level `负责资源的检测（获取信息），包含MBeans，通常也叫做Instrumentation Level。MX管理构件（MBean）分为四种形式，分别是标准管理构件（Standard MBean）、动态管理构件（Dynamic MBean）、开放管理构件(Open Mbean)和模型管理构件(Model MBean)。
-` The Agent Level `或者叫做MBean Server（代理服务器），是JMX的核心，连接Mbeans和远程监控程序。
- `Remote Management Level` 通过connectors和adaptors来远程操作MBean Server。
### 6.9.3 JMX使用案例
> 上节只是引入和相关概念，这是不够的，你依然需要一个案例来帮助你理解JMX是如何工作的。
#### 6.9.3.1 基于JMX的监控例子
- `ServerImpl` - 我们模拟的某个服务器ServerImpl状态
```java
public class ServerImpl {
    public final long startTime;
    public ServerImpl() {
        startTime = System.currentTimeMillis();
    }
}
```
- 由于MXBean规定，标准MBean也要实现一个接口，其所有向外界公开的方法都要在该接口中声明，否则管理系统就不能从中获取信息。此外，该接口的命名有一定的规范：在标准MBean类名后加上MBean后缀。这里的标准MBean类就是ServerMonitor，所以其对应的接口就应该是ServerMonitorMBean。因此ServerMonitorMBean的实现如下
```java
public interface ServerMonitorMBean {
	public long getUpTime();
}
```
- 使用ServerMonitor类来监测ServerImpl的状态，实现如下
```java
public class ServerMonitor implements ServerMonitorMBean {
    private final ServerImpl target;
    public ServerMonitor(ServerImpl target) {
        this.target = target;
    }

    @Override
    public long getUpTime() {
        return System.currentTimeMillis() - target.startTime;
    }
}
```
- 对于管理系统来讲，这些MBean中公开的方法，最终会被JMX转换为属性（Attribute）、监听（Listener）和调用（Invoke）的概念。下面代码中Main类的manage方法就模拟了管理程序是如何获取监测到的属性，并表现监测结果。
```java
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

public class Main {
    private static ObjectName objectName;
    private static MBeanServer mBeanServer;

    public static void main(String[] args) throws Exception {
        init();
        manage();
    }

    private static void init() throws Exception {
        ServerImpl serverImpl = new ServerImpl();
        ServerMonitor serverMonitor = new ServerMonitor(serverImpl);
        mBeanServer = MBeanServerFactory.createMBeanServer();
        objectName = new ObjectName("objectName:id=ServerMonitor1");

        // 注册到MBeanServer
        mBeanServer.registerMBean(serverMonitor, objectName);
    }

    private static void manage() throws Exception {
        // 获取属性值
        long upTime = (Long)mBeanServer.getAttribute(objectName, "UpTime");
        System.out.println(upTime);
    }
}
```
- 整体流程
![30.tomcat-x-jmx-3.jpg](../../assets/images/04-主流框架/Servlet容器/30.tomcat-x-jmx-3.jpg)
> 如上步骤就能让你理解常见的Jconsole是如何通过JMX获取属性，对象等监控信息的了。

#### 6.9.3.2 基于JMX的HTMLAdapter案例
> 上面例子，还没有体现adapter展示，比如上述信息在HTML页面中展示出来，再看一个例子
- 我们的管理目标
```java
public class ControlTarget {
	private long width;
	private long length;
	
	public ControlTarget( long width, long length) {
		this.width = width;
		this.length = length;
	}
	
	public long getWidth() {
		return width;
	}
	
	public long getLength() {
		return length;
	}
}
```
- 根据标准MBean类抽象出符合规范的MBean类的接口，并修改标准MBean类实现该接口。
```java
public interface ControlImplMBean {
	public long getLength();
	public long getWidth();
	public long getArea();
	public double getLengthWidthRatio();
}
```
- 根据需求，创建管理（目标程序）的类，其中包含操纵和获取（目标程序）特性的方法。这个类就是标准MBean类。
```java
public class ControlImpl implements ControlImplMBean {
	private ControlTarget target;
	
	public ControlImpl(ControlTarget target) {
		this.target = target;
	}
	
	@Override
	public long getLength() {
		return target.getLength();
	}
	
	@Override
	public long getWidth() {
		return target.getWidth();
	}
	
	@Override
	public long getArea() {
		return target.getLength() * target.getWidth();
	}
	
	@Override
	public double getLengthWidthRatio() {
		return  target.getLength() * 1.0f / target.getWidth();
	}
}
```
- 创建MBean的代理类，代理中包含创建MBeanServer、生成ObjectName、注册MBean、表现MBean
```java
import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;

public class ControlImplAgent {

    public static void main(String[] args) throws MalformedObjectNameException, NullPointerException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {

        // 创建MBeanServer
        MBeanServer server = MBeanServerFactory.createMBeanServer();

        // 为MBean创建ObjectName
        ObjectName controlImplName = new ObjectName("controlImpl:name=firstOne");

        // 注册MBean到Server中
        server.registerMBean(new ControlImpl(new ControlTarget(50, 200)), controlImplName);

        // 表现MBean(一种方式)
        ObjectName adapterName = new ObjectName("ControlImpl:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter, adapterName);

        adapter.start();
        //adapter.stop();
    }

}
```
- 打开相关页面
PS：相关Adapter可以通过<a href = 'https://download.csdn.net/download/com_ma/10379741'>这里</a>下载
![31.tomcat-x-jmx-4.jpg](../../assets/images/04-主流框架/Servlet容器/31.tomcat-x-jmx-4.jpg)

点击最后一个链接
![32.tomcat-x-jmx-5.jpg](../../assets/images/04-主流框架/Servlet容器/32.tomcat-x-jmx-5.jpg)
### 6.9.4 Tomcat如何通过JMX实现组件管理
> 在简单理解了JMX概念和案例之后，我们便可以开始学习Tomcat基于JMX的实现了。
![28.tomcat-x-jmx-1.jpg](../../assets/images/04-主流框架/Servlet容器/28.tomcat-x-jmx-1.jpg)

上述图中，我们看下相关的类的用途
- `MBeanRegistration`：Java JMX框架提供的注册MBean的接口，引入此接口是为了便于使用JMX提供的管理功能；
- `JmxEnabled`: 此接口由组件实现，这些组件在创建时将注册到MBean服务器，在销毁时将注销这些组件。它主要是由实现生命周期的组件来实现的，但并不是专门为它们实现的。
- `LifecycleMBeanBase`：Tomcat提供的对MBeanRegistration的抽象实现类，运用抽象模板模式将所有容器统一注册到JMX；

此外，ContainerBase、StandardServer、StandardService、WebappLoader、Connector、StandardContext、StandardEngine、StandardHost、StandardWrapper等容器都继承了LifecycleMBeanBase，因此这些容器都具有了同样的生命周期并可以通过JMX进行管理。
### 6.9.5 MBeanRegistration
理解MBeanRegistration主要在于:
- 两块内容：registered 和 unregistered
- 两类方法：before和after
```java
public interface MBeanRegistration   {

    // 在注册之前执行的方法，如果发生异常，MBean不会注册到MBean Server中
    public ObjectName preRegister(MBeanServer server,
                                  ObjectName name) throws java.lang.Exception;
    // 在注册之后执行的方法，比如注册失败提供报错信息
    public void postRegister(Boolean registrationDone);


    // 在卸载前执行的方法
    public void preDeregister() throws java.lang.Exception ;
    // 在执行卸载之后的方法
    public void postDeregister();

 }
```






















































































































































































































































































































































































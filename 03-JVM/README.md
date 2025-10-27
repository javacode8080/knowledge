> 最后更新：2025-10-22 | [返回主目录](../README.md)

# 一、概述
![jvm-overview](../assets/images/03-JVM/1.jvm-overview.png)
## 1.1 学习要点
> 不同的虚拟机实现方式上也有差别，如果没有特别指出，这里的JVM指的是sun的HotSpot；不同的JDK版本略有差别，这里主要以1.8为主，具体差异请看各个章节中详解。下图主要表示的逻辑关系，用来将所有知识点放到一张图里，帮助你理解。
![java-jvm-overview](../assets/images/03-JVM/2.java-jvm-overview.png)
>Java进阶 - JVM相关 排错调优： 最后围绕着调试和排错，分析理解JVM调优参数，动态字节码技术及动态在线调试的原理；学会使用常用的调工具和在线动态调试工具等。
![java-jvm-debug](../assets/images/03-JVM/3.java-jvm-debug.png)
# 二、JVM 基础 - 类字节码详解
> 源代码通过编译器编译为字节码，再通过类加载子系统进行加载到JVM中运行。
## 2.1 多语言编译为字节码在JVM运行
计算机是不能直接运行java代码的，必须要先运行java虚拟机，再由java虚拟机运行编译后的java代码。这个编译后的java代码，就是本文要介绍的java字节码。

为什么jvm不能直接运行java代码呢，这是因为在cpu层面看来计算机中所有的操作都是一个个指令的运行汇集而成的，java是高级语言，只有人类才能理解其逻辑，计算机是无法识别的，所以java代码必须要先编译成字节码文件，jvm才能正确识别代码转换后的指令并将其运行。
- Java代码间接翻译成字节码，储存字节码的文件再交由运行于不同平台上的JVM虚拟机去读取执行，从而实现一次编写，到处运行的目的。
- JVM也不再只支持Java，由此衍生出了许多基于JVM的编程语言，如Groovy, Scala, Koltin等等。
![基于JVM运行的语言](../assets/images/03-JVM/4.基于JVM运行的语言.png)
## 2.2 Java字节码文件
class文件本质上是一个以8位字节为基础单位的二进制流，各个数据项目严格按照顺序紧凑的排列在class文件中。jvm根据其特定的规则解析该二进制数据，从而得到相关信息。

Class文件采用一种伪结构来存储数据，它有两种类型：无符号数和表。这里暂不详细的讲。

本文将通过简单的java例子编译后的文件来理解。
### 2.2.1 Class文件的结构属性
在理解之前先从整体看下java字节码文件包含了哪些类型的数据：
![java字节码包含的数据结构](../assets/images/03-JVM/5.java字节码包含的数据结构.png)
### 2.2.2 从一个例子开始
下面以一个简单的例子来逐步讲解字节码。
```java
//Main.java
public class Main {
    
    private int m;
    
    public int inc() {
        return m + 1;
    }
}
```
通过以下命令, 可以在当前所在路径下生成一个Main.class文件。
```sh
javac Main.java
```
以文本的形式打开生成的class文件，内容如下:
```sh
cafe babe 0000 0034 0013 0a00 0400 0f09
0003 0010 0700 1107 0012 0100 016d 0100
0149 0100 063c 696e 6974 3e01 0003 2829
5601 0004 436f 6465 0100 0f4c 696e 654e
756d 6265 7254 6162 6c65 0100 0369 6e63
0100 0328 2949 0100 0a53 6f75 7263 6546
696c 6501 0009 4d61 696e 2e6a 6176 610c
0007 0008 0c00 0500 0601 0010 636f 6d2f
7268 7974 686d 372f 4d61 696e 0100 106a
6176 612f 6c61 6e67 2f4f 626a 6563 7400
2100 0300 0400 0000 0100 0200 0500 0600
0000 0200 0100 0700 0800 0100 0900 0000
1d00 0100 0100 0000 052a b700 01b1 0000
0001 000a 0000 0006 0001 0000 0003 0001
000b 000c 0001 0009 0000 001f 0002 0001
0000 0007 2ab4 0002 0460 ac00 0000 0100
0a00 0000 0600 0100 0000 0800 0100 0d00
0000 0200 0e
```
- 文件开头的4个字节("cafe babe")称之为 魔数，唯有以"cafe babe"开头的class文件方可被虚拟机所接受，这4个字节就是字节码文件的身份识别。
- 0000是编译器jdk版本的次版本号0，0034转化为十进制是52,是主版本号，java的版本号从45开始，除1.0和1.1都是使用45.x外,以后每升一个大版本，版本号加一。也就是说，编译生成该class文件的jdk版本为1.8.0。

通过java -version命令稍加验证, 可得结果。
```sh
Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)
```
继续往下是常量池... 知道是这么分析的就可以了，然后我们通过工具反编译字节码文件继续去看。
### 2.2.3 反编译字节码文件
> 使用到java内置的一个反编译工具javap可以反编译字节码文件, 用法: javap <options> <classes>
其中<options>选项包括:
```sh
  -help  --help  -?        输出此用法消息
  -version                 版本信息
  -v  -verbose             输出附加信息
  -l                       输出行号和本地变量表
  -public                  仅显示公共类和成员
  -protected               显示受保护的/公共类和成员
  -package                 显示程序包/受保护的/公共类和成员(默认)
  -p  -private             显示所有类和成员
  -c                       对代码进行反汇编
  -s                       输出内部类型签名
  -sysinfo                 显示正在处理的类的系统信息 (路径, 大小, 日期, MD5 散列)
  -constants               显示最终常量
  -classpath <path>        指定查找用户类文件的位置
  -cp <path>               指定查找用户类文件的位置
  -bootclasspath <path>    覆盖引导类文件的位置
```
输入命令javap -verbose -p Main.class查看输出内容:
```java
Classfile /E:/JavaCode/TestProj/out/production/TestProj/com/rhythm7/Main.class
  Last modified 2018-4-7; size 362 bytes
  MD5 checksum 4aed8540b098992663b7ba08c65312de
  Compiled from "Main.java"
public class com.rhythm7.Main
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#19         // com/rhythm7/Main.m:I
   #3 = Class              #20            // com/rhythm7/Main
   #4 = Class              #21            // java/lang/Object
   #5 = Utf8               m
   #6 = Utf8               I
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               LocalVariableTable
  #12 = Utf8               this
  #13 = Utf8               Lcom/rhythm7/Main;
  #14 = Utf8               inc
  #15 = Utf8               ()I
  #16 = Utf8               SourceFile
  #17 = Utf8               Main.java
  #18 = NameAndType        #7:#8          // "<init>":()V
  #19 = NameAndType        #5:#6          // m:I
  #20 = Utf8               com/rhythm7/Main
  #21 = Utf8               java/lang/Object
{
  private int m;
    descriptor: I
    flags: ACC_PRIVATE

  public com.rhythm7.Main();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/rhythm7/Main;

  public int inc();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: getfield      #2                  // Field m:I
         4: iconst_1
         5: iadd
         6: ireturn
      LineNumberTable:
        line 8: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  this   Lcom/rhythm7/Main;
}
```
### 2.2.4 字节码文件信息

开头的7行信息包括:Class文件当前所在位置，最后修改时间，文件大小，MD5值，编译自哪个文件，类的全限定名，jdk次版本号，主版本号。

然后紧接着的是该类的访问标志：ACC_PUBLIC, ACC_SUPER，访问标志的含义如下:

| 标志名称        | 标志值 (十六进制) | 含义                                       |
|----------------|------------------|-------------------------------------------|
| ACC_PUBLIC     | 0x0001           | 声明为 public 类型                        |
| ACC_FINAL      | 0x0010           | 声明为 final（仅类可用）                  |
| **ACC_SUPER**  | **0x0020**       | **启用 invokespecial 指令的新语义**        |
| ACC_INTERFACE  | 0x0200           | 标识为接口                                |
| ACC_ABSTRACT   | 0x0400           | 抽象类型（接口/抽象类为 true）            |
| ACC_SYNTHETIC  | 0x1000           | 由编译器生成（非用户代码）                |
| ACC_ANNOTATION | 0x2000           | 标识为注解                                |
| ACC_ENUM       | 0x4000           | 标识为枚举                                |

`invokespecial` 是 JVM 字节码指令，用于调用：

- 实例初始化方法（<init> 构造器）
- 私有方法
- 父类方法（通过 super.method() 调用）
- 通过接口引用的默认方法


### 2.2.5 常量池
`Constant pool`意为常量池。

常量池可以理解成Class文件中的资源仓库。主要存放的是两大类常量：`字面量(Literal)`和`符号引用(Symbolic References)`。字面量类似于java中的常量概念，如文本字符串，final常量等，而符号引用则属于编译原理方面的概念，包括以下三种:
- 类和接口的全限定名(Fully Qualified Name)
- 字段的名称和描述符号(Descriptor)
- 方法的名称和描述符

不同于C/C++, JVM是在加载Class文件的时候才进行的动态链接，也就是说这些字段和方法符号引用只有在运行期转换后才能获得真正的内存入口地址。当虚拟机运行时，需要从常量池获得对应的符号引用，再在类创建或运行时解析并翻译到具体的内存地址中。 直接通过反编译文件来查看字节码内容：
```java
#1 = Methodref          #4.#18         // java/lang/Object."<init>":()V
#4 = Class              #21            // java/lang/Object
#7 = Utf8               <init>
#8 = Utf8               ()V
#18 = NameAndType        #7:#8          // "<init>":()V
#21 = Utf8               java/lang/Object
```
`第一个常量`是一个方法定义，指向了第4和第18个常量。以此类推查看第4和第18个常量。最后可以拼接成第一个常量右侧的注释内容:
```java
java/lang/Object."<init>":()V
```
这段可以理解为该类的实例构造器的声明，由于Main类没有重写构造方法，所以调用的是父类的构造方法（**所有构造器（无论是默认构造器还是自定义构造器）都会包含对父类构造器的调用**）。此处也说明了Main类的直接父类是Object。 该方法默认返回值是V, 也就是void，无返回值。

为什么是父类的构造器在常量池而不是自身的构造器
- 默认构造器真实存在：
    - 在反编译输出中明确显示为 public com.rhythm7.Main();
    - 这是编译器自动生成的
    - 构造器方法 (<init>) 不需要单独的常量池方法引用
- 常量池 #1 的作用：
  - 不是 Main 的构造器定义
  - 而是 Main 构造器需要调用的父类构造器 (Object.<init>)
  - 因为所有 Java 类最终都继承自 Object
`第二个常量`同理可得: 
```java
#2 = Fieldref           #3.#19         // com/rhythm7/Main.m:I
#3 = Class              #20            // com/rhythm7/Main
#5 = Utf8               m
#6 = Utf8               I
#19 = NameAndType        #5:#6          // m:I
#20 = Utf8               com/rhythm7/Main
```
此处声明了一个字段m，类型为I, I即是int类型。关于字节码的类型对应如下：

| 标识字符 | 对应类型      | 说明                                                                 | 示例                          |
|----------|--------------|----------------------------------------------------------------------|-------------------------------|
| **B**    | `byte`       | 8位有符号整数                                                        | `B` → `byte`                 |
| **C**    | `char`       | Unicode 字符（UTF-16）                                               | `C` → `char`                 |
| **D**    | `double`     | 双精度浮点数 (64位)                                                  | `D` → `double`               |
| **F**    | `float`      | 单精度浮点数 (32位)                                                  | `F` → `float`                |
| **I**    | `int`        | 32位整数                                                             | `I` → `int`                  |
| **J**    | `long`       | 64位整数                                                             | `J` → `long`                 |
| **S**    | `short`      | 16位整数                                                             | `S` → `short`                |
| **Z**    | `boolean`    | 布尔值 (`true`/`false`)                                              | `Z` → `boolean`              |
| **V**    | `void`       | 无返回值类型                                                         | `V` → `void`                 |
| **L**    | 对象引用类型 | 全限定类名，以 `;` 结尾                                              | `Ljava/lang/Object;` → `Object` |
| **[**    | 数组         | 前缀符号，可多层嵌套                                                 | `[I` → `int[]`               |

---

- 补充说明：

1. **数组类型**：使用 `[` 前缀表示
   - `[I` → 一维 `int` 数组
   - `[[D` → 二维 `double` 数组
   - `[Ljava/lang/String;` → `String[]`

2. **方法描述符**：结合类型标识符描述方法签名
   - `(I)V` → 参数 `int`，返回 `void`
   - `(Ljava/lang/String;[B)Z` → 参数 `String` 和 `byte[]`，返回 `boolean`

3. **特殊规则**：
   - `long` 和 `double` 在局部变量表中占用 2 个槽位（slot）
   - 对象类型必须使用全限定类名（如 `Ljava/lang/Object;`）

### 2.2.6 字段表
在常量池之后的是对类内部的方法描述，在字节码中以表的集合形式表现，暂且不管字节码文件的16进制文件内容如何，我们直接看反编译后的内容。
```java
private int m;
  descriptor: I
  flags: ACC_PRIVATE
```
此处声明了一个私有变量m，类型为int，返回值为int
### 2.2.7 方法表
```java
public com.rhythm7.Main();
   descriptor: ()V
   flags: ACC_PUBLIC
   Code:
     stack=1, locals=1, args_size=1
        0: aload_0
        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
        4: return
```
这里是构造方法：Main()，返回值为void, 公开方法。

code内的主要属性为:

- `stack`: 最大操作数栈，JVM运行时会根据这个值来分配栈帧(Frame)中的操作栈深度,此处为1
- `locals`: 局部变量所需的存储空间，单位为Slot, Slot是虚拟机为局部变量分配内存时所使用的最小单位，为4个字节大小。方法参数(包括实例方法中的隐藏参数this)，显示异常处理器的参数(try catch中的catch块所定义的异常)，方法体中定义的局部变量都需要使用局部变量表来存放。值得一提的是，locals的大小并不一定等于所有局部变量所占的Slot之和，因为局部变量中的Slot是可以重用的。
- `args_size`: 方法参数的个数，这里是1，因为每个实例方法都会有一个隐藏参数this
- `attribute_info`: 方法体内容，0,1,4为字节码"行号"，该段代码的意思是将第一个引用类型本地变量推送至栈顶，然后执行该类型的实例方法，也就是常量池存放的第一个变量，也就是注释里的java/lang/Object."":()V, 然后执行返回语句，结束方法。
- `LineNumberTable`: 该属性的作用是描述源码行号与字节码行号(字节码偏移量)之间的对应关系。可以使用 -g:none 或-g:lines选项来取消或要求生成这项信息，如果选择不生成LineNumberTable，当程序运行异常时将无法获取到发生异常的源码行号，也无法按照源码的行数来调试程序。
- `LocalVariableTable`: 该属性的作用是描述帧栈中局部变量与源码中定义的变量之间的关系。可以使用 -g:none 或 -g:vars来取消或生成这项信息，如果没有生成这项信息，那么当别人引用这个方法时，将无法获取到参数名称，取而代之的是arg0, arg1这样的占位符。 start 表示该局部变量在哪一行开始可见，length表示可见行数，Slot代表所在帧栈位置，Name是变量名称，然后是类型签名。

同理可以分析Main类中的另一个方法"inc()":

方法体内的内容是：将this入栈，获取字段#2并置于栈顶, 将int类型的1入栈，将栈内顶部的两个数值相加，返回一个int类型的值。

### 2.2.8 属性表
```java
   LineNumberTable:
       line 3: 0
     LocalVariableTable:
       Start  Length  Slot  Name   Signature
           0       5     0  this   Lcom/rhythm7/Main;
```
### 2.2.9 字节码文件的整体介绍
**下面针对完整的反编译字节码进行总体介绍：**

#### 1. **类文件头信息**
```plaintext
Classfile /E:/.../Main.class
Last modified 2018-4-7; size 362 bytes
MD5 checksum 4aed8540b098992663b7ba08c65312de
Compiled from "Main.java"
```
- **文件路径**：类文件存储位置
- **修改时间**：2018-4-7
- **文件大小**：362字节
- **MD5校验**：确保文件完整性
- **源文件**：`Main.java`

---

#### 2. **类基本信息**
```plaintext
public class com.rhythm7.Main
minor version: 0
major version: 52
flags: ACC_PUBLIC, ACC_SUPER
```
- **类名**：`com.rhythm7.Main`（全限定名）
- **版本号**：
  - `major 52` → Java 8（版本映射：45=Java 1.1, 52=Java 8）
  - `minor 0` → 次要版本号（通常为0）
- **访问标志**：
  - `ACC_PUBLIC`：公有类
  - `ACC_SUPER`：历史遗留标志（保持向后兼容）

---

#### 3. **常量池（Constant Pool）**
常量池是类文件的"符号表"，存储所有字面量和符号引用：
```plaintext
#1 = Methodref    #4.#18  // java/lang/Object."<init>":()V
#2 = Fieldref     #3.#19  // com/rhythm7/Main.m:I
#3 = Class        #20     // com/rhythm7/Main
...
#5 = Utf8         m       // 字段名
#6 = Utf8         I       // int类型符
#7 = Utf8         <init>  // 构造器方法名
```
- **符号引用类型**：
  - `Methodref`：方法引用（类+方法）
  - `Fieldref`：字段引用（类+字段）
  - `Class`：类引用
  - `Utf8`：UTF-8编码字符串
  - `NameAndType`：名称+描述符组合

> 常量池索引**从1开始**，索引0保留不用

---

#### 4. **字段表（Field Table）**
```plaintext
private int m;
descriptor: I
flags: ACC_PRIVATE
```
- **字段元数据**：
  - 名称：`m`（对应常量池 `#5`）
  - 类型：`I`（基本类型int）
  - 访问标志：`ACC_PRIVATE`
- **作用**：定义类的成员变量，不包含具体值（值在对象实例化时分配）

---

#### 5. **方法表（Method Table）**
##### 5.1 构造器方法
```plaintext
public com.rhythm7.Main();
descriptor: ()V
flags: ACC_PUBLIC
Code:
  stack=1, locals=1, args_size=1
  0: aload_0
  1: invokespecial #1  // Object.<init>
  4: return
```
- **方法描述符**：`()V` → 无参数，返回void
- **操作数栈深度**：stack=1
- **局部变量槽**：locals=1
- **字节码指令**：
  - `aload_0`：加载this引用
  - `invokespecial #1`：调用父类构造器
  - `return`：方法返回

##### 5.2 inc()方法
```plaintext
public int inc();
descriptor: ()I
flags: ACC_PUBLIC
Code:
  stack=2, locals=1, args_size=1
  0: aload_0
  1: getfield #2  // Field m:I
  4: iconst_1
  5: iadd
  6: ireturn
```
- **方法描述符**：`()I` → 无参数，返回int
- **字节码指令**：
  - `getfield #2`：获取字段m的值
  - `iconst_1`：压入常量1
  - `iadd`：执行加法（m+1）
  - `ireturn`：返回int结果

---

#### 6. **属性表（Attribute Table）**
##### 6.1 LineNumberTable
```plaintext
LineNumberTable:
  line 3: 0   // Main.java第3行对应字节码偏移0
```
- **源码映射**：关联字节码偏移量与源代码行号

##### 6.2 LocalVariableTable
```plaintext
LocalVariableTable:
  Start Length Slot Name Signature
  0     5      0   this Lcom/rhythm7/Main;
```
- **局部变量信息**：
  - `this`：引用类型，Slot 0
  - 作用范围：字节码偏移0-5

---
### 2.2.10 类名
最后很显然是源码文件：
```java
SourceFile: "Main.java"
```
## 2.3 再看两个示例
### 2.3.1 分析try-catch-finally
通过以上一个最简单的例子，可以大致了解源码被编译成字节码后是什么样子的。 下面利用所学的知识点来分析一些Java问题:
```java
public class TestCode {
    public int foo() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
```
试问当不发生异常和发生异常的情况下，foo()的返回值分别是多少。
```sh
javac TestCode.java
javap -verbose TestCode.class
```
查看字节码的foo方法内容:
```java
public int foo();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=5, args_size=1
         0: iconst_1 //int型1入栈 ->栈顶=1
         1: istore_1 //将栈顶的int型数值存入第二个局部变量 ->局部2=1
         2: iload_1 //将第二个int型局部变量推送至栈顶 ->栈顶=1
         3: istore_2 //!!将栈顶int型数值存入第三个局部变量 ->局部3=1
         
         4: iconst_3 //int型3入栈 ->栈顶=3
         5: istore_1 //将栈顶的int型数值存入第二个局部变量 ->局部2=3
         6: iload_2 //!!将第三个int型局部变量推送至栈顶 ->栈顶=1
         7: ireturn //从当前方法返回栈顶int数值 ->1
         
         8: astore_2 // ->局部3=Exception
         9: iconst_2 // ->栈顶=2
        10: istore_1 // ->局部2=2
        11: iload_1 //->栈顶=2
        12: istore_3 //!! ->局部4=2
        
        13: iconst_3 // ->栈顶=3
        14: istore_1 // ->局部1=3
        15: iload_3 //!! ->栈顶=2
        16: ireturn // -> 2
        
        17: astore        4 //将栈顶引用型数值存入第五个局部变量=any
        19: iconst_3 //将int型数值3入栈 -> 栈顶3
        20: istore_1 //将栈顶第一个int数值存入第二个局部变量 -> 局部2=3
        21: aload         4 //将局部第五个局部变量(引用型)推送至栈顶
        23: athrow //将栈顶的异常抛出
      Exception table:
         from    to  target type
             0     4     8   Class java/lang/Exception //0到4行对应的异常，对应#8中储存的异常
             0     4    17   any //Exeption之外的其他异常
             8    13    17   any
            17    19    17   any
```
在字节码的4,5，以及13,14中执行的是同一个操作，就是将int型的3入操作数栈顶，并存入第二个局部变量。这正是我们源码在finally语句块中内容。也就是说，JVM在处理异常时，会在每个可能的分支都将finally语句重复执行一遍。

通过一步步分析字节码，可以得出最后的运行结果是：
- 不发生异常时: return 1
- 发生异常时: return 2
- 发生非Exception及其子类的异常，抛出异常，不返回值
### 2.3.2 扩展分析下面类三个方法的输出结果
```java
public class Main {

    private int m;

    public int foo() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    public int foo2() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
        }
    }

    public int foo3() {
        try {
            m = 1;
            return m;
        } catch (Exception e) {
            m = 2;
            return m;
        } finally {
            m = 3;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("foo():"+main.foo());
        System.out.println("foo2():"+main.foo2());
        System.out.println("foo3():"+main.foo3());
        System.out.println("m:"+main.m);
    }
}
```
输出结果：
```java
foo():1
foo2():3
foo3():1
m:3
```

从字节码角度分析输出结果的原因如下，我们将逐个方法进行解析（基于标准 Javac 编译行为）：

---

### 1. `foo()` 方法分析
**输出结果：1**  
字节码关键逻辑：
```java
public int foo();
  Code:
   0: iconst_1       // 将常量1压入栈 (x=1)
   1: istore_1       // 存储到局部变量x (槽位1)
   2: iload_1        // 加载x的值 (1) 到栈顶 (准备返回)
   3: istore_2       // 将返回值暂存到槽位2 (固定返回值保存位置)
   4: iconst_3       // finally块开始 (x=3)
   5: istore_1       // 更新x=3 (但返回值已保存)
   6: iload_2        // 加载暂存的返回值 (1)
   7: ireturn        // 返回1

   8: astore_2       // catch块开始 (异常处理)
   ...               // catch逻辑（本例未触发）
   16: iconst_3      // finally块 (x=3)
   17: istore_1
   18: aload_2       // 重新抛出异常
   19: athrow
```

**关键机制**：
- **遇到 return x 时，需要暂存返回值（因 finally 必须执行）**: iconst_1; istore_1
- **返回值提前固化**：在进入 finally 前（字节码偏移3），返回值 1 已存入局部变量表槽位2
- **finally 修改无效**：虽然 finally 将 x 改为 3，但不影响已固化的返回值
- **返回路径**：`ireturn` 指令从槽位2加载值返回

---

### 2. `foo2()` 方法分析
**输出结果：3**  
字节码关键逻辑：
```java
public int foo2();
  Code:
   0: iconst_1       // x=1
   1: istore_1
   2: iload_1        // 加载x(1)准备返回
   3: istore_2       // 暂存返回值1 (槽位2)
   4: iconst_3       // finally块 (x=3)
   5: istore_1
   6: iload_1        // 加载x的新值(3) 
   7: ireturn        // 返回3

   8: astore_2      // catch块
   ...               // catch逻辑
   18: iconst_3      // finally块
   19: istore_1
   20: iload_1
   21: istore_3      // 再次覆盖返回值为3
   22: iload_3
   23: ireturn       // 返回3
```

**关键机制**：
- **finally 中的 return 优先**：字节码偏移7-9 覆盖了之前暂存的返回值
- **双保险设计**：
  - 正常路径：偏移3暂存1 → 偏移7用3覆盖
  - 异常路径：catch后同样用3覆盖返回值
- **本质**：finally 中的 return 会劫持所有退出路径

---

### 3. `foo3()` 方法分析
**输出结果：1（但成员变量 m 最终为3）**  
字节码关键逻辑：
```java
public int foo3();
  Code:
   0: aload_0        // 加载this
   1: iconst_1       
   2: putfield #2    // m=1 (Field m:I)
   5: aload_0
   6: getfield #2    // 获取m的值(1)
   9: istore_1       // 暂存返回值1 (槽位1)
   10: aload_0       // finally块开始
   11: iconst_3
   12: putfield #2   // m=3 (修改成员变量)
   13: iload_1       // 加载暂存值1
   14: ireturn       // 返回1

   15: astore_1      // catch块
   ...               // catch逻辑
   23: aload_0       // finally块
   24: iconst_3
   25: putfield #2   // m=3
   26: aload_1       // 重新抛出异常
   27: athrow
```

**关键机制**：
- **成员变量 vs 返回值**：
  - 返回值：在偏移9处固化 m 的当前值 1
  - 成员变量：finally 修改的是对象状态（偏移12）
- **两次内存操作**：
  ```mermaid
  sequenceDiagram
    字节码->>堆内存: putfield m=1 (偏移2)
    字节码->>局部变量表: istore_1=1 (偏移9)
    字节码->>堆内存: putfield m=3 (偏移12)
    返回指令->>调用者: 返回局部变量1的值(1)
  ```

---

### 内存状态变化时序图
```mermaid
sequenceDiagram
  main->>foo(): 调用
  foo()->>局部变量表: 固化返回值1
  foo()->>局部变量表: 修改x=3 (finally)
  foo()->>main: 返回1
  
  main->>foo2(): 调用
  foo2()->>局部变量表: 暂存1
  foo2()->>局部变量表: 覆盖返回值为3 (finally)
  foo2()->>main: 返回3
  
  main->>foo3(): 调用
  foo3()->>堆内存: m=1
  foo3()->>局部变量表: 固化返回值1
  foo3()->>堆内存: m=3 (finally)
  foo3()->>main: 返回1
  
  main->>堆内存: 读取m=3
```

### 最终输出解析
| 输出项         | 值  | 原因 |
|----------------|-----|------|
| `foo()`        | 1   | 返回值在finally执行前固化 |
| `foo2()`       | 3   | finally中的return覆盖原值 |
| `foo3()`       | 1   | 返回m的快照值（修改前） |
| `m`            | 3   | finally修改了对象状态 |

> **设计本质**：Java 使用局部变量槽固化返回值，finally 修改局部变量不影响已固化的返回值，但修改成员变量会影响对象状态。finally 中的 return 会彻底改变返回路径。

**这里最重要的是要明确这几个地方：**
- 1.遇到 return 时，需要暂存返回值（因 finally 必须执行）i_store
- 2.finally有return时的编译器优化，当检测到finally中有return语句时，废弃所有路径的暂存返回值
- 3.finally没有return的时候返回暂存值

| 场景                  | 返回值加载指令 | 是否需要临时槽位 | 典型字节码模式               |
|-----------------------|----------------|------------------|------------------------------|
| **无 finally 块**     | `iload_1`      | 否               | `iload_1 → ireturn`          |
| **有 finally(无return)** | `iload_2`      | 是               | `istore_2 → ... → iload_2`   |
| **有 finally(有return)** | `iload_1`      | 否（最终路径）   | `iload_1 → ireturn`          |
# 三、JVM 基础 - 字节码的增强技术
## 3.1 概述
在上文中，着重介绍了字节码的结构，这为我们了解字节码增强技术的实现打下了基础。字节码增强技术就是一类对现有字节码进行修改或者动态生成全新字节码文件的技术。接下来，我们将从最直接操纵字节码的实现方式开始深入进行剖析
![字节码增强技术框架](../assets/images/03-JVM/6.字节码增强技术框架.png)
## 3.2 ASM
对于需要手动操纵字节码的需求，可以使用ASM，它可以直接生产 .class字节码文件，也可以在类被加载入JVM之前动态修改类行为（如下图17所示）。ASM的应用场景有AOP（Cglib就是基于ASM）、热部署、修改其他jar包中的类等。当然，涉及到如此底层的步骤，实现起来也比较麻烦。接下来，本文将介绍ASM的两种API，并用ASM来实现一个比较粗糙的AOP。但在此之前，为了让大家更快地理解ASM的处理流程，强烈建议读者先对访问者模式进行了解。简单来说，访问者模式主要用于修改或操作一些数据结构比较稳定的数据，而通过第一章，我们知道字节码文件的结构是由JVM固定的，所以很适合利用访问者模式对字节码文件进行修改。
![ASM](../assets/images/03-JVM/7.ASM.png)
### 3.2.1 ASM API
#### 3.2.1.1 核心API(Core API)
ASM Core API可以类比解析XML文件中的SAX方式，不需要把这个类的整个结构读取进来，就可以用流式的方法来处理字节码文件。好处是非常节约内存，但是编程难度较大。然而出于性能考虑，一般情况下编程都使用Core API。在Core API中有以下几个关键类：
- ClassReader：用于读取已经编译好的.class文件。
- ClassWriter：用于重新构建编译后的类，如修改类名、属性以及方法，也可以生成新的类的字节码文件。
- 各种Visitor类：如上所述，CoreAPI根据字节码从上到下依次处理，对于字节码文件中不同的区域有不同的Visitor，比如用于访问方法的MethodVisitor、用于访问类变量的FieldVisitor、用于访问注解的AnnotationVisitor等。为了实现AOP，重点要使用的是MethodVisitor。
#### 3.2.1.2 树形API(Tree API)
ASM Tree API可以类比解析XML文件中的DOM方式，把整个类的结构读取到内存中，缺点是消耗内存多，但是编程比较简单。TreeApi不同于CoreAPI，TreeAPI通过各种Node类来映射字节码的各个区域，类比DOM节点，就可以很好地理解这种编程方式。
### 3.2.2 直接利用ASM实现AOP
利用ASM的CoreAPI来增强类。这里不纠结于AOP的专业名词如切片、通知，只实现在方法调用前、后增加逻辑，通俗易懂且方便理解。首先定义需要被增强的Base类：其中只包含一个process()方法，方法内输出一行“process”。增强后，我们期望的是，方法执行前输出“start”，之后输出”end”。
```java
public class Base {
    public void process(){
        System.out.println("process");
    }
}
```
为了利用ASM实现AOP，需要定义两个类：一个是MyClassVisitor类，用于对字节码的visit以及修改；另一个是Generator类，在这个类中定义ClassReader和ClassWriter，其中的逻辑是，classReader读取字节码，然后交给MyClassVisitor类处理，处理完成后由ClassWriter写字节码并将旧的字节码替换掉。Generator类较简单，我们先看一下它的实现，如下所示，然后重点解释MyClassVisitor类。
```java
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class Generator {
    public static void main(String[] args) throws Exception {
		//读取
        ClassReader classReader = new ClassReader("meituan/bytecode/asm/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        //输出
        File f = new File("operation-server/target/classes/meituan/bytecode/asm/Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");
    }
}
```
MyClassVisitor继承自ClassVisitor，用于对字节码的观察。它还包含一个内部类MyMethodVisitor，继承自MethodVisitor用于对类内方法的观察，它的整体代码如下：
```java
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor implements Opcodes {
    public MyClassVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }
    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
                exceptions);
        //Base类中有两个方法：无参构造以及process方法，这里不增强构造方法
        if (!name.equals("<init>") && mv != null) {
            mv = new MyMethodVisitor(mv);
        }
        return mv;
    }
    class MyMethodVisitor extends MethodVisitor implements Opcodes {
        public MyMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("start");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                    || opcode == Opcodes.ATHROW) {
                //方法在返回之前，打印"end"
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("end");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            }
            mv.visitInsn(opcode);
        }
    }
}
```
利用这个类就可以实现对字节码的修改。详细解读其中的代码，对字节码做修改的步骤是：
- 首先通过MyClassVisitor类中的visitMethod方法，判断当前字节码读到哪一个方法了。跳过构造方法 <init> 后，将需要被增强的方法交给内部类MyMethodVisitor来进行处理。
- 接下来，进入内部类MyMethodVisitor中的visitCode方法，它会在ASM开始访问某一个方法的Code区时被调用，重写visitCode方法，将AOP中的前置逻辑就放在这里。 MyMethodVisitor继续读取字节码指令，每当ASM访问到无参数指令时，都会调用MyMethodVisitor中的visitInsn方法。我们判断了当前指令是否为无参数的“return”指令，如果是就在它的前面添加一些指令，也就是将AOP的后置逻辑放在该方法中。
- 综上，重写MyMethodVisitor中的两个方法，就可以实现AOP了，而重写方法时就需要用ASM的写法，手动写入或者修改字节码。通过调用methodVisitor的visitXXXXInsn()方法就可以实现字节码的插入，XXXX对应相应的操作码助记符类型，比如mv.visitLdcInsn(“end”)对应的操作码就是ldc “end”，即将字符串“end”压入栈。 完成这两个visitor类后，运行Generator中的main方法完成对Base类的字节码增强，增强后的结果可以在编译后的target文件夹中找到Base.class文件进行查看，可以看到反编译后的代码已经改变了。然后写一个测试类MyTest，在其中new Base()，并调用base.process()方法，可以看到下图右侧所示的AOP实现效果：
![ASM增强效果](../assets/images/03-JVM/8.ASM增强效果.png)
### 3.2.3 ASM工具
利用ASM手写字节码时，需要利用一系列visitXXXXInsn()方法来写对应的助记符，所以需要先将每一行源代码转化为一个个的助记符，然后通过ASM的语法转换为visitXXXXInsn()这种写法。第一步将源码转化为助记符就已经够麻烦了，不熟悉字节码操作集合的话，需要我们将代码编译后再反编译，才能得到源代码对应的助记符。第二步利用ASM写字节码时，如何传参也很令人头疼。ASM社区也知道这两个问题，所以提供了工具<a href= 'https://plugins.jetbrains.com/plugin/5918-asm-bytecode-outline'>ASM ByteCode Outline</a>
安装后，右键选择“Show Bytecode Outline”，在新标签页中选择“ASMified”这个tab，如图19所示，就可以看到这个类中的代码对应的ASM写法了。图中上下两个红框分别对应AOP中的前置逻辑于后置逻辑，将这两块直接复制到visitor中的visitMethod()以及visitInsn()方法中，就可以了。
![ASM工具详情](../assets/images/03-JVM/9.ASM工具详情.png)
## 3.3 Javassist
ASM是在指令层次上操作字节码的，阅读上文后，我们的直观感受是在指令层次上操作字节码的框架实现起来比较晦涩。故除此之外，我们再简单介绍另外一类框架：强调源代码层次操作字节码的框架Javassist。

利用Javassist实现字节码增强时，可以无须关注字节码刻板的结构，其优点就在于编程简单。直接使用java编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构或者动态生成类。其中最重要的是ClassPool、CtClass、CtMethod、CtField这四个类：

- CtClass（compile-time class）：编译时类信息，它是一个class文件在代码中的抽象表现形式，可以通过一个类的全限定名来获取一个CtClass对象，用来表示这个类文件。
- ClassPool：从开发视角来看，ClassPool是一张保存CtClass信息的HashTable，key为类名，value为类名对应的CtClass对象。当我们需要对某个类进行修改时，就是通过pool.getCtClass(“className”)方法从pool中获取到相应的CtClass。
- CtMethod、CtField：这两个比较好理解，对应的是类中的方法和属性。

了解这四个类后，我们可以写一个小Demo来展示Javassist简单、快速的特点。我们依然是对Base中的process()方法做增强，在方法调用前后分别输出”start”和”end”，实现代码如下。我们需要做的就是从pool中获取到相应的CtClass对象和其中的方法，然后执行method.insertBefore和insertAfter方法，参数为要插入的Java代码，再以字符串的形式传入即可，实现起来也极为简单。
```java
import com.meituan.mtrace.agent.javassist.*;

public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        // 1. 获取默认的类池（管理类的容器）
        ClassPool cp = ClassPool.getDefault();
        
        // 2. 从类池中获取目标类 "meituan.bytecode.javassist.Base" 的 CtClass 对象
        CtClass cc = cp.get("meituan.bytecode.javassist.Base");
        
        // 3. 获取目标类中声明的 "process" 方法
        CtMethod m = cc.getDeclaredMethod("process");
        
        // 4. 在 process 方法开头插入字节码：打印 "start"
        m.insertBefore("{ System.out.println(\"start\"); }");
        
        // 5. 在 process 方法结尾插入字节码：打印 "end"（包括正常返回和异常退出）
        m.insertAfter("{ System.out.println(\"end\"); }");
        
        // 6. 将修改后的 CtClass 转换为可运行的 Class 对象
        Class c = cc.toClass();
        
        // 7. 将修改后的字节码写入文件系统（保存到 /Users/zen/projects 目录）
        cc.writeFile("/Users/zen/projects");
        
        // 8. 通过反射创建修改后类的实例
        Base h = (Base)c.newInstance();
        
        // 9. 调用 process 方法验证修改效果
        h.process();
    }
}

```
## 3.4 运行时类的重载
### 3.4.1 问题引出
上一章重点介绍了两种不同类型的字节码操作框架，且都利用它们实现了较为粗糙的AOP。其实，为了方便大家理解字节码增强技术，在上文中我们避重就轻将ASM实现AOP的过程分为了两个main方法：第一个是利用MyClassVisitor对已编译好的class文件进行修改，第二个是new对象并调用。这期间并不涉及到JVM运行时对类的重加载，而是在第一个main方法中，通过ASM对已编译类的字节码进行替换，在第二个main方法中，直接使用已替换好的新类信息。另外在Javassist的实现中，我们也只加载了一次Base类，也不涉及到运行时重加载类。

如果我们在一个JVM中，先加载了一个类，然后又对其进行字节码增强并重新加载会发生什么呢？模拟这种情况，只需要我们在上文中Javassist的Demo中main()方法的第一行添加Base b=new Base()，即在增强前就先让JVM加载Base类，然后在执行到c.toClass()方法时会抛出错误，如下图20所示。跟进c.toClass()方法中，我们会发现它是在最后调用了ClassLoader的native方法defineClass()时报错。也就是说，JVM是不允许在运行时动态重载一个类的。
![JVM不允许运行时动态加载类](../assets/images/03-JVM/10.JVM不允许运行时动态加载类.png)
显然，如果只能在类加载前对类进行强化，那字节码增强技术的使用场景就变得很窄了。我们期望的效果是：在一个持续运行并已经加载了所有类的JVM中，还能利用字节码增强技术对其中的类行为做替换并重新加载。为了模拟这种情况，我们将Base类做改写，在其中编写main方法，每五秒调用一次process()方法，在process()方法中输出一行“process”。

我们的目的就是，在JVM运行中的时候，将process()方法做替换，在其前后分别打印“start”和“end”。也就是在运行中时，每五秒打印的内容由”process”变为打印”start process end”。那如何解决JVM不允许运行时重加载类信息的问题呢？为了达到这个目的，我们接下来一一来介绍需要借助的Java类库。
```java
import java.lang.management.ManagementFactory;

public class Base {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];
        //打印当前Pid
        System.out.println("pid:"+s);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            process();
        }
    }

    public static void process() {
        System.out.println("process");
    }
}
```
### 3.4.2 Instrument
instrument是JVM提供的一个可以修改已加载类的类库，专门为Java语言编写的插桩服务提供支持。它需要依赖JVMTI的Attach API机制实现，JVMTI这一部分，我们将在下一小节进行介绍。在JDK 1.6以前，instrument只能在JVM刚启动开始加载类时生效，而在JDK 1.6之后，instrument支持了在运行时对类定义的修改。要使用instrument的类修改功能，我们需要实现它提供的ClassFileTransformer接口，定义一个类文件转换器。接口中的transform()方法会在类文件被加载时调用，而在transform方法里，我们可以利用上文中的ASM或Javassist对传入的字节码进行改写或替换，生成新的字节码数组后返回。

我们定义一个实现了ClassFileTransformer接口的类TestTransformer，依然在其中利用Javassist对Base类中的process()方法进行增强，在前后分别打印“start”和“end”，代码如下
```java
import java.lang.instrument.ClassFileTransformer;

public class TestTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("Transforming " + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("meituan.bytecode.jvmti.Base");
            CtMethod m = cc.getDeclaredMethod("process");
            m.insertBefore("{ System.out.println(\"start\"); }");
            m.insertAfter("{ System.out.println(\"end\"); }");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
```
现在有了Transformer，那么它要如何注入到正在运行的JVM呢？还需要定义一个Agent，借助Agent的能力将Instrument注入到JVM中。我们将在下一小节介绍Agent，现在要介绍的是Agent中用到的另一个类Instrumentation。在JDK 1.6之后，Instrumentation可以做启动后的Instrument、本地代码（Native Code）的Instrument，以及动态改变Classpath等等。我们可以向Instrumentation中添加上文中定义的Transformer，并指定要被重加载的类，代码如下所示。这样，当Agent被Attach到一个JVM中时，就会执行类字节码替换并重载入JVM的操作。
```java
import java.lang.instrument.Instrumentation;

public class TestAgent {
    public static void agentmain(String args, Instrumentation inst) {
        //指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new TestTransformer(), true);
        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
```
### 3.4.3 JVMTI & Agent & Attach API
上一小节中，我们给出了Agent类的代码，追根溯源需要先介绍JPDA（Java Platform Debugger Architecture）[Java 平台调试器架构]。如果JVM启动时开启了JPDA，那么类是允许被重新加载的。在这种情况下，已被加载的旧版本类信息可以被卸载，然后重新加载新版本的类。正如JDPA名称中的Debugger，JDPA其实是一套用于调试Java程序的标准，任何JDK都必须实现该标准。

JPDA定义了一整套完整的体系，它将调试体系分为三部分，并规定了三者之间的通信接口。三部分由低到高分别是Java 虚拟机工具接口（JVMTI），Java 调试协议（JDWP）以及 Java 调试接口（JDI），三者之间的关系如下图所示：
![JPDA](../assets/images/03-JVM/11.JPDA.png)

现在回到正题，我们可以借助JVMTI的一部分能力，帮助动态重载类信息。JVM TI（JVM TOOL INTERFACE，JVM工具接口）是JVM提供的一套对JVM进行操作的工具接口。通过JVMTI，可以实现对JVM的多种操作，它通过接口注册各种事件勾子，在JVM事件触发时，同时触发预定义的勾子，以实现对各个JVM事件的响应，事件包括类文件加载、异常产生与捕获、线程启动和结束、进入和退出临界区、成员变量修改、GC开始和结束、方法调用进入和退出、临界区竞争与等待、VM启动与退出等等。

而Agent就是JVMTI的一种实现，Agent有两种启动方式，一是随Java进程启动而启动，经常见到的java -agentlib就是这种方式；二是运行时载入，通过attach API，将模块（jar包）动态地Attach到指定进程id的Java进程内。

Attach API 的作用是提供JVM进程间通信的能力，比如说我们为了让另外一个JVM进程把线上服务的线程Dump出来，会运行jstack或jmap的进程，并传递pid的参数，告诉它要对哪个进程进行线程Dump，这就是Attach API做的事情。在下面，我们将通过Attach API的loadAgent()方法，将打包好的Agent jar包动态Attach到目标JVM上。具体实现起来的步骤如下：

- 定义Agent，并在其中实现AgentMain方法，如上一小节中定义的代码块7中的TestAgent类；
- 然后将TestAgent类打成一个包含MANIFEST.MF的jar包，其中MANIFEST.MF文件中将Agent-Class属性指定为TestAgent的全限定名，如下图所示；
![Agent-MANIFESTMF文件配置](../assets/images/03-JVM/12.Agent-MANIFESTMF文件配置.png)
- 最后利用Attach API，将我们打包好的jar包Attach到指定的JVM pid上，代码如下：
```java
import com.sun.tools.attach.VirtualMachine;

public class Attacher {
    public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("39333");
        vm.loadAgent("/Users/zen/operation_server_jar/operation-server.jar");
    }
}
```
- 由于在MANIFEST.MF中指定了Agent-Class，所以在Attach后，目标JVM在运行时会走到TestAgent类中定义的agentmain()方法，而在这个方法中，我们利用Instrumentation，将指定类的字节码通过定义的类转化器TestTransformer做了Base类的字节码替换（通过javassist），并完成了类的重新加载。由此，我们达成了“在JVM运行时，改变类的字节码并重新载入类信息”的目的。

以下为运行时重新载入类的效果：先运行Base中的main()方法，启动一个JVM，可以在控制台看到每隔五秒输出一次”process”。接着执行Attacher中的main()方法，并将上一个JVM的pid传入。此时回到上一个main()方法的控制台，可以看到现在每隔五秒输出”process”前后会分别输出”start”和”end”，也就是说完成了运行时的字节码增强，并重新载入了这个类。
![类重新加载](../assets/images/03-JVM/13.类重新加载.png)

总结：

Attacher **不是**在 Java 项目最开始运行时就在的，而是需要**通过另一个独立的 Java 进程**来将增强的字节码加载到已经运行的 Java 进程中。以下是详细说明：

- 📌 关键流程解析
1. **目标 JVM 进程启动**  
   - 首先运行包含 `Base.main()` 的 Java 程序（目标进程）
   - 该进程正常执行，无任何字节码增强逻辑
   - 示例输出：每隔5秒输出 `"process"`

2. **Attacher 作为独立进程启动**  
   - 在另一个独立的 Java 进程中运行 `Attacher.main()`
   - 此进程通过 `VirtualMachine.attach(pid)` 连接到目标 JVM 进程
   - 使用 `vm.loadAgent()` 加载包含字节码增强逻辑的 Agent JAR 包

3. **动态注入过程**  
   ```mermaid
   sequenceDiagram
       目标JVM->>Attacher: 正在运行(输出"process")
       Attacher->>目标JVM: attach(pid)
       Attacher->>目标JVM: loadAgent(agent.jar)
       目标JVM->>TestAgent: 执行agentmain()
       TestAgent->>Instrumentation: addTransformer()
       TestAgent->>Instrumentation: retransformClasses(Base)
       Instrumentation->>TestTransformer: transform() 修改字节码
       目标JVM->>Base: 重加载增强后的类
       目标JVM->>控制台: 开始输出"start"和"end"
   ```

   🔧 技术组件角色
| 组件 | 运行位置 | 作用 |
|------|----------|------|
| **目标 JVM** | 独立进程 | 运行被监控/增强的业务程序 |
| **Attacher** | 独立进程 | 将 Agent 注入目标 JVM 的"注入器" |
| **TestAgent** | 注入到目标 JVM | 注册字节码转换器 |
| **TestTransformer** | 在目标 JVM 内执行 | 动态修改类字节码 |

- ⚠️ 重要注意事项
1. **Attacher 必须独立运行**
   - 不能和目标 JVM 在同一个进程内
   - 需要获取目标 JVM 的进程 ID (pid)

2. **依赖 JDK 工具包**
   ```java
   import com.sun.tools.attach.VirtualMachine; // 属于 JDK 的 tools.jar
   ```
   - 运行 Attacher 时需要 JDK 而非 JRE
   - Maven 依赖示例：
     ```xml
     <dependency>
         <groupId>com.sun</groupId>
         <artifactId>tools</artifactId>
         <version>${java.version}</version>
         <scope>system</scope>
         <systemPath>${java.home}/../lib/tools.jar</systemPath>
     </dependency>
     ```

3. **权限要求**
   - Attacher 进程需要和目标 JVM 相同的用户权限
   - Linux/Unix 系统可能需要 `sudo` 权限

- 🌰 典型执行流程
1. 终端1 启动目标程序：
   ```bash
   java -cp app.jar com.example.Main # 输出"process" every 5s
   ```
   PID=39333

2. 终端2 编译并运行 Attacher：
   ```bash
   javac -cp $JAVA_HOME/lib/tools.jar Attacher.java
   java -cp .:$JAVA_HOME/lib/tools.jar Attacher 39333
   ```

3. 观察终端1输出变化：
   ```
   process     # 原始输出
   process     # 原始输出
   start       # Agent注入后
   process     # 增强逻辑
   end         # Agent注入后
   ```

- 💡 设计价值
这种"进程分离"架构实现了：
1. **无侵入式增强**：无需修改目标程序代码
2. **运行时热更新**：业务系统无需重启
3. **生产环境诊断**：动态添加监控/日志功能
4. **安全隔离**：注入失败不影响目标进程

> **总结**：Attacher 是一个独立的外部进程，专门用于将字节码增强逻辑动态注入到**已经运行的目标 JVM 进程**中，不是目标程序的一部分。这种机制是 Java 热更新和 APM（应用性能监控）系统的核心实现原理。
## 3.5 使用场景
至此，字节码增强技术的可使用范围就不再局限于JVM加载类前了。通过上述几个类库，我们可以在运行时对JVM中的类进行修改并重载了。通过这种手段，可以做的事情就变得很多了：
- 热部署：不部署服务而对线上服务做修改，可以做打点、增加日志等操作。
- Mock：测试时候对某些服务做Mock。
- 性能诊断工具：比如bTrace就是利用Instrument，实现无侵入地跟踪一个正在运行的JVM，监控到类和方法级别的状态信息。
## 3.6 总结
字节码增强技术相当于是一把打开运行时JVM的钥匙，利用它可以动态地对运行中的程序做修改，也可以跟踪JVM运行中程序的状态。此外，我们平时使用的动态代理、AOP也与字节码增强密切相关，它们实质上还是利用各种手段生成符合规范的字节码文件。综上所述，掌握字节码增强后可以高效地定位并快速修复一些棘手的问题（如线上性能问题、方法出现不可控的出入参需要紧急加日志等问题），也可以在开发中减少冗余代码，大大提高开发效率。
# 四、JVM 基础 - Java 类加载机制
## 4.1 类的生命周期
其中类加载的过程包括了`加载`、`连接`(验证、准备、解析)、`初始化`五个阶段。在这五个阶段中，加载、验证、准备和初始化这四个阶段发生的顺序是确定的，而解析阶段则不一定，它在某些情况下可以在初始化阶段之后开始，这是为了支持Java语言的运行时绑定(也成为动态绑定或晚期绑定)。另外注意这里的几个阶段是按顺序开始，而不是按顺序进行或完成，因为这些阶段通常都是互相交叉地混合进行的，通常在一个阶段执行的过程中调用或激活另一个阶段。
![类加载过程](../assets/images/03-JVM/14.类加载过程.png)
## 4.2 类的加载: 查找并加载类的二进制数据
加载是类加载过程的第一个阶段，在加载阶段，虚拟机需要完成以下三件事情:
- 通过一个类的全限定名来获取其定义的二进制字节流。
- 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。
- 在Java堆中生成一个代表这个类的java.lang.Class对象，作为对方法区中这些数据的访问入口。
![类加载机制-加载](../assets/images/03-JVM/15.类加载机制-加载.png)
相对于类加载的其他阶段而言，加载阶段(准确地说，是加载阶段获取类的二进制字节流的动作)是可控性最强的阶段，因为开发人员既可以使用系统提供的类加载器来完成加载，也可以自定义自己的类加载器来完成加载。

加载阶段完成后，虚拟机外部的 二进制字节流就按照虚拟机所需的格式存储在方法区之中，而且在Java堆中也创建一个java.lang.Class类的对象，这样便可以通过该对象访问方法区中的这些数据。

类加载器并不需要等到某个类被“首次主动使用”时再加载它，JVM规范允许类加载器在预料某个类将要被使用时就预先加载它，如果在预先加载的过程中遇到了.class文件缺失或存在错误，类加载器必须在程序首次主动使用该类时才报告错误(LinkageError错误)如果这个类一直没有被程序主动使用，那么类加载器就不会报告错误。
> 加载.class文件的方式
- 从本地系统中直接加载
- 通过网络下载.class文件
- 从zip，jar等归档文件中加载.class文件
- 从专有数据库中提取.class文件
- 将Java源文件动态编译为.class文件
## 4.3 连接
### 4.3.1 验证: 确保被加载的类的正确性
验证是连接阶段的第一步，这一阶段的目的是为了确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。验证阶段大致会完成4个阶段的检验动作:
- `文件格式验证`:验证字节流是否符合Class文件格式的规范；例如: 是否以0xCAFEBABE开头、主次版本号是否在当前虚拟机的处理范围之内、常量池中的常量是否有不被支持的类型。
- `元数据验证`: 对字节码描述的信息进行语义分析(注意: 对比javac编译阶段的语义分析)，以保证其描述的信息符合Java语言规范的要求；例如: 这个类是否有父类，除了java.lang.Object之外。
- `字节码验证`: 通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。
- `符号引用验证`: 确保解析动作能正确执行。
> 验证阶段是非常重要的，但不是必须的，它对程序运行期没有影响，如果所引用的类经过反复验证，那么可以考虑采用-Xverifynone参数来关闭大部分的类验证措施，以缩短虚拟机类加载的时间。
### 4.3.2 准备: 为类的静态变量分配内存，并将其初始化为默认值
准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，这些内存都将在**方法区**中分配。对于该阶段有以下几点需要注意*:
- 这时候进行内存分配的仅包括类变量(static)，而不包括实例变量，**实例变量会在对象实例化时随着对象一块分配在Java堆中**。
- **这里所设置的初始值通常情况下是数据类型默认的零值(如0、0L、null、false等)，而不是被在Java代码中被显式地赋予的值。**

假设一个类变量的定义为: `public static int value = 3`；那么变量value在准备阶段过后的初始值为0，而不是3，因为这时候尚未开始执行任何Java方法，而把value赋值为3的`put static`指令是在程序编译后，存放于类构造器<clinit>()方法之中的，所以把value赋值为3的动作将在初始化阶段才会执行。
> 这里还需要注意如下几点
- 对基本数据类型来说，对于`类变量(static:一定是全局变量)和全局变量`，如果不显式地对其赋值而直接使用，则系统会为其赋予默认的零值，而对于`局部变量`来说，在使用前必须显式地为其赋值，否则编译时不通过。
- 对于同时被static和final修饰的常量，必须在声明的时候就为其显式地赋值，否则编译时不通过；而只被final修饰的常量则既可以在声明时显式地为其赋值，也可以在类初始化时显式地为其赋值，总之，`final在使用前必须为其显式地赋值，系统不会为其赋予默认零值`。
- 对于引用数据类型reference来说，如数组引用、对象引用等，如果没有对其进行显式地赋值而直接使用，系统都会为其赋予默认的零值，即null。
- 如果在数组初始化时没有对数组中的各元素赋值，那么其中的元素将根据对应的数据类型而被赋予默认的零值。
- 如果类字段的字段属性表中存在ConstantValue属性，即同时被final和static修饰，那么在准备阶段变量value就会被初始化为ConstValue属性所指定的值。假设上面的类变量value被定义为:  public static final int value = 3；编译时Javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据ConstantValue的设置将value赋值为3。我们可以理解为static final常量在**编译期**就将其结果放入了调用它的类的常量池中(注意和public static int value = 3赋值在初始化阶段有区别)
 
#### static int i=3和static final int i=3的区别
- 📌 核心区别对比

| 特性 | `static int i = 3` | `static final int i = 3` |
|------|-------------------|-------------------------|
| **赋值时机** | 类加载的**初始化阶段** | 编译期间|
| **存储位置** | 方法区的类变量区域 | 运行时常量池 |
| **是否可修改** | ✅ 可重新赋值 | ❌ 不可修改（编译时常量） |
| **内存分配** | 类加载时分配内存 | 编译期确定，直接内联到代码 |

- 🔧 具体实现机制

1. `static int i = 3`（类加载初始化阶段）
```java
public class Test {
    static int i = 3;  // 在<clinit>()方法中赋值
    
    // 编译后生成的字节码逻辑：
    static <clinit>() {
        i = 3;  // 在类初始化时执行赋值
    }
}
```

**类加载过程：**
1. **加载** → 2. **验证** → 3. **准备** → 4. **解析** → 5. **初始化**
- 在**准备阶段**：`i` 被赋默认值 `0`
- 在**初始化阶段**：执行 `<clinit>()` 方法，`i` 被赋值为 `3`

2. `static final int i = 3`（编译期常量池）
```java
public class Test {
    static final int i = 3;  // 编译时常量
    
    public void print() {
        System.out.println(i);  // 编译后直接变为：System.out.println(3)
    }
}
```

**编译期处理：**
- 编译器直接将常量值 `3` 写入 `.class` 文件的**常量池**
- **所有使用 `i` 的地方都会被替换为字面量 `3`**
- 运行时**不会**有内存分配和赋值操作

- 🧪 验证示例

1. 反编译对比
```java
// 源代码
public class ConstantTest {
    static int normalStatic = 3;
    static final int CONSTANT_STATIC = 3;
    
    public void test() {
        int a = normalStatic;     // 需要访问静态变量
        int b = CONSTANT_STATIC;  // 直接替换为3
    }
}
```

**编译后的字节码伪代码：**
```java
public class ConstantTest {
    static int normalStatic = 0;  // 准备阶段默认值
    
    // 没有CONSTANT_STATIC字段，因为被内联了
    
    public void test() {
        int a = ConstantTest.normalStatic;  // getstatic指令
        int b = 3;                         // ldc指令，直接加载常量3
    }
    
    static <clinit>() {
        normalStatic = 3;  // 初始化阶段赋值
    }
}
```

- 💡 实际影响

1. 性能差异
```java
// 场景1：访问static变量（需要类加载和内存访问）
static int count = 100;
for (int i = 0; i < 1000000; i++) {
    sum += count;  // 每次都需要读取内存
}

// 场景2：访问static final常量（编译期优化）
static final int COUNT = 100;
for (int i = 0; i < 1000000; i++) {
    sum += COUNT;  // 编译为：sum += 100;
}
```

2. 跨类引用影响
```java
// A.java
public class A {
    public static int VAR = 10;
    public static final int CONST = 20;
}

// B.java  
public class B {
    public void method() {
        System.out.println(A.VAR);    // 需要加载A类
        System.out.println(A.CONST);  // 不需要加载A类，直接内联20
    }
}
```

3. 反射可修改性
```java
public class ReflectionTest {
    static int mutable = 1;
    static final int immutable = 2;
    
    public static void main(String[] args) throws Exception {
        Field f1 = ReflectionTest.class.getDeclaredField("mutable");
        f1.set(null, 100);  // ✅ 修改成功
        
        Field f2 = ReflectionTest.class.getDeclaredField("immutable");
        f2.setAccessible(true);
        f2.set(null, 200);  // ❌ 可能抛出IllegalAccessException
    }
}
```
#### final修饰不同场景的区别
| 场景 | 是否编译期常量 | 关键特征 |
|------|---------------|----------|
| `static final int = 3` | ✅ **是** | 值内联到字节码 |
| `static final Object = new Object()` | ❌ **不是** | 引用不可变，对象内容可能可变 |
| `static final String = "字面量"` | ✅ **是** | 字符串字面量是特例 |
| `static final String = new String()` | ❌ **不是** | 运行时创建的对象 |

### 4.3.3 解析: 把类中的符号引用转换为直接引用

解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程，解析动作主要针对`类`或`接口`、`字段`、`类方法`、`接口方法`、`方法类型`、`方法句柄`和`调用点`限定符7类符号引用进行。

`符号引用`就是一组符号来描述目标，可以是任何字面量。

`直接引用`就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。

<a href='#双亲委派机制的实现'>双亲委派机制源码(ClassLoader.loadClass())</a>中存在resolve参数，用来控制加载之后是否进行解析

#### 4.3.3.1. **符号引用解析为直接引用**
```java
public class Example {
    public void method() {
        // 在字节码中，这些是符号引用（Symbolic References）
        String str = "hello";
        System.out.println(str);  // System/out 和 println 都是符号引用
    }
}
```

**解析过程：**
- `java/lang/System` → 内存地址 0x7f123456
- `java/io/PrintStream` → 内存地址 0x7f123789  
- `println` 方法 → 方法表索引 #15

#### 4.3.3.2. **解析的具体内容**
```java
// 解析以下类型的符号引用：
resolveClass(c) {
    // 1. 类/接口解析
    resolveClassReferences();     // 将类名解析为Class对象
    
    // 2. 字段解析  
    resolveFieldReferences();     // 将字段名解析为内存偏移量
    
    // 3. 方法解析
    resolveMethodReferences();    // 将方法名解析为方法指针
    
    // 4. 接口方法解析
    resolveInterfaceMethodReferences();
}
```

## 4.4 初始化
初始化，为类的静态变量赋予正确的初始值，JVM负责对类进行初始化，主要对`类变量`进行初始化。在Java中对类变量进行初始值设定有两种方式:

- 声明类变量时指定初始值
- 使用静态代码块为类变量指定初始值
### 4.4.1 JVM初始化步骤
- 假如这个类还没有被加载和连接，则程序先加载并连接该类
- 假如该类的直接父类还没有被初始化，则先初始化其直接父类
- 假如类中有初始化语句，则系统依次执行这些初始化语句

### 4.4.2 类初始化时机: 
只有当对类的主动使用的时候才会导致类的初始化，类的主动使用包括以下六种:
- 创建类的实例，也就是new的方式
- 访问某个类或接口的静态变量，或者对该静态变量赋值
- 调用类的静态方法
- 反射(如Class.forName("com.pdai.jvm.Test"))
- 初始化某个类的子类，则其父类也会被初始化
- Java虚拟机启动时被标明为启动类的类(Java Test)，直接使用java.exe命令来运行某个主类
## 4.5 使用
类访问方法区内的数据结构的接口， 对象是Heap区的数据。
## 4.6 卸载
Java虚拟机将结束生命周期的几种情况
- 执行了System.exit()方法
- 程序正常执行结束
- 程序在执行过程中遇到了异常或错误而异常终止
- 由于操作系统出现错误而导致Java虚拟机进程终止

## 4.7 类加载器
### 4.7.1 类加载器的层次
![类加载器的层次](../assets/images/03-JVM/16.类加载器的层次.png)
> 注意: 这里父类加载器并不是通过继承关系来实现的，而是采用组合实现的。

- 站在Java虚拟机的角度来讲，只存在两种不同的类加载器: 
  - 启动类加载器: 它使用C++实现(这里仅限于Hotspot，也就是JDK1.5之后默认的虚拟机，有很多其他的虚拟机是用Java语言实现的)，是虚拟机自身的一部分；
  - 所有其他的类加载器: 这些类加载器都由Java语言实现，独立于虚拟机之外，并且全部继承自抽象类java.lang.ClassLoader，这些类加载器需要由启动类加载器加载到内存中之后才能去加载其他的类。
- 站在Java开发人员的角度来看，类加载器可以大致划分为以下三类 :
- `启动类加载器`: Bootstrap ClassLoader，负责加载存放在JDK\jre\lib(JDK代表JDK的安装目录，下同)下，或被-Xbootclasspath参数指定的路径中的，并且能被虚拟机识别的类库(如rt.jar，所有的java.*开头的类均被Bootstrap ClassLoader加载)。启动类加载器是无法被Java程序直接引用的。
- `扩展类加载器`: Extension ClassLoader，该加载器由sun.misc.Launcher$ExtClassLoader实现，它负责加载JDK\jre\lib\ext目录中，或者由java.ext.dirs系统变量指定的路径中的所有类库(如javax.*开头的类)，开发者可以直接使用扩展类加载器。
- `应用程序类加载器`: Application ClassLoader，该类加载器由sun.misc.Launcher$AppClassLoader来实现，它负责加载用户类路径(ClassPath)所指定的类，开发者可以直接使用该类加载器，如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。

应用程序都是由这三种类加载器互相配合进行加载的，如果有必要，我们还可以加入自定义的类加载器。因为JVM自带的ClassLoader只是懂得从本地文件系统加载标准的java class文件，因此如果编写了自己的ClassLoader，便可以做到如下几点:
- 在执行非置信代码之前，自动验证数字签名。
- 动态地创建符合用户特定需要的定制化构建类。
- 从特定的场所取得java class，例如数据库中和网络中。
### 4.7.2 寻找类加载器
寻找类加载器小例子如下:
```java
package com.pdai.jvm.classloader;
public class ClassLoaderTest {
     public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }
}
```
结果如下:
```java
sun.misc.Launcher$AppClassLoader@64fef26a
sun.misc.Launcher$ExtClassLoader@1ddd40f3
null
```
从上面的结果可以看出，并没有获取到ExtClassLoader的父Loader，原因是BootstrapLoader(引导类加载器)是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null。
## 4.8 类的加载
类加载有三种方式:
- 1、命令行启动应用时候由JVM初始化加载
- 2、通过Class.forName()方法动态加载
- 3、通过ClassLoader.loadClass()方法动态加载
```java
package com.pdai.jvm.classloader;
public class loaderTest { 
        public static void main(String[] args) throws ClassNotFoundException { 
                ClassLoader loader = HelloWorld.class.getClassLoader(); 
                System.out.println(loader); 
                //使用ClassLoader.loadClass()来加载类，不会执行初始化块 
                loader.loadClass("Test2"); 
                //使用Class.forName()来加载类，默认会执行初始化块 
//                Class.forName("Test2"); 
                //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块 
//                Class.forName("Test2", false, loader); 
        } 
}

public class Test2 { 
        static { 
                System.out.println("静态初始化块执行了！"); 
        } 
}
```
分别切换加载方式，会有不同的输出结果。
> Class.forName()和ClassLoader.loadClass()区别?
- Class.forName(): 将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块；
- ClassLoader.loadClass(): 只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
- Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象 。
 
 **"需要知道"原则**
- **Class.forName()**：我需要这个类**立即工作**，包括它的所有静态初始化
- **ClassLoader.loadClass()**：我只需要知道这个类存在，但还不需要它工作
## 4.9 JVM类加载机制
- `全盘负责`，当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该类加载器负责载入，除非显示使用另外一个类加载器来载入
- `父类委托`，先让父类加载器试图加载该类，只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类
- `缓存机制`，缓存机制将会保证所有加载过的Class都会被缓存，当程序中需要使用某个Class时，类加载器先从缓存区寻找该Class，只有缓存区不存在，系统才会读取该类对应的二进制数据，并将其转换成Class对象，存入缓存区。**这就是为什么修改了Class后，必须重启JVM，程序的修改才会生效**
- `双亲委派机制`, 如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把请求委托给父加载器去完成，依次向上，因此，所有的类加载请求最终都应该被传递到顶层的启动类加载器中，只有当父加载器在它的搜索范围中没有找到所需的类时，即无法完成该加载，子加载器才会尝试自己去加载该类。
### 4.9.1 双亲委派机制过程？
- 当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
- 当ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader去完成。
- 如果BootStrapClassLoader加载失败(**例如在$JAVA_HOME/jre/lib里未查找到该class**)，会使用ExtClassLoader来尝试加载；
- 若ExtClassLoader也加载失败，则会使用AppClassLoader来加载，如果AppClassLoader也加载失败，则会报出异常ClassNotFoundException。
### 4.9.2 <a id='双亲委派机制的实现'>双亲委派代码实现</a>
```java
public Class<?> loadClass(String name)throws ClassNotFoundException {
            return loadClass(name, false);
    }
    protected synchronized Class<?> loadClass(String name, boolean resolve)throws ClassNotFoundException {
            // 首先判断该类型是否已经被加载
            Class c = findLoadedClass(name);
            if (c == null) {
                //如果没有被加载，就委托给父类加载或者委派给启动类加载器加载
                try {
                    if (parent != null) {
                         //如果存在父类加载器，就委派给父类加载器加载
                        c = parent.loadClass(name, false);
                    } else {
                    //如果不存在父类加载器，就检查是否是由启动类加载器加载的类，通过调用本地方法native Class findBootstrapClass(String name)
                        c = findBootstrapClass0(name);
                    }
                } catch (ClassNotFoundException e) {
                 // 如果父类加载器和启动类加载器都不能完成加载任务，才调用自身的加载功能
                    c = findClass(name);
                }
            }
            // resolve 控制是否在加载后立即进行类的"链接（Linking）"阶段的解析（Resolution）步骤
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
```
### 4.9.3 双亲委派优势
- 系统类防止内存中出现多份同样的字节码（同名的类会优先原则jdk中的加载而不是Application ClassLoader）
- 保证Java程序安全稳定运行
## 4.10 自定义类加载器
通常情况下，我们都是直接使用系统类加载器。但是，有的时候，我们也需要自定义类加载器。比如应用是通过网络来传输 Java 类的字节码，为保证安全性，这些字节码经过了加密处理，这时系统类加载器就无法对其进行加载，这样则需要自定义类加载器来实现。自定义类加载器一般都是继承自 ClassLoader 类(抽象类，基础)，从上面对 loadClass 方法来分析来看，我们只需要重写 findClass 方法(自定义扩展点)即可。下面我们通过一个示例来演示自定义类加载器的流程:
```java
package com.pdai.jvm.classloader;
import java.io.*;

public class MyClassLoader extends ClassLoader {

    private String root;

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public static void main(String[] args)  {

        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("D:\\temp");

        Class<?> testClass = null;
        try {
            testClass = classLoader.loadClass("com.pdai.jvm.classloader.Test2");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
```
自定义类加载器的核心在于对字节码文件的获取，如果是加密的字节码则需要在该类中对文件进行解密。由于这里只是演示，我并未对class文件进行加密，因此没有解密的过程。

这里有几点需要注意 :
- 1、这里传递的文件名需要是类的全限定性名称，即com.pdai.jvm.classloader.Test2格式的，因为 defineClass 方法是按这种格式进行处理的。
- 最好不要重写loadClass方法，因为这样容易破坏双亲委托模式。
- Test 类本身可以被 AppClassLoader 类加载，因此我们不能把com/pdai/jvm/classloader/Test2.class 放在类路径下(`AppClassLoader默认加载类路径`)。否则，由于双亲委托机制的存在，会直接导致该类由 AppClassLoader 加载，而不会通过我们自定义类加载器来加载。
- ClassLoader抽象类的parent默认值AppClassLoader
```java
// 当你这样创建自定义ClassLoader时：
public class MyClassLoader extends ClassLoader {
    // 没有显式调用父类构造器
}

// 实际上Java会调用ClassLoader的无参构造函数：
public ClassLoader() {
    this(checkCreateClassLoader(), getSystemClassLoader()); // ← 关键！
}

// getSystemClassLoader() 返回系统类加载器（AppClassLoader）
```

## 4.11 JDK 11版本中`ClassLoader.loadClass()`主要改进

### 4.11.1 🔍 **关键区别分析**

| 特性 | 旧版本 | JDK 11版本 |
|------|-------|------------|
| **同步机制** | `synchronized`方法级锁 | `synchronized(getClassLoadingLock(name))`细粒度锁 |
| **异常处理** | 直接抛出异常 | 捕获异常后继续尝试当前加载器 |
| **性能监控** | 无 | 增加了`PerfCounter`性能统计 |
| **启动类加载** | `findBootstrapClass0()` | `findBootstrapClassOrNull()`更清晰的方法名 |

### 4.11.2 🔧 **`findClass()`的核心作用**

#### 4.11.2.1. **`findClass()`是类加载的"模板方法"**

```java
// ClassLoader中的默认实现 - 需要子类重写
protected Class<?> findClass(String name) throws ClassNotFoundException {
    throw new ClassNotFoundException(name);
}
```

**设计意图：** `findClass()`是留给子类实现的**扩展点**，用于定义自定义的类加载逻辑。

#### 4.11.2.2. **`loadClass()` vs `findClass()`的分工**

```java
public class CustomClassLoader extends ClassLoader {
    
    // loadClass() 负责双亲委派机制（框架逻辑）
    // 这个方法通常不需要重写
    
    // findClass() 负责具体的类加载实现（业务逻辑）
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 自定义加载逻辑：从文件、网络、数据库等加载类字节码
        byte[] classBytes = loadClassBytes(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }
    
    private byte[] loadClassBytes(String className) {
        // 实现具体的字节码加载逻辑
        // 例如：从特定目录读取.class文件
    }
}
```

### 4.11.3 🏗️ **实际自定义类加载器示例**

#### 4.11.3.1 示例1：文件系统类加载器
```java
public class FileSystemClassLoader extends ClassLoader {
    private String classPath;
    
    public FileSystemClassLoader(String classPath) {
        this.classPath = classPath;
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 1. 读取.class文件
            String path = classPath + name.replace('.', '/') + ".class";
            byte[] classBytes = Files.readAllBytes(Paths.get(path));
            
            // 2. 调用defineClass完成类定义
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("类文件未找到: " + name, e);
        }
    }
}
```

#### 4.11.3.2 示例2：网络类加载器
```java
public class NetworkClassLoader extends ClassLoader {
    private String serverUrl;
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 从网络服务器加载类字节码
            URL url = new URL(serverUrl + name.replace('.', '/') + ".class");
            try (InputStream is = url.openStream()) {
                byte[] classBytes = is.readAllBytes();
                return defineClass(name, classBytes, 0, classBytes.length);
            }
        } catch (IOException e) {
            throw new ClassNotFoundException("网络加载失败: " + name, e);
        }
    }
}
```

### 4.11.4 🔄 **完整的类加载流程**

#### 4.11.4.1 **JDK 11的`loadClass()`详细执行流程：**

```java
// 伪代码展示完整流程
public Class<?> loadClass(String name, boolean resolve) {
    // 1. 获取类加载锁（避免重复加载）
    synchronized (getClassLoadingLock(name)) {
        
        // 2. 检查是否已加载（缓存机制）
        Class<?> c = findLoadedClass(name);
        if (c != null) {
            if (resolve) resolveClass(c);
            return c;
        }
        
        long t0 = System.nanoTime();
        
        // 3. 双亲委派：先让父加载器尝试
        try {
            if (parent != null) {
                c = parent.loadClass(name, false);  // 注意：resolve=false
            } else {
                c = findBootstrapClassOrNull(name); // 启动类加载器
            }
        } catch (ClassNotFoundException e) {
            // 4. 父加载器找不到，不立即失败，继续尝试当前加载器
        }
        
        // 5. 当前加载器尝试（核心：调用findClass）
        if (c == null) {
            long t1 = System.nanoTime();
            c = findClass(name);  // ⭐ 这里是关键调用！
            
            // 6. 性能统计
            recordStatistics(t1 - t0, t1);
        }
        
        // 7. 解析阶段
        if (resolve) {
            resolveClass(c);
        }
        
        return c;
    }
}
```

### 4.11.5 💡 **`findClass()`的设计哲学**

#### 4.11.5.1  **模板方法模式的应用**
```java
// ClassLoader框架提供了算法骨架
public abstract class ClassLoader {
    
    // 模板方法 - 定义加载流程
    public final Class<?> loadClass(String name) {
        // 1. 检查缓存
        // 2. 双亲委派
        // 3. 调用findClass() ← 留给子类实现
        // 4. 解析类
    }
    
    // 抽象方法 - 子类必须实现
    protected abstract Class<?> findClass(String name);
}
```

#### 4.11.5.2 **为什么分离`loadClass()`和`findClass()`？**

| 方法 | 责任 | 可变性 |
|------|------|--------|
| **`loadClass()`** | 框架逻辑（双亲委派、缓存、同步） | **final** - 不允许修改 |
| **`findClass()`** | 业务逻辑（如何获取字节码） | **protected** - 需要子类实现 |

**好处：**
- **框架稳定性**：双亲委派等核心机制不可修改
- **扩展灵活性**：加载来源可以自由定制
- **职责分离**：框架管流程，子类管实现

### 4.11.6 🎯 **JDK 11改进的实际价值**

#### 4.11.6.1. **更细粒度的锁机制**
```java
// 旧版本：整个方法同步，性能瓶颈
public synchronized Class<?> loadClass(String name) {...}

// JDK 11：基于类名的细粒度锁
protected Class<?> loadClass(String name, boolean resolve) {
    synchronized (getClassLoadingLock(name)) {  // 每个类独立的锁
        // ...
    }
}

// 锁实现：通常使用ConcurrentHashMap
protected Object getClassLoadingLock(String className) {
    Object lock = this;
    if (parallelLockMap != null) {
        // 为每个类名创建独立的锁对象
        lock = parallelLockMap.computeIfAbsent(className, k -> new Object());
    }
    return lock;
}
```

#### 4.11.6.2. **更好的异常处理策略**
```java
// 旧版本：父加载器失败就立即抛出异常
try {
    c = parent.loadClass(name, false);
} catch (ClassNotFoundException e) {
    // 直接抛出，不给当前加载器机会
    throw e;
}

// JDK 11：父加载器失败后，当前加载器还有机会
try {
    c = parent.loadClass(name, false);
} catch (ClassNotFoundException e) {
    // 捕获异常，继续执行下面的当前加载器逻辑
    // 这样设计更合理：父加载器找不到，不代表当前加载器也找不到
}
```

#### 4.11.6.3. **性能监控支持**
```java
// 新增的性能计数器
PerfCounter.getParentDelegationTime().addTime(t1 - t0);  // 父委派时间
PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);   // 查找类时间
PerfCounter.getFindClasses().increment();                // 加载类计数
```
# 五、JVM 基础 - JVM 内存结构
![JVM运行时数据区](../assets/images/03-JVM/17.JVM运行时数据区.jpg)
## 5.1 运行时数据区
内存是非常重要的系统资源，是硬盘和 CPU 的中间仓库及桥梁，承载着操作系统和应用程序的实时运行。JVM 内存布局规定了 Java 在运行过程中内存申请、分配、管理的策略，保证了 JVM 的高效稳定运行。不同的 JVM 对于内存的划分方式和管理机制存在着部分差异。

下图是 JVM 整体架构，中间部分就是 Java 虚拟机定义的各种运行时数据区域。
![JVM整体架构](../assets/images/03-JVM/18.JVM整体架构.jpg)

Java 虚拟机定义了若干种程序运行期间会使用到的运行时数据区，其中有一些会随着虚拟机启动而创建，随着虚拟机退出而销毁。另外一些则是与线程一一对应的，这些与线程一一对应的数据区域会随着线程开始和结束而创建和销毁。

- 线程私有：程序计数器、虚拟机栈、本地方法栈(Hotspot JVM将虚拟机栈和本地方法栈合二为一)
- 线程共享：堆、方法区, 堆外内存（Java7的永久代或JDK8的元空间、代码缓存）

**但值得注意的一个重要的补充：线程专属内存区**

虽然我们说线程私有的**区域**主要是PC寄存器和栈，但还有一个非常重要的**线程私有数据**存在于“堆”中，那就是 **TLAB**。

*   **TLAB**： 即线程本地分配缓冲区。它是堆内存中划分出来的一小块专属区域，每个线程独立拥有一份。当线程需要创建新对象时，会优先在自己的TLAB中快速分配内存，这样可以避免在堆上进行同步操作，提升效率。

所以，更严谨地说，在 Hotspot JVM 中：
*   线程私有的**内存区域**：程序计数器、虚拟机栈。
*   线程私有的**数据**：程序计数器、虚拟机栈中的数据，以及堆中的 TLAB。


> 下面我们就来一一解读下这些内存区域，先从最简单的入手

## 5.2 一、程序计数器
程序计数寄存器（Program Counter Register），Register 的命名源于 CPU 的寄存器，寄存器存储指令相关的线程信息，CPU 只有把数据装载到寄存器才能够运行。

这里，并非是广义上所指的物理寄存器，叫程序计数器（或PC计数器或指令计数器）会更加贴切，并且也不容易引起一些不必要的误会。`JVM 中的 PC 寄存器是对物理 PC 寄存器的一种抽象模拟`。

程序计数器是一块较小的内存空间，可以看作是当前线程所执行的字节码的行号指示器。

### 5.2.1 作用
PC 寄存器用来存储指向下一条指令的地址，即将要执行的指令代码。由执行引擎读取下一条指令。
![程序计数器](../assets/images/03-JVM/19.PC寄存器作用.jpg)
（分析：进入class文件所在目录，执行 javap -v xx.class 反解析（或者通过 IDEA 插件 Jclasslib 直接查看，上图），可以看到当前类对应的Code区（汇编指令）、本地变量表、异常表和代码行偏移量映射表、常量池等信息。）
### 5.2.2 概述
> 通过下面两个问题，理解下PC计数器
- 使用PC寄存器存储字节码指令地址有什么用呢？为什么使用PC寄存器记录当前线程的执行地址呢？

因为CPU需要不停的切换各个线程，这时候切换回来以后，就得知道接着从哪开始继续执行。JVM的字节码解释器就需要通过改变PC寄存器的值来明确下一条应该执行什么样的字节码指令。
- PC寄存器为什么会被设定为线程私有的？

多线程在一个特定的时间段内只会执行其中某一个线程方法，CPU会不停的做任务切换，这样必然会导致经常中断或恢复。为了能够准确的记录各个线程正在执行的当前字节码指令地址，所以为每个线程都分配了一个PC寄存器，每个线程都独立计算，不会互相影响。
> 相关总结如下：
- 它是一块很小的内存空间，几乎可以忽略不计。也是运行速度最快的存储区域
- 在 JVM 规范中，每个线程都有它自己的程序计数器，是线程私有的，生命周期与线程的生命周期一致
- 任何时间一个线程都只有一个方法在执行，也就是所谓的当前方法。如果当前线程正在执行的是 Java 方法，程序计数器记录的是 JVM 字节码指令地址，如果是执行 native 方法，则是未指定值（undefined）
- 它是程序控制流的指示器，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成
- 字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令
- **它是唯一一个在 JVM 规范中没有规定任何 `OutOfMemoryError` 情况的区域**
## 5.3 二、虚拟机栈
### 5.3.1 概述
> Java 虚拟机栈(Java Virtual Machine Stacks)，早期也叫 Java 栈。每个线程在创建的时候都会创建一个虚拟机栈，其内部保存一个个的栈帧(Stack Frame)，对应着一次次 Java 方法调用，是线程私有的，生命周期和线程一致。
- 作用：主管 Java 程序的运行，它保存方法的局部变量、部分结果，并参与方法的调用和返回。
- 特点：
  - 栈是一种快速有效的分配存储方式，访问速度仅次于程序计数器
  - JVM 直接对虚拟机栈的操作只有两个：每个方法执行，伴随着入栈（进栈/压栈），方法执行结束出栈
  - `栈不存在垃圾回收问题`
- 栈中可能出现的异常：
  - Java 虚拟机规范允许 `Java虚拟机栈的大小是动态的或者是固定不变的`
    - 果采用固定大小的 Java 虚拟机栈，那每个线程的 Java 虚拟机栈容量可以在线程创建的时候独立选定。如果线程请求分配的栈容量超过 Java 虚拟机栈允许的最大容量，Java 虚拟机将会抛出一个 `StackOverflowError` 异常
    - 如果 Java 虚拟机栈可以动态扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的虚拟机栈，那 Java 虚拟机将会抛出一个`OutOfMemoryError`异常
    - HotSpot虚拟机不实现`栈动态扩展`这个功能，而是采用固定大小的栈
- 可以通过参数-Xss来设置线程的最大栈空间，栈的大小直接决定了函数调用的最大可达深度。
- 官方提供的参考工具，可查一些参数和操作：https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html#BGBCIEFC
### 5.3.2 栈的存储单位
栈中存储什么？
- 每个线程都有自己的栈，栈中的数据都是以`栈帧（Stack Frame）的格式存在`
- 在这个线程上`正在执行的每个方法都各自有对应的一个栈帧`
- 栈帧是一个内存区块，是一个数据集，维系着方法执行过程中的各种数据信息
### 5.3.3 栈运行原理
- JVM 直接对 Java 栈的操作只有两个，对栈帧的`压栈`和`出栈`，遵循“先进后出/后进先出”原则
- 在一条活动线程中，一个时间点上，只会有一个活动的栈帧。即只有当前正在执行的方法的栈帧（`栈顶栈帧`）是有效的，这个栈帧被称为`当前栈帧（Current Frame）`，与当前栈帧对应的方法就是`当前方法（Current Method）`，定义这个方法的类就是`当前类（Current Class）`
- 执行引擎运行的所有字节码指令只针对当前栈帧
- 进行操作如果在该方法中调用了其他方法，对应的新的栈帧会被创建出来，放在栈的顶端，称为`新的当前栈帧`
- 不同线程中所包含的栈帧是不允许存在相互引用的，即不可能在一个栈帧中引用另外一个线程的栈帧
- 如果当前方法调用了其他方法，方法返回之际，当前栈帧会传回此方法的执行结果给前一个栈帧，接着，虚拟机会丢弃当前栈帧，使得前一个栈帧重新成为当前栈帧
- Java 方法有两种返回函数的方式，一种是正常的函数返回，使用 return 指令，另一种是抛出异常，不管用哪种方式，都会导致栈帧被弹出

IDEA 在 debug 时候，可以在 debug 窗口看到 Frames 中各种方法的压栈和出栈情况
![虚拟机栈](../assets/images/03-JVM/20.虚拟机栈.jpg)
### 5.3.4 栈帧的内部结构
每个`栈帧`（Stack Frame）中存储着：
- 局部变量表（Local Variables）
- 操作数栈（Operand Stack）(或称为表达式栈)
- 动态链接（Dynamic Linking）：指向运行时常量池的方法引用
- 方法返回地址（Return Address）：方法正常退出或异常退出的地址
- 一些附加信息
![栈帧的内部结构](../assets/images/03-JVM/21.栈帧的内部结构.jpg)
#### 5.3.4.1 局部变量表
- 局部变量表也被称为局部变量数组或者本地变量表
- 是一组变量值存储空间，`主要用于存储方法参数和定义在方法体内的局部变量`，包括编译器可知的各种 Java 虚拟机基本数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference类型，它并不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或其他与此相关的位置）和 returnAddress 类型（指向了一条字节码指令的地址，已被异常表取代）
- 由于局部变量表是建立在线程的栈上，是线程的私有数据，因此不存在数据安全问题
- 局部变量表所需要的容量大小是编译期确定下来的，并保存在方法的 Code 属性的 maximum local variables 数据项中。在方法运行期间是不会改变局部变量表的大小的
- 方法嵌套调用的次数由栈的大小决定。一般来说，栈越大，方法嵌套调用次数越多。对一个函数而言，它的参数和局部变量越多，使得局部变量表膨胀，它的栈帧就越大，以满足方法调用所需传递的信息增大的需求。进而函数调用就会占用更多的栈空间，导致其嵌套调用次数就会减少。
- 局部变量表中的变量只在当前方法调用中有效。在方法执行时，虚拟机通过使用局部变量表完成参数值到参数变量列表的传递过程。当方法调用结束后，随着方法栈帧的销毁，局部变量表也会随之销毁。
- 参数值的存放总是在局部变量数组的 index0 开始，到数组长度 -1 的索引结束
##### 5.3.4.1.1 槽 Slot
- 局部变量表最基本的存储单元是 Slot（变量槽）
- 在局部变量表中，32 位以内的类型只占用一个 Slot(包括returnAddress类型)，64 位的类型（long和double）占用两个连续的 Slot
  - byte、short、char 在存储前被转换为int，boolean也被转换为int，0 表示 false，非 0 表示 true
  - long 和 double 则占据两个 Slot
- JVM 会为局部变量表中的每一个 Slot 都分配一个访问索引，通过这个索引即可成功访问到局部变量表中指定的局部变量值，索引值的范围从 0 开始到局部变量表最大的 Slot 数量
- 当一个实例方法被调用的时候，它的方法参数和方法体内部定义的局部变量将会`按照顺序被复制`到局部变量表中的每一个 Slot 上
- `如果需要访问局部变量表中一个 64bit 的局部变量值时，只需要使用前一个索引即可。`（比如：访问 long 或 double 类型变量，不允许采用任何方式单独访问其中的某一个 Slot）
- 如果当前帧是由构造方法或实例方法创建的，那么该对象引用 this 将会存放在 index 为 0 的 Slot 处，其余的参数按照参数表顺序继续排列（这里就引出一个问题：`静态方法中为什么不可以引用 this，就是因为this 变量不存在于当前方法的局部变量表中`）
  - 非静态方法（实例方法）：这些方法依赖于对象实例调用，因此在栈帧的局部变量表中，第一个槽位（索引0）会自动存放当前对象的引用，即this。这样，方法内部可以通过this访问实例变量或其他实例方法。
  - 静态方法（类方法）：这些方法不依赖于对象实例，而是属于类本身。因此，在静态方法的栈帧中，局部变量表的索引0不会存放this引用。 Instead，局部变量表直接从索引0开始存放方法的参数（如果有的话）
- `栈帧中的局部变量表中的槽位是可以重用的`，如果一个局部变量过了其作用域，那么在其作用域之后申明的新的局部变量就很有可能会复用过期局部变量的槽位，从而达到节省资源的目的。（下图中，this、a、b、c 理论上应该有 4 个变量，c 复用了 b 的槽）
![局部变量表的槽位复用](../assets/images/03-JVM/22.局部变量表的槽位复用.jpg)
- 在栈帧中，与性能调优关系最为密切的就是局部变量表。在方法执行时，虚拟机使用局部变量表完成方法的传递
- `局部变量表中的变量也是重要的垃圾回收根节点，只要被局部变量表中直接或间接引用的对象都不会被回收`
#### 5.3.4.2 操作数栈
- 每个独立的栈帧中除了包含局部变量表之外，还包含一个后进先出（Last-In-First-Out）的操作数栈，也可以称为表达式栈（Expression Stack）
- `操作数栈，在方法执行过程中，根据字节码指令，往操作数栈中写入数据或提取数据，即入栈（push）、出栈`（pop）
- 某些字节码指令将值压入操作数栈，其余的字节码指令将操作数取出栈。使用它们后再把结果压入栈。比如，执行复制、交换、求和等操作
##### 5.3.4.2.1 概述
- `操作数栈，主要用于保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间`
- 操作数栈就是 JVM 执行引擎的一个工作区，当一个方法刚开始执行的时候，一个新的栈帧也会随之被创建出来，此时这个方法的操作数栈是空的
- 每一个操作数栈都会拥有一个明确的栈深度用于存储数值，其所需的最大深度在编译期就定义好了，保存在方法的 Code 属性的 max_stack 数据项中
- 栈中的任何一个元素都可以是任意的 Java 数据类型 
  - 32bit 的类型占用一个栈单位深度
  - 64bit 的类型占用两个栈单位深度
- 操作数栈并非采用访问索引的方式来进行数据访问的，而是只能通过标准的入栈和出栈操作来完成一次数据访问
- `如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中，并更新 PC 寄存器中下一条需要执行的字节码指令 `
- 操作数栈中元素的数据类型必须与字节码指令的序列严格匹配，这由编译器在编译期间进行验证，同时在类加载过程中的类检验阶段的数据流分析阶段要再次验证
- 另外，我们说`Java虚拟机的解释引擎是基于栈的执行引擎`，其中的栈指的就是操作数栈
##### 5.3.4.2.2 栈顶缓存（Top-of-stack-Cashing）
HotSpot 的执行引擎采用的并非是基于寄存器的架构，但这并不代表 HotSpot VM 的实现并没有间接利用到寄存器资源。寄存器是物理 CPU 中的组成部分之一，它同时也是 CPU 中非常重要的高速存储资源。一般来说，寄存器的读/写速度非常迅速，甚至可以比内存的读/写速度快上几十倍不止，不过寄存器资源却非常有限，不同平台下的CPU 寄存器数量是不同和不规律的。寄存器主要用于缓存本地机器指令、数值和下一条需要被执行的指令地址等数据。

基于栈式架构的虚拟机所使用的零地址指令更加紧凑，但完成一项操作的时候必然需要使用更多的入栈和出栈指令，这同时也就意味着将需要更多的指令分派（instruction dispatch）次数和内存读/写次数。由于操作数是存储在内存中的，因此频繁的执行内存读/写操作必然会影响执行速度。为了解决这个问题，HotSpot JVM 设计者们提出了栈顶缓存技术，`将栈顶元素全部缓存在物理 CPU 的寄存器中，以此降低对内存的读/写次数，提升执行引擎的执行效率`
#### 5.3.4.3 动态链接（指向运行时常量池的方法引用）
-` 每一个栈帧内部都包含一个指向运行时常量池中该栈帧所属方法的引用`。包含这个引用的目的就是为了支持当前方法的代码能够实现动态链接(Dynamic Linking)。
- 在 Java 源文件被编译到字节码文件中时，所有的变量和方法引用都作为`符号引用（Symbolic Reference）`保存在 Class 文件的常量池中。比如：`描述一个方法调用了另外的其他方法时，就是通过常量池中指向方法的符号引用来表示的，那么动态链接的作用就是为了将这些符号引用转换为调用方法的直接引用`
##### 5.3.4.3.1 JVM 是如何执行方法调用的
方法调用不同于方法执行，方法调用阶段的唯一任务就是确定被调用方法的版本（即调用哪一个方法），暂时还不涉及方法内部的具体运行过程。Class 文件的编译过程中不包括传统编译器中的连接步骤，一切方法调用在 Class文件里面存储的都是符号引用，而不是方法在实际运行时内存布局中的入口地址（直接引用）。也就是需要在类加载阶段，甚至到运行期才能确定目标方法的直接引用。
> 【这一块内容，除了方法调用，还包括解析、分派（静态分派、动态分派、单分派与多分派），这里先不介绍，后续再挖】
在 JVM 中，将符号引用转换为调用方法的直接引用与方法的绑定机制有关
- `静态链接`：当一个字节码文件被装载进 JVM 内部时，如果被调用的目标方法在编译期可知，且运行期保持不变时。这种情况下将调用方法的符号引用转换为直接引用的过程称之为静态链接
- `动态链接`: 如果被调用的方法在编译期无法被确定下来，也就是说，只能在程序运行期将调用方法的符号引用转换为直接引用，由于这种引用转换过程具备动态性，因此也就被称之为动态链接

对应的方法的绑定机制为：早期绑定（Early Binding）和晚期绑定（Late Binding）。`绑定是一个字段、方法或者类在符号引用被替换为直接引用的过程，这仅仅发生一次。`

- 早期绑定：早期绑定就是指被调用的目标方法如果在编译期可知，且运行期保持不变时，即可将这个方法与所属的类型进行绑定，这样一来，由于明确了被调用的目标方法究竟是哪一个，因此也就可以使用静态链接的方式将符号引用转换为直接引用。
- 晚期绑定：如果被调用的方法在编译器无法被确定下来，只能够在程序运行期根据实际的类型绑定相关的方法，这种绑定方式就被称为晚期绑定。
##### 5.3.4.3.2 虚方法和非虚方法
- 如果方法在编译器就确定了具体的调用版本，这个版本在运行时是不可变的。这样的方法称为非虚方法，比如静态方法、私有方法、final 方法、实例构造器、父类方法都是非虚方法
- 其他方法称为虚方法
##### 5.3.4.3.3 虚方法表
- 在面向对象编程中，会频繁的使用到动态分派，如果每次动态分派都要重新在类的方法元数据中搜索合适的目标有可能会影响到执行效率。为了提高性能，JVM 采用在类的方法区建立一个虚方法表（virtual method table），使用索引表来代替查找。非虚方法不会出现在表中。
- 每个类中都有一个虚方法表，表中存放着各个方法的实际入口。
- 虚方法表会在类加载的连接阶段被创建并开始初始化，类的变量初始值准备完成之后，JVM 会把该类的方法表也初始化完毕。
##### 5.3.4.3.4 总结

1. 核心概念回顾

- **早期绑定（静态绑定）**：在**编译期**就能确定具体调用哪个方法。方法调用与方法的实现（即直接引用）在编译时就已经绑定好了。
- **晚期绑定（动态绑定）**：在**运行期**才能根据对象的实际类型来确定调用哪个方法。方法调用在编译时无法确定具体实现。

---

2. 什么情况下会出现早期绑定？

早期绑定发生在方法**不可能被重写或继承**，因此在编译时就能唯一确定的情况下。主要包括以下几类方法：

1.  **静态方法**
    - **原因**：静态方法属于类本身，与对象实例无关。调用时直接通过类名引用，不存在多态。
    - **示例**：`Math.max(1, 2)`，编译时就知道是调用 `java.lang.Math` 类的 `max` 方法。

2.  **私有方法**
    - **原因**：私有方法只在当前类中可见，不能被继承或重写，因此调用它的唯一可能就是在当前类内部。
    - **示例**：一个类内部的 `private void helper()` 方法。

3.  **final 方法**
    - **原因**：被 `final` 修饰的方法不能被子类重写，所以其实现是固定的。
    - **示例**：`public final void cannotOverride()`

4.  **实例构造器**
    - **原因**：构造器不能被重写（虽然可以重载），调用哪个构造器在编译时通过 `new` 关键字后的类名就能确定。
    - **示例**：`new MyObject()`

5.  **通过 super 关键字调用的父类方法**
    - **原因**：`super.method()` 明确指定了要调用父类的实现，绕过了子类的重写，因此也是编译期可知的。
    - **示例**：在子类方法中调用 `super.toString()`。

**对于早期绑定的方法，JVM 会使用静态链接，将符号引用直接转换为直接引用。**

---

3. 什么情况下会出现晚期绑定？

晚期绑定发生在方法**可能被子类重写**，因此在编译时无法知道最终会调用哪个类的实现，必须等到运行时根据对象的实际类型来决定。主要包括：

3.1  **普通（非final）的实例方法（虚方法）**

    - **原因**：这是面向对象多态性的核心体现。一个父类引用可以指向子类对象，而子类可能重写了父类的方法。编译时只能知道引用的类型（静态类型），但运行时需要根据对象的实际类型（动态类型）来调用正确的方法。
    - **示例**：

```java
class Animal {
    public void speak() { // 这是一个虚方法
        System.out.println("Animal speaks");
    }
}
class Dog extends Animal {
    @Override
    public void speak() { // 重写了父类方法
        System.out.println("Woof!");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // 编译时类型是Animal，运行时类型是Dog
        myAnimal.speak(); // 晚期绑定：运行时才能确定调用的是Dog的speak方法
    }
}
```

**对于晚期绑定的方法，JVM 会使用动态链接，在运行时将符号引用转换为直接引用。** 这个过程通常涉及虚方法表（vtable）的查找。

4. 总结表格

| 绑定类型 | 发生时机 | 典型方法 | JVM 链接方式 |
| :--- | :--- | :--- | :--- |
| **早期绑定** | 编译期 | 静态方法、私有方法、final方法、构造器、super调用 | 静态链接 |
| **晚期绑定** | 运行期 | 可被重写的普通实例方法（虚方法） | 动态链接 |

简单来说，**判断标准就是“这个方法在运行时会不会因为对象的不同而表现出不同的行为（多态）”。** 如果不会（如静态方法、final方法），就是早期绑定；如果会（如普通实例方法），就是晚期绑定。

#### 5.3.4.4 方法返回地址（return address）
用来存放调用该方法的 PC 寄存器的值(PC 寄存器用来存储指向下一条指令的地址)。

一个方法的结束，有两种方式
- 正常执行完成
- 出现未处理的异常，非正常退出

无论通过哪种方式退出，在方法退出后都返回到该方法被调用的位置。
- 方法正常退出时，调用者的 PC 计数器的值作为返回地址，即调用该方法的指令的下一条指令的地址。
- 而通过异常退出的，返回地址是要通过异常表来确定的，栈帧中一般不会保存这部分信息。
当一个方法开始执行后，只有两种方式可以退出这个方法：

1. 执行引擎遇到任意一个方法返回的字节码指令，会有返回值传递给上层的方法调用者，简称**正常完成出口**

一个方法的正常调用完成之后究竟需要使用哪一个返回指令还需要根据方法返回值的实际数据类型而定

在字节码指令中，返回指令包含 ireturn(当返回值是 boolean、byte、char、short 和 int 类型时使用)、lreturn、freturn、dreturn 以及 areturn，另外还有一个 return 指令供声明为 void 的方法、实例初始化方法、类和接口的初始化方法使用。

2. 在方法执行的过程中遇到了异常，并且这个异常没有在方法内进行处理，也就是只要在`本方法的异常表中没有搜索到匹配的异常处理器`，就会导致方法退出。简称**异常完成出口**

方法执行过程中抛出异常时的异常处理，存储在一个异常处理表，方便在发生异常的时候找到处理异常的代码。本质上，`方法的退出就是当前栈帧出栈的过程`。此时，需要恢复上层方法的局部变量表、操作数栈、将返回值压入调用者栈帧的操作数栈、设置PC寄存器值等，让调用者方法继续执行下去。

正常完成出口和异常完成出口的区别在于：**通过异常完成出口退出的不会给他的上层调用者产生任何的返回值**
#### 5.3.4.5  附加信息
栈帧中还允许携带与 Java 虚拟机实现相关的一些附加信息。例如，对程序调试提供支持的信息，但这些信息取决于具体的虚拟机实现。
##  5.4 三、本地方法栈
### 5.4.1 本地方法接口(JNI)
简单的讲，`一个 Native Method 就是一个 Java 调用非 Java 代码的接口`。我们知道的 Unsafe 类就有很多本地方法。
> 为什么要使用本地方法（Native Method）?

Java 使用起来非常方便，然而有些层次的任务用 Java 实现起来也不容易，或者我们对程序的效率很在意时，问题就来了
- 与 Java 环境外交互：有时 Java 应用需要与 Java 外面的环境交互，这就是本地方法存在的原因。
- 与操作系统交互：JVM 支持 Java 语言本身和运行时库，但是有时仍需要依赖一些底层系统的支持。通过本地方法，我们可以实现用 Java 与实现了 jre 的底层系统交互， JVM 的一些部分就是 C 语言写的。
- Sun's Java：Sun的解释器就是C实现的，这使得它能像一些普通的C一样与外部交互。jre大部分都是用 Java 实现的，它也通过一些本地方法与外界交互。比如，类 java.lang.Thread 的 setPriority() 的方法是用Java 实现的，但它实现调用的是该类的本地方法 setPrioruty()，该方法是C实现的，并被植入 JVM 内部。
### 5.4.2 本地方法栈（Native Method Stack）
- Java 虚拟机栈用于管理 Java 方法的调用，而本地方法栈用于管理本地方法的调用
- 本地方法栈也是线程私有的
- 允许线程固定或者可动态扩展的内存大小
  - 如果线程请求分配的栈容量超过本地方法栈允许的最大容量，Java 虚拟机将会抛出一个 StackOverflowError 异常
  - 如果本地方法栈可以动态扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的本地方法栈，那么 Java虚拟机将会抛出一个OutofMemoryError异常
- 本地方法是使用 C 语言实现的
- 它的具体做法是 Native Method Stack 中登记 native 方法，在 Execution Engine 执行时加载本地方法库当某个线程调用一个本地方法时，它就进入了一个全新的并且不再受虚拟机限制的世界。它和虚拟机拥有同样的权限。
- 本地方法可以通过本地方法接口来访问虚拟机内部的运行时数据区，它甚至可以直接使用本地处理器中的寄存器，直接从本地内存的堆中分配任意数量的内存
- 并不是所有 JVM 都支持本地方法。因为 Java 虚拟机规范并没有明确要求本地方法栈的使用语言、具体实现方式、数据结构等。如果 JVM 产品不打算支持 native 方法，也可以无需实现本地方法栈
- `在 Hotspot JVM 中，直接将本地方法栈和虚拟机栈合二为一`

> **栈是运行时的单位，而堆是存储的单位。**
> 栈解决程序的运行问题，即程序如何执行，或者说如何处理数据。堆解决的是数据存储的问题，即数据怎么放、放在哪。

## 5.5 四、堆内存
### 5.5.1 内存划分
对于大多数应用，Java 堆是 Java 虚拟机管理的内存中最大的一块，被所有线程共享。此内存区域的唯一目的就是存放对象实例，`几乎所有的对象实例以及数据都在堆这里分配内存`。
为了进行高效的垃圾回收，虚拟机把堆内存逻辑上划分成三块区域（`分代的唯一理由就是优化 GC 性能`）：
- 新生带（年轻代）：新对象和没达到一定年龄的对象都在新生代
- 老年代（养老区）：被长时间使用的对象，老年代的内存空间应该要比年轻代更大
- 元空间（JDK1.8 之前叫永久代）：主要存储的是类的元数据，如`类型信息、方法信息、字段信息等`等，JDK1.8 之前是占用 JVM 内存（堆内存中），`JDK1.8 之后直接使用物理内存（本地内存）`
![堆内存划分](../assets/images/03-JVM/24.堆内存划分.jpg)
Java 虚拟机规范规定，Java 堆可以是处于物理上不连续的内存空间中，只要逻辑上是连续的即可，像磁盘空间一样。实现时，既可以是固定大小，也可以是可扩展的，主流虚拟机都是可扩展的（通过 -Xmx 和 -Xms 控制），如果堆中没有完成实例分配，并且堆无法再扩展时，就会抛出 OutOfMemoryError 异常。
- JVM参数调整内存分区
  - 新生代（年轻代）参数设置
    ```bash
    # 设置新生代初始大小
    -XX:NewSize=256m

    # 设置新生代最大大小
    -XX:MaxNewSize=512m

    # 简写方式：设置新生代大小（同时设置初始和最大）
    -Xmn512m
    # 设置新生代中Eden区与Survivor区的比例（默认8:1:1）
    -XX:SurvivorRatio=8

    # 设置老年代与新生代的比例（默认约2:1）
    -XX:NewRatio=2
    ```
  - 老年代参数设置
    ```bash
    # 设置堆内存初始大小
    -Xms2g

    # 设置堆内存最大大小
    -Xmx4g

    # 设置老年代大小（通过总堆减去新生代计算得出）
    # 如果 -Xmx=4g, -Xmn=1g，则老年代为3g

    # 设置大对象直接进入老年代的阈值
    -XX:PretenureSizeThreshold=1m

    # 设置晋升到老年代的对象年龄阈值（默认15次：对象在新生代中经历Minor GC的次数）
    -XX:MaxTenuringThreshold=15
    ```
  - 元空间参数设置（JDK 8+）
    ```bash
    # 设置元空间初始大小（JDK 8+）
    -XX:MetaspaceSize=256m

    # 设置元空间最大大小（默认无限制，使用系统内存）
    -XX:MaxMetaspaceSize=512m

    # 设置元空间触发Full GC的阈值
    -XX:MetaspaceSize=128m  # 当使用量达到此值时触发GC

    # JDK 7及之前设置永久代大小
    -XX:PermSize=128m
    -XX:MaxPermSize=256m
    ```
#### 5.5.1.1 年轻代 (Young Generation)
年轻代是所有新对象创建的地方。当填充年轻代时，执行垃圾收集。这种垃圾收集称为 `Minor GC`。年轻一代被分为三个部分——`伊甸园（Eden Memory）`和`两个幸存区（Survivor Memory，被称为from/to或s0/s1）`，默认比例是8:1:1
- 大多数新创建的对象都位于 Eden 内存空间中
- 当 Eden 空间被对象填充时，执行Minor GC，并将所有幸存者对象移动到一个幸存者空间中
- Minor GC 检查幸存者对象，并将它们移动到另一个幸存者空间。**所以每次，一个幸存者空间总是空的**
- 经过多次 GC 循环后存活下来的对象被移动到老年代。通常，这是通过设置年轻一代对象的年龄阈值来实现的，然后他们才有资格提升到老一代
#### 5.5.1.2 老年代(Old Generation)
旧的一代内存包含那些经过许多轮小型 GC 后仍然存活的对象。通常，垃圾收集是在老年代内存满时执行的。老年代垃圾收集称为 `主GC（Major GC）`，通常需要更长的时间。`大对象直接进入老年代（大对象是指需要大量连续内存空间的对象）。这样做的目的是避免在 Eden 区和两个Survivor 区之间发生大量的内存拷贝`
![堆内存结构对比](../assets/images/03-JVM/25.堆内存结构对比.jpg)
#### 5.5.1.3 元空间
不管是 JDK8 之前的永久代，还是 JDK8 及以后的元空间，都可以看作是 Java 虚拟机规范中方法区的实现。虽然 Java 虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫 Non-Heap（非堆），目的应该是与 Java 堆区分开。所以元空间放在后边的方法区再说。
### 5.5.2 设置堆内存大小和 OOM
Java 堆用于存储 Java 对象实例，那么堆的大小在 JVM 启动的时候就确定了，我们可以通过 -Xmx 和 -Xms 来设定
- -Xms 用来表示堆的起始内存，等价于 -XX:InitialHeapSize
- -Xmx 用来表示堆的最大内存，等价于 -XX:MaxHeapSize

如果堆的内存大小超过 -Xmx 设定的最大内存， 就会抛出 OutOfMemoryError 异常。

我们通常会将 -Xmx 和 -Xms 两个参数配置为相同的值，其目的是为了能够在垃圾回收机制清理完堆区后不再需要重新分隔计算堆的大小，从而提高性能
- 默认情况下，初始堆内存大小为：电脑内存大小/64
- 默认情况下，最大堆内存大小为：电脑内存大小/4

可以通过代码获取到我们的设置值，当然也可以模拟 OOM：
```java
public static void main(String[] args) {

  //返回 JVM 堆大小
  long initalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
  //返回 JVM 堆的最大内存
  long maxMemory = Runtime.getRuntime().maxMemory() / 1024 /1024;

  System.out.println("-Xms : "+initalMemory + "M");
  System.out.println("-Xmx : "+maxMemory + "M");

  System.out.println("系统内存大小：" + initalMemory * 64 / 1024 + "G");
  System.out.println("系统内存大小：" + maxMemory * 4 / 1024 + "G");
}
```
### 5.5.3 查看 JVM 堆内存分配
- 在默认不配置 JVM 堆内存大小的情况下，JVM 根据默认值来配置当前内存大小
- 默认情况下新生代和老年代的比例是 1:2，可以通过 –XX:NewRatio 来配置
  - 新生代中的 Eden:From Survivor:To Survivor 的比例是 8:1:1，可以通过 -XX:SurvivorRatio 来配置
- 若在 JDK 7 中开启了 -XX:+UseAdaptiveSizePolicy，JVM 会动态调整 JVM 堆中各个区域的大小以及进入老年代的年龄
  - 此时 –XX:NewRatio 和 -XX:SurvivorRatio 将会失效，而 JDK 8 是默认开启-XX:+UseAdaptiveSizePolicy
  - 在 JDK 8中，不要随意关闭-XX:+UseAdaptiveSizePolicy，除非对堆内存的划分有明确的规划
- 每次 GC 后都会重新计算 Eden、From Survivor、To Survivor 的大小
- 计算依据是GC过程中统计的`GC时间、吞吐量、内存占用量`
```sh
java -XX:+PrintFlagsFinal -version | grep HeapSize
    uintx ErgoHeapSizeLimit                         = 0                                   {product}
    uintx HeapSizePerGCThread                       = 87241520                            {product}
    uintx InitialHeapSize                          := 134217728                           {product}
    uintx LargePageHeapSizeThreshold                = 134217728                           {product}
    uintx MaxHeapSize                              := 2147483648                          {product}
java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
```
```sh
$ jmap -heap 进程号
```
### 5.5.4 对象在堆中的生命周期
- 在 JVM 内存模型的堆中，堆被划分为新生代和老年代 
  - 新生代又被进一步划分为 `Eden区` 和 `Survivor区`，Survivor 区由 From Survivor 和 To Survivor 组成
- 当创建一个对象时，对象会被优先分配到新生代的 Eden 区 
  - 此时 JVM 会给对象定义一个`对象年轻计数器（-XX:MaxTenuringThreshold）`
- 当 Eden 空间不足时，JVM 将执行新生代的垃圾回收（Minor GC）
  -  JVM 会把存活的对象转移到 Survivor 中，并且对象年龄 +1
  -  对象在 Survivor 中同样也会经历 Minor GC，每经历一次 Minor GC，对象年龄都会+1
-  如果分配的对象超过了-XX:PetenureSizeThreshold，对象会`直接被分配到老年代`
### 5.5.5 对象的分配过程
为对象分配内存是一件非常严谨和复杂的任务，JVM 的设计者们不仅需要考虑内存如何分配、在哪里分配等问题，并且由于内存分配算法和内存回收算法密切相关，所以还需要考虑 GC 执行完内存回收后是否会在内存空间中产生内存碎片。
- new 的对象先放在伊甸园区，此区有大小限制
- 当伊甸园的空间填满时，程序又需要创建对象，JVM 的垃圾回收器将对伊甸园区进行垃圾回收（Minor GC），将伊甸园区中的不再被其他对象所引用的对象进行销毁。再加载新的对象放到伊甸园区
- 然后将伊甸园中的剩余对象移动到幸存者 0 区
- 如果再次触发垃圾回收，此时上次幸存下来的放到幸存者 0 区，如果没有回收，就会放到幸存者 1 区
- 如果再次经历垃圾回收，此时会重新放回幸存者 0 区，接着再去幸存者 1 区
- 什么时候才会去养老区呢？ 默认是 15 次回收标记
- 在养老区，相对悠闲。当养老区内存不足时，再次触发 Major GC，进行养老区的内存清理
- 若养老区执行了 Major GC 之后发现依然无法进行对象的保存，就会产生 OOM 异常
### 5.5.6 GC 垃圾回收简介
Minor GC、Major GC、Full GC(一次清理整个堆内存（年轻代 + 老年代 + 元空间） 的垃圾回收事件)

JVM 在进行 GC 时，并非每次都对堆内存（新生代、老年代；方法区）区域一起回收的，大部分时候回收的都是指新生代。

针对 HotSpot VM 的实现，它里面的 GC 按照回收区域又分为两大类：`部分收集（Partial GC），整堆收集（Full GC）`
- 部分收集：不是完整收集整个 Java 堆的垃圾收集。其中又分为： 
  - 新生代收集（Minor GC/Young GC）：只是新生代的垃圾收集
  - 老年代收集（Major GC/Old GC）：只是老年代的垃圾收集 
    - 目前，只有 CMS GC 会有单独收集老年代的行为
    - 很多时候 Major GC 会和 Full GC 混合使用，需要具体分辨是老年代回收还是整堆回收
  - 混合收集（Mixed GC）：收集整个新生代以及部分老年代的垃圾收集
    -  目前只有 G1 GC 会有这种行为
-  整堆收集（Full GC）：收集整个 Java 堆和方法区的垃圾
### 5.5.7  TLAB
#### 5.5.7.1 什么是 TLAB（Thread Local Allocation Buffer:线程本地分配缓冲区）
- 从内存模型而不是垃圾回收的角度，对 Eden 区域继续进行划分，JVM 为每个线程分配了一个私有缓存区域，它包含在 Eden 空间内
- 多线程同时分配内存时，使用 TLAB 可以避免一系列的非线程安全问题，同时还能提升内存分配的吞吐量，因此我们可以将这种内存分配方式称为快速分配策略
- OpenJDK 衍生出来的 JVM 大都提供了 TLAB 设计
#### 5.5.7.2 为什么要有 TLAB ?
- 堆区是线程共享的，任何线程都可以访问到堆区中的共享数据
- 由于对象实例的创建在 JVM 中非常频繁，因此在并发环境下从堆区中划分内存空间是线程不安全的
- 为避免多个线程操作同一地址，需要使用加锁等机制，进而影响分配速度

`**尽管不是所有的对象实例都能够在 TLAB 中成功分配内存，但 JVM 确实是将 TLAB 作为内存分配的首选**`。

在程序中，可以通过 -XX:UseTLAB 设置是否开启 TLAB 空间。

默认情况下，TLAB 空间的内存非常小，仅占有整个 Eden 空间的 1%，我们可以通过 -XX:TLABWasteTargetPercent 设置 TLAB 空间所占用 Eden 空间的百分比大小。

一旦对象在 TLAB 空间分配内存失败时，JVM 就会尝试着通过使用加锁机制确保数据操作的原子性，从而直接在 Eden 空间中分配内存。

#### 5.5.7.3 TLAB 的工作原理

1.  **申请**：在应用启动时，JVM 会为每个线程在 Eden 区初始化一个 TLAB。当线程的 TLAB 用尽时，它会向 JVM 申请一个新的 TLAB。
2.  **分配**：线程创建新对象时，优先在自己的 TLAB 中分配。分配过程仅仅是移动 TLAB 内部的偏移量指针，无需同步。
3.  **用尽与回收**：当 TLAB 剩余空间不足以分配新对象时，会发生两种情况：
    -   **如果对象不大**：线程会放弃当前的 TLAB，申请一个新的 TLAB 来分配该对象。旧的 TLAB 就被“退回”给 Eden 区，其剩余空间会被其他线程在申请 TLAB 时复用（但不会被该线程自己用来分配小对象）
    -   **Refill（重新填充）**：当 TLAB 用尽时，申请新 TLAB 的过程叫 “refill”。这个操作本身是有成本的，所以 JVM 会尽量优化。。
    -   **如果对象非常大**：JVM 会尝试直接在 TLAB 之外（仍在 Eden 区）进行分配，这种分配可能需要加锁，但大对象分配不频繁，所以影响较小。
4.  **GC 回收**：当发生 Minor GC 时，Eden 区（包括所有的 TLAB）会被整体回收。之后，存活下来的线程会获得新的 TLAB。
### 5.5.8 堆是分配对象存储的唯一选择吗
> 随着 JIT 编译期的发展和逃逸分析技术的逐渐成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化，所有的对象都分配到堆上也渐渐变得不那么“绝对”了。 ——《深入理解 Java 虚拟机》
#### 5.5.8.1 逃逸分析
`逃逸分析(Escape Analysis)是目前 Java 虚拟机中比较前沿的优化技术。这是一种可以有效减少 Java 程序中同步负载和内存堆分配压力的跨函数全局数据流分析算法`。通过逃逸分析，Java Hotspot 编译器能够分析出一个新的对象的引用的使用范围从而决定是否要将这个对象分配到堆上。

逃逸分析的基本行为就是分析对象动态作用域：

- 当一个对象在方法中被定义后，对象只在方法内部使用，则认为没有发生逃逸。
- 当一个对象在方法中被定义后，它被外部方法所引用，则认为发生逃逸。例如作为调用参数传递到其他地方中，称为方法逃逸。
```java
public static StringBuffer craeteStringBuffer(String s1, String s2) {
   StringBuffer sb = new StringBuffer();
   sb.append(s1);
   sb.append(s2);
   return sb;
}
```
`StringBuffer sb`是一个方法内部变量，上述代码中直接将sb返回，这样这个 StringBuffer 有可能被其他方法所改变，这样它的作用域就不只是在方法内部，虽然它是一个局部变量，但是其逃逸到了方法外部。甚至还有可能被外部线程访问到，譬如赋值给类变量或可以在其他线程中访问的实例变量，称为线程逃逸。

上述代码如果想要 StringBuffer sb不逃出方法，可以这样写：
```java
public static String createStringBuffer(String s1, String s2) {
   StringBuffer sb = new StringBuffer();
   sb.append(s1);
   sb.append(s2);
   return sb.toString();
}
```
不直接返回 StringBuffer，那么 StringBuffer 将不会逃逸出方法。

参数设置：
- 在 JDK 6u23 版本之后，HotSpot 中默认就已经开启了逃逸分析
- 如果使用较早版本，可以通过-XX"+DoEscapeAnalysis显式开启

开发中使用局部变量，就不要在方法外定义。

使用逃逸分析，编译器可以对代码做优化：
- `栈上分配`：将堆分配转化为栈分配。如果一个对象在子程序中被分配，要使指向该对象的指针永远不会逃逸，对象可能是栈分配的候选，而不是堆分配
- `同步省略`：如果一个对象被发现只能从一个线程被访问到，那么对于这个对象的操作可以不考虑同步
- `分离对象或标量替换`：有的对象可能不需要作为一个连续的内存结构存在也可以被访问到，那么对象的部分（或全部）可以不存储在内存，而存储在 CPU 寄存器

JIT 编译器在编译期间根据逃逸分析的结果，发现如果一个对象并没有逃逸出方法的话，就可能被优化成栈上分配。分配完成后，继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收。这样就无需进行垃圾回收了。

常见栈上分配的场景：成员变量赋值、方法返回值、实例引用传递

**逃逸分析的约束：**
关于逃逸分析的论文在1999年就已经发表了，但直到JDK 1.6才有实现，而且这项技术到如今也并不是十分成熟的。

- **其根本原因就是无法保证逃逸分析的性能消耗一定能高于他的消耗。虽然经过逃逸分析可以做标量替换、栈上分配、和锁消除。但是逃逸分析自身也是需要进行一系列复杂的分析的，这其实也是一个相对耗时的过程。**
- 一个极端的例子，就是经过逃逸分析之后，发现没有一个对象是不逃逸的。那这个逃逸分析的过程就白白浪费掉了。
- 虽然这项技术并不十分成熟，但是他也是即时编译器优化技术中一个十分重要的手段。


##### 5.5.8.1.1. 栈上分配（Stack Allocation）
- 我们通过 JVM 内存分配可以知道 JAVA 中的对象都是在堆上进行分配，当对象没有被引用的时候，需要依靠 GC 进行回收内存，如果对象数量较多的时候，会给 GC 带来较大压力，也间接影响了应用的性能。为了减少临时对象在堆内分配的数量，JVM 通过逃逸分析确定该对象不会被外部访问。那就通过标量替换将该对象分解在栈上分配内存，这样该对象所占用的内存空间就可以随栈帧出栈而销毁，就减轻了垃圾回收的压力。

**优化原理**：如果对象仅在方法内部使用，没有逃逸到方法外部（例如，没有被其他方法引用或返回），那么 JVM 可能会将该对象分配在栈上（而不是堆上）。栈上分配的对象随方法调用结束而自动销毁，无需垃圾回收，从而提高性能。

**示例**：
假设我们有一个方法，内部创建了一个临时对象，只用于局部计算：
```java
public class StackAllocationExample {
    public int calculate() {
        // 创建一个局部对象，它没有逃逸出方法
        Point point = new Point(10, 20);
        return point.x + point.y; // 仅在此方法内使用
    }
    
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
```
**优化效果**：
- 如果没有逃逸分析，`Point` 对象会在堆上分配。
- 经过逃逸分析后，编译器发现 `point` 对象没有逃逸，因此可能将 `point` 的字段（`x` 和 `y`）直接存储在栈上，或者完全省略对象分配，直接使用两个整型变量。这减少了堆内存分配和 GC 压力。

---

##### 5.5.8.1.2. 同步省略（Synchronization Elimination）[锁消除]
- 线程同步的代价是相当高的，同步的后果是降低并发性和性能
- 在动态编译同步块的时候，JIT 编译器可以借助逃逸分析来判断同步块所使用的锁对象是否能够被一个线程访问而没有被发布到其他线程。如果没有，那么 JIT 编译器在编译这个同步块的时候就会取消对这个代码的同步。这样就能大大提高并发性和性能。这个取消同步的过程就叫做**同步省略，也叫锁消除**。

**优化原理**：如果对象被同步锁（如 `synchronized`）保护，但逃逸分析发现该对象只能被一个线程访问（即没有逃逸到其他线程），那么 JVM 会移除同步操作，因为锁是多余的。



**示例**：
假设我们有一个方法，使用 `synchronized` 块操作一个局部对象：
```java
public class SyncEliminationExample {
    public void doSomething() {
        // 创建一个局部对象，它没有逃逸到其他线程
        Object lock = new Object();
        synchronized(lock) { // 这个同步块可能被优化掉
            System.out.println("操作锁对象");
        }
    }
}
```
**优化效果**：
- 逃逸分析确定 `lock` 对象只在当前线程的 `doSomething` 方法内使用，不会被其他线程共享。
- 因此，编译器会省略 `synchronized` 块，直接执行代码，避免了锁获取和释放的开销。这提升了性能，尤其是在高并发场景下。
```java
public void doSomething() {
  System.out.println("操作锁对象");
}
```
---

##### 5.5.8.1.3. 分离对象或标量替换（Scalar Replacement）
- 标量（Scalar）是指一个无法再分解成更小的数据的数据。Java 中的原始数据类型就是标量。
- 相对的，那些的还可以分解的数据叫做聚合量（Aggregate），Java 中的对象就是聚合量，因为其还可以分解成其他聚合量和标量。
- 在 JIT 阶段，通过逃逸分析确定该对象不会被外部访问，并且对象可以被进一步分解时，JVM 不会创建该对象，而会将该对象成员变量分解若干个被这个方法使用的成员变量所代替。这些代替的成员变量在栈帧或寄存器上分配空间。这个过程就是标量替换。

**优化原理**：如果对象不需要作为一个连续的内存结构存在（即对象被分解后不影响逻辑），编译器可能会将对象的字段替换为多个独立的局部变量（标量，如 int、float），这些变量可以存储在栈或 CPU 寄存器中。

**示例**：
假设我们有一个方法，使用一个对象来存储临时数据：
```java
public class ScalarReplacementExample {
    public int compute() {
        // 创建一个对象，用于存储两个值
        Rectangle rect = new Rectangle(5, 10);
        return rect.width * rect.height; // 仅使用对象的字段
    }
    
    static class Rectangle {
        int width, height;
        Rectangle(int w, int h) {
            this.width = w;
            this.height = h;
        }
    }
}
```
**优化效果**：
- 逃逸分析发现 `rect` 对象没有逃逸，且仅用于计算。
- 编译器会进行标量替换：将 `rect.width` 和 `rect.height` 替换为两个局部变量 `int width = 5` 和 `int height = 10`，然后直接计算 `width * height`。
- 这样，`Rectangle` 对象本身不会被分配内存，而是被分解为基本类型，减少了内存占用和访问开销。
```java
 public int compute() {
        // 创建一个对象，用于存储两个值
       int x = 5;
       int y = 10
        return x * y; // 仅使用对象的字段
    }
```
## 5.6 五、方法区
- 方法区（Method Area）与 Java 堆一样，是所有线程共享的内存区域。
- 虽然 Java 虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫 Non-Heap（非堆），目的应该是与 Java 堆区分开。
- 运行时常量池（Runtime Constant Pool）是方法区的一部分。Class 文件中除了有类的版本/字段/方法/接口等描述信息外，还有一项信息是常量池（Constant Pool Table），用于存放编译期生成的各种字面量和符号引用，这部分内容将类在加载后进入方法区的运行时常量池中存放。运行期间也可能将新的常量放入池中，这种特性被开发人员利用得比较多的是`String.intern()`方法。受方法区内存的限制，当常量池无法再申请到内存时会抛出 OutOfMemoryError 异常。
- 方法区的大小和堆空间一样，可以选择固定大小也可选择可扩展，方法区的大小决定了系统可以放多少个类，如果系统类太多，导致方法区溢出，虚拟机同样会抛出内存溢出错误
- JVM 关闭后方法区即被释放

### 5.6.1 概念上的解惑
你是否也有看不同的参考资料，有的内存结构图有方法区，有的又是永久代，元数据区，一脸懵逼的时候？

- 方法区（method area）只是 JVM 规范中定义的一个概念，用于存储类信息、常量池、静态变量、JIT编译后的代码等数据，并没有规定如何去实现它，不同的厂商有不同的实现。而`永久代（PermGen）`是 Hotspot 虚拟机特有的概念， Java8 的时候又被`元空间`取代了，**永久代和元空间都可以理解为方法区的落地实现。**
- 永久代物理是堆的一部分，和新生代，老年代地址是连续的（受垃圾回收器管理），而元空间存在于本地内存（我们常说的堆外内存，不受垃圾回收器管理），这样就不受 JVM 限制了，也比较难发生OOM（都会有溢出异常）
- Java7 中我们通过`-XX:PermSize` 和 `-xx:MaxPermSize`来设置永久代参数，Java8 之后，随着永久代的取消，这些参数也就随之失效了，改为通过`-XX:MetaspaceSize` 和` -XX:MaxMetaspaceSize `用来设置元空间参数
- 存储内容不同，元空间存储类的元信息，静态变量和常量池等并入堆中。相当于永久代的数据被分到了堆和元空间中
- 如果方法区域中的内存不能用于满足分配请求，则 Java 虚拟机抛出 OutOfMemoryError
- JVM 规范说方法区在逻辑上是堆的一部分，但目前实际上是与 Java 堆分开的（Non-Heap）

所以对于方法区，Java8 之后的变化：
- 移除了永久代（PermGen），替换为元空间（Metaspace）；
- 永久代中的 class metadata 转移到了 native memory（本地内存，而不是虚拟机）；
- 永久代中的 interned Strings 和 class static variables 转移到了 Java heap；
- 永久代参数 （PermSize MaxPermSize） -> 元空间参数（MetaspaceSize MaxMetaspaceSize）

| 数据内容 | JDK 7（永久代） | JDK 8+（元空间 + 堆） |
| :--- | :--- | :--- |
| **类的元信息**<br>（类名、方法、字段、注解等） | **永久代** | **元空间**（本地内存） |
| **运行时常量池**<br>（字面量、符号引用等） | **永久代** | **堆**（Java Heap） |
| **字符串常量池**<br>（String Table） | **永久代** | **堆**（Java Heap） |
| **静态变量**<br>（static variables） | **永久代** | **堆**（Java Heap） |

- 栈、堆、方法区的交互关系
![堆-栈-方法区的区分](../assets/images/03-JVM/17.堆-栈-方法区的区分.png)
### 5.6.2 设置方法区内存的大小
JDK8 及以后：
- 元数据区大小可以使用参数 -XX:MetaspaceSize 和 -XX:MaxMetaspaceSize 指定，替代上述原有的两个参数
- 默认值依赖于平台。Windows 下，-XX:MetaspaceSize 是 21M，-XX:MaxMetaspacaSize 的值是 -1，即没有限制
- 与永久代不同，如果不指定大小，默认情况下，虚拟机会耗尽所有的可用系统内存。如果元数据发生溢出，虚拟机一样会抛出异常 OutOfMemoryError:Metaspace
- -XX:MetaspaceSize ：设置初始的元空间大小。对于一个 64 位的服务器端 JVM 来说，其默认的 -XX:MetaspaceSize 的值为20.75MB，这就是初始的高水位线，一旦触及这个水位线，Full GC 将会被触发并卸载没用的类（即这些类对应的类加载器不再存活），然后这个高水位线将会重置，新的高水位线的值取决于 GC 后释放了多少元空间。如果释放的空间不足，那么在不超过 MaxMetaspaceSize时，适当提高该值。如果释放空间过多，则适当降低该值
- 如果初始化的高水位线设置过低，上述高水位线调整情况会发生很多次，通过垃圾回收的日志可观察到 Full GC 多次调用。为了避免频繁 GC，建议将 -XX:MetaspaceSize 设置为一个相对较高的值。
### 5.6.3 方法区内部结构
`方法区用于存储已被虚拟机加载的类型信息、常量、静态变量、即时编译器编译后的代码缓存等。`
#### 5.6.3.1 类型信息
对每个加载的类型（类 class、接口 interface、枚举 enum、注解 annotation），JVM 必须在方法区中存储以下类型信息
- 这个类型的完整有效名称（全名=包名.类名）
- 这个类型直接父类的完整有效名（对于 interface或是 java.lang.Object，都没有父类）
- 这个类型的修饰符（public，abstract，final 的某个子集）
- 这个类型直接接口的一个有序列表
#### 5.6.3.2 域（Field）信息
- JVM 必须在方法区中保存类型的所有域的相关信息以及域的声明顺序
- 域的相关信息包括：域名称、域类型、域修饰符（public、private、protected、static、final、volatile、transient 的某个子集）
#### 5.6.3.3 方法（Method）信息
JVM 必须保存所有方法的
- 方法名称
- 方法的返回类型
- 方法参数的数量和类型
- 方法的修饰符（public，private，protected，static，final，synchronized，native，abstract 的一个子集）
- 方法的字符码（bytecodes）、操作数栈、局部变量表及大小（abstract 和 native 方法除外）
- 异常表（abstract 和 native 方法除外）
  -  每个异常处理的开始位置、结束位置、代码处理在程序计数器中的偏移地址、被捕获的异常类的常量池索引
### 5.6.4 运行时常量池
运行时常量池（Runtime Constant Pool）是方法区的一部分，理解运行时常量池的话，我们先来说说字节码文件（Class 文件）中的常量池（常量池表）
#### 5.6.4.1 常量池
一个有效的字节码文件中除了包含类的版本信息、字段、方法以及接口等描述信息外，还包含一项信息那就是常量池表（Constant Pool Table），包含各种字面量和对类型、域和方法的符号引用。
#### 5.6.4.2 为什么需要常量池？
一个 Java 源文件中的类、接口，编译后产生一个字节码文件。而 Java 中的字节码需要数据支持，通常这种数据会很大以至于不能直接存到字节码里，换另一种方式，可以存到常量池，这个字节码包含了指向常量池的引用。在动态链接的时候用到的就是运行时常量池。
如下，我们通过 jclasslib 查看一个只有 Main 方法的简单类，字节码中的 #2 指向的就是 Constant Pool
![常量池](../assets/images/03-JVM/26.常量池.jpg)
常量池可以看作是一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等类型。
#### 5.6.4.3 运行时常量池
- 在加载类和结构到虚拟机后，就会创建对应的运行时常量池
- 常量池表（Constant Pool Table）是 Class 文件的一部分，用于存储编译期生成的各种字面量和符号引用，**这部分内容将在类加载后存放到方法区的运行时常量池中**
- JVM 为每个已加载的类型（类或接口）都维护一个常量池。池中的数据项像数组项一样，是通过索引访问的
- 运行时常量池中包含各种不同的常量，包括编译器就已经明确的数值字面量，也包括到运行期解析后才能够获得的方法或字段引用。此时不再是常量池中的符号地址了，这里换为真实地址 
  - 运行时常量池，相对于 Class 文件常量池的另一个重要特征是：`动态性`，Java 语言并不要求常量一定只有编译期间才能产生，运行期间也可以将新的常量放入池中，String 类的 `intern() `方法就是这样的
- 当创建类或接口的运行时常量池时，如果构造运行时常量池所需的内存空间超过了方法区所能提供的最大值，则 JVM 会抛出 OutOfMemoryError 异常。
### 5.6.5 方法区在 JDK6、7、8中的演进细节
只有 HotSpot 才有永久代的概念
| JDK 版本 | 方法区实现 | 静态变量存放位置 | 字符串常量池存放位置 | 类型信息、字段、方法、常量存放位置 |
| :--- | :--- | :--- | :--- | :--- |
| **JDK 1.6 及之前** | 永久代（在堆中） | 永久代 | 永久代 | 永久代 |
| **JDK 1.7** | 永久代（在堆中，但开始“去永久代”） | **堆** | **堆** | 永久代 |
| **JDK 1.8 及之后** | 元空间（在本地内存） | **堆** | **堆** | **元空间（本地内存）** |


- HotSpot中字符串常量池保存哪里？永久代？方法区还是堆区？
  - 运行时常量池（Runtime Constant Pool）是虚拟机规范中是方法区的一部分，在加载类和结构到虚拟机后，就会创建对应的运行时常量池；而字符串常量池是这个过程中常量字符串的存放位置。所以从这个角度，字符串常量池属于虚拟机规范中的方法区，它是一个逻辑上的概念；而堆区，永久代以及元空间是实际的存放位置。
  - 不同的虚拟机对虚拟机的规范（比如方法区）是不一样的，只有 HotSpot 才有永久代的概念。
  - HotSpot也是发展的，由于一些问题在新窗口打开的存在，HotSpot考虑逐渐去永久代，对于不同版本的JDK，实际的存储位置是有差异的，具体看如下表格：
- 为永久代设置空间大小是很难确定的。

在某些场景下，如果动态加载类过多，容易产生 Perm 区的 OOM。如果某个实际 Web 工程中，因为功能点比较多，在运行过程中，要不断动态加载很多类，经常出现 OOM。而元空间和永久代最大的区别在于，元空间不在虚拟机中，而是使用本地内存，所以默认情况下，元空间的大小仅受本地内存限制
- 对永久代进行调优较困难#
### 5.6.6 方法区的垃圾回收
方法区的垃圾收集主要回收两部分内容：`常量池中废弃的常量和不再使用的类型。`

先来说说方法区内常量池之中主要存放的两大类常量：字面量和符号引用。字面量比较接近 Java 语言层次的常量概念，如文本字符串、被声明为 final 的常量值等。而符号引用则属于编译原理方面的概念，包括下面三类常量：
- 类和接口的全限定名
- 字段的名称和描述符
- 方法的名称和描述符

HotSpot 虚拟机对常量池的回收策略是很明确的，只要常量池中的常量没有被任何地方引用，就可以被回收

判定一个类型是否属于“不再被使用的类”，需要同时满足三个条件：

- 该类所有的实例都已经被回收，也就是 Java 堆中不存在该类及其任何派生子类的实例
- 加载该类的类加载器已经被回收，这个条件除非是经过精心设计的可替换类加载器的场景，如 OSGi、JSP 的重加载等，否则通常很难达成
- 该类对应的 java.lang.Class 对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法

Java 虚拟机被允许堆满足上述三个条件的无用类进行回收，这里说的仅仅是“被允许”，而并不是和对象一样，不使用了就必然会回收。是否对类进行回收，HotSpot 虚拟机提供了 -Xnoclassgc 参数进行控制，还可以使用 -verbose:class 以及 -XX:+TraceClassLoading 、-XX:+TraceClassUnLoading 查看类加载和卸载信息。

`在大量使用反射、动态代理、CGLib 等 ByteCode 框架、动态生成 JSP 以及 OSGi 这类频繁自定义 ClassLoader 的场景都需要虚拟机具备类卸载的功能，以保证永久代不会溢出。`

### 5.6.7 运行时常量池和字符串常量池的区别
这是一个非常核心的JVM面试题，很多初学者容易混淆。它们的主要区别在于**所属维度、包含内容和创建时机**。

简单来说：
*   **运行时常量池是“类”级别的**，每个类都有一个。
*   **字符串常量池是“全局”级别的**，整个JVM实例只有一个。

下面通过一个对比表格和详细解释来说明。

#### 5.6.7.1 核心区别对比表

| 特征 | 运行时常量池 | 字符串常量池 |
| :--- | :--- | :--- |
| **所属维度** | **类级别**（每个类都有自己的运行时常量池） | **全局级别**（整个JVM实例共享一个） |
| **数据来源** | **Class文件中的常量池表** | 1. **字面量**（双引号括起来的字符串）<br>2. **String.intern()** 方法主动添加 |
| **包含内容** | 丰富多彩的符号引用：<br>• 类、接口、字段、方法的**符号引用**<br>• **各种字面量**（包括字符串字面量、数值字面量等） | **极其单一**，只包含**字符串对象的直接引用** |
| **创建时机** | **类加载时**，JVM将Class文件中的常量池表加载到内存中，转换为运行时常量池 | **JVM启动时**就被创建，是一个全局的缓存池 |
| **与Class文件的关系** | 是Class文件中**常量池表**的内存映射和运行时形态 | **没有直接的对应关系**，它是一个运行时动态维护的缓存 |

---

#### 5.6.7.2 详细解释与互动关系

##### 5.6.7.2.1. 运行时常量池

*   **是什么**：它是 **Java Class 文件中“常量池表”的运行时表示**。当你编译一个 `.java` 文件后，编译器会在生成的 `.class` 文件中创建一个“常量池表”，这个表里存放了编译期可知的各种字面量和符号引用。
*   **作用**：在类加载的“解析”阶段，JVM会将运行时常量池中的**符号引用**（如类的全限定名、方法名）**解析**为**直接引用**（具体的内存地址或句柄）。
*   **例子**：一个类中定义了 `private int age = 18;` 和 `String name = "张三";`，那么数字 `18` 和字符串 `"张三"` 的字面量信息都会存在于这个类的运行时常量池中。

##### 5.6.7.2.2. 字符串常量池

*   **是什么**：可以理解为一个**全局的 `String` 对象缓存池**。它的**唯一目的就是避免重复创建字符串对象**，以节省内存、提高性能。
*   **工作原理（String Interning）**：
    1.  当代码中出现一个字符串字面量（如 `String s1 = "hello";`）时，JVM会先去字符串常量池中查找是否存在内容相等的字符串对象。
    2.  **如果找到了**，则直接返回池中那个对象的引用。
    3.  **如果没找到**，则会在池中（JDK 1.7后是在堆中）创建一个新的字符串对象，并将其引用放入字符串常量池，然后返回这个引用。
    4.  使用 `new String("hello")` 这种方式，会**强制**在堆中创建一个新的对象（即使池中已存在"hello"）。

##### 5.6.7.2.3. 它们如何互动？

这是理解两者关系的关键。我们通过一段代码来演示：

```java
String s1 = "hello"; // 字面量赋值
String s2 = new String("hello"); // new 关键字创建

System.out.println(s1 == s2); // 比较引用地址，输出：false
```

**JVM 内部的执行步骤：**

1.  **类加载**：JVM加载这个包含代码的类，解析其Class文件。
2.  **运行时常量池的创建**：在类加载过程中，Class文件常量池表的内容被加载到内存，形成**该类的运行时常量池**。此时，字符串字面量 `"hello"` 作为一项内容被记录在**运行时常量池**中。
3.  **执行 `String s1 = "hello";`**：
    *   JVM看到字面量 `"hello"`，它会去**全局的字符串常量池**中查找是否已有 `"hello"`。
    *   假设是第一次执行，池中没有。于是，JVM**在堆中创建一个新的String对象**，内容为"hello"，然后**将这个对象的引用登记到字符串常量池中**。
    *   最后，将这个引用赋值给变量 `s1`。
4.  **执行 `String s2 = new String("hello");`**：
    *   JVM同样看到字面量 `"hello"`，它会重复第3步的查找过程。但这次，因为第3步已经创建过了，所以会**直接从字符串常量池中拿到同一个 "hello" 对象的引用**（注意：这里拿到的是作为构造函数参数的引用）。
    *   但是，`new` 关键字会**强制在堆中创建一个全新的、不同的String对象**。
    *   这个新对象在构造时，会将池中那个"hello"对象的内容（char数组）复制过来。
    *   最终，变量 `s2` 指向的是那个**新创建的**String对象，而不是池中的那个。
5.  **结果**：`s1` 指向池中的对象，`s2` 指向堆中的一个新对象，所以 `s1 == s2` 为 `false`。

#### 5.6.7.3 总结与比喻

*   **运行时常量池**：像一个**班级的花名册**（每个班级/类都有自己的花名册），上面记录了本班学生的基本信息（名字、学号等符号描述）。
*   **字符串常量池**：像学校的**总学生档案室**（全校只有一个），里面存放着所有学生的实体档案（直接引用）。当某个班级的花名册上提到“张三”时，会去总档案室找到张三的实体档案。
# 六、JVM 基础 - Java 内存模型引入
## 6.1 JMM引入
### 6.1.1 从堆栈说起
JVM内部使用的Java内存模型在线程栈和堆之间划分内存。 此图从逻辑角度说明了Java内存模型：

![JAVA堆栈](../assets/images/03-JVM/18.JAVA堆栈.png)
### 6.1.2 堆栈里面放了什么?
线程堆栈还包含正在执行的每个方法的所有局部变量(调用堆栈上的所有方法)。 线程只能访问它自己的线程堆栈。 由线程创建的局部变量对于创建它的线程以外的所有其他线程是不可见的。 即使两个线程正在执行完全相同的代码，两个线程仍将在每个自己的线程堆栈中创建该代码的局部变量。 因此，每个线程都有自己的每个局部变量的版本。

基本类型的所有局部变量(boolean，byte，short，char，int，long，float，double)完全存储在线程堆栈中，因此对其他线程不可见。 一个线程可以将一个基本类型变量的副本传递给另一个线程，但它不能共享原始局部变量本身。

堆包含了在Java应用程序中创建的所有对象，无论创建该对象的线程是什么。 这包括基本类型的包装类(例如Byte，Integer，Long等)。 无论是创建对象并将其分配给局部变量，还是创建为另一个对象的成员变量，该对象仍然存储在堆上。
![JAVA堆栈2](../assets/images/03-JVM/27.JAVA堆栈2.png)
- 局部变量可以是基本类型，在这种情况下，它完全保留在线程堆栈上。
- 局部变量也可以是对象的引用。 在这种情况下，引用(局部变量)存储在线程堆栈中，但是对象本身存储在堆(Heap)上。
- 对象的成员变量与对象本身一起存储在堆上。 当成员变量是基本类型时，以及它是对象的引用时都是如此。
- 静态类变量也与类定义一起存储在堆上。
### 6.1.3 线程栈如何访问堆上对象?
所有具有对象引用的线程都可以访问堆上的对象。 当一个线程有权访问一个对象时，它也可以访问该对象的成员变量。 如果两个线程同时在同一个对象上调用一个方法，它们都可以访问该对象的成员变量，但每个线程都有自己的局部变量副本。
![线程栈访问堆上对象](../assets/images/03-JVM/228.线程栈访问堆上对象.png)
- 两个线程有一组局部变量。 其中一个局部变量(局部变量2)指向堆上的共享对象(对象3)。 两个线程各自对同一对象具有不同的引用。 它们的引用是局部变量，因此存储在每个线程的线程堆栈中(在每个线程堆栈上)。 但是，这两个不同的引用指向堆上的同一个对象。
- 注意共享对象(对象3)如何将对象2和对象4作为成员变量引用(由对象3到对象2和对象4的箭头所示)。 通过对象3中的这些成员变量引用，两个线程可以访问对象2和对象4.
- 该图还显示了一个局部变量，该变量指向堆上的两个不同对象。 在这种情况下，引用指向两个不同的对象(对象1和对象5)，而不是同一个对象。 理论上，如果两个线程都引用了两个对象，则两个线程都可以访问对象1和对象5。 但是在上图中，每个线程只引用了两个对象中的一个。
### 6.1.4 线程栈访问堆示例
那么，什么样的Java代码可以导致上面的内存图? 好吧，代码就像下面的代码一样简单：
```java
public class MyRunnable implements Runnable() {

    public void run() {
        methodOne();
    }

    public void methodOne() {
        int localVariable1 = 45;

        MySharedObject localVariable2 =
            MySharedObject.sharedInstance;

        //... do more with local variables.

        methodTwo();
    }

    public void methodTwo() {
        Integer localVariable1 = new Integer(99);

        //... do more with local variable.
    }
}

public class MySharedObject {

    //static variable pointing to instance of MySharedObject

    public static final MySharedObject sharedInstance =
        new MySharedObject();


    //member variables pointing to two objects on the heap

    public Integer object2 = new Integer(22);
    public Integer object4 = new Integer(44);

    public long member1 = 12345;
    public long member1 = 67890;
}
```
- 如果两个线程正在执行run()方法，则前面显示的图表将是结果。 run()方法调用methodOne()，methodOne()调用methodTwo()。
- methodOne()声明一个局部基本类型变量(类型为int的localVariable1)和一个局部变量，它是一个对象引用(localVariable2)。执行methodOne()的每个线程将在各自的线程堆栈上创建自己的localVariable1和localVariable2副本。 localVariable1变量将完全相互分离，只存在于每个线程的线程堆栈中。 一个线程无法看到另一个线程对其localVariable1副本所做的更改。
- 执行methodOne()的每个线程也将创建自己的localVariable2副本。 但是，localVariable2的两个不同副本最终都指向堆上的同一个对象。 代码将localVariable2设置为指向静态变量引用的对象。 静态变量只有一个副本，此副本存储在堆上。 因此，localVariable2的两个副本最终都指向静态变量指向的MySharedObject的同一个实例。 MySharedObject实例也存储在堆上。 它对应于上图中的对象3。
- 注意MySharedObject类还包含两个成员变量。 成员变量本身与对象一起存储在堆上。 两个成员变量指向另外两个Integer对象。 这些Integer对象对应于上图中的Object 2和Object 4。
- 另请注意methodTwo()如何创建名为localVariable1的局部变量。 此局部变量是对Integer对象的对象引用。 该方法将localVariable1引用设置为指向新的Integer实例。 localVariable1引用将存储在执行methodTwo()的每个线程的一个副本中。 实例化的两个Integer对象将存储在堆上，但由于该方法每次执行该方法时都会创建一个新的Integer对象，因此执行此方法的两个线程将创建单独的Integer实例。 在methodTwo()中创建的Integer对象对应于上图中的Object 1和Object 5。
- 另请注意类型为long的MySharedObject类中的两个成员变量，它们是基本类型。 由于这些变量是成员变量，因此它们仍与对象一起存储在堆上。 只有局部变量存储在线程堆栈中。
## 6.2 JMM与硬件内存结构关系
### 6.2.1 硬件内存结构简介
现代硬件内存架构与内部Java内存模型略有不同。 了解硬件内存架构也很重要，以了解Java内存模型如何与其一起工作。 本节介绍了常见的硬件内存架构，后面的部分将介绍Java内存模型如何与其配合使用。

这是现代计算机硬件架构的简化图：
![JAVA堆现代计算机硬件架构栈](../assets/images/03-JVM/29.现代计算机硬件架构.png)
- 现代计算机通常有2个或更多CPU。 其中一些CPU也可能有多个内核。 关键是，在具有2个或更多CPU的现代计算机上，可以同时运行多个线程。 每个CPU都能够在任何给定时间运行一个线程。 这意味着如果您的Java应用程序是多线程的，线程真的在可能同时运行.
- 每个CPU基本上都包含一组在CPU内存中的寄存器。 CPU可以在这些寄存器上执行的操作比在主存储器中对变量执行的操作快得多。 这是因为CPU可以比访问主存储器更快地访问这些寄存器。
- 每个CPU还可以具有CPU高速缓存存储器层。 事实上，大多数现代CPU都有一些大小的缓存存储层。 CPU可以比主存储器更快地访问其高速缓存存储器，但通常不会像访问其内部寄存器那样快。 因此，CPU高速缓存存储器介于内部寄存器和主存储器的速度之间。 某些CPU可能有多个缓存层(级别1和级别2)，但要了解Java内存模型如何与内存交互，这一点并不重要。 重要的是要知道CPU可以有某种缓存存储层。
- 计算机还包含主存储区(RAM)。 所有CPU都可以访问主内存。 主存储区通常比CPU的高速缓存存储器大得多。同时访问速度也就较慢.
- 通常，当CPU需要访问主存储器时，它会将部分主存储器读入其CPU缓存。 它甚至可以将部分缓存读入其内部寄存器，然后对其执行操作。 当CPU需要将结果写回主存储器时，它会将值从其内部寄存器刷新到高速缓冲存储器，并在某些时候将值刷新回主存储器。
### 6.2.2 JMM与硬件内存连接 - 引入
如前所述，Java内存模型和硬件内存架构是不同的。 硬件内存架构不区分线程堆栈和堆。 在硬件上，线程堆栈和堆都位于主存储器中。 线程堆栈和堆的一部分有时可能存在于CPU高速缓存和内部CPU寄存器中。 这在图中说明：
![JMM与硬件内存连接](../assets/images/03-JVM/30.JMM与硬件内存连接.png)
当对象和变量可以存储在计算机的各种不同存储区域中时，可能会出现某些问题。 两个主要问题是：
- Visibility of thread updates (writes) to shared variables.线程更新（写入）到共享变量的可见性。

- Race conditions when reading, checking and writing shared variables. 读取、检查和写入共享变量时的竞态条件。

以下各节将解释这两个问题。
### 6.2.3 JMM与硬件内存连接 - 对象共享后的可见性
- 如果两个或多个线程共享一个对象，而没有正确使用volatile声明或同步，则一个线程对共享对象的更新可能对其他线程不可见。
- 想象一下，共享对象最初存储在主存储器中。 然后，在CPU上运行的线程将共享对象读入其CPU缓存中。 它在那里对共享对象进行了更改。 只要CPU缓存尚未刷新回主内存，共享对象的更改版本对于在其他CPU上运行的线程是不可见的。 这样，每个线程最终都可能拥有自己的共享对象副本，每个副本都位于不同的CPU缓存中。
- 下图描绘了该情况。 在左CPU上运行的一个线程将共享对象复制到其CPU缓存中，并将其count变量更改为2.对于在右边的CPU上运行的其他线程，此更改不可见，因为计数更新尚未刷新回主内存中.
![可见性](../assets/images/03-JVM/31.可见性.png)
要解决此问题，您可以使用Java的volatile关键字。 volatile关键字可以确保直接从主内存读取给定变量，并在更新时始终写回主内存。
### 6.2.4 JMM与硬件内存连接 - 竞态条件
- 如果两个或多个线程共享一个对象，并且多个线程更新该共享对象中的变量，则可能会出现竞态。
- 想象一下，如果线程A将共享对象的变量计数读入其CPU缓存中。 想象一下，线程B也做同样的事情，但是进入不同的CPU缓存。 现在，线程A将一个添加到count，而线程B执行相同的操作。 现在var1已经增加了两次，每个CPU缓存一次。
- 如果这些增量是按先后顺序执行的，则变量计数将增加两次并将原始值+ 2写回主存储器。
- 但是，两个增量同时执行而没有适当的同步。 无论线程A和B中哪一个将其更新后的计数版本写回主存储器，更新的值将仅比原始值高1，尽管有两个增量。
- 该图说明了如上所述的竞争条件问题的发生：
![竞态条件](../assets/images/03-JVM/32.竞态条件.png)
要解决此问题，您可以使用Java synchronized块。 同步块保证在任何给定时间只有一个线程可以进入代码的给定关键部分。 同步块还保证在同步块内访问的所有变量都将从主存储器中读入，当线程退出同步块时，所有更新的变量将再次刷新回主存储器，无论变量是不是声明为volatile
# 七、JVM 基础 - Java 内存模型详解
## 7.1 基础
### 7.1.1 并发编程模型的分类
在并发编程中，我们需要处理两个关键问题：线程之间如何通信及线程之间如何同步（这里的线程是指并发执行的活动实体）。通信是指线程之间以何种机制来交换信息。在命令式编程中，线程之间的通信机制有两种：`共享内存`和`消息传递`。

在共享内存的并发模型里，线程之间共享程序的公共状态，`线程之间通过写 - 读内存中的公共状态`来隐式进行通信。在消息传递的并发模型里，线程之间没有公共状态，线程之间必须通过明确的发送消息来显式进行通信。

`同步`是指程序用于控制不同线程之间操作发生相对顺序的机制。在共享内存并发模型里，同步是显式进行的。程序员必须显式指定某个方法或某段代码需要在线程之间互斥执行。在消息传递的并发模型里，由于消息的发送必须在消息的接收之前，因此同步是隐式进行的。

`Java 的并发采用的是共享内存模型`，Java 线程之间的通信总是隐式进行，整个通信过程对程序员完全透明。如果编写多线程程序的 Java 程序员不理解隐式进行的线程之间通信的工作机制，很可能会遇到各种奇怪的内存可见性问题。
### 7.1.2 Java 内存模型的抽象
在 java 中，所有实例域、静态域和数组元素存储在堆内存中，堆内存在线程之间共享（本文使用“共享变量”这个术语代指实例域，静态域和数组元素）。局部变量（Local variables），方法定义参数（java 语言规范称之为 formal method parameters）和异常处理器参数（exception handler parameters）不会在线程之间共享，它们不会有内存可见性问题，也不受内存模型的影响。

Java 线程之间的通信由 Java 内存模型（本文简称为 JMM）控制，JMM 决定一个线程对共享变量的写入何时对另一个线程可见。从抽象的角度来看，JMM 定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读 / 写共享变量的副本。本地内存是 JMM 的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。Java 内存模型的抽象示意图如下：
![JAVA内存抽象](../assets/images/03-JVM/33.JAVA内存抽象.png)
从上图来看，线程 A 与线程 B 之间如要通信的话，必须要经历下面 2 个步骤：
- 首先，线程 A 把本地内存 A 中更新过的共享变量刷新到主内存中去。
- 然后，线程 B 到主内存中去读取线程 A 之前已更新过的共享变量。

下面通过示意图来说明这两个步骤：
![AB线程共享变量](../assets/images/03-JVM/34.AB线程共享变量.png)
如上图所示，本地内存 A 和 B 有主内存中共享变量 x 的副本。假设初始时，这三个内存中的 x 值都为 0。线程 A 在执行时，把更新后的 x 值（假设值为 1）临时存放在自己的本地内存 A 中。当线程 A 和线程 B 需要通信时，线程 A 首先会把自己本地内存中修改后的 x 值刷新到主内存中，此时主内存中的 x 值变为了 1。随后，线程 B 到主内存中去读取线程 A 更新后的 x 值，此时线程 B 的本地内存的 x 值也变为了 1。

从整体来看，这两个步骤实质上是线程 A 在向线程 B 发送消息，而且这个通信过程必须要经过主内存。JMM 通过控制主内存与每个线程的本地内存之间的交互，来为 java 程序员提供内存可见性保证。
### 7.1.3 重排序
在执行程序时为了提高性能，编译器和处理器常常会对指令做重排序。重排序分三种类型：
- `编译器优化的重排序`。编译器在不改变单线程程序语义的前提下，可以重新安排语句的执行顺序。
- `指令级并行的重排序`。现代处理器采用了指令级并行技术（Instruction-Level Parallelism， ILP）来将多条指令重叠执行。如果不存在数据依赖性，处理器可以改变语句对应机器指令的执行顺序。
- `内存系统的重排序`。由于处理器使用缓存和读 / 写缓冲区，这使得加载和存储操作看上去可能是在乱序执行。

从 java 源代码到最终实际执行的指令序列，会分别经历下面三种重排序：
![重排序](../assets/images/03-JVM/35.重排序.png)
上述的 1 属于编译器重排序，2 和 3 属于处理器重排序。这些重排序都可能会导致多线程程序出现内存可见性问题。对于编译器，JMM 的编译器重排序规则会禁止特定类型的编译器重排序（不是所有的编译器重排序都要禁止）。对于处理器重排序，JMM 的处理器重排序规则会要求 java 编译器在生成指令序列时，插入特定类型的内存屏障（memory barriers，intel 称之为 memory fence）指令，通过内存屏障指令来禁止特定类型的处理器重排序（不是所有的处理器重排序都要禁止）。

JMM 属于语言级的内存模型，它确保在不同的编译器和不同的处理器平台之上，通过禁止特定类型的编译器重排序和处理器重排序，为程序员提供一致的内存可见性保证。
### 7.1.4 处理器重排序与内存屏障指令
现代的处理器使用写缓冲区来临时保存向内存写入的数据。写缓冲区可以保证指令流水线持续运行，它可以避免由于处理器停顿下来等待向内存写入数据而产生的延迟。同时，通过以批处理的方式刷新写缓冲区，以及合并写缓冲区中对同一内存地址的多次写，可以减少对内存总线的占用。虽然写缓冲区有这么多好处，但每个处理器上的写缓冲区，仅仅对它所在的处理器可见。这个特性会对内存操作的执行顺序产生重要的影响：处理器对内存的读 / 写操作的执行顺序，不一定与内存实际发生的读 / 写操作顺序一致！为了具体说明，请看下面示例：
```java
// Processor A
a = 1; //A1  
x = b; //A2

// Processor B
b = 2; //B1  
y = a; //B2

// 初始状态：a = b = 0；处理器允许执行后得到结果：x = y = 0

```
假设处理器 A 和处理器 B 按程序的顺序并行执行内存访问，最终却可能得到 x = y = 0 的结果。具体的原因如下图所示：
![写缓冲区对数据可见性的影响](../assets/images/03-JVM/36.写缓冲区对数据可见性的影响.png)
这里处理器 A 和处理器 B 可以同时把共享变量写入自己的写缓冲区（A1，B1），然后从内存中读取另一个共享变量（A2，B2），最后才把自己写缓存区中保存的脏数据刷新到内存中（A3，B3）。当以这种时序执行时，程序就可以得到 x = y = 0 的结果。

从内存操作实际发生的顺序来看，直到处理器 A 执行 A3 来刷新自己的写缓存区，写操作 A1 才算真正执行了。虽然处理器 A 执行内存操作的顺序为：A1->A2，但内存操作实际发生的顺序却是：A2->A1。此时，处理器 A 的内存操作顺序被重排序了（处理器 B 的情况和处理器 A 一样，这里就不赘述了）。

这里的关键是，由于写缓冲区仅对自己的处理器可见，它会导致处理器执行内存操作的顺序可能会与内存实际的操作执行顺序不一致。由于现代的处理器都会使用写缓冲区，因此现代的处理器都会允许对写 - 读操做重排序。

下面是常见处理器允许的重排序类型的列表：
| 架构 | Load-Load | Load-Store | Store-Store | Store-Load | 数据依赖 |
| :--- | :---: | :---: | :---: | :---: | :---: |
| sparc-TSO | N | N | N | Y | N |
| x86 | N | N | N | Y | N |
| ia64 | Y | Y | Y | Y | N |
| PowerPC | Y | Y | Y | Y | N |
上表单元格中的“N”表示处理器不允许两个操作重排序，“Y”表示允许重排序。

从上表我们可以看出：常见的处理器都允许 Store-Load 重排序；常见的处理器都不允许对存在数据依赖的操作做重排序。sparc-TSO 和 x86 拥有相对较强的处理器内存模型，它们仅允许对写 - 读操作做重排序（因为它们都使用了写缓冲区）。
- ※注 1：sparc-TSO 是指以 TSO(Total Store Order) 内存模型运行时，sparc 处理器的特性。
- ※注 2：上表中的 x86 包括 x64 及 AMD64。
- ※注 3：由于 ARM 处理器的内存模型与 PowerPC 处理器的内存模型非常类似，本文将忽略它。
- ※注 4：数据依赖性后文会专门说明。

为了保证内存可见性，java 编译器在生成指令序列的适当位置会插入内存屏障指令来禁止特定类型的处理器重排序。JMM 把内存屏障指令分为下列四类：
| 屏障类型 | 指令示例 | 说明 |
| :--- | :--- | :--- |
| **LoadLoad Barriers** | `Load1; LoadLoad; Load2` | 确保 Load1 数据的装载，之前于 Load2 及所有后续装载指令的装载。 |
| **StoreStore Barriers** | `Store1; StoreStore; Store2` | 确保 Store1 数据对其他处理器可见（刷新到内存），之前于 Store2 及所有后续存储指令的存储。 |
| **LoadStore Barriers** | `Load1; LoadStore; Store2` | 确保 Load1 数据装载，之前于 Store2 及所有后续的存储指令刷新到内存。 |
| **StoreLoad Barriers** | `Store1; StoreLoad; Load2` | 确保 Store1 数据对其他处理器变得可见（指刷新到内存），之前于 Load2 及所有后续装载指令的装载。 |

StoreLoad Barriers 会使该屏障之前的所有内存访问指令（存储和装载指令，也就是**写指令+读指令**）完成之后，才执行该屏障之后的内存访问指令(存储和装载指令，也就是**写指令+读指令**)。

- 内存访问指令包含：存储(Store)和装载(Load)的对应关系
  - **存储(Store) = 写操作**：把数据写入内存
  - **装载(Load) = 读操作**：从内存读取数据

| 屏障类型 | 防止的重排序 | 比喻 |
|---------|-------------|------|
| **LoadLoad** | 后面的读 → 前面的读 | 单向门 |
| **StoreStore** | 后面的写 → 前面的写 | 单向门 |
| **LoadStore** | 后面的写 → 前面的读 | 单向门 |
| **StoreLoad** | **后面的读 → 前面的写** + **后面的写 → 前面的读** | **双向墙** |

**关键理解：** StoreLoad屏障之所以强大，就是因为它创建了一个**完全的内存同步点**，屏障前后的任何内存访问操作都不能互相跨越，这既是它的设计目的，也是它开销大的原因。

StoreLoad Barriers 是一个“全能型”的屏障，它同时具有其他三个屏障的效果。现代的多处理器大都支持该屏障（其他类型的屏障不一定被所有处理器支持）。执行该屏障开销会很昂贵，因为当前处理器通常要把写缓冲区中的数据全部刷新到内存中（buffer fully flush）。

**StoreLoad屏障实际上是两个独立约束的组合**

- 约束1：防止"写→读"重排序（主要目的）
```
Store1; StoreLoad; Load2
```
**禁止：** `Load2` 被重排序到 `Store1` 之前

这是StoreLoad屏障的**主要目的** - 防止后续的读操作看到陈旧数据。

- 约束2：防止"读→写"重排序（副作用）
```
Load1; StoreLoad; Store2  
```
**禁止：** `Store2` 被重排序到 `Load1` 之前

这是StoreLoad屏障的**副作用**，因为它在实现主要目的时自然获得了这个能力。

**为什么会有这种"双向"效果？**

- 硬件实现的角度

当CPU执行StoreLoad屏障时，它需要：
1. **清空存储缓冲区**（处理Store部分）
2. **等待所有挂起操作完成**（处理Load部分）

当CPU执行StoreLoad屏障时：

- 步骤1：等待所有挂起的存储完成
  - 必须等待存储缓冲区(Store Buffer)中的所有写操作被提交到缓存
  - 这**自然地**阻止了屏障**后**的写操作被提前执行（因为存储缓冲区被阻塞了）

- 步骤2：使缓存失效并同步
  - 为了确保后续的读操作能看到最新数据，需要使相应缓存行失效
  - 这**自然地**创建了一个同步点，屏障前后的操作无法跨越

- 步骤3：执行屏障后的操作
  - 只有在前面的所有内存操作都完成后，才能继续
### 7.1.5 happens-before
从 JDK5 开始，java 使用新的 JSR -133 内存模型（本文除非特别说明，针对的都是 JSR- 133 内存模型）。JSR-133 提出了 happens-before 的概念，通过这个概念来阐述操作之间的内存可见性。如果一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必须存在 happens-before 关系。这里提到的两个操作既可以是在一个线程之内，也可以是在不同线程之间。 与程序员密切相关的 happens-before 规则如下：

- 程序顺序规则：一个线程中的每个操作，happens-before 于该线程中的任意后续操作。
- 监视器锁规则：对一个监视器锁的解锁，happens-before 于随后对这个监视器锁的加锁。
- volatile 变量规则：对一个 volatile 域的写，happens-before 于任意后续对这个 volatile 域的读。
- 传递性：如果 A happens-before B，且 B happens-before C，那么 A happens-before C。

注意，两个操作之间具有 happens-before 关系，并不意味着前一个操作必须要在后一个操作之前执行！happens-before 仅仅要求前一个操作（执行的结果）对后一个操作可见，且前一个操作按顺序排在第二个操作之前（the first is visible to and ordered before the second）。happens- before 的

happens-before 与 JMM 的关系如下图所示：
![appens-before与MM的关系](../assets/images/03-JVM/37.happens-before与MM的关系.png)
如上图所示，一个 happens-before 规则通常对应于多个编译器重排序规则和处理器重排序规则。对于 java 程序员来说，happens-before 规则简单易懂，它避免程序员为了理解 JMM 提供的内存可见性保证而去学习复杂的重排序规则以及这些规则的具体实现。
## 7.2 重排序
### 7.2.1 数据依赖性
如果两个操作访问同一个变量，且这两个操作中有一个为写操作，此时这两个操作之间就存在数据依赖性。数据依赖分下列三种类型：

| 名称 | 代码示例 | 说明 |
| :--- | :--- | :--- |
| 写后读 | `a = 1;`<br>`b = a;` | 写一个变量之后，再读这个位置 |
| 写后写 | `a = 1;`<br>`a = 2;` | 写一个变量之后，再写这个变量 |
| 读后写 | `a = b;`<br>`b = 1;` | 读一个变量之后，再写这个变量 |

上面三种情况，只要重排序两个操作的执行顺序，程序的执行结果将会被改变。

前面提到过，编译器和处理器可能会对操作做重排序。编译器和处理器在重排序时，会遵守数据依赖性，编译器和处理器不会改变存在数据依赖关系的两个操作的执行顺序。

注意，这里所说的数据依赖性仅针对单个处理器中执行的指令序列和单个线程中执行的操作，不同处理器之间和不同线程之间的数据依赖性不被编译器和处理器考虑。
### 7.2.2 as-if-serial 语义
as-if-serial 语义的意思指：不管怎么重排序（编译器和处理器为了提高并行度），（单线程）程序的执行结果不能被改变。编译器，runtime 和处理器都必须遵守 as-if-serial 语义。

为了遵守 as-if-serial 语义，编译器和处理器不会对存在数据依赖关系的操作做重排序，因为这种重排序会改变执行结果。但是，如果操作之间不存在数据依赖关系，这些操作可能被编译器和处理器重排序。为了具体说明，请看下面计算圆面积的代码示例：
```java
double pi  = 3.14;    //A
double r   = 1.0;     //B
double area = pi * r * r; //C
```
上面三个操作的数据依赖关系如下图所示：

![aas-if-serial语义1](../assets/images/03-JVM/38.as-if-serial语义1.png)

如上图所示，A 和 C 之间存在数据依赖关系，同时 B 和 C 之间也存在数据依赖关系。因此在最终执行的指令序列中，C 不能被重排序到 A 和 B 的前面（C 排到 A 和 B 的前面，程序的结果将会被改变）。但 A 和 B 之间没有数据依赖关系，编译器和处理器可以重排序 A 和 B 之间的执行顺序。下图是该程序的两种执行顺序：

![aas-if-serial语义2](../assets/images/03-JVM/39.as-if-serial语义2.png)

as-if-serial 语义把单线程程序保护了起来，遵守 as-if-serial 语义的编译器，runtime 和处理器共同为编写单线程程序的程序员创建了一个幻觉：单线程程序是按程序的顺序来执行的。as-if-serial 语义使单线程程序员无需担心重排序会干扰他们，也无需担心内存可见性问题。
### 7.2.3 程序顺序规则
根据 happens- before 的程序顺序规则，上面计算圆的面积的示例代码存在三个 happens- before 关系：
- A happens- before B；
- B happens- before C；
- A happens- before C；

这里的第 3 个 happens- before 关系，是根据 happens- before 的传递性推导出来的。

这里 A happens- before B，但实际执行时 B 却可以排在 A 之前执行（看上面的重排序后的执行顺序）。在第一章提到过，如果 A happens- before B，JMM 并不要求 A 一定要在 B 之前执行。`JMM 仅仅要求前一个操作（执行的结果）对后一个操作可见`，且前一个操作按顺序排在第二个操作之前。这里操作 A 的执行结果不需要对操作 B 可见；而且重排序操作 A 和操作 B 后的执行结果，与操作 A 和操作 B 按 happens- before 顺序执行的结果一致。在这种情况下，JMM 会认为这种重排序并不非法（not illegal），JMM 允许这种重排序。

在计算机中，软件技术和硬件技术有一个共同的目标：在不改变程序执行结果的前提下，尽可能的开发并行度。编译器和处理器遵从这一目标，从 happens- before 的定义我们可以看出，JMM 同样遵从这一目标。
### 7.2.4 重排序对多线程的影响
现在让我们来看看，重排序是否会改变多线程程序的执行结果。请看下面的示例代码：
```java
class ReorderExample {
    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1;                   //1
        flag = true;             //2
    }

    Public void reader() {
        if (flag) {                //3
            int i =  a * a;        //4
            ……
        }
    }
}
```
flag 变量是个标记，用来标识变量 a 是否已被写入。这里假设有两个线程 A 和 B，A 首先执行 writer() 方法，随后 B 线程接着执行 reader() 方法。线程 B 在执行操作 4 时，能否看到线程 A 在操作 1 对共享变量 a 的写入?

答案是：不一定能看到。

由于操作 1 和操作 2 没有数据依赖关系，编译器和处理器可以对这两个操作重排序；同样，操作 3 和操作 4 没有数据依赖关系，编译器和处理器也可以对这两个操作重排序。让我们先来看看，当操作 1 和操作 2 重排序时，可能会产生什么效果? 请看下面的程序执行时序图：
![重排序对多线程的影响](../assets/images/03-JVM/40.重排序对多线程的影响.png)

如上图所示，操作 1 和操作 2 做了重排序。程序执行时，线程 A 首先写标记变量 flag，随后线程 B 读这个变量。由于条件判断为真，线程 B 将读取变量 a。此时，变量 a 还根本没有被线程 A 写入，在这里多线程程序的语义被重排序破坏了！

※注：本文统一用红色的虚箭线表示错误的读操作，用绿色的虚箭线表示正确的读操作。

下面再让我们看看，当操作 3 和操作 4 重排序时会产生什么效果（借助这个重排序，可以顺便说明控制依赖性）。下面是操作 3 和操作 4 重排序后，程序的执行时序图：
![重排序对多线程的影响2](../assets/images/03-JVM/41.重排序对多线程的影响2.png)

在程序中，操作 3 和操作 4 存在控制依赖关系。当代码中存在控制依赖性时，会影响指令序列执行的并行度。为此，编译器和处理器会采用猜测（Speculation）执行来克服控制相关性对并行度的影响。以处理器的猜测执行为例，执行线程 B 的处理器可以提前读取并计算 a*a，然后把计算结果临时保存到一个名为重排序缓冲（reorder buffer ROB）的硬件缓存中。当接下来操作 3 的条件判断为真时，就把该计算结果写入变量 i 中。

从图中我们可以看出，猜测执行实质上对操作 3 和 4 做了重排序。重排序在这里破坏了多线程程序的语义！

在单线程程序中，对存在控制依赖的操作重排序，不会改变执行结果（这也是 as-if-serial 语义允许对存在控制依赖的操作做重排序的原因）；但在多线程程序中，对存在控制依赖的操作重排序，可能会改变程序的执行结果。
## 7.3 顺序一致性
### 7.3.1 数据竞争与顺序一致性保证
当程序未正确同步时，就会存在数据竞争。java 内存模型规范对数据竞争的定义如下：
- 在一个线程中写一个变量，
- 在另一个线程读同一个变量，
- 而且写和读没有通过同步来排序。

当代码中包含数据竞争时，程序的执行往往产生违反直觉的结果（前一章的示例正是如此）。如果一个多线程程序能正确同步，这个程序将是一个没有数据竞争的程序。

JMM 对正确同步的多线程程序的内存一致性做了如下保证：

- 如果程序是正确同步的，程序的执行将具有顺序一致性（sequentially consistent）-- 即程序的执行结果与该程序在顺序一致性内存模型中的执行结果相同（马上我们将会看到，这对于程序员来说是一个极强的保证）。这里的同步是指广义上的同步，包括对常用同步原语（lock，volatile 和 final）的正确使用。

### 7.3.2 顺序一致性内存模型
顺序一致性内存模型是一个被计算机科学家理想化了的理论参考模型，它为程序员提供了极强的内存可见性保证。顺序一致性内存模型有两大特性：
- 一个线程中的所有操作必须按照程序的顺序来执行。 +（不管程序是否同步）所有线程都只能看到一个单一的操作执行顺序。在顺序一致性内存模型中，每个操作都必须原子执行且立刻对所有线程可见。 顺序一致性内存模型为程序员提供的视图如下：
![顺序一致性内存模型](../assets/images/03-JVM/42.顺序一致性内存模型.png)

在概念上，顺序一致性模型有一个单一的全局内存，这个内存通过一个左右摆动的开关可以连接到任意一个线程。同时，每一个线程必须按程序的顺序来执行内存读 / 写操作。从上图我们可以看出，在任意时间点最多只能有一个线程可以连接到内存。当多个线程并发执行时，图中的开关装置能把所有线程的所有内存读 / 写操作串行化。

为了更好的理解，下面我们通过两个示意图来对顺序一致性模型的特性做进一步的说明。

假设有两个线程 A 和 B 并发执行。其中 A 线程有三个操作，它们在程序中的顺序是：A1->A2->A3。B 线程也有三个操作，它们在程序中的顺序是：B1->B2->B3。

假设这两个线程使用监视器来正确同步：A 线程的三个操作执行后释放监视器，随后 B 线程获取同一个监视器。那么程序在顺序一致性模型中的执行效果将如下图所示：
![顺序一致性模型示例1](../assets/images/03-JVM/43.顺序一致性模型示例1.png)
现在我们再假设这两个线程没有做同步，下面是这个未同步程序在顺序一致性模型中的执行示意图：
![顺序一致性模型示例2](../assets/images/03-JVM/44.顺序一致性模型示例2.png)
未同步程序在顺序一致性模型中虽然整体执行顺序是无序的，但所有线程都只能看到一个一致的整体执行顺序。以上图为例，线程 A 和 B 看到的执行顺序都是：B1->A1->A2->B2->A3->B3。之所以能得到这个保证是因为顺序一致性内存模型中的每个操作必须立即对任意线程可见。

但是，在 JMM 中就没有这个保证。未同步程序在 JMM 中不但整体的执行顺序是无序的，而且所有线程看到的操作执行顺序也可能不一致。比如，在当前线程把写过的数据缓存在本地内存中，且还没有刷新到主内存之前，这个写操作仅对当前线程可见；从其他线程的角度来观察，会认为这个写操作根本还没有被当前线程执行。只有当前线程把本地内存中写过的数据刷新到主内存之后，这个写操作才能对其他线程可见。在这种情况下，当前线程和其它线程看到的操作执行顺序将不一致。

### 7.3.3 同步程序的顺序一致性效果

下面我们对前面的示例程序 ReorderExample 用监视器来同步，看看正确同步的程序如何具有顺序一致性。

请看下面的示例代码：
```java
class SynchronizedExample {
    int a = 0;
    boolean flag = false;

    public synchronized void writer() {
        a = 1;
        flag = true;
    }

    public synchronized void reader() {
        if (flag) {
            int i = a;
            ……
        }
    }
}
```
面示例代码中，假设 A 线程执行 writer() 方法后，B 线程执行 reader() 方法。这是一个正确同步的多线程程序。根据 JMM 规范，该程序的执行结果将与该程序在顺序一致性模型中的执行结果相同。下面是该程序在两个内存模型中的执行时序对比图：
![顺序一致性模型示例3](../assets/images/03-JVM/45.顺序一致性模型示例3.png)
在顺序一致性模型中，所有操作完全按程序的顺序串行执行。而在 JMM 中，临界区内的代码可以重排序（但 JMM 不允许临界区内的代码“逸出”到临界区之外，那样会破坏监视器的语义）。JMM 会在退出监视器和进入监视器这两个关键时间点做一些特别处理，使得线程在这两个时间点具有与顺序一致性模型相同的内存视图（具体细节后文会说明）。虽然线程 A 在临界区内做了重排序，但由于监视器的互斥执行的特性，这里的线程 B 根本无法“观察”到线程 A 在临界区内的重排序。这种重排序既提高了执行效率，又没有改变程序的执行结果。

从这里我们可以看到 JMM 在具体实现上的基本方针：在不改变（正确同步的）程序执行结果的前提下，尽可能的为编译器和处理器的优化打开方便之门。

### 7.3.4 未同步程序的执行特性
对于未同步或未正确同步的多线程程序，JMM 只提供最小安全性：线程执行时读取到的值，要么是之前某个线程写入的值，要么是默认值（0，null，false），JMM 保证线程读操作读取到的值不会无中生有（out of thin air）的冒出来。为了实现最小安全性，JVM 在堆上分配对象时，首先会清零内存空间，然后才会在上面分配对象（JVM 内部会同步这两个操作）。因此，在以清零的内存空间（pre-zeroed memory）分配对象时，域的默认初始化已经完成了。

JMM 不保证未同步程序的执行结果与该程序在顺序一致性模型中的执行结果一致。因为未同步程序在顺序一致性模型中执行时，整体上是无序的，其执行结果无法预知。保证未同步程序在两个模型中的执行结果一致毫无意义。

和顺序一致性模型一样，未同步程序在 JMM 中的执行时，整体上也是无序的，其执行结果也无法预知。同时，未同步程序在这两个模型中的执行特性有下面几个差异：

- 顺序一致性模型保证单线程内的操作会按程序的顺序执行，而 JMM 不保证单线程内的操作会按程序的顺序执行（比如上面正确同步的多线程程序在临界区内的重排序）。这一点前面已经讲过了，这里就不再赘述。
- 顺序一致性模型保证所有线程只能看到一致的操作执行顺序，而 JMM 不保证所有线程能看到一致的操作执行顺序。这一点前面也已经讲过，这里就不再赘述。
- JMM 不保证对 64 位的 long 型和 double 型变量的读 / 写操作具有原子性，而顺序一致性模型保证对所有的内存读 / 写操作都具有原子性。

第 3 个差异与处理器总线的工作机制密切相关。在计算机中，数据通过总线在处理器和内存之间传递。每次处理器和内存之间的数据传递都是通过一系列步骤来完成的，这一系列步骤称之为总线事务（bus transaction）。总线事务包括读事务（read transaction）和写事务（write transaction）。读事务从内存传送数据到处理器，写事务从处理器传送数据到内存，每个事务会读 / 写内存中一个或多个物理上连续的字。这里的关键是，总线会同步试图并发使用总线的事务。在一个处理器执行总线事务期间，总线会禁止其它所有的处理器和 I/O 设备执行内存的读 / 写。下面让我们通过一个示意图来说明总线的工作机制：
![总线](../assets/images/03-JVM/46.总线.png)

如上图所示，假设处理器 A，B 和 C 同时向总线发起总线事务，这时总线仲裁（bus arbitration）会对竞争作出裁决，这里我们假设总线在仲裁后判定处理器 A 在竞争中获胜（总线仲裁会确保所有处理器都能公平的访问内存）。此时处理器 A 继续它的总线事务，而其它两个处理器则要等待处理器 A 的总线事务完成后才能开始再次执行内存访问。假设在处理器 A 执行总线事务期间（不管这个总线事务是读事务还是写事务），处理器 D 向总线发起了总线事务，此时处理器 D 的这个请求会被总线禁止。

总线的这些工作机制可以把所有处理器对内存的访问以串行化的方式来执行；在任意时间点，最多只能有一个处理器能访问内存。这个特性确保了单个总线事务之中的内存读 / 写操作具有原子性。

在一些 32 位的处理器上，如果要求对 64 位数据的读 / 写操作具有原子性，会有比较大的开销。为了照顾这种处理器，java 语言规范鼓励但不强求 JVM 对 64 位的 long 型变量和 double 型变量的读 / 写具有原子性。当 JVM 在这种处理器上运行时，会把一个 64 位 long/ double 型变量的读 / 写操作拆分为两个 32 位的读 / 写操作来执行。这两个 32 位的读 / 写操作可能会被分配到不同的总线事务中执行，此时对这个 64 位变量的读 / 写将不具有原子性。

当单个内存操作不具有原子性，将可能会产生意想不到后果。请看下面示意图：
![单内存操作的非原子性](../assets/images/03-JVM/47.单内存操作的非原子性.png)
如上图所示，假设处理器 A 写一个 long 型变量，同时处理器 B 要读这个 long 型变量。处理器 A 中 64 位的写操作被拆分为两个 32 位的写操作，且这两个 32 位的写操作被分配到不同的写事务中执行。同时处理器 B 中 64 位的读操作被拆分为两个 32 位的读操作，且这两个 32 位的读操作被分配到同一个的读事务中执行。当处理器 A 和 B 按上图的时序来执行时，处理器 B 将看到仅仅被处理器 A“写了一半“的无效值。
## 7.4 总结
### 7.4.1 处理器内存模型
顺序一致性内存模型是一个理论参考模型，JMM 和处理器内存模型在设计时通常会把顺序一致性内存模型作为参照。JMM 和处理器内存模型在设计时会对顺序一致性模型做一些放松，因为如果完全按照顺序一致性模型来实现处理器和 JMM，那么很多的处理器和编译器优化都要被禁止，这对执行性能将会有很大的影响。

根据对不同类型读 / 写操作组合的执行顺序的放松，可以把常见处理器的内存模型划分为下面几种类型：

- 放松程序中写 - 读操作的顺序，由此产生了 total store ordering 内存模型（简称为 TSO）。
- 在前面 1 的基础上，继续放松程序中写 - 写操作的顺序，由此产生了 partial store order 内存模型（简称为 PSO）。
- 在前面 1 和 2 的基础上，继续放松程序中读 - 写和读 - 读操作的顺序，由此产生了 relaxed memory order 内存模型（简称为 RMO）和 PowerPC 内存模型。

注意，这里处理器对读 / 写操作的放松，是以两个操作之间不存在数据依赖性为前提的（因为处理器要遵守 as-if-serial 语义，处理器不会对存在数据依赖性的两个内存操作做重排序）。

下面的表格展示了常见处理器内存模型的细节特征：


| 内存模型名称 | 对应的处理器 | Store-Load 重排序 | Store-Store 重排序 | Load-Load 和 Load-Store 重排序 | 可以更早读取到其它处理器的写 | 可以更早读取到当前处理器的写 |
| :--- | :--- | :---: | :---: | :---: | :---: | :---: |
| TSO | sparc-TSO, X64 | Y | N | N | N | Y |
| PSO | sparc-PSO | Y | Y | N | N | Y |
| RMO | ia64 | Y | Y | Y | Y | Y |
| PowerPC | PowerPC | Y | Y | Y | Y | Y |

在这个表格中，我们可以看到所有处理器内存模型都允许写 - 读重排序，原因在第一章以说明过：它们都使用了写缓存区，写缓存区可能导致写 - 读操作重排序。同时，我们可以看到这些处理器内存模型都允许更早读到当前处理器的写，原因同样是因为写缓存区：由于写缓存区仅对当前处理器可见，这个特性导致当前处理器可以比其他处理器先看到临时保存在自己的写缓存区中的写。

上面表格中的各种处理器内存模型，从上到下，模型由强变弱。越是追求性能的处理器，内存模型设计的会越弱。因为这些处理器希望内存模型对它们的束缚越少越好，这样它们就可以做尽可能多的优化来提高性能。

由于常见的处理器内存模型比 JMM 要弱，java 编译器在生成字节码时，会在执行指令序列的适当位置插入内存屏障来限制处理器的重排序。同时，由于各种处理器内存模型的强弱并不相同，为了在不同的处理器平台向程序员展示一个一致的内存模型，JMM 在不同的处理器中需要插入的内存屏障的数量和种类也不相同。下图展示了 JMM 在不同处理器内存模型中需要插入的内存屏障的示意图：
![JMM在不同处理器内存模型中需要插入的内存屏障](../assets/images/03-JVM/48.JMM在不同处理器内存模型中需要插入的内存屏障.png)
如上图所示，JMM 屏蔽了不同处理器内存模型的差异，它在不同的处理器平台之上为 java 程序员呈现了一个一致的内存模型。
### 7.4.2 JMM，处理器内存模型与顺序一致性内存模型之间的关系
JMM 是一个语言级的内存模型，处理器内存模型是硬件级的内存模型，顺序一致性内存模型是一个理论参考模型。下面是语言内存模型，处理器内存模型和顺序一致性内存模型的强弱对比示意图：
![JMM，处理器内存模型与顺序一致性内存模型之间的关系](../assets/images/03-JVM/49.JMM，处理器内存模型与顺序一致性内存模型之间的关系.png)
从上图我们可以看出：常见的 4 种处理器内存模型比常用的 3 中语言内存模型要弱，处理器内存模型和语言内存模型都比顺序一致性内存模型要弱。同处理器内存模型一样，越是追求执行性能的语言，内存模型设计的会越弱。
### 7.4.3 MM 的设计

从 JMM 设计者的角度来说，在设计 JMM 时，需要考虑两个关键因素：

- 程序员对内存模型的使用。程序员希望内存模型易于理解，易于编程。程序员希望基于一个强内存模型来编写代码。
- 编译器和处理器对内存模型的实现。编译器和处理器希望内存模型对它们的束缚越少越好，这样它们就可以做尽可能多的优化来提高性能。编译器和处理器希望实现一个弱内存模型。

由于这两个因素互相矛盾，所以 JSR-133 专家组在设计 JMM 时的核心目标就是找到一个好的平衡点：一方面要为程序员提供足够强的内存可见性保证；另一方面，对编译器和处理器的限制要尽可能的放松。下面让我们看看 JSR-133 是如何实现这一目标的。

为了具体说明，请看前面提到过的计算圆面积的示例代码：

```java
double pi  = 3.14;    //A
double r   = 1.0;     //B
double area = pi * r * r; //C
```
上面计算圆的面积的示例代码存在三个 happens- before 关系：

- A happens- before B；
- B happens- before C；
- A happens- before C；

由于 A happens- before B，happens- before 的定义会要求：A 操作执行的结果要对 B 可见，且 A 操作的执行顺序排在 B 操作之前。 但是从程序语义的角度来说，对 A 和 B 做重排序即不会改变程序的执行结果，也还能提高程序的执行性能（允许这种重排序减少了对编译器和处理器优化的束缚）。也就是说，上面这 3 个 happens- before 关系中，虽然 2 和 3 是必需要的，但 1 是不必要的。因此，JMM 把 happens- before 要求禁止的重排序分为了下面两类：
- 会改变程序执行结果的重排序。
- 不会改变程序执行结果的重排序。

JMM 对这两种不同性质的重排序，采取了不同的策略：

- 对于会改变程序执行结果的重排序，JMM 要求编译器和处理器必须禁止这种重排序。
- 对于不会改变程序执行结果的重排序，JMM 对编译器和处理器不作要求（JMM 允许这种重排序）。

下面是 JMM 的设计示意图：
![JMM禁止重排序](../assets/images/03-JVM/50.JMM禁止重排序.png)
从上图可以看出两点：
- JMM 向程序员提供的 happens- before 规则能满足程序员的需求。JMM 的 happens- before 规则不但简单易懂，而且也向程序员提供了足够强的内存可见性保证（有些内存可见性保证其实并不一定真实存在，比如上面的 A happens- before B）。
- JMM 对编译器和处理器的束缚已经尽可能的少。从上面的分析我们可以看出，JMM 其实是在遵循一个基本原则：只要不改变程序的执行结果（指的是单线程程序和正确同步的多线程程序），编译器和处理器怎么优化都行。比如，如果编译器经过细致的分析后，认定一个锁只会被单个线程访问，那么这个锁可以被消除。再比如，如果编译器经过细致的分析后，认定一个 volatile 变量仅仅只会被单个线程访问，那么编译器可以把这个 volatile 变量当作一个普通变量来对待。这些优化既不会改变程序的执行结果，又能提高程序的执行效率。
### 7.4.4 JMM 的内存可见性保证
Java 程序的内存可见性保证按程序类型可以分为下列三类：
- 单线程程序。单线程程序不会出现内存可见性问题。编译器，runtime 和处理器会共同确保单线程程序的执行结果与该程序在顺序一致性模型中的执行结果相同。
- 正确同步的多线程程序。正确同步的多线程程序的执行将具有顺序一致性（程序的执行结果与该程序在顺序一致性内存模型中的执行结果相同）。这是 JMM 关注的重点，JMM 通过限制编译器和处理器的重排序来为程序员提供内存可见性保证。
- 未同步 / 未正确同步的多线程程序。JMM 为它们提供了最小安全性保障：线程执行时读取到的值，要么是之前某个线程写入的值，要么是默认值（0，null，false）。
下图展示了这三类程序在 JMM 中与在顺序一致性内存模型中的执行结果的异同：
![JMM可见性](../assets/images/03-JVM/51.JMM可见性.png)
## 7.5 JSR-133 对旧内存模型的修补
JSR-133 对 JDK5 之前的旧内存模型的修补主要有两个：
- 增强 volatile 的内存语义。旧内存模型允许 volatile 变量与普通变量重排序。JSR-133 严格限制 volatile 变量与普通变量的重排序，使 volatile 的写 - 读和锁的释放 - 获取具有相同的内存语义。
- 增强 final 的内存语义。在旧内存模型中，多次读取同一个 final 变量的值可能会不相同。为此，JSR-133 为 final 增加了两个重排序规则。现在，final 具有了初始化安全性。



















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































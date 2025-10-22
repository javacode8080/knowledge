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
     LineNumberTable:
       line 3: 0
     LocalVariableTable:
       Start  Length  Slot  Name   Signature
           0       5     0  this   Lcom/rhythm7/Main;
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
### 2.2.8 类名
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


















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































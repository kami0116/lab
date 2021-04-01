### 1. hsdis

反汇编需要hsdis的支持，hsdis的源码网站kenail.com已经关闭了，openjdk和github上有源码的备份（[传送门0](http://hg.openjdk.java.net/jdk8u/jdk8u/hotspot)，[传送门1](https://github.com/drazzib/base-hsdis)，[传送门2）](https://github.com/drazzib/openjdk-hsdis)。不过不建议自己编译，在网上下载别人编译好的文件就好了。这里必须要吐槽下百度和csdn，百度上一搜全是重复的内容，各条都引到csdn，而到csdn后就需要用钱或VIP才能下载。很不理解，我们中国程序员为什么总是要走内耗路线！真就像刘强东说的呗，犹太人在一个地方开加油站赚火了，又来一个犹太人开一个餐厅，第三个开了个洗车房，几十年后一个小镇就出现了。要是中国人开了一个加油站火了，立马前面一个后面一个左面一个右面一个。

[hsdis-amd64.dll](resources/hsdis-amd64.dll)

### 2. jitwatch

> 功能介绍：https://skillsmatter.com/skillscasts/5243-chris-newland-hotspot-profiling-with-jit-watch  需要有梯子
>
> [视频资料](resources/HotSpot_Profiling_Using_JITWatch.pdf)



jvm有三种编译器，client compiler(C1), server compiler(C2), tiered compilation(C1+C2)。在java8中默认的就是tiered compilation，即C1和C2的混用。C1只是对Code进行简单的翻译，编译很快。C2在收集了更多信息后，对Code进行编译，同时对代码进行大量的优化包括（Loop unrolling, Inlining, Dead Code Elimination, Escape analysis, Intrinsics, Branch prediction）

#### 2.1 下载jitwatch

下载源码([传送门](https://github.com/AdoptOpenJDK/jitwatch/releases))，再用maven编译(mvn clean install -Dmaven.test.skip=true)，

#### 2.2 编译

添加vm参数： -server -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=target/compile.log

#### 2.3 用jitwatch看

launchUI.bat --> Open Log 选择刚才的compile.log文件 --> Start

ps:如果方法被使用次数太少是不会被编译的
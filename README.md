# JavaPerformanceTesting
**1**.Here we will be dealing with different VM arguments,Options,Tools that might be helpful to imporve performance.
<br/>
**2**.Goto cmd and type-> **jps** - it will give all the current running java processes on Windows. 
<br/>
**3**.Goto cmd and type-> **jinfo -flags $javaprocessid$**. It will list down which vm flags are at present used by jvm for that java application.
<br/>
**4**.Goto cmd and type-> **jinfo -flag $flag_name$ $javaprocessid$**. It will print current value of that flag used by jvm for that java application.
<br/> e.g. -> _jinfo -flag PrintCompilation 22704_ will print output -> _-XX:-PrintCompilation_ . It indicate that while running that application PrintCompilation option is disabled. _-XX:+PrintCompilation_ indicate that this option is enabled.
<br/>
**5**.**jinfo -flag CompileThreshold 22704** will give output -XX:CompileThreshold=10000. It means value of CompileThreshold flag is 10000. and 
 CompileThreshold indicates the threshold after that method will be natively compiled by JIT and put into code cache.
<br/>
**6**. **java -XX:+PrintCompilation mainTest** will run mainTest application with PrintCompilation enabled. <br/> PrintCompilation option gives statistics like which tier of jit compilation is used.method is synchronized or not. any exception occurred or not. natively compiled or not.
<br/>
In output - 
**n** means native method.<br/>
**s** means synchronized method<br/>
**%** means code is natively comipled and taken from code cache.<br/>
**!** means exception handling going on.<br/>
There is column value from 0-4 <br/>
0 means no compilation done (Here compilation means that JIT will compile bytecode into machine instructions) <br/>
1-3 means C1 compiler compilation happened (with three tiers 1-3) - less detailed compilation  <br/>
4 - means high level compilation done by using c2 compiler and code put into code cache. so that next time same method needed. directly machine instructions will be taken from code cache and lot of time in this way saved.Hence in such case jvm does not need to convert code into machine instructions again and again. <br/>
**7**. **java -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation mainTest** -> _UnlockDiagnosticVMOptions_ is used to activate option like _LogCompilation_ . <br/> LogCompilation is more detailed option as compare to PrintCompilation. It generate detailed statistic into the file in current directory folder.<br/>
**8**. **jmh** -> JMH (Java Microbenchmark Harness) is a framework to measure the performance of a function. <br/>
**9** **java -XX:+PrintCodeCache mainTest** -> it will give data like code cache used,max size,free space etc.<br/>
**10** **InitialCodeCacheSize ReservedCodeCacheSize CodeCacheExpansionSize** -> these three property are used to update the initial code cache size used,maximum code cache size available, and by what rate codeCache will expand from initial size to max size.<br/>
e.g. - **java -XX:ReservedCodeCacheSize=28m MainTest** -> it set max code cache size to 28MB.<br/>
**11**. **JConsole application** - It is present inside jdk bin folder and on start it ask for the process id which we want to connect.<br/>JConsole is a graphical monitoring tool to monitor Java Virtual Machine (JVM) and Java applications both on a local or remote machine<br/>
It gives java Application Memory Analysis,Thread Analysis etc. There are other free tools such as **JVisualvm** which provide some add on features also. <br/>
**12** **jmc (Java Mission Control)** is a GUI tool suite for managing, monitoring, profiling, and troubleshooting your Java applications. it provides **jfr(Flight Recording)** and **MBean**. MBean server can provide various aspects of running jvm and jfr is used to collects event.<br/>
**13** **java -XX:+PrintFlagsFinal** -> It does not require any class name with it. it will list down all the possible VM flags and there current values.<br/>
**14a** **java -XX:CompileThreshold=n MainTest** -> It set the CompileThreshold value to n. It means after n value code will start to be natively compiled. default value is 10000. if we decreased it code will be earlier natively compiled. this option is rarely used.<br/>
**14b** **java -XX:CICompilerCount=n MainTest** -> It set the CICompilerCount value to n. It means n threads will now be available to do compiling the code by jvm.<br/>
**15** java has permGem related flags which are not needed post java 8. if we still use those flags in java 8 it will be ignored. in place of permGem java use MetaSpace. permGem is fixed size memory in heap but on ther hand MetaSpace is part of native memory not part of Heap <br/>
**16** Static reference variables and class definition details are stored in **MetaSpace**<br/>
**17** before java 7 String constant pool was part of permGem Space. after 7 it is moved to Heap. <br/>
After java 8 permgem is not used. metaspace is used. <br/>
**18** **GraalVM** is a high-performance, embeddable, polyglot Virtual Machine for running applications written in JavaScript, Python, Ruby, R, JVM-based languages like Java, Scala, Kotlin, and LLVM-based languages such as C and C++.<br/>
for java it gives own javac and own jvm. it also provide option to compile code directly into native instructions( so, in such case no need of jvm, code will directly run on operating system)<br/>
from java 11 we get GraalVM option also instead of using jvm. <br/>
**19** **javap** -> it is used to give any class information and description. <br/>
e.g. 1-> javap java.lang.String<br/>
e.g. 2-> javap -v java.lang.String -> it give more detail in verbose mode of class.<br/>
**20** **java -Xms330m App** it run app with initial heap size as 300MB.<br/>
**21** **java -Xmx2048m App** it run app with maximum heap size as 2048MB.<br/>
**22** Since java provide automatic garbage collection, Memory Leaks are not possible. but if there are some object which no longer needed in code but they are reachable via some reference variable, In such case those objects will lead to Soft leak. and even after increasing heap size, problem will not get resolved as it will evenutally give outOfMemory Error. **Soft leak** - object in memory remained referenced when no longer needed.<br/>
**23** Heap dump can be generated via command line option or via jvisualVM also. Eg-> java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=filepath Main.<br/>
**24** **Eclipse Memory Analayser** is a tool which cane be used to analyse the heap dump already generated or can also connect to live running application to analyse the heap. It provide option of generating 'Leak Suspects Report' to provide info of which objects is taking how much memory.<br/>
**25** Garbage collector uses **Mark and Sweep** algorithm, Basically when jvm starts garbage collector thread to cleanup heap Memory, It will mark the references of all those objects which are rechable. and move them to the contiguos memory area and then in sweep step it will flush the entire remaning memory.when gc runs it will pause the program execution, i.e.during that time code execution will pause.<br/>
**26** **In java 8->** JVM Heap Memory is divided into 2 sections, young and old generation. When new object is created it will get created in young space. Once Young space becomes full, JVM will run gc thread. All those objects which survives gc i.e. they are being rechable from code, Will move to old space. GC that runs on Young generation takes less time. as Young space is much smaller.<br/>
**27** **In java 8->** Young generation is further divided into 3 parts-> Eden , s0 and s1. When new object created it is created in Eden space. when eden space becomes full, gc will run and all the rechable objects are marked and moved to s0 space. again as more objects created, It will be created in eden space. Once Eden is full again. now gc will run for s0 and eden. all the reachable objects are marked and moved to s1.again as more objects created, it will be created in eden space. Once Eden is full again. Now gc will run for s1 and eden. all the reachable objects are marked and moved to s0. so, Basically s0 and s1 will be used alternatively to store reachable objects.And always s0 or s1 will either be empty.<br/>
Now, when does objects goes into old generation from s1 or s0. it depends on threshold, if suppose threshold is set to 5, then it means if object survived 5 gc call, then that object will be moved to old generation.<br/>
Size of eden,s0 and s1 also changes dynamically according to application need by jvm in between execution also.We can also set initial size of eden space, old space and survivor spaces by using flags provided by jvm while running application.<br/> 
**29** To Monitor garbage collection details related to application, either jvisualVM can be used. and also it can be viewed via -verbose:gc option.<br/>
**30** **java -verbose:gc app** -> It will give details like when minor gc run, when full gc run, reason of gc run. time taken by gc, memory before and after gc run etc.<br/>
**31** **There are 3 Types of gc ->** Serial, Parallel and Mostly Concurrent</br>
- **Serial:** -XX:+UseSerialGC -> In it gc will run in single thread. hence it will take more time to perform its operation. can be used when current application needed to be run with less cpu. like if any background process, where performance is not imp, and in foreground more imp process is running.
- **Parallel:** -XX:+UseParallelGC -> In it gc will run in multi-threaded way. **This is default GC in java 8.**
- **Mostly Concurrent:** It is also multi-threaded, and it is much faster in a way that here only in mark step application is paused, But in sweep step application is resumed. 
  - **-XX:+UseConcMarkSweepGC** -> default GC in java 9.<br/>
  - **-XX:+UseG1GC** -> default GC in java 10. In it memory is little differently divided. Entire heap memory is divided into chunks. and some of the chunks are eden,s0, s1 and old space. while remaining can be normal memory. After minor gc runs for young spaces, It will re-allocate young spaces, and can create some extra eden also, or can changes say few s0 to old. And when full gc run, it will first check out of all old spaces. which ones has only garbage. and will do cleaning of them first. and it might also decide that after deletion of them no need to run gc on other old spaces at all. hence it is faster then other types of gc, As for full gc also, it is not searching entire old space in most of cases.<br/>

**32** If G1 collector is used.then we can also enable flag which will run an extra thread which check all the string objects in heap which are created using new object and has active reference varibale in stack (hence not part of string constant pool) and it will check content of such strings and if same it will make both strings variable point to same object and the other object will be eligible for garbage collection.<br/>
It is less usefull flag, As it will require extra thread to be run to be able to do such clean up work.<br/>

# JavaPerformanceTesting
**1**.Here we will be dealing with different VM arguments,Options,Tools that might be helpful to imporve performance.
<br/>
**2**.Goto cmd and type-> **jps** - it will give all the current running java processes on Windows. 
<br/>
**3**.Goto cmd and type-> **jinfo -flags $javaprocessid$**. It will list down which vm flags are at present used by jvm for that java application.
<br/>
**4**.Goto cmd and type-> **jinfo -flag $flag_name$**. It will print current value of that flag used by jvm for that java application.
<br/> e.g. -> _jinfo -flag PrintCompilation 22704_ will print output -> _-XX:-PrintCompilation_ . It indicate that while running that application PrintCompilation option is disabled. _-XX:+PrintCompilation_ indicate that this option is enabled.
<br/>
**5**.**jinfo -flag CompileThreshold 22704** will give output -XX:CompileThreshold=10000. It means value of CompileThreshold flag is 10000. and 
 CompileThreshold indicates the threshold after that method will be natively compiled by JIT and put into code cache.
<br/>
**6**. **java -XX:+PrintCompilation mainTest** will run mainTest application with PrintCompilation enabled. <br/> PrintCompilation option gives statistics like which tier of jit compilation is used.method is static or not. any exception occurred or not. natively compiled or not.
<br/>
In output - 
**n** means native method.<br/>
**s** means static method<br/>
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
It gives java Application Memory Analysis,Thread Analysis etc. There are other free tools such as JVisualvm which provide some add on features also. <br/>

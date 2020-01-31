# JavaPerformanceTesting
1.Here we will be dealing with different VM arguments,Options,Tools that might be helpful to imporve performance.
<br/>
2.Goto cmd and type-> **jps** - it will give all the current running java processes on Windows. 
<br/>
3.Goto cmd and type-> **jinfo -flags $javaprocessid$**. It will list down which vm flags are at present used by jvm for that java application.
<br/>
4.Goto cmd and type-> **jinfo -flag $flag_name$**. It will print current value of that flag used by jvm for that java application.
<br/> e.g. -> _jinfo -flag PrintCompilation 22704_ will print output -> _-XX:-PrintCompilation_ . It indicate that while running that application PrintCompilation option is disabled. _-XX:+PrintCompilation_ indicate that this option is enabled.
<br/>
5.**jinfo -flag CompileThreshold 22704** will give output -XX:CompileThreshold=10000. It means value of CompileThreshold flag is 10000. and 
 CompileThreshold indicates the threshold after that method will be natively compiled by JIT and put into code cache.
<br/>
6. **java -XX:+PrintCompilation mainTest** will run mainTest application with PrintCompilation enabled. <br/> PrintCompilation option gives statistics like which tier of jit compilation is used.method is static or not. any exception occurred or not. natively compiled or not.
<br/>
7. **jmh** -> JMH (Java Microbenchmark Harness) is a framework to measure the performance of a function.

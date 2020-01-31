# Micro-benchmarking

<br/>
1.Micro-benchmarking is the way of testing running time of method by extracting that method out from the original project and run it in a seprate project to test.
<br/>
Micro-benchmarking is not ideal way to measure performance. As same method when moved back to orignal project can impact other method performance. and might reduce overall performance.
<br/> There are tools like JMH that can do Micro-benchmarking also.
<br/>
2.Here two version of isPrime method exists. one has primitive int and less loop time. other has Integer object and run o(n)
<br/>
3.To be able to minimized the impact of external factors like native method compilation. the method has been called multiple times and CompileThreshold is set to 1000.
<br/>
4.and also after the method is compiled natively. then we try to start the timer. so that in both isPrime method we will get same native method call from code cache.
<br/>
5.to verify run this command - java -XX:+PrintCompilation -XX:CompileThreshold=1000 TestMain

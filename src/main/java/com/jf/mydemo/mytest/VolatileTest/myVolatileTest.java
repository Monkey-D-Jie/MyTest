package com.jf.mydemo.mytest.VolatileTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/2/7 0007
 * @time: 16:24
 * To change this template use File | Settings | File and Templates.
 */

/**
 * @author: wjie
 * @date: 2018/2/8 0008 11:18
 * 下述内容来自：http://www.importnew.com/18126.html
 * 大家想一下这段程序的输出结果是多少？也许有些朋友认为是10000。但是事实上运行它会发现每次运行结果都不一致，都是一个小于10000的数字。
可能有的朋友就会有疑问，不对啊，上面是对变量inc进行自增操作，
由于volatile保证了可见性，那么在每个线程中对inc自增完之后，在其他线程中都能看到修改后的值啊，所以有10个线程分别进行了1000次操作，那么最终inc的值应该是1000*10=10000。

这里面就有一个误区了，volatile关键字能保证可见性没有错，但是上面的程序错在没能保证原子性。可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。

在前面已经提到过，自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。那么就是说自增操作的三个子操作可能会分割开执行，就有可能导致下面这种情况出现：

假如某个时刻变量inc的值为10，

线程1对变量进行自增操作，线程1先读取了变量inc的原始值，然后线程1被阻塞了；

然后线程2对变量进行自增操作，线程2也去读取变量inc的原始值，由于线程1只是对变量inc进行读取操作，而没有对变量进行修改操作，所以不会导致线程2的工作内存中缓存变量inc的缓存行无效，所以线程2会直接去主存读取inc的值，发现inc的值时10，然后进行加1操作，并把11写入工作内存，最后写入主存。

然后线程1接着进行加1操作，由于已经读取了inc的值，注意此时在线程1的工作内存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的值为11，然后将11写入工作内存，最后写入主存。

那么两个线程分别进行了一次自增操作后，inc只增加了1。

解释到这里，可能有朋友会有疑问，不对啊，前面不是保证一个变量在修改volatile变量时，会让缓存行无效吗？然后其他线程去读就会读到新的值，对，这个没错。这个就是上面的happens-before规则中的volatile变量规则，但是要注意，线程1对变量进行读取操作之后，被阻塞了的话，并没有对inc值进行修改。然后虽然volatile能保证线程2对变量inc的值读取是从内存中读取的，但是线程1没有进行修改，所以线程2根本就不会看到修改的值。

根源就在这里，自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的。
 *
 */

public class myVolatileTest {
    public volatile   int inc = 0;

    public   void increase() {
        inc++;
    }
    public static void main(String[] args) throws InterruptedException {
        final myVolatileTest test = new myVolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    for(int j=0;j<1000;j++) {
                        test.increase();
                    }
                };
            }.start();
        }
        Thread.sleep(3000);
        while(Thread.activeCount()>1)  //保证前面的线程都执行完--》不注释的半天都执行不完
        {
            Thread.yield();
//            Thread.join();
        }
        System.out.println(test.inc);
    }
/**
 * @author: wjie
 * @date: 2018/2/8 0008 11:20*
 * 把上面的代码改成以下任何一种都可以达到效果：
采用synchronized：
public class Test {
public  int inc = 0;

public synchronized void increase() {
inc++;
}

public static void main(String[] args) {
final Test test = new Test();
for(int i=0;i<10;i++){
new Thread(){
public void run() {
for(int j=0;j<1000;j++)
test.increase();
};
}.start();
}

while(Thread.activeCount()>1)  //保证前面的线程都执行完
Thread.yield();
System.out.println(test.inc);
}
}
采用Lock：
public class Test {
public  int inc = 0;
Lock lock = new ReentrantLock();

public  void increase() {
lock.lock();
try {
inc++;
} finally{
lock.unlock();
}
}

public static void main(String[] args) {
final Test test = new Test();
for(int i=0;i<10;i++){
new Thread(){
public void run() {
for(int j=0;j<1000;j++)
test.increase();
};
}.start();
}

while(Thread.activeCount()>1)  //保证前面的线程都执行完
Thread.yield();
System.out.println(test.inc);
}
}
采用AtomicInteger：

public class Test {
public  AtomicInteger inc = new AtomicInteger();

public  void increase() {
inc.getAndIncrement();
}

public static void main(String[] args) {
final Test test = new Test();
for(int i=0;i<10;i++){
new Thread(){
public void run() {
for(int j=0;j<1000;j++)
test.increase();
};
}.start();
}

while(Thread.activeCount()>1)  //保证前面的线程都执行完
Thread.yield();
System.out.println(test.inc);
}
}
在java 1.5的java.util.concurrent.atomic包下提供了一些原子操作类，即对基本数据类型的 自增（加1操作），自减（减1操作）、以及加法操作（加一个数），减法操作（减一个数）进行了封装，保证这些操作是原子性操作。atomic是利用CAS来实现原子性操作的（Compare And Swap），
CAS实际上是利用处理器提供的CMPXCHG指令实现的，而处理器执行CMPXCHG指令是一个原子性操作。
 */
}


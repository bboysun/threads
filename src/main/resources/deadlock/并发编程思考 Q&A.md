# 并发编程思考 Q&A

**Q1**: 并发编程中为什么会出现死锁，或者说是什么原因导致的死锁？

**A1**: 死锁出现的原因，一般是多个线程互相争抢资源互相等待，导致永远阻塞的现象；举个栗子：比如线程A 拿到了 a 锁后同时等待 b 锁，线程 B 拿到 b 锁后同时等待 a 锁，这样两个线程 A/B 就在互相等待死锁中；

官方的说法满足死锁的四个条件：

1. 互斥性，共享资源 a, b 只能被一个线程占用；
2. 占有且等待，线程 A 在占有共享资源 a 的同时等待共享资源 b 的时候，也不释放共享资源 a;
3. 不可抢占，其他线程不能强行抢占线程 A 已经占有的共享资源 a;
4. 循环等待，线程 A 等待线程 B 占有的共享资源，线程 B 等待线程 A 占有的共享资源；

只有满足了以上四个条件，就会在并发场景下发生死锁现象；



**Q2**: 既然死锁现象是我们不想看到的，我们如何避免死锁现象呢？

**A2**: 既然我们知道了是什么原因导致的死锁，那么想要避免死锁那就需要破坏以上四个必要条件即可；

第一，互斥性应该是无法避免的，既然是既然使用了锁就是为了互斥性诞生的，所以互斥性是无法破坏的；

第二，想想如何破坏占有且等待，不难想到既然我们其实可以一次性把需要占有的共享资源锁一次性全部获取后再进行线程内部的逻辑处理即可，但该方法一般适用较少的共享资源锁的场景，一旦资源锁过多还是不太现实的；

第三，对于不可抢占，我们线程获取了共享资源 a 后再想获取共享资源 b 时无法获取后，可以一起把共享资源 a 释放掉，此时就意味着线程 A 无法正常完成预定逻辑，就需要考虑异常处理，重试机制等；

第四，避免循环等待，就可以将共享资源进行排序，按顺序获取，这样就不会有多个线程交叉占有不同的共享资源；

综上所述，避免死锁有以下几点：

1. 获取锁时带上超时时间；
2. 对于已经获取锁的资源，增加主动释放机制；
3. 放弃锁资源时，增加异常处理流程，如重试机制；
4. 需要获取多个锁资源时，可对锁资源进行排序；



**Q3**: 遇到死锁问题，我们一般要如何定位处理呢？

**A3**: 一般我们会通过 jstack 命令输出当前进程的堆栈信息，我们会看到有如下信息：

```javascript
Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x00007f819e011078 (object 0x00000007957dd440, a java.lang.Object),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x00007f819e00ed68 (object 0x00000007957dd450, a java.lang.Object),
  which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
	at deadlock.Worker.run(Worker.java:29)
	- waiting to lock <0x00000007957dd440> (a java.lang.Object)
	- locked <0x00000007957dd450> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:748)
"Thread-0":
	at deadlock.Worker.run(Worker.java:29)
	- waiting to lock <0x00000007957dd450> (a java.lang.Object)
	- locked <0x00000007957dd440> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock
```

我们可以看到当前死锁是因为 workA 和 workB 争抢共享资源时循环等待导致了死锁，永远阻塞；



**Q4**: 那我们就上面的例子要如何破掉死锁呢？

**A4**: 我们对共享锁资源进行排序，让不同的线程 worker 按顺序获取对应的锁资源，不单单避免了死锁现象，如果仅仅是加了超时时间和释放资源重试机制的话，就会有线程在不断的获取不到想要的资源而重试；所以对锁资源的排序不单单处理的死锁问题，还避免了不必要的重试资源的浪费；

```java
public SmartWorker(Object lockA, Object lockB) {
		int res = lockA.toString().compareTo(lockB.toString());
		// lockA 永远是排序大的资源，lockB 永远是排序小的资源
		this.lockA = res >= 0 ? lockA : lockB;
		this.lockB = res >= 0 ? lockB : lockA;
	}
```


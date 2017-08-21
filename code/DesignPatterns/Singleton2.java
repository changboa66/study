package com.designPatterns;

/**
 * 双重检查锁
 * double check locking
 * Created by chang on 17/8/21.
 */
public class Singleton2 {
    //方法写成private,防止被外部实例化
    private Singleton2() {}
    //实例化对象时并不是一个原子操作,声明为volatile禁止指令重排序
    //volatile变量的写操作都先行发生于后面对这个变量的读操作
    private volatile static Singleton2 singleton2;
    //得到实例的入口方法
    public static Singleton2 getSingleton2() {
        //第一个if是为了减少不必要的同步带来的性能开销
        //保证高效
        if (null==singleton2) {
            synchronized (Singleton2.class) {
                //第二个if保证两个线程同时进去第一个if后,
                //只产生一个Singleton2的实例
                //即:
                //如果有thread1,thread2同时都进入了第一个if块,
                //只有一个线程能进入synchronzied块,若thread1进入synchronized块,
                //thread1第二个if发现instance==null,则new Singleton(),
                //thread1时间戳到了结束.
                //thread2进入synchronized块,第二个if时发现instance!=null,
                //则直接退出后return instance
                if (null==singleton2) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}

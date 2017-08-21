package com.designPatterns;

/**
 * 饿汉式
 * 在第一次加载类到内存中时就会初始化
 * JVM本身机制保证了线程安全问题
 * Created by chang on 17/8/21.
 */
public class Singleton3 {
    //在类第一次加载到内存中时就会初始化,
    //缺点是它不是一种懒加载模式（lazy initialization），
    //单例会在加载类后一开始就被初始化，即使客户端没有调用 getSingleton3()方法。
    private static final Singleton3 singleton3 = new Singleton3();
    private Singleton3(){}
    public static Singleton3 getSingleton3() {
        return singleton3;
    }
}

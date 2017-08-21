package com.designPatterns;

/**
 * 静态内部类(懒汉式)
 * JVM本身机制保证了线程安全问题
 * Created by chang on 17/8/21.
 */

//1.不用synchronized,所以可以保证性能
//2.只有调用getSingleton4()方法时才会创建对象所以是懒汉式的
public class Singleton4 {
    private Singleton4(){}
    private static class Singleton4Holder {
        private static final Singleton4 singleton4 = new Singleton4();
    }
    public static Singleton4 getSingleton4() {
        return Singleton4Holder.singleton4;
    }
}

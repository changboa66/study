package com.designPatterns;

/**
 * 懒汉式
 * synchronized同步整个方法,不高效
 * Created by chang on 17/8/21.
 */
public class Singleton1 {
    //方法写成private,防止被外部实例化
    private Singleton1() {}
    //单例的引用
    private static Singleton1 singleton1;
    //得到Singleton1实例的唯一入口
    //synchronized同步整个方法,不高效
    public static synchronized Singleton1 getSingleton1() {
        if (null==singleton1) {
            singleton1 = new Singleton1();
        }
        return singleton1;
    }
}

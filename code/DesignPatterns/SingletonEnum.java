package com.designPatterns;

/**
 * 枚举单例
 * Created by chang on 17/8/21.
 */
public enum SingletonEnum {

    //public static final SingletonEnum INSTANCE;(javap -c可以看到)
    //说明INSTANCE是SingletonEnum的一个实例
    //static final保证实例只能被实例化一次
    INSTANCE;
    private Resource resource;
    //枚举类本身就是private
    //访问枚举实例INSTANCE时会调用构造方法(SingletonEnum.INSTANCE)
    SingletonEnum(){
        resource = new Resource();
    }

    //实例方法通过SingletonEnum的枚举实例INSTANCE调用(INSTANCE.getInstance())
    public Resource getInstance() {
        return resource;
    }

    //类Resource是我们要应用单例模式的资源，
    //具体可以表现为网络连接，数据库连接，线程池等等
    private class Resource {}

    public static void main(String[] args) {
        //获取单例
        Resource resource1 = SingletonEnum.INSTANCE.getInstance();
    }
}



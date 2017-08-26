package com.designPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式:
 *   客户端不想或不能直接访问一个对象，此时可以通过一个称之为“代理”的第三者来
 *   实现间接访问，该方案对应的设计模式被称为代理模式。
 * 静态代理模式:
 *   由程序员创建或特定工具自动生成源代码,再对其编译。在程序运行前,
 *   代理类的.class文件就已经存在了。客户端面向接口编程，符合开闭原则
 * 动态代理:
 *   在程序运行时，运用JAVA反射机制动态创建代理实例。
 * (1) Subject（抽象主题角色）：它声明了真实主题和代理主题的共同接口，这样一来
 *     在任何使用真实主题的地方都可以使用代理主题，客户端通常需要针对抽象主题角色进行编程。
 * (2) Proxy（代理主题角色）：它包含了对真实主题的引用，从而可以在任何时候操作真实主题对象；
 *     在代理主题角色中提供一个与真实主题角色相同的接口，以便在任何时候都可以替代真实主题；
 *     代理主题角色还可以控制对真实主题的使用，负责在需要的时候创建和删除真实主题对象，
 *     并对真实主题对象的使用加以约束。通常，在代理主题角色中，客户端在调用所引用的真实
 *     主题操作之前或之后还需要执行其他操作，而不仅仅是单纯调用真实主题对象中的操作。
 * (3) RealSubject（真实主题角色）：它定义了代理角色所代表的真实对象，在真实主题角色中
 *     实现了真实的业务操作，客户端可以通过代理主题角色间接调用真实主题角色中定义的操作。
 *
 * 使用反射的一个最大的弊端是性能比较差。相同的操作，用反射API所需的时间大概比直接的使用要慢一两个数量级。
 * Created by chang on 17/8/26.
 */
public class DynamicProxyPattern {
    public static void main(String[] args) {
        //new被代理对象
        Subject subject = new RealSubject();
        LogInterceptor logInterceptor = new LogInterceptor();
        //将被代理对象交给代理
        logInterceptor.object = subject;
        //Proxy.newProxyInstance产生代理对象
        // 参数说明：
        // ClassLoader loader：类加载器；
        // Class<?>[] interfaces：得到全部的接口；
        // InvocationHandler h：得到InvocationHandler接口的子类实例 ；
        Subject proxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), logInterceptor);
        proxy.visit();
    }
}

//被代理的类的接口
interface Subject {
    void visit();
}

//接口的实现
class RealSubject implements Subject {
    @Override
    public void visit() {
        System.out.println("访问Real Subject!!");
    }
}

//访问真实对象前后加一些日志操作
class LogInterceptor implements InvocationHandler {

    //需要添加逻辑的对象
    Object object;

    //在访问对象的方法前后添加日志操作
    void log(String beforeOrAfter, Method method) {
        System.out.println(beforeOrAfter + "访问" + method.getName());
    }

    // 参数说明:
    // Object proxy：指被代理的对象；
    // Method method：被代理的对象的要调用的方法；
    // Object[] args：方法调用时所需要的参数；
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        log("Before", method);
        method.invoke(object, args);
        log("After", method);
        return null;
    }
}
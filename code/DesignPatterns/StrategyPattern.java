package com.designPatterns;

/**
 * 策略模式
 * 在策略模式中，我们可以定义一些独立的类来封装不同的算法，
 * 每一个类封装一种具体的算法，在这里，每一个封装算法的类我们都可以称之为一种策略(Strategy)，
 * 为了保证这些策略在使用时具有一致性，一般会提供一个抽象的策略类来做规则的定义，
 * 而每种算法则对应于一个具体策略类。
 * 策略模式的主要目的是将算法的定义与使用分开。
 * ● Context（环境类）：环境类是使用算法的角色，它在解决某个问题（即实现某个方法）时
 *   可以采用多种策略。在环境类中维持一个对抽象策略类的引用实例，用于定义所采用的策略。
 * ● Strategy（抽象策略类）：它为所支持的算法声明了抽象方法，是所有策略类的父类，
 *   它可以是抽象类或具体类，也可以是接口。环境类通过抽象策略类中声明的方法在运行时
 *   调用具体策略类中实现的算法。
 * ● ConcreteStrategy（具体策略类）：它实现了在抽象策略类中声明的算法，在运行时，
 *   具体策略类将覆盖在环境类中定义的抽象策略类对象，使用一种具体的算法实现某个业务处理。
 * 策略模式的重心不是如何实现算法，而是如何组织、调用这些算法
 * Created by chang on 17/8/26.
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Strategy strategy = new StudentStrategy();
        Context context = new Context(strategy);
        System.out.println(context.calculate(100));
    }
}

//使用策略类的类
class Context {

    //持有对策略类对象的引用
    private Strategy strategy;
    Context(Strategy strategy) {
        this.strategy = strategy;
    }
    double calculate (double price) {
        return strategy.calculate(price);
    }
}

//抽象策略类
interface Strategy {
    double calculate(double price);
}

//策略实现类1-学生的打折方案
class StudentStrategy implements Strategy {
    //学生可享受票价8折优惠；
    @Override
    public double calculate(double price) {
        return 0.8 * price;
    }
}

//策略实现类2-贵宾的打折方案
class VIPStrategy implements Strategy {
    //影院VIP用户除享受票价半价优惠外还可进行积分
    @Override
    public double calculate(double price) {
        return 0.5 * price;
    }
}

//策略实现类3-儿童的打折方案
class ChildrenStrategy implements Strategy {
    //儿童可享受每张票减免10元的优惠（原始票价需大于等于20元）
    @Override
    public double calculate(double price) {
        return price>20?(price-10):price;
    }
}


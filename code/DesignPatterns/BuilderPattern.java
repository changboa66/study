package com.designPatterns;

/**
 * 建造者模式
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * ● Builder（抽象建造者）：它为创建一个产品Product对象的各个部件指定抽象接口，
 *     在该接口中一般声明两类方法，一类方法是buildPartX()，它们用于创建复杂对象的各个部件；
 *     另一类方法是getResult()，它们用于返回复杂对象。Builder既可以是抽象类，也可以是接口。
 * ● ConcreteBuilder（具体建造者）：它实现了Builder接口，实现各个部件的具体构造和装配方法，
 *     定义并明确它所创建的复杂对象，也可以提供一个方法返回创建好的复杂产品对象。
 * ● Product（产品角色）：它是被构建的复杂对象，包含多个组成部件，
 *     具体建造者创建该产品的内部表示并定义它的装配过程。
 * ● Director（指挥者）：指挥者又称为导演类，它负责安排复杂对象的建造次序，
 *     指挥者与抽象建造者之间存在关联关系，可以在其construct()建造方法中调用建造者对象的部件构造与装配方法，
 *     完成复杂对象的建造。客户端一般只需要与指挥者进行交互，在客户端确定具体建造者的类型，
 *     并实例化具体建造者对象（也可以通过配置文件和反射机制），然后通过指挥者类的构造函数或者Setter方法
 *     将该对象传入指挥者类中。
 * Director充当指挥者，Builder充当抽象建造者，YujiBuilder
 * 和YaseBuilder充当具体建造者，Actor充当复杂产品。
 * Created by chang on 17/8/25.
 */
public class BuilderPattern {
    public static void main(String[] args) {
        Builder builder = new YujiBuilder();
        Actor actor = Director.actor(builder);
        System.out.println();
    }
}

//指挥者
class Director {

    //产品构建与组装方法
    static Actor actor(Builder builder) {
        builder.buildAct();
        builder.buildAge();
        builder.buildName();
        return builder.getActor();
    }
}

//产品
class Actor {
    //部件
    String name;
    int age;
    String act;
}

//抽象建造者
abstract class Builder {

    //持有需要构建的产品对象
    Actor actor = new Actor();
    abstract void buildName();
    abstract void buildAge();
    abstract void buildAct();
    //返回产品对象
    Actor getActor() {
        return actor;
    }

}

//具体建造者1-虞姬
class YujiBuilder extends Builder{
    @Override
    void buildName() {
        actor.name = "虞姬";
    }

    @Override
    void buildAge() {
        actor.age = 28;
    }

    @Override
    void buildAct() {
        actor.act = "射手";
    }
}

//具体建造者2-亚瑟
class YaseBuilder extends Builder{
    @Override
    void buildName() {
        actor.name = "亚瑟";
    }

    @Override
    void buildAge() {
        actor.age = 34;
    }

    @Override
    void buildAct() {
        actor.act = "坦克";
    }
}

/**
 * 建造者模式与抽象工厂模式有点相似，但是建造者模式返回一个完整的复杂产品，
 * 而抽象工厂模式返回一系列相关的产品；在抽象工厂模式中，客户端通过选择具体
 * 工厂来生成所需对象，而在建造者模式中，客户端通过指定具体建造者类型并
 * 指导Director类如何去生成对象，侧重于一步步构造一个复杂对象，然后将结果返回。
 * 如果将抽象工厂模式看成一个汽车配件生产厂，生成不同类型的汽车配件，那么建造者模式
 * 就是一个汽车组装厂，通过对配件进行组装返回一辆完整的汽车。
 */

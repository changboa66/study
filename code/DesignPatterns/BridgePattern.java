package com.designPatterns;

/**
 * 桥接模式
 * https://quanke.gitbooks.io/design-pattern-java/处理多维度变化——桥接模式（二）.html
 **桥接模式是一种很实用的结构型设计模式，如果软件系统中某个类存在两个独立变化的维度，
 * 通过该模式可以将这两个维度分离出来，使两者可以独立扩展，让系统更加符合“单一职责原则”。
 * 与多层继承方案不同，它将两个独立变化的维度设计为两个独立的继承等级结构，
 * 并且在抽象层建立一个抽象关联，该关联关系类似一条连接两个独立继承结构的桥，故名桥接模式。
 **桥接模式用一种巧妙的方式处理多层继承存在的问题，用抽象关联取代了传统的多层继承，
 * 将类之间的静态继承关系转换为动态的对象组合关系，使得系统更加灵活，并易于扩展，
 * 同时有效控制了系统中类的个数。桥接定义如下：
 **桥接模式(Bridge Pattern)：将抽象部分与它的实现部分分离，使它们都可以独立地变化。
 * 它是一种对象结构型模式，又称为柄体(Handle and Body)模式或接口(Interface)模式。
 *---------------------------------------------------------------------------
 * ● Abstraction（抽象类）：用于定义抽象类的接口，它一般是抽象类而不是接口，
 *   其中定义了一个Implementor（实现类接口）类型的对象并可以维护该对象，
 *   它与Implementor之间具有关联关系，它既可以包含抽象业务方法，也可以包含具体业务方法。
 * ● RefinedAbstraction（扩充抽象类）：扩充由Abstraction定义的接口，通常情况下
 *   它不再是抽象类而是具体类，它实现了在Abstraction中声明的抽象业务方法，
 *   在RefinedAbstraction中可以调用在Implementor中定义的业务方法。
 * ● Implementor（实现类接口）：定义实现类的接口. 一般而言，Implementor接口仅提供基本操作，
 *   而Abstraction定义的接口可能会做更多更复杂的操作。Implementor接口对这些基本操作进行了声明，
 *   而具体实现交给其子类。通过关联关系，在Abstraction中不仅拥有自己的方法，
 *   还可以调用到Implementor中定义的方法，使用关联关系来替代继承关系。
 * ● ConcreteImplementor（具体实现类）：具体实现Implementor接口，在不同
 *   基本操作的不同实现，在程序运行时，ConcreteImplementor对象将替换其父类对象，
 *   提供给抽象类具体的业务操作方法。
 *--------------------------------------------------------------------------
 * 桥接模式是一个非常有用的模式，在桥接模式中体现了很多面向对象设计原则的思想，
 * 包括“单一职责原则”、“开闭原则”、“合成复用原则”、“里氏代换原则”、“依赖倒转原则”等。
 * 熟悉桥接模式有助于我们深入理解这些设计原则，也有助于我们形成正确的设计思想和培养良好的设计风格。
 * 在使用桥接模式时，我们首先应该识别出一个类所具有的两个独立变化的维度，将它们设计为两个独立的继承等级结构，
 * 为两个维度都提供抽象层，并建立抽象耦合。通常情况下，我们将具有两个独立变化维度的类的一些普通业务方法
 * 和与之关系最密切的维度设计为“抽象类”层次结构（抽象部分Abstraction），
 * 而将另一个维度设计为“实现类”层次结构（实现部分Implementor）。
 * 例如：对于毛笔而言，由于型号是其固有的维度，因此可以设计一个抽象的毛笔类，在该类中声明并部分实现毛笔的业务方法，
 * 而将各种型号的毛笔作为其子类；
 * 颜色是毛笔的另一个维度，由于它与毛笔之间存在一种“设置”的关系，因此我们可以提供一个抽象的颜色接口，
 * 而将具体的颜色作为实现该接口的子类。在此，型号可认为是毛笔的抽象部分，而颜色是毛笔的实现部分
 * Created by chang on 17/8/27.
 */
public class BridgePattern {
    public static void main(String[] args) {
        Color color = new Blue();
        Brush brush = new SmallBrush();
        brush.color = color;
        brush.paint("monkey");
    }
}

//毛笔的第一个维度-抽象部分
abstract class Brush {
    //抽象类持有对第二个维度类的引用(重要)
    Color color;
    abstract void paint(String view);

}

//第一个维度的子类-小号毛笔
class SmallBrush extends Brush{

    @Override
    void paint(String view) {
        System.out.println("Use small Brush panit : " + view);
        color.coloration();
    }
}

//第一个维度的子类-中号毛笔
class MiddleBrush extends Brush{

    @Override
    void paint(String view) {
        System.out.println("Use small Brush panit : " + view);
        color.coloration();
    }
}

//第一个维度的子类-大号毛笔
class BigBrush extends Brush{

    @Override
    void paint(String view) {
        System.out.println("Use small Brush panit : " + view);
        color.coloration();
    }
}

//毛笔的第二个维度颜色-实现部分
interface Color {
    void coloration();
}

//毛笔的第二个维度颜色红色-实现部分的实现
class Red implements Color {
    @Override
    public void coloration() {
        System.out.println("with Red");
    }
}

//毛笔的第二个维度颜色黄色-实现部分的实现
class Yellow implements Color {
    @Override
    public void coloration() {
        System.out.println("with Yellow");
    }
}

//毛笔的第二个维度颜色蓝色-实现部分的实现
class Blue implements Color {
    @Override
    public void coloration() {
        System.out.println("with Blue");
    }
}

/**
 * 适配器模式与桥接模式的联用
 * 1.在软件开发中，适配器模式通常可以与桥接模式联合使用。
 *   适配器模式可以解决两个已有接口间不兼容问题，在这种情况下被适配的类
 *   往往是一个黑盒子，有时候我们不想也不能改变这个被适配的类，也不能控制其扩展。
 *   适配器模式通常用于现有系统与第三方产品功能的集成，采用增加适配器的方式
 *   将第三方类集成到系统中。桥接模式则不同，用户可以通过接口继承或类继承的方式来对系统进行扩展。
 * 2.桥接模式和适配器模式用于设计的不同阶段，桥接模式用于系统的初步设计，
 *   对于存在两个独立变化维度的类可以将其分为抽象化和实现化两个角色，使它们可以分别进行变化；
 *   而在初步设计完成之后，当发现系统与已有类无法协同工作时，可以采用适配器模式。
 *   但有时候在设计初期也需要考虑适配器模式，特别是那些涉及到大量第三方应用接口的情况。
 */


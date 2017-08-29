package com.designPatterns;

/**
 * 装饰模式
 *   装饰模式可以在不改变一个对象本身功能的基础上给对象增加额外的新行为，在现实生活中，
 *   这种情况也到处存在，例如一张照片，我们可以不改变照片本身，给它增加一个相框，
 *   使得它具有防潮的功能，而且用户可以根据需要给它增加不同类型的相框，
 *   甚至可以在一个小相框的外面再套一个大相框。
 *   装饰模式是一种用于替代继承的技术，它通过一种无须定义子类的方式来给对象动态增加职责，
 *   使用对象之间的关联关系取代类之间的继承关系。在装饰模式中引入了装饰类，
 *   在装饰类中既可以调用待装饰的原有类的方法，还可以增加新的方法，以扩充原有类的功能。
 *
 * ● Component（抽象构件）：它是具体构件和抽象装饰类的共同父类，声明了在具体构件中实现的业务方法，
 *   它的引入可以使客户端以一致的方式处理未被装饰的对象以及装饰之后的对象，实现客户端的透明操作。
 * ● ConcreteComponent（具体构件）：它是抽象构件类的子类，用于定义具体的构件对象，
 *   实现了在抽象构件中声明的方法，装饰器可以给它增加额外的职责（方法）。
 * ● Decorator（抽象装饰类）：它也是抽象构件类的子类，用于给具体构件增加职责，
 *   但是具体职责在其子类中实现。它维护一个指向抽象构件对象的引用，
 *   通过该引用可以调用装饰之前构件对象的方法，并通过其子类扩展该方法，以达到装饰的目的。
 * ● ConcreteDecorator（具体装饰类）：它是抽象装饰类的子类，负责向构件添加新的职责。
 *   每一个具体装饰类都定义了一些新的行为，它可以调用在抽象装饰类中定义的方法，
 *   并可以增加新的方法用以扩充对象的行为。
 * Created by chang on 17/8/29.
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Component component = new Window();
        Decorator decorator = new ScrollDecorator(component);
        Decorator decorator1 = new BlackDecorator(decorator);
        decorator1.display();
    }
}

//抽象构件
abstract class Component {
    abstract void display();
}

//具体构件继承抽象构件-窗口
class Window extends Component {

    @Override
    void display() {
        System.out.println("显示窗体");
    }
}

//具体构件继承抽象构件-文本框
class TextBox extends Component {
    @Override
    void display() {
        System.out.println("显示文本框");
    }
}

//具体构件继承抽象构件-列表框
class ListBox extends Component {
    @Override
    void display() {
        System.out.println("显示列表框");
    }
}

//抽象装饰类继承抽象构件
class Decorator extends Component {

    //关键:持有抽象构件的引用
    Component component;
    // 关键:抽象装饰类的构造方法,
    // 可以不断传进去自己的子类进行装饰
    Decorator (Component component) {
        this.component = component;
    }

    @Override
    void display() {
        //关键:调用的是具体构件或者自己的子类的方法
        component.display();
    }
}

//具体装饰类继承抽象装饰类-滚动条
class ScrollDecorator extends Decorator {

    ScrollDecorator(Component component) {
        //调用父类的构造方法
        super(component);
    }

    @Override
    void display() {
        //设置自身的装饰
        this.setScroll();
        //调用父类的display()方法,而父类的display()方法又调用了引用的display()
        //即:可以调用父类的子类的display()也可以调用具体构件的display()
        super.display();
    }

    void setScroll() {
        System.out.println("加滚动条");
    }
}

//具体装饰类继承抽象装饰类-黑色边框
class BlackDecorator extends Decorator {
    BlackDecorator(Component component) {
        //调用父类的构造方法
        super(component);
    }

    @Override
    void display() {
        //设置自身的装饰
        this.setBlack();
        //调用父类的display()方法,而父类的display()方法又调用了引用的display()
        //即:可以调用父类的子类的display()也可以调用具体构件的display()
        super.display();
    }

    void setBlack() {
        System.out.println("加黑色边框");
    }
}



package com.designPatterns;

/**
 * 原型模式(浅克隆)
 * 浅克隆(ShallowClone)和深克隆(DeepClone)。在Java语言中，数据类型分为值类型（基本数据类型）
 * 和引用类型，值类型包括int、double、byte、boolean、char等简单数据类型，
 * 引用类型包括类、接口、数组等复杂类型。浅克隆和深克隆的主要区别在于是否支持引用类型的成员变量的复制.
 * 在浅克隆中，如果原型对象的成员变量是值类型，将复制一份给克隆对象；如果原型对象的成员变量是引用类型，
 * 则将引用对象的地址复制一份给克隆对象，也就是说原型对象和克隆对象的成员变量指向相同的内存地址。
 * 在原型模式结构图中包含如下几个角色：
 * ● Prototype（抽象原型类）(此例为Object)：它是声明克隆方法的接口，是所有具体原型类的公共父类，
 *    可以是抽象类也可以是接口，甚至还可以是具体实现类。
 * ● ConcretePrototype（具体原型类）(此例为ShallowClone)：它实现在抽象原型类中声明的克隆方法，在克隆方法中返回自己的一个克隆对象。
 * ● Client（客户类）(此例为ShallowClone)：让一个原型对象克隆自身从而创建一个新的对象，在客户类中只需要直接实例化或通过工厂方法等
 *    方式创建一个原型对象，再通过调用该对象的克隆方法即可得到多个相同的对象。由于客户类针对抽象原型类Prototype编程，
 *    因此用户可以根据需要选择具体原型类，系统具有较好的可扩展性，增加或更换具体原型类都很方便。
 * Created by chang on 17/8/24.
 */
public class ShallowClone implements Cloneable {

    public static void main(String[] args) {
        ShallowClone shallowClone1 = new ShallowClone();
        shallowClone1.age = 18;
        shallowClone1.name = "chang";
        ToClone toClone = new ToClone();
        toClone.param1 = 20;
        shallowClone1.toClone = toClone;
        ShallowClone shallowClone2 = shallowClone1.clone();
        System.out.println(shallowClone1==shallowClone2);  //false : 两个对象不相同
        System.out.println(shallowClone1.toClone==shallowClone2.toClone);  //true : 两个对象的引用地址相同
    }

    String name;
    int age;
    ToClone toClone;



    //使用clone()方法实现浅克隆
    public ShallowClone clone() {
        Object obj = null;
        try
        {
            obj = super.clone();
            return (ShallowClone)obj;
        }
        catch(CloneNotSupportedException  e)
        {
            System.out.println("不支持复制！");
            return null;
        }
    }

}


class ToClone {
    int param1;
    String param2;
}


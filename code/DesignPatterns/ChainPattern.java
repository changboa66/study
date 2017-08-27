package com.designPatterns;

/**
 * 职责链模式
 * SCM系统中的采购单审批，主任、副董事长、董事长和董事会都可以处理采购单，
 * 他们可以构成一条处理采购单的链式结构，采购单沿着这条链进行传递，这条链就称为职责链。
 * 职责链可以是一条直线、一个环或者一个树形结构，最常见的职责链是直线型，
 * 即沿着一条单向的链来传递请求。链上的每一个对象都是请求处理者，
 * 职责链模式可以将请求的处理者组织成一条链，并让请求沿着链传递，由链上的处理者对请求进行相应的处理，
 * 客户端无须关心请求的处理细节以及请求的传递，只需将请求发送到链上即可，实现请求发送者和请求处理者解耦。
 * -------------------------------------------------------------------------------------
 * ● Handler（抽象处理者）：它定义了一个处理请求的接口，一般设计为抽象类，由于不同的具体
 *   处理者处理请求的方式不同，因此在其中定义了抽象请求处理方法。因为每一个处理者的下家
 *   还是一个处理者，因此在抽象处理者中定义了一个抽象处理者类型的对象（如结构图中的successor），
 *   作为其对下家的引用。通过该引用，处理者可以连成一条链。
 * ● ConcreteHandler（具体处理者）：它是抽象处理者的子类，可以处理用户请求，在具体处理者类中
 *   实现了抽象处理者中定义的抽象请求处理方法，在处理请求之前需要进行判断，看是否有相应的处理权限，
 *   如果可以处理请求就处理它，否则将请求转发给后继者；在具体处理者中可以访问链中下一个对象，以便请求的转发。
 * Created by chang on 17/8/27.
 */
public class ChainPattern {
    public static void main(String[] args) {
        Handler lowHandler = new LowHandler();
        Handler middleHandler = new MiddleHandler();
        Handler highHandler = new HighHandler();
        lowHandler.successor = middleHandler;
        middleHandler.successor = highHandler;

        lowHandler.handle(9);
    }
}

abstract class Handler {

    //持有后继者对象的引用
    Handler successor;
    abstract void handle(int money);
}

//小领导审批
class LowHandler extends Handler {
    //持有后继者对象
    Handler successor = new MiddleHandler();
    @Override
    void handle(int money) {
        if (money<10) {
            System.out.println("低级别处理!");
        } else {
            successor.handle(money);
        }
    }
}

//中等级别领导审批
class MiddleHandler extends Handler {
    //持有后继者对象
    Handler successor = new HighHandler();
    @Override
    void handle(int money) {
        if (money>=10 && money<100) {
            System.out.println("中等级别处理!!");
        } else {
            successor.handle(money);
        }
    }
}

//高层审批
class HighHandler extends Handler {
    @Override
    void handle(int money) {
        if (money>=100) {
            System.out.println("高级别处理!!!");
        }
    }
}



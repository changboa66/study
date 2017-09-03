package com.designPatterns;

/**
 * 外观模式
 * 在软件开发中，有时候为了完成一项较为复杂的功能，一个客户类需要和多个业务类交互，
 * 而这些需要交互的业务类经常会作为一个整体出现，由于涉及到的类比较多，导致使用时代码较为复杂，
 * 此时，特别需要一个类似服务员一样的角色，由它来负责和多个业务类进行交互，
 * 而客户类只需与该类交互。外观模式通过引入一个新的外观类(Facade)来实现该功能，
 * 外观类充当了软件系统中的“服务员”，它为多个业务类的调用提供了一个统一的入口，
 * 简化了类与类之间的交互。在外观模式中，那些需要交互的业务类被称为子系统(Subsystem)。
 * 如果没有外观类，那么每个客户类需要和多个子系统之间进行复杂的交互，系统的耦合度将很大。
 * 外观模式是迪米特法则的一种具体实现，通过引入一个新的外观角色可以降低原有系统的复杂度，
 * 同时降低客户类与子系统的耦合度。
 * Created by chang on 17/9/3.
 */
public class FacadePattern {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.startup();
        facade.shutdown();
    }
}

//外观类
class Facade {
    final CPU cpu;
    final Memory memory;
    final Disk disk;
    Facade() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }
    void startup() {
        cpu.startup();
        memory.startup();
        disk.startup();
    }
    void shutdown() {
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
    }
}

//子系统-cpu
class CPU {
    void startup() {
        System.out.println("CPU启动!");
    }
    void shutdown() {
        System.out.println("CPU停止运行!");
    }
}

//子系统-内存
class Memory {
    void startup() {
        System.out.println("内存启动!");
    }
    void shutdown() {
        System.out.println("内存停止运行!");
    }
}

//子系统-硬盘
class Disk {
    void startup() {
        System.out.println("硬盘启动!");
    }
    void shutdown() {
        System.out.println("硬盘停止运行!");
    }
}
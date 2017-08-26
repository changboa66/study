package com.designPatterns;

/**
 * 适配器模式
 * https://quanke.gitbooks.io/design-pattern-java/不兼容结构的协调——适配器模式（一）.html
 * ● Target（目标抽象类）：目标抽象类定义客户所需接口，可以是一个抽象类或接口，也可以是具体类。
 * ● Adapter（适配器类）：适配器可以调用另一个接口，作为一个转换器，对Adaptee和Target进行适配，
 *   适配器类是适配器模式的核心，在对象适配器中，它通过继承Target并关联一个Adaptee对象使二者产生联系。
 * ● Adaptee（适配者类）：适配者即被适配的角色，它定义了一个已经存在的接口，这个接口需要适配，
 *   适配者类一般是一个具体类，包含了客户希望使用的业务方法，在某些情况下可能没有适配者类的源代码。
 * -------------------------------------------------------------------------------------
 *   由于某些原因，开发人员已经找不到该算法库(QuickSort,BinarySearch)的源代码，
 *   无法直接通过复制和粘贴操作来重用其中的代码；部分开发人员已经针对Target接口编程，
 *   如果再要求对该接口进行修改或要求大家直接使用QuickSort类和BinarySearch类
 *   将导致大量代码需要修改, 不符合开闭原则,所以用适配器模式解决问题.
 * Created by chang on 17/8/26.
 */
public class AdapterPattern {
    public static void main(String[] args) {
        Target target = new OperationAdapter();
        target.sort(list);
        target.search(list, 64);
    }
}

//适配器类(继承现有Target并持有无源码类的对象)
class OperationAdapter extends Target{

    //持有适配者的对象
    QuickSort quickSort = new QuickSortImpl();
    BinarySearch binarySearch = new BinarySearchImpl();

    @Override
    int[] sort(int[] list) {
        return quickSort.sort(list);
    }

    @Override
    int search(int[] list, int key) {
        return binarySearch.search(list, key);
    }
}

abstract class Target {
    abstract int[] sort(int[] list);

    abstract int search(int[] list, int key);
}

//快速排序算法
interface QuickSort {
    int[] sort(int[] list);
}

//二分查找算法
interface BinarySearch {
    int search(int[] list, int key);
}


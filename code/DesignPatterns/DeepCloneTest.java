package com.designPatterns;

import java.io.*;

/**
 * 原型模式(深克隆)
 * 对象的成员变量不管是值类型还是引用类型，都将复制一份给克隆对象.
 * 如果需要实现深克隆，可以通过序列化(Serialization)等方式来实现。
 * 序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个拷贝，
 * 而原对象仍然存在于内存中。通过序列化实现的拷贝不仅可以复制对象本身，
 * 而且可以复制其引用的成员对象，因此通过序列化将对象写到一个流中，再从流里将其读出来，
 * 可以实现深克隆。需要注意的是能够实现序列化的对象其类必须实现Serializable接口，
 * 否则无法实现序列化操作。下面我们使用深克隆技术来实现工作周报和附件对象的复制，
 * 由于要将附件对象和工作周报对象都写入流中，因此两个类均需要实现Serializable接口
 * Created by chang on 17/8/25.
 */
public class DeepCloneTest {
    public static void main(String[] args) throws Exception{
        Attachment attachment = new Attachment();
        attachment.name = "附件1";
        WeeklyLog weeklyLog = new WeeklyLog();
        weeklyLog.attachment = attachment;
        weeklyLog.data = 20110909;
        weeklyLog.name = "星期一";
        WeeklyLog clone = weeklyLog.deepClone();
        System.out.println(clone==weeklyLog);  //false
        System.out.println(clone.attachment==weeklyLog.attachment);  //false
    }
}

//周报
class WeeklyLog implements Serializable {
    String name;
    int data;
    //附件
    Attachment attachment;

    //深克隆,把需要克隆的对象写入流, 再从流中读出
    WeeklyLog deepClone() throws Exception{
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        //将对象从流中取出
        ByteArrayInputStream bis=new  ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois=new  ObjectInputStream(bis);
        return  (WeeklyLog)ois.readObject();
    }
}

//附件
class Attachment implements Serializable {
    String name;
}



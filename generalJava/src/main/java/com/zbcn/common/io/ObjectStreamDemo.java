package com.zbcn.common.io;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.io.*;

/**
 *  对象流
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/29 10:20
 */
public class ObjectStreamDemo {

    public static void main(String[] args) {
        //serialization();
        deSerialization();
    }

    /**
     * 序列化:把对象的内容写到本地存储的文件中
     */
    public static void serialization(){
        FileOutputStream fos = null;
        BufferedOutputStream bfs = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("D:/javaTest/objectTest.txt");
            bfs = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bfs);
            oos.writeObject(new Person("张三",23,"12345678"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 反序列化
     */
    public static void deSerialization(){
        FileInputStream fis = null;
        BufferedInputStream bfs = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("D:/javaTest/objectTest.txt");
            bfs = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bfs);
            Object object = ois.readObject();
            if(object instanceof Person){
                System.out.println(JSON.toJSONString(object));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Person implements Serializable{
    private static final long serialVersionUID = -6713165751656730620L;
    private String name;
    private int age;
    /**
     * 身份证号:transient 修饰，反序列化不会显示
     */
    private transient String idCard;
}

package classloader;

/**
 * @Author: darrylsun
 * @Description: 一个 person 类
 * @Date: 2023/07/23
 */
public class Person {

    static {
        System.out.println("person static block");
    }

    public Person(String name) {
        System.out.println("person name is " + name);
    }

}

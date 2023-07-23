package classloader;

/**
 * @Author: darrylsun
 * @Description: 一个测试子类，继承测试父类
 * @Date: 2023/07/23
 */
public class TestClassLoaderChild extends TestClassLoader {

    Person person = new Person("TestClassLoaderChild");

    static {
        System.out.println("this is TestClassLoaderChild's static block");
    }

    public TestClassLoaderChild() {
        System.out.println("TestClassLoaderChild contractor");
    }
}

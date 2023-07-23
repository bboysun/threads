package classloader;

/**
 * @Author: darrylsun
 * @Description: 测试父类，并有 main 方法执行入口
 * @Date: 2023/07/23
 */
public class TestClassLoader {

    Person person = new Person("TestClassLoader");

    // static final Person personStatic = new Person("Static TestClassLoader");

    static {
        System.out.println("this is TestClassLoader static block");
    }

    public TestClassLoader() {
        System.out.println("TestClassLoader constructor");
    }

    public static String findJay() {
        return "find Jay";
    }

    public static void main(String[] args) {
        /**
         * 数据结果：
         * 1。首先要加载 TestClassLoader 类，类初始化，所以先输出 TestClassLoader 静态代码块的内容：this is TestClassLoader static block
         * 2。接着执行 new TestClassLoaderChild，因为其父类已经被加载过了，仅先需要先对 TestClassLoaderChild 进行类加载，加载过程中初始化，执行静态代码块内容：this is TestClassLoaderChild's static block
         * 3。初始化完成后就要生成对象，生成对象实例化前需要先对父类的成员变量实例化，即 new Person，则需要加载 person，初始化执行其静态代码块内容：person static block
         * 4。接着就要完成成员变量的构造函数完成对象的实例化，即输出内容：person name is TestClassLoader
         * 5。然后就可以执行父类的构造函数完成父类对象的实例的构建，即输出内容：TestClassLoader constructor
         * 6。此时父类已完成了全部的加载，继续对 child 类完成实例化，会先完成其成员变量的实例化，因为 person已经被初始化过了，即可直接执行构造函数输出内容：person name is TestClassLoaderChild
         * 7。然后对 child 类执行构造函数完成类加载的实例化，即输出内容：TestClassLoaderChild contractor
         */
        new TestClassLoaderChild();

        /**
         * 如果没有这个语句：static final Person personStatic = new Person("Static TestClassLoader");
         *
         * 输出内容：
         * this is TestClassLoader static block
         * find Jay
         */
        // System.out.println(TestClassLoader.findJay());

        /**
         * 如果有这个语句：static final Person personStatic = new Person("Static TestClassLoader");
         *
         * 输出内容：
         * person static block
         * person name is Static TestClassLoader
         * this is TestClassLoader static block
         * find Jay
         */
        // System.out.println(TestClassLoader.findJay());
    }
}

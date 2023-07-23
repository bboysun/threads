package designmodel;

/**
 * @Author: darrylsun
 * @Description: 单例 懒汉模式
 * @Date: 2023/07/22
 */
public class LazySinglePattern {

    // 依然堵住被随意创建对象的入口，私有化构造函数
    private LazySinglePattern() {
        System.out.println("我又被初始化了");
    }

//    static {
//        System.out.println("static block code init");
//    }

    // 声明我们需要的单例实例化对象，这里需要留意 volatile 关键字哟！！！
    private static volatile LazySinglePattern instance;

    // 双重锁校验，保证多线程环境中单例的线程安全问题！！！
    public static LazySinglePattern getInstance() {
        if (instance == null) {
            synchronized (LazySinglePattern.class) {
                if (instance == null) {
                    instance = new LazySinglePattern();
                }
            }
        }
        return instance;
    }

    public static String fingJay() {
        return "find Jay Chou again";
    }

    public static void main(String[] args) {
        /**
         * 输出结果：
         * find Jay Chou again
         */
        System.out.println(LazySinglePattern.fingJay());

        // 获取单例实例
        // System.out.println(LazySinglePattern.getInstance());
    }
}

package designmodel;

/**
 * @Author: darrylsun
 * @Description: 单例模式--饿汉模式
 * @Date: 2023/07/22
 */
public class SinglePattern {

    // 私有化构造函数，堵住其他地方创建实例的入口
    private SinglePattern() {
        System.out.println("我被实例化了");
    }

    // 创建私有的静态实例，类加载过程中已经被创建
    // 问题1：初始化的顺序无法确定，如果该类依赖于其他类的初始化，那就会有问题！！！
    private static SinglePattern instance = new SinglePattern();

    // 静态方法获取唯一的实例，这样我们就构建好了一个单例
    public static SinglePattern getInstance() {
        return instance;
    }

    // 问题2：假设这里有一个静态方法，开发者仅仅只是想调用该方法不想实例化这个对象占用空间，不好意思，这个类已经被实例化了！！！
    public static String findJay() {
        return "find Jay Chou";
    }


    public static void main(String[] args) {
        /**
         * 输出结果：
         * 我被实例化了
         * find Jay Chou
         */
        System.out.println(SinglePattern.findJay());

        // 获取单例实例
        // System.out.println(SinglePattern.getInstance());
    }
}

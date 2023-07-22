package designmodel;

/**
 * @Author: darrylsun
 * @Description: 单例模式，内部类
 * @Date: 2023/07/23
 */
public class InnerClassSinglePattern {

    // 依然堵住该类被实例化的入口
    private InnerClassSinglePattern() {
        System.out.println("看看我被实例化了吗？？？");
    }

    // 内部类私有化实例我们的单例对象
    private static class HolderInstance {
        private static InnerClassSinglePattern instance = new InnerClassSinglePattern();
    }

    public static InnerClassSinglePattern getInstance() {
        return HolderInstance.instance;
    }

    public static String findJay() {
        return "find Jay Chou again and again";
    }

    public static void main(String[] args) {
        /**
         * 输出结果：
         * find Jay Chou again and again
         */
        System.out.println(InnerClassSinglePattern.findJay());

        // 获取单例实例
        // System.out.println(InnerClassSinglePattern.getInstance());
    }
}

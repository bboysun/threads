/**
 * @Author: darrylsun
 * @Description:
 * @Date: 2023/11/30
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> context = new ThreadLocal<String>();
    public static void main(String[] args) {
        context.set("hello main");
        ThreadOne one = new ThreadOne();
        one.start();
        System.out.println(context.get());
    }

    static class ThreadOne extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                context.set("hello thread one");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(context.get());
        }
    }

}

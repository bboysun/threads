package deadlock;

import java.text.MessageFormat;

/**
 * @Auther: Darryl
 * @Description:
 * @Date: 2022/07/09
 */
public class Worker implements Runnable {

	private final Object lockA;

	private final Object lockB;

	public Worker(Object lockA, Object lockB) {
		this.lockA = lockA;
		this.lockB = lockB;
	}

	public void run() {

		synchronized (lockA) {
			// 获取 lockA 资源
			sleep(3000);
			System.out.println(MessageFormat.format("{0} get lock: {1}", Thread.currentThread().getName(), lockA));

			synchronized (lockB) {
				System.out.println(MessageFormat.format("{0} get lock: {1}", Thread.currentThread().getName(), lockA));
			}
		}

	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package deadlock;

import java.text.MessageFormat;

/**
 * 破锁 worker
 *
 * @Auther: Darryl
 * @Description:
 * @Date: 2022/07/09
 */
public class SmartWorker implements Runnable {

	private final Object lockA;
	private final Object lockB;

	public SmartWorker(Object lockA, Object lockB) {
		int res = lockA.toString().compareTo(lockB.toString());
		// lockA 永远是排序大的资源，lockB 永远是排序小的资源
		this.lockA = res >= 0 ? lockA : lockB;
		this.lockB = res >= 0 ? lockB : lockA;
	}

	public void run() {
		synchronized (lockA) {
			sleep(2000);
			System.out.println(MessageFormat.format("{0} get lock: {1}", Thread.currentThread().getName(), lockA));
			synchronized (lockB) {
				System.out.println(MessageFormat.format("{0} get lock: {1}", Thread.currentThread().getName(), lockB));
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

package deadlock;

/**
 * 破死锁
 *
 * @Auther: Darryl
 * @Description:
 * @Date: 2022/07/09
 */
public class BreakDeadLock {

	public static void main(String[] args) {
		final Object lockA = new Object();
		final Object lockB = new Object();

		System.out.println("lockA is " + lockA);
		System.out.println("lockB is " + lockB);

		SmartWorker smartWorkerA = new SmartWorker(lockA, lockB);
		SmartWorker smartWorkerB = new SmartWorker(lockB, lockA);

		Thread threadA = new Thread(smartWorkerA, "smartWorkA");
		Thread threadB = new Thread(smartWorkerB, "smartWorkB");

		threadA.start();
		threadB.start();
	}
}

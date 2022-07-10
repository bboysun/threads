package deadlock;

/**
 * dead lock learn
 *
 * @Auther: Darryl
 * @Description:
 * @Date: 2022/07/09
 */
public class DeadLock {

	public static void main(String[] args) {
		final Object lockA = new Object();
		final Object lockB = new Object();

		System.out.println("lockA is " + lockA);
		System.out.println("lockB is " + lockB);

		Worker workerA = new Worker(lockA, lockB);
		Worker workerB = new Worker(lockB, lockA);

		Thread threadA = new Thread(workerA, "workA");
		Thread threadB = new Thread(workerB, "workB");

		threadA.start();
		threadB.start();
	}

}

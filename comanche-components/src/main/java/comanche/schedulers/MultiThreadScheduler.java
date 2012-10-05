package comanche.schedulers;

import comanche.api.Scheduler;

/** Runs tasks by creating one new thread per task */
public class MultiThreadScheduler implements Scheduler {
	/* (non-Javadoc)
	 * @see comanche.schedulers.Scheduler#schedule(java.lang.Runnable)
	 */
	public void schedule(Runnable task) {
		new Thread(task).start();
	}
}

package comanche.schedulers;

import comanche.api.Scheduler;

/** Runs tasks as soon as they arrive, in the same thread. */
public class SequentialScheduler implements Scheduler {
	/* (non-Javadoc)
	 * @see comanche.schedulers.Scheduler#schedule(java.lang.Runnable)
	 */
	public void schedule(Runnable task) {
		task.run();
	}
}

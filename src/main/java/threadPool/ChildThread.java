package threadPool;

import server.Constants;

public class ChildThread extends Thread {

	private boolean runningFlag;

	private Taskable task;

	public ChildThread() {
		runningFlag = false;
	}

	public synchronized void run() {
		try {
			while (true) {
				if (!runningFlag) {
					this.wait();
				} else {
					this.task.doTask();
					setRunning(false);
				}
			}
		} catch (Exception e) {
			Constants.logger.info("Interrupt");
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return this.runningFlag;
	}

	public void setTask(Taskable task) {
		this.task = task;
	}

	public synchronized void setRunning(boolean flag) {
		this.runningFlag = flag;
		if (flag) {
			this.notifyAll();
		}
	}
}

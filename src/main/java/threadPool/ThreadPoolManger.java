package threadPool;

import java.util.Vector;

public class ThreadPoolManger {
	public Vector<Object> vector;
	private int maxNum;
	private int minNum;
	public ThreadPoolManger(int initThreads, int maxNum, int minNum) {
		vector = new Vector<Object>();// 存放线程
		this.maxNum = maxNum;
		this.minNum = minNum;
		for (int i = 1; i <= initThreads; i++) {
			ChildThread thread = new ChildThread();
			vector.addElement(thread);
		}
	}

	public void process(Taskable task) {
		int i;
		//从线程池中依次取出未使用的线程,添加taskable并启动
		for (i = 0; i < vector.size(); i++) {
			//取出线程
			ChildThread currentThread = (ChildThread) vector.elementAt(i);
			if (!currentThread.isRunning()) {
				//如果该线程未启动则加入任务
				currentThread.setTask(task);
				//修改线程状态为运行中
				currentThread.setRunning(true);
				//启动线程
				currentThread.start();
				return;
			}
		}
		//vector 中所有线程均已在运行,则对vector进行扩容,每次扩大5
		if (i >= vector.size()) {
			int temp = vector.size();
			for (int j = temp + 1; j <= temp + 5; j++) {
				ChildThread ct = new ChildThread();
				vector.addElement(ct);
				ct.start();
			}
			//添加任务
			this.process(task);
		}
	}

}

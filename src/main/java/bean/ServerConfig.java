package bean;

import java.util.Map;


public class ServerConfig {
	private Map<String , Integer> ports;
	private Map<String , String > threadPool;

	public Map<String, Integer> getPorts() {
		return ports;
	}

	public void setPorts(Map<String, Integer> ports) {
		this.ports = ports;
	}

	public Map<String, String> getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(Map<String, String> threadPool) {
		this.threadPool = threadPool;
	}
}

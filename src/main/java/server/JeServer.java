package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import bean.ServerConfig;
import threadPool.ThreadPoolManger;

/**
 * 启动服务器
 */
public class JeServer {
    public static void main(String[] args) throws Exception {
        JeServer ss = new JeServer();
        ss.startServer();
    }

    private boolean flag = false;
    private int initThreadNum = 5;

    private void startServer() throws Exception {
        ServerSocket ss = null;
        ServerConfig config = Constants.getServerCongfig();// 获取Server.xml配置
        int startPort = config.getPorts().get("START");// 监听的端口号

        //启动监听
        try {
            ss = new ServerSocket(startPort);
            //输出启动日志
            Constants.logger.info("[JeServer] is starting , and listening to port [" + ss.getLocalPort() + "]");
        } catch (Exception e) {
            // 端口被占用时
            Constants.logger.info("The port [" + startPort + "] is already in use...");
            return;
        }

        while (!flag) {
            try {
                Socket client = ss.accept();
                //输出客户端的地址
                Constants.logger.info("A client [" + client.getInetAddress() + "] is connecting to [JeServer]...");

                if (config.getThreadPool() != null) {// 配置了线程池
                    //取得线程池配置 maxThreads minSpareThreads
                    Map<String, String> m = config.getThreadPool();
                    // 开启线程池
                    //初始化线程池
                    ThreadPoolManger tpm = new ThreadPoolManger(initThreadNum, Integer.parseInt(m.get("maxThreads")),
                            Integer.parseInt(m.get("minSpareThreads")));
                    TaskService ts = new TaskService(client);
                    tpm.process(ts);
                } else {
                    // 未配置线程池
                    TaskService ts = new TaskService(client);
                    ts.doTask();
                }
            } catch (Exception e) {
                //服务器运行时出错日志
                Constants.logger.error("client is down,cause:" + e.getMessage());
                ss.close();
                return;
            }
        }
        ss.close();
    }
}

class ClosePort implements Runnable {

    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
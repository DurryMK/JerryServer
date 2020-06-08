package server;

import java.io.IOException;

import java.net.Socket;

import server.process.DynamicProcess;
import server.process.StaticProcess;
import service.Impl.WordService;
import threadPool.Taskable;

public class TaskService implements Taskable {
    private Socket client = new Socket();

    public TaskService(Socket client) {
        this.client = client;
    }

    public Object doTask() {
        try {
            JeHttpServletRequest request = new JeHttpServletRequest(client.getInputStream());
            JeHttpServletResponse response = new JeHttpServletResponse(client.getOutputStream(), request);
            //解析request请求  取得请求的后缀  设定带有.do和.action后缀的请求为动态资源请求
            String uri = request.getRequestURI();
            String last = uri.substring(uri.lastIndexOf(".") + 1);
            if ("do".equals(last) || "action".equals(last)) {
                //动态资源请求
                DynamicProcess dnp = new DynamicProcess();
                dnp.process(request, response);
            }
            //请求中含有.service的视为访问JerryServer的内置服务
            else if (uri.contains(".service")) {
                //截取服务名
                String serviceName = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
                Constants.logger.info("Access to Service [" + serviceName + "]");
                //激活相应的服务
                if ("word".equals(serviceName)) {//分词服务
                    WordService ws = new WordService(uri, response);
                    ws.service();
                } else {
                    //找不到服务则当作静态资源处理
                    StaticProcess sp = new StaticProcess();
                    sp.process(request, response);
                }
            } else {
                //静态资源请求
                StaticProcess sp = new StaticProcess();
                sp.process(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package server;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import bean.ServerConfig;

public class Tools {

    /**
     * 解析sever.xml
     */
    public static ServerConfig parserServerXml() throws Exception {
        //最终返回的包含Server.xml内容的对象
        ServerConfig bean = new ServerConfig();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            //解析Server.xml文档
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Constants.SERVERCONFIG);
            //获取所有Connector节点
            NodeList nodes = doc.getElementsByTagName("Connector");
            Map<String, Integer> portxml = new HashMap<String, Integer>();
            // 解析端口
            //遍历所有的Connector节点
            for (int i = 0; i < nodes.getLength(); i++) {
                Element node = (Element) nodes.item(i);
                //保存节点中action与port对应的内容
                portxml.put(node.getAttribute("action"), Integer.parseInt(node.getAttribute("port")));
                bean.setPorts(portxml);
            }
            //解析线程池配置
            NodeList pool = doc.getElementsByTagName("Executor");
            Map<String, String> poolxml = new HashMap<String, String>();
            //遍历所有的Executor节点
            for (int i = 0; i < pool.getLength(); i++) {
                Element node = (Element) pool.item(i);
                poolxml.put("name", node.getAttribute("name"));
                poolxml.put("maxThreads", node.getAttribute("maxThreads"));
                poolxml.put("minSpareThreads", node.getAttribute("minSpareThreads"));
                bean.setThreadPool(poolxml);
            }
        } catch (Exception e) {
        	Constants.logger.error("Error parsing server.xml:"+e.getMessage());
        	return null;
        }
        return bean;
    }

    /**
     * 解析web.xml
     */
    public static Map<String, String> parserWebXml() {
        Map<String, String> types = new HashMap<String, String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Constants.WEBCONFIG);
            NodeList nodes = doc.getElementsByTagName("mime-mapping");
            NodeList welcomePage = doc.getElementsByTagName("welcome-file");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                NodeList childNodes = e.getChildNodes();
                types.put(childNodes.item(1).getTextContent(), childNodes.item(3).getTextContent());
            }
            for (int i = 0; i < welcomePage.getLength(); i++) {
                types.put("welcome-file", welcomePage.item(i).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }

    public static void main(String[] args) throws Exception {
        Tools.getCookieID();
    }

    /**
     * 生成一个cookie标识
     */
    public static String getCookieID() {
        String id = "";
        Random rd = new Random();
        for (int i = 0; i < 3; i++)
            id += (char) (rd.nextInt(25) + 65);
        id += new Date().getTime();
        return id;
    }
}

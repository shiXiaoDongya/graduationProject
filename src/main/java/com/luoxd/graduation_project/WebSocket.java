package com.luoxd.graduation_project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luoxd.graduation_project.domain.Message;
import com.luoxd.graduation_project.domain.User;
import com.luoxd.graduation_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/websocket/{userType}/{userId}")
public class WebSocket {
    private final static Logger logger = LoggerFactory.getLogger(WebSocket.class);

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以"js/re-userId"为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户
     */
    private String userType;
    private String userId;


    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userType") String userType, @PathParam("userId") String userId, Session session)
    {
//        onlineNumber++;
//        String totalmsg = new String();
        this.userType = userType;
        this.userId = userId;
        this.session = session;

        String key = userType+"-"+userId;
        //把自己的信息加入到map当中去
        clients.put(key, this);
//        //此处是解决无法注入的关键
//        Map<String, Object> map1 = new HashMap<String,Object>();
//        try {
//            sendMessageAll(JSON.toJSONString(map1),username);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误"+error.getMessage());
        //error.printStackTrace();
    }
    /**
     * 连接关闭
     */
    @OnClose
    public void onClose()
    {
//        onlineNumber--;
        //webSockets.remove(this);
        String key = userType+"-"+userId;
        clients.remove(key);
//        try {
//            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
//            Map<String, Object> map1 = new HashMap<String,Object>();
//            map1.put("messageType",2);
//            map1.put("onlineUsers",clients.keySet());
//            map1.put("username",username);
//            sendMessageAll(JSON.toJSONString(map1),username);
//        }
//        catch (IOException e){
//            log.info(username+"下线的时候通知所有人发生了错误");
//        }
//        log.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     * message格式{"msg":"","from":"","to":"","jobId":""}
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        try {
            log.info("来自客户端消息：" + message+"客户端的id是："+session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String msg = jsonObject.getString("msg");
            String from = jsonObject.getString("from");
            String to = jsonObject.getString("to");
            String jobId = jsonObject.getString("jobId");
            Map<String, Object> msgMap = new HashMap<String,Object>();
            msgMap.put("msg",msg);
            msgMap.put("from",from);
            msgMap.put("to",to);
            msgMap.put("jobId",jobId);
            sendMessageTo(JSON.toJSONString(msgMap),to);
//            String textMessage = jsonObject.getString("message");
//            String fromusername = jsonObject.getString("from");
//            String tousername = jsonObject.getString("to");
//            //如果不是发给所有，那么就发给某一个人
//            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
//            Map<String, Object> map1 = new HashMap<String,Object>();
//            map1.put("messageType",4);
//            map1.put("textMessage",textMessage);
//            map1.put("fromusername",fromusername);
//            if(tousername.equals("All")){
//                map1.put("tousername","所有人");
//                sendMessageAll(JSON.toJSONString(map1),fromusername);
//            }
//            else{
//                map1.put("tousername",tousername);
//                sendMessageTo(JSON.toJSONString(map1),tousername);
//            }
        }
        catch (Exception e){
            log.info("发生了错误了");
        }

    }


    public void sendMessageTo(String message, String ToUserName) throws IOException {
        if(clients.keySet().contains(ToUserName)){
            clients.get(ToUserName).session.getAsyncRemote().sendText(message);
        }
//        for (WebSocket item : clients.values()) {
//            if (item.username.equals(ToUserName) ) {
//                item.session.getAsyncRemote().sendText(message);
//                break;
//            }
//        }
    }

//    public void sendMessageAll(String message,String FromUserName) throws IOException {
//        for (WebSocket item : clients.values()) {
//            item.session.getAsyncRemote().sendText(message);
//        }
//    }
//
//    public static synchronized int getOnlineCount() {
//        return onlineNumber;
//    }

}
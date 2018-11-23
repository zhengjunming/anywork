package com.qg.anywork.web.socket;

import com.google.gson.Gson;
import com.qg.anywork.model.po.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by ming on 18-8-11 上午9:13
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Component
@ServerEndpoint(value = "/websocket/{userId}")
@Slf4j
public class OnlineWebSocket {

    private static Gson gson = new Gson();

    /**
     * 记录当前连接数
     */
    private static int onlineCount = 0;

    /**
     * 存放服务端与客户端的唯一标识符
     */
    private static ConcurrentHashMap<Integer, OnlineWebSocket> onlineMap = new ConcurrentHashMap<>();

    /**
     * 与客户端的会话连接
     */
    private Session session;

    /**
     * 用户id
     */
    private int userId;

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        OnlineWebSocket.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        OnlineWebSocket.onlineCount--;
        if (onlineCount < 0) {
            onlineCount = 0;
        }
    }

    /**
     * 向在线用户推送公告
     *
     * @param message   消息
     * @param userIds   用户ID集合
     * @param publisher 发布人
     * @throws IOException ioException
     */
    public static void publishMessage(Message message, List<Integer> userIds, String publisher) throws IOException {
        Map<String, Object> map = new HashMap<>(6);
        // 2为公告推送
        map.put("type", 2);
        map.put("messageId", message.getMessageId());
        map.put("title", message.getTitle());
        map.put("content", message.getContent());
        map.put("publisher", publisher);
        map.put("status", 0);
        String response = gson.toJson(map);
        for (Integer userId : onlineMap.keySet()) {
            if (userIds.contains(userId)) {
                onlineMap.get(userId).sendMessage(response);
            }
        }
    }

    public static void sendOnLineToAll() throws IOException {
        Map<String, Object> map = new HashMap<>(2);
        // 1为在线人数
        map.put("type", 1);
        map.put("onlineCount", getOnlineCount());
        for (int userId : onlineMap.keySet()) {
            onlineMap.get(userId).sendMessage(gson.toJson(map));
        }
    }

    @OnOpen
    public void onOpen(@PathParam("userId") Integer userId, Session session) throws IOException {
        this.session = session;
        onlineMap.put(userId, this);
        addOnlineCount();
        this.userId = userId;
        log.info("有新链接加入，当前人数为 " + getOnlineCount());
        sendToUser(userId);
    }

    @OnClose
    public void onClose() {
        onlineMap.remove(userId);
        subOnlineCount();
        log.info("连接断开，当前人数为 " + getOnlineCount());
    }

    @OnError
    public void onError(Throwable error) {
        onlineMap.remove(userId);
        subOnlineCount();
        log.info("连接断开，当前人数为 " + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("收到消息： " + message);
    }

    private void sendMessage(final String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 向单个用户推送消息
     *
     * @param userId 用户ID
     * @throws IOException ioException
     */
    private void sendToUser(int userId) throws IOException {
        Map<String, Object> map = new HashMap<>(2);
        // 1为在线人数
        map.put("type", 1);
        map.put("onlineCount", getOnlineCount());
        onlineMap.get(userId).sendMessage(gson.toJson(map));
    }
}

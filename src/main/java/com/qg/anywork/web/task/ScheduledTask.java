package com.qg.anywork.web.task;

import com.qg.anywork.web.socket.OnlineWebSocket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Create by ming on 18-8-17 上午11:29
 * 定时任务
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 1000 * 60)
    public void getOnlineCount() throws IOException {
        OnlineWebSocket.sendOnLineToAll();
    }
}

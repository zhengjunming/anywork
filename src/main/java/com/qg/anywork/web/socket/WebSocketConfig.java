package com.qg.anywork.web.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Create by ming on 18-9-13 下午5:19
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("//websocket/{userId}")
                .setAllowedOrigins("*");
    }
}

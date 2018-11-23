package com.qg.anywork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Create by ming on 18-8-14 下午2:27
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 3)
public class SessionConfig {

}

package io.github.xxyopen.novel.core.config;

import io.livekit.server.AgentDispatchServiceClient;
import io.livekit.server.RoomServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LiveKit 客户端配置
 *
 * @author storyforge
 */
@Configuration
public class LiveKitConfig {

    @Bean
    public RoomServiceClient roomServiceClient(
        @Value("${livekit.host}") String host,
        @Value("${livekit.api-key}") String apiKey,
        @Value("${livekit.api-secret}") String apiSecret) {
        return RoomServiceClient.createClient(host, apiKey, apiSecret);
    }

    @Bean
    public AgentDispatchServiceClient agentDispatchServiceClient(
        @Value("${livekit.host}") String host,
        @Value("${livekit.api-key}") String apiKey,
        @Value("${livekit.api-secret}") String apiSecret) {
        return AgentDispatchServiceClient.createClient(host, apiKey, apiSecret);
    }
}

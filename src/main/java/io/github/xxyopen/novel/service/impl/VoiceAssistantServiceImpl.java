package io.github.xxyopen.novel.service.impl;

import io.github.xxyopen.novel.core.common.constant.ErrorCodeEnum;
import io.github.xxyopen.novel.core.common.exception.BusinessException;
import io.github.xxyopen.novel.dto.resp.VoiceSessionRespDto;
import io.github.xxyopen.novel.service.VoiceAssistantService;
import io.livekit.server.AccessToken;
import io.livekit.server.AgentDispatchServiceClient;
import io.livekit.server.CanPublish;
import io.livekit.server.CanSubscribe;
import io.livekit.server.RoomJoin;
import io.livekit.server.RoomName;
import io.livekit.server.RoomServiceClient;
import livekit.LivekitAgentDispatch.AgentDispatch;
import livekit.LivekitModels.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 语音客服 服务实现类
 *
 * @author storyforge
 */
@Service
@Slf4j
public class VoiceAssistantServiceImpl implements VoiceAssistantService {

    private static final DateTimeFormatter ROOM_TIME_FORMAT =
        DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final RoomServiceClient roomServiceClient;

    private final AgentDispatchServiceClient agentDispatchServiceClient;

    private final String liveKitHost;

    private final String apiKey;

    private final String apiSecret;

    private final String roomPrefix;

    private final String identityPrefix;

    private final String agentName;

    public VoiceAssistantServiceImpl(
        RoomServiceClient roomServiceClient,
        AgentDispatchServiceClient agentDispatchServiceClient,
        @Value("${livekit.host}") String liveKitHost,
        @Value("${livekit.api-key}") String apiKey,
        @Value("${livekit.api-secret}") String apiSecret,
        @Value("${novel.voice.room-prefix}") String roomPrefix,
        @Value("${novel.voice.identity-prefix}") String identityPrefix,
        @Value("${novel.voice.agent-name}") String agentName) {
        this.roomServiceClient = roomServiceClient;
        this.agentDispatchServiceClient = agentDispatchServiceClient;
        this.liveKitHost = liveKitHost;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.roomPrefix = roomPrefix;
        this.identityPrefix = identityPrefix;
        this.agentName = agentName;
    }

    @Override
    public VoiceSessionRespDto createSession() {
        String roomName = buildRoomName();
        String identity = buildIdentity();

        createRoom(roomName);
        String token = signJoinToken(roomName, identity);

        return VoiceSessionRespDto.builder()
            .wsUrl(liveKitHost)
            .token(token)
            .roomName(roomName)
            .identity(identity)
            .build();
    }

    @Override
    public void startAgent(String roomName) {
        try {
            Response<AgentDispatch> response = agentDispatchServiceClient
                .createDispatch(roomName, agentName)
                .execute();

            if (!response.isSuccessful() || response.body() == null) {
                log.warn("LiveKit agent dispatch failed with HTTP {}", response.code());
                throw new BusinessException(ErrorCodeEnum.THIRD_SERVICE_ERROR);
            }
        } catch (IOException e) {
            log.error("LiveKit agent dispatch failed", e);
            throw new BusinessException(ErrorCodeEnum.THIRD_SERVICE_ERROR);
        }
    }

    private String buildRoomName() {
        return roomPrefix + "-" + LocalDateTime.now().format(ROOM_TIME_FORMAT)
            + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String buildIdentity() {
        return identityPrefix + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private void createRoom(String roomName) {
        try {
            Response<Room> response = roomServiceClient
                .createRoom(roomName, 600, 20)
                .execute();

            if (!response.isSuccessful()) {
                log.warn("LiveKit createRoom failed with HTTP {}", response.code());
                throw new BusinessException(ErrorCodeEnum.THIRD_SERVICE_ERROR);
            }
        } catch (IOException e) {
            log.error("LiveKit createRoom failed", e);
            throw new BusinessException(ErrorCodeEnum.THIRD_SERVICE_ERROR);
        }
    }

    private String signJoinToken(String roomName, String identity) {
        AccessToken accessToken = new AccessToken(apiKey, apiSecret);
        accessToken.setIdentity(identity);
        accessToken.setTtl(TimeUnit.HOURS.toMillis(1));
        accessToken.addGrants(
            new RoomJoin(true),
            new RoomName(roomName),
            new CanPublish(true),
            new CanSubscribe(true)
        );
        return accessToken.toJwt();
    }
}

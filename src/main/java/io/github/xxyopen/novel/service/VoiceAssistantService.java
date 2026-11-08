package io.github.xxyopen.novel.service;

import io.github.xxyopen.novel.dto.resp.VoiceSessionRespDto;

/**
 * 语音客服 服务类
 *
 * @author storyforge
 */
public interface VoiceAssistantService {

    /**
     * 创建语音会话
     *
     * @return 语音会话信息
     */
    VoiceSessionRespDto createSession();

    /**
     * 启动语音客服 Agent
     *
     * @param roomName LiveKit 房间名
     */
    void startAgent(String roomName);
}

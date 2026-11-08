package io.github.xxyopen.novel.controller.front;

import io.github.xxyopen.novel.core.common.resp.RestResp;
import io.github.xxyopen.novel.core.constant.ApiRouterConsts;
import io.github.xxyopen.novel.dto.req.VoiceAgentStartReqDto;
import io.github.xxyopen.novel.dto.resp.VoiceSessionRespDto;
import io.github.xxyopen.novel.service.VoiceAssistantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台门户-语音客服模块 API 控制器
 *
 * @author storyforge
 */
@Tag(name = "VoiceAssistantController", description = "前台门户-语音客服模块")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_VOICE_URL_PREFIX)
@RequiredArgsConstructor
public class VoiceAssistantController {

    private final VoiceAssistantService voiceAssistantService;

    /**
     * 创建语音客服会话接口
     */
    @Operation(summary = "创建语音客服会话接口")
    @PostMapping("/session")
    public RestResp<VoiceSessionRespDto> createSession() {
        return RestResp.ok(voiceAssistantService.createSession());
    }

    /**
     * 启动语音客服 Agent 接口
     */
    @Operation(summary = "启动语音客服 Agent 接口")
    @PostMapping("/agent/start")
    public RestResp<Void> startAgent(@Valid @RequestBody VoiceAgentStartReqDto request) {
        voiceAssistantService.startAgent(request.getRoomName());
        return RestResp.ok();
    }
}

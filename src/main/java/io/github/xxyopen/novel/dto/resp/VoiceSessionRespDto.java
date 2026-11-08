package io.github.xxyopen.novel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 创建语音会话 响应DTO
 *
 * @author storyforge
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoiceSessionRespDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "LiveKit WebSocket 地址")
    private String wsUrl;

    @Schema(description = "LiveKit 加入令牌")
    private String token;

    @Schema(description = "房间名")
    private String roomName;

    @Schema(description = "用户身份")
    private String identity;
}

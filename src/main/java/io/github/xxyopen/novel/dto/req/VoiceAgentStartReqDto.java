package io.github.xxyopen.novel.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 启动语音客服 请求DTO
 *
 * @author storyforge
 */
@Data
public class VoiceAgentStartReqDto {

    @Schema(description = "LiveKit 房间名", required = true)
    @NotBlank(message = "房间名不能为空！")
    private String roomName;
}

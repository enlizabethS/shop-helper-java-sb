package com.shophelperjavasb.shophelperjavasb.config.details;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "StandardResponseDto", description = "сведения о запросе")
public class StandardResponseDto {
    @Schema(description = "Текст сообщения")
    private String message;
    @Schema(description = "HTTP-статус")
    private int status;
}

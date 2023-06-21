package com.shophelperjavasb.shophelperjavasb.exceptions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExceptionDto {
    @Schema(description = "Exception text")
    private String message;
    @Schema(description = "Exception http-status")
    private int status;
}

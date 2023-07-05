package com.shophelperjavasb.shophelperjavasb.products.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductNameDto {
    @Schema(description = "Name of the product")
    private String name;
}

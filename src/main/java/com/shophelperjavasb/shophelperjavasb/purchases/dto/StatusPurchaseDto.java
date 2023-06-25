package com.shophelperjavasb.shophelperjavasb.purchases.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatusPurchaseDto {
    public enum Status {
        CREATED,
        PERFORMED,
        DONE
    }

    @Schema(description = "New status of the purchase")
    @Enumerated(value = EnumType.STRING)
    private Status status;
}

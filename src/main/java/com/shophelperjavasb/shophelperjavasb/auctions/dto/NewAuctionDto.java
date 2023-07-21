package com.shophelperjavasb.shophelperjavasb.auctions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewAuctionDto {
    @Schema(description = "id of the product that participates in the auction")
    private Long productId;
    @Schema(description = "start date of the auction")
    private LocalDateTime startDate;
    @Schema(description = "expiration date of the auction")
    private LocalDateTime expirationDate;
    @Schema(description = "step up bid")
    private int step;
}

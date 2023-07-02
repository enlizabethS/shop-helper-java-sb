package com.shophelperjavasb.shophelperjavasb.auctions.dto;

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
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
}

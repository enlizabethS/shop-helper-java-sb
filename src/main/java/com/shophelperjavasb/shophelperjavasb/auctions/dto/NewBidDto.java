package com.shophelperjavasb.shophelperjavasb.auctions.dto;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewBidDto {
    @Schema(description = "amount of the bid")
    private double amount;
    @Schema(description = "id of the auction")
    private Long auctionId;
}

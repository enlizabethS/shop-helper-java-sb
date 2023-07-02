package com.shophelperjavasb.shophelperjavasb.auctions.dto;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewBidDto {
    private double amount;
    private Long auctionId;
}

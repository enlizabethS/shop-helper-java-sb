package com.shophelperjavasb.shophelperjavasb.auctions.dto;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuctionDto {
    @Schema(description = "id of the auction")
    private Long id;
    @Schema(description = "id of the user who created the auction")
    private Long userId;
    @Schema(description = "id of the product that participates in the auction")
    private Long productId;
    @Schema(description = "date of creation of the auction")
    private LocalDateTime createdDate;
    @Schema(description = "start date of the auction")
    private LocalDateTime startDate;
    @Schema(description = "expiration date of the auction")
    private LocalDateTime expirationDate;
    @Schema(description = "id of the bids participating in the auction")
    private List<Long> bidsId = new ArrayList<>();
    @Schema(description = "status of the auction")
    private String status;
    @Schema(description = "step up bid")
    private int step;

    public static AuctionDto from(Auction auction) {
        return AuctionDto.builder()
            .id(auction.getId())
            .userId(auction.getUser().getId())
            .productId(auction.getProduct().getId())
            .createdDate(auction.getCreatedDate())
            .startDate(auction.getStartDate())
            .expirationDate(auction.getExpirationDate())
            .bidsId(auction.getBids().size() > 0
                ? auction.getBids().stream().map(Bid::getId).collect(Collectors.toList())
                : null)
            .status(auction.getStatus().name())
            .step(auction.getStep())
            .build();
    }

    public static List<AuctionDto> from(List<Auction> auctions) {
        return auctions.stream()
            .map(AuctionDto::from)
            .collect(Collectors.toList());
    }
}

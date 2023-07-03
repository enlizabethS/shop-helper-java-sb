package com.shophelperjavasb.shophelperjavasb.auctions.dto;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Auction;
import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
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
    private Long id;
    private Long userId;
    private Long productId;
    private LocalDateTime createdDate;
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
    private List<Long> bidsId = new ArrayList<>();
    private String status;

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
            .build();
    }

    public static List<AuctionDto> from(List<Auction> auctions) {
        return auctions.stream()
            .map(AuctionDto::from)
            .collect(Collectors.toList());
    }
}

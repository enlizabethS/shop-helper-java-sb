package com.shophelperjavasb.shophelperjavasb.auctions.dto;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BidDto {
    private Long id;
    private Long userId;
    private double amount;
//    private Long auctionId;
    private LocalDateTime createdDate;
    private String status;

    public static BidDto from(Bid bid) {
        return BidDto.builder()
            .id(bid.getId())
            .userId(bid.getUser().getId())
            .amount(bid.getAmount())
//            .auctionId(bid.getAuction().getId())
            .createdDate(bid.getCreatedDate())
            .status(bid.getStatus().name())
            .build();
    }

    public static List<BidDto> from(List<Bid> bids) {
        return bids.stream()
            .map(BidDto::from)
            .collect(Collectors.toList());
    }
}

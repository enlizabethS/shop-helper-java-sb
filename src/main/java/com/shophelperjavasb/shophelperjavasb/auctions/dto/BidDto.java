package com.shophelperjavasb.shophelperjavasb.auctions.dto;

import com.shophelperjavasb.shophelperjavasb.auctions.model.Bid;
import com.shophelperjavasb.shophelperjavasb.purchases.dto.PurchaseResponseDto;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "id of the bid")
    private Long id;
    @Schema(description = "id of the user who created the bid")
    private Long userId;
    @Schema(description = "amount of the bid")
    private double amount;
    @Schema(description = "id of the auction")
    private Long auctionId;
    @Schema(description = "date of creation of the bid")
    private LocalDateTime createdDate;
    @Schema(description = "status of the bid")
    private String status;

    public static BidDto from(Bid bid) {
        return BidDto.builder()
            .id(bid.getId())
            .userId(bid.getUser().getId())
            .amount(bid.getAmount())
            .auctionId(bid.getAuction().getId())
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

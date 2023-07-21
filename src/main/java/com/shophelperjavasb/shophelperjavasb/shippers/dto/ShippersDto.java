package com.shophelperjavasb.shophelperjavasb.shippers.dto;

import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShippersDto {
    private Long id;
    private String shipperName;
    private String phone;
    private List<Purchase> purchases;
    private boolean selfPickUp;
}

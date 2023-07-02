package com.shophelperjavasb.shophelperjavasb.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewProductDTO {
    private Long id;
    private String name;
    private double price;
}

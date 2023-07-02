package com.shophelperjavasb.shophelperjavasb.addresses.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewAddressDto {
    @Schema(description = "the name of the street where the user lives")
    private String street;
    @Schema(description = "the house number where the user lives")
    private int houseNumber;
    @Schema(description = "the name of the city where the user lives")
    private String city;
    @Schema(description = "the postal code where the user lives")
    private int postalCode;
    @Schema(description = "the name of the country where the user lives")
    private String country;
}

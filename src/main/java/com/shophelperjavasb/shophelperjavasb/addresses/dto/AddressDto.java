package com.shophelperjavasb.shophelperjavasb.addresses.dto;

import com.shophelperjavasb.shophelperjavasb.addresses.model.Address;
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
public class AddressDto {
    @Schema(description = "id of the address")
    private Long id;
    @Schema(description = "id of the user")
    private Long userId;
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
    @Schema(description = "address creation date")
    private LocalDateTime createdDate;

    public static AddressDto from(Address address) {
        return AddressDto.builder()
            .id(address.getId())
            .userId(address.getUser().getId())
            .street(address.getStreet())
            .houseNumber(address.getHouseNumber())
            .city(address.getCity())
            .postalCode(address.getPostalCode())
            .country(address.getCountry())
            .createdDate(address.getCreatedDate())
            .build();
    }

    public static List<AddressDto> from(List<Address> addresses) {
        return addresses.stream()
            .map(AddressDto::from)
            .collect(Collectors.toList());
    }
}

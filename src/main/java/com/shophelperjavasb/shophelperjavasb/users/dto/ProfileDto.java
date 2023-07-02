package com.shophelperjavasb.shophelperjavasb.users.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String createdDateTime;
    private String role;
    private Long addressId;
    private List<Long> productsId = new ArrayList<>();
    private List<Long> purchasesId = new ArrayList<>();

    public static ProfileDto from(User user) {
        return ProfileDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .createdDateTime(user.getCreatedDate().toString())
            .role(user.getRole().name())
            .addressId(user.getAddress() != null
                ? user.getAddress().getId()
                : null)
            .productsId(user.getProducts().size() > 0
                ? user.getProducts().stream().map(Product::getId).collect(Collectors.toList())
                : null)
            .purchasesId(user.getPurchases().size() > 0
                ? user.getPurchases().stream().map(Purchase::getId).collect(Collectors.toList())
                : null)
            .build();
    }
}

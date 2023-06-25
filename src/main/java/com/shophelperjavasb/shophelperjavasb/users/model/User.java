package com.shophelperjavasb.shophelperjavasb.users.model;

import com.shophelperjavasb.shophelperjavasb.addresses.model.Address;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    public enum Role {ADMIN, USER}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String username;
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String hashPassword;
    @NotNull
    private LocalDateTime createdDate;
    @OneToOne(mappedBy = "user", targetEntity = Address.class)
    private Address address;
    @OneToMany(mappedBy = "user", targetEntity = Product.class)
    private List<Product> products;
    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;

    @Enumerated(value = EnumType.STRING) // чтобы хранил в БД как строку, а не число
    private Role role;
}

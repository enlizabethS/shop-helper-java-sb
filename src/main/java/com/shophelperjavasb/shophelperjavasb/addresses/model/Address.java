package com.shophelperjavasb.shophelperjavasb.addresses.model;

import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @OneToOne
    private User user;
    @NotNull
    private String street;
    @NotNull
    private int houseNumber;
    @NotNull
    private String city;
    @NotNull
    private int postalCode;
    @NotNull
    private String country;
}

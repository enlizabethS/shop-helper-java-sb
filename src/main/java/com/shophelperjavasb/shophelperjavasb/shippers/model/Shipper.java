package com.shophelperjavasb.shophelperjavasb.shippers.model;

import com.shophelperjavasb.shophelperjavasb.purchases.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String shipperName;
    @NotNull
    private String phone;
    @NotNull
    @OneToMany(mappedBy = "shipper")
    private List<Purchase> purchases;
}

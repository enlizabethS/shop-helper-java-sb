package com.shophelperjavasb.shophelperjavasb.purchase.model;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.purchaseDetails.model.PurchaseDetails;
import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
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
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @NotNull
    @OneToMany(mappedBy = "id", targetEntity = PurchaseDetails.class)
    private List<PurchaseDetails> purchaseDetails;
    @NotNull
    private int quantity;
    @NotNull
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    private Shipper shipper;
}

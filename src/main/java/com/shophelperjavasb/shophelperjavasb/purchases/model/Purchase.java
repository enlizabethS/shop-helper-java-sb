package com.shophelperjavasb.shophelperjavasb.purchases.model;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Purchase {
    public enum Status {
        CREATED,
        PERFORMED,
        DONE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @NotNull
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @NotNull
    private int quantity;
    @NotNull
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    private Shipper shipper;
    @Enumerated(value = EnumType.STRING)
    private Status status;
}

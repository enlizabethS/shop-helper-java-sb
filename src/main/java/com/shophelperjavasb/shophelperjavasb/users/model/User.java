package com.shophelperjavasb.shophelperjavasb.users.model;

import com.shophelperjavasb.shophelperjavasb.addresses.model.Address;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import com.shophelperjavasb.shophelperjavasb.purchase.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements UserDetails {
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

    private boolean active;
    @NotNull
    private String hashPassword;
    @NotNull
    private LocalDateTime createdDate;
    @OneToOne(mappedBy = "user", targetEntity = Address.class)
    private Address address;
    @OneToMany(mappedBy = "id", targetEntity = Product.class)
    private List<Product> products;
    @OneToMany(mappedBy = "id", targetEntity = Purchase.class)
    private List<Purchase> purchases;

    @Enumerated(value = EnumType.STRING) // чтобы хранил в БД как строку, а не число
    private Role role;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return hashPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

package com.shophelperjavasb.shophelperjavasb.addresses.repositories;

import com.shophelperjavasb.shophelperjavasb.addresses.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

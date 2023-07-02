package com.shophelperjavasb.shophelperjavasb.shippers.repositories;

import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippersRepository extends JpaRepository<Shipper, Long> {
}

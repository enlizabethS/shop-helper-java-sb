package com.shophelperjavasb.shophelperjavasb.products.repositories;

import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Override
    List<Image> findAllById(Iterable<Long> longs);
}

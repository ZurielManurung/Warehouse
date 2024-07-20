package com.test.warehouse.repository;

import com.test.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByDeleted(Boolean deleted);

    Optional<Product> findByName(String name);
}

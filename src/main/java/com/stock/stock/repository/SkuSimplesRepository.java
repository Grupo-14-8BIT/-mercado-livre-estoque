package com.stock.stock.repository;

import com.stock.stock.entity.SkuSimples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkuSimplesRepository extends JpaRepository <SkuSimples, Integer> {
    Optional<SkuSimples> findBySKU(String SKU);




}
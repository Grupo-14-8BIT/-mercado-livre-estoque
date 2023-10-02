package com.stock.stock.repository;

import com.stock.stock.entity.Anuncio;
import com.stock.stock.entity.Order;
import com.stock.stock.entity.SkuSimples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {

Optional<Order> findByMlbId ( Long MlbId);


}
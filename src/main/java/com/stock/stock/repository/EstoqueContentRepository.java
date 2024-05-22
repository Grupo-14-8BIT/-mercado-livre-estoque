package com.stock.stock.repository;

import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.EstoqueContent;
import com.stock.stock.entity.SkuSimples;
import com.stock.stock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueContentRepository extends JpaRepository <EstoqueContent, Integer> {


List<EstoqueContent> findAllByEstoque(Estoque estoque);

    Optional<EstoqueContent> findByUserAndSkuSimplesAndAndEstoque(User user, SkuSimples skuSimples, Estoque estoque);


}
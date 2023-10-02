package com.stock.stock.repository;

import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.EstoqueContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueContentRepository extends JpaRepository <EstoqueContent, Integer> {


List<EstoqueContent> findAllByEstoque(Estoque estoque);

}
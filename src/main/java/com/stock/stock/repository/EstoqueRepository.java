package com.stock.stock.repository;

import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository <Estoque, Integer> {

List<Estoque> findAllByUser(User user);


    Optional<Estoque> findByConta(Conta contas);
}
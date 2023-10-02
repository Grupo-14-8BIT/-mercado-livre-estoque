package com.stock.stock.repository;

import com.stock.stock.entity.Estoque;
import com.stock.stock.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository <Estoque, Integer> {

List<Estoque> findAllByUser(User user);

}
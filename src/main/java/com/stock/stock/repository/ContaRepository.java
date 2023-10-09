package com.stock.stock.repository;

import com.stock.stock.entity.Conta;
import com.stock.stock.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository  extends JpaRepository <Conta, Integer> {


    List<Conta> findContasByUsuario(User usuario);

   Optional<Conta> findContaByContaid(Long contaid);

}
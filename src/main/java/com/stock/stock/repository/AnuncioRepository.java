package com.stock.stock.repository;

import com.stock.stock.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository <Anuncio, Integer> {






}
package com.stock.stock.repository;

import com.stock.stock.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnuncioRepository extends JpaRepository <Anuncio, Integer> {


    Optional<Anuncio> findAnuncioByMlb(String mlb);


}
package com.stock.stock.Service;

import com.stock.stock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //testar
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
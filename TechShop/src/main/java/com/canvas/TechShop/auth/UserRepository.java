package com.canvas.TechShop.auth;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findAllByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}

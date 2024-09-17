package com.canvas.TechShop.repositories;

import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.models.Owner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Transactional
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Owner> findByUser(User user);
}

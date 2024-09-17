package com.canvas.TechShop.repositories;

import com.canvas.TechShop.models.TechProfile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProfileRepository extends JpaRepository<TechProfile,Long> {
}

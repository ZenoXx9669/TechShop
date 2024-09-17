package com.canvas.TechShop.repositories;

import com.canvas.TechShop.models.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface TypeRepository extends JpaRepository<Type,Long> {
    List<Type> findByNameIn(List<String> types);
}

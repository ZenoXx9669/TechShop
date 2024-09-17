package com.canvas.TechShop.repositories;

import com.canvas.TechShop.models.Category;
import com.canvas.TechShop.models.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c JOIN c.types t WHERE t.id IN :typeIds")
    List<Category> findByTypeIds(List<Type> typeIds);
}

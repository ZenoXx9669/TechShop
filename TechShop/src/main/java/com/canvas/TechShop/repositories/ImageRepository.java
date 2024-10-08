package com.canvas.TechShop.repositories;

import com.canvas.TechShop.models.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image,Long> {
}

package com.canvas.TechShop.repositories;

import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByBrandIgnoreCase(String brand);
    List<Product> searchProductsByFullNameProductContainsIgnoreCase(String product);
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();
//    Optional<Product> findByUser(User user);
//    List<Product> findAllByUser(User user);
}

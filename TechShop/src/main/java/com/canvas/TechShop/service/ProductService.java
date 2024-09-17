package com.canvas.TechShop.service;

import com.canvas.TechShop.models.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
//    Product addProduct(Product product);

    Product addProduct(Product product, Long ownerId);

    Product getProduct(Long id);
//    Product updateProduct(Product updProduct);

    Product updateProduct(Product updProduct, Long ownerId);

//    void removeProduct(Long id);

    void removeProduct(Long id, Long ownerId);

    List<Product> getAllPhones();
    List<Product> getAllLaptops();
    List<Product> getAllAccessory();
    List<Product> getAllWashers();
    List<Product> getAllFridges();
    List<Product> getAllTools();
    List<Product> getAllPlumbings();
    List<Product> getAllByBrand(String brand);
    List<Product> searchProducts(String product);
    List<Product> asc();
    List<Product> desc();
    List<Product> startAndLast(int start,int last);
    List<Product> addAll(List<Product> products);
}

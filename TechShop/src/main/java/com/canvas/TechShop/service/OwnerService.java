package com.canvas.TechShop.service;

import com.canvas.TechShop.models.Owner;
import com.canvas.TechShop.models.Product;

import java.util.List;

public interface OwnerService {
    List<Product> getUserProductsInOwner(Long userId);
    void toBecomeSalesman(Owner owner);
    Owner checkCode(short code);
    Owner add(Owner owner);
}

package com.canvas.TechShop.controllers;

import com.canvas.TechShop.auth.services.UserService;
import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.service.FavoriteService;
import com.canvas.TechShop.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/my")
public class OfficeController {
    private final FavoriteService favoriteService;
    private final OwnerService ownerService;

    @GetMapping("/favorites/{userId}")
    public List<Product> getUserFavorites(@PathVariable Long userId) {
        return favoriteService.getUserFavorites(userId);
    }
    @GetMapping("/products/{userId}")
    public List<Product> getUserProducts(@PathVariable Long userId){
        return ownerService.getUserProductsInOwner(userId);
    }
}

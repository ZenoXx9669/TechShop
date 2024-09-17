package com.canvas.TechShop.controllers;

import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/add")
    public void addProductToFavorites(@RequestParam Long userId, @RequestParam Long productId) {
        favoriteService.addProductToFavorites(userId, productId);
    }

    @DeleteMapping("/remove")
    public void removeProductFromFavorites(@RequestParam Long userId, @RequestParam Long productId) {
        favoriteService.removeProductFromFavorites(userId, productId);
    }


}

package com.canvas.TechShop.service;

import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.models.Favorite;
import com.canvas.TechShop.models.Product;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    Favorite addProductToFavorites(Long userId, Long productId);
    void removeProductFromFavorites(Long userId, Long productId);
    List<Product> getUserFavorites(Long userId);
}

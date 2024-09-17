package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.auth.UserRepository;
import com.canvas.TechShop.models.Favorite;
import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.repositories.FavoriteRepository;
import com.canvas.TechShop.repositories.ProductRepository;
import com.canvas.TechShop.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Favorite addProductToFavorites(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        Favorite favorite = favoriteRepository.findByUser(user)
                .orElseGet(() -> {
                    Favorite newFavorite = new Favorite();
                    newFavorite.setUser(user);
                    return newFavorite;
                });

        favorite.getProducts().add(product);
        return favoriteRepository.save(favorite);
    }

    @Override
    public void removeProductFromFavorites(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Favorite favorite = favoriteRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Favorite not found"));
        favorite.getProducts().remove(product);
        favoriteRepository.save(favorite);
    }
    @Override
    public List<Product> getUserFavorites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Favorite favorite = favoriteRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Favorite not found"));
        return favorite.getProducts();
    }
}

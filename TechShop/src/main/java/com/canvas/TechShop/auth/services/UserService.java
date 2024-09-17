package com.canvas.TechShop.auth.services;


import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.models.Product;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void register(User user);
    User checkCode(short code);
    User login(String email, String password);
//    List<Product> getAllUserProducts(Long userId);
}

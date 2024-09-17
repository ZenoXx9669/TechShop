package com.canvas.TechShop.auth.services;

import com.canvas.TechShop.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserServiceImpl service;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        service.register(user);
    }

    @PostMapping("/check-code")
    public User checkCode(@RequestParam short code) {
        return service.checkCode(code);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return service.login(email, password);
    }
}

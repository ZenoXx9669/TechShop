package com.canvas.TechShop.controllers;

import com.canvas.TechShop.models.Owner;
import com.canvas.TechShop.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/to-become-salesman")
public class OwnerController {
    private final OwnerService ownerService;

    @PutMapping
    public void toBecomeSalesman(@RequestBody Owner owner){
        ownerService.toBecomeSalesman(owner);
    }
    @PostMapping
    public Owner checkCode(@RequestParam short code){
        return ownerService.checkCode(code);
    }
}

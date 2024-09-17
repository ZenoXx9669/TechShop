package com.canvas.TechShop.controllers;

import com.canvas.TechShop.models.Category;
import com.canvas.TechShop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product/category")
public class CategoryController {
    private final CategoryService service;
    @GetMapping
    public List<Category> getAllCategory(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return service.add(category);
    }
    @GetMapping("/electronics")
    public List<Category> getElectronics(){
        return service.getElectronics();
    }
    @GetMapping("/house-appliances")
    public List<Category> getHouseAppliances(){
        return service.getHouseAppliances();
    }
    @GetMapping("/construction-technicians")
    public List<Category> getConstructionTechnicians(){
        return service.getConstructionTechnicians();
    }

}

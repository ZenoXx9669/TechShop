package com.canvas.TechShop.controllers;

import com.canvas.TechShop.models.Type;
import com.canvas.TechShop.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product/type")
public class TypeController {
    private final TypeService service;
    @GetMapping()
    public List<Type> getAllClasses(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Type getClassById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @PostMapping
    private Type addClass(@RequestBody Type clazz){
        return service.add(clazz);
    }
}

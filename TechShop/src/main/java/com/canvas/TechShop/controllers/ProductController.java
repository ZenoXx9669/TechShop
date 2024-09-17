package com.canvas.TechShop.controllers;

import com.canvas.TechShop.models.Image;
import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.service.ImageService;
import com.canvas.TechShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    private final ImageService imageService;

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product,@RequestParam Long ownerId){
        return service.addProduct(product,ownerId);
    }

    @PostMapping("/image")
    public List<Image> saveImages(@RequestBody List<Image> images){
        return imageService.saveImages(images);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        return service.getProduct(id);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product updProduct,@RequestParam Long ownerId){
        return service.updateProduct(updProduct,ownerId);
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable("id") Long id,@RequestParam Long ownerId){
        service.removeProduct(id,ownerId);
    }

    @DeleteMapping("/image/{id}")
    public void deleteImage(@PathVariable("id") Long id){
        imageService.removeImage(id);
    }

    @GetMapping("/phones")
    public List<Product> getAllPhones(){
        return service.getAllPhones();
    }

    @GetMapping("/laptops")
    public List<Product> getAllLaptops() {
        return service.getAllLaptops();
    }

    @GetMapping("/accessories")
    public List<Product> getAllAccessory() {
        return service.getAllAccessory();
    }

    @GetMapping("/washers")
    public List<Product> getAllWashers() {
        return service.getAllWashers();
    }

    @GetMapping("/fridges")
    public List<Product> getAllFridges() {
        return service.getAllFridges();
    }

    @GetMapping("/tools")
    public List<Product> getAllTools() {
        return service.getAllTools();
    }

    @GetMapping("/plumbings")
    public List<Product> getAllPlumbings() {
        return service.getAllPlumbings();
    }
    @GetMapping("/to/{brand}")
    public List<Product> getAllByBrand(@PathVariable("brand") String brand){
        return service.getAllByBrand(brand);
    }
    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String product){
        return service.searchProducts(product);
    }
    @GetMapping("/asc")
    public List<Product> productsASC(){
        return service.asc();
    }
    @GetMapping("/desc")
    public List<Product> productsDESC(){
        return service.desc();
    }
    @GetMapping("/start-to-last-price")
    public List<Product> startToLast(@RequestParam int start,@RequestParam int last){
        return service.startAndLast(start,last);
    }


    @PostMapping("/all")
    public List<Product> addAllProducts(@RequestBody List<Product> products){
        return service.addAll(products);
    }
}

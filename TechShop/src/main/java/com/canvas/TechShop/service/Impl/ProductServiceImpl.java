package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.exceptions.RoleException;
import com.canvas.TechShop.models.Category;
import com.canvas.TechShop.models.Owner;
import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.repositories.CategoryRepository;
import com.canvas.TechShop.repositories.OwnerRepository;
import com.canvas.TechShop.repositories.ProductRepository;
import com.canvas.TechShop.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Primary
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    @Transactional
    public Product addProduct(Product product, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        assert owner != null;
        if (owner.getUser().getRoles().size() == 2){
            Category category1 = categoryRepository.findById(55L).orElse(null);
            Category category2 = categoryRepository.findById(56L).orElse(null);
            Category category3 = categoryRepository.findById(57L).orElse(null);
            checkTypeProduct(product, category1, category2, category3);
            owner.getProducts().add(product);
        }else {
            throw new RoleException("You are not Salesman or your account has not been activated");
        }

        return productRepository.save(product);
    }

    private void checkTypeProduct(Product product, Category category1, Category category2, Category category3) {
        if (product.getType().getId() <=3){
            product.setCategory(category1);
        } else if (product.getType().getId() <=5) {
            product.setCategory(category2);
        } else if (product.getType().getId() <=7) {
            product.setCategory(category3);
        }
        String fullName = product.getBrand() + " " + product.getModel();
        product.setFullNameProduct(fullName);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Product updProduct, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        assert owner != null;
        List<Product> products = owner.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getId(), updProduct.getId())){
                owner.getProducts().set(i,updProduct);
            }
        }
        return productRepository.save(updProduct);
    }

    @Override
    public void removeProduct(Long id, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        assert owner != null;
        List<Product> products = owner.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getId(), id)){
                owner.getProducts().remove(i);
            }
        }
        productRepository.deleteById(id);
    }
    @Override
    public List<Product> getAllPhones(){
        return productRepository.findAll().stream()
                .filter(product -> product.getType().getName().equals("Phone"))
                .toList();
    }

    @Override
    public List<Product> getAllLaptops() {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().getName().equals("Laptop"))
                .toList();
    }

    @Override
    public List<Product> getAllAccessory() {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().getName().equals("Accessory"))
                .toList();
    }

    @Override
    public List<Product> getAllWashers() {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().getName().equals("Washer"))
                .toList();
    }

    @Override
    public List<Product> getAllFridges() {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().getName().equals("Fridge"))
                .toList();
    }

    @Override
    public List<Product> getAllTools() {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().getName().equals("Tool"))
                .toList();
    }

    @Override
    public List<Product> getAllPlumbings() {
        return productRepository.findAll().stream()
                .filter(product -> product.getType().getName().equals("Plumbing"))
                .toList();
    }

    @Override
    public List<Product> getAllByBrand(String brand) {
        return productRepository.findAllByBrandIgnoreCase(brand);
    }

    @Override
    public List<Product> searchProducts(String product) {
        return productRepository.searchProductsByFullNameProductContainsIgnoreCase(product);
    }

    @Override
    public List<Product> asc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Product> desc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Product> startAndLast(int start, int last) {
        List<Product> products = productRepository.findAll();
        return products.stream().filter(product -> product.getPrice() >= start && product.getPrice() <= last).toList();
    }

    @Override
    public List<Product> addAll(List<Product> products) {
        Category category1 = categoryRepository.findById(55L).orElse(null);
        Category category2 = categoryRepository.findById(56L).orElse(null);
        Category category3 = categoryRepository.findById(57L).orElse(null);
        for (Product product : products) {
            checkTypeProduct(product, category1, category2, category3);
        }
        return productRepository.saveAll(products);
    }

    @Async
    public CompletableFuture<List<Product>> getAllProductsAsync() {
        List<Product> products = productRepository.findAll();
        return CompletableFuture.completedFuture(products);
    }
}

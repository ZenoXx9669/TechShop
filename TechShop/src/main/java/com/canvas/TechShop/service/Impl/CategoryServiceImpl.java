package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.models.Category;
import com.canvas.TechShop.models.Type;
import com.canvas.TechShop.repositories.CategoryRepository;
import com.canvas.TechShop.repositories.TypeRepository;
import com.canvas.TechShop.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category add(Category item) {
        return categoryRepository.save(item);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category update(Category updItem) {
        return categoryRepository.save(updItem);
    }

    @Override
    public void removeById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getElectronics() {
        List<String> electronicsTypes = List.of("Phone", "Laptop", "Accessory");
        return categoryRepository.findAll().stream()
                .filter(category -> category.getTypes().stream()
                        .anyMatch(type -> electronicsTypes.contains(type.getName())))
                .collect(Collectors.toList());
    }
    @Override
    public List<Category> getHouseAppliances() {
        List<String> houseAppliancesTypes = List.of("Washer", "Fridge");
        return categoryRepository.findAll().stream()
                .filter(category -> category.getTypes().stream()
                        .anyMatch(type -> houseAppliancesTypes.contains(type.getName())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getConstructionTechnicians() {
        List<String> constructionTechniciansTypes = List.of("Tool", "Plumbing");
        return categoryRepository.findAll().stream()
                .filter(category -> category.getTypes().stream()
                        .anyMatch(type -> constructionTechniciansTypes.contains(type.getName())))
                .collect(Collectors.toList());
    }


}

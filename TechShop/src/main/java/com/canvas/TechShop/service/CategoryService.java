package com.canvas.TechShop.service;

import com.canvas.TechShop.models.Category;

import java.util.List;
import java.util.stream.Collectors;

public interface CategoryService extends MainService<Category>{
    List<Category> getElectronics();
    List<Category> getHouseAppliances();
    List<Category> getConstructionTechnicians();
}

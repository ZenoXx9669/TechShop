package com.canvas.TechShop.service;

import com.canvas.TechShop.models.Image;

import java.util.List;

public interface ImageService {
    List<Image> saveImages(List<Image> images);
    void removeImage(Long id);
}

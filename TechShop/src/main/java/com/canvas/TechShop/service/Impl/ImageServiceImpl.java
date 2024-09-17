package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.models.Image;
import com.canvas.TechShop.repositories.ImageRepository;
import com.canvas.TechShop.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    @Override
    public List<Image> saveImages(List<Image> images) {
        return imageRepository.saveAll(images);
    }

    @Override
    public void removeImage(Long id) {
        imageRepository.deleteById(id);
    }

}

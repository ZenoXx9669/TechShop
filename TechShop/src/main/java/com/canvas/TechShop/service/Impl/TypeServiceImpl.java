package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.models.Type;
import com.canvas.TechShop.repositories.TypeRepository;
import com.canvas.TechShop.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;
    @Override
    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type add(Type clazz) {
        return typeRepository.save(clazz);
    }

    @Override
    public Type getById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Type update(Type updItem) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }
}

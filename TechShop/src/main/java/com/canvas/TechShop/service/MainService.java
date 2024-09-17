package com.canvas.TechShop.service;

import java.util.List;

public interface MainService<T> {
    List<T> getAll();
    T add(T item);
    T getById(Long id);
    T update(T updItem);
    void removeById(Long id);
}

package com.canvas.TechShop.models;

import com.canvas.TechShop.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private String fullNameProduct;
    private int price;
    private int amount;
    private String color;
    private String characteristic;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Type type;
}

package com.canvas.TechShop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TechProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techShopId;
    private String fullName;
    private int age;
    private String phoneNumber;
    private String email;
    private boolean confirmEmail;
    private String name;
    private String countryAndCity;
    private String dateOfBirth;
    private String dateOfCreated;
    @OneToOne(fetch = FetchType.EAGER)
    private Gender gender;
    @OneToOne(fetch = FetchType.EAGER)
    private Image image;
}

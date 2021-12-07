package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String info;
    @NotNull
    private int amount;
    @NotNull
    private Category category;

    @OneToMany
    private List<Image> images;
    @OneToOne
    private Discount discount_id;
    @ManyToMany
    private List<Purchase> purchases;

}

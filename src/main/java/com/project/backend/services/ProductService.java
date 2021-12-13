package com.project.backend.services;

import com.project.backend.models.Product;
import com.project.backend.models.Purchase;

import java.util.List;

public interface ProductService {

    List<Product> findByCategory(String category);

    Product findProductById(Long id);

    void addDiscountToProducts(List<Product> products);

    void removeDiscountFromProduct(Long id);

    // List<Product> getTopSoldProductsByCategory(String category);
}

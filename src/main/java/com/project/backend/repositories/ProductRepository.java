package com.project.backend.repositories;

import com.project.backend.models.Category;
import com.project.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategory(Category category);

    Optional<Product> findById(Long id);

    @Query("FROM Product p \n" +
            "JOIN PurchasesProducts pp ON p.id=pp.product.id\n" +
            "            WHERE p.category=:category\n" +
            "           GROUP BY p.title\n" +
            "            ORDER BY pp.amount DESC")
    List<Product> getTopProductsByCategory(Category category);

    @Query("FROM Product p WHERE p.category=:category ORDER BY p.price ASC")
    List<Product> getByPriceAscByCategory(Category category);
}

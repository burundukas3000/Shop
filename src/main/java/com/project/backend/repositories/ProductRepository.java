package com.project.backend.repositories;

import com.project.backend.models.Category;
import com.project.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategory(Category category);

    // method works in SQL but doesn't in Hibernate because no @Entity mapping for joint table. How to get additional fields
    // from joint table?
/*    @Query("SELECT p.title, pp.amount FROM Product p JOIN purchases_products pp ON p.id=pp.product_id\n" +
            "    WHERE p.category= :category\n" +
            "    GROUP BY p.title\n" +
            "    ORDER BY pp.amount DESC")
    List<Product> getTopProductsByCategory(Category category);*/
}

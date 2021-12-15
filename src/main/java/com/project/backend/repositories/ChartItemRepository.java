package com.project.backend.repositories;

import com.project.backend.models.ChartItem;
import com.project.backend.models.Product;
import com.project.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartItemRepository extends JpaRepository<ChartItem, Long> {

    List<ChartItem> findByUser(User user);

    ChartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);
}

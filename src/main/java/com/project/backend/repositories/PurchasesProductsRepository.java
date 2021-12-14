package com.project.backend.repositories;

import com.project.backend.models.Purchase;
import com.project.backend.models.PurchaseProductId;
import com.project.backend.models.PurchasesProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasesProductsRepository extends JpaRepository<PurchasesProducts, PurchaseProductId>  {
}

package com.project.backend.services;

import com.project.backend.models.Purchase;
import com.project.backend.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepo;

    public Purchase getByPurchaseId(Long id) {
        Purchase purchase = purchaseRepo.getById(id);
        return purchase;
    }
}

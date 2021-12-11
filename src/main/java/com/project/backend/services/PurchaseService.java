package com.project.backend.services;

import com.project.backend.models.Purchase;

public interface PurchaseService {

    Purchase getByPurchaseId(Long id);
}

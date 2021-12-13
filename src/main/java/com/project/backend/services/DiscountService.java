package com.project.backend.services;

import com.project.backend.models.Discount;

import java.util.List;

public interface DiscountService {

    List<Discount> getAllDiscounts();

    Discount findById(Long id);
}

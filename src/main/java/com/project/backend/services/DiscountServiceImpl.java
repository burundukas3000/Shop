package com.project.backend.services;

import com.project.backend.models.Discount;
import com.project.backend.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepository discountRepo;

    public List<Discount> getAllDiscounts() {
        return discountRepo.findAll();
    }

    public Discount findById(Long id) {
        return discountRepo.findById(id).get();
    }
}

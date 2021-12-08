package com.project.backend.services;

import com.project.backend.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepository discountRepo;
}

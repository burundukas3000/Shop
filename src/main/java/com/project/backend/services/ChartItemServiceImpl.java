package com.project.backend.services;

import com.project.backend.models.ChartItem;
import com.project.backend.models.User;
import com.project.backend.repositories.ChartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartItemServiceImpl implements ChartItemService {

    @Autowired
    ChartItemRepository chartRepo;

    public List<ChartItem> listChartItems(User user) {
        return chartRepo.findByUser(user);
    }
}

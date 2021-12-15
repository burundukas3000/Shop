package com.project.backend.services;

import com.project.backend.models.ChartItem;
import com.project.backend.models.Product;
import com.project.backend.models.User;

import java.util.List;

public interface ChartItemService {

    List<ChartItem> listChartItems(User user);

    Integer addCartItem(Product product, int quantity, User user);

    void deleteCartItem(Product product, User user);
}

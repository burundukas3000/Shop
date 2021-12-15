package com.project.backend.services;

import com.project.backend.models.ChartItem;
import com.project.backend.models.Product;
import com.project.backend.models.User;
import com.project.backend.repositories.ChartItemRepository;
import com.project.backend.repositories.ProductRepository;
import com.project.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartItemServiceImpl implements ChartItemService {

    @Autowired
    ChartItemRepository cartRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    ProductService productService;

    public List<ChartItem> listChartItems(User user) {

        List<ChartItem> cartItems = cartRepo.findByUser(user);
        for(ChartItem c: cartItems)        {
            productService.calculateDiscounts(c.getProduct());
        }
        return cartItems;
    }

    public Integer addCartItem(Product product, int quantity, User user) {
        User u = userRepo.findByUserName(user.getUserName());
        Product p = productRepo.findById(product.getId()).get();
        ChartItem cartItem = cartRepo.findByUserAndProduct(u, p);
        if(cartItem!=null) {
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setProduct(p);
            cartItem.setUser(u);
            cartItem.setQuantity(quantity);
        }
        Integer currentQnt = cartRepo.save(cartItem).getQuantity();
        return currentQnt;
    }
}

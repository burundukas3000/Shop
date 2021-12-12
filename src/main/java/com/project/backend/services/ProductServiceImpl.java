package com.project.backend.services;

import com.project.backend.models.Category;
import com.project.backend.models.Image;
import com.project.backend.models.Product;
import com.project.backend.models.Purchase;
import com.project.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    UserService userService;
    @Autowired
    PurchaseService purchaseService;

    // getting products by category and assigning discount if available + checking if loyal customer to assign additional 5%
    public List<Product> findByCategory(String category) {

        List<Product> products = productRepo.findProductByCategory(Category.valueOf(category.toUpperCase(Locale.ROOT)));
        for(Product product: products){
            if(product.getDiscount()!=null) {
                int first = product.getDiscount().getPercentage();
                System.out.println("price before " +product.getPrice());
                product.setHappyPrice(product.getPrice().divide(new BigDecimal(100),2, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(100-first)));
                System.out.println("price after assigning discount" + product.getHappyPrice());
            }
            // if loyal customer +5% discount
            if(userService.loggedInUser()!= null && userService.hasRole("ROLE_LYCUSTOMER")){
                BigDecimal currentPrice = product.getHappyPrice()!=null ? product.getHappyPrice() : product.getPrice();
                product.setLoyalPrice(currentPrice.divide(new BigDecimal(100),2, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(95)));
                System.out.println("price for loyal customer" + product.getLoyalPrice());
            }
        }
       return products;
    }

/*    public List<Product> getTopSoldProductsByCategory(String category) {
        return productRepo.getTopProductsByCategory(Category.valueOf(category.toUpperCase(Locale.ROOT)));
    }*/
}

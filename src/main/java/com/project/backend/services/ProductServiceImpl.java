package com.project.backend.services;

import com.project.backend.models.*;
import com.project.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepo;
    @Autowired
    UserService userService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    DiscountService discountService;

    // getting products by category and assigning discount if available + checking if loyal customer to assign additional 5%
    public List<Product> findByCategory(String category) {

        List<Product> products = productRepo.findProductByCategory(Category.valueOf(category.toUpperCase(Locale.ROOT)));
        for(Product product: products){
            calculateDiscounts(product);
        }
       return products;
    }

    public Product findProductById(Long id) {
        Product product = productRepo.findById(id).get();
        if(product!=null) {
            calculateDiscounts(product);
        }
        return product;
    }

    protected void calculateDiscounts(Product product) {
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

    public void addDiscountToProducts(List<Product> products) {
        for(Product product: products) {
            if(product.getDiscount().getId()!=0) {
                Discount assignableDisc = discountService.findById(product.getDiscount().getId());
                Product p = productRepo.findById(product.getId()).get();
                p.setDiscount(assignableDisc);
                productRepo.save(p);
            }
        }
    }

    public void removeDiscountFromProduct(Long id) {
        Product p = productRepo.findById(id).get();
        p.setDiscount(null);
        productRepo.save(p);
    }

    public List<Product> getTopSoldProductsByCategory(String category) {

        List<Product> topSold = productRepo.getTopProductsByCategory(Category.valueOf(category.toUpperCase(Locale.ROOT)));
        for(Product product: topSold){
            calculateDiscounts(product);
        }
        return topSold;
    }

    public List<Product> getByPriceAscByCategory(String category) {

        List<Product> byPriceAsc = productRepo.getByPriceAscByCategory(Category.valueOf(category.toUpperCase(Locale.ROOT)));
        for(Product product: byPriceAsc){
            calculateDiscounts(product);
        }
        return byPriceAsc;
    }

}

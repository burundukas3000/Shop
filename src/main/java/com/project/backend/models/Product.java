package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private BigDecimal price;
    // not for DB use
    @Transient
    private BigDecimal happyPrice;
    @Transient
    private BigDecimal loyalPrice;
    @NotNull
    private String info;
    @NotNull
    private int amount;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;
    @OneToMany(fetch=FetchType.EAGER, mappedBy="product")
    private List<Image> images = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
    // one product can be in different purchases. Bidirectional mapping.
    // fetchType = default -lazy. Using getter to get purchases
    // cascade = all operations except DELETE are performed on associated table
    @ManyToMany(mappedBy="products",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    private Set<Purchase> purchases = new HashSet<Purchase>();

    public Product() {
    }

    public Product(String title, BigDecimal price, String info,
                   int amount, Category category, List<Image> images,
                   Discount discount, Set<Purchase> purchases) {
        this.title = title;
        this.price = price;
        this.info = info;
        this.amount = amount;
        this.category = category;
        this.images = images;
        this.purchases = purchases;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    // for @ManyToMany association - methods to add/delete purchase
    // returns true if success
    public boolean addPurchase(Purchase purchase) {
        boolean added = this.purchases.add(purchase);
        boolean added2 = purchase.getProducts().add(this);

        return added && added2;
    }

    public boolean deleteProduct(Product product) {
        boolean deleted = this.purchases.remove(product);
        boolean deleted2 = product.getPurchases().remove(this);

        return deleted && deleted2;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public BigDecimal getHappyPrice() { return happyPrice; }

    public void setHappyPrice(BigDecimal happyPrice) { this.happyPrice = happyPrice; }

    public BigDecimal getLoyalPrice() { return loyalPrice; }

    public void setLoyalPrice(BigDecimal loyalPrice) { this.loyalPrice = loyalPrice; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", discount=" + discount +
                '}';
    }
}
package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
    @NotNull
    @Column(name="date_created")
    private Date dateCreated;
    @Column(name="date_completed")
    private Date dateCompleted;
    @Column(name="date_cancelled")
    private Date dateCancelled;
    @Column(name="product_costs")
    private BigDecimal productCosts;
    @Column(name="shipping_costs")
    private BigDecimal shippingCosts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // one Purchase can have many Products and opposite. Bidirectional mapping.
    // fetchType = default -lazy. Setting to eager - getting products when getting purchase.
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="purchases_products",
            joinColumns = { @JoinColumn(name="purchase_id") },
            inverseJoinColumns = { @JoinColumn(name="product_id")})
    private Set<Product> products = new HashSet<Product>();

    // for @ManyToMany association - methods to add/delete product
    // returns true if success
    public boolean addProduct(Product product) {
        boolean added = this.products.add(product);
        boolean added2 = product.getPurchases().add(this);

        return added && added2;
    }

    public boolean deleteProduct(Product product) {
        boolean deleted = this.products.remove(product);
        boolean deleted2 = product.getPurchases().remove(this);

        return deleted && deleted2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Date getDateCancelled() {
        return dateCancelled;
    }

    public void setDateCancelled(Date dateCancelled) {
        this.dateCancelled = dateCancelled;
    }

    public BigDecimal getProductCosts() {
        return productCosts;
    }

    public void setProductCosts(BigDecimal productCosts) {
        this.productCosts = productCosts;
    }

    public BigDecimal getShippingCosts() {
        return shippingCosts;
    }

    public void setShippingCosts(BigDecimal shippingCosts) {
        this.shippingCosts = shippingCosts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateCompleted=" + dateCompleted +
                ", dateCancelled=" + dateCancelled +
                ", productCosts=" + productCosts +
                ", shippingCosts=" + shippingCosts +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
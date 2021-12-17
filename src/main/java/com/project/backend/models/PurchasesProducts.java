package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name ="purchases_products")
public class PurchasesProducts {

    @EmbeddedId
    private PurchaseProductId id = new PurchaseProductId();

    @ManyToOne
    @MapsId("purchaseId")
    private Purchase purchase;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @NotNull
    private int amount;

    public PurchasesProducts() {
    }

    public PurchaseProductId getId() {
        return id;
    }

    public void setId(PurchaseProductId id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PurchasesProducts{" +
                "id=" + id +
                ", purchase=" + purchase +
                ", product=" + product +
                ", amount=" + amount +
                '}';
    }
}

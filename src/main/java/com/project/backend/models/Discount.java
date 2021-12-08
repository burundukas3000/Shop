package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String promo;
    @Column(name="date_from")
    private Date dateFrom;
    @Column(name="date_to")
    private Date dateTo;
    @NotNull
    private int percentage;
    @OneToMany(mappedBy="discount")
    private List<Product> product = new ArrayList<>();

    public Discount() {
    }

    public Discount(String promo, Date dateFrom, Date dateTo, int percentage, List<Product> product) {
        this.promo = promo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.percentage = percentage;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", promo='" + promo + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", percentage=" + percentage+
                '}';
    }
}
package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

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
}

package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Status status;
    @NotNull
    @Column(name="date_created")
    private Date dateCreated;
    @Column(name="date_completed")
    private Date dateCompleted;
    @Column(name="date_cancelled")
    private Date dateCancelled;

    @OneToOne
    private User user;



}
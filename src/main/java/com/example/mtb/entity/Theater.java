package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String theaterId;
    private String name;
    private String address;
    private String city;
    private String landmark;
    private Long createAt;
    private  Long updatedAt;
    private String createdBy;

    @ManyToOne()
    private TheaterOwner theaterOwner;
}

package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "theater_id",nullable = false,updatable = false)
    private String theaterId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "landmark",nullable = false)
    private String landmark;

    @CreatedDate
    @Column(name = "create_at",nullable = false,updatable = false)
    private Instant createAt;

    @LastModifiedDate
    @Column(name = "update_at",nullable = false,updatable = false)
    private Instant updatedAt;

    @CreatedBy
    @Column(name = "create_by",nullable = false,updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "update_by",nullable = false)
    private String updatedBy;

    @ManyToOne
    private TheaterOwner theaterOwner;

    @OneToMany(mappedBy = "theater")
    private List<Screen> screen;


    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    private List<Show> shows;
}

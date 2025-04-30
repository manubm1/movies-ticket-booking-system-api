package com.example.mtb.entity;

import com.example.mtb.enums.ScreenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "screen_id",nullable = false,updatable = false)
    private String screenId;

    @Column(name = "screen_type",nullable = false,updatable = false)
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;


    @Column(name = "capacity",nullable = false,updatable = false)
    private int capacity;


    @Column(name = "noOfRows",nullable = false,updatable = false)
    private int noOfRows;

     @CreatedDate
    @Column(name = "created_At",nullable = false,updatable = false)
    private Long createdAt;

   @LastModifiedDate
    @Column(name = "update_at",nullable = false,updatable = false)
    private Long updatedAt;

    @CreatedBy
    @Column(name = "create_by",nullable = false,updatable = false)
    private String createdBy;


    @LastModifiedBy
    @Column(name = "update_by",nullable = false)
    private String updatedBy;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screen",fetch = FetchType.EAGER)
    private List<Seat> seat;

}

package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name="shows")
@EntityListeners(AuditingEntityListener.class)
public class Show {

    @Id
    @Column(name = "show_id",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showId;

    @Column(name = "start_at",nullable = false,updatable = false)
    private Instant startsAt;

    @Column(name = "ends_at",nullable = false,updatable = false)
    private Instant endsAt;

    @Column(name = "create_at",nullable = false,updatable = false)
    @CreatedDate
    private Instant createAt;


    @Column(name = "update_at",nullable = false,updatable = false)
    @LastModifiedDate
    private Instant updatedAt;


    @Column(name = "create_by",nullable = false,updatable = false)
    @CreatedBy
    private String createdBy;


    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Movie movie;

}

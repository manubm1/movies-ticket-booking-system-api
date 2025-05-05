package com.example.mtb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "feedback")
@EntityListeners(AuditingEntityListener.class)
public class Feedback {

    @Id
    @Column(name = "feedback_id",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String feedbackId;

    @Column(name = "ratings",updatable = false,nullable = false)
    private double rating;

    @Column(name = "reviews",updatable = false,nullable = false)
    private String reviews;

    @CreatedDate
    @Column(name = "created_at",updatable = false,nullable = false)
    private Instant createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;


}

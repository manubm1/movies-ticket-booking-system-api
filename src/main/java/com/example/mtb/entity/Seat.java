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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "seat_id",nullable = false,updatable = false)
    private String seatId;

    @Column(name = "seat_name",nullable = false,updatable = false)
    private String seatname;

    @CreatedDate
    @Column(name = "create_at",nullable = false,updatable = false)
    private Instant createAt;

    @ManyToOne
    private Screen screen;


    @Override
    public String toString() {
        return "Seat{" +
                "seatId='" + seatId + '\'' +
                ", seatname='" + seatname + '\'' +
                '}';
    }
}

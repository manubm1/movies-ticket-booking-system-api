package com.example.mtb.entity;

import com.example.mtb.enums.ScreenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;
    private int capacity;
    private int noOfRows;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screen",fetch = FetchType.EAGER)
    private List<Seat> seat;

}

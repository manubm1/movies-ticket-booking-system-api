package com.example.mtb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Setter;

import java.util.List;

@Entity
@Setter

public class User  extends UserDetails{


    @OneToMany(mappedBy = "user")
    private List<Feedback> feedback;

}

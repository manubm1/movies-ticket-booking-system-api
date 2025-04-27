package com.example.mtb.repository;

import com.example.mtb.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails,String> {


    public boolean existsByEmail(String email);
     public UserDetails findByEmail(String email);
}

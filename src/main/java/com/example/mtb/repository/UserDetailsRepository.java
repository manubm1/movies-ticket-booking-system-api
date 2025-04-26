package com.example.mtb.repository;

import com.example.mtb.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,String> {


    public boolean existsByEmail(String email);


}

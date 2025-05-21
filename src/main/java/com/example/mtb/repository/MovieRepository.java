package com.example.mtb.repository;

import com.example.mtb.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,String> {

    List<Movie> findByTitleContainingIgnoreCase(String title);
}

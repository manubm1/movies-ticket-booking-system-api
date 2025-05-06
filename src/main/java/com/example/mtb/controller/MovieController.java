package com.example.mtb.controller;

import com.example.mtb.dto.MovieResponse;
import com.example.mtb.responseBuilders.ResponseBuilder;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.service.MovieService;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class MovieController {


    private final MovieService movieService;
    private final ResponseBuilder responseBuilder;
  @GetMapping("/movie/{movieId}")
    public ResponseEntity<ResponseStructure<MovieResponse>>  findById(@PathVariable String movieId){
           MovieResponse response = movieService.findById(movieId);
           return responseBuilder.success(HttpStatus.FOUND,"Movie fetched successfully",response);


       }

}

package com.example.mtb.controller;

import com.example.mtb.dto.MovieFetchResponse;
import com.example.mtb.dto.MovieResponse;
import com.example.mtb.responseBuilders.ResponseBuilder;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.service.MovieService;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

  @GetMapping("movies")
  public ResponseEntity<ResponseStructure<List<MovieFetchResponse>>> findByname(@RequestParam String search){

      List<MovieFetchResponse> response = movieService.findByName(search);
      return responseBuilder.success(HttpStatus.FOUND,"fetched successfully",response);


  }

}

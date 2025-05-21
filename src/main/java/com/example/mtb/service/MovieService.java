package com.example.mtb.service;

import com.example.mtb.dto.MovieFetchResponse;
import com.example.mtb.dto.MovieResponse;

import java.util.List;

public interface MovieService {

    MovieResponse findById(String movieId);

   List<MovieFetchResponse> findByName(String name);
}

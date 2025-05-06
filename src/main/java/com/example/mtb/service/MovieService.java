package com.example.mtb.service;

import com.example.mtb.dto.MovieResponse;

public interface MovieService {

    MovieResponse findById(String movieId);
}

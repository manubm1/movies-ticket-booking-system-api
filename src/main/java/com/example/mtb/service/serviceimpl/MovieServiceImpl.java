package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.CastResponse;
import com.example.mtb.dto.MovieResponse;
import com.example.mtb.entity.Feedback;
import com.example.mtb.entity.Movie;
import com.example.mtb.exception.MovieNotFoundException;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {


    private final MovieRepository movieRepository;


    @Override
    public MovieResponse findById(String movieId) {
        Optional<Movie> optinalmovie = Optional.ofNullable(movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(" Movie not founded by this ID")));

        Movie movie = optinalmovie.get();
        List<Feedback> feedbacks = movie.getFeedbacks();
        double sum = 0;
        int i = 0;
        for (Feedback feedback : feedbacks) {
            i = +5;
            sum = +feedback.getRating();

        }
        double average = sum % i;
        String averages= average+"/5";



        Set<String> casts = movie.getCast();
        Set<CastResponse> castResponse = new HashSet<>();

        if (!casts.isEmpty()) {
            for (String cast : casts) {
                String caste = cast;
                CastResponse response = new CastResponse(caste);
                castResponse.add(response);
            }
        }
        return new MovieResponse(movie.getTitle(), movie.getDescription(), castResponse, movie.getCertificate(), movie.getGenre(), averages);
    }
}

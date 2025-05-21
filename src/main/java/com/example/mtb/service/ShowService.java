package com.example.mtb.service;

import com.example.mtb.dto.ShowProjection;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.dto.ShowsRequest;
import com.example.mtb.entity.Show;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;

public interface ShowService {
    ShowResponse createShow(String theaterId, String screenId, String movieId, Instant startsAt);

    ShowResponse findShows(String theaterID);

    Page<ShowProjection> findShowsByMovieId(String movieId, ShowsRequest request,String city);
}

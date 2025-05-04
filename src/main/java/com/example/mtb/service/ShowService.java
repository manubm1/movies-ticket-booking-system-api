package com.example.mtb.service;

import com.example.mtb.dto.ShowResponse;

import java.time.Instant;

public interface ShowService {
    ShowResponse createShow(String theaterId, String screenId, String movieId, Instant startsAt);
}

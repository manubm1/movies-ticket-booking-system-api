package com.example.mtb.dto;

import com.example.mtb.entity.Show;

import java.util.List;

public record ShowProjection(String theaterId,
                             String theatername,
                             String adress,
                             List<ShowResponse> shows) {
}

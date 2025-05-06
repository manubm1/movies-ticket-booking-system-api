package com.example.mtb.dto;

import com.example.mtb.enums.Certificate;
import com.example.mtb.enums.Gener;

import java.util.Set;

public record MovieResponse(String title,
                            String description,
                            Set<CastResponse> cast,
                            Certificate certificate,
                            Gener gener,
                            double rating) {
}

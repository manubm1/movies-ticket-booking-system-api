package com.example.mtb.dto;

import com.example.mtb.enums.Certificate;
import com.example.mtb.enums.Gener;

import java.util.Set;

public record MovieFetchResponse(String title,
                                 String movieId,
                                 String description,
                                 String cast,
                                 Certificate certificate,
                                 Gener gener) {
}

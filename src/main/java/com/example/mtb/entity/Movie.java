package com.example.mtb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;

@Entity
public class Movie {

      private String movieId;
      private String title;
      private String description;
      private String[] cast;
      private Duration runtime;
      private certificate certificate;
      private gener  gener;

}

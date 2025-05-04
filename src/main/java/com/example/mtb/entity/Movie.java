package com.example.mtb.entity;

import com.example.mtb.enums.Certificate;
import com.example.mtb.enums.Gener;
import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Set;
@Component
@Entity
@Getter
@Setter
@Table(name = "movie")
@ToString
public class Movie {


      @Id
      @GeneratedValue(strategy = GenerationType.UUID)
      private String movieId;

      @Column(name = "title")
      private String title;

      @Column(name = "description")
      private String description;

      @ElementCollection
      private Set<String> cast;

      @Column(name = "runtime")
      private Duration runtime;


      @Enumerated(value = EnumType.STRING)
      @Column(name = "certificate")
      private Certificate certificate;


      @Enumerated(value = EnumType.STRING)
      @Column(name = "genre")
      private Gener genre;

      @OneToMany(mappedBy = "movie")
      private List<Show> show;

}

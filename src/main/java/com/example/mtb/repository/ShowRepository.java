package com.example.mtb.repository;

import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import com.example.mtb.enums.ScreenType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,String> {



   public  List<Show> findByScreen_ScreenId(String screenId);


   @Query("""
    SELECT s
    FROM Show s
    JOIN s.screen sc
    JOIN s.theater t
    WHERE s.movie.id = :movieId
      AND s.startsAt BETWEEN :startTime AND :endTime
      AND sc.screenType = :screenType
      AND t.city = :city
""")
   Page<Show> findDistinctTheaterIdsByMovieAndTimeAndScreenTypeAndCity(
           @Param("movieId") String movieId,
           @Param("startTime") Instant startTime,
           @Param("endTime") Instant endTime,
           @Param("screenType") ScreenType screenType,
           @Param("city") String city,
           Pageable pageable
   );

}

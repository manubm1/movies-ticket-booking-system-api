package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.ShowResponse;
import com.example.mtb.entity.Movie;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import com.example.mtb.entity.Theater;
import com.example.mtb.exception.MovieNotFoundException;
import com.example.mtb.exception.ScreenNotFoundException;
import com.example.mtb.exception.ShowNotFoundException;
import com.example.mtb.exception.TheaterNotFoundException;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.ShowRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ShowService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShowImpl implements ShowService {

    private final ShowRepository showRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;


    @Override
    public ShowResponse createShow(String theaterId, String screenId, String movieId, Instant startsAt) {
        Optional<Theater> optinalTheater = Optional.ofNullable(theaterRepository.findById(theaterId)
                .orElseThrow(() -> new TheaterNotFoundException("theater not found ")));
        Theater theater = optinalTheater.get();
        Optional<Screen> optionalScreen = Optional.ofNullable(screenRepository.findById(screenId)
                .orElseThrow(() -> new ScreenNotFoundException("")));
        Screen screen = optionalScreen.get();
        Optional<Movie> optinalMovie = Optional.ofNullable(movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found exception")));
        Movie movie = optinalMovie.get();



        Duration duration = Duration.ofMinutes(movie.getRuntime().toNanos());
        Instant ends = (startsAt.plus(duration));
        List<Show> listShow = showRepository.findByScreen_ScreenId(screenId);
        for(Show shows :listShow){
            boolean overlap = (startsAt.isBefore(shows.getEndsAt()) && ends.isAfter(shows.getStartsAt())) ;
            System.out.println(overlap);
            if(overlap){
                throw new ShowNotFoundException(" Show already fixed for this time. But  you can try for different timings");
            }
        }

             Show show = new Show();
             show.setStartsAt(startsAt);
            show.setEndsAt(startsAt.plus(duration));
            show.setScreen(screen);

            List<Show> showlist = new ArrayList<>();
            showlist.add(show);
            screen.setShow(showlist);

            movie.setShow(showlist);
            show.setMovie(movie);


            showRepository.save(show);
            screenRepository.save(screen);
            movieRepository.save(movie);

            return new ShowResponse(show.getShowId(), show.getStartsAt(), show.getEndsAt());


    }

    @Override
    public ShowResponse findShows(String theaterId) {

        Optional<Show> optionalTheater = Optional.ofNullable(showRepository.findById(theaterId)
                .orElseThrow(() -> new TheaterNotFoundException("there is no theater")));
//        Show shows = optionalTheater.get();

        if(optionalTheater.isPresent()) {
            Show shows = optionalTheater.get();
            return new ShowResponse(shows.getShowId(), shows.getStartsAt(), shows.getEndsAt());
        }
        else
            throw  new ShowNotFoundException("ther is no shows ");
    }
}

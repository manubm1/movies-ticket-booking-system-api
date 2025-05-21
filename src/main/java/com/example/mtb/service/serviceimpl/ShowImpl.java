package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.*;
import com.example.mtb.entity.Movie;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import com.example.mtb.entity.Theater;
import com.example.mtb.exception.*;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.ShowRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ShowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
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
            show.setTheater(theater);
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

    @Override
    public Page<ShowProjection> findShowsByMovieId(String movieId, ShowsRequest request,String city) {

//        Pageable pages = PageRequest.of(request.number(), request.size());
        int pageNumber = Math.max(0, request.number());  // prevents negative or too-high values
        int pageSize = request.size();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        ZoneId zoneId = request.zoneId() == null || request.zoneId().isBlank()
                ? ZoneId.of("UTC")
                : ZoneId.of(ZoneId.SHORT_IDS.getOrDefault(request.zoneId(), "UTC"));

        ZonedDateTime start = request.date().atStartOfDay(zoneId);
        ZonedDateTime end = request.date().plusDays(1).atStartOfDay(zoneId);

        Instant startAt = start.toInstant();
        Instant endsAt = end.toInstant();

        log.info(String.valueOf(startAt));
        log.info(String.valueOf(endsAt));


          Optional<Theater> theaters= Optional.ofNullable(Optional.ofNullable(theaterRepository.findByCity(city))
                  .orElseThrow(() -> new CityNotFoundException(" Invalid City")));

        Page<Show> showspage = showRepository.findDistinctTheaterIdsByMovieAndTimeAndScreenTypeAndCity(movieId,startAt,endsAt,request.screenType(),city,pageable);




//
        List<Show> showsList =showspage.getContent();
//        List<ShowResponse> shows = new ArrayList<>();
//
//        for(Show show :showsList){
//            ShowResponse response = new ShowResponse(show.getShowId(),show.getStartsAt(),show.getEndsAt());
//            shows.add(response);
//        }
//
//        Set<ShowMovieResponse> sets = new HashSet<>();
//
//        for(Show show :showsList){
//            ShowMovieResponse response = new ShowMovieResponse(show.getTheater().getTheaterId(),
//                    show.getTheater().getName(),show.getTheater().getAddress());
//            sets.add(response);
////        }
//
//
//        Page<ShowProjection> projections = showspage.map(show -> new ShowProjection(
//                show.getTheater().getTheaterId(),
//                show.getTheater().getName(),
//                show.getTheater().getAddress(),
//                shows// or null, or multiple shows if grouped
//        ));
//
//
//
//        return projections;
//
//
//



























        Map<String, List<Show>> groupedByTheater = showsList.stream()
                .collect(Collectors.groupingBy(show -> show.getTheater().getTheaterId()));

// Step 2: Convert to ShowProjections
        List<ShowProjection> projectionList = groupedByTheater.entrySet().stream()
                .map(entry -> {
                    String theaterId = entry.getKey();
                    List<Show> theaterShows = entry.getValue();
                    Theater theater = theaterShows.get(0).getTheater(); // all shows share the same theater

                    List<ShowResponse> theaterShowResponses = theaterShows.stream()
                            .map(show -> new ShowResponse(show.getShowId(), show.getStartsAt(), show.getEndsAt()))
                            .collect(Collectors.toList());

                    return new ShowProjection(
                            theaterId,
                            theater.getName(),
                            theater.getAddress(),
                            theaterShowResponses
                    );
                })
                .toList();

// Step 3: Create a Page manually (if needed)
        Page<ShowProjection> projections = new PageImpl<>(projectionList, pageable, projectionList.size());













  return projections;
    }
}

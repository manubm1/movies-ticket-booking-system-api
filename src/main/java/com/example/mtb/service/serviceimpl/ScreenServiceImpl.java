package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.ScreenRegistrationRequest;
import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.dto.SeatResponse;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Seat;
import com.example.mtb.entity.Theater;
import com.example.mtb.exception.ScreenNotFoundException;
import com.example.mtb.exception.TheaterNotFoundException;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ScreenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterRepository theaterRepository;
     private final SeatService seatService;

    @Override
    public Screen screenRegistration(String theaterId, ScreenRegistrationRequest request) {

        Optional<Theater> optionalScreen = theaterRepository.findById(theaterId);

        if(optionalScreen.isPresent()){
            Theater theater = optionalScreen.get();
            Screen screen = new Screen();
            screen.setScreenType(request.screenType());
            screen.setCapacity(request.capacity());
            screen.setNoOfRows(request.noOfRows());
//            screen.setCreatedBy(request.createdBy());
//            screen.setCreatedAt(request.createdAt());
//            screen.setUpdatedAt(request.updatedAt());




            List<Screen> screenlist = new ArrayList<>();
            screenlist.add(screen);
            theater.setScreen(screenlist);
            screen.setTheater(theater);
            screenRepository.save(screen);
            seatService.genearateSeats(screen);
            theaterRepository.save(theater);


            return screen;

        }else
            throw new TheaterNotFoundException(" Theater not found");

    }


    @Override
    public ScreenResponse findScreen(String screenId) {
        Optional<Screen> optionalScreen = screenRepository.findById(screenId);

        if(optionalScreen.isPresent()){
            Screen screen = optionalScreen.get();
            List<Seat> seatlist = screen.getSeat();

              List<SeatResponse> seatResponses= new ArrayList<>();


            for(Seat seats :seatlist){
                  SeatResponse response = new SeatResponse(seats.getSeatId(),seats.getSeatname());
               seatResponses.add(response);

            }

            Collections.sort(seatResponses);
            return new ScreenResponse(screen.getScreenType(),screen.getCapacity(),screen.getNoOfRows(),seatResponses);


        }else
            throw new ScreenNotFoundException("Screen not found by this Screen ID");
    }
}

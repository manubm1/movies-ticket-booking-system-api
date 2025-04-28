package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.ScreenRegistrationRequest;
import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Theater;
import com.example.mtb.exception.ScreenNotFoundException;
import com.example.mtb.exception.TheaterNotFoundException;
import com.example.mtb.repository.ScreenRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.service.ScreenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterRepository theaterRepository;
//    private final SeatRepository seatRepository;

    @Override
    public ScreenResponse screenRegistration(String theaterId, ScreenRegistrationRequest request) {

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

//            seatRepository.save(SeattService.genearateSeats(screen);
            screenRepository.save(screen);
            theaterRepository.save(theater);


            return new ScreenResponse(request.screenType(),request.capacity(),request.noOfRows());

        }else
            throw new TheaterNotFoundException(" Theater not found");

    }


    @Override
    public ScreenResponse findScreen(String screenId) {
        Optional<Screen> optionalScreen = screenRepository.findById(screenId);

        if(optionalScreen.isPresent()){
            Screen screen = optionalScreen.get();

            return new ScreenResponse(screen.getScreenType(),screen.getCapacity(),screen.getNoOfRows());


        }else
            throw new ScreenNotFoundException("Screen not found by this Screen ID");
    }
}

package com.example.mtb.service.serviceimpl;

import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Seat;
import com.example.mtb.repository.SeatRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {

    private SeatRepository seatRepository;

    public void genearateSeats(Screen screen) {


        int capacity = screen.getCapacity();
        int noOfrows = screen.getNoOfRows();
        char name = 'A';


        for (int i = 1; i <= capacity / noOfrows; i++) {

            for (int j = 0; j < noOfrows; j++) {

                Seat seats = new Seat();
                seats.setSeatname( name+"-" + j);
                seats.setScreen(screen);
                seatRepository.save(seats);

                List<Seat> seatList = new ArrayList<>();
                seatList.add(seats);
                screen.setSeat(seatList);
            }
            name++;
        }
    }
}

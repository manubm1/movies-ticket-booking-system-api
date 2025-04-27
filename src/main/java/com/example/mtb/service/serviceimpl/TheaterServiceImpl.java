package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.TheaterRegistrationRequest;
import com.example.mtb.dto.TheaterResponse;
import com.example.mtb.entity.Theater;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.exception.TheaterNotFoundException;
import com.example.mtb.exception.TheaterOwnerNotFoundException;
import com.example.mtb.exception.UserNotFoundException;
import com.example.mtb.repository.TheaterOwnerRepository;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.mtb.enums.UserRole.THEATER_OWNER;

@Service
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterrepository;
    private final UserDetailsRepository userDetailsRepository;
    private  final TheaterOwnerRepository theaterOwnerRepository;
    @Override
    public TheaterResponse registration(String email, TheaterRegistrationRequest request) {
        Optional<UserDetails> optionalUserDetails = Optional.ofNullable(userDetailsRepository.findByEmail(email));

        if (optionalUserDetails.isPresent()) {
            UserDetails user = optionalUserDetails.get();
            if (user.getUserRole() == THEATER_OWNER) {
                Theater theater = new Theater();
                theater.setName(request.name());
                theater.setAddress(request.address());
                theater.setCity(request.city());
                theater.setLandmark(request.landmark());

                List<Theater> theaterlist = new ArrayList<>();
                theaterlist.add(theater);


                TheaterOwner owner = (TheaterOwner) user;
                owner.setTheater(theaterlist);
                theater.setTheaterOwner(owner);

                theaterrepository.save(theater);
                theaterOwnerRepository.save(owner);
                return new TheaterResponse(theater.getTheaterId(),theater.getName(), theater.getAddress(), theater.getCity(), theater.getLandmark());

            } else
                throw new TheaterOwnerNotFoundException("user not a theater owner");

        } else
            throw new UserNotFoundException("User Not found");
    }

    @Override
    public TheaterResponse findById(String theaterId) {
        Optional<Theater> optionalTheater = theaterrepository.findById(theaterId);
        if(optionalTheater.isPresent()){
            Theater theater = optionalTheater.get();
            return new TheaterResponse(theater.getTheaterId(),theater.getName(),theater.getAddress(),theater.getCity(),theater.getLandmark());
        }else
            throw new TheaterNotFoundException(" Theater not exist");

    }
}

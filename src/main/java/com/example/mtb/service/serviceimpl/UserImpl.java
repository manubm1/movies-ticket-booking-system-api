package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.enums.UserRole;
import com.example.mtb.exception.UserRegistrationException;
import com.example.mtb.repository.UserDetailsRepository;
import com.example.mtb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserImpl  implements  UserService {

    private final UserDetailsRepository userDetailsRepository;


    @Override
    public UserResponse userRegistration(UserRegistrationRequest users) {
        if(userDetailsRepository.existsByEmail(users.email())) {
            throw new UserRegistrationException(" User already exists");
        }
        else {

            if (users.userRole() == UserRole.USER) {
                User user = new User();
                user.setUsername(users.username());
                user.setEmail(users.email());
                user.setUserRole(users.userRole());
                user.setPassword(users.password());
                user.setPhoneNumber(users.phoneNumber());
                user.setDateOfBirth(users.dateOfBirth());

                userDetailsRepository.save(user);
                return new UserResponse(user.getUserId(),user.getUsername(),user.getEmail(),user.getPhoneNumber(),user.getUserRole(),user.getDateOfBirth());
            } else {

                TheaterOwner owner = new TheaterOwner();

                owner.setUsername(users.username());
                owner.setEmail(users.email());
                owner.setUserRole(users.userRole());
                owner.setPassword(users.password());
                owner.setPhoneNumber(users.phoneNumber());
                owner.setDateOfBirth(users.dateOfBirth());

                return new UserResponse(owner.getUserId(),owner.getUsername(),owner.getEmail(),owner.getPhoneNumber(),owner.getUserRole(),owner.getDateOfBirth());


            }
        }
    }
}


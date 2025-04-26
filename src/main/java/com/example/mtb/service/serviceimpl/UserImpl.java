package com.example.mtb.service.serviceimpl;

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
    public UserDetails userRegistration(UserDetails users) {
        if(userDetailsRepository.existsByEmail(users.getEmail())) {
            throw new UserRegistrationException(" User already exists");
        }
        else {

            if (users.getUserRole() == UserRole.USER) {
                User user = new User();
                user.setUsername(users.getUsername());
                user.setEmail(users.getEmail());
                user.setUserRole(users.getUserRole());
                user.setPassword(users.getPassword());
                user.setPhoneNumber(users.getPhoneNumber());
                user.setDataOfBirth(users.getDataOfBirth());
                user.setCreateAt(users.getCreateAt());
                user.setUpdateAt(users.getUpdateAt());
                userDetailsRepository.save(user);
                return user;
            } else {

                TheaterOwner owner = new TheaterOwner();

                owner.setUsername(users.getUsername());
                owner.setEmail(users.getEmail());
                owner.setUserRole(users.getUserRole());
                owner.setPassword(users.getPassword());
                owner.setPhoneNumber(users.getPhoneNumber());
                owner.setDataOfBirth(users.getDataOfBirth());
                owner.setCreateAt(users.getCreateAt());
                owner.setUpdateAt(users.getUpdateAt());
                return userDetailsRepository.save(owner);

            }
        }
    }
}


package org.example.rfshop.User.Application.GetUserByEmail;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.User.Infrastructure.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserByEmailImpl implements GetUserByEmail {

    private final UserRepository userRepository;

    public GetUserByEmailImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(String email) {
      return this.userRepository.findUserByEmail(email).orElseThrow(()-> new EntityNotFoundException("User with email "+email+"found"));
    }
}

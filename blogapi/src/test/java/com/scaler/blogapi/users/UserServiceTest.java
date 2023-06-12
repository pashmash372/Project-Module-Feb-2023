package com.scaler.blogapi.users;

import com.scaler.blogapi.security.jwt.JWTService;
import com.scaler.blogapi.users.dtos.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    private UserService getUserService() {
        if (userService == null) {

            var modelMapper = new ModelMapper();
            var passwordEncoder = new BCryptPasswordEncoder();
            var jwtService = new JWTService();
            userService = new UserService(userRepository, modelMapper, passwordEncoder, jwtService);
        }
        return userService;
    }

    @Test
    public void testCreateUser() {
        var newUserDTO = new CreateUserDTO();
        newUserDTO.setEmail("arnav@email.com");
        newUserDTO.setUsername("arnav123");
        newUserDTO.setPassword("password");
        var savedUser = getUserService().createUser(newUserDTO);
        assertNotNull(savedUser.getId());
    }
}
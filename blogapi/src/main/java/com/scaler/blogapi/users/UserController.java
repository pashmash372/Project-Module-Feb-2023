package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dtos.CreateUserDTO;
import com.scaler.blogapi.users.dtos.LoginUserDTO;
import com.scaler.blogapi.users.dtos.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        var savedUser = userService.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(
            @RequestBody LoginUserDTO loginUserDTO,
            @RequestParam(name="token",defaultValue = "jwt") String token
    ) {
//        if token == "jwt" (default) generate jwt , if token == "auth" generate auth token
        var authType = UserService.AuthType.JWT;
        if (token.equals("auth_token")) {
            authType = UserService.AuthType.AUTH_TOKEN;
        }
        var savedUser = userService.loginUser(loginUserDTO, authType);
        return ResponseEntity.ok(savedUser);
    }

    @ExceptionHandler(UserService.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserService.UserNotFoundException e) {
        return ResponseEntity
                .notFound()
                .header("reason", e.getMessage())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .badRequest()
                .header("reason", e.getMessage())
                .build();
    }
}

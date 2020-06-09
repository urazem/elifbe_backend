package com.urazem.elifbe.rest;


import com.urazem.elifbe.config.jwt.JwtTokenProvider;
import com.urazem.elifbe.model.user.User;
import com.urazem.elifbe.modelDto.AuthenticationRequestDto;
import com.urazem.elifbe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationRestController {
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtTokenProvider jwtTokenProvider;

        @Autowired
        private UserService userService;

    @PostMapping(path = "login")
    @CrossOrigin("*")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

            String token = jwtTokenProvider.createToken(username);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("registration")
    @CrossOrigin("*")
    public ResponseEntity<Object> addNewUser (@RequestBody AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        String email = requestDto.getEmail();
        Map<Object, Object> response = new HashMap<>();

        if(userService.existsUser(username, email)){
            response.put("error", HttpStatus.ALREADY_REPORTED);
            response.put("message", "Такой пользователь уже существует");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {

            User registeredUser = userService.createUser(username,email,requestDto.getPassword());
            log.info("In register user {} successfully", response);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        }
    }
}
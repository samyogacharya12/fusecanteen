package com.example.fusecanteen.controller;

import com.example.fusecanteen.dto.UserDto;
import com.example.fusecanteen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;


    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        userDto = userService.save(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @GetMapping("/api/users")
    public ResponseEntity<?> findAll() {
        List<UserDto> userDtoList = userService.findAll();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);

    }

    @GetMapping("/noauth/user/{username}")
    public ResponseEntity<?> findByUserName(@PathVariable String username){
       UserDto userDto=userService.getUserByUsername(username);
       return new ResponseEntity<>(userDto, HttpStatus.OK);
    }



}

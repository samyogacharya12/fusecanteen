package com.example.fusecanteen.service;



import com.example.fusecanteen.dto.ChangePasswordDto;
import com.example.fusecanteen.dto.UserDto;
import com.example.fusecanteen.entity.User;

import java.util.List;

public interface UserService {



    User findUserByUserName(String userName);

    UserDto getUserByUsername(String username);

    UserDto findUserByUserId(Long id);

    User findByEmail(String email);

    UserDto save(UserDto userDto);


    List<UserDto> findAll();

    UserDto findUserById(Long id);

    List<String> findAllUsername();

    Boolean changePassword(ChangePasswordDto changePasswordDto);

}

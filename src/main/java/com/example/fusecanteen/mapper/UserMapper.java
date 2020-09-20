package com.example.fusecanteen.mapper;

import com.example.fusecanteen.dto.UserDto;
import com.example.fusecanteen.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}

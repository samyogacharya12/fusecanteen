package com.example.fusecanteen.service;


import com.example.fusecanteen.dto.ChangePasswordDto;
import com.example.fusecanteen.dto.UserDto;
import com.example.fusecanteen.entity.User;
import com.example.fusecanteen.entity.security.Role;
import com.example.fusecanteen.entity.security.UserRole;
import com.example.fusecanteen.mapper.UserMapper;
import com.example.fusecanteen.repository.RoleRepository;
import com.example.fusecanteen.repository.UserRepository;
import com.example.fusecanteen.repository.UserRoleRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final SecurityUtility securityUtility;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserRoleRepository userRoleRepository;


    public UserServiceImpl(final UserRepository userRepository,
                           final UserMapper userMapper,
                           final RoleRepository roleRepository,
                           final SecurityUtility securityUtility,
                           final UserRoleRepository userRoleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.securityUtility = securityUtility;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserDto userDto = userMapper.toDto(userRepository.findUserByUsername(username));
        return userDto;
    }

    @Override
    public UserDto findUserByUserId(Long id) {
        User user = userRepository.findUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto save(UserDto userDto) {
        logger.debug("saving user");
        userDto.setCreatedBy(SecurityUtility.getUserName());
        userDto.setLastModifiedBy(SecurityUtility.getUserName());
        userDto.setPassword(SecurityUtility.passwordEncoder().encode(userDto.getPassword()));
        User user = userMapper.toEntity(userDto);
        Role role = roleRepository.findByName(userDto.getUserType());
        role.setCreatedBy(SecurityUtility.getUserName());
        role.setLastModifiedBy(SecurityUtility.getUserName());
        UserRole userRole = new UserRole(user, role);
        userRole.setCreatedBy(SecurityUtility.getUserName());
        userRole.setLastModifiedBy(SecurityUtility.getUserName());
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setUserRoles(userRoles);
        user = userRepository.save(user);
        userRoleRepository.save(userRole);
        return userMapper.toDto(user);
    }


    @Override
    public List<UserDto> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public List<String> findAllUsername() {
        return userRepository.getUserNameList();
    }

    @Override
    public Boolean changePassword(ChangePasswordDto changePasswordDto) {

        Boolean valid = true;
        String username = securityUtility.getUserName();
        UserDto userDto = userMapper.toDto(userRepository.findUserByUsername(username));
        if (!BCrypt.checkpw(changePasswordDto.getOldPassword(), userDto.getPassword())) {
            valid = false;
        } else {
            userDto.setPassword(SecurityUtility.passwordEncoder().encode(changePasswordDto.getNewPassword()));
            userRepository.save(userMapper.toEntity(userDto));
        }
        return valid;
    }

}

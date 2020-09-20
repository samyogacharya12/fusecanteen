package com.example.fusecanteen.repository;


import com.example.fusecanteen.entity.User;
import com.example.fusecanteen.entity.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {


    UserRole findByUser(User user);



}

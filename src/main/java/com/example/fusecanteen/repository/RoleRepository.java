package com.example.fusecanteen.repository;

import com.example.fusecanteen.entity.security.Role;
import com.example.fusecanteen.enumconstant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   Role findByName(String name);


   @Query("select r from Role r where r.status=?1")
   List<Role> findAllByStatus(Status status);


}

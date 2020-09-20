package com.example.fusecanteen.repository;

import com.example.fusecanteen.entity.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {


    @Query("select max(i.name) from ItemRequest  i")
    String findMaxNoOfItemRequest();

    List<ItemRequest> findByName(String name);

}

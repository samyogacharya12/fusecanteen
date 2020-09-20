package com.example.fusecanteen.repository;

import com.example.fusecanteen.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    @Query("select i from Item i where i.created between :yesterday and :today")
    List<Item> findByCreatedBetween(@Param(value = "yesterday") Date yesterday, @Param(value = "today") Date today);



}

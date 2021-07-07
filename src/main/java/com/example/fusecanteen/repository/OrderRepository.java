package com.example.fusecanteen.repository;

import com.example.fusecanteen.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findAllByCreatedBetween(Date startDate, Date endDate);

}

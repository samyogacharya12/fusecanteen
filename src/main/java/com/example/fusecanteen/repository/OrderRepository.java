package com.example.fusecanteen.repository;

import com.example.fusecanteen.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {


}

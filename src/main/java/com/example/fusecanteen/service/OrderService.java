package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    OrderDto updateStatus(OrderDto orderDto) throws InterruptedException;

    OrderDto findById(Long id);

    List<OrderDto> findAll();

}

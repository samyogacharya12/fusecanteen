package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.CustomerDto;
import com.example.fusecanteen.dto.OrderDto;
import com.example.fusecanteen.entity.OrderItem;
import com.example.fusecanteen.enumconstant.Status;
import com.example.fusecanteen.mapper.OrderMapper;
import com.example.fusecanteen.repository.OrderRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger loggerFactory = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private final OrderMapper orderMapper;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final CustomerService customerService;



    public OrderServiceImpl(final OrderMapper orderMapper,
                            final OrderRepository orderRepository,
                            final CustomerService customerService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        loggerFactory.debug("saving order");
        CustomerDto customerDto = customerService.save(orderDto.getCustomer());
        orderDto.setCustomer(customerDto);
        orderDto.setCreatedBy(SecurityUtility.getUserName());
        orderDto.setLastModifiedBy(SecurityUtility.getUserName());
        OrderItem order = orderMapper.toEntity(orderDto);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDto scheduleOrderStatus(OrderDto orderDto) {
        return null;
    }


    @Override
    public OrderDto updateStatus(OrderDto orderDto) throws InterruptedException {
        if (!orderDto.getOrderStatus().contains(Status.Ready.toString())) {
            if (orderDto.getOrderStatus().equalsIgnoreCase(Status.Pending.toString())) {
                orderDto.setOrderStatus(Status.InProcess.toString());
                Thread.sleep(orderDto.getTime());
                orderDto.setCustomer(customerService.save(orderDto.getCustomer()));
                orderDto = save(orderDto);
            }
            if(orderDto.getOrderStatus().equalsIgnoreCase(Status.InProcess.toString())){
                orderDto.setOrderStatus(Status.Ready.toString());
                Thread.sleep(orderDto.getTime());
                orderDto.setCustomer(customerService.save(orderDto.getCustomer()));
                orderDto = save(orderDto);
            }
        }

        return orderDto;
    }

    @Override
    public OrderDto findById(Long id) {
        loggerFactory.debug("getting order by id");
        return orderMapper.toDto(orderRepository.getOne(id));
    }

    @Override
    public List<OrderDto> findAll() {
        loggerFactory.debug("getting all order");
        return orderMapper.toDto(orderRepository.findAll());
    }
}

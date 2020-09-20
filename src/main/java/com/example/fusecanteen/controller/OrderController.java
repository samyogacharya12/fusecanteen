package com.example.fusecanteen.controller;

import com.example.fusecanteen.dto.OrderDto;
import com.example.fusecanteen.service.ItemService;
import com.example.fusecanteen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OrderController {

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final OrderService orderService;


    public OrderController(final ItemService itemService,
                           final OrderService orderService) {
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> saveOrder(@RequestBody OrderDto orderDto) {
        orderDto = orderService.save(orderDto);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PutMapping("/schedule/order/{id}")
    public ResponseEntity<?> scheduleOrder(@PathVariable Long id) throws InterruptedException {
        OrderDto orderDto = orderService.findById(id);
        orderDto = orderService.updateStatus(orderDto);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/order")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/item/created")
    public ResponseEntity<?> itemForToday() {
        return new ResponseEntity<>(itemService.findByCreated(), HttpStatus.OK);
    }


}

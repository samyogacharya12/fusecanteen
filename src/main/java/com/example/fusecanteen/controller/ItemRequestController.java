package com.example.fusecanteen.controller;

import com.example.fusecanteen.dto.ItemRequestDto;
import com.example.fusecanteen.service.ItemRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ItemRequestController {


    @Autowired
    private final ItemRequestService itemRequestService;

    public ItemRequestController(final ItemRequestService itemRequestService) {
        this.itemRequestService = itemRequestService;
    }

    @PostMapping("/itemrequest")
    public ResponseEntity<?> save(@RequestBody ItemRequestDto itemRequestDto) {
        return new ResponseEntity<>(itemRequestService.save(itemRequestDto), HttpStatus.OK);
    }

    @GetMapping("/itemrequest/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(itemRequestService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/itemrequest")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(itemRequestService.findAll(), HttpStatus.OK);
    }

}

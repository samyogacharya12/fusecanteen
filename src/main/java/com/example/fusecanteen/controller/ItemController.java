package com.example.fusecanteen.controller;

import com.example.fusecanteen.dto.ItemDto;
import com.example.fusecanteen.service.ItemRequestService;
import com.example.fusecanteen.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ItemController {


    @Autowired
    private final ItemService itemService;

    @Autowired
    private final ItemRequestService itemRequestService;

    public ItemController(final ItemService itemService,
                          final ItemRequestService itemRequestService) {
        this.itemService = itemService;
        this.itemRequestService = itemRequestService;
    }

    @PostMapping("/item")
    public ResponseEntity<?> save(@RequestBody ItemDto itemDto) {
        itemDto = itemService.save(itemDto);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @GetMapping("/maxitemreq")
    public ResponseEntity<?> findMaxFoodRequest() {
        return new ResponseEntity<>(itemRequestService.findMaxReqFood(), HttpStatus.OK);
    }


    @GetMapping("/item/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ItemDto itemDto = itemService.findById(id);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }


    @GetMapping("/item")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        itemService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}

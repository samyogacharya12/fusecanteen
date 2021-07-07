package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.ItemDto;

import java.util.List;

public interface ItemService {


    ItemDto save(ItemDto itemDto);

    Boolean findItemByCode(Double code);


    List<ItemDto> findByCreated();




    ItemDto findById(Long id);

    List<ItemDto> findAll();

    void delete(Long id);

}

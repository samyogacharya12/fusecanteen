package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.ItemRequestDto;

import java.util.List;

public interface ItemRequestService {

    ItemRequestDto save(ItemRequestDto itemRequestDto);

    ItemRequestDto findById(Long id);

    List<ItemRequestDto> findAll();

    List<ItemRequestDto> findMaxReqFood();

}

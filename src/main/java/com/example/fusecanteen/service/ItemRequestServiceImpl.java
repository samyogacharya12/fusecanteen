package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.ItemRequestDto;
import com.example.fusecanteen.entity.ItemRequest;
import com.example.fusecanteen.mapper.ItemRequestMapper;
import com.example.fusecanteen.repository.ItemRequestRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemRequestServiceImpl implements ItemRequestService {

    private final static Logger logger = LoggerFactory.getLogger(ItemRequestServiceImpl.class);

    @Autowired
    private final ItemRequestRepository itemRepository;

    @Autowired
    private final ItemRequestMapper itemRequestMapper;

    public ItemRequestServiceImpl(final ItemRequestRepository itemRepository,
                                  final ItemRequestMapper itemRequestMapper) {
        this.itemRepository = itemRepository;
        this.itemRequestMapper = itemRequestMapper;
    }

    @Override
    public ItemRequestDto save(ItemRequestDto itemRequestDto) {
        logger.debug("saving item request");
        itemRequestDto.setCreatedBy(SecurityUtility.getUserName());
        itemRequestDto.setLastModifiedBy(SecurityUtility.getUserName());
        ItemRequest itemRequest = itemRequestMapper.toEntity(itemRequestDto);
        return itemRequestMapper.toDto(itemRepository.save(itemRequest));
    }

    @Override
    public ItemRequestDto findById(Long id) {
        logger.debug("finding request item by id");
        return itemRequestMapper.toDto(itemRepository.getOne(id));
    }

    @Override
    public List<ItemRequestDto> findAll() {
        logger.debug("finding all item request");
        return itemRequestMapper.toDto(itemRepository.findAll());
    }

    @Override
    public List<ItemRequestDto> findMaxReqFood() {
        logger.debug("finding max request food");
        String foodName = itemRepository.findMaxNoOfItemRequest();
        return itemRequestMapper.toDto(itemRepository.findByName(foodName));
    }
}

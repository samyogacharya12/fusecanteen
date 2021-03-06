package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.ItemDto;
import com.example.fusecanteen.entity.Item;
import com.example.fusecanteen.mapper.ItemMapper;
import com.example.fusecanteen.repository.ItemRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger loggerFactory = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private final ItemRepository itemRepository;

    @Autowired
    private final ItemMapper itemMapper;

    public ItemServiceImpl(final ItemRepository itemRepository,
                           final ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemDto save(ItemDto itemDto) {
        loggerFactory.debug("saving item");
        itemDto.setCreatedBy(SecurityUtility.getUserName());
        itemDto.setLastModifiedBy(SecurityUtility.getUserName());
        Item item = itemMapper.toEntity(itemDto);
        return itemMapper.toDto(itemRepository.save(item));
    }

    @Override
    public List<ItemDto> findByCreated() {
        loggerFactory.debug("getting item in today");
        Date date = new Date();
        Date yesterday = getYesterday();
        List<Item> items = itemRepository.findByCreatedBetween(yesterday, date);
        return itemMapper.toDto(items);
    }

    private Date getYesterday() {
        return new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    }

    @Override
    public ItemDto findById(Long id) {
        loggerFactory.debug("getting item by id");
        return itemMapper.toDto(itemRepository.getOne(id));
    }

    @Override
    public List<ItemDto> findAll() {
        loggerFactory.debug("getting all item");
        return itemMapper.toDto(itemRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        loggerFactory.debug("deleting food item");
        itemRepository.deleteById(id);
    }


}

package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.ItemDto;
import com.example.fusecanteen.entity.Item;
import com.example.fusecanteen.errors.Invalid;
import com.example.fusecanteen.mapper.ItemMapper;
import com.example.fusecanteen.repository.ItemRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import com.example.fusecanteen.utility.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger loggerFactory = LoggerFactory.getLogger(ItemServiceImpl.class);

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
        validation(itemDto);
        itemDto.setCreatedBy(SecurityUtility.getUserName());
        itemDto.setLastModifiedBy(SecurityUtility.getUserName());
        Item item = itemMapper.toEntity(itemDto);
        return itemMapper.toDto(itemRepository.save(item));
    }


    private void validation(ItemDto itemDto) {
        if (StringUtils.isBlank(itemDto.getName())) {
            throw new Invalid("name cannot be null", itemDto);
        }

        if (itemDto.getPrice() == null) {
            throw new Invalid("price cannot be null", itemDto);
        }
        boolean isPresent = findItemByCode(itemDto.getCode());
        if (isPresent) {
            throw new Invalid("code is already used", itemDto);
        }

        if (StringUtils.isBlank(itemDto.getMagnifactureDate())) {
            throw new Invalid("magnifactured date cannot be blank", itemDto);
        }

        if (StringUtils.isBlank(itemDto.getExpiryDate())) {
            throw new Invalid("expiry date cannot be blank", itemDto);
        }
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

    @Override
    public Boolean findItemByCode(Double code) {
        Item item = itemRepository.findByCode(code);
        return Objects.nonNull(item);
    }


}

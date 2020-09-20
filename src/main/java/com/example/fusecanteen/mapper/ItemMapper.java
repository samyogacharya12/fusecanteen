package com.example.fusecanteen.mapper;

import com.example.fusecanteen.dto.ItemDto;
import com.example.fusecanteen.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, Item> {

    ItemDto toDto(Item item);

    Item toEntity(ItemDto itemDto);

}

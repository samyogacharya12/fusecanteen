package com.example.fusecanteen.mapper;

import com.example.fusecanteen.dto.ItemRequestDto;
import com.example.fusecanteen.entity.ItemRequest;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface ItemRequestMapper extends EntityMapper<ItemRequestDto, ItemRequest> {

    ItemRequestDto toDto(ItemRequest itemRequest);

    ItemRequest toEntity(ItemRequestDto itemRequestDto);
}

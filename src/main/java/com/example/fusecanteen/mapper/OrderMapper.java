package com.example.fusecanteen.mapper;

import com.example.fusecanteen.dto.OrderDto;
import com.example.fusecanteen.entity.OrderItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDto, OrderItem> {


    OrderDto toDto(OrderItem order);

    OrderItem toEntity(OrderDto orderDto);

}

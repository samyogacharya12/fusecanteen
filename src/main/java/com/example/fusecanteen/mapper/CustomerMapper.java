package com.example.fusecanteen.mapper;

import com.example.fusecanteen.dto.CustomerDto;
import com.example.fusecanteen.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer> {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);

}

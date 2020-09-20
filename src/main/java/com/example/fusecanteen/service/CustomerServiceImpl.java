package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.CustomerDto;
import com.example.fusecanteen.entity.Customer;
import com.example.fusecanteen.mapper.CustomerMapper;
import com.example.fusecanteen.repository.CustomerRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(final CustomerRepository customerRepository,
                               final CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        logger.debug("saving customer");
        customerDto.setCreatedBy(SecurityUtility.getUserName());
        customerDto.setLastModifiedBy(SecurityUtility.getUserName());
        Customer customer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }
}

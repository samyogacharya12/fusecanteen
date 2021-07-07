package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.CustomerDto;
import com.example.fusecanteen.dto.OrderDto;
import com.example.fusecanteen.entity.Item;
import com.example.fusecanteen.entity.MultiCalendar;
import com.example.fusecanteen.entity.OrderItem;
import com.example.fusecanteen.enumconstant.Status;
import com.example.fusecanteen.errors.Invalid;
import com.example.fusecanteen.mapper.OrderMapper;
import com.example.fusecanteen.repository.MultiCalendarRepository;
import com.example.fusecanteen.repository.OrderRepository;
import com.example.fusecanteen.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger loggerFactory = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private final OrderMapper orderMapper;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final CustomerService customerService;

    private final MultiCalendarRepository multiCalendarRepository;


    public OrderServiceImpl(final OrderMapper orderMapper,
                            final OrderRepository orderRepository,
                            final CustomerService customerService,
                            final MultiCalendarRepository multiCalendarRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.multiCalendarRepository=multiCalendarRepository;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        loggerFactory.debug("saving order");
        CustomerDto customerDto = customerService.save(orderDto.getCustomer());
        orderDto.setCustomer(customerDto);
        orderDto.setCreatedBy(SecurityUtility.getUserName());
        orderDto.setLastModifiedBy(SecurityUtility.getUserName());
        OrderItem order = orderMapper.toEntity(orderDto);
        return orderMapper.toDto(orderRepository.save(order));
    }


    @Override
    public OrderDto updateStatus(OrderDto orderDto) {
        if (!orderDto.getOrderStatus().contains(Status.Ready.toString())) {
            if (orderDto.getOrderStatus().equalsIgnoreCase(Status.Pending.toString())) {
                orderDto.setOrderStatus(Status.InProcess.toString());
            }
            if (orderDto.getOrderStatus().equalsIgnoreCase(Status.InProcess.toString())) {
                orderDto.setOrderStatus(Status.Ready.toString());
            }
            orderDto.setCustomer(customerService.save(orderDto.getCustomer()));
            return save(orderDto);
        }
        return orderDto;
    }

    @Override
    public OrderDto findById(Long id) {
        loggerFactory.debug("getting order by id");
        return orderMapper.toDto(orderRepository.getOne(id));
    }

    @Override
    public List<OrderDto> findAll() {
        loggerFactory.debug("getting all order");
        return orderMapper.toDto(orderRepository.findAll());
    }

    @Override
    public List<OrderDto> filter(String fromDate, String toDate) {
        loggerFactory.debug("filter orders");
        if (Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
            MultiCalendar fromBsDate = multiCalendarRepository.findByBsDate(fromDate);
            if (fromBsDate == null) {
                throw new Invalid("Sorry Calendar Not Found",
                        "CalendarError");
            }
            MultiCalendar toBsDate = multiCalendarRepository.findByBsDate(toDate);
            if (toBsDate == null) {
                throw new Invalid("Sorry Calendar Not Found",
                        "CalendarError");
            }
        }
        try {
            List<MultiCalendar> multiCalendars;
            LocalDate startDate;
            LocalDate endDate;
            if (fromDate == null && toDate == null) {
                LocalDate adDate = LocalDate.now();
                MultiCalendar multiCalendar = multiCalendarRepository.findByAdDate(adDate);
                StringBuilder builder = new StringBuilder(multiCalendar.getBsDate());
                builder.delete(7, 10);
                String bsDate = builder.toString();
                multiCalendars = multiCalendarRepository.
                        findAllByBsDateOrderByAdDateDesc(bsDate);
                startDate = multiCalendars.get(multiCalendars.size() - 1).getAdDate();
                endDate = multiCalendars.get(0).getAdDate();
                return orderRepository.findAllByCreatedBetween(
                        Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .stream()
                        .map(orderMapper::toDto).collect(Collectors.toList());
            }
            DateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
            return orderRepository.findAllByCreatedBetween(formatter.parse(fromDate),
                    formatter.parse(toDate)).stream()
                    .map(orderMapper::toDto).collect(Collectors.toList());

        } catch (Exception e) {
            loggerFactory.error(e.getMessage());
        }
        return null;
    }
}

package com.example.fusecanteen.controller;

import com.example.fusecanteen.dto.ItemDto;
import com.example.fusecanteen.dto.OrderDto;
import com.example.fusecanteen.service.ExcelService;
import com.example.fusecanteen.service.ItemService;
import com.example.fusecanteen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/auth")
public class ExcelController {

    @Autowired
    private final ExcelService excelService;

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final OrderService orderService;


    @Autowired
    private ServletContext context;


    public ExcelController(final ExcelService excelService,
                           final ItemService itemService,
                           final OrderService orderService) {
        this.excelService = excelService;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/createitemexpenses")
    public void createItemReport(HttpServletRequest request, HttpServletResponse response) {

        List<ItemDto> itemDtoList = itemService.findAll();
        boolean isFlag = excelService.createExcelForItem(itemDtoList, context, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "items" + ".xls");
            excelService.fileDownload(fullPath, response, "items.xls");
        }
    }

    @GetMapping(value = "/createorderitemreport")
    public void createOrderItemReport(HttpServletRequest request, HttpServletResponse response) {
        List<OrderDto> orderDtos = orderService.findAll();
        boolean isFlag = excelService.createExcelForOrderItem(orderDtos, context, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "orderitem" + ".xls");
            excelService.fileDownload(fullPath, response, "orderitem.xls");
        }


    }


}

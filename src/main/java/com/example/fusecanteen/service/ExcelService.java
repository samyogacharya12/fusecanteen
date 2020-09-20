package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.ItemDto;
import com.example.fusecanteen.dto.OrderDto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExcelService {

    boolean createExcelForItem(List<ItemDto> itemDtos, ServletContext context,
                               HttpServletRequest request, HttpServletResponse response);

    boolean createExcelForOrderItem(List<OrderDto> orderDtos, ServletContext context,
                                    HttpServletRequest request, HttpServletResponse response);

    void fileDownload(String fullpath, HttpServletResponse response, String filename);

}

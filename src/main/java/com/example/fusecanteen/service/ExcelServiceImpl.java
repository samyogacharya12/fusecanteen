package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.ItemDto;
import com.example.fusecanteen.dto.OrderDto;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {


    @Autowired
    private ServletContext context;


    @Override
    public boolean createExcelForItem(List<ItemDto> itemDtos,
                                      ServletContext context,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        String filepath = context.getRealPath("/resources/reports");
        File file = new File(filepath);
        boolean exists = new File(filepath).exists();
        if (!exists) {
            new File(filepath).mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "items" + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Items");
            worksheet.setDefaultColumnWidth(30);

            HSSFCellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFRow headerRow = worksheet.createRow(0);
            HSSFCell itemId = headerRow.createCell(0);
            itemId.setCellValue("Item Id");

            HSSFCell itemName = headerRow.createCell(1);
            itemName.setCellValue("Item Name");

            HSSFCell createdBy = headerRow.createCell(2);
            createdBy.setCellValue("Created By");

            HSSFCell lastModifiedBy = headerRow.createCell(3);
            lastModifiedBy.setCellValue("Last Modified By");

            HSSFCell createdDate = headerRow.createCell(4);
            createdDate.setCellValue("Created Date");

            HSSFCell lastModifiedDate = headerRow.createCell(5);
            lastModifiedDate.setCellValue("Last Modified Date");

            HSSFCell price = headerRow.createCell(6);
            price.setCellValue("Price");


            HSSFCell code = headerRow.createCell(7);
            code.setCellValue("Code");

            HSSFCell magnifactureDate = headerRow.createCell(8);
            magnifactureDate.setCellValue("Magnifacture Date");


            HSSFCell expiryDate = headerRow.createCell(9);
            expiryDate.setCellValue("Expiry Date");
            int i = 1;
            for (ItemDto itemDto : itemDtos) {
                HSSFRow bodyRow = worksheet.createRow(i);
                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
                bodyCellStyle.setFillBackgroundColor(HSSFColor.WHITE.index);

                HSSFCell itemIdValue = bodyRow.createCell(0);
                itemIdValue.setCellValue(itemDto.getId());
                itemIdValue.setCellStyle(bodyCellStyle);

                HSSFCell itemNameValue = bodyRow.createCell(1);
                itemNameValue.setCellValue(itemDto.getName());
                itemNameValue.setCellStyle(bodyCellStyle);

                HSSFCell createdByValue = bodyRow.createCell(2);
                createdByValue.setCellValue(itemDto.getCreatedBy());
                createdByValue.setCellStyle(bodyCellStyle);

                HSSFCell lastModifiedByValue = bodyRow.createCell(3);
                lastModifiedByValue.setCellValue(itemDto.getLastModifiedBy());
                lastModifiedByValue.setCellStyle(bodyCellStyle);

                HSSFCell createdValue = bodyRow.createCell(4);
                createdValue.setCellValue(itemDto.getCreated());
                createdValue.setCellStyle(bodyCellStyle);

                HSSFCell lastModifiedValue = bodyRow.createCell(5);
                lastModifiedValue.setCellValue(itemDto.getLastModified());
                lastModifiedValue.setCellStyle(bodyCellStyle);

                HSSFCell priceValue = bodyRow.createCell(6);
                priceValue.setCellValue(itemDto.getPrice());
                priceValue.setCellStyle(bodyCellStyle);

                HSSFCell codeValue = bodyRow.createCell(7);
                codeValue.setCellValue(itemDto.getCode());
                codeValue.setCellStyle(bodyCellStyle);


                HSSFCell magnifactureValue = bodyRow.createCell(8);
                magnifactureValue.setCellValue(itemDto.getMagnifactureDate());
                magnifactureValue.setCellStyle(bodyCellStyle);

                HSSFCell expiryValue = bodyRow.createCell(9);
                expiryValue.setCellValue(itemDto.getExpiryDate());
                expiryValue.setCellStyle(bodyCellStyle);
                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean createExcelForOrderItem(List<OrderDto> orderDtos,
                                           ServletContext context,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {

        String filepath = context.getRealPath("/resources/reports");
        File file = new File(filepath);
        boolean exists = new File(filepath).exists();
        if (!exists) {
            new File(filepath).mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "orderitem" + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("OrderItem");
            worksheet.setDefaultColumnWidth(30);

            HSSFCellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFRow headerRow = worksheet.createRow(0);
            HSSFCell orderId = headerRow.createCell(0);
            orderId.setCellValue("Order Id");

            HSSFCell createdBy = headerRow.createCell(1);
            createdBy.setCellValue("Created By");

            HSSFCell lastModifiedBy = headerRow.createCell(2);
            lastModifiedBy.setCellValue("Last Modified By");

            HSSFCell createdAt = headerRow.createCell(3);
            createdAt.setCellValue("Created At");


            HSSFCell itemName = headerRow.createCell(4);
            itemName.setCellValue("Item Name");


            HSSFCell itemPrice = headerRow.createCell(5);
            itemPrice.setCellValue("Item Price");

            HSSFCell itemCode = headerRow.createCell(6);
            itemCode.setCellValue("Code");

            HSSFCell time = headerRow.createCell(7);
            time.setCellValue("Time");


            HSSFCell orderStatus = headerRow.createCell(8);
            orderStatus.setCellValue("Order Status");

            HSSFCell firstName = headerRow.createCell(9);
            firstName.setCellValue("First Name");

            HSSFCell middleName = headerRow.createCell(10);
            middleName.setCellValue("Middle Name");

            HSSFCell lastName = headerRow.createCell(11);
            lastName.setCellValue("Last Name");


            HSSFCell permanentAddress = headerRow.createCell(12);
            permanentAddress.setCellValue("Permanent Address");

            HSSFCell temporaryAddress = headerRow.createCell(13);
            temporaryAddress.setCellValue("Temporary Address");

            int i = 1;
            for (OrderDto orderDto : orderDtos) {
                HSSFRow bodyRow = worksheet.createRow(i);
                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
                bodyCellStyle.setFillBackgroundColor(HSSFColor.WHITE.index);

                HSSFCell orderIdValue = bodyRow.createCell(0);
                orderIdValue.setCellValue(orderDto.getId());
                orderIdValue.setCellStyle(bodyCellStyle);


                HSSFCell createdByValue = bodyRow.createCell(1);
                createdByValue.setCellValue(orderDto.getCreatedBy());
                createdByValue.setCellStyle(bodyCellStyle);

                HSSFCell lastModifiedByValue = bodyRow.createCell(2);
                lastModifiedByValue.setCellValue(orderDto.getLastModifiedBy());
                lastModifiedByValue.setCellStyle(bodyCellStyle);

                HSSFCell createdAtValue = bodyRow.createCell(3);
                createdAtValue.setCellValue(orderDto.getCreated());
                createdAtValue.setCellStyle(bodyCellStyle);

                HSSFCell itemNameValue = bodyRow.createCell(4);
                itemNameValue.setCellValue(orderDto.getItem().getName());
                itemNameValue.setCellStyle(bodyCellStyle);


                HSSFCell itemPriceValue = bodyRow.createCell(5);
                itemPriceValue.setCellValue(orderDto.getItem().getPrice());
                itemPriceValue.setCellStyle(bodyCellStyle);

                HSSFCell itemCodeValue = bodyRow.createCell(6);
                itemCodeValue.setCellValue(orderDto.getItem().getCode());
                itemCodeValue.setCellStyle(bodyCellStyle);


                HSSFCell timeValue = bodyRow.createCell(7);
                timeValue.setCellValue(orderDto.getTime());
                timeValue.setCellStyle(bodyCellStyle);

                HSSFCell orderStatusValue = bodyRow.createCell(8);
                orderStatusValue.setCellValue(orderDto.getOrderStatus());
                orderStatusValue.setCellStyle(bodyCellStyle);

                HSSFCell firstNameValue = bodyRow.createCell(9);
                firstNameValue.setCellValue(orderDto.getCustomer().getFirstName());
                firstNameValue.setCellStyle(bodyCellStyle);

                HSSFCell middleNameValue = bodyRow.createCell(10);
                middleNameValue.setCellValue(orderDto.getCustomer().getMiddleName());
                middleNameValue.setCellStyle(bodyCellStyle);

                HSSFCell lastNameValue = bodyRow.createCell(11);
                lastNameValue.setCellValue(orderDto.getCustomer().getLastName());
                lastNameValue.setCellStyle(bodyCellStyle);

                HSSFCell permanentAddressValue = bodyRow.createCell(12);
                permanentAddressValue.setCellValue(orderDto.getCustomer().getPermanentAddress());
                permanentAddressValue.setCellStyle(bodyCellStyle);

                HSSFCell temporaryAddressValue = bodyRow.createCell(13);
                temporaryAddressValue.setCellValue(orderDto.getCustomer().getTemporaryAddress());
                temporaryAddressValue.setCellStyle(bodyCellStyle);
                i++;
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void fileDownload(String fullpath, HttpServletResponse response, String filename) {
        File file = new File(fullpath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullpath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachement; filename=" + filename);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

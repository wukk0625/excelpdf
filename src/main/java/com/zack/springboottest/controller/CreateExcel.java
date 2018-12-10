package com.zack.springboottest.controller;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController
public class CreateExcel {
    private String fileName = "创建excel.xls";

    @RequestMapping("/createexcel")
    @ResponseBody
    public String writeExcel(HttpServletResponse response) throws Exception {

        fileName = response.encodeURL(new String(fileName.getBytes("gb2312"), "iso8859-1"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        OutputStream os = response.getOutputStream();

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(2);
        XSSFCell cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("hellword");
        workbook.write(os);
        return "create excel";
    }

}

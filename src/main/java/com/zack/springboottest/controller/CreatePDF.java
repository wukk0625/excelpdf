package com.zack.springboottest.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController
public class CreatePdf {

    private String fileName = "创建pdf.pdf";

    @RequestMapping("/createpdf")
    @ResponseBody
    public String writePdf(HttpServletResponse response) throws Exception {
        fileName = response.encodeURL(new String(fileName.getBytes("gb2312"), "iso8859-1"));
        //对文件编码
        response.setContentType("application/pdf;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        OutputStream os = response.getOutputStream();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, os);
        document.open();

        document.add(new Paragraph("hello world"));
        document.close();
        writer.close();
        return "create pdf";
    }

}

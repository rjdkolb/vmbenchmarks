package com.github.vmbenchmarks;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class GeneratePoiWorkBook {

    static AtomicInteger xlsCellCount = new AtomicInteger(20000);
    static AtomicLong  xlsCount = new AtomicLong(100000L);
    
    static AtomicInteger xlsxCellCount = new AtomicInteger(20000);
    static AtomicLong  xlsxCount = new AtomicLong(100000L);
    
    @Benchmark
    public void generatePoiXLSWorkBook() throws IOException {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(xlsCellCount.incrementAndGet());

        // Or do it on one line.
        row.createCell(1).setCellValue(xlsCount.incrementAndGet());
        row.createCell(2).setCellValue(
                createHelper.createRichTextString(Long.toString(xlsCount.get())));
        row.createCell(3).setCellValue(true);
        wb.write(new ByteArrayOutputStream());
    }
     @Benchmark
    public void generatePoiXLSXWorkBook() throws IOException {
        Workbook wb = new XSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(xlsxCellCount.incrementAndGet());

        // Or do it on one line.
        row.createCell(1).setCellValue(xlsxCount.incrementAndGet());
        row.createCell(2).setCellValue(
                createHelper.createRichTextString(Long.toString(xlsxCount.get())));
        row.createCell(3).setCellValue(true);
        wb.write(new ByteArrayOutputStream());
    }
}

package com.github.pt.xlsx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class XlsxModifier {
    private final InputStream inputStream;

    private XlsxModifier(InputStream inputStream) {
       this.inputStream = inputStream; 
    }

    public static XlsxModifier of(InputStream inputStream) {
        return new XlsxModifier(inputStream);
    }

    public void updateCellData(OutputStream outputStream) {
        try {
            XSSFWorkbook book = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = book.getSheetAt(1);
            fillCell(sheet, 5, 12, "Test data 3");
            inputStream.close();
            book.write(outputStream);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private void fillCell(XSSFSheet sheet, int columnNumber, int rowNumber, String value) {
        XSSFRow row = sheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(columnNumber);
        cell.setCellValue(value == null ? "" : value);
    }

}

package writetoexcel;

import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.Scanner;

public class WriteToExcel {
    private static final String FILE_NAME = "/home/shine/Desktop/product_details.xlsx";

    public static void main(String[] args) throws IOException {

        ProductModel productModel = new ProductModel();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet("List products");

        //  create heading
        Row rowHeading = sheet.createRow(0);
        rowHeading.createCell(0).setCellValue("Id");
        rowHeading.createCell(1).setCellValue("Name");
        rowHeading.createCell(2).setCellValue("Price");
        rowHeading.createCell(3).setCellValue("Quantity");
        rowHeading.createCell(4).setCellValue("Creation date");
        rowHeading.createCell(5).setCellValue("Sub total");

        for (int i=0; i<6; i++) {
            CellStyle styleRowHeading = xssfWorkbook.createCellStyle();
            Font font = xssfWorkbook.createFont();
            font.setBold(true);
            font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
            font.setFontHeightInPoints((short) 11);
            styleRowHeading.setFont(font);
            rowHeading.getCell(i).setCellStyle(styleRowHeading);
        }


        int r = 1;
        for (Product p: productModel.findAll()) {
            Row row = sheet.createRow(r);
            // id column
            Cell cellId = row.createCell(0);
            cellId.setCellValue(p.getId());

            // id Name
            Cell cellName = row.createCell(1);
            cellName.setCellValue(p.getName());

            // id price
            Cell cellPrice = row.createCell(2);
            cellPrice.setCellValue(p.getPrice());
            CellStyle stylePrice = xssfWorkbook.createCellStyle();
            XSSFDataFormat priceDataFormate = xssfWorkbook.createDataFormat();
            ((XSSFCellStyle) stylePrice).setDataFormat(priceDataFormate.getFormat("$#,##0.00"));
            cellPrice.setCellStyle(stylePrice);

            // id Quantity
            Cell cellQuantity = row.createCell(3);
            cellQuantity.setCellValue(p.getQuantity());

            // id CreationDate
            Cell cellCreationDate = row.createCell(4);
            cellCreationDate.setCellValue(p.getCreationDate());
            CellStyle styleCreationDate = xssfWorkbook.createCellStyle();
            XSSFDataFormat xssfDataFormat = xssfWorkbook.createDataFormat();
            ((XSSFCellStyle) styleCreationDate).setDataFormat(xssfDataFormat.getFormat("m/d/yy"));
            cellCreationDate.setCellStyle(styleCreationDate);

            //  id subTotal
            Cell cellSubTotal = row.createCell(5);
            cellSubTotal.setCellValue(p.getQuantity() * p.getPrice());
            CellStyle styleSubTotal = xssfWorkbook.createCellStyle();
            XSSFDataFormat subTotalDataFormate = xssfWorkbook.createDataFormat();
            ((XSSFCellStyle) styleSubTotal).setDataFormat(subTotalDataFormate.getFormat("$#,##0.00"));
            cellSubTotal.setCellStyle(styleSubTotal);

            r++;
        }

        /** upload a image **/
        FileInputStream fis = new FileInputStream("/home/shine/Desktop/images.png");
        ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
        int b;
        while((b = fis.read()) != -1) {
            imageBytes.write(b);
        }
        XSSFClientAnchor anchor;
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 5, (short) 10, 20);
        patriarch.createPicture(anchor, xssfWorkbook.addPicture(imageBytes.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
        System.out.println("Image is uploaded....");


        /** total column **/
        Row rowTotal = sheet.createRow(productModel.findAll().size() + 1);
        Cell cellTextTotal = rowTotal.createCell(0);
        cellTextTotal.setCellValue("Total");
        CellRangeAddress region = CellRangeAddress.valueOf("A5:E5");
        sheet.addMergedRegion(region);

        /** autofit the column **/
        for (int i=0; i<6; i++) {
            sheet.autoSizeColumn(i);
        }

        /** save the excel file **/
        FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
        System.out.println("Excel written is successfully created....");
    }
}

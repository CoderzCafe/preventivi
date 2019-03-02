package main.model;

import main.Main;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class Excel {

    /** save file data directory
     * in this class we are insert, delete data in
     * excel sheet and create a excel file
     * feature will also adding here**/
    private final String FILE_PATH = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"data"+File.separator;
    private FileInputStream fis = null;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String fileName;

    public Excel(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        try {
            fis = new FileInputStream(new File(FILE_PATH+fileName));
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            Main.notification("Error", e.getMessage());
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void createNewExcel() {
        try {
            FileOutputStream fout = new FileOutputStream(new File(FILE_PATH+fileName));
            workbook.write(fout);
            fout.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            Main.notification("File not found", e.getMessage());
        } catch (IOException e) {
            Main.notification("Failed to create excel file", e.getMessage());
        }

        Main.notification("Congrats", "File is created successfully");
    }

    /** creating the heading **/
    public void createHeading() {
        /** this method is only for data model **/
        Row rowHeading = sheet.createRow(1);
        rowHeading.createCell(0).setCellValue("id");
        rowHeading.createCell(1).setCellValue("name");
        rowHeading.createCell(2).setCellValue("price");
        rowHeading.createCell(3).setCellValue("type");
        rowHeading.createCell(4).setCellValue("description");
        rowHeading.createCell(5).setCellValue("image");

        /** style the heading **/
        for (int i=0; i<6; i++) {
            CellStyle styleRowHeading = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
            font.setFontHeightInPoints((short) 11);
            styleRowHeading.setFont(font);
            rowHeading.getCell(i).setCellStyle(styleRowHeading);
        }
        Main.notification("Row is created", "");
    }

    /** insert the data into the sheet **/
    public void insertToSheet(String id, String name, long price,
                              String type, String description, String imagePath) throws IOException {
        int r = sheet.getLastRowNum();

        Row row = sheet.createRow(r);

        /** insert to id **/
        Cell idCell = row.createCell(0);
        idCell.setCellValue(id);

        /** insert name **/
        Cell nameCell = row.createCell(1);
        nameCell.setCellValue(name);

        /** insert price **/
        Cell priceCell = row.createCell(2);
        priceCell.setCellValue(price);
        CellStyle priceStyle = workbook.createCellStyle();
        XSSFDataFormat dataFormat = workbook.createDataFormat();
        ((XSSFCellStyle) priceStyle).setDataFormat(dataFormat.getFormat("$##,##,###.00"));
        priceCell.setCellStyle(priceStyle);

        /** insert type **/
        Cell typeCell = row.createCell(3);
        typeCell.setCellValue(type);

        /** insert description **/
        Cell descriptionCell = row.createCell(4);
        descriptionCell.setCellValue(description);

        /** insert image **/
        /** insert image **/
        FileInputStream fis = new FileInputStream(imagePath);
        byte[] imagebytes = IOUtils.toByteArray(fis);
        int picId = 0;
        if (((imagePath.endsWith("jpg")) || (imagePath.endsWith("JPG"))) ||
                ((imagePath.endsWith("jpeg")) || (imagePath.endsWith("JPEG")))) {
            picId = workbook.addPicture(imagebytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
        } else if ((imagePath.endsWith("png")) || (imagePath.endsWith("PNG"))) {
            picId = workbook.addPicture(imagebytes, XSSFWorkbook.PICTURE_TYPE_PNG);
        } else if ((imagePath.endsWith("bmp")) || (imagePath.endsWith("BMP"))) {
            picId = workbook.addPicture(imagebytes, XSSFWorkbook.PICTURE_TYPE_BMP);
        }
        CreationHelper helper = workbook.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = ((XSSFCreationHelper) helper).createClientAnchor();
        anchor.setCol1(5);
        anchor.setRow1(r);
        Picture picture = drawing.createPicture(anchor, picId);
        picture.resize(1.0, 1.0);
        r++;
    }

    /** save the file **/
    public void saveFile() {
        try {
            FileOutputStream fout = new FileOutputStream(FILE_PATH+fileName);
            workbook.write(fout);
            fout.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            Main.notification("File not saved", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
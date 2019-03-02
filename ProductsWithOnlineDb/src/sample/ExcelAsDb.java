package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.List;

public class ExcelAsDb {
    private ObservableList<Product> productList = FXCollections.observableArrayList();
//    private final String FILE_PATH = System.getProperty("user.dir")+File.separator+"src"+File.separator+"data"+File.separator+"products.xlsx";
    private String FILE_PATH = "/home/shine/Desktop/products.xlsx";
    FileInputStream fis = new FileInputStream(new File(FILE_PATH));
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelAsDb() throws IOException {
        // default constructor
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet("Sheet1");
    }

    public void createNewExcel(String name) throws IOException {
        String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"data"+File.separator+name+".xlsx";
        FileOutputStream fout = new FileOutputStream(new File(path));
        workbook.write(fout);
        fout.close();
        workbook.close();
        System.out.println("File is created..");
    }

    public void createHeading() {
        /** this method is only for my data model **/
        Row rowHeding = sheet.createRow(0);
        rowHeding.createCell(0).setCellValue("Id");
        rowHeding.createCell(1).setCellValue("Name");
        rowHeding.createCell(2).setCellValue("Type");
        rowHeding.createCell(3).setCellValue("Price");
        rowHeding.createCell(4).setCellValue("Description");
        rowHeding.createCell(5).setCellValue("Image");

        /** style the heading **/
        for (int i=0; i<6; i++) {
            CellStyle styleRowHeading = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
            font.setFontHeightInPoints((short) 11);
            styleRowHeading.setFont(font);
            rowHeding.getCell(i).setCellStyle(styleRowHeading);
        }
        System.out.println("Excel header is created...");
    }

    public void insertIntoSheet(String id, String name, long price, String type,
                                String description, String imagePath) throws IOException {
        int r = sheet.getLastRowNum();
        /** crhere is problem in exceleating the new row **/
        Row row = sheet.createRow(r);

        /** insert id **/
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
        System.out.println("Data inserted successfully....");
    }

    public void saveFile() throws IOException {
        FileOutputStream fout = new FileOutputStream(FILE_PATH);
        workbook.write(fout);
        fout.close();
        workbook.close();
        System.out.println("File is saved successfully......");
    }

    public ObservableList<Product> getProductList() {
        /** get all pictures **/
        List pictureList = workbook.getAllPictures();
        byte[] imageData = null;
        String id = "";
        String name = "";
        long price = 0;
        String type = "";
        String description = "";

        for (int i=0; i<pictureList.size(); i++) {
            id = sheet.getRow(i).getCell(0).getStringCellValue();
            name = sheet.getRow(i).getCell(1).getStringCellValue();
            price = Long.valueOf(sheet.getRow(i).getCell(2).getStringCellValue());
            type = sheet.getRow(i).getCell(3).getStringCellValue();
            description = sheet.getRow(i).getCell(4).getStringCellValue();

            /** get the pictures from the sheet **/
            PictureData pictureData = (PictureData) pictureList.get(i);
            String extension = pictureData.suggestFileExtension();
            if ((extension.equals("jpg")) || (extension.equals("JPG")) ||
                    (extension.equals("jepg")) || (extension.equals("JPEG"))) {
                imageData = pictureData.getData();
            } else if ((extension.equals("png")) || (extension.equals("PNG"))) {
                imageData = pictureData.getData();
            } else if ((extension.equals("bmp")) || (extension.equals("BMP"))) {
                imageData = pictureData.getData();
            }
            productList.add(new Product(id, name, price, type, description, imageData));
        }
        return productList;
    }
}

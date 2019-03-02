package writetoexceltest2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Excel2 {

    ObservableList<TestUser> userList = FXCollections.observableArrayList();
    File file = new File("/home/shine/Desktop/user_details.xlsx");
    FileInputStream fis = new FileInputStream(file);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheetAt(0);
    int lastRow = sheet.getLastRowNum();  //  sheets row number
    public Excel2() throws IOException {

    }

    /** insert data into the sheet **/
    public void insertIntoSheet(String firstName, String lastName, String email, String imagePath) throws IOException {
        int r = lastRow + 1;
        /** creating the new row **/
        Row row = sheet.createRow(r);

        /** insert first name **/
        Cell firstNameCell = row.createCell(0);
        firstNameCell.setCellValue(firstName);

        /** insert last name **/
        Cell lastNameCell = row.createCell(1);
        lastNameCell.setCellValue(lastName);

        /** insert email **/
        Cell emailCell = row.createCell(2);
        emailCell.setCellValue(email);


        /** insert image to the cell **/
        FileInputStream fis = new FileInputStream(imagePath);
        byte[] bytes = IOUtils.toByteArray(fis);
        int picId = 0;
        if (imagePath.endsWith(".jpg")) {
            picId = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
        } else if (imagePath.endsWith(".png")) {
            picId = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
        } else if (imagePath.endsWith(".jpeg")) {
            picId = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
        } else if (imagePath.endsWith(".bmp")) {
            picId = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_BMP);
        }
        CreationHelper helper = workbook.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = ((XSSFCreationHelper) helper).createClientAnchor();
        anchor.setCol1(3);
        anchor.setRow1(r);
        Picture picture = drawing.createPicture(anchor, picId);
        picture.resize(1.0, 1.0);
        r ++;
        System.out.println("Data is inserted....");
    }

    public void saveFile() throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        workbook.close();
        System.out.println("File is saved successfully....");
    }

    public ObservableList<TestUser> getDataList() throws FileNotFoundException {
        List list = workbook.getAllPictures();
        byte[] imageData = null;
        String firstName = "";
        String lastName = "";
        String email = "";
        for (int i=1; i<=lastRow; i++) {
            firstName = sheet.getRow(i).getCell(0).getStringCellValue();
            lastName = sheet.getRow(i).getCell(1).getStringCellValue();
            email = sheet.getRow(i).getCell(2).getStringCellValue();

            /** get images form the excel sheet **/
            PictureData pictureData = (PictureData) list.get(i);
            String extension = pictureData.suggestFileExtension();
            if (extension.endsWith("jpg")) {
                imageData = pictureData.getData();
            } else if (extension.endsWith("png")) {
                imageData = pictureData.getData();
            } else if (extension.endsWith("jpeg")) {
                imageData = pictureData.getData();
            } else if (extension.endsWith("bmp")) {
                imageData = pictureData.getData();
            }
            userList.add(new TestUser(firstName, lastName, email, imageData));
        }
        return userList;
    }
}

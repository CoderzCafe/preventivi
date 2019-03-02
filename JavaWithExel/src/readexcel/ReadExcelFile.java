package readexcel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

public class ReadExcelFile {

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = null;
        ObservableList<UserDetails> list = FXCollections.observableArrayList();

        File file = new File("/home/shine/Desktop/user_details.xlsx");
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = xssfWorkbook.getSheetAt(0);

//        String firstName = sheet1.getRow(1).getCell(0).getStringCellValue();
//        String lastName = sheet1.getRow(1).getCell(1).getStringCellValue();
//        String email = sheet1.getRow(1).getCell(2).getStringCellValue();
//
//        System.out.println("First name : " +firstName);
//        System.out.println("Last name : " +lastName);
//        System.out.println("Email : " +email);

        /** image **/
        List lst = xssfWorkbook.getAllPictures();
        int j = 1;
        for (Iterator it = lst.iterator(); it.hasNext();) {
            PictureData pictureData = (PictureData)it.next();
            String ext = pictureData.suggestFileExtension();
            byte[] data = pictureData.getData();
            if (ext.equals("jpg")) {
                fileOutputStream = new FileOutputStream("/home/shine/pictureData"+(j+1)+ ".jpg");
                fileOutputStream.write(data);
                fileOutputStream.close();
            } else if (ext.equals("png")) {
                fileOutputStream = new FileOutputStream("/home/shine/pictureData"+(j+1)+ ".png");
                fileOutputStream.write(data);
                fileOutputStream.close();
            } else if (ext.equals("jpeg")) {
                fileOutputStream = new FileOutputStream("/home/shine/pictureData"+(j+1)+ ".jpeg");
                fileOutputStream.write(data);
                fileOutputStream.close();
            }
        }

        /** read all data at onece **/

        int rowCount = sheet1.getLastRowNum();

        for (int i=1; i<=rowCount; i++) {
            String firstName = sheet1.getRow(i).getCell(0).getStringCellValue();
            String lastName = sheet1.getRow(i).getCell(1).getStringCellValue();
            String email = sheet1.getRow(i).getCell(2).getStringCellValue();
            list.add(new UserDetails(firstName, lastName, email));
        }

        for (int i=0; i<list.size(); i++) {
            System.out.println("Person" +(i+1));
            System.out.println("\t First name: " +list.get(i).getFirstName());
            System.out.println("\t Last name: " +list.get(i).getLastName());
            System.out.println("\t Email: " +list.get(i).getEmail());

        }
    }
}

package writetoexcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class WriteToExcelTest2 {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        FileOutputStream fout = new FileOutputStream(new File("/home/shine/Desktop/image.xlsx"));

        //  add the picture to the file
        InputStream is = new FileInputStream("/home/shine/Desktop/images.png");
        byte[] bytes = IOUtils.toByteArray(is);
        int pictureIdx = xssfWorkbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        is.close();

        CreationHelper helper = xssfWorkbook.getCreationHelper();

        //  create sheet
        Sheet sheet = xssfWorkbook.createSheet();

        //  create the drawing patriarch. This is the top level containeer for all
        Drawing drawing = sheet.createDrawingPatriarch();

        //   add a picture shape
        XSSFClientAnchor anchor = ((XSSFCreationHelper) helper).createClientAnchor();
        //  set top-left corner of the picture
        //  subsquent call of picture #resize() will operatre relative to it
        anchor.setCol1(5);
        anchor.setRow1(0);

        Picture picture1 = drawing.createPicture(anchor, pictureIdx);

        //  auto size picture relative to its top-left corner
        picture1.resize(1, 1);


        // picture 2
        InputStream is2 = new FileInputStream(new File("/home/shine/Desktop/java.jpg"));
        byte[] bytes1 = IOUtils.toByteArray(is2);
        int pictureId2 = xssfWorkbook.addPicture(bytes1, Workbook.PICTURE_TYPE_PNG);
        is2.close();
        CreationHelper helper1 = xssfWorkbook.getCreationHelper();
        Drawing drawing1 = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor1 = ((XSSFCreationHelper) helper1).createClientAnchor();
        anchor1.setCol1(5);
        anchor1.setRow1(1);
        Picture picture2 = drawing1.createPicture(anchor1, pictureId2);
        picture2.resize(1, 1);

        // save workbook
        xssfWorkbook.write(fout);
        fout.close();

    }
}

package writetoexceltest2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class WriteToExcelTest1 {
    /** in this class
     * we only write the string based data **/
    private File file = null;
    private FileOutputStream fout = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private User user;

    public WriteToExcelTest1(User user) throws IOException {
        this.user = user;

        file = new File("/home/shine/Desktop/test1.xlsx");
        fout = new FileOutputStream(file);
        FileInputStream fis = new FileInputStream(file);
        //  get the excel sheet
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.createSheet("User");

        /**  create the heading   **/
        Row rowHeading = sheet.createRow(0);
        rowHeading.createCell(0).setCellValue("id");
        rowHeading.createCell(1).setCellValue("name");
        rowHeading.createCell(2).setCellValue("email");

        /** style the row heading -> heading **/
        for (int i=0; i<3; i++) {
            CellStyle styleRowHeading = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
            font.setFontHeightInPoints((short) 11);
            styleRowHeading.setFont(font);
            rowHeading.getCell(i).setCellStyle(styleRowHeading);
        }

        /** data will be entered from row1
         * row 0 is the heading **/
        int r = 1;
            /** create the row **/
            Row row = sheet.createRow(r);

            /** insert data in cell **/
            Cell idCell = row.createCell(0);
            idCell.setCellValue(user.getId());

            /** insert user data in name **/
            Cell nameCell = row.createCell(1);
            nameCell.setCellValue(user.getName());

            /** insert user data email **/
            Cell emailCell = row.createCell(2);
            emailCell.setCellValue(user.getEmail());
            r+=1;

        /** save data **/
        fout = new FileOutputStream(file);
        workbook.write(fout);
        fout.close();
        workbook.close();
    }

    public static void main(String[] args) throws IOException {
        User user = new User("9", "Rakib", "rakib@gmail.com");
        WriteToExcelTest1 test1 = new WriteToExcelTest1(user);

    }

    private ObservableList<User> userDataList(User user) {
        ObservableList<User> dataList = FXCollections.observableArrayList();
        dataList.add(user);
        return dataList;
    }
}

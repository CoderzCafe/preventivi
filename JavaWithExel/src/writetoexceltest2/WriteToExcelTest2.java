package writetoexceltest2;

import javafx.collections.ObservableList;

import java.io.IOException;

public class WriteToExcelTest2 {

    public static void main(String[] args) throws IOException {
        Excel2 excel2 = new Excel2();
//        excel2.insertIntoSheet("Rebeka", "Sultana", "ruhirebeka@gmail.com", "/home/shine/Android_logo.jpg");
//        excel2.saveFile();
        ObservableList<TestUser> userList = excel2.getDataList();
        for (int i=0; i<userList.size(); i++) {
            System.out.println("Person " +(i));
            System.out.println("First name: " +userList.get(i).getFirstName());
            System.out.println("Last name: " +userList.get(i).getLastName());
            System.out.println("Email : " +userList.get(i).getEmail());
            System.out.println("Image: " +userList.get(i).getPicture().toString());
        }
    }
}

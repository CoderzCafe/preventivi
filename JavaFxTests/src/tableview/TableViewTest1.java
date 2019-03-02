package tableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewTest1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<UserAccount> table = new TableView<UserAccount>();

        //  create column userName
        TableColumn<UserAccount, String> userNameCol = new TableColumn<UserAccount, String>("User name");

        //  create email column
        TableColumn<UserAccount, String> emailCol = new TableColumn<UserAccount, String>("Email");

        //  create full name column
        TableColumn<UserAccount, String> fullNameCol = new TableColumn<UserAccount, String>("Full name");

        //  create sub column for full name
        TableColumn<UserAccount, String> firstNameCol = new TableColumn<UserAccount, String>("First name");
        TableColumn<UserAccount, String> lastNameCol = new TableColumn<UserAccount, String>("Last name");

        //  add subcolumn to full name
        fullNameCol.getColumns().addAll(firstNameCol, lastNameCol);

        //  create active column
        TableColumn<UserAccount, Boolean> activeCol = new TableColumn<UserAccount, Boolean>("Active");

        table.getColumns().addAll(userNameCol, emailCol, fullNameCol, activeCol);


        StackPane root = new StackPane();
        root.setPadding(new Insets(10));
        root.getChildren().add(table);

        primaryStage.setTitle("User account");

        Scene scene = new Scene(root, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private ObservableList<UserAccount> getUserList() {

        UserAccount user1 = new UserAccount(1L, "smith", "smith@gmail.com", //
                "Susan", "Smith", true);
        UserAccount user2 = new UserAccount(2L, "mcneil", "mcneil@gmail.com", //
                "Anne", "McNeil", true);
        UserAccount user3 = new UserAccount(3L, "white", "white@gmail.com", //
                "Kenvin", "White", false);

        ObservableList<UserAccount> list = FXCollections.observableArrayList(user1, user2, user3);
        return list;
    }
}

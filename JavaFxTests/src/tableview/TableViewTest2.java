package tableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewTest2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<UserAccount> tableView = new TableView<UserAccount>();

        //  creating column
        TableColumn<UserAccount, String> userNameCol = new TableColumn<UserAccount, String>("User name");

        //  email
        TableColumn<UserAccount, String> emailCol = new TableColumn<UserAccount, String>("Email");

        //  full name
        TableColumn<UserAccount, String> fullNameCol = new TableColumn<UserAccount, String>("Full name");

        //  creating sub col for full name
        TableColumn<UserAccount, String> firstNameCol = new TableColumn<UserAccount, String>("First name");
        TableColumn<UserAccount, String> lastNameCol = new TableColumn<UserAccount, String>("Last name");
        fullNameCol.getColumns().addAll(firstNameCol, lastNameCol);

        TableColumn<UserAccount, Boolean> activeCol = new TableColumn<UserAccount, Boolean>("Active");

        /** defines how to fill fata for each cell
         * get value from property of userAccount **/
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));;
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));


        //  set sort type for userName column
        userNameCol.setSortType(TableColumn.SortType.DESCENDING);
        lastNameCol.setSortable(false);

        //  display the data
        ObservableList<UserAccount> list = getUserData();
        tableView.setItems(list);
        tableView.getColumns().addAll(userNameCol, emailCol, fullNameCol, activeCol);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(tableView);

        Scene scene = new Scene(root, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<UserAccount> getUserData() {
        UserAccount user1 = new UserAccount(1L, "Sudipto", "sudipto.shine@gmail.com", "Sudipto", "Shahin", true);
        UserAccount user2 = new UserAccount(2L, "mcneil", "mcneil@gmail.com", //
                "Anne", "McNeil", true);
        UserAccount user3 = new UserAccount(3L, "white", "white@gmail.com", //
                "Kenvin", "White", false);
        ObservableList<UserAccount> list = FXCollections.observableArrayList(user1, user2, user3);
        return list;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

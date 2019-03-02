package tableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class TableViewTest3 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        TableView<Person> table = new TableView<Person>();

        TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("First name");
        TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>("Last name");
        TableColumn<Person, String> emailCol = new TableColumn<Person, String>("Email");
        TableColumn checkBoxCol = new TableColumn("Select");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, checkBoxCol);
        ObservableList<Person> list = getData();

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        checkBoxCol.setCellValueFactory(new PropertyValueFactory<Person, String>("checkBox"));

        table.setItems(list);

        Pane pane = new Pane();
        pane.getChildren().add(table);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private ObservableList<Person> getData() {
        ObservableList<Person> list = FXCollections.observableArrayList();
        Person p1 = new Person("Sudipto", "shine", "sudipto.shine@gmail.com");
        Person p2 = new Person("Rahim", "Sekh", "rahim@gmail.com");
        Person p3 = new Person("Abdul", "Karim", "karim@gmail.com");
        Person p4 = new Person("Arif", "Islam", "arif@gmail.com");
        list.addAll(p1, p2, p3, p4);
        return list;
    }
}

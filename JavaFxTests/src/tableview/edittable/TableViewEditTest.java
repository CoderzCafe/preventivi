package tableview.edittable;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableViewEditTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<Person> table = new TableView<Person>();

        // editable
        table.setEditable(true);

        TableColumn<Person, String> fullNameCol = new TableColumn<Person, String>("Full name");
        TableColumn<Person, String> genderCol = new TableColumn<Person, String>("Gender");
        TableColumn<Person, Boolean> singleCol = new TableColumn<Person, Boolean>("Single?");

        /** === full name textfield=== **/
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        fullNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        fullNameCol.setMinWidth(200);

        /** === on edit commit for fullNameColumn === **/
        fullNameCol.setOnEditCommit((TableColumn.CellEditEvent<Person, String> event) -> {
            TablePosition<Person, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            Person person = event.getTableView().getItems().get(row);
            person.setFullName(newFullName);
        });


        /** gender comboBox **/
//        ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.values());
//        genderCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, Gender ObservableValue<Gender>>() {
//            @Override
//            public ObservableValue<Gender> call(TableColumn.CellDataFeatures<Person, Gender> param) {
//                Person person = param.get
//                return null;
//            }
//        });
    }

    public static void main(String args) {
        Application.launch(args);
    }
}

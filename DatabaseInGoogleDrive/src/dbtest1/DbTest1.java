package dbtest1;

import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DbTest1 extends Application {

    private TableView<Event> table = new TableView<Event>();

    @Override
    public void start(Stage stage) throws Exception {
        List<Event> allEvents = new ArrayList<Event>();
        Event x = new Event("","","","","","");
        allEvents = x.create();

        ObservableList<Event> data = FXCollections.observableArrayList();

        for (Event y: allEvents) {
            data.add(y);
        }

        Scene scene = new Scene(new Group());
        stage.setTitle("Berru college events");
        stage.setWidth(1200);
        stage.setHeight(700);

        final Label label = new Label("Upcomming events");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);

        TableColumn eventCol = new TableColumn("Event name");
        eventCol.setMinWidth(100);
        eventCol.setCellValueFactory(new PropertyValueFactory<Event, String>("sName"));

        TableColumn statCol = new TableColumn("Status");
        statCol.setMinWidth(100);
        statCol.setCellValueFactory(new PropertyValueFactory<Event, String>("sStatus"));

        TableColumn groupCol = new TableColumn("Group");
        groupCol.setMinWidth(100);
        groupCol.setCellValueFactory(new PropertyValueFactory<Event, String>("sGroup"));

        TableColumn datingCol = new TableColumn("Date/Time");
        datingCol.setMinWidth(200);
        datingCol.setCellValueFactory(new PropertyValueFactory<Event, Date>("DateTime"));

        TableColumn descCol = new TableColumn("Description");
        descCol.setMinWidth(300);
        descCol.setCellValueFactory(new PropertyValueFactory<Event, String>("sDesc"));

        TableColumn locationCol = new TableColumn("Location");
        locationCol.setMinWidth(300);
        locationCol.setCellValueFactory(new PropertyValueFactory<Event, String>("sLoc"));

        FilteredList<Event> flEvent = new FilteredList(data, p -> true);
        table.setItems(flEvent);
        table.getColumns().addAll(eventCol, statCol, groupCol, datingCol, descCol, locationCol);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Event name", "Status", "Group", "Date", "Location");
        choiceBox.setValue("Event name");

        TextField textField = new TextField();
        textField.setPromptText("Search here");
        textField.setOnKeyPressed(keyEvent -> {
            switch (choiceBox.getValue()) {
                case "Event name":
                    flEvent.setPredicate(p -> p.getSName().);
            }

        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

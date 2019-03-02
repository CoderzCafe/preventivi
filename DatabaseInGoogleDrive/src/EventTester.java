 //Your package name will differ based on what you named your project.

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javafx.event.Event;
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
import org.jsoup.*;

public class EventTester extends Application {

    private TableView<Event> table = new TableView<Event>(); //This will hold the values we use to create our table in JavaFX.
       
    /**
     * The main method is used to launch our application.
     * @param args main method.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Start method, used to run the application. 
     * @param stage the stage that will be viewed by the user.
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        List<Event> allEvents = new ArrayList<Event>();
        Event x = new Event("","","","","","");
        allEvents = x.create(); //This will use our create method to access Jsoup and gather our List of Events.
        
        ObservableList<Event> data = FXCollections.observableArrayList();
        
        for (Event y : allEvents) {
            data.add(y); //Adds our events to our data set.
        }
        
        Scene scene = new Scene(new Group());
        stage.setTitle("Berry College Events"); 
        stage.setWidth(2000);
        stage.setHeight(1000);
 
        final Label label = new Label("Upcoming Events");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);

        //The following columns will use your functions to get values. So, if you change your function names, be sure to change them here as well.
        //Essentially, they need to be getFunctionName(). For example, I have a function getSName(), which is used by the first column to gather values.
 
        TableColumn eventCol = new TableColumn("Event Name");
        eventCol.setMinWidth(100);
        eventCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("sName")); 
 
        TableColumn statCol = new TableColumn("Status");
        statCol.setMinWidth(100);
        statCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("sStatus"));
 
        TableColumn groupCol = new TableColumn("Group");
        groupCol.setMinWidth(100);
        groupCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("sGroup"));
        
        TableColumn datingCol = new TableColumn("Date/Time");
        datingCol.setMinWidth(200);
        datingCol.setCellValueFactory(
                new PropertyValueFactory<Event, Date>("DateTime"));
        
        TableColumn descCol = new TableColumn("Description");
        descCol.setMinWidth(300);
        descCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("sDesc"));
        
        TableColumn locationCol = new TableColumn("Location");
        locationCol.setMinWidth(300);
        locationCol.setCellValueFactory(
                new PropertyValueFactory<Event, String>("sLoc"));

 
        FilteredList<Event> flEvent = new FilteredList(data, p -> true);
        table.setItems(flEvent);
        table.getColumns().addAll(eventCol, statCol, groupCol, datingCol, descCol, locationCol);
 
        
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Event Name", "Status", "Group", "Date", "Location");
        choiceBox.setValue("Event Name");


        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue()) //Switch on choiceBox value
            {
                case "Event Name":
                    flEvent.setPredicate(p -> p.getSName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Event Name.
                    break;
                case "Status":
                    flEvent.setPredicate(p -> p.getSStatus().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Status.
                    break;
                case "Group":
                    flEvent.setPredicate(p -> p.getSGroup().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Group.
                    break;
                case "Date":
                    flEvent.setPredicate(p -> p.getSDate().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Date. 
                    break;
                case "Location":
                    flEvent.setPredicate(p -> p.getSLoc().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by Status.
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            //reset table and textfield when new choice is selected
            if (newVal != null)
            {
                textField.setText("");
                flEvent.setPredicate(null); //This is same as saying flPerson.setPredicate(p->true);
            }
        });
        HBox hBox = new HBox(choiceBox, textField); //Add choiceBox and textField to hBox
        hBox.setAlignment(Pos.CENTER); //Center HBox
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hBox);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
}
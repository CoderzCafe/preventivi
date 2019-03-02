package radiobutton;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonTest1 extends Application {

    RadioButton radioButtonData = null;

    @Override
    public void start(Stage stage) {

        Label showSelectedRadioButton = new Label();
        showSelectedRadioButton.setStyle("-fx-text-weight: bold;");

        Label text = new Label("Plese select one");
        text.setStyle("-fx-text-weight: bold;");

        VBox vBox = new VBox(10);

        HBox hBox = new HBox(10);

        /**
         * first create toggle group for
         * multiple radio button**/

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton rb1 = new RadioButton("One");
        rb1.setToggleGroup(toggleGroup);
        RadioButton rb2 = new RadioButton("Two");
        rb2.setToggleGroup(toggleGroup);
        RadioButton rb3 = new RadioButton("Three");
        rb3.setToggleGroup(toggleGroup);

        hBox.getChildren().addAll(rb1, rb2, rb3);

//        radioButtonData = (RadioButton) toggleGroup.getSelectedToggle();
//        showSelectedRadioButton.setText(radioButtonData.getUserData().toString());
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (toggleGroup.getSelectedToggle() != null) {
                    radioButtonData = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                    showSelectedRadioButton.setText(radioButtonData.getText());
                }
            }
        });

        vBox.getChildren().addAll(text, hBox, showSelectedRadioButton);

        stage.setScene(new Scene(vBox, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

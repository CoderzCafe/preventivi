package toggleButton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToggleButtonTest1 extends Application {

    @Override
    public void start(Stage stage) {

        Label label = new Label();

        ToggleButton top = new ToggleButton("Top");
        ToggleButton right = new ToggleButton("Right");
        ToggleButton bottom = new ToggleButton("Bottom");
        ToggleButton left = new ToggleButton("Left");

        top.setOnAction(event -> {
            label.setText("Top");
        });
        right.setOnAction(event -> {
            label.setText("Right");
        });
        bottom.setOnAction(event -> {
            label.setText("Bottom");
        });
        left.setOnAction(event -> {
            label.setText("Left");
        });

        HBox hBox1 = new HBox(10);
        hBox1.getChildren().addAll(top, right);

        HBox hBox2 = new HBox(10);
        hBox2.getChildren().addAll(left, bottom);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(hBox1, hBox2, label);


        /** get the selected toggle **/
        ToggleGroup tg = new ToggleGroup();
        top.setToggleGroup(tg);
        right.setToggleGroup(tg);
        bottom.setToggleGroup(tg);
        left.setToggleGroup(tg);

//        ToggleButton selectedToggleButton = (ToggleButton) tg.getSelectedToggle();
//        label.setText(String.valueOf(selectedToggleButton.getText()));

        stage.setScene(new Scene(vBox, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

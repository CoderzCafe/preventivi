package button;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonTest1 extends Application {

    //  icon absolute url "/home/shine/IdeaProjects/JavaFxTests/src/icons" in my pc
    private String URL = String.valueOf(getClass().getResource("/icons/click.png"));

    @Override
    public void start(Stage stage) {

        Label label = new Label("No button is clicked");

        //  simple button
        Button button = new Button("Click");
        button.setPrefSize(100, 50);

        /** style the button **/
        button.setStyle("-fx-border-color: black;" +
                "-fx-background-color: #3674e2;" +
                "-fx-text-fill: #ffffff;");

        //  button with icon
        Button button1 = new Button("Click");
        button1.setGraphicTextGap(5);
        button1.setGraphic(new ImageView(new Image(URL)));
        button1.setPrefSize(100, 70);


        /** set button action events **/
        button.setOnAction(event -> {
            label.setText("Click is clicked");
        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText("Click with icon is clicked");
            }
        });

        //  layout
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.getChildren().addAll(button, button1, label);

        //  creating scene
        Scene scene = new Scene(vBox, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

package javafxskeleton;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class HelloWorld extends Application {

    public void start(Stage stage) {
        VBox vBox = new VBox(10);
        Label label = new Label("Hello World");

        vBox.getChildren().add(label);
        Scene scene = new Scene(vBox, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

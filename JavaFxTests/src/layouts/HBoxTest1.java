package layouts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxTest1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(10);
        hBox.setManaged(true);
        Label label1 = new Label("One");
        Label label2 = new Label("Two");
        Label label3 = new Label("Three");
        Label label4 = new Label("Four");
        Label label5 = new Label("Five");
        Label label6 = new Label("Six");
        Label label7 = new Label("Seven");
        Label label8 = new Label("Eight");
        Label label9 = new Label("Nine");
        Label label10 = new Label("Ten");

        hBox.getChildren().addAll(label1, label2, label3, label4, label5,
                label6, label7, label8, label9, label10);

        Scene scene = new Scene(hBox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

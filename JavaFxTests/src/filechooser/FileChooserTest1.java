package filechooser;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FileChooserTest1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox(10);
        HBox hBox = new HBox(10);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

package label;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LabelTest1 extends Application {

    @Override
    public void start(Stage stage) {
        /** vBox layout
         * that will show all controls in vertical views
         * spacing will set at 10 -> vGap = 10**/
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_CENTER);

        /** Label with text **/
        Label text = new Label();
        text.setText("Label With Text");

        //  label with only image
        Label pic = new Label();
        pic.setGraphic(new ImageView(new Image(String.valueOf(getClass()
                .getResource("/icons/house.png")))));

        // label with icons
        Label icon = new Label("Home");
        icon.setGraphicTextGap(5);   // gap between the icons and the text
        icon.setGraphic(new ImageView(new Image(String.valueOf(getClass()
                .getResource("/icons/house.png")))));
        icon.setStyle("-fx-font-weight: bold;");    // in line css

        //  adding all the nodes to the vBox layout
        vBox.getChildren().addAll(text, pic, icon);

        //  adding the layout to the scene
        Scene scene = new Scene(vBox, 300, 300);

        //  add the scene on the stage
        stage.setScene(scene);
        //  showing the stage
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

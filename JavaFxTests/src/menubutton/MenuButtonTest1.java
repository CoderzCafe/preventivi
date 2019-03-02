package menubutton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuButtonTest1 extends Application {

    //  icon absolute url "/home/shine/IdeaProjects/JavaFxTests/src/icons" in my pc
    private String URL = String.valueOf(getClass().getResource("/icons/click.png"));

    @Override
    public void start(Stage stage) {
        /**
         * first create all menu items
         * then add the items on the
         * menu button**/
        // layout
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        MenuButton menuButton = new MenuButton("Home Options");
        menuButton.setGraphic(new ImageView(new Image(String.valueOf(getClass().getResource("/icons/house.png")))));

        //  creating menu items
        MenuItem item1 = new MenuItem("Item1");
        //  if you want to set the icon
        item1.setGraphic(new ImageView(new Image(URL)));
        MenuItem item2 = new MenuItem("Item2");
        MenuItem item3 = new MenuItem("Item3");
        MenuItem item4 = new MenuItem("Item4");
        MenuItem item5 = new MenuItem("Item5");

        //  adding the items on the menu button
        menuButton.getItems().addAll(item1, item2, item3, item4, item5);

        //  adding menubutton on the scenes layout
        vBox.getChildren().add(menuButton);

        Scene scene = new Scene(vBox, 300, 300);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

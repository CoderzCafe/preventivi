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

public class MenuButtonTest2 extends Application {

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

        /**creating menu items
         *  and its action**/
        MenuItem item1 = new MenuItem("Item1");
        item1.setOnAction(event -> {
            menuButton.setText(item1.getText());
            menuButton.setGraphic(item1.getGraphic());
        });
        item1.setGraphic(new ImageView(new Image(URL)));
        MenuItem item2 = new MenuItem("Item2");
        item2.setOnAction(event -> {
            menuButton.setText(item2.getText());
            menuButton.setGraphic(item2.getGraphic());
        });
        MenuItem item3 = new MenuItem("Item3");
        item3.setOnAction(event -> {
            menuButton.setText(item3.getText());
            menuButton.setGraphic(item3.getGraphic());
        });
        MenuItem item4 = new MenuItem("Item4");
        item4.setOnAction(event -> {
            menuButton.setText(item4.getText());
            menuButton.setGraphic(item4.getGraphic());
        });
        MenuItem item5 = new MenuItem("Item5");
        item5.setOnAction(event -> {
            menuButton.setText(item5.getText());
            menuButton.setGraphic(item5.getGraphic());
        });

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

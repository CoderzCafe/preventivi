package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button clickButton;

    @FXML
    void clickButtonAction(ActionEvent e) {
        Image image = new Image(String.valueOf(getClass().getResource("/res/warning.png")));
        Notifications.create()
                .title("Javafx Notifications")
                .text("Hello this is the text")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .graphic(new ImageView(image))
                .onAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Notification is clicked");
                    }
                })
                .darkStyle()
        .showConfirm();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

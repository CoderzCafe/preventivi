package sticky;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label menuButton;

    @FXML
    private Label addButton;

    @FXML
    private TextArea notesArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = image = new Image(String.valueOf(getClass().getResource("/sticky/icons/plus.png")));;
        ImageView addIcon = new ImageView(image);
        addButton.setGraphic(addIcon);
    }
}

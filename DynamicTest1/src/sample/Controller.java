package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //  imageFile
    private static File imageFile = null;
    @FXML
    private GridPane imageContainerGridPane;

    @FXML
    private ImageView productsImage;

    @FXML
    private Button uploadImageButton;

    @FXML
    private TextField productsIdText;

    @FXML
    private TextField productsNameText;

    @FXML
    private TextField productsPriceText;

    @FXML
    private MenuButton productsType;
    @FXML
    private MenuItem item1;
    @FXML
    private MenuItem item2;
    @FXML
    private MenuItem item3;
    @FXML
    void item1Action(ActionEvent event) {
        productsType.setText(item1.getText());
    }
    @FXML
    void item2Action(ActionEvent event) {
        productsType.setText(item2.getText());
    }
    @FXML
    void item3Action(ActionEvent event) {
        productsType.setText(item3.getText());
    }

    @FXML
    private TextArea productsDetailsText;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    void clearButtonAction(ActionEvent event) {
        clearAllFields();
    }

    @FXML
    void uploadImageAction(ActionEvent event) {
        System.out.println("CLIcke click");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("*.jpg", "*.png", "*.jpeg", "*.svg");
        imageFile = fileChooser.showOpenDialog(null);

        if (imageFile == null) {
            System.out.println("Cancel button is clicked");
        } else {
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(imageFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                productsImage.setImage(image);
                productsImage.setFitWidth(152);
                productsImage.setFitHeight(148);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        Database db = new Database();
        int id = Integer.valueOf(productsIdText.getText());
        String name = productsNameText.getText();
        String price = productsPriceText.getText();
        String type = productsType.getText();
        String details = productsDetailsText.getText();
        File imageFile = this.imageFile;

        db.insertData(id, name, price, type, details, imageFile);
        clearAllFields();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void clearAllFields() {
        productsIdText.setText("");
        productsNameText.setText("");
        productsDetailsText.setText("");
        productsType.setText("Select product type");
        productsPriceText.setText("");
        productsImage.setImage(null);
    }
}

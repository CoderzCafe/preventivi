package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ObservableList<String> typeList = FXCollections.observableArrayList();
    private ObservableList<Product> productsList = FXCollections.observableArrayList();
    private File file = null;
    private Image image = null;
    private BufferedImage bufferedImage = null;

    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField priceText;

    @FXML
    private TextArea descriptionText;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private ImageView imageView;

    @FXML
    private Button uploadImage;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;
    @FXML
    private TextField addType;

    @FXML
    void clearButtonAction(ActionEvent event) {
        idText.setText("");
        nameText.setText("");
        priceText.setText("");
        typeChoiceBox.getSelectionModel().clearSelection();
        descriptionText.setText("");
        imageView.setImage(null);
    }

    @FXML
    void uploadImageAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        file = fileChooser.showOpenDialog(null);
        if (!(file == null)) {
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitHeight(imageView.getFitHeight());
            imageView.setFitWidth(imageView.getFitWidth());
            imageView.setPreserveRatio(false);
            System.out.println(file.toString());
        } else {
            System.out.println("cancel");
        }
    }

    @FXML
    void addButtonAction(ActionEvent event) throws IOException {
        String id = idText.getText();
        String name = nameText.getText();
        long price = Long.valueOf(priceText.getText());
        String type = typeChoiceBox.getSelectionModel().getSelectedItem();
        String description = descriptionText.getText();

        addToExcel(new Product(id, name, price, type, description, Files.readAllBytes(file.toPath())));
//        excel.insertIntoSheet(id, name, price, type, description, file.getAbsolutePath());
//        excel.saveFile();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeList.addAll("Type1", "Type2", "Type3", "Type4");
        typeChoiceBox.setItems(typeList);
    }

    /** convert buffered image to byte array **/
    public static byte[] getBufferedImageByte(BufferedImage image) {
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }

    public ObservableList<Product> addToExcel(Product product) {
        productsList.add(product);
        return productsList;
    }
}

package main;

import com.dropbox.core.DbxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.model.Dropbox;
import main.model.Excel;
import main.model.Product;
import org.controlsfx.control.Notifications;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.nio.file.Files;
import java.util.ResourceBundle;

public class ControllerAddingProducts implements Initializable {
    private ObservableList<Product> productsList = FXCollections.observableArrayList();
    private final Font ROBOTO_BOLD = Font.loadFont((String.valueOf(getClass().getResource("/res/fonts/RobotoBold.ttf"))), 18);
    private final Font ROBOTO_REGULAR = Font.loadFont((String.valueOf(getClass().getResource("/res/fonts/RobotoRegular.ttf"))), 16);
    private File file = null;
    private ObservableList<String> choiceBoxItem = FXCollections.observableArrayList();

    @FXML
    private AnchorPane addProductsMainPane;
    @FXML
    private Label brandName;
    @FXML
    private Label createProductsHeader;

    @FXML
    private Label idLabel;
    @FXML
    private TextField idText;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameText;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField priceText;
    @FXML
    private Label typeLabel;
    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Button uploadImageButton;
    @FXML
    private ImageView uploadImageView;
    @FXML
    private Pane imageContainerPane;
    @FXML
    private Button submitButton;
    @FXML
    private Button clearButton;
    @FXML
    private Label backButton;

    @FXML
    void backButtonAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ui/mainview.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.hide();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void clearButtonAction(ActionEvent e) {
        nameText.setText("");
        priceText.setText("");
        typeChoiceBox.getSelectionModel().clearSelection();
        descriptionText.setText("");
    }

    @FXML
    void uploadImageAction(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("*.jpg", "*.png", "*.jpeg", "*.bmp", "*.svg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                uploadImageView.setImage(image);
                uploadImageView.setFitWidth(imageContainerPane.getWidth());
                uploadImageView.setFitHeight(imageContainerPane.getHeight());
                uploadImageView.setPreserveRatio(false);
            } catch (IOException e1) {
                Notifications.create()
                        .title("Please upload an image")
                        .text("File is not set here\n"+e1.getMessage())
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.CENTER)
                        .graphic(null)
                        .onAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Notification is clicked");
                            }
                        })
                        .darkStyle()
                        .showConfirm();
            }
        } else {
            Main.notification("Cancel button", "Please select an image");
        }
    }

    @FXML
    void submitButtonAction(ActionEvent e) throws IOException, DbxException {
        Dropbox dropbox = new Dropbox();
        Excel excel = new Excel("products.xlsx");
        String id = idText.getText();
        String name = nameText.getText();
        long price = Long.valueOf(priceText.getText());
        String type = (String) typeChoiceBox.getSelectionModel().getSelectedItem();
        String description = descriptionText.getText();
        String imagePath = file.getAbsolutePath();

        productsList.add(new Product(id, name, price, type, description, imagePath));

        excel.setFileName("products.xlsx");
        for (int i=0; i<productsList.size(); i++) {
            excel.insertToSheet(productsList.get(i).getId(),
                            productsList.get(i).getName(),
                            productsList.get(i).getPrice(),
                            productsList.get(i).getType(),
                            productsList.get(i).getDescription(),
                            productsList.get(i).getImagePath());
        }
        excel.saveFile();
        dropbox.uploadFile("products.xlsx");
//        printAllProduct();

    }

    public void printAllProduct() throws IOException {
        for (int i=0; i<productsList.size(); i++) {
            System.out.println("Product" +i);
            System.out.println("Id : " +productsList.get(i).getId());
            System.out.println("Name : " +productsList.get(i).getName());
            System.out.println("Price : " +productsList.get(i).getPrice());
            System.out.println("Type : " +productsList.get(i).getType());
            System.out.println("Description : " +productsList.get(i).getDescription());
            System.out.println("Image : " + Files.readAllBytes(new File(productsList.get(i).getImagePath()).toPath()));
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addProductsMainPane.setStyle("-fx-background-image: url('/res/pic/page.png');");

        brandName.setFont(Font.loadFont(getClass().getResource("/res/fonts/SpicyRice.ttf").toString(), 40));
        brandName.setTextFill(Paint.valueOf("#DB1E1E"));

        createProductsHeader.setFont(Font.loadFont(getClass().getResource("/res/fonts/LatoRegular.ttf").toString(), 32));
        createProductsHeader.setLineSpacing(2.0);

        /** styling all the labels and textField and choice box **/
        idLabel.setFont(Font.loadFont((String.valueOf(getClass().getResource("/res/fonts/RobotoRegular.ttf"))), 18));
        idText.setFont(ROBOTO_REGULAR);
        idText.setStyle("-fx-background-insets: 1 0 5 0;");
        idText.setStyle("-fx-border-weight: 5");

        nameLabel.setFont(Font.loadFont((String.valueOf(getClass().getResource("/res/fonts/RobotoRegular.ttf"))), 18));
        nameText.setFont(ROBOTO_REGULAR);
        nameText.setStyle("-fx-background-insets: 1 0 5 0;");
        nameText.setStyle("-fx-border-weight: 5");

        priceLabel.setFont(Font.loadFont((String.valueOf(getClass().getResource("/res/fonts/RobotoRegular.ttf"))), 18));
        priceText.setFont(ROBOTO_REGULAR);
        typeLabel.setFont(Font.loadFont((String.valueOf(getClass().getResource("/res/fonts/RobotoRegular.ttf"))), 18));
        descriptionLabel.setFont(Font.loadFont((String.valueOf(getClass().getResource("/res/fonts/RobotoRegular.ttf"))), 18));
        descriptionText.setFont(ROBOTO_REGULAR);

        uploadImageButton.setFont(Font.loadFont(getClass().getResource("/res/fonts/LatoBlack.ttf").toString(), 18));
        uploadImageButton.setTextFill(Paint.valueOf("#ffffff"));

        submitButton.setFont(ROBOTO_BOLD);
        submitButton.setTextFill(Paint.valueOf("#ffffff"));
        clearButton.setFont(ROBOTO_BOLD);
        clearButton.setTextFill(Paint.valueOf("#ffffff"));

        /** choice box items **/
        typeChoiceBox.getItems().addAll("Matrimony", "Baptism");

        /** back button **/
        backButton.setGraphic(ControllerMain.getImage("back.png"));
    }

    private void getAllData() {
        String name = nameText.getText();
        String price = priceText.getText();
        String type = typeChoiceBox.getSelectionModel().getSelectedItem().toString();
        String description = descriptionText.getText();

        System.out.println(name +"\n" +price +"\n" +type+ "\n" +description);
    }

}
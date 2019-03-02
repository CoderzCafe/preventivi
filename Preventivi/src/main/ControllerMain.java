package main;

import com.dropbox.core.DbxException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.model.Dropbox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerMain implements Initializable {
    /** custom fonts are here **/
    private final Font SPICY_RICE = Font.loadFont(ControllerMain.class.getResource("/res/fonts/SpicyRice.ttf").toString(), 64);
    private final Font ROBOTO_BOLD = Font.loadFont(ControllerMain.class.getResource("/res/fonts/RobotoBold.ttf").toString(), 18);

    /** landing page **/
    @FXML
    private Label brandNameText;
    @FXML
    private Label addProductsIcon;
    @FXML
    private Label addProductsTitle;
    @FXML
    private Pane addProductsPane;

    @FXML
    private Pane productsQuotationPane;
    @FXML
    private Label productsQuotationIcon;
    @FXML
    private Label productsQuotationTitle;
    @FXML
    private Pane adminPane;
    @FXML
    private Label adminIcon;
    @FXML
    private Label adminTitle;


    @FXML
    void addProductAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ui/addingproducts.fxml"));
        Scene newScene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    void productsQuotationAction(MouseEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("ui/productsquotation.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void adminPanelAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ui/admin.fxml"));

        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        brandNameText.setFont((Font) SPICY_RICE);
        brandNameText.setLayoutX(131);
        brandNameText.setLayoutY(129);
        brandNameText.setTextFill(Paint.valueOf("#DB1E1E"));

        /** landing page menu items icons and fonts **/
        addProductsPane.setLayoutX(20);
        addProductsPane.setLayoutY(326);
        addProductsIcon.setGraphic(getImage("add_products.png"));
        addProductsTitle.setFont(ROBOTO_BOLD);
        productsQuotationIcon.setGraphic(getImage("product_quote.png"));
        productsQuotationTitle.setFont(ROBOTO_BOLD);
        productsQuotationPane.setLayoutX(239);
        productsQuotationPane.setLayoutY(326);
        adminIcon.setGraphic(getImage("admin.png"));
        adminTitle.setFont(ROBOTO_BOLD);
        adminPane.setLayoutX(458);
        adminPane.setLayoutY(326);

    }

    public static ImageView getImage(String imageName) {
        Image image = new Image(ControllerMain.class.getResource("/res/pic/"+imageName).toString());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        return imageView;
    }
}

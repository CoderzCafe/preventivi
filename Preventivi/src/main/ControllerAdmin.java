package main;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdmin implements Initializable {

    private static final Font LATO_BOLD_16 = Font.loadFont(ControllerAdmin.class.getResource("/res/fonts/LatoBold.ttf").toString(), 16);

    @FXML
    private AnchorPane adminPage;
    @FXML
    private Label brandName;
    @FXML
    private Pane leftMenuPane;
    @FXML
    private Pane listOfProductsMenu;
    @FXML
    private Label listOfProductsTitle;
    @FXML
    private Pane listOfQuotationMenu;
    @FXML
    private Label listOfQuotationTitle;
    @FXML
    private Pane addCategoryMenu;
    @FXML
    private Label addCategoryTitle;
    @FXML
    private Pane listOfQuotationPane;
    @FXML
    private Pane addCategoryPane;
    @FXML
    private Pane listOfProductsPane;
    @FXML
    private Label backButton;


    @FXML
    void backButtonAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ui/mainview.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminPage.setStyle("-fx-background-image: url('/res/pic/page.png');");
        leftMenuPane.setStyle("-fx-background-color: transparent;");
        brandName.setFont(Font.loadFont(getClass().getResource("/res/fonts/SpicyRice.ttf").toString(), 35));
        brandName.setTextFill(Paint.valueOf("#DB1E1E"));

        listOfProductsTitle.setFont(LATO_BOLD_16);
        listOfQuotationTitle.setFont(LATO_BOLD_16);
        addCategoryTitle.setFont(LATO_BOLD_16);

        backButton.setGraphic(new ImageView(new Image("/res/pic/back.png")));
    }
}
